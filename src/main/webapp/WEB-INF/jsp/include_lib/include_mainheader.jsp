		<header class="main-header">
			<!-- Logo -->
			<a href="index2.html" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>tpt</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>transporter</b></span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-custom-menu">

					<ul class="nav navbar-nav">

						<% if (session.getAttribute("username") == null) { %>
							<!-- Login Button and Popup Include -->
							<%@ include file="/WEB-INF/jsp/include_lib/login_popup.jsp"%>
						<% } else {%>
							<!-- User Account: style can be found in dropdown.less -->
							<%@ include file="/WEB-INF/jsp/include_lib/login_navprofile.jsp"%>
						<% } %>

						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i
								class="fa fa-gears"></i></a></li>
					</ul>
				</div>
			</nav>
		</header>