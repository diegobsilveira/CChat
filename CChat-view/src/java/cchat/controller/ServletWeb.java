package cchat.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletWeb extends HttpServlet {
    private String jsp = "";
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String acao = request.getParameter("acao");
        String type = request.getParameter("type");
         
        switch (acao) {
            case "Login":
                jsp = Login.execute(request);
                break;
            case "userList":
                ArrayList<String> users = userList.execute(request);     
                
                response.setContentType("text/xml;charset=UTF-8");  
                PrintWriter p = response.getWriter();
                p.append("<g>");
                for(String s : users){
                    p.append("<user>");
                    p.append("<name>").append(s).append("</name>");
                    p.append("</user>"); 
                }
                p.append("</g>");
                
                //response.getWriter().write("Insira seu codigo malegno aqui"); 
                break;
        }
        
        //Redirecionando pagina
        switch (type) {
            case "refresh":
                RequestDispatcher rd = request.getRequestDispatcher(jsp);
                rd.forward(request, response); 
            break;
            case "async":
                
            break;
        }
        
    }    
}

