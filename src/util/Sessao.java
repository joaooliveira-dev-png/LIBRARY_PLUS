package util;

import model.Funcionario;

public class Sessao {
    
    private static Funcionario funcionarioLogado;
    
    public static void setFuncionarioLogado(Funcionario f){
        funcionarioLogado = f;
    }
    
    public static Funcionario getFuncionarioLogado(){
        return funcionarioLogado;
    }
    
    public static void encerrarSessao(){
        funcionarioLogado = null;
    }
}
