const API_URL = "http://localhost:8080";

function requisicaoAutenticada(endpoint, opcoes = {}){
    const token = sessionStorage.getItem("token");

    return fetch(`${API_URL}${endpoint}`, {
        ...opcoes,
        headers: {
            ...opcoes.headers,
            Authorization: `Bearer ${token}`
        }
    });
}