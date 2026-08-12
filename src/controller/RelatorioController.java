package controller;

import dao.EmprestimoDAO;
import java.util.List;
import model.Emprestimo;

public class RelatorioController {

    private EmprestimoDAO dao;

    public RelatorioController() {
        dao = new EmprestimoDAO();
    }

    public List<Emprestimo> buscarPorPeriodo(String dataInicial, String dataFinal) {
        return dao.buscarPorPeriodo(dataInicial, dataFinal);
    }
}