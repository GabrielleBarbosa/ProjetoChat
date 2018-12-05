import java.io.*;
import java.net.*;
import java.util.ArrayList;

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
		try
		{
			instanciarUsuario();

			Enviavel recebido = null;
			do
			{
				recebido = usuario.recebe();

				System.out.println("aaaa");
				if(recebido instanceof Mensagem)
				{
					if(((Mensagem)recebido).getDestinatario().equals(""))
					{
						for(int i=0; i<this.usuario.getSala().getQtdOcupado(); i++)
							if(this.usuario.getSala().getUsuario(i) != this.usuario)
								this.usuario.getSala().getUsuario(i).envia(recebido);
					}
					else
					{
						System.out.println("Privada");
						String nomeDestinatario = ((Mensagem)recebido).getDestinatario();
						System.out.println("Destinatario:" + nomeDestinatario);
						this.usuario.getSala().getUsuario(nomeDestinatario).envia(recebido);
						System.out.println("Enviada");
					}

				}
			}
			while(!(recebido instanceof PedidoParaSairDaSala));

			//remover o this.usuario da sala
			//mandar para todos da sala diferentes do this.usuario --> new AvisoDeSaidaDaSala(this.usuario.getNome())
			for(int i=0; i<this.usuario.getSala().getQtdOcupado(); i++)
				this.usuario.getSala().getUsuario(i).envia(new AvisoDeSaidaDaSala(this.usuario.getNome()));

			usuario.getSala().removerUsuario(usuario);

			this.usuario.fechaTudo();

		}
		catch(Exception err)
		{}
	}

	private void instanciarUsuario() throws Exception
	{
		// declarar e instanciar ObjectOutputStream e ObjectInputStream
		ObjectOutputStream OOS = new ObjectOutputStream(conexao.getOutputStream());
		ObjectInputStream OIS = new ObjectInputStream(conexao.getInputStream());
		SalaUsuario sala;
		// interagir com o usuário via OOS e OIS para(até) descobrir sala desejada, eventualmente informando sala cheia
		// procurar em salas a sala com o nome desejado

		ArrayList<String> listaNomeSalas = new ArrayList<String>(salas.getQtdSalas());

		for(int i=0; i<salas.getQtdSalas(); i++)
			listaNomeSalas.add(salas.getSala(i).getNome());



		OOS.writeObject(new SalasDisponiveis(listaNomeSalas));
		for(;;)
		{
			Object obj = OIS.readObject();

			if(obj instanceof EscolhaDeSala)
			{
				String s = ((EscolhaDeSala)obj).getNomeSala();

				if(!(salas.existeSala(s)))
					OOS.writeObject(new AvisoDeSalaInvalida());
				else
				{
					sala = salas.getSala(s);
					if(sala.isCheia())
					   OOS.writeObject(new AvisoDeSalaCheia());
					else
					{
						OOS.writeObject(new AvisoDeSalaEscolhidaComSucesso());
						break;
					}
				}

			}
		}
		String nome;
		// interagir com o usr via OOS e OIS para(até) descobrir nome desejado, eventualmente informando nome inválido ou já usado
		for(;;)
		{
			Object obj = OIS.readObject();

			if(obj instanceof EscolhaDeNome)
			{
				nome = ((EscolhaDeNome)obj).getNome();
				if(sala.existeNome(nome))
				{
					OOS.writeObject(new AvisoDeNomeExistente());
				}
				else
				{
					OOS.writeObject(new AvisoDeNomeEscolhidoComSucesso());
					break;
				}
			}
		}

		ArrayList<String> listaUsuariosSala = new ArrayList<String>(sala.getQtdMax());

		for(int i=0; i<sala.getQtdOcupado(); i++)
			listaUsuariosSala.add(sala.getUsuario(i).getNome());

		OOS.writeObject(new UsuariosNaSala(listaUsuariosSala));

		// instanciar um Usuario, fornecendo conexao, OOS, OIS, sala e nome
		usuario = new Usuario(nome,conexao,sala,OOS,OIS);

		// enviar para todos os usuarios da sala new AvisoDeEntradaNaSala(usuario.getNome())//this.usuario.envia(new AvisoDeEntradaNaSala())
		for(int i=0;i<sala.getQtdOcupado();i++)
			sala.getUsuario(i).envia(new AvisoDeEntradaNaSala(this.usuario.getNome()));

		// enviar para o usuario muitos new AvisoDeEntradaNaSala(i), onde i é o nome de algum usuario que já estava na sala //i.envia(new AvisoDeEntradaNaSala(usuario.getNome())
		for(int i=0;i<sala.getQtdOcupado();i++)
			usuario.envia(new AvisoDeEntradaNaSala(sala.getUsuario(i).getNome()));

		// incluir o usuario na sala
		sala.adicionarUsuario(usuario);
	}
}