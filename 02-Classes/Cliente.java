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
			//Socket conexao = new Socket("123.45.67.89",12321);   //ip e porta
			//colocar um receptor pra enviar pra janela
			Janela janela = new Janela(/*conexao*/);
			ArrayList<String> salas = new ArrayList(6);
			salas.add("oi");
			salas.add("tchau");
			salas.add("boa tarde");
			janela.mostrarSalas(salas);

			/*for(;;)
			{
				Enviavel recebido = receptor.readObject();

				if(recebido instanceof SalasDisponiveis)
				   janela.mostrarSalas(((SalasDisponiveis)recebido).getNomeSala());

					else if(recebido instanceof AvisoDeSalaCheia)
						janela.mostrarAvisoDeErro("A sala escolhida est� cheia!");

						else if(recebido instanceof AvisoDeSalaInvalida)
							janela.mostrarAvisoDeErro("A sala escolhida � inv�lida!");

							else if(recebido instanceof AvisoDeSalaEscolhidaComSucesso)
								break;
			}

			janela.mostrarEscolhaDeNome();
			for(;;)
			{
				Enviavel recebido = receptor.readObject();

				if(recebido instanceof AvisoDeNomeEscolhidoComSucesso)
					break;

				else if (recebido instanceof AvisoDeNomeExistente)
					janela.mostrarAvisoDeErro("O nome escolhido j� est� sendo usado na sala");
			}

			Enviavel recebido = receptor.readObject();

			if(recebido instanceof UsuariosNaSala)
			{
				janela.mostrarDesingDeChat(((UsuariosNaSala)receptor).getUsuarios());

				for(;;)
				{
					Enviavel recebido = receptor.readObject();

					if(recebido instanceof Mensagem)
						janela.mostra(((Mensagem)recebido).getMensagem());

					else if(recebido instanceof AvisoDeEntradaNaSala)
						janela.mostraEntrada(((AvisoDeEntradaNaSala)recebido).getRemetente());

					else if(recebido instanceof AvisoDeSaidaDaSala)
						janela.mostraSaida(((AvisoDeSaidaDaSala)recebido).getRemetente());
				}
			}*/
	    }
	    catch(Exception err)
	    {}
	}
}