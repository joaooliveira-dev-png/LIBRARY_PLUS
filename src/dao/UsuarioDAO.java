package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import util.Conexao;

public class UsuarioDAO {
    

    public int salvar(Usuario u){
        String sql = "INSERT INTO usuario(usuario, senha) VALUES(?,?)";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getSenha());
            
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if (rs.next()) {
                int id = rs.getInt(1);
                u.setId(id);
                return id;
            }
            
        }catch(SQLException erro){
            System.out.println("Erro ao salvar Usuário : " + erro.getMessage());
        }
        return 0;
    }
    
    
    public List<Usuario> listar(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                
                usuarios.add(u);
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar todos os usuários! Tente novamente: " + erro.getMessage());
        } 
        return usuarios;
    }
    
    public Usuario buscarPorId(int id){
        String sql = "SELECT * FROM usuario WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
            
            if(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));
                
                return u;
                }
            }
            
        }catch(SQLException erro){
            System.out.println("Erro ao buscar usuário por ID: " + erro.getMessage());
        }
        return null;
    }
    
    public void atualizar(Usuario u){
        String sql = "UPDATE usuario SET usuario = ?, senha = ? WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getSenha());
            stmt.setInt(3, u.getId());
            
            stmt.executeUpdate();
            
        } catch(SQLException erro){
            System.out.println("Erro ao atualizar: " + erro.getMessage());
        } 
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try(Connection conn = new Conexao().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch(SQLException erro){
            System.out.println("Erro ao excluir os dados! " + erro.getMessage());
        } 
    }
    
    public Usuario autenticar(String usuario, String senha) {
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";

        try (Connection conn = new Conexao().conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setSenha(rs.getString("senha"));

                return u;
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao autenticar Usuário: " + erro.getMessage());
        }

        return null;
    }
}
