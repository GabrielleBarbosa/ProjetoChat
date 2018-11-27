public class SalasDisponiveis implements Enviavel
{
	private Salas salas;

	public SalasDisponiveis(Salas salas)
	{
		if(salas == null)
			throw new Exception("Objeto de Salas � null");

		this.salas = salas;
	}

	public int getQtdSalas()
	{
		return this.salas.getQtdSalas();
	}

	public String getNomeSala(int index)
	{
		if(index < 0 || index >= this.getQtdSalas())
			throw new Exception("index inv�lido");

		return this.salas.getNomeSala(index);
	}
}