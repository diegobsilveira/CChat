<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="en"><head>
        <meta charset="utf-8">
        <title>CHAT</title>
        <meta name="viewport" content="width=device-width,initial-scale=1">
        <link rel="stylesheet" href="css.css"> 

        <style type="text/css"></style><style type="text/css"></style></head>

    <body class="html" style="
          background: #7000ec; 
          background: -moz-linear-gradient(top, rgba(98,2,253,1) 0%, rgba(69,0,126,1) 100%);
          ">

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

            <img src="http://i.imgur.com/zHBwVHE.png?1" style="
                 height: 60px;
                 position: absolute;
                 top: 30px;
                 border-radius: 10000px;
                 right: 31px;
                 ">

            <span style="
                  font-size: 48px;
                  font-family: PB;
                  color: #7000ec;
                  font-weight: bold;
                  position: absolute;
                  top: 30px;
                  left: 30px;
                  ">JUNTE-SE</span>


            <form id="form" action="/chat/servletweb?acao=Login&type=refresh" method="POST">
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
                        ">NICKNAME</span><input type="text" name="nick" placeholder="O NICKNAME É NECESSÁRIO" class="inputo">
                    <button class="enter" onclick="document.getElementById(form).submit();">ENTRAR
                    </button>
                </div>
            </form>
        </div>

    </body></html>