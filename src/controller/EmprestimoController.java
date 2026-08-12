package controller;

import model.Emprestimo;
import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.UsuarioDAO;

public class EmprestimoController {
    
    private EmprestimoDAO daoEmprestimo;
    private LivroDAO daoLivro;
    private UsuarioDAO daoUsuario;

    public EmprestimoController() {
        daoEmprestimo = new EmprestimoDAO();
        daoLivro = new LivroDAO();
        daoUsuario = new UsuarioDAO();
    }
    
    public void registrarEmprestimo(Emprestimo emprestimo){
    
    }
}
