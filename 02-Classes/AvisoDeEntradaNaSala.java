public class AvisoDeEntradaNaSala implements Enviavel
{
	private Usuario remetente;

	public AvisoDeEntradaNaSala(Usuario r)
	{
		if(r == null)
			throw new Exception("Usuario fornecido é null");

		this.remetente = r;
	}

	public Usuario getUsuario()
	{
		return this.remetente;
	}

	public String getNomeUsuario()
	{
		return this.remetente.getNome();
	}
}