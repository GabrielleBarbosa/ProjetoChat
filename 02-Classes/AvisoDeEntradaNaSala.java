public class AvisoDeEntradaNaSala implements Enviavel
{
	private String remetente;

	public AvisoDeEntradaNaSala(String r) throws Exception
	{
		if(r == null || r == "")
			throw new Exception("Usuario fornecido é null");

		this.remetente = r;
	}

	public String getRemetente()
	{
		return this.remetente;
	}
}