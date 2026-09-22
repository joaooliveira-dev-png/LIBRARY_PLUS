package controller;

import model.Emprestimo;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
import java.util.List;
import model.Livro;
import model.Usuario;

public class EmprestimoController {
    
    private EmprestimoDAO daoEmprestimo;
    private LivroDAO daoLivro;
    private UsuarioDAO daoUsuario;

    public EmprestimoController() {
        daoEmprestimo = new EmprestimoDAO();
        daoLivro = new LivroDAO();
        daoUsuario = new UsuarioDAO();
    }
    
    public void registrarEmprestimo(Emprestimo emprestimo) {

    daoUsuario = new UsuarioDAO();
    Usuario usuario = daoUsuario.buscarPorId(emprestimo.getIdUsuario());

    if (usuario == null) {
        throw new IllegalArgumentException("Usuário não encontrado");
    }

    daoLivro = new LivroDAO();
    Livro livro = daoLivro.buscarPorId(emprestimo.getIdLivro());

    if (livro == null) {
        throw new IllegalArgumentException("Livro não encontrado");
    }

    if (livro.getQuantidade() <= 0) {
        throw new IllegalArgumentException(
            "Não há exemplares disponíveis deste livro"
        );
    }

    daoEmprestimo = new EmprestimoDAO();

    daoEmprestimo.salvar(emprestimo);

    livro.setQuantidade(livro.getQuantidade() - 1);
    daoLivro.atualizar(livro);
}
    public List<Emprestimo> listar(){
        return daoEmprestimo.listar();
    }
    
    public void devolverEmprestimo(int idEmprestimo, String dataDevolucao){
        Emprestimo emprestimo = daoEmprestimo.buscarPorId(idEmprestimo);

        if (emprestimo == null) {
            throw new IllegalArgumentException("Empréstimo não encontrado.");
        }

        if ("Devolvido".equalsIgnoreCase(emprestimo.getStatus())) {
            throw new IllegalArgumentException("Este empréstimo já foi devolvido.");
        }

        Livro livro = daoLivro.buscarPorId(emprestimo.getIdLivro());

        if (livro == null) {
            throw new IllegalArgumentException("Livro associado ao empréstimo não foi encontrado.");
        }

        emprestimo.setDataDevolucao(dataDevolucao);
        emprestimo.setStatus("Devolvido");

        daoEmprestimo.atualizar(emprestimo);

        livro.setQuantidade(livro.getQuantidade() + 1);
        daoLivro.atualizar(livro);
    }
}
