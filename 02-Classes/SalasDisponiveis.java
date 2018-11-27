public class SalasDisponiveis implements Enviavel
{
	private Salas salas;

	public SalasDisponiveis(Salas salas) throws Exception
	{
		if(salas == null)
			throw new Exception("Objeto de Salas é null");

		this.salas = salas;
	}

	public int getQtdSalas()
	{
		return this.salas.getQtdSalas();
	}

	public String getNomeSala(int index) throws Exception
	{
		if(index < 0 || index >= this.getQtdSalas())
			throw new Exception("index inválido");

		return this.salas.getNomeSala(index);
	}
}