package controller;

import dao.FuncionarioDAO;
import model.Funcionario;

public class FuncionarioController {
    
    private FuncionarioDAO dao;

    public FuncionarioController() {
        dao = new FuncionarioDAO();
    }
    
    public Funcionario autenticar(String usuario, String senha){
        return dao.autenticar(usuario, senha);
    }
}
