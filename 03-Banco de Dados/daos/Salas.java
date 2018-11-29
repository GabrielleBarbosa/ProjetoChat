package Banco.dao;

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
            
            resultado = BDSQLServer.COMANDO.executeQuery();
        }
        catch(SQLException erro)
        {
            throw new Exception ("Erro ao recuperar salas");
        }
        
        return resultado;
    }
    
    public static MeuResultSet getSala(int codigo) throws Exception
    {
        Sala sala = null
            
        try
        {
            String sql - "SELECT * SALA WHERE CODIGO = ?";
            
            BDSQLServer.COMANDO.prepareStatement (sql);
            
            BDSQLServer.COMANDO.setInt (1, codigo);
            
            MeuResultSet resultado = (MeuResultSet)BDSQLServer.COMANDO.excecuteQuery();
            
            if(!resultado.first())
                throw new Exception ("Sala inexistente");
            
            sala = new sala(resultado.getString("NOME"),
                            resultado.getInt("QTDMAX"),
                            resultado.getString("TEMA"),
                            resultado.getINT("CODSALA")); 
        }
        catch(SQLException erro)
        {
            throw new Exception ("Erro ao procurar sala");
        }
        
        return sala;
    }
}