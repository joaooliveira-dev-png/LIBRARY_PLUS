package controller;

import model.Emprestimo;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;
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

    UsuarioDAO daoUser = new UsuarioDAO();
    Usuario usuario = daoUser.listarPorId(emprestimo.getIdUsuario());

    if (usuario == null) {
        throw new IllegalArgumentException("Usuário não encontrado");
    }

    LivroDAO daoLivro = new LivroDAO();
    Livro livro = daoLivro.listarPorId(emprestimo.getIdLivro());

    if (livro == null) {
        throw new IllegalArgumentException("Livro não encontrado");
    }

    if (livro.getQuantidade() <= 0) {
        throw new IllegalArgumentException(
            "Não há exemplares disponíveis deste livro"
        );
    }

    EmprestimoDAO daoEmprestimo = new EmprestimoDAO();

    daoEmprestimo.salvar(emprestimo);

    livro.setQuantidade(livro.getQuantidade() - 1);
    daoLivro.atualizar(livro);
}
}
