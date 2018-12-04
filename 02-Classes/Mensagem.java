public class Mensagem implements Enviavel
{
	private String remetente;
	private String destinatario;
	private String mensagem;

	public Mensagem(String nomeDestinatario, String nomeRemetente ,String msg) throws Exception
	{
		if(destinatario == null || destinatario == "" || nomeRemetente == null || nomeRemetente == "" || msg == null || msg == "")
			throw new Exception("Valores não podem ser null ou vazios");

		this.remetente = nomeRemetente;
		this.destinatario = nomeDestinatario;
		this.mensagem = msg;
	}

	public Mensagem(String msg, String nomeRemetente)throws Exception
	{
		if(msg == null || msg == "" || nomeRemetente == null || nomeRemetente == "" )
			throw new Exception("Valores não podem ser nulls ou vazios");

		this.remetente = nomeRemetente;
		this.mensagem = msg;
		destinatario = "";
	}

	public String getDestinatario()
	{
		return this.destinatario;
	}

	public String getRemetente()
	{
		return this.remetente;
	}

	public String getMensagem()
	{
		return this.mensagem;
	}
}