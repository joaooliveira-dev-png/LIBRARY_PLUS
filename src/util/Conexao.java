package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    
    private static final String URL = "jdbc:mysql://localhost:3306/library_plus";
    private static final String USER = "Joao";
    private static final String PASSWORD = "joao123";
    
    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(ClassNotFoundException | SQLException erro){
            System.out.println("Erro ao conectar com o Banco de Dados : " + erro.getMessage());
            return null;
        }
    }
}
