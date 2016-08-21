package cchat.view;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterUsuario;

public class CChatView {
 
    public static void main(String args[]) {
        String IPServidor = "localhost";
        int PortaServidor = 2223;
 
        IManterUsuario credenciar = new stubManterUsuario(IPServidor, PortaServidor);
        Sessao user = new Sessao();
        user.setNomeUsuario("cleberso");
 
        Boolean logged = false;
 
        do {
            logged = credenciar.Logar(user);
            System.out.println("AINDA NAO LOGOU - TA NO LOOP");
        } while (!logged);
        System.out.println("LOGOU");
       
        /*
        while(true){
           
        }
         */
    }
 
}
