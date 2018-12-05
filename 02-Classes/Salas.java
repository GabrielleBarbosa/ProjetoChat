import java.util.ArrayList;

/**
A classe Salas � uma classe que representa todas as salas do programa principal, as quais recupera do Banco de Dados.

Nela encontramos m�todos para adicionar salas, verificar se salas existem e recuperar informa��es sobre as Salas.
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
	vari�vel que representa a quantidade de salas existentes(apenas as adicionadas via m�todo 'adiconarSala').
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
	Adiciona uma sala � lista de salas e, para tanto recebe como par�metro um objeto do tipo sala.

	@param sala sala a qual o programador deseja incluir na lista de salas.
	*/
	public void adicionarSala(Sala sala)
	{
		listaSalas.add(sala);
		this.qtdSalas++;
	}


	/**
	Verifica se a sala em quest�o existe e para	isso utiliza o nome da sala para verificar sua exist�ncia.

	@param nome nome da sala que deseja ser verificada a exist�ncia.
	@return retorna verdadeiro ou falso dependendo se a sala existe ou n�o.
	*/
	public boolean existeSala(String nome)throws Exception
	{
		for(int i=0; i<this.qtdSalas;i++)
			if(this.getSala(i).getNome().trim().equals(nome.trim()))
				return true;

		return false;
	}

	/**
	Retorna a sala desejada caso a mesma exista e para isso utiliza o nome dela para encontr�-la.

	@param nome nome da sala que deseja ser encontrada.
	@return retorna a sala desejada.
	@throws Exception lan�a uma exce��o quando a sala que deseja ser retornada n�o existe.
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

	@param index posi��o da sala em quest�o no array.
	@return retorna a sala desejada.
	@throws Exception caso o par�metro esteja fora dos limites do array.
	*/
	public Sala getSala(int index) throws Exception
	{
		if(index < 0 || index > this.qtdSalas)
			throw new Exception("index fora dos limites");

		return this.listaSalas.get(index);
	}

	/**
	Retorna o nome da sala em quest�o e para isso utiliza do index da sala passado como par�metro para encontr�-la.

	@param index posi��o da sala em quest�o no array.
	@return retorna o nome da sala.
	@throws Exception quando index est� fora dos limites do array.
	*/
	public String getNomeSala(int index) throws Exception
	{
		if(index >= this.qtdSalas || index < 0)
			throw new Exception("index inv�lido");

		return this.listaSalas.get(index).getNome();
	}

	/**
	M�todo que retorna a quantidade de salas existentes.

	@return retorna a quantidade de salas existentes.
	*/
	public int getQtdSalas()
	{
		return this.qtdSalas;
	}

	/**
	Retorna a quantidade de espa�os ocupados no vetor Salas em forma de String.

	@return retorna String que cont�m os espa�os ocupados no vetor Salas.
	*/
	public String toString()
	{
		return "Salas Existentes: " + listaSalas.size();
	}

	/**
	compara objetos da classe Salas levando em conta os objetos do vetor de salas.

	@return retorna verdadeiro ou falso dependendendo das compara��es.
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
	Cria um c�digo para a classe Salas levando em conta todos os seus atributos.

	return retorna inteiro formado como c�digo pelos passos do hashCode().
	*/
	public int hashCode()
	{
		int ret = 4;

		ret = 2*ret + listaSalas.hashCode();

		return ret;
	}
}