
const formulario = document.querySelector("form");
const descricao = document.querySelector("#nome");
const mensagem = document.querySelector("#mensagem");

formulario.addEventListener("submit", function(event){
    event.preventDefault();

    const pauta = {
        nome: descricao.value
    }

     console.log("formulario enviado");

     const json = JSON.stringify(pauta);

    fetch("http://localhost:8080/pautas",{
        method: "Post",
        headers: {
            "Content-Type": "application/json"
        },
        body: json
    })
    .then(response => {
        console.log(response.status);

        return response.json();
    })
    .then(data => {
        console.log(data);

        mensagem.textContent =
        "| ID: " + data.id +
        "| Nome: " + data.nome +
        "| Sim: " + data.totalSim +
        "| Não: " + data.totalNao +
        "| Total: " + data.totalVotos;
    });    
}); 
