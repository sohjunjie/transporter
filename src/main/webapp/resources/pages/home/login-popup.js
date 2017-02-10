var signInModal = document.getElementById('signInModal');
var signInBtn = document.getElementById("SignInBtn");
var signInClose = document.getElementById("signin_modal_close");

if(signInModal != null){
	signInBtn.onclick = function() {
		$("#signInUsernameOrEmail").val("");
		$("#signInPassword").val("");
		signInModal.style.display = "block";
	}
	signInClose.onclick = function() {
		$("#signInUsernameOrEmail").val("");
		$("#signInPassword").val("");
		signInModal.style.display = "none";
	}
	window.onclick = function(event) {
	    if (event.target == signInModal) {
	        signInModal.style.display = "none";
	    }
	}
}