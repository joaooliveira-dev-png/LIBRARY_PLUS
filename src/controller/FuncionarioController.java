package controller;

import dao.FuncionarioDAO;
import dao.UsuarioDAO;
import java.util.List;
import model.Funcionario;

public class FuncionarioController {
    
    private FuncionarioDAO daoFuncionario;
    private UsuarioDAO daoUsuario;

    public FuncionarioController() {
        daoFuncionario = new FuncionarioDAO();
        daoUsuario = new UsuarioDAO();
    }
    
    public void cadastrar(Funcionario funcionario){
        daoUsuario.salvar(funcionario.getUsuario());
        daoFuncionario.salvar(funcionario);
    }
    
    public List<Funcionario> listar(){
        return daoFuncionario.listar();
    }
        
    public Funcionario buscarPorId(int id){
        return daoFuncionario.buscarPorId(id);
    }
    
    public Funcionario buscarPorUsuarioId(int idUsuario){
        return daoFuncionario.buscarPorUsuarioId(idUsuario);
    }
    
    public void atualizar(Funcionario funcionario){
        daoUsuario.atualizar(funcionario.getUsuario());
        daoFuncionario.atualizar(funcionario);
    }
    
    public void excluir(int id){
        daoFuncionario.excluir(id);
    }
}
