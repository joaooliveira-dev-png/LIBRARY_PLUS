package model;


import java.util.Date;

public class Relatorio {
    
    private int id;
    private String tipo;
    private Date dataGeracao;

    public Relatorio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }
    
    public void gerarRelatorio() {
        System.out.println("Relatório gerado.");
    }

    public void exportarPDF() {
        System.out.println("Relatório exportado em PDF.");
    }

}
