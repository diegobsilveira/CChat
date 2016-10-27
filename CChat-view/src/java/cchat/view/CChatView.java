package cchat.view;

import cchat.common.model.domain.impl.Sessao;
import cchat.view.proxi.stubManterUsuario;
import java.rmi.RemoteException;

public class CChatView {

    public static void main(String args[]) throws RemoteException {
    
        stubManterUsuario credenciar = new stubManterUsuario();
        Sessao user = new Sessao();

        user.setNome("cleberson");

        boolean logged = credenciar.Logar(user);

        if (logged) {
            System.out.println("LOGOU");
        } else{
            System.out.println("JÁ EXISTE UM USUARIO COM ESSE NOME");
        }

    }

}
