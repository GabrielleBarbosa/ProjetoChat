import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Cliente //instancia janela
{
	public static void main(String[] args)
	{
		try
		{
			System.out.println("aaaaaaaaaaaaaa");
			Socket conexao = new Socket("123.45.67.89",12321);   //ip e porta
			Janela janela = new Janela(conexao);
	    }
	    catch(Exception err)
	    {}
	}
}