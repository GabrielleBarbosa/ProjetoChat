package bd.daos;
import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Salas
{
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

    public static Sala getSala(int codigo)

    throws Exception
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