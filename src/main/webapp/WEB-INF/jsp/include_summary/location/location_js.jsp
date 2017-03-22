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

	<script src="${resourcePath}/pages/summary/location.js"></script>
	<script src="${resourcePath}/pages/home/login-popup.js"></script>
	<script src="${resourcePath}/pages/home/login.js"></script>
	<script src="${resourcePath}/pages/transporter.js"></script>

	<!-- Script Variables -->
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var googleApiKey = "${properties['api.google.services']}";
	</script>

	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${properties['api.google.services']}&region=SG&libraries=places&callback=initMap" type="text/javascript"></script>
	<script src="${resourcePath}/markerclusterer/markerclusterer.js"></script>
	<script>
    
    var pagectx = "${pageContext.servletContext.contextPath}";
	var marker;
	var sgmap;
	var markers = [];
	
	function initMap() {

		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			mapTypeControl: false,
			streetViewControl: false,
			fullscreenControl: false
		});
		
		var labels = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
		
		<c:forEach items="${accidentReports}" var="aAccident" varStatus = "status">
		if('${aAccident.resolvedBy}' != ''){
			cameraIconLink = '/resources/icons/accident_resolved32x32.png';
		}
		else {
			cameraIconLink = '/resources/icons/accident_approved32x32.png';
		}
			marker = new google.maps.Marker({
				position: {lat: ${aAccident.latitude}, lng: ${aAccident.longitude}},
				map: sgmap,
				label: labels[status.index % labels.length],
				icon: pagectx + cameraIconLink
			});
			markers.push(marker);
		</c:forEach>
		
		 var markerCluster = new MarkerClusterer(sgmap, markers,
		            {imagePath: '${resourcePath}/markerclusterer/img/m'});
		      
//		getLocation();

	}

	function geocodeLatLng(geocoder, markerloc) {
		var latlng = {lat: markerloc.position.lat(), lng: markerloc.position.lng()};
		geocoder.geocode({'location': latlng}, function(results, status) {
			if (status === 'OK') {
				if (results[0]) {
					document.getElementById('accidentLocation').value = results[0].formatted_address;
			    } else {
			    	document.getElementById('accidentLocation').value = "Location cannot be identified";
			    }
			} else {
				document.getElementById('accidentLocation').value = "Location cannot be identified due to: " + status;
			}
		});
	}

	function getLocation() {
	    if (navigator.geolocation) {
	    	navigator.geolocation.getCurrentPosition(showPosition);
	    }
	}
	function showPosition(position) {
		marker.setPosition({lat: position.coords.latitude, lng: position.coords.longitude});
		sgmap.setZoom(13);
		sgmap.panTo(marker);
	}
	</script>
