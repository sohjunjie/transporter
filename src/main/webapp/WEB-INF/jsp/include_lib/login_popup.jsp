<li id="SignInBtn" class="dropdown user user-menu">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
	<i class="fa fa-sign-in"></i><span class="hidden-xs">Sign In</span>
	</a>
</li>

<!-- The Modal -->
<div id="signInModal" class="modal">

	<!-- Modal content -->
	<div class="signin-modal-content box box-info">
	    <div class="box-header ui-sortable-handle">
	      <i class="fa fa-sign-in"></i>
	
	      <h3 class="box-title">Sign in to transporter</h3>
	      <!-- tools box -->
	      <div class="pull-right box-tools">
	        <button id="signin-modal-close" type="button" class="btn btn-info btn-sm close" data-toggle="tooltip" title="Close">
	          <i class="fa fa-times"></i></button>
	      </div>
	      <!-- /. tools -->
	    </div>
	    <div class="box-body">
	        <div id="login-feedback" class="alert alert-danger hide">
	            <button class="close message" data-close="alert">&times;</button>
	                <span>Incorrect Password</span>
	        </div>     
			<form class="login-form" action="" method="post">
				<div class="form-group">
			    	<label>Username or email</label><span class="login_validate_err_label"></span>
			    	<input id="signInUsernameOrEmail" type="text" class="form-control" name="usernameOrEmail" placeholder="Username or mail">
			  	</div>
			  	<div class="form-group">
			    	<label>Password</label><span class="login_validate_err_label"></span>
			    	<input id="signInPassword" type="password" class="form-control" name="password" placeholder="Password">
			  	</div>
			    <div class="box-footer clearfix">
			      <button type="submit" class="pull-right btn btn-default">Sign In
			        <i class="fa fa-arrow-circle-right"></i></button>
			    </div>
			</form>
	    </div>

	</div>
</div>