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

        if (!response.ok){
            throw new Error("Usuario ou senha errado");
        }

        return response.json();
    })
    .then(data => {
        console.log(data)

        if(!data.token){
            throw new Error("Erro, token inválido")
        }

        console.log("Token: ", data.token);
        sessionStorage.setItem("token", data.token);
                
    })
    .catch(error => {
        sessionStorage.removeItem("token");

        console.error("Erro no login", error);
        alert(error.alert || "Não foi possivel realizar login");
    })
});

mostrarSenha.addEventListener("click", function(){
    if (senha.type === "password"){
        senha.type = "text"
    }else{
        senha.type = "password";
    }
});