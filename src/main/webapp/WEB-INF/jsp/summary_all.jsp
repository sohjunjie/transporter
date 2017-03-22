<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<spring:url value="/resources" var="resourcePath" />

<title>transporter | Summary | Location</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- CSS Library Include -->
<%@ include file="/WEB-INF/jsp/include_summary/location/location_css.jsp"%>

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
				<h1>Summary Report</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Summary</a></li>
					<li class="active"><i class="fa fa-file"></i> Summary Report</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-lg-6 col-xs-6">
						<%@ include file="/WEB-INF/jsp/include_summary/cause/cause_chart.jsp"%>
					</div>
					<div class="col-lg-6 col-xs-6">
						<%@ include file="/WEB-INF/jsp/include_summary/time/time_chart.jsp"%>
					</div>
				<div class="col-lg-6 col-xs-6" style="padding-right: 0px;">
						<div id="map"></div>
					</div>
					</div>
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
	<%@ include file="/WEB-INF/jsp/include_summary/all/all_js.jsp"%>

</body>
</html>