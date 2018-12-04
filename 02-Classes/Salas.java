import java.util.ArrayList;


public class Salas
{
	private ArrayList<Sala> listaSalas;
	private int qtdSalas;

	public Salas()
	{
		listaSalas = new ArrayList<Sala>();
		this.qtdSalas = this.listaSalas.size();
	}

	public void adicionarSala(Sala sala)
	{
		listaSalas.add(sala);
		this.qtdSalas++;
	}

	public boolean existeSala (String nome)
	{
		for(int i=0; i<listaSalas.size();i++)
			if(listaSalas.get(i).getNome() == nome)
				return true;

		return false;
	}

	public Sala getSala(String nome) throws Exception
	{
		for(int i=0; i<listaSalas.size();i++)
			if(listaSalas.get(i).getNome() == nome)
				return listaSalas.get(i);

		throw new Exception("Nome de sala inexistente");
	}

	public Sala getSala(int index) throws Exception
	{
		if(index < 0 || index > this.qtdSalas)
			throw new Exception("index fora dos limites");

		return this.listaSalas.get(index);
	}

	public String getNomeSala(int index) throws Exception
	{
		if(index >= this.qtdSalas || index < 0)
			throw new Exception("index inválido");

		return this.listaSalas.get(index).getNome();
	}

	public int getQtdSalas()
	{
		return this.qtdSalas;
	}

	public String toString()
	{
		return "Salas Existentes: " + listaSalas.size();
	}

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

	public int hashCode()
	{
		int ret = 4;

		ret = 2*ret + listaSalas.hashCode();

		return ret;
	}
}