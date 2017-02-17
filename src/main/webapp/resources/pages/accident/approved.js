$('#vert_acc_menu').addClass('active');
$('#vert_acc_menu_approved').addClass('active');

$('.aAccidentMapLoc').on( "click", function() {
	event_getApprovedAccidentMapLoc(this.value);
});

function event_getApprovedAccidentMapLoc(reportId){
	var reportMarker = markers[reportId];
	sgmap.setZoom(14);
	sgmap.panTo(reportMarker.position);
}