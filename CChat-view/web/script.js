var sala = "GERAL";
var ult = 0;

// um dia talvez quem sabe tenha a chance de repentinamente pensar na possibilidade de quase se imaginar em escrever código aqui
function abobora(){
    var el = document.getElementById('campo');
    if(el){
      el.addEventListener('keyup', function(event) {
        event.preventDefault();
        if (event.keyCode == 13) {
            document.getElementById("botao").click();
        }
    })
    
    }
}

function envia(){
    var msg = document.getElementById("campo").value;
    document.getElementById("campo").value="";
    if(msg !== ""){
        var http = new XMLHttpRequest();
        http.open("GET", "?acao=sendMessage&type=async&msg="+encodeURI(msg), true);
        http.send();
    }
}

var refreshUsers = setInterval(function(){
    
    var http = new XMLHttpRequest();    
    var users = document.getElementById("users");
    
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

var refreshUsers = setInterval(function(){
    
    var http = new XMLHttpRequest();    
    var users = document.getElementById("users");
    
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

var refreshGrupos = setInterval(function(){
    
    var http = new XMLHttpRequest();    
    var groups = document.getElementById("groups");
    
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
        groups.innerHTML = username;
    };
    
    http.open("GET", "?acao=groupList&type=async", true);
    http.send();
    
},5000);


var update = setInterval(function(){
    
    var http = new XMLHttpRequest();    
    http.open("GET", "?acao=Update&type=async", true);
    http.send();
    
},5000);


var refreshMSG = setInterval(function(){
    
    var http = new XMLHttpRequest();    
    var msg = document.getElementById("msgs");
    
    http.onreadystatechange = function() {
        var o,t, d;
        var msgs = "";
        var xmlDoc,parser;
        
        if (http.readyState == 4 && http.status == 200) {
            
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(http.responseText, "text/xml");            
            o = xmlDoc.getElementsByTagName("org");
            t = xmlDoc.getElementsByTagName("txt");
            d = xmlDoc.getElementsByTagName("date");
            dest = xmlDoc.getElementsByTagName("dst");
            atual = 0;
            while(atual<d.length && parseInt(d[atual].childNodes[0].nodeValue) <= ult){
                atual++;
            }
            
            for (i = atual; i < o.length; i++) {
               msgs += ("<li class='msg'><h4 id='remetente'><b style='color: deeppink;'>@ "+dest[i].childNodes[0].nodeValue+" : </b>"+o[i].childNodes[0].nodeValue+"</h4><span id='mensagem'>"+t[i].childNodes[0].nodeValue+"</span></li>");
            }
            if(d.length-1 >= 0){
                ult = parseInt(d[d.length-1].childNodes[0].nodeValue);
            }            
        }
        var objDiv = document.getElementById("wrapper");
        if(msgs !== ""){
            if(objDiv.scrollTop >= objDiv.scrollHeight -600){
                msg.innerHTML += msgs;
                objDiv.scrollTop = objDiv.scrollHeight;
            }else{
                msg.innerHTML += msgs;
            }
        }
    };
    
    http.open("GET", "?acao=getMessage&type=async", true);
    http.send();
    
},1000);


function abreModalGrupo(){
    document.getElementById("modal").style.display = "block";
    document.body.style.overflowY ="hidden";
}

function fechaModalGrupo(){
    document.getElementById("modal").style.display = "none";
    document.body.style.overflowY ="auto";
}

function criarGrupo(){
     var nomeGrupo = "";
     if(document.getElementById("nomeGrupo").value != null && document.getElementById("nomeGrupo").value != ""){         
        nomeGrupo += document.getElementById("nomeGrupo").value;    
        fechaModalGrupo();
        var http = new XMLHttpRequest();
        http.open("GET", "?acao=createGroup&type=async&grupo=" + encodeURI(nomeGrupo), true);
        http.send();     
    }
    else{
    }
}