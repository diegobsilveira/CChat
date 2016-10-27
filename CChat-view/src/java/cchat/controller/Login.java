package cchat.controller;

import cchat.common.model.domain.impl.Grupo;
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
        
        try {
            String nick = request.getParameter("nick");
            Sessao user = new Sessao();
            user.setNome(nick);
            IManterUsuario manter = new stubManterUsuario();
            
            if(manter.Logar(user)){        
                jsp = "/room.jsp";
                request.setAttribute("usrList", manter.listarUsuarios());
                request.getSession().setAttribute("user", user);
            }            
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "index.jsp";
        }
        
        return jsp;        
    }
}    
