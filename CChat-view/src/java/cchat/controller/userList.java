package cchat.controller;

import cchat.common.model.domain.impl.Sessao;
import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterUsuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import cchat.common.util.Response;
import java.util.ArrayList;

/**
 *
 * @author Igor
 */
public class userList {
    
    public static ArrayList<String> execute(HttpServletRequest request) {
        String jsp = "room.jsp";
        String host = "localhost";
        int port = 2223;
        
        try {  
            IManterUsuario manter = new stubManterUsuario(host,port);
            ArrayList<String> users = manter.listarUsuarios();
            return users;          
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "index.jsp";
        }
       
        return null;        
    }
}    
