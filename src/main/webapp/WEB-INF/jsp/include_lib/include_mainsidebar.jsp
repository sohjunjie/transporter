		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li id="vert_acc_menu" class="treeview"><a href="index"> <i class="fa fa-car"></i>
							<span>Accident</span> <span class="pull-right-container"> 
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
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

					<li><a href="pages/widgets.html"> <i class="fa fa-video-camera"></i>
							<span>Enforcement Camera</span>
					</a></li>

					<li><a href="pages/widgets.html"> <i class="fa fa-dashboard"></i>
							<span>Statistics</span>
					</a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>