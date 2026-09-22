package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;
import model.Usuario;
import util.Conexao;

public class FuncionarioDAO {


    public void salvar(Funcionario f){
        String sql = "INSERT INTO funcionario(nome, cargo, id_usuario) VALUES(?,?,?)";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setInt(3, f.getUsuario().getId());
            
            stmt.executeUpdate();

        }catch(SQLException erro){
            System.out.println("Erro ao salvar o Funcionário : " + erro.getMessage());
        } 
    }
    
    public List<Funcionario> listar(){
        String sql = "SELECT f.id, f.nome, f.cargo, u.id "
                + "AS usuario_id, u.usuario, u.senha "
                + "FROM funcionario f "
                + "JOIN usuario u ON f.id_usuario = u.id";
        
        List<Funcionario> funcionarios = new ArrayList<>();
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("usuario_id"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(u);
                
                funcionarios.add(f);
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar todos os funcionários Tente novamente: " + erro.getMessage());
        } 
        return funcionarios;
    }
    
    public Funcionario buscarPorId(int id){
        String sql = "SELECT f.id, f.nome, f.cargo, u.id "
                + "AS usuario_id, u.usuario, u.senha "
                + "FROM funcionario f "
                + "JOIN usuario u ON f.id_usuario = u.id "
                + "WHERE f.id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);

           try(ResultSet rs = stmt.executeQuery()) {
            
            if(rs.next()){
                Funcionario f = new Funcionario();
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("usuario_id"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(u);
                
                return f;
             }
           }
        }catch(SQLException erro){
            System.out.println("Erro ao buscar funcionário por ID: " + erro.getMessage());
        } 
        return null;
    }
    
    public Funcionario buscarPorUsuarioId(int idUsuario){
        String sql = "SELECT f.id, f.nome, f.cargo, u.id "
                + "AS usuario_id, u.usuario, u.senha "
                + "FROM funcionario f "
                + "JOIN usuario u ON f.id_usuario = u.id "
                + "WHERE f.id_usuario = ?";

        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            try(ResultSet rs = stmt.executeQuery()) {

                if(rs.next()){
                    Funcionario f = new Funcionario();
                    Usuario u = new Usuario();

                    u.setId(rs.getInt("usuario_id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setSenha(rs.getString("senha"));

                    f.setId(rs.getInt("id"));
                    f.setNome(rs.getString("nome"));
                    f.setCargo(rs.getString("cargo"));
                    f.setUsuario(u);

                    return f;
                }
            }

        } catch(SQLException erro){
            System.out.println("Erro ao buscar funcionário por usuário: " + erro.getMessage());
        }

        return null;
    }
    
    
    public void atualizar(Funcionario f){
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, id_usuario = ? WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setInt(3, f.getUsuario().getId());
            stmt.setInt(4, f.getId());
            
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
    
}
