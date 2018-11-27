public class EscolhaDeSala implements Enviavel
{
	private String nomeSala;

	public EscolhaDeSala(String nomeSala) throws Exception
	{
		if(nomeSala == null || nomeSala == "")
			throw new Exception("Objeto de Sala � null ou vazio");

		this.nomeSala = nomeSala;
	}

	public String getNomeSala()
	{
		return this.nomeSala;
	}
}