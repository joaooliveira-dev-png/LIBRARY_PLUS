package model;

public class Funcionario {
    
    private int id;
    private String nome;
    private String cargo;

    public Funcionario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void cadastrarLivro() {
        System.out.println("Livro cadastrado.");
    }

    public void cadastrarUsuario() {
        System.out.println("Usuário cadastrado.");
    }

    public void registrarEmprestimo() {
        System.out.println("Empréstimo registrado.");
    }

    public void registrarDevolucao() {
        System.out.println("Devolução registrada.");
    }

}
