package bd.dbos;

/**
	A classe SalaUsuario é uma classe dbo da tabela Sala do banco de dados.

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
		Int código da sala.
	*/
	protected int cod;
	/**
		Int quantidade máxima de pessoas que podem ocupar a sala.
	*/
	protected int qtdMax;

	/**
		Construtor da classe.

		@param n nome da sala.
		@param q quantidade maxima na sala.
		@param c código da sala.
		@throws Exception se o o nome for null ou vazio.
	*/
	public Sala(String n, int q, int c)throws Exception
	{
		if(n == null || n == "")
			throw new Exception("nome passado é null ou vazio");

		this.nome = n;
		this.cod = c;
		this.qtdMax = q;
	}

	/**
		Método para mudar o nome da sala.

		@throws Exception se nome passado for null ou vazio.
		@param n nome novo para a sala.
	*/
	public void setNome(String n)throws Exception
	{
		if(n == null || n == vazio)
			throw new Exception("nome passado é null ou vazio");

		this.nome = n;
	}

	/**
		Método para mudar a quantidade máxima na sala.

		@throws Exception se a quantidade for negativa ou 0.
		@param q quantidade nova para a sala.
	*/
	public void setQtdMax(int q)throws Exception
	{
		if(q <= 0)
			throw new Exception("quantidade máxima passada é menor ou igual a zero");

		this.qtdMax = q;
	}

	/**
		Método que retorna nome da sala.

		@return nome da sala.
	*/
	public String getNome()
	{
		return this.nome;
	}

	/**
		Método que retorna quantidade máxima de usuarios na sala.

		@return quantidade máxima de usuários.
	*/
	public int getQtdMax()
	{
		return this.qtdMax;
	}

	/**
		Método que retorna o código da sala.

		@return código da sala.
	*/
	public int getCod()
	{
		return this.cod;
	}

	/**
		Método que retorna uma String com informações da classe.

		@return String contendo nome da sala, código e quantidade máxima ocupada.
	*/
	public String toString()
	{
		return "Nome da sala: " + this.nome + " Código: " + this.cod + " Quantidade máxima na sala: " + this.getQtdMax();
	}

	/**
		Método que compara dois objetos da classe verificando seus atributos.

		@param Objeto para comparação.
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
		Método que cria um código para um objeto da classe.

		@return int código formado.
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