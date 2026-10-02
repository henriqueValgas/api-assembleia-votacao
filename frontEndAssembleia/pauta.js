const parametros = new URLSearchParams(window.location.search);

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