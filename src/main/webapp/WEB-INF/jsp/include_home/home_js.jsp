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

	<script src="${resourcePath}/pages/home/home.js"></script>
	<script src="${resourcePath}/pages/home/login-popup.js"></script>
	<script src="${resourcePath}/pages/home/login.js"></script>
	<script src="${resourcePath}/pages/transporter.js"></script>
	
	<script src="${resourcePath}/pages/home/report-popup.js"></script>
	<script src="${resourcePath}/pages/home/report.js"></script>

	<!-- Script Variables -->
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var googleApiKey = "${properties['api.google.services']}";
	</script>

	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=${properties['api.google.services']}&region=SG&libraries=places&callback=initMap" type="text/javascript"></script>
	<script>
    var pagectx = "${pageContext.servletContext.contextPath}";
	var marker;
	var sgmap;
	var currentAccidentMarkers = {};
	var accidentInfowindow;

	function initMap() {

		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			mapTypeControl: false,
			streetViewControl: false,
			fullscreenControl: false
		});
		marker = new google.maps.Marker({
			position: sgloc,
			map: sgmap,
			draggable: true,
			title: "Accident location"
		});

		accidentInfowindow = new google.maps.InfoWindow();

//		getLocation();
    	
		var geocoder = new google.maps.Geocoder;
		var infowindow = new google.maps.InfoWindow({
		    content: "Drag pin to specify location of accident"
		});
		document.getElementById('accidentLatitude').value = marker.position.lat();
    	document.getElementById('accidentLongitude').value = marker.position.lng();
    	geocodeLatLng(geocoder, marker);
		marker.addListener('dragend', function() {
	    	document.getElementById('accidentLatitude').value = this.position.lat();
	    	document.getElementById('accidentLongitude').value = this.position.lng();
	    	geocodeLatLng(geocoder, this);
	   	});
	    marker.addListener('click', function() {
	        infowindow.open(sgmap, marker);
	   	});

	    // Create report accident button and link it to the UI element
	    var reportBtn = document.getElementById('report_accident_btn');
	    sgmap.controls[google.maps.ControlPosition.TOP_RIGHT].push(reportBtn);

	    // Create the search box and link it to the UI element.
	    var input = document.getElementById('pac_input');
	    var searchBox = new google.maps.places.SearchBox(input);
	    sgmap.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	    // Bias the SearchBox results towards current map's viewport.
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
				var icon = {
					url: place.icon,
					size: new google.maps.Size(71, 71),
					origin: new google.maps.Point(0, 0),
					anchor: new google.maps.Point(17, 34),
					scaledSize: new google.maps.Size(25, 25)
				};

				// Update our marker for each place.
				marker.setPosition(place.geometry.location);
				
				if (place.geometry.viewport) {
				  	// Only geocodes have viewport.
					bounds.union(place.geometry.viewport);
				} else {
				  	bounds.extend(place.geometry.location);
				}
			});
          	sgmap.fitBounds(bounds);
        });
	    
        <c:forEach items="${currentReports}" var="aAccident">
		currentaccmarker = new google.maps.Marker({
			position: {lat: ${aAccident.latitude}, lng: ${aAccident.longitude}},
			map: sgmap,
			icon: pagectx + '/resources/icons/accident_approved32x32.png',
		});
		</c:forEach>

		var accidentIconLink = '/resources/icons/accident_approved32x32.png';
		<c:forEach items="${currentReports}" var="aAccident">
			addAccidentMarkerToMap(${aAccident.latitude},
					${aAccident.longitude}, sgmap,
					${aAccident.reportId}, pagectx + accidentIconLink,
					"${aAccident.formattedAddress}", "${aAccident.description}", "${aAccident.accidentDateTime}", "${resourcePath}${aAccident.imageLink}");
		</c:forEach>

	}

	function addAccidentMarkerToMap(lat, lng, map, reportId, iconImg, formattedAddress, description, accidentDateTime, imageLink) {
		var latlng = {lat: lat, lng: lng};
		var newmarker = new google.maps.Marker({
			position: latlng,
			map: map,
			icon: iconImg
		});
		currentAccidentMarkers[reportId] = newmarker;
		newmarker.addListener('click', function() {
			accidentInfowindow.setContent(
					'<table class="table" style="margin-bottom: 0px;">'	+
					'<tbody>'	+
					'<tr>'		+
						'<td><img src="' + imageLink + '" style=' + '"height: 90px; width: 90px;"></td>' +
						'<td><b>' + formattedAddress + '</b><br/>' +
							description + '<br/>' +
							accidentDateTime +
						'</td>' +
					'</tr>' +
					'</tbody>' +
					'</table>'
			);
			accidentInfowindow.open(map, newmarker);
		});
		
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
