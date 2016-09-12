<%@page import="cchat.common.model.domain.IDestinatario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cchat.common.model.domain.impl.*" %>

<%
    /*   
    String nickS = (String)session.getAttribute("nick");
    if(null == nickS){
        nickS = "";
    }
     
    ArrayList<String> listaU = (ArrayList) session.getAttribute("usrList");  
    if(listaU == null){
        listaU = (ArrayList) request.getAttribute("usrList");
    }
    
 
    String nick  = request.getParameter("nick");
    if(null != nick){
       nick = nickS + nick;
       session.setAttribute("nick", nick);
       response.sendRedirect(request.getContextPath() + "/room.jsp");
       return;
    }
        
    System.out.println("nick = "+nick);
    
    System.out.println("nickS = "+nickS);+ */
%>
<html lang="en"><head>
        <meta charset="utf-8">
        <title>CHAT</title>
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="stylesheet" href="css.css">
        <style>
            ::-webkit-input-placeholder {
                color: gainsboro;
            }
        </style> 

        <style type="text/css"></style><style type="text/css"></style><style type="text/css"></style><style type="text/css"></style>
        <script src="//twemoji.maxcdn.com/twemoji.min.js"></script>
        <script type="text/javascript" src="script.js"></script>
        <style type="text/css"></style><style type="text/css"></style><style type="text/css"></style><style type="text/css"></style></head>

    <body onload="abobora()" class="html" style="
          /* background: #7000ec; */ /* Old browsers */
          /* FF3.6-15 */
          /* Chrome10-25,Safari5.1-6 */
          /* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
          /* background: -webkit-linear-gradient(top, rgb(98, 0, 207) 0%, rgba(69,0,126,1) 100%); */
          background: -webkit-linear-gradient(top, rgb(98, 0, 207) 52%, #F14D7B 100%);
          ">

        <div style="
    margin: 0 auto;
    width: 1100px;
    margin-top: 80px;
"><div class="you" style="
">
  
  <div style="display: inline-block;padding: 20px;background-color: #fff;color: #F14D7B;width: 1060px;position: relative;box-shadow: 0px 3px 6px 3px rgba(0,0,0,0.19);font-size: 20px;border-bottom: 2px solid #F14D7B;"><img src="http://i.imgur.com/zHBwVHE.png?1" style="
                 height: 45px;
                 /* top: 30px; */
                 /* border-radius: 10000px; */
                 /* left: 200; */
                 /* right: 31px; */
                 position: absolute;
                 right: 10px;
                 top: 10px;
                 ">ADEVAS CHAT 3.0<span style="color: black;display: -webkit-inline-box;font-family: PB;display: none;">ADEVAS CHAT 3.0</span>            
     </div> 
  
  </div></div>

        <div class="container">
            <div class="msgdiv">
                <div style="padding: 20px;border-bottom: 1px solid gainsboro;">
                    <h3 id="nickname" style="
    color: #f04d7b;
"><%=((IDestinatario) request.getSession().getAttribute("user")).getNome()%></h3>
                    <a href="" style="background: url(&quot;https://cdn4.iconfinder.com/data/icons/geomicons/32/672366-x-128.png&quot;);height: 12px;width: 12px;position: absolute;top: 24px;right: 30px;background-size: cover;"></a>
                </div>

                <div class="body" id="wrapper">
                    <ul id="msgs">    </ul>
                </div>

                <div>
                    <input id="campo" placeholder="Digite aqui sua mensagem" type="text" class="box">
                    <button type="button" onclick="envia()" id="botao" class="sendbutton">ENVIAR
                    </button>
                </div>
            </div>

            <div class="nav">
                <div class="salas" style="border-bottom: 1px solid gainsboro;">
                    <h2>GRUPOS</h2>
                    <button type="button" class="addbutton" onclick="abreModalGrupo()"></button>
                    <ul id="groups" class="body" style="height:252.25px;margin:0px;"></ul>
                </div>

                <div class="salas"><h2>PESSOAS</h2>
                    <ul id="users" class="body" style="height:252.25px;margin:0px;"></ul>
                </div>
            </div>
        </div>
        <div id="modal" class="modal">
            <modal>
                <div style="
                     width: 540px;
                     height: 320px;
                     background: rgb(255, 255, 255);
                     margin: 0 auto;
                     margin-top: 200px;
                     margin-bottom: 100px;
                     -moz-box-shadow: 0px 3px 6px 3px rgba(0,0,0,0.19);
                     box-shadow: 0px 3px 6px 3px rgba(0,0,0,0.19);
                     position: relative;
                     border-radius: 5px;
                     ">

                    <button type="button" style="background: url(&quot;https://cdn4.iconfinder.com/data/icons/geomicons/32/672366-x-128.png&quot;);height: 24px;width: 24px;position: absolute;top: 45px;right: 30px;background-size: cover;" onclick="fechaModalGrupo()"></button>

                    <span style="
                          font-size: 48px;
                          font-family: PB;
                          color: #7000ec;
                          font-weight: bold;
                          position: absolute;
                          top: 30px;
                          left: 30px;
                          ">CRIAR GRUPO</span>


                    <div style="
                         margin: 0 auto;
                         width: 500px;
                         padding-top: 110px;
                         "><span style="
                            font-size: 14px;
                            font-family: PB;
                            color: #7000ec;
                            display: block;
                            margin-left: 10px;
                            ">NOME DO GRUPO</span>
                        <input type="text" id="nomeGrupo" name="nome" placeholder="O NOME É NECESSÁRIO" class="inputo">
                        <button type="button" class="enter" onclick="criarGrupo()">PRONTO</button>
                    </div>

                </div>
            </modal>
        </div>

    </body></html>