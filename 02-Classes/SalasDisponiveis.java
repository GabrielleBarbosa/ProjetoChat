import java.util.ArrayList;

public class SalasDisponiveis implements Enviavel
{
	private ArrayList<String> salas;

	public SalasDisponiveis(ArrayList<String> salas) throws Exception
	{
		if(salas == null)
			throw new Exception("Objeto de Salas é null");

		this.salas = salas;
	}

	public ArrayList<String> getNomeSala()
	{
		return this.salas;
	}
}