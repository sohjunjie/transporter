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
	
	<script src="${resourcePath}/pages/accident/pending.js"></script>
	<script src="${resourcePath}/pages/accident/image_popup.js"></script>

	<script src="${resourcePath}/pages/transporter.js"></script>

	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8Cha4wQszJ2djt-AxJ_tYfGhSI70IDpk&region=SG&callback=initMap" type="text/javascript"></script>
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var markers = {};
	var sgmap;
	var infowindow;

	function initMap() {
		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			mapTypeControl: false,
			streetViewControl: false,
			fullscreenControl: false
		});
		infowindow = new google.maps.InfoWindow();

		// initialise pending accident locations
		<c:forEach items="${pendingAccidents}" var="pAccident">
		addMarkerToMap(${pAccident.latitude},
				${pAccident.longitude}, sgmap,
				${pAccident.reportId}, pagectx + '/resources/icons/accident_pending32x32.png');
		</c:forEach>
	}

	function addMarkerToMap(lat, lng, map, pid, iconImg) {
		var latlng = {lat: lat, lng: lng};
		var marker = new google.maps.Marker({
			position: latlng,
			map: map,
			icon: iconImg
		});
		markers[pid] = marker;
	}

    function setMapOnAll(map) {
		for (var i = 0; i < markers.length; i++) {
			markers[i].setMap(map);
		}
	}

    function clearMarkers() {
		setMapOnAll(null);
    }

    function deleteMarkers() {
		clearMarkers();
		markers = [];
	}
    </script>
	