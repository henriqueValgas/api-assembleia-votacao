const listaPautas = document.querySelector("#lista-pautas");

requisicaoAutenticada("/pautas")
    .then( response => {
        console.log(response.status);

        if(!response.ok){
            throw new Error(`Erro ao buscar pautas: ${response.status}`)
        }

        return response.json();
    })
    .then(data => { data.forEach(pauta => {

        const link = document.createElement("a");

        link.href = `pauta.html? id=${pauta.id}`;

        link.textContent = pauta.nome;

        listaPautas.appendChild(link);       

        });
    })
    .catch( error => {
        console.error("Erro ao carregar pautas", error);    
    });