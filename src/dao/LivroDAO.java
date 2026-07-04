package dao;

import java.util.List;
import util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import model.Livro;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivroDAO {
    
    private Conexao conexao;
    private Connection conn;

    public LivroDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }
    
    PreparedStatement stmt;
    
    public void salvar(Livro l){
        String sql = "INSERT INTO livro(titulo, autor, editora, quantidade) VALUES(?,?,?,?)";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setString(3, l.getEditora());
            stmt.setInt(4, l.getQuantidade());
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
    
    public List<Livro> listar(){
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Livro l = new Livro();
                
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setEditora(rs.getString("editora"));
                l.setQuantidade(rs.getInt("quantidade"));
                
                livros.add(l);
            }
        } catch(SQLException erro){
            System.out.println("Erro ao listar todos os livros! Tente novamente: " + erro.getMessage());
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
        return livros;
    }
    
    public Livro listarPorId(int id){
        String sql = "SELECT * FROM livro WHERE id = ?";
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Livro l = new Livro();
                
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setEditora(rs.getString("editora"));
                l.setQuantidade(rs.getInt("quantidade"));
                
                return l;
            }
        }catch(SQLException erro){
            System.out.println("Erro ao buscar livro por ID: " + erro.getMessage());
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
    
    public void atualizar(Livro l){
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, quantidade = ? WHERE id = ?";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, l.getTitulo());
            stmt.setString(2, l.getAutor());
            stmt.setString(3, l.getEditora());
            stmt.setInt(4, l.getQuantidade());
            stmt.setInt(5, l.getId());
            
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
        String sql = "DELETE FROM livro WHERE id = ?";
        
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
