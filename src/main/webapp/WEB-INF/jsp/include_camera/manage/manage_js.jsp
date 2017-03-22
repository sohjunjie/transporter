<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<!-- jQuery 2.2.3 -->
	<script src="${resourcePath}/plugins/jQuery/jquery-2.2.3.min.js"></script>

	<!-- jQuery Validate -->
	<script src="${resourcePath}/plugins/jQueryValidation/jquery.validate.min.js"></script>

	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>$.widget.bridge('uibutton', $.ui.button);</script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${resourcePath}/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<!-- Sparkline -->
	<script src="${resourcePath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="${resourcePath}/plugins/knob/jquery.knob.js"></script>
	<!-- daterangepicker -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script src="${resourcePath}/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script src="${resourcePath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script src="${resourcePath}/plugins/datepicker/bootstrap-datetimepicker.js"></script>

	<!-- boostrap imageupload -->
	<script src="${resourcePath}/plugins/bootstrap-imageupload/bootstrap-imageupload.min.js"></script>

	<!-- Bootstrap WYSIHTML5 -->
	<script src="${resourcePath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="${resourcePath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="${resourcePath}/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${resourcePath}/dist/js/app.min.js"></script>

	<script src="${resourcePath}/pages/home/login-popup.js"></script>
	<script src="${resourcePath}/pages/home/login.js"></script>

	<script src="${resourcePath}/pages/transporter.js"></script>

	<script src="${resourcePath}/pages/camera/manage/manage.js"></script>

	<!-- Script Variables -->
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var googleApiKey = "${properties['api.google.services']}";
	</script>

	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${properties['api.google.services']}&region=SG&libraries=places&callback=initMap" type="text/javascript"></script>
	<script>

	var markers = {};
	var sgmap;
	var infowindowCamera;
	
	function initMap() {
		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			mapTypeControl: false,
			streetViewControl: false,
			fullscreenControl: false
		});

		infowindowCamera = new google.maps.InfoWindow();

		var cameraIconLink = "";
		<c:forEach items="${enforcementCamera}" var="camera">
			if('${camera.type}' == 'SPEED' && '${camera.status}' == 'PENDING'){
				cameraIconLink = '/resources/icons/speed_camera_pending32x32.png';
			}
			if('${camera.type}' == 'SPEED' && '${camera.status}' == 'INSTALLED'){
				cameraIconLink = '/resources/icons/speed_camera32x32.png';
			}
			if('${camera.type}' == 'TRAFFIC' && '${camera.status}' == 'PENDING'){
				cameraIconLink = '/resources/icons/traffic_camera_pending32x32.png';
			}
			if('${camera.type}' == 'TRAFFIC' && '${camera.status}' == 'INSTALLED'){
				cameraIconLink = '/resources/icons/traffic_camera32x32.png';
			}
			addMarkerToMap(${camera.latitude},
					${camera.longitude}, sgmap,
					${camera.cameraId}, pagectx + cameraIconLink,
					'${camera.status}', '${camera.type}', "${camera.formattedAddress}");
		</c:forEach>
	}

	function addMarkerToMap(lat, lng, map, reportId, iconImg, camStatus, camType, formattedAddress) {
		var latlng = {lat: lat, lng: lng};
		var marker = new google.maps.Marker({
			position: latlng,
			map: map,
			icon: iconImg
		});
		markers[reportId] = marker;
		marker.addListener('click', function() {
			infowindowCamera.setContent("<b>" + formattedAddress + "</b><br/>"
					+ "type: " + camType + "<br/>"
					+ "status: " + camStatus + "<br/>"
					+ "lat: " + lat + "<br/>"
					+ "lng: " + lng + "<br/>");
			infowindowCamera.open(map, marker);
		});
	}

	</script>