$(document).ready(function() {
	setHideAlert($("#login-feedback"));
    event_loginFormAjax();
});

function event_loginFormAjax(){
	$(".login-form").on('submit', function(event){
	
		var data = {}
		data["usernameOrEmail"] = $("#signInUsernameOrEmail").val();
		data["password"] = $("#signInPassword").val();
		
		$.ajax({
			type : 'POST',
			url  : 'login',
			data : data,
			success :  function(response){
				if(response=="OK"){
					location.reload();
				}else{
					$("#login-feedback > span").html("Incorrect login details!");					
					showAlert($( "#login-feedback" ), "danger");
				}
			},
			error : function(e) {
				$("#login-feedback > span").html("Error loggin in. Please try again!");
				showAlert($( "#login-feedback" ), "danger");
				console.log("ERROR: ", e);
			},
			done : function(e) {}
		});
	
	    event.preventDefault();
	});
}