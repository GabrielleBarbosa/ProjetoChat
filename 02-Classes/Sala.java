import java.util.ArrayList;

public class Sala
{
	protected ArrayList<Usuario> listaUsuarios;
	protected String nome;
	protected int cod;
	protected int qtdMax;
	protected int qtdOcupado;

	public Sala(String n, int q, int c)
	{
		this.listaUsuarios = new ArrayList<Usuario>(q);
		this.nome = n;
		this.cod = c;
		this.qtdMax = q;
		this.qtdOcupado = this.listaUsuarios.size();
	}

	public void setNome(String n)
	{
		this.nome = n;
	}

	public void setQtdMax(int q)
	{
		this.qtdMax = q;
	}

	public String getNome()
	{
		return this.nome;
	}

	public int getQtdMax()
	{
		return this.qtdMax;
	}

	public int getCod()
	{
		return this.cod;
	}

	public int getQtdOcupado()
	{
		return this.qtdOcupado;
	}

	public boolean isCheia()
	{
		if(this.qtdOcupado == this.qtdMax)
			return true;

		return false;
	}

	public Usuario getUsuario(int index)throws Exception
	{
		if(index<0 || index>qtdOcupado-1)
			throw new Exception("index fora dos limites");

		return listaUsuarios.get(index);
	}

	public Usuario getUsuario(String nome)throws Exception
	{
			if(nome == null || nome == "")
				throw new Exception("Parâmetro null");

			for(int i=0; i<this.qtdOcupado; i++)
				if(this.listaUsuarios.get(i).getNome().trim().equals(nome.trim()))
					return listaUsuarios.get(i);

			throw new Exception("Usuario não consta na sala");
	}

	public void adicionarUsuario(Usuario usuario) throws Exception
	{
		if(!this.isCheia())
		{
			this.listaUsuarios.add(usuario);
			this.qtdOcupado++;
		}
		else
			throw new Exception("sala cheia");
	}

	public void removerUsuario(Usuario usuario)throws Exception
	{
		if(!(listaUsuarios.contains(usuario)))
			throw new Exception("usuario não existe nessa sala");

		this.listaUsuarios.remove(usuario);
		this.qtdOcupado--;
	}

	public boolean existeNome(String nome)
	{
		for(int i=0; i<this.qtdOcupado;i++)
			if(this.listaUsuarios.get(i).getNome().trim().equals(nome.trim()))
				return true;

		return false;
	}

	public String toString()
	{
		return "Nome da sala: " + this.nome + " Código: " + this.cod + " Lugares disponíveis: " + this.getQtdOcupado();
	}

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