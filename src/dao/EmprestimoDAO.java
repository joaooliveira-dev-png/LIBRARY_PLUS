package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Emprestimo;
import util.Conexao;

public class EmprestimoDAO {

    private Conexao conexao;
    private Connection conn;
    private PreparedStatement stmt;

    public EmprestimoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.conectar();
    }

    public void salvar(Emprestimo e) {
        String sql = "INSERT INTO emprestimo(data_emprestimo, data_devolucao, status, id_usuario, id_livro, id_funcionario) VALUES(?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, e.getDataEmprestimo());
            stmt.setString(2, e.getDataDevolucao());
            stmt.setString(3, e.getStatus());
            stmt.setInt(4, e.getIdUsuario());
            stmt.setInt(5, e.getIdLivro());
            stmt.setInt(6, e.getIdFuncionario());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            System.out.println("Erro ao salvar empréstimo: " + erro.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException erro) {
                System.out.println("Erro ao fechar Statement: " + erro.getMessage());
            }
        }
    }

    public List<Emprestimo> listar() {

        String sql = "SELECT * FROM emprestimo";

        List<Emprestimo> emprestimos = new ArrayList<>();
        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {

                Emprestimo e = new Emprestimo();

                e.setId(rs.getInt("id"));
                e.setDataEmprestimo(rs.getString("data_emprestimo"));
                e.setDataDevolucao(rs.getString("data_devolucao"));
                e.setStatus(rs.getString("status"));
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setIdLivro(rs.getInt("id_livro"));
                e.setIdFuncionario(rs.getInt("id_funcionario"));

                emprestimos.add(e);
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao listar empréstimos: " + erro.getMessage());
        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }

        }

        return emprestimos;
    }

    public Emprestimo listarPorId(int id) {

        String sql = "SELECT * FROM emprestimo WHERE id = ?";

        ResultSet rs = null;

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if (rs.next()) {

                Emprestimo e = new Emprestimo();

                e.setId(rs.getInt("id"));
                e.setDataEmprestimo(rs.getString("data_emprestimo"));
                e.setDataDevolucao(rs.getString("data_devolucao"));
                e.setStatus(rs.getString("status"));
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setIdLivro(rs.getInt("id_livro"));
                e.setIdFuncionario(rs.getInt("id_funcionario"));

                return e;
            }

        } catch (SQLException erro) {
            System.out.println("Erro ao buscar empréstimo: " + erro.getMessage());
        } finally {

            try {

                if (rs != null) {
                    rs.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }

        }

        return null;
    }
    
    public List<Emprestimo> buscarPorPeriodo(String dataInicial, String dataFinal) {
    List<Emprestimo> lista = new ArrayList<>();

    String sql = "SELECT * FROM emprestimo WHERE data_emprestimo BETWEEN ? AND ?";

    try {
        stmt = conn.prepareStatement(sql);
        
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            Emprestimo e = new Emprestimo();
            e.setIdUsuario(rs.getInt("idUsuario"));
            e.setIdLivro(rs.getInt("idLivro"));
            e.setDataEmprestimo(rs.getString("data_emprestimo"));
            e.setDataDevolucao(rs.getString("data_devolucao"));
            e.setStatus(rs.getString("status"));

            lista.add(e);
        }

    } catch(Exception e){
        e.printStackTrace();
    }

    return lista;
}

    public void atualizar(Emprestimo e) {

        String sql = "UPDATE emprestimo SET data_emprestimo = ?, data_devolucao = ?, status = ?, id_usuario = ?, id_livro = ?, id_funcionario = ? WHERE id = ?";

        try {

            stmt = conn.prepareStatement(sql);

            stmt.setString(1, e.getDataEmprestimo());
            stmt.setString(2, e.getDataDevolucao());
            stmt.setString(3, e.getStatus());
            stmt.setInt(4, e.getIdUsuario());
            stmt.setInt(5, e.getIdLivro());
            stmt.setInt(6, e.getIdFuncionario());
            stmt.setInt(7, e.getId());

            stmt.executeUpdate();

        } catch (SQLException erro) {
            System.out.println("Erro ao atualizar empréstimo: " + erro.getMessage());
        } finally {

            try {

                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao fechar recursos: " + erro.getMessage());
            }

        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM emprestimo WHERE id = ?";

        try {

            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException erro) {
            System.out.println("Erro ao excluir empréstimo: " + erro.getMessage());
        } finally {

            try {

                if (stmt != null) {
                    stmt.close();
                }

            } catch (SQLException erro) {
                System.out.println("Erro ao fechar Statement: " + erro.getMessage());
            }

        }
    }
}
