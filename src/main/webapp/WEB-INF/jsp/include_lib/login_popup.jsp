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
	        <button type="button" class="btn btn-info btn-sm close" data-toggle="tooltip" title="Close">
	          <i class="fa fa-times"></i></button>
	      </div>
	      <!-- /. tools -->
	    </div>
	    <div class="box-body">
	      <form action="" method="post">
	        <div class="form-group">
	          <label>Username or email</label>
	          <input id="signInUsernameOrEmail" type="text" class="form-control" name="usernameOrEmail" placeholder="Username or mail">
	        </div>
	        <div class="form-group">
	          <label>Password</label>
	          <input id="signInPassword" type="password" class="form-control" name="password" placeholder="Password">
	        </div>
	      </form>
	    </div>
	    <div class="box-footer clearfix">
	      <button type="button" class="pull-right btn btn-default" id="SignIn">Sign In
	        <i class="fa fa-arrow-circle-right"></i></button>
	    </div>
	</div>
</div>