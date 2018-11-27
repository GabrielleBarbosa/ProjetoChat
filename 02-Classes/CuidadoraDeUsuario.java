import java.io.*;
import java.net.*;

public class CuidadoraDeUsuario extends Thread
{
	private Socket conexao;
	private Salas salas;
	private Usuario usuario;

	public CuidadoraDeUsuario(Socket conexao, Salas salas) throws Exception
	{
		this.conexao = conexao;
		this.salas = salas;
	}

	public void run()
	{
		instanciarUsuario();
		//loops for(;;) --> fim no break(pedido para sair da sala)
		Enviavel recebido=null;
		do
		{
			recebido=usuario.recebe();
			if(recebido instanceof Mensagem)
				recebido.getDestinatario().envia(recebido);
		}
		while(!(recebido instanceof PedidoParaSairDaSala));

		//remover o this.usuario da sala
		//mandar para todos da sala diferentes do this.usuario --> new AvisoDeSaidaDaSala(this.usuario.getNick())
		usuario.getSala().removerUsuario(usuario);

		for(int i=0; i<this.usuario.getSala().getQtdOcupado(); i++)
			this.usuario.getSala().getUsuario(i).envia(new AvisoDeSaidaDaSala(this.usuario));
		this.usuario.fechaTudo();
	}

	private void instanciarUsuario()
	{
		// declarar e instanciar ObjectOutputStream e ObjectInputStream
		ObjectOutputStream OOS = new ObjectOutputStream(conexao.getOutputStream());
		ObjectInputStream OIS = new ObjectInputStream(conexao.getInputStream());
		Sala sala;
		// interagir com o usuário via OOS e OIS para(até) descobrir sala desejada, eventualmente informando sala cheia
		// procurar em salas a sala com o nome desejado
		for(;;)
		{
			OOS.writeObject(new SalasDisponiveis(salas));
			Object obj = OIS.readObject();

			if(obj instanceof EscolhaDeSala)
			{
				String s = ((EscolhaDeSala)obj).getNomeSala();

				if(!(salas.existeSala(s)))
					OIS.writeObject(new AvisoDeSalaInvalida());
				else
				{
					sala = salas.getSala(s);

					if(sala.isCheia())
					   OOS.writeObject(new AvisoDeSalaCheia());
					else
						break;
				}

			}
		}

		String nome;
		// interagir com o usr via OOS e OIS para(até) descobrir nome desejado, eventualmente informando nome inválido ou já usado
		for(;;)
		{
			Object obj  = OIS.readObject();

			if(obj instanceof String)
			{
				nome = (String)obj;
				if(sala.existeNome(nome))
					OOS.writeObject(new AvisoDeNomeExistente());
				else
					break;
			}
		}
		// instanciar um Usuario, fornecendo conexao, OOS, OIS, sala e nome
		usuario = new Usuario(nome,conexao,sala,OOS,OIS);

		// enviar para todos os usuarios da sala new AvisoDeEntradaNaSala(usuario.getNome())//this.usuario.envia(new AvisoDeEntradaNaSala())
		for(int i=0;i<sala.getQtdOcupado();i++)
			sala.getUsuario(i).envia(new AvisoDeEntradaNaSala(sala.getUsuario(i)));

		for(int i=0;i<sala.getQtdOcupado();i++)
			sala.getUsuario(i).envia(new AvisoDeEntradaNaSala(this.usuario));

		// enviar para o usuario muitos new AvisoDeEntradaNaSala(i), onde i é o nome de algum usuario que já estava na sala //i.envia(new AvisoDeEntradaNaSala(usuario.getNome())
		for(int i=0;i<sala.getQtdOcupado();i++)
			usuario.envia(new AvisoDeEntradaNaSala(sala.getUsuario(i)));
		// incluir o usuario na sala
		sala.adicionarUsuario(usuario);
	}
}