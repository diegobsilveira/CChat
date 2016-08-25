package cchat.view;

import cchat.common.model.domain.impl.Sessao;
import cchat.view.proxi.stubManterUsuario;

public class CChatView {

    public static void main(String args[]) {
        String IPServidor = "localhost";
        int PortaServidor = 2223;

        stubManterUsuario credenciar = new stubManterUsuario(IPServidor, PortaServidor);
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
