import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Usuario
{
 	protected String nome;
	protected Socket conexao;
	protected ObjectOutputStream transmissor;
	protected ObjectInputStream receptor;
	protected Sala sala;

	public Usuario(String nick, Socket s, Sala sa, ObjectOutputStream o, ObjectInputStream i)throws Exception //mandados pela thread
	{
		if(this.nome == null||this.skUsuario == null||this.sala == null ||this.receptor == null ||this.transmissor == null)
		   throw new Exception("PASSE TODOS OS PARÂMETROS!!!");

		//verificar parâmetros(existência da sala)

		nome = nick;
		conexao = s;
		sala = sa;
		transmissor = o;
		receptor = i;
	}

	public String getNome()
	{
		return this.nome;
	}

	public void envia(Enviavel x)
	{
		transmissor.writeObject(x);
		transmissor.flush();
	}

	public Enviavel recebe() throws Exception//vai ser em janela
	{
		return this.receptor.readObject();
	}

	public void fechaTudo()
	{
		this.transmissor.close();
		this.receptor.close();
		this.conexao.close();
	}


	public String toString()
	{
		return "Nome: "+this.nome;
	}

	public boolean equals (Object obj)
	{
		if (this==obj)
		    return true;

		if (obj==null)
		    return false;

		if (this.getClass()!=obj.getClass())
		    return false;

		Usuario usu = (Usuario) obj;


		if (this.nome!=usu.nome)
		    return false;

		if(this.skUsuario!=usu.skUsuario)
		   return false;

		if(this.receptor!= usu.receptor)
		   return false;

		if(this.transmissor!= usu.transmissor)
		   return false;

		return true;
	}

	public int hashCode()
	{
		int ret = 666;

		ret = ret*2 + this.nome.hashCode();

		ret = ret*2 + this.receptor.hashCode();

		ret = ret*2 + this.transmissor.hashCode();

		return ret;
	}
}