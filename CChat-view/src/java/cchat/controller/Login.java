package cchat.controller;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterUsuario;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Igor
 */
public class Login {
    
    public static String execute(HttpServletRequest request) {
        String jsp = "index.jsp";
        String host = "localhost";
        int port = 2223;
        
        try {                        
            String nick = request.getParameter("nick");   
            Sessao user = new Sessao();
            user.setNome(nick);
            
            IManterUsuario manter = new stubManterUsuario(host,port);
            
            if(manter.Logar(user)){        
                System.out.println("Dento");
                jsp = "/room.jsp";
                request.setAttribute("usrList", manter.listarUsuarios());
                request.getSession().setAttribute("user", user);
//                request.setAttribute("", port);
//                request.setAttribute("", port);
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "index.jsp";
        }
        
        
        
        return jsp;        
    }
}    
