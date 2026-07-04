package model;

public class Funcionario {
    
    private int id;
    private String nome;
    private String cargo;
    private String usuario;
    private String senha;

    public Funcionario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
