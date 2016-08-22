package cchat.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Igor
 */
public class Login {
    
    public static String execute(HttpServletRequest request) {
        String jsp = "/room.jsp";
        
        try {            
            String nick = request.getParameter("nick"); 
            System.out.println("AOOOOOOOOOOOOOOOOOOOOOOOO "+nick);
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "";
        }
        
        
        return jsp;        
    }
}    
