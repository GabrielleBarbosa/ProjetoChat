package bd.dbos;

/**
	A classe SalaUsuario � uma classe dbo da tabela Sala do banco de dados.

	@authors Felipe Melchior de Britto, Gabrielle da Silva barbosa e Christovam Alves Lemos.
	@since 2018.
*/
public class Sala
{
	/**
	String com nome da sala.
	*/
	protected String nome;
	/**
		Int c�digo da sala.
	*/
	protected int cod;
	/**
		Int quantidade m�xima de pessoas que podem ocupar a sala.
	*/
	protected int qtdMax;

	/**
		Construtor da classe.

		@param n nome da sala.
		@param q quantidade maxima na sala.
		@param c c�digo da sala.
		@throws Exception se o o nome for null ou vazio.
	*/
	public Sala(String n, int q, int c)throws Exception
	{
		if(n == null || n == "")
			throw new Exception("nome passado � null ou vazio");

		this.nome = n;
		this.cod = c;
		this.qtdMax = q;
	}

	/**
		M�todo para mudar o nome da sala.

		@throws Exception se nome passado for null ou vazio.
		@param n nome novo para a sala.
	*/
	public void setNome(String n)throws Exception
	{
		if(n == null || n == vazio)
			throw new Exception("nome passado � null ou vazio");

		this.nome = n;
	}

	/**
		M�todo para mudar a quantidade m�xima na sala.

		@throws Exception se a quantidade for negativa ou 0.
		@param q quantidade nova para a sala.
	*/
	public void setQtdMax(int q)throws Exception
	{
		if(q <= 0)
			throw new Exception("quantidade m�xima passada � menor ou igual a zero");

		this.qtdMax = q;
	}

	/**
		M�todo que retorna nome da sala.

		@return nome da sala.
	*/
	public String getNome()
	{
		return this.nome;
	}

	/**
		M�todo que retorna quantidade m�xima de usuarios na sala.

		@return quantidade m�xima de usu�rios.
	*/
	public int getQtdMax()
	{
		return this.qtdMax;
	}

	/**
		M�todo que retorna o c�digo da sala.

		@return c�digo da sala.
	*/
	public int getCod()
	{
		return this.cod;
	}

	/**
		M�todo que retorna uma String com informa��es da classe.

		@return String contendo nome da sala, c�digo e quantidade m�xima ocupada.
	*/
	public String toString()
	{
		return "Nome da sala: " + this.nome + " C�digo: " + this.cod + " Quantidade m�xima na sala: " + this.getQtdMax();
	}

	/**
		M�todo que compara dois objetos da classe verificando seus atributos.

		@param Objeto para compara��o.
		@return true ou false de acordo com a igualdade.
	*/
	public boolean equals(Object obj)
	{
		if(obj == this)
			return true;

		if(obj == null)
			return false;

		if(obj.getClass() != this.getClass())
			return false;

		Sala sala = (Sala)obj;

		if(this.cod != sala.cod)
			return false;

		return true;
	}

	/**
		M�todo que cria um c�digo para um objeto da classe.

		@return int c�digo formado.
	*/
	public int hashCode()
	{
		int ret = 5;

		ret = ret*2 + new Integer(this.cod).hashCode();

		ret = ret*2 + new Integer(this.qtdMax).hashCode();

		ret = ret*2 + this.nome.hashCode();

		return ret;
	}
}