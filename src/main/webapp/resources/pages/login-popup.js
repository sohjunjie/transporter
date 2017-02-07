// Get the modal
var modal = document.getElementById('signInModal');

// Get the button that opens the modal
var signInBtn = document.getElementById("SignInBtn");

// Get the <span> element that closes the modal
var signInClose = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal 
signInBtn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
signInClose.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}