import java.util.ArrayList;
public class Salas
{
	protected ArrayList<Sala> listaSalas;

	public Salas()
	{
		listaSalas = new ArrayList<Sala>();
	}

	public void adicionarSala(Sala sala)
	{
		listaSalas.add(sala);
	}

	public boolean ExisteSala (String nome)
	{
		for(int i=0; i<listaSalas.size();i++)
			if(listaSalas.get(i).getNome() == nome)
				return true;

		return false;
	}

	public Sala getSala(String nome)
	{
		for(int i=0; i<listaSalas.size();i++)
			if(listaSalas.get(i).getNome() == nome)
				return listaSalas.get(i);

		throw new Exception("Nome de sala inexistente");
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