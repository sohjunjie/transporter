$('#vert_acc_menu').addClass('active');
$('#vert_acc_menu_pending').addClass('active');

$('.pAccidentDelete').on( "click", function() {
	event_deletePendingAccident(this.value);
});
$('.pAccidentApprove').on( "click", function() {
	event_approvePendingAccident(this.value);
});
$('.pAccidentMapLoc').on( "click", function() {
	event_getPendingAccidentMapLoc(this.value);
});

function event_deletePendingAccident(reportId){
	//send ajax delete pending accident
	data = {}
	data['reportId'] = reportId;

    var confirmDelete = confirm("Confirm deleting accident report?");
    if (!confirmDelete) { return; }

	$.ajax({
		type : 'POST',
		url  : pagectx + '/accident/report/reject',
		data : data,
		success :  function(response){
			if(response){
				alert("The accident report has been rejected!");
				location.reload();
			}
		},
		error : function(e) {console.log(e);},
		done : function(e) {console.log(e);}
	});
}

function event_approvePendingAccident(reportId){
	//send ajax approve pending accident
	data = {}
	data['reportId'] = reportId;

    var confirmApprove = confirm("Confirm approve accident report?");
    if (!confirmApprove) { return; }
	
	$.ajax({
		type : 'POST',
		url  : pagectx + '/accident/report/approve',
		data : data,
		success :  function(response){
			if(response){
				alert("The accident report has been successfully approved!");
				location.reload();
			}
		},
		error : function(e) {console.log(e);},
		done : function(e) {console.log(e);}
	});
}

function event_getPendingAccidentMapLoc(reportId){
	var reportMarker = markers[reportId];
	sgmap.setZoom(14);
	sgmap.panTo(reportMarker.position);
}