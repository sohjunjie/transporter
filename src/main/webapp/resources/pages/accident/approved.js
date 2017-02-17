$('#vert_acc_menu').addClass('active');
$('#vert_acc_menu_approved').addClass('active');

$('.aAccidentResolve').on( "click", function() {
	event_resolveApprovedAccident(this.value);
});
$('.aAccidentMapLoc').on( "click", function() {
	event_getApprovedAccidentMapLoc(this.value);
});

function event_resolveApprovedAccident(reportId){
	//send ajax approve pending accident
	data = {}
	data['reportId'] = reportId;

    var confirmApprove = confirm("Confirm resolve accident report?");
    if (!confirmApprove) { return; }
	
	$.ajax({
		type : 'POST',
		url  : pagectx + '/accident/report/resolve',
		data : data,
		success :  function(response){
			if(response){
				alert("The accident report has been successfully resolved!");
				location.reload();
			}
		},
		error : function(e) {console.log(e);},
		done : function(e) {console.log(e);}
	});
}

function event_getApprovedAccidentMapLoc(reportId){
	var reportMarker = markers[reportId];
	sgmap.setZoom(14);
	sgmap.panTo(reportMarker.position);
}