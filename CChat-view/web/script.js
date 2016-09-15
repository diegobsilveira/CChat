var sala = "GERAL";
var ult = 0;

var usuarionome = "";

// um dia talvez quem sabe tenha a chance de repentinamente pensar na possibilidade de quase se imaginar em escrever código aqui
function abobora(){
    
    usuarionome=document.getElementById("nickname").innerHTML;
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
            var west = xmlDoc.getElementsByTagName("me")[0].childNodes[0].nodeValue;
            
            if(usuarionome!=west){        
                alert(west+",essa janela já era, tem outra sessao aberta");
            }
            
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
               username += ("<li onclick='abreModalGrupo(1,\""+u[i].childNodes[0].nodeValue+"\")'><a>"+u[i].childNodes[0].nodeValue+"</a></li>");
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
               dat = new Date(parseInt(d[d.length-1].childNodes[0].nodeValue));
               msgs += ("<li class='msg'><h4 id='remetente'><b style='color: deeppink;'>@ "+dest[i].childNodes[0].nodeValue+" : </b>"+o[i].childNodes[0].nodeValue+"<span style='float:right;color:gray;'>"+(dat.getHours() < 10 ? "0"+dat.getHours(): dat.getHours())+":"+(dat.getMinutes()<10?"0"+dat.getMinutes():dat.getMinutes())+"</span></h4><span id='mensagem'>"+t[i].childNodes[0].nodeValue+"</span></li>");
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

function abreModalGrupo(op,gn){
    
    var st = "";
    
    switch(op){
        case 0: 
            st="CRIAR GRUPO";
            document.getElementById("modalbutton").setAttribute( "onClick", "javascript: criarGrupo();" );
            break;
        case 1: 
            //st="ADICIONAR AO GRUPO" 
            st='<span style="font-size:40px">ADICIONAR AO GRUPO</span>';
            document.getElementById("modalbutton").setAttribute( "onClick", "addToGrupo('"+gn+"')");
            break;
    }
    
    
    document.getElementById("modaltitle").innerHTML=st;
    document.getElementById("modal").style.display = "block";
    document.body.style.overflowY ="hidden";
}

function fechaModalGrupo(){
    document.getElementById("modal").style.display = "none";
    document.body.style.overflowY ="auto";
}

function criarGrupo(){
     var modalinput = "";
     if(document.getElementById("modalinput").value != null && document.getElementById("modalinput").value != ""){   
        modalinput += document.getElementById("modalinput").value;    
        fechaModalGrupo();
        document.getElementById("modalinput").value= "";
        var http = new XMLHttpRequest();
        http.open("GET", "?acao=createGroup&type=async&grupo=" + encodeURI(modalinput), true);
        http.send();     
    }
    else{
    }
}

function addToGrupo(n){
     var nomeUser = "" ;
     var nomeGrupo = n;     
     if(document.getElementById("modalinput").value != null && document.getElementById("modalinput").value != ""){ 
        fechaModalGrupo();
        nomeUser = document.getElementById("modalinput").value;
        document.getElementById("modalinput").value= "";
        var http = new XMLHttpRequest();
        //http.open("GET", "?acao=MUDARAQUIPRAAÇAODEFINITIVA&type=async&grupo=" + encodeURI(nomeGrupo) + "&user=" + encodeURI(nomeUser), true);
        http.send();     
    }
    else{
    }
}