const formulario = document.querySelector("form");
const cpf = document.querySelector("#cpf");
const senha = document.querySelector("#senha");
const mostrarSenha = document.querySelector("#mostrarSenha");

formulario.addEventListener("submit", function(event){
    event.preventDefault();

    const login = {
        cpf: cpf.value,
        senha: senha.value
    }

    const json = JSON.stringify(login);

    fetch(`http://localhost:8080/auth/login`,{
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
    .then(data => {
        console.log(data)
        console.log("Token: ", data.token);
    })
});

mostrarSenha.addEventListener("click", function(){
    if (senha.type === "password"){
        senha.type = "text"
    }else{
        senha.type = "password";
    }
});