$(document).ready(function() {
	setDateTimePicker();
	$('.datetimepicker').val(new Date());
});

function setHideAlert(AlertObject){
	AlertObject.children('button').click(function(event){
        AlertObject.addClass('hide');
    });
}

function showAlert(AlertObject, AlertType){
    var classList = AlertObject.attr("class").split(/\s+/);
    for (var i = 0; i < classList.length; i++) {
        if (classList[i].startsWith("alert-")) {
            AlertObject.removeClass(classList[i]);
        }
    }
    AlertObject.addClass('alert-' + AlertType);
    AlertObject.removeAttr('style');
    AlertObject.removeClass('hide');
}

function setDateTimePicker(){
	$('.datetimepicker').datepicker();	
}

