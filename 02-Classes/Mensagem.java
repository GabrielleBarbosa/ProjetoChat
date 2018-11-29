public class Mensagem implements Enviavel
{
	private String destinatario;
	private String mensagem;

	public Mensagem(String nomeDestinatario, String msg) throws Exception
	{
		if(this.destinatario == null || msg == null || msg == "")
			throw new Exception("Valores não podem ser null");

		this.destinatario = nomeDestinatario;
		this.mensagem = msg;
	}

	public Mensagem()
	{
		destinatario = "";
	}

	public String getDestinatario()
	{
		return this.destinatario;
	}

	public String getMensagem()
	{
		return this.mensagem;
	}
}