public class EscolhaDeNome implements Enviavel
{
	private String nomeUsuario;

	public EscolhaDeNome(String nome) throws Exception
	{
		if(nome == null || nome == "")
			throw new Exception("Objeto de nome é null ou vazio");

		this.nomeUsuario = nome;
	}

	public String getNome()
	{
		return this.nomeUsuario;
	}
}