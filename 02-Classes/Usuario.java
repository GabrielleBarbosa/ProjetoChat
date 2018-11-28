package usuario;
import enviavel.*;
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

	public Usuario(String nick, Socket s, Sala sa, ObjectOutputStream o, ObjectInputStream i) throws Exception //mandados pela thread
	{
		if(nick == null||s == null||sa == null ||i == null ||o == null)
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

	public Sala getSala()
	{
		return this.sala;
	}

	public void envia(Enviavel x) throws Exception
	{
		if(x == null)
			throw new Exception("Objeto enviado null");

		transmissor.writeObject(x);
		transmissor.flush();
	}

	public Enviavel recebe() throws Exception
	{
		Object obj = this.receptor.readObject();

		if(!(obj instanceof Enviavel))
			throw new Exception("Objeto lido não é Enviavel!!");

		return (Enviavel)obj;
	}

	public void fechaTudo() throws Exception
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

		if(this.conexao!=usu.conexao)
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