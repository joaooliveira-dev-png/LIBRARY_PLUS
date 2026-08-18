package controller;

import dao.LivroDAO;
import java.util.List;
import model.Livro;

public class LivroController {
    
    private LivroDAO dao;

    public LivroController() {
        dao = new LivroDAO();
    }
    
    public void cadastrar(Livro livro){
        dao.salvar(livro);
    }
    
    public List<Livro> listar(){
       return dao.listar();
    }    
    
    public Livro listarPorId(int id){
        return dao.listarPorId(id);
    }
    
    
}
