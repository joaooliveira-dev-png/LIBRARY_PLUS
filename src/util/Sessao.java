package util;

import model.Usuario;

public class Sessao {
    
    private static Usuario usuarioLogado;
    
    public static void setUsuarioLogado(Usuario u){
        usuarioLogado = u;
    }
    
    public static Usuario getUsuarioLogado(){
        return usuarioLogado;
    }
    
    public static void encerrarSessao(){
        usuarioLogado = null;
    }
}
