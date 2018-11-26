package bd.daos;

import java.sql.*;
import bd.*;
import bd.core.*;
import bd.dbos.*;

public class Usuarios
{
    public static void incluir (Usuario usuario) throws Exception
    {
        if(livro == null)
            throw new Exception("Usuario nao fornecido");
        
        try
        {
            String sql;
            
            sql = "Insert into Usuario" +
                  "(codUsuario, nome)" +
                  "Values" +
                  "(?,?)";
            
            BDSQLServer.COMANDO.prepareStatement (sql);
            
            BDSQLServer.COMANDO.setInt (1, usuario.getCodUsuario());
            BDSQLServer.COMANDO.setString (2, usuario.getNome());
        }
    }
}