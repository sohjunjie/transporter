var imageModal = document.getElementById('imageModal');

if(imageModal != null){

	var modalImg = document.getElementById("modalImage");
	var captionText = document.getElementById("caption");

	$('.imageEnlarge').on( "click", function() {
		imageModal.style.display = "block";
	    modalImg.src = this.src;
	    captionText.innerHTML = this.alt;
	});

	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("imageModalClose")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() { 
		imageModal.style.display = "none";
	}

	window.onclick = function(event) {
	    if (event.target == imageModal) {
	    	imageModal.style.display = "none";
	    }
	}

}