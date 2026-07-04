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
    private Conexao conexao;
    private Connection conn;

    public FuncionarioDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }
    
    PreparedStatement stmt;
    
    public void salvar(Funcionario f){
        String sql = "INSERT INTO funcionario(nome, cargo, usuario, senha) VALUES(?,?,?,?)";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setString(3, f.getUsuario());
            stmt.setString(4, f.getSenha());
            
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
    
    public List<Funcionario> listar(){
        String sql = "SELECT * FROM funcionario";
        List<Funcionario> funcionarios = new ArrayList<>();
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            rs = stmt.executeQuery();
            
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
            System.out.println("Erro ao listar todos os funcionarios Tente novamente: " + erro.getMessage());
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
        return funcionarios;
    }
    
    public Funcionario listarPorId(int id){
        String sql = "SELECT * FROM funcionario WHERE id = ?";
        ResultSet rs = null;
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                Funcionario f = new Funcionario();
                
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setUsuario(rs.getString("usuario"));
                f.setSenha(rs.getString("senha"));
                
                return f;
            }
        }catch(SQLException erro){
            System.out.println("Erro ao buscar funcionario por ID: " + erro.getMessage());
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
    
    public void atualizar(Funcionario f){
        String sql = "UPDATE funcionario SET nome = ?, cargo = ?, usuario = ?, senha = ? WHERE id = ?";
        
        try{
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setString(3, f.getUsuario());
            stmt.setString(4, f.getSenha());
            stmt.setInt(5, f.getId());
            
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
        String sql = "DELETE FROM funcionario WHERE id = ?";
        
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
    
    public Funcionario autenticar(String usuario, String senha) {
    String sql = "SELECT * FROM funcionario WHERE usuario = ? AND senha = ?";
    ResultSet rs = null;

    try {
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);

        rs = stmt.executeQuery();

        if (rs.next()) {
            Funcionario f = new Funcionario();

            f.setId(rs.getInt("id"));
            f.setNome(rs.getString("nome"));
            f.setCargo(rs.getString("cargo"));
            f.setUsuario(rs.getString("usuario"));
            f.setSenha(rs.getString("senha"));

            return f;
        }

    } catch (SQLException e) {
        System.out.println("Erro ao autenticar: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }

    return null;
    }
}
