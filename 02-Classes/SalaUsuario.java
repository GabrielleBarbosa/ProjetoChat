import java.util.ArrayList;
import bd.dbos.*;

/**
	A classe SalaUsuario é uma classe que serve para controlar os usuarios de uma sala, com um ArrayList<Usuario>.

	@authors Felipe Melchior de Britto, Gabrielle da Silva barbosa e Christovam Alves Lemos.
	@since 2018.
*/
public class SalaUsuario
{
	/**
		ArrayList<Usuario> lista que mantém controle dos usuarios da sala.
	*/
	protected ArrayList<Usuario> listaUsuarios;
	/**
		String nome da sala.
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
		Int quantas pessoas estão na sala.
	*/
	protected int qtdOcupado;

	/**
		Construtor de SalaUsuario, listaUsuario começa vazia pois, quando uma sala é criada, ainda não há usuários.

		@param n nome da sala.
		@param q quantidade maxima de pessoas.
		@param c codigo da sala.
		@throws Exception se o nome da sala passado é null ou vazio, não se verifica q ou c pois int n pode ser vazio.
	*/
	public SalaUsuario(String n, int q, int c)throws Exception
	{
		if(n == null || n == "")
			throw new Exception("Parâmetro para nome de sala null ou vazio");

		this.listaUsuarios = new ArrayList<Usuario>(q);
		this.nome = n;
		this.cod = c;
		this.qtdMax = q;
		this.qtdOcupado = 0;
	}

	/**
		Construtor da classe que utiliza de um dbo de Sala e pega os atributos dele, novamente
		inicializando listaUsuarios vazia.

		@param sala dbo.
		@throws Exception se o objeto do parâmetro for null.
	*/
	public SalaUsuario(bd.dbos.Sala sala)throws Exception
	{
		if(sala == null)
		   throw new Exception("Objeto de sala vazio");

		this.listaUsuarios = new ArrayList<Usuario>(sala.getQtdMax());
		this.nome = sala.getNome();
		this.cod = sala.getCod();
		this.qtdMax = sala.getQtdMax();
		this.qtdOcupado = 0;
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
		Método que retorna quantos usuários há na sala.

		@return nome da sala.
	*/
	public int getQtdOcupado()
	{
		return this.qtdOcupado;
	}

	/**
		Método que retorna se a sala está cheia.

		@return true ou false se a sala estiver cheia ou não.
	*/
	public boolean isCheia()
	{
		if(this.qtdOcupado == this.qtdMax)
			return true;

		return false;
	}

    /**
		Método que retorna um usuário da sala de acordo com o index passado.

		@param int index para o get da ArrayList.
		@return usuario procurado.
		@throws Exception se o index for uma posição inválida.
	*/
	public Usuario getUsuario(int index)throws Exception
	{
		if(index<0 || index>qtdOcupado-1)
			throw new Exception("index fora dos limites");

		return listaUsuarios.get(index);
	}

	/**
		Método que retorna um usuário da sala de acordo com o index passado.

		@param nome do usuario a ser procurado.
		@return usuario procurado.
		@throws Exception se o nome passado for null ou vazio ou se não houver nenhum usuario que tenha tal nome na sala.
	*/
	public Usuario getUsuario(String nome)throws Exception
	{
			if(nome == null || nome == "")
				throw new Exception("Parâmetro null");

			for(int i=0; i<this.qtdOcupado; i++)
				if(this.listaUsuarios.get(i).getNome().trim().equals(nome.trim()))
					return listaUsuarios.get(i);

			throw new Exception("Usuario não consta na sala");
	}

	/**
		Método que adiciona um usuário à lista de usuarios da sala.

		@param usuario a ser adicionado.
		@throws Exception se o usuario passado for null ou se a sala estiver cheia.
	*/
	public void adicionarUsuario(Usuario usuario) throws Exception
	{
		if(usuario == null)
			throw new Exception("usuario passado é null");

		if(!this.isCheia())
		{
			this.listaUsuarios.add(usuario);
			this.qtdOcupado++;
		}
		else
			throw new Exception("sala cheia");
	}

	/**
		Método que remove um usuário da sala.

		@param usuario a ser removido.
		@return usuario procurado.
		@throws Exception se o nome passado for null ou vazio ou se não houver nenhum usuario que tenha tal nome na sala.
	*/
	public void removerUsuario(Usuario usuario)throws Exception
	{
		if(!(listaUsuarios.contains(usuario)))
			throw new Exception("usuario não existe nessa sala");

		this.listaUsuarios.remove(usuario);
		this.qtdOcupado--;
	}

	/**
		Método que verifica se existe um usuario na sala com o nome passado por parâmetro.

		@param nome do usuario a ser procurado.
		@return true ou false dependendo da existência do nome passado.
		@throws Exception se o nome passado for null ou vazio.
	*/
	public boolean existeNome(String nome)throws Exception
	{
		if(nome == null || nome == "")
			throw new Exception("nome passado é null ou vazio");

		for(int i=0; i<this.qtdOcupado;i++)
			if(this.listaUsuarios.get(i).getNome().trim().equals(nome.trim()))
				return true;

		return false;
	}

	/**
		Método que retorna uma String com informações da classe.

		@return String contendo nome da sala, código e quantidade ocupada em relação à máxima.
	*/
	public String toString()
	{
		return "Nome da sala: " + this.nome + " Código: " + this.cod + " Lugares ocupados: " + this.getQtdOcupado() + "/" + this.getQtdMax();
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

		SalaUsuario sala = (SalaUsuario)obj;

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

		ret = ret*2 + this.listaUsuarios.hashCode();

		return ret;
	}
}