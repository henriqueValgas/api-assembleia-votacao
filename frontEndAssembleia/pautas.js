const listaPautas = document.querySelector("#lista-pautas");

fetch("http://localhost:8080/pautas")
    .then(response => {
        console.log(response.status);

        return response.json();
    })
    .then (data => {
        data.forEach(pauta => {

        const link = document.createElement("a");

        link.href = `pauta.html?id=${pauta.id}`;
        
        link.textContent = pauta.nome;

        listaPautas.appendChild(link);

        });
    });