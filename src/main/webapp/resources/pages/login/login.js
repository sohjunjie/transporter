$(document).ready(function() {
    event_loginFormAjax();
});

// login ajax js
function event_loginFormAjax(){

	$('#SignIn').click(function(event) {

		var data = {}
		data["usernameOrEmail"] = $("#signInUsernameOrEmail").val();
		data["password"] = $("#signInPassword").val();
		
		$.ajax({
			type : 'POST',
			url  : 'login',
			data : data,
			success :  function(response){
				location.reload();
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	});

}
