							<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="${resourcePath}dist/img/avatar.jpg" class="user-image"
								alt="User Image">
								<!-- TODO: User full name goes here -->
								<span class="hidden-xs">${ sessionScope.username }</span>
								</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="${resourcePath}dist/img/avatar.jpg" class="img-circle"
									alt="User Image">

									<p>${ sessionScope.userfullname } - LTA Personnel</p></li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Profile</a>
									</div>
									<div class="pull-right">
										<a href="logout" class="btn btn-default btn-flat">Sign out</a>
									</div>
								</li>
							</ul></li>