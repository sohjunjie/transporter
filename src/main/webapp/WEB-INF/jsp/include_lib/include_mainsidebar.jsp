		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li id="vert_acc_menu" class="treeview"><a href="${pageContext.servletContext.contextPath}">
						<i class="fa fa-car"></i>
							<span>Accident</span>
							<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i>
								</span></a>
						<ul class="treeview-menu">
							<li id="vert_acc_menu_report"><a href="${pageContext.servletContext.contextPath}">
								<i class="fa fa-exclamation-circle" style="color:red"></i> Report Accident</a></li>
							<% if (session.getAttribute("username") != null) { %>
							<li id="vert_acc_menu_pending"><a href="${pageContext.servletContext.contextPath}/accident/pending">
								<i class="fa fa-flag-o" style="color:white"></i> Pending Report
								<span class="pull-right-container">
								<span id="pending_report_count_badge" class="label label-primary pull-right"></span>
								</span>
								</a></li>

							<li id="vert_acc_menu_approved"><a href="${pageContext.servletContext.contextPath}/accident/approved">
								<i class="fa fa-flag-o" style="color:red"></i> Resolve Approved
								<span class="pull-right-container">
								<span id="approved_report_count_badge" class="label label-primary pull-right"></span>
								</span>
								</a></li>
							<% } %>
						</ul></li>

					<% if (session.getAttribute("username") != null) { %>
					<li id="vert_cam_menu" class="treeview"><a href="${pageContext.servletContext.contextPath}/camera/suggest"> <i class="fa fa-video-camera"></i>
							<span>Enforcement Camera</span>
							<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i>
								</span></a>
						<ul class="treeview-menu">
							<li id="vert_cam_menu_suggest"><a href="${pageContext.servletContext.contextPath}/camera/suggest">
								<i class="fa fa-pencil"></i> Suggest Camera</a></li>

							<li id="vert_cam_menu_manage"><a href="${pageContext.servletContext.contextPath}/camera/manage">
								<i class="fa fa-laptop"></i> Manage Camera</a></li>

						</ul></li>
					<% } %>

					<li id="vert_stat_menu" class="treeview"><a href="index"> <i class="fa fa-dashboard"></i>
							<span>Statistics</span> <span class="pull-right-container"> 
							<i class="fa fa-angle-left pull-right"></i>
							</span>
					</a>
					<ul class="treeview-menu">
							<li id="vert_stat_cause_report"><a href="${pageContext.servletContext.contextPath}/summary/cause">
								<i class="fa fa-file"></i> Summary Report By Cause</a></li>
							<li id="vert_stat_time_report"><a href="${pageContext.servletContext.contextPath}/summary/time">
								<i class="fa fa-file"></i> Summary Report By Time</a></li>
							<li id="vert_stat_location_report"><a href="${pageContext.servletContext.contextPath}/summary/location">
								<i class="fa fa-file"></i> Summary Report By Location</a></li>
						</ul></li>
					
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>