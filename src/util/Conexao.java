package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/library_plus";
    private static final String USER = "Joao";
    private static final String PASSWORD = "joao123";
    
    
    public Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
