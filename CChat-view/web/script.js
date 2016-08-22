// um dia talvez quem sabe tenha a chance de repentinamente pensar na possibilidade de quase se imaginar em escrever código aqui

document.getElementById("id_of_textbox").addEventListener("keyup",
        function (event) {
            event.preventDefault();
            if (event.keyCode == 13) {
                document.getElementById("botao").click();
            }
        }
);

function envia(){
    alert("NAO VAI ENVIAR MENSAGEM NAO! NAO VAI NAO!");   
    document.getElementById("campo").value="";
}