import java.io.*;
import java.net.*;

public class Servidor
{
	public static void main(String[] args)
	{
		try
		{
			Salas salas = new Salas();

			ServerSocket pedido = new ServerSocket(12321); // ip e porta

			for(;;)
			{
				Socket conexao = pedido.accept();
				CuidadoraDeUsuario cuidadora = new CuidadoraDeUsuario(conexao, salas);
				cuidadora.start();
				//aqui pode ter outras coisas
			}
		}
		catch(Exception err)
		{
			System.err.println(err.getMessage());
		}
	}
}