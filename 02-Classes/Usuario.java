import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;


/**
A classe Usuario � uma classe que representa(como nos mostra o nome) o pr�prio Usuario.
Nela encontramos m�todos como instanciar usuario recuperar a sala e nome do usuario.

@authors Felipe Melchior de Britto, Gabrielle da Silva barbosa e Christovam Alves Lemos.
@since 2018.
*/
public class Usuario
{
	/**
	String que armazena o nome do usuario.
	*/
 	protected String nome;
 	/**
	Socket que representa a conex�o com o servidor.
	*/
	protected Socket conexao;
	/**
	Vari�vel que representa a transmiss�o de informa��es para o servidor.
	*/
	protected ObjectOutputStream transmissor;
	/**
	Vari�vel que representa a recep��o de informa��es provenientes de outros usu�rios e do servidor.
	*/
	protected ObjectInputStream receptor;
	/**
	Vari�vel que representa a sala na qual o usu�rio se encontra.
	*/
	protected SalaUsuario sala;

	/**
	M�todo que instancia o usu�rio.

	@param nick nome do usu�rio.
	@param s Socket que conecta com o servidor.
	@param sa recebe a sala na qual o usu�rio deseja entrar.
	@param o vari�vel para instanciar o transmissor de informa��es do usu�rio.
	@param i vari�vel para instanciar o receptor de informa��es do usu�rio.
	@throws Exception quando algum dos par�metros � nulo.
	*/
	public Usuario(String nick, Socket s, SalaUsuario sa, ObjectOutputStream o, ObjectInputStream i) throws Exception //mandados pela thread
	{
		if(nick == null||s == null||sa == null ||i == null ||o == null)
		   throw new Exception("PASSE TODOS OS PAR�METROS!!!");

		//verificar par�metros(exist�ncia da sala)

		nome = nick;
		conexao = s;
		sala = sa;
		transmissor = o;
		receptor = i;
	}

	/**
	M�todo que recupera o nome do usu�rio.

	@return retorna o nome do usu�rio.
	*/
	public String getNome()
	{
		return this.nome;
	}

	/**
	Recupera a sala do usu�rio.

	@return retorna a sala na qual o suu�rio se encontra.
	*/
	public SalaUsuario getSala()
	{
		return this.sala;
	}

	/**
	M�todo que envia um objeto da classe Enviavel.

	@throws Exception quando o objeto que deseja ser enviado � null.
	*/
	public void envia(Enviavel x) throws Exception
	{
		if(x == null)
			throw new Exception("Objeto enviado null");

		transmissor.writeObject(x);
		transmissor.flush();
	}


	/**
	M�todo que recebe um objeto da classe Envi�vel.

	@return retorna  o objeto recebido
	@throws Exception quando o objeto lido n�o � uma inst�ncia de Envi�vel.
	*/
	public Enviavel recebe() throws Exception
	{
		Object obj = this.receptor.readObject();

		if(!(obj instanceof Enviavel))
			throw new Exception("Objeto lido n�o � Enviavel!!");

		return (Enviavel)obj;
	}

	/**
	M�todo que fecha tudo relacionado � conex�o com o servidor.
	*/
	public void fechaTudo() throws Exception
	{
		this.transmissor.close();
		this.receptor.close();
		this.conexao.close();
	}


	/**
	M�todo que retorna String contendo o nome do usu�rio.

	@return retorna nome do usu�rio.
	*/
	public String toString()
	{
		return "Nome: "+this.nome;
	}

	/**
	M�todo que compara objetos da classe usu�rio pelas suas vari�veis.

	@return verdadeiro ou falso dependendo se as vari�veis e os objetos s�o iguais ou n�o.
	*/
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

	/**
	M�todo que cria um c�digo para cada vari�vel da classe

	@return retorna o inteiro formado como c�digo pelo m�todo hashCode().
	*/
	public int hashCode()
	{
		int ret = 666;

		ret = ret*2 + this.nome.hashCode();

		ret = ret*2 + this.receptor.hashCode();

		ret = ret*2 + this.transmissor.hashCode();

		return ret;
	}
}