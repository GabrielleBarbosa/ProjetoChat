import java.io.*;
import java.net.*;

public class CuidadoraDeUsuario extends Thread
{
	private Usuario usuario;

	public CuidadoraDeUsuario(Socket conexao, Salas salas) throws Exception
	{
		// declarar e instanciar ObjectOutputStream e ObjectInputStream
		ObjectOutputStream OOS = new ObjectOutputStream();
		ObjectInputStream OIS = new ObjectInputStream();
		Sala sala;
		// interagir com o usu�rio via OOS e OIS para(at�) descobrir sala desejada, eventualmente informando sala cheia
		// procurar em salas a sala com o nome desejado
		for(;;)
		{
			OOS.writeObject(new SalasDisponiveis(salas));
			Object obj = OIS.readObject();

			if(obj instanceof EscolhaDeSala)
			{
				String s = EscolhaDeSala.getNomeSala();

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
		// interagir com o usr via OOS e OIS para(at�) descobrir nome desejado, eventualmente informando nome inv�lido ou j� usado
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
		this.usuario.envia(new AvisoDeEntradaNaSala(usuario))
		// enviar para o usuario muitos new AvisoDeEntradaNaSala(i), onde i � o nome de algum usuario que j� estava na sala //i.envia(new AvisoDeEntradaNaSala(usuario.getNome())
		for(int i=0;i<sala.getQtdOcupado;i++)
			sala.getUsuario(i).envia(new AvisoDeEntradaNaSala(sala.getUsuario(i).getNome()));
		// incluir o usuario na sala
		sala.adicionar(usuario);
	}

	public void run()
	{
		//loops for(;;) --> fim no break(pedido para sair da sala)
		Enviavel recebido=null;
		do
		{
			recebido=usuario.recebe();

			// se for PedidoParaSairDaSala n�o fazer nada, se for mensagem particular s� escrever se for para este usuario
			// receber msgs, avisos de entrada e de sa�da da sala
		}
		while(!(recebido instanceof PedidoParaSairDaSala))

		//remover o this.usuario da sala
		//mandar para todos da sala diferentes do this.usuario --> new AvisoDeSaidaDaSala(this.usuario.getNick())
		usuario.getSala().removerUsuario(usuario);
		usuario.envia(new AvisoDeSaidaDaSala(this.usuario.getNick()))
		this.usuario.fechaTudo();
	}
}