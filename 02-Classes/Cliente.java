import java.io.*;

public class Cliente //instancia janela
{
	public static void Main(String[] args)
	{
		Socekt conexao = new Socket("123.45.67.89",12321);   //ip e porta
		Janela janela = new Janela(conexao);
	}
}