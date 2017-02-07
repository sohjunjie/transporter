		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li class="active treeview"><a href="index"> <i class="fa fa-car"></i>
							<span>Accident</span> <span class="pull-right-container"> 
							<i class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li class="active"><a href="index2.html">
								<i class="fa fa-exclamation-circle" style="color:red"></i> Report Accident</a></li>
							<% if (session.getAttribute("username") != null) { %>
							<li><a href="index.html">
								<i class="fa fa-flag-o" style="color:white"></i> Pending Report
								<span class="pull-right-container">
								<!-- TODO: badge showing number of reports pending approval -->
								<span class="label label-primary pull-right">4</span>
								</span>
								</a></li>

							<li><a href="index.html">
								<i class="fa fa-flag-o" style="color:red"></i> Resolved Approved
								</a></li>
							<% } %>
						</ul></li>

					<li><a href="pages/widgets.html"> <i class="fa fa-video-camera"></i>
							<span>Enforcement Camera</span>
					</a></li>

					<li><a href="pages/widgets.html"> <i class="fa fa-dashboard"></i>
							<span>Statistics</span>
					</a></li>

					<li class="treeview"><a href="#"> <i class="fa fa-files-o"></i>
							<span>Layout Options</span> <span class="pull-right-container">
								<span class="label label-primary pull-right">4</span>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="pages/layout/top-nav.html"><i
									class="fa fa-circle-o"></i> Top Navigation</a></li>
							<li><a href="pages/layout/boxed.html"><i
									class="fa fa-circle-o"></i> Boxed</a></li>
							<li><a href="pages/layout/fixed.html"><i
									class="fa fa-circle-o"></i> Fixed</a></li>
							<li><a href="pages/layout/collapsed-sidebar.html"><i
									class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
						</ul></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>