package cchat.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import cchat.common.model.domain.impl.Grupo;
import cchat.common.model.domain.impl.Mensagem;
import cchat.common.model.domain.impl.Sessao;
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
        PrintWriter p ;
        ArrayList<String> nameList;
        ArrayList<Mensagem> msgList;
        switch (acao) {
            case "Login":
                jsp = Login.execute(request);
                break;
            case "Update":
                Update.execute(request);
                break;
            case "userList":
                nameList = userList.execute(request);     
                
                response.setContentType("text/xml;charset=UTF-8");  
                p = response.getWriter();
                p.append("<g>");
                Sessao kanye = (Sessao) request.getSession().getAttribute("user");
                p.append("<me>").append(kanye.getNome()).append("</me>");
                for(String s : nameList){
                    p.append("<user>");
                    p.append("<name>").append(s).append("</name>");
                    p.append("</user>"); 
                }
                p.append("</g>");
                response.getWriter().write(jsp);
                break;
            case "groupList":
                nameList = groupList.execute(request);     
                response.setContentType("text/xml;charset=UTF-8");  
                p = response.getWriter();
                p.append("<g>");
                for(String s : nameList){
                    p.append("<user>");
                    p.append("<name>").append(s).append("</name>");
                    p.append("</user>"); 
                }
                p.append("</g>");
                response.getWriter().write(jsp);
                break;
            case "getMessage":
                msgList = mensagemIn.execute(request);     
                response.setContentType("text/xml;charset=UTF-8");  
                p = response.getWriter();
                p.append("<mensagens>");
                for(Mensagem msg : msgList){
                    p.append("<msg>");
                    p.append("<txt><![CDATA[").append(msg.getMensagem()).append("]]></txt>");
                    if(msg.getDestino() instanceof Grupo) {
                        p.append("<org>").append(msg.getOrigem().getNome()).append("</org>");
                        p.append("<dst>").append(msg.getDestino().getNome()).append("</dst>");
                    }else{
                        p.append("<org>").append(msg.getDestino().getNome()).append("</org>");
                        p.append("<dst>PRIVADO COM ").append(msg.getOrigem().getNome()).append("</dst>");
                    }
                    p.append("<date>").append((new Long(msg.getEnvio().getTime())).toString()).append("</date>");
                    p.append("</msg>"); 
                }
                p.append("</mensagens>");
                break;
            case "sendMessage":
                mensagemOut.execute(request);
                break;
            case "createGroup":
                createGroup.execute(request);
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

