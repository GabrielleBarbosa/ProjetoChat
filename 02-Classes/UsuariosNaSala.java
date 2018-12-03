import java.util.ArrayList;

public class UsuariosNaSala implements Enviavel
{
	private ArrayList<String> usuarios;

	public UsuariosNaSala(ArrayList<String> usuarios)throws Exception
	{
		if(usuarios == null)
		   throw new Exception("ArrayList de usuarios null");
		this.usuarios = usuarios;
	}

	public ArrayList<String> getUsuarios()
	{
		return this.usuarios;
	}
}