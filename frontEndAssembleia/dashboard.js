const headers = document.querySelector("header");
const btnSair = document.querySelector("#btnSair");

btnSair.addEventListener("click", function(){

    sessionStorage.removeItem("token");

    window.location.href = "login.html";    
})