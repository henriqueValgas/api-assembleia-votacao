const formulario = document.querySelector("form");
const nome = document.querySelector("#nome");
const mensagem = document.querySelector("#mensagem");


formulario.addEventListener("submit", function(event){
    event.preventDefault();

    const pauta ={
        nome: nome.value         
    }

    console.log("formulário enviado");

    const json = JSON.stringify(pauta);

    requisicaoAutenticada("/pautas", {
        method: "Post",
        headers: {"Content-Type": "Application/json"
        },
        body: json    
    })
    .then(response => {
        console.log(response.body)

        if(!response.ok){
            throw new Error(`Não foi possivel salvar nova pauta: ${response.status}`)
        }
        
        mensagem.textContent = "Pauta criada com sucesso!";
    })        
    .catch(error => {
        console.error("Error", error);
        mensagem.textContent = error.message;

    });
})
