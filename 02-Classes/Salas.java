import java.util.ArrayList;

/**
A classe Salas é uma classe que representa todas as salas do programa principal, as quais recupera do Banco de Dados.

Nela encontramos métodos para adicionar salas, verificar se salas existem e recuperar informações sobre as Salas.
@authors Felipe Melchior de Britto e Gabrielle da Silva Barbosa.
@since 2018.
*/
public class Salas
{
	/**
	Array que armazena todas as salas do banco de dados.
	*/
	private ArrayList<Sala> listaSalas;
	/**
	variável que representa a quantidade de salas existentes(apenas as adicionadas via método 'adiconarSala').
	*/
	private int qtdSalas;

	/**
	Instancia um objeto do tipo Salas e instancia 'qtdSalas', fazendo com que receba 0.
	*/
	public Salas()
	{
		listaSalas = new ArrayList<Sala>();
		this.qtdSalas = 0;
	}

	/**
	Adiciona uma sala à lista de salas e, para tanto recebe como parâmetro um objeto do tipo sala.

	@param sala sala a qual o programador deseja incluir na lista de salas.
	*/
	public void adicionarSala(Sala sala)
	{
		listaSalas.add(sala);
		this.qtdSalas++;
	}


	/**
	Verifica se a sala em questão existe e para	isso utiliza o nome da sala para verificar sua existência.

	@param nome nome da sala que deseja ser verificada a existência.
	@return retorna verdadeiro ou falso dependendo se a sala existe ou não.
	*/
	public boolean existeSala(String nome)throws Exception
	{
		for(int i=0; i<this.qtdSalas;i++)
			if(this.getSala(i).getNome().trim().equals(nome.trim()))
				return true;

		return false;
	}

	/**
	Retorna a sala desejada caso a mesma exista e para isso utiliza o nome dela para encontrá-la.

	@param nome nome da sala que deseja ser encontrada.
	@return retorna a sala desejada.
	@throws Exception lança uma exceção quando a sala que deseja ser retornada não existe.
	*/
	public Sala getSala(String nome) throws Exception
	{
		for(int i=0; i<listaSalas.size();i++)
			if(this.getSala(i).getNome().trim().equals(nome.trim()))
				return listaSalas.get(i);

		throw new Exception("Nome de sala inexistente");
	}

	/**
	Retorna a sala desejada caso a mesma exista e para isso utiliza o index do array listaSalas.

	@param index posição da sala em questão no array.
	@return retorna a sala desejada.
	@throws Exception caso o parâmetro esteja fora dos limites do array.
	*/
	public Sala getSala(int index) throws Exception
	{
		if(index < 0 || index > this.qtdSalas)
			throw new Exception("index fora dos limites");

		return this.listaSalas.get(index);
	}

	/**
	Retorna o nome da sala em questão e para isso utiliza do index da sala passado como parâmetro para encontrá-la.

	@param index posição da sala em questão no array.
	@return retorna o nome da sala.
	@throws Exception quando index está fora dos limites do array.
	*/
	public String getNomeSala(int index) throws Exception
	{
		if(index >= this.qtdSalas || index < 0)
			throw new Exception("index inválido");

		return this.listaSalas.get(index).getNome();
	}

	/**
	Método que retorna a quantidade de salas existentes.

	@return retorna a quantidade de salas existentes.
	*/
	public int getQtdSalas()
	{
		return this.qtdSalas;
	}

	/**
	Retorna a quantidade de espaços ocupados no vetor Salas em forma de String.

	@return retorna String que contém os espaços ocupados no vetor Salas.
	*/
	public String toString()
	{
		return "Salas Existentes: " + listaSalas.size();
	}

	/**
	compara objetos da classe Salas levando em conta os objetos do vetor de salas.

	@return retorna verdadeiro ou falso dependendendo das comparações.
	*/
	public boolean equals(Object obj)
	{
		if(obj == this)
		    return true;

		if(obj == null)
			return false;

		if(obj.getClass() != this.getClass())
			return false;

		Salas salas = (Salas)obj;

		if(!(this.listaSalas.equals(salas.listaSalas)))
			return false;

		return true;
	}

	/**
	Cria um código para a classe Salas levando em conta todos os seus atributos.

	return retorna inteiro formado como código pelos passos do hashCode().
	*/
	public int hashCode()
	{
		int ret = 4;

		ret = 2*ret + listaSalas.hashCode();

		return ret;
	}
}