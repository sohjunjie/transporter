$('#vert_cam_menu').addClass('active');
$('#vert_cam_menu_manage').addClass('active');

$('.cameraMapLoc').on( "click", function() {
	event_getCameraMapLoc(this.value);
});
$('.cameraDelete').on( "click", function() {
	event_deleteCamera(this.value);
});
$('.cameraSetInstalled').on( "click", function() {
	event_cameraSetApproved(this.value);
});

function event_getCameraMapLoc(cameraId){
	var reportMarker = markers[cameraId];
	sgmap.setZoom(14);
	sgmap.panTo(reportMarker.position);
	google.maps.event.trigger(reportMarker, 'click' );
}

function event_deleteCamera(cameraId){
	//send ajax delete pending accident
	data = {}
	data['cameraId'] = cameraId;

    var confirmDelete = confirm("Confirm deleting camera?");
    if (!confirmDelete) { return; }

	$.ajax({
		type : 'POST',
		url  : pagectx + '/camera/delete',
		data : data,
		success :  function(response){
			if(response){
				alert("The camera has been deleted!");
				location.reload();
			}
		},
		error : function(e) {console.log(e);},
		done : function(e) {console.log(e);}
	});
}

function event_cameraSetApproved(cameraId){
	//send ajax delete pending accident
	data = {}
	data['cameraId'] = cameraId;
	data['cameraStatusOrdinal'] = 1;

    var confirmDelete = confirm("Confirm approve camera?");
    if (!confirmDelete) { return; }

	$.ajax({
		type : 'POST',
		url  : pagectx + 'camera/set/status',
		data : data,
		success :  function(response){
			if(response){
				alert("The camera has been deleted!");
				location.reload();
			}
		},
		error : function(e) {console.log(e);},
		done : function(e) {console.log(e);}
	});
}