<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="${resourcePath}/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">

<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

<!-- Admin Demo -->
<link rel="stylesheet" href="${resourcePath}/dist/css/AdminLTE.css">

<!-- Theme style -->
<link rel="stylesheet" href="${resourcePath}/dist/css/AdminLTE.min.css">

<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet" href="${resourcePath}/dist/css/skins/_all-skins.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${resourcePath}/plugins/iCheck/flat/blue.css">

<!-- Date Picker -->
<link rel="stylesheet" href="${resourcePath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet" href="${resourcePath}/plugins/datepicker/bootstrap-datetimepicker.css">

<!-- Daterange picker -->
<link rel="stylesheet" href="${resourcePath}/plugins/daterangepicker/daterangepicker.css">

<!-- boostrap imageupload -->
<link rel="stylesheet" href="${resourcePath}/plugins/bootstrap-imageupload/bootstrap-imageupload.min.css">

<!-- bootstrap wysihtml5 - text editor -->
<link rel="stylesheet" href="${resourcePath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

<!-- home -->
<link rel="stylesheet" href="${resourcePath}/pages/home/login-popup.css">

<!-- transporter -->
<link rel="stylesheet" href="${resourcePath}/pages/transporter.css">

<style>
#map {
	width: 100%;
	height: 500px;
	background-color: grey;
}
.enforcement_cam_icon{
	width: 32px;
	height: 32px;
}
#camera_viewer_table{
	height: 500px;
}

#camera_viewer_table > table > tbody>tr:focus{
	background-color: #f5f5f5;
}

#camera_viewer_table > table > tbody > tr > td:nth-child(1){
	width: 32px;
	vertical-align: middle;
}

#camera_viewer_table > table > tbody > tr > td:nth-child(3){
	width: 120px;
	vertical-align: middle;
	text-align: right;
}
.itemOptions{display: none;}
.itemOptions > button{float: none;}
.itemOptions > button:focus, .itemOptions > button:hover {opacity: 1;}
#camera_viewer_table > table > tbody > tr:hover .itemOptions{display:block;}
.cameraDelete{color: #e83030 !important;}
.cameraSetInstalled{color: #07e007 !important;}
.cameraMapLoc{color: #413dea !important;}
</style>