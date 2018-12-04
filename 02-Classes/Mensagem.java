public class Mensagem implements Enviavel
{
	private String remetente;
	private String destinatario;
	private String mensagem;

	public Mensagem(String nomeDestinatario, String nomeRemetente ,String msg) throws Exception
	{
		if(destinatario == null || destinatario == "" || nomeRemetente == null || nomeRemetente == "" || msg == null || msg == "")
			throw new Exception("Valores não podem ser null");

		this.remetente = nomeRemetente;
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

	public String getRemetente()
	{
		return this.destinatario;
	}

	public String getMensagem()
	{
		return this.mensagem;
	}
}