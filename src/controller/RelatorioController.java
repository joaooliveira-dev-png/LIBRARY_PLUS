package controller;

import dao.EmprestimoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import model.Emprestimo;

public class RelatorioController {

    private EmprestimoDAO dao;

    public RelatorioController() {
        dao = new EmprestimoDAO();
    }

    public List<Emprestimo> buscarPorPeriodo(String dataInicial, String dataFinal) {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicio;
        LocalDate fim;

        try {
            inicio = LocalDate.parse(dataInicial, formatter);
            fim = LocalDate.parse(dataFinal, formatter);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Data inválida. Use o formato dd/MM/yyyy."
            );
        }

        if (inicio.isAfter(fim)) {
            throw new IllegalArgumentException(
                    "A data inicial não pode ser posterior à data final."
            );
        }

        return dao.buscarPorPeriodo(dataInicial, dataFinal);
    }
}