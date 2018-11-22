import java.io.*;

public class Servidor
{
	public static void Main(String[] args)
	{
		Salas salas = new Salas();
		ServerSocket pedido = new ServerSocket("123.45.67.89", 12321) // ip e conexao

		for(;;)
		{
			Socket conexao = pedido.accept();
			CuidadoraDeUsuario cuidadora = new CuidadoraDeUsuario();
			cuidadora.start();

			//aqui pode ter outras coisas
		}
	}
}