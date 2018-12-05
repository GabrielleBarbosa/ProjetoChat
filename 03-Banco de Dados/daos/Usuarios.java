package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Usuarios
{
    public static void incluir (Usuario usuario) throws Exception
    {
        if(usuario == null)
            throw new Exception("Usuario não foi fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO Usuario VALUES" +
                  "(codSala, nome)" +
                  "(?,?)";

            BDSQLServer.COMANDO.prepareStatement (sql);

            BDSQLServer.COMANDO.setInt (1, usuario.getCodSala());
            BDSQLServer.COMANDO.setString (2, usuario.getNome());
        }
    }
}