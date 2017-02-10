$(document).ready(function() {
	setDateTimePicker();
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

// TODO: How to set default datetime with format DD/MM/YYYY HH:MM
function setDateTimePicker(){
	$('.datetimepicker').datetimepicker({
		useCurrent: true,
		format: 'DD/MM/YYYY HH:mm'
	});
}

