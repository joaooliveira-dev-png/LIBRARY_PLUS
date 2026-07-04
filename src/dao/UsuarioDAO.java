package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import util.Conexao;

public class UsuarioDAO {
    
    private Conexao conexao;
    private Connection conn;

    public UsuarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }
    
    PreparedStatement stmt;
    
    public void salvar(Usuario u){
        String sql = "INSERT INTO usuario(nome, email, telefone) VALUES(?,?,?)";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            
            stmt.execute();
        }catch(SQLException erro){
            System.out.println("Erro, verifique os valores digitados : " + erro.getMessage());
        } finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException erro){
                System.out.println("Erro ao fechar a Statement: " + erro.getMessage());
            }
        }
    }
    
    public List<Usuario> listar(){
        String sql = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<>();
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                
                usuarios.add(u);
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar todos os usuários! Tente novamente: " + erro.getMessage());
        } finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
                if(rs != null){
                    rs.close();
                }
            } catch(SQLException erro){
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }
        }
        return usuarios;
    }
    
    public Usuario listarPorId(int id){
        String sql = "SELECT * FROM usuario WHERE id = ?";
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Usuario u = new Usuario();
                
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setTelefone(rs.getString("telefone"));
                
                return u;
            }
        }catch(SQLException erro){
            System.out.println("Erro ao buscar usuario por ID: " + erro.getMessage());
        } finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(stmt != null){
                    stmt.close();
                }
            }catch(SQLException erro){
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }
        }
        return null;
    }
    
    public void atualizar(Usuario u){
        String sql = "UPDATE usuario SET nome = ?, email = ?, telefone = ?  WHERE id = ?";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getTelefone());
            stmt.setInt(4, u.getId());
            
            stmt.executeUpdate();
        } catch(SQLException erro){
            System.out.println("Erro ao atualizar: " + erro.getMessage());
        } finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException erro){
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }
        }
    }
    
    public void excluir(int id){
        String sql = "DELETE FROM usuario WHERE id = ?";
        
        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch(SQLException erro){
            System.out.println("Erro ao excluir os dados! " + erro.getMessage());
        } finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException erro){
                System.out.println("Erro ao fechar Statement: " + erro.getMessage());
            }
        }
    }
}
