<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<spring:url value="/resources" var="resourcePath" />

<title>transporter | Suggest Camera</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- start css library include -->
<%@ include file="/WEB-INF/jsp/include_camera/suggest/suggest_css.jsp"%>

<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		
		<!-- Main Header Include -->
		<%@ include file="/WEB-INF/jsp/include_lib/include_mainheader.jsp"%>

		<!-- Main Sidebar Include -->
		<%@ include file="/WEB-INF/jsp/include_lib/include_mainsidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>Suggest Camera</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-video-camera"></i> Camera</a></li>
					<li class="active">Suggest Camera</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<input id="pac_input" class="form-control" type="text" placeholder="Type address to search" style="width: 300px;">
				<button id="suggest_camera_btn" class="btn btn-info">
					<i class="fa fa-video-camera"></i> Suggest here!</button>
				<div id="map"></div>
			</section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->

		<!-- Footer Include -->
		<%@ include file="/WEB-INF/jsp/include_lib/include_footer.jsp"%>

		<!-- Control Sidebar Include -->
		<%@ include file="/WEB-INF/jsp/include_lib/include_controlsidebar.jsp"%>

	</div>

	<!-- JS Library Include -->
	<%@ include file="/WEB-INF/jsp/include_camera/suggest/suggest_js.jsp"%>

</body>
</html>
