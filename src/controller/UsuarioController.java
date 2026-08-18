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
    
    public Usuario listaPorId(int id){
        return dao.listarPorId(id);
    }
}
