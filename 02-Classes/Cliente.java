import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.ArrayList;

public class Cliente //instancia janela
{
	public static void main(String[] args)
	{
		try
		{
			for(;;)
			{
				Socket conexao = new Socket("177.220.18.103",12321);   //ip e porta
				//colocar um receptor pra enviar pra janela
				Janela janela = new Janela(conexao);


				ObjectInputStream receptor = new ObjectInputStream(conexao.getInputStream());
				Enviavel recebido = null;
				for(;;)
				{
					recebido = (Enviavel)receptor.readObject();

					if(recebido instanceof SalasDisponiveis)
					   janela.mostrarSalas(((SalasDisponiveis)recebido).getNomeSala());

						else if(recebido instanceof AvisoDeSalaCheia)
							janela.mostrarAvisoDeErro("A sala escolhida está cheia!");

							else if(recebido instanceof AvisoDeSalaInvalida)
								janela.mostrarAvisoDeErro("A sala escolhida é inválida!");

								else
									break;
				}

				janela.mostrarEscolhaDeNome();
				for(;;)
				{
					recebido = (Enviavel)receptor.readObject();

					if(recebido instanceof AvisoDeNomeEscolhidoComSucesso)
						break;

					else if (recebido instanceof AvisoDeNomeExistente)
						janela.mostrarAvisoDeErro("O nome escolhido já está sendo usado na sala");
				}

				recebido = (Enviavel)receptor.readObject();

				if(recebido instanceof UsuariosNaSala)
				{
					janela.mostrarDesignDeChat(((UsuariosNaSala)recebido).getUsuarios());

					for(;;)
					{
						recebido = (Enviavel)receptor.readObject();

						if(recebido instanceof Mensagem)
						{
							if(((Mensagem)recebido).getDestinatario().equals(""))
							{
								System.out.println(((Mensagem)recebido).getRemetente());
								janela.mostra(((Mensagem)recebido).getMensagem(), ((Mensagem)recebido).getRemetente());
							}
							else
							{
								System.out.println("Privada");
								janela.mostraPriv(((Mensagem)recebido).getMensagem(), ((Mensagem)recebido).getRemetente());
								System.out.println("Enviada para mostrar");
							}
						}

						else if(recebido instanceof AvisoDeEntradaNaSala)
						{
							janela.mostraEntrada(((AvisoDeEntradaNaSala)recebido).getRemetente());
						}

						else if(recebido instanceof AvisoDeSaidaDaSala)
							janela.mostraSaida(((AvisoDeSaidaDaSala)recebido).getRemetente());


						else if(recebido instanceof PedidoParaSairDaSala)
							break;
					}

					janela.fecharTudo();
					receptor.close();
				}
			}

	    }
	    catch(Exception err)
	    {
			System.err.println(err.getMessage());
		}
	}
}