package controller;

import dao.FuncionarioDAO;
import java.util.List;
import model.Funcionario;

public class FuncionarioController {
    
    private FuncionarioDAO dao;

    public FuncionarioController() {
        dao = new FuncionarioDAO();
    }
    
    public Funcionario autenticar(String usuario, String senha){
        return dao.autenticar(usuario, senha);
    }
    
    public void cadastrar(Funcionario funcionario){
        dao.salvar(funcionario);
    }
    
    public List<Funcionario> listar(){
        return dao.listar();
    }
        
    public Funcionario buscarPorId(int id){
        return dao.buscarPorId(id);
    }
    
    public void atualizar(Funcionario funcionario){
        dao.atualizar(funcionario);
    }
    
    public void excluir(int id){
        dao.excluir(id);
    }
}
