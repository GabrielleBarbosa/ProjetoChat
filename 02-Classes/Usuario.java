import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;


/**
A classe Usuario é uma classe que representa(como nos mostra o nome) o próprio Usuario.
Nela encontramos métodos como instanciar usuario recuperar a sala e nome do usuario.

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
	Socket que representa a conexão com o servidor.
	*/
	protected Socket conexao;
	/**
	Variável que representa a transmissão de informações para o servidor.
	*/
	protected ObjectOutputStream transmissor;
	/**
	Variável que representa a recepção de informações provenientes de outros usuários e do servidor.
	*/
	protected ObjectInputStream receptor;
	/**
	Variável que representa a sala na qual o usuário se encontra.
	*/
	protected SalaUsuario sala;

	/**
	Método que instancia o usuário.

	@param nick nome do usuário.
	@param s Socket que conecta com o servidor.
	@param sa recebe a sala na qual o usuário deseja entrar.
	@param o variável para instanciar o transmissor de informações do usuário.
	@param i variável para instanciar o receptor de informações do usuário.
	@throws Exception quando algum dos parâmetros é nulo.
	*/
	public Usuario(String nick, Socket s, SalaUsuario sa, ObjectOutputStream o, ObjectInputStream i) throws Exception //mandados pela thread
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

	/**
	Método que recupera o nome do usuário.

	@return retorna o nome do usuário.
	*/
	public String getNome()
	{
		return this.nome;
	}

	/**
	Recupera a sala do usuário.

	@return retorna a sala na qual o suuário se encontra.
	*/
	public SalaUsuario getSala()
	{
		return this.sala;
	}

	/**
	Método que envia um objeto da classe Enviavel.

	@throws Exception quando o objeto que deseja ser enviado é null.
	*/
	public void envia(Enviavel x) throws Exception
	{
		if(x == null)
			throw new Exception("Objeto enviado null");

		transmissor.writeObject(x);
		transmissor.flush();
	}


	/**
	Método que recebe um objeto da classe Enviável.

	@return retorna  o objeto recebido
	@throws Exception quando o objeto lido não é uma instância de Enviável.
	*/
	public Enviavel recebe() throws Exception
	{
		Object obj = this.receptor.readObject();

		if(!(obj instanceof Enviavel))
			throw new Exception("Objeto lido não é Enviavel!!");

		return (Enviavel)obj;
	}

	/**
	Método que fecha tudo relacionado à conexão com o servidor.
	*/
	public void fechaTudo() throws Exception
	{
		this.transmissor.close();
		this.receptor.close();
		this.conexao.close();
	}


	/**
	Método que retorna String contendo o nome do usuário.

	@return retorna nome do usuário.
	*/
	public String toString()
	{
		return "Nome: "+this.nome;
	}

	/**
	Método que compara objetos da classe usuário pelas suas variáveis.

	@return verdadeiro ou falso dependendo se as variáveis e os objetos são iguais ou não.
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
	Método que cria um código para cada variável da classe

	@return retorna o inteiro formado como código pelo método hashCode().
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