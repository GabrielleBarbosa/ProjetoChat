package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

/**
	A classe Salas é uma classe static dao que faz operações no Banco de Dados referentes à tabela Sala.

	@authors Felipe Melchior de Britto, Gabrielle da Silva barbosa e Christovam Alves Lemos.
	@since 2018.
*/
public class Salas
{
	/**
		Método que retorna todos os elementos da tabela sala.

		@return MeuResultSet resultado.
	*/
    public static MeuResultSet getSalas() throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql= "SELECT * FROM SALA";

            BDSQLServer.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
        }
        catch(SQLException erro)
        {
            throw new Exception ("Erro ao recuperar salas");
        }

        return resultado;
    }

	/**
		Método que retorna uma sala de acordo com o código passado por parâmetro.
	*/
    public static Sala getSala(int codigo)throws Exception
    {
        Sala sala = null;

        try
        {
            String sql = "SELECT * FROM SALA WHERE CODSALA = ?";
            BDSQLServer.COMANDO.prepareStatement (sql);
            BDSQLServer.COMANDO.setInt (1, codigo);
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.executeQuery();
            if(!resultado.first())
                throw new Exception ("Sala inexistente");
            sala = new Sala(resultado.getString("NOME"),
                            resultado.getInt("QTDMAX"),
                            resultado.getInt("CODSALA"));
        }
        catch(SQLException erro)
        {
            throw new Exception (erro.getMessage());
        }

        return sala;
    }
}