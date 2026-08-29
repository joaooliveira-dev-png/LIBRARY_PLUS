package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import util.Conexao;

public class FuncionarioDAO {


    public void salvar(Funcionario f){
        String sql = "INSERT INTO funcionario(nome, cargo, usuario, senha) VALUES(?,?,?,?)";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setString(3, f.getUsuario());
            stmt.setString(4, f.getSenha());
            
            stmt.executeUpdate();

        }catch(SQLException erro){
            System.out.println("Erro ao salvar o Funcionário : " + erro.getMessage());
        } 
    }
    
    public List<Funcionario> listar(){
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                
                funcionarios.add(f);
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar todos os funcionários Tente novamente: " + erro.getMessage());
        } 
        return funcionarios;
    }
    
    public Funcionario buscarPorId(int id){
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

           try(ResultSet rs = stmt.executeQuery()) {
            
            if(rs.next()){
                Funcionario f = new Funcionario();
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                
                return f;
             }
           }
        }catch(SQLException erro){
            System.out.println("Erro ao buscar funcionário por ID: " + erro.getMessage());
        } 
        return null;
    }
    
    public void atualizar(Funcionario f){
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, usuario = ?, senha = ? WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setString(3, f.getUsuario());
            stmt.setString(4, f.getSenha());
            stmt.setInt(5, f.getId());
            
            stmt.executeUpdate();
            
        } catch(SQLException erro){
            System.out.println("Erro ao atualizar: " + erro.getMessage());
        } 
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM funcionario WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch(SQLException erro){
            System.out.println("Erro ao excluir os dados! " + erro.getMessage());
        }
    }
    
    public Funcionario autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";
    

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            try(ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Funcionario f = new Funcionario();

                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));

                return f;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e.getMessage());
        }
            return null;
    }
}
