<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<spring:url value="/resources/" var="resourcePath" />

<title>transporter | Home</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<!-- start css library include -->
<%@ include file="/WEB-INF/jsp/include_accident/pending_css.jsp"%>
<!--  end css library include  -->

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
				<h1>
					Pending Report
				</h1>
				<ol class="breadcrumb">
					<li><a href="${pageContext.servletContext.contextPath}"><i class="fa fa-dashboard"></i> Accident</a></li>
					<li class="active">Pending Report</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-lg-6 col-xs-6">
						<div id="map"></div>
					</div>
					<div class="col-lg-6 col-xs-6">
						<%@ include file="/WEB-INF/jsp/include_accident/pending_viewer.jsp"%>
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
	<%@ include file="/WEB-INF/jsp/include_accident/pending_js.jsp"%>

</body>
</html>
