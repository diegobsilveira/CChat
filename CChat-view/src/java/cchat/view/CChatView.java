package cchat.view;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.util.Response;
import cchat.view.proxi.stubManterUsuario;

public class CChatView {

    public static void main(String args[]) {
        String IPServidor = "localhost";
        int PortaServidor = 2223;

        stubManterUsuario credenciar = new stubManterUsuario(IPServidor, PortaServidor);
        Sessao user = new Sessao();

        user.setNomeUsuario("cleberson");

        Response logged = credenciar.Logar(user);

        if (logged == Response.SUCCESS) {
            System.out.println("LOGOU");
            Thread teste = new Thread(credenciar);
            teste.run();
        } else if (logged == Response.FAILURE) {
            System.out.println("JÁ EXISTE UM USUARIO COM ESSE NOME");
        }

    }

}
