	<!-- jQuery 2.2.3 -->
	<script src="${resourcePath}plugins/jQuery/jquery-2.2.3.min.js"></script>

	<!-- jQuery Validate -->
	<script src="${resourcePath}plugins/jQueryValidation/jquery.validate.min.js"></script>

	<!-- jQuery UI 1.11.4 -->
	<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
  	$.widget.bridge('uibutton', $.ui.button);
	</script>
	<!-- Bootstrap 3.3.6 -->
	<script src="${resourcePath}bootstrap/js/bootstrap.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
	<!-- Sparkline -->
	<script src="${resourcePath}plugins/sparkline/jquery.sparkline.min.js"></script>
	<!-- jvectormap -->
	<script src="${resourcePath}plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="${resourcePath}plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- jQuery Knob Chart -->
	<script src="${resourcePath}plugins/knob/jquery.knob.js"></script>
	<!-- daterangepicker -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<script src="${resourcePath}plugins/daterangepicker/daterangepicker.js"></script>
	<!-- datepicker -->
	<script src="${resourcePath}plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script
		src="${resourcePath}plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<!-- Slimscroll -->
	<script src="${resourcePath}plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<!-- FastClick -->
	<script src="${resourcePath}plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="${resourcePath}dist/js/app.min.js"></script>

	<!-- AdminLTE dashboard demo (This is only for demo purposes)
	<script src="${resourcePath}dist/js/pages/dashboard.js"></script> -->

	<!-- AdminLTE for demo purposes -->
	<script src="${resourcePath}dist/js/demo.js"></script>
	
	<script src="${resourcePath}pages/login/login-popup.js"></script>
	<script src="${resourcePath}pages/login/login.js"></script>
	<script src="${resourcePath}pages/transporter.js"></script>
	
	<!-- Google map api -->
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8Cha4wQszJ2djt-AxJ_tYfGhSI70IDpk&region=SG&libraries=places&callback=initMap" type="text/javascript"></script>
	<script>

	var marker;
	
	function initMap() {
		var sgloc = {lat: 1.3553794, lng: 103.8677444};
		var sgmap = new google.maps.Map(document.getElementById('map'), {
			zoom: 12,
			center: sgloc,
			streetViewControl: false,
			fullscreenControl: false
		});
		marker = new google.maps.Marker({
			position: sgloc,
			map: sgmap,
			draggable: true,
			title: "Accident location"
		});
		var geocoder = new google.maps.Geocoder;
		var infowindow = new google.maps.InfoWindow({
		    content: "lat:" + sgloc['lat'] + " lng:" + sgloc['lng']
		});
	    marker.addListener('dragend', function() {
			// document.getElementById('pac-input').value = "tt"
			// infowindow.setContent("lat:" + marker.position.lat() + " lng:" + marker.position.lng())
	   	});
	    marker.addListener('click', function() {
	    	geocodeLatLng(geocoder, sgmap, infowindow);
	        infowindow.open(sgmap, marker);
	   	});

	    // Create the search box and link it to the UI element.
	    var input = document.getElementById('pac-input');
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
				marker.setPosition(place.geometry.location)
				
				if (place.geometry.viewport) {
				  	// Only geocodes have viewport.
					bounds.union(place.geometry.viewport);
				} else {
				  	bounds.extend(place.geometry.location);
				}
			});
          	sgmap.fitBounds(bounds);
        });
	    
	}
	
	function geocodeLatLng(geocoder, map, infowindow) {
		var latlng = {lat: marker.position.lat(), lng: marker.position.lng()};
		geocoder.geocode({'location': latlng}, function(results, status) {
			if (status === 'OK') {
				if (results[0]) {
					infowindow.setContent(results[0].formatted_address);
			    } else {
			    	infowindow.setContent('No results found');
			    }
			} else {
				infowindow.setContent('Geocoder failed due to: ' + status);
			}
		});
	}	
	</script>
	