const parametros = new URLSearchParams(window.location.search);
const formulario = document.querySelector("form");
const entrada = document.querySelector("#minutos");

const id = parametros.get("id");

console.log(id);

fetch(`http://localhost:8080/pautas/${id}`)
    .then(response => response.json())
    .then(pauta => {
        console.log(pauta);

        const nomePauta = document.getElementById("nome-pauta");
        const totalSim = document.getElementById("total-sim");
        const totalNao = document.getElementById("total-nao");
        const totalVotos = document.getElementById("total-votos");
        const resultado = document.getElementById("resultado");

        nomePauta.textContent = pauta.nome;
        totalSim.textContent = pauta.totalSim;
        totalNao.textContent = pauta.totalNao;
        totalVotos.textContent = pauta.totalVotos;
        resultado.textContent = pauta.resultado;
});

formulario.addEventListener("submit", function(event){
    event.preventDefault();

    const sessao = {
        pautaId: id,
        duracao: Number(entrada.value) 
    }

    console.log("Tempo adicionado");
    
    
    const json = JSON.stringify(sessao)

    console.log(sessao);
    console.log(json);
    

    fetch(`http://localhost:8080/sessao-votacao`,{
        method: "Post",
        headers: {
            "Content-Type": "application/json"
        },
        body: json
    })
    .then(response => {
        console.log(response)

        return response.json();
    })
});



