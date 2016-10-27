package cchat.controller;

import cchat.common.services.IManterUsuario;
import cchat.view.proxi.stubManterUsuario;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 *
 * @author Igor
 */
public class userList {
    
    public static ArrayList<String> execute(HttpServletRequest request) {
        String jsp = "room.jsp";
        
        try {  
            IManterUsuario manter = new stubManterUsuario();
            ArrayList<String> users = manter.listarUsuarios();
            return users;          
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "index.jsp";
        }
       
        return null;        
    }
}    
