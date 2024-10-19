



// https://www.w3schools.com/howto/tryit.asp?filename=tryhow_css_modal
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var anonBtn = document.getElementById("myAnonymousBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

btn.onclick = function() {  modal.style.display = "block"; }
span.onclick = function() { modal.style.display = "none"; }
anonBtn.onclick = function() { alert("sign up"); }

window.onclick = function(event) {
  if (event.target == modal) {  modal.style.display = "none"; }
}

const sendMesageForm = document.getElementById("sendMesageForm")

sendMesageForm.addEventListener("submit", async event => {

    event.preventDefault();

    let res = await fetch(apiUrl, {
        method: 'POST',
        body: JSON.stringify({
            fromUserId: document.getElementById("fromUserId").value,
            toUserId: document.getElementById("toUserId").value,
            listingId: document.getElementById("listingId").value,
            message: document.getElementById("message").value
        })
    });
    modal.style.display = "none";
    alert("message sent");

})