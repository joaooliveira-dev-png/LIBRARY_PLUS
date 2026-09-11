package controller;

import dao.UsuarioDAO;
import java.util.List;
import model.Usuario;

public class UsuarioController {
    
    private UsuarioDAO dao;

    public UsuarioController() {
        dao = new UsuarioDAO();
    }
    
    public void cadastrar(Usuario usuario){
        dao.salvar(usuario);
    }
    
    public List<Usuario> listar(){
        return dao.listar();
    }
    
    public Usuario buscarPorId(int id){
        return dao.buscarPorId(id);
    }
    
    public void atuzalizar(Usuario usuario){
        dao.atualizar(usuario);
    }
    
    public void excluir(int id){
        dao.excluir(id);
    }
}
