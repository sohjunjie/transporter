// Get the signInModal
var signInModal = document.getElementById('signInModal');

// Get the button that opens the signInModal
var signInBtn = document.getElementById("SignInBtn");

// Get the <span> element that closes the signInModal
var signInClose = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the signInModal 
signInBtn.onclick = function() {
    signInModal.style.display = "block";
}

// When the user clicks on <span> (x), close the signInModal
signInClose.onclick = function() {
    signInModal.style.display = "none";
}

// When the user clicks anywhere outside of the signInModal, close it
window.onclick = function(event) {
    if (event.target == signInModal) {
        signInModal.style.display = "none";
    }
}