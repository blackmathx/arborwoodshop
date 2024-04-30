
document.getElementById("apit").addEventListener("click", apitest);

function apitest(){

    fetch("http://localhost:8080/api/testing", {
        method: 'GET',
        credentials: 'include'
        })
        .then((res) => res.json())
        .then( (r) => console.log(r))
        .catch(function() {
            console.log("ERROR")
        });

}