public class Mensagem implements Enviavel
{
	private Usuario destinatario;

	public Mensagem(Usuario remetente, Usuario destinatario) throws Exception
	{
		if(this.destinatario == null)
			throw new Exception("Valores n�o podem ser null");

		this.destinatario = destinatario;
	}

	public Usuario getDestinatario()
	{
		return this.destinatario;
	}
}