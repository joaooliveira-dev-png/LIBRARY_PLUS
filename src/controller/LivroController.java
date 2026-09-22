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
    
    public Livro buscarPorId(int id){
        return dao.buscarPorId(id);
    }
    
    public void atualizar(Livro livro){
        dao.atualizar(livro);
    }
    
    public void excluir(int id){
        dao.excluir(id);
    }
}
