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
    //document.getElementById("msgs").innerHTML+="<li class='msg'><h4 id='remetente'>"+document.getElementById("nickname").innerHTML+"</h4><span id='mensagem'>"+msg+"</span></li>";
    //var objDiv = document.getElementById("wrapper");
    //objDiv.scrollTop = objDiv.scrollHeight;
    var http = new XMLHttpRequest();
    http.open("GET", "?acao=sendMessage&type=async&msg="+encodeURI(msg), true);
    http.send();
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
        var o,t;
        var msgs = "";
        var xmlDoc,parser;
        
        if (http.readyState == 4 && http.status == 200) {
            
            parser = new DOMParser();
            xmlDoc = parser.parseFromString(http.responseText, "text/xml");            
            o = xmlDoc.getElementsByTagName("org");
            t = xmlDoc.getElementsByTagName("txt");
            
            for (i = 0; i < o.length; i++) {
               msgs += ("<li class='msg'><h4 id='remetente'>"+o[i].childNodes[0].nodeValue+"</h4><span id='mensagem'>"+t[i].childNodes[0].nodeValue+"</span></li>");
            }
            var objDiv = document.getElementById("wrapper");
            objDiv.scrollTop = objDiv.scrollHeight;
        }
        msg.innerHTML = msgs;
    };
    
    http.open("GET", "?acao=getMessage&type=async", true);
    http.send();
    
},1000);