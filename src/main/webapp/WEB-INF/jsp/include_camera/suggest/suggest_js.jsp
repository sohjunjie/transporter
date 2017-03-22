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

	<script src="${resourcePath}/pages/camera/suggest/suggest.js"></script>
	<script src="${resourcePath}/pages/camera/suggest/suggest-camera.js"></script>
	<script src="${resourcePath}/pages/camera/suggest/suggest-camera-popup.js"></script>

	<!-- Script Variables -->
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var googleApiKey = "${properties['api.google.services']}";
	</script>

	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${properties['api.google.services']}&region=SG&libraries=places&callback=initMap" type="text/javascript"></script>
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var markers = {};
	var suggestMarker;
	var sgmap;
	var infowindowCamera;

	function initMap() {
		infowindowCamera = new google.maps.InfoWindow();

		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			mapTypeControl: false,
			streetViewControl: false,
			fullscreenControl: false
		});

		suggestMarker = new google.maps.Marker({
			position: sgloc,
			map: sgmap,
			draggable: true
		});

		var geocoder = new google.maps.Geocoder;
		var infowindow = new google.maps.InfoWindow({
		    content: "Drag pin to specify location to suggest camera"
		});

		document.getElementById('cameraLatitude').value = suggestMarker.position.lat();
    	document.getElementById('cameraLongitude').value = suggestMarker.position.lng();
    	geocodeLatLng(geocoder, suggestMarker);
    	suggestMarker.addListener('dragend', function() {
	    	document.getElementById('cameraLatitude').value = this.position.lat();
	    	document.getElementById('cameraLongitude').value = this.position.lng();
	    	geocodeLatLng(geocoder, this);
	   	});
		suggestMarker.addListener('click', function() {
	        infowindow.open(sgmap, suggestMarker);
	   	});

	    var suggestCameraBtn = document.getElementById('suggest_camera_btn');
	    sgmap.controls[google.maps.ControlPosition.TOP_RIGHT].push(suggestCameraBtn);

	    var input = document.getElementById('pac_input');
	    sgmap.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	    var searchBox = new google.maps.places.SearchBox(input);

	    sgmap.addListener('bounds_changed', function() {
    		searchBox.setBounds(sgmap.getBounds());
	    });

        var searchMarkers = [];
        searchBox.addListener('places_changed', function() {
	        var places = searchBox.getPlaces();

	        if (places.length == 0) {
	          return;
	        }

	        // Clear out the old markers.
	        searchMarkers.forEach(function(searchMarker) {
	      	  searchMarker.setMap(null);
	        });
	        searchMarker = [];

			// For each place, get the icon, name and location.
			var bounds = new google.maps.LatLngBounds();
			places.forEach(function(place) {
				if (!place.geometry) {
					console.log("Returned place contains no geometry");
					return;
				}

				suggestMarker.setPosition(place.geometry.location);
				
				if (place.geometry.viewport) {
					bounds.union(place.geometry.viewport);
				} else {
				  	bounds.extend(place.geometry.location);
				}
			});
          	sgmap.fitBounds(bounds);
        });


		// initialise all camera markers
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

	function geocodeLatLng(geocoder, markerloc) {
		var latlng = {lat: markerloc.position.lat(), lng: markerloc.position.lng()};
		geocoder.geocode({'location': latlng}, function(results, status) {
			if (status === 'OK') {
				if (results[0]) {
					document.getElementById('cameraLocation').value = results[0].formatted_address;
			    } else {
			    	document.getElementById('cameraLocation').value = "Location cannot be identified";
			    }
			} else {
				document.getElementById('cameraLocation').value = "Location cannot be identified due to: " + status;
			}
		});
	}
    </script>
