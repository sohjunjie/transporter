$(document).ready(function() {
	setHideAlert($("#login-feedback"));
    event_loginFormAjax();
    event_loginFormValidate();
});

function event_loginFormAjax(){
	$(".login-form").on('submit', function(event){

		setHideAlert($("#login-feedback"));
        var isvalidate = $( this ).valid();

        if(isvalidate){
			var data = {}
			data["usernameOrEmail"] = $("#signInUsernameOrEmail").val();
			data["password"] = $("#signInPassword").val();
			
			$.ajax({
				type : 'POST',
				url  : pagectx + '/login',
				data : data,
				success :  function(response){
					console.log(response);
					if(response){
						location.reload();
					}else{
						$("#login-feedback > span").html("Incorrect login details!");					
						showAlert($( "#login-feedback" ), "danger");
					}
				},
				error : function(e) {
					$("#login-feedback > span").html("Error logging in. Please try again!");
					showAlert($( "#login-feedback" ), "danger");
					console.log("ERROR: ", e);
				},
				done : function(e) {}
			});
        }

	    event.preventDefault();
	});
}

//event handler that initialise login form validation
function event_loginFormValidate(){
    $(".login-form").validate({
        errorElement: "span",
        errorClass: "help-block",
        focusInvalid: !1,
        rules: {
        	usernameOrEmail: "required",
            password: "required"
        },
        messages: {
        	usernameOrEmail: "Please enter username or email",
        	password: "Please enter password"
        },
        errorPlacement: function(e, r) {
            e.insertBefore(r)
        }
    });
    $(".login-form input").keypress(function(e) {
        return 13 == e.which ? ($(".login-form").validate().form() && $(".login-form").submit(), !1) : void 0
    });
}