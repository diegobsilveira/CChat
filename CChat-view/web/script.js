// um dia talvez quem sabe tenha a chance de repentinamente pensar na possibilidade de quase se imaginar em escrever código aqui
/*
document.getElementById("campo").addEventListener("keyup",
        function (event) {
            event.preventDefault();
            if (event.keyCode == 13) {
                document.getElementById("botao").click();
            }
        }
);
*/

function envia(){
    alert("NAO VAI ENVIAR MENSAGEM NAO! NAO VAI NAO!");   
    document.getElementById("campo").value="";
}

setInterval(function(){
    
    var http = new XMLHttpRequest();    
    var users = document.getElementById("users");
    var groups = document.getElementById("groups");
    var msgs = document.getElementById("msgs");
    
    http.onreadystatechange = function() {
        var u;
        var username = "";
        var xmlDoc,parser;
        
        if (http.readyState == 4 && http.status == 200) {
            
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(http.responseText, "text/xml");            
            u = xmlDoc.getElementsByTagName("name");            
            
            for (i = 0; i < u.length; i++) {
               username += ("<li><a>"+u[i].childNodes[0].nodeValue+"</a></li>");
            }
        }
        users.innerHTML = username;
    };
    
    http.open("GET", "?acao=userList&type=async", true);
    http.send();
    
},5000);