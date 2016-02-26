<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<style>
.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.btn-xs.navbar-btn {
    margin-top: 12px;
    margin-left: 5px;
}

ul.nav.navbar-nav.navbar-right {
	padding-right: 10px;
}
</style>

<tilesx:useAttribute name="current" />

<div class="container">
	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="<spring:url value="/" />">TVA</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="${current == 'index' ? 'active' : ''}">
						<a href='<spring:url value="/" />'>Home</a>
					</li>
					<li class="${current == 'tournaments' ? 'active' : ''}">
						<a href='<spring:url value="/tournaments.html" />'>Tournaments</a>
					</li>
					<li class="${current == 'events' ? 'active' : ''}">
						<a href='<spring:url value="/events.html" />'>Events</a>
					</li>
					<li class="${current == 'matchmaker' ? 'active' : ''}">
						<a href='<spring:url value="/matchmaker.html" />'>MatchMaker</a>
					</li>
					<li class="${current == 'tennis-kids' ? 'active' : ''}">
						<a href='<spring:url value="/tennis-kids.html" />'>Tennis Kids</a>
					</li>
					<security:authorize access="isAuthenticated()">  
						<li class="${current == 'account' ? 'active' : ''}">
							<a href='<spring:url value="/account.html" />'>My account</a>
						</li>
					</security:authorize>
					<li class="${current == 'contact' ? 'active' : ''}">
						<a href='<spring:url value="/contact.html" />'>Contact</a>
					</li>					
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<security:authorize access="!isAuthenticated()">
						<li class="${current == 'user-register' ? 'active' : ''}">
							<button type="button" id="registerButton" class="btn btn-default btn-xs navbar-btn" >Register</button>
						</li>
						<li class="${current == 'login' ? 'active' : ''}">
							<button type="button" class="btn btn-default btn-xs navbar-btn" data-toggle="modal" data-target="#modalLogin">
							<span class="glyphicon glyphicon-log-in"></span> Login</button>
						</li>
					</security:authorize>
					<security:authorize access="isAuthenticated()">	
						<li><span><a type="button" class="btn btn-primary btn-xs navbar-btn" href='<spring:url value="/logout" />'>
						<span class="glyphicon glyphicon-log-out"></span> Logout</a></span></li>
					</security:authorize>
					<security:authorize access="hasRole('ROLE_ADMIN')"> 
						<li class="${current == 'users' ? 'active' : ''}">
							<span><a class="btn btn-primary btn-xs navbar-btn" href='<spring:url value="/users.html" />'><span class="glyphicon glyphicon-cog" aria-hidden="true"></span></a></span>
						</li>
					</security:authorize>
				</ul>
			</div><!--/.nav-collapse -->
		</div><!--/.container-fluid -->
	</nav>
</div>

<!-- Modal login -->
<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog">
    	<div class="modal-content">
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title" id="myModalLabel">Login</h4>
	      	</div>
	      	<div class="modal-body">
	        
	        <form class="form-signin" action="/login" method="POST">
				<h2 class="form-signin-heading">Please sign in</h2>
				<input type="text" name="username" class="form-control" placeholder="Name" required autofocus> 
				<input type="password" name="password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				<br>
				<div class="text-center">
					<a href="#">Forgot password</a> | <a href='<spring:url value="register.html" />'>Register</a>
				</div>
			</form>
	        
	      	</div>
	      	<div class="modal-footer">
	        	<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	      	</div>
    	</div>
  	</div>
</div><!-- Modal login -->

<script type="text/javascript">
    document.getElementById("registerButton").onclick = function () {
        location.href = "/register.html"  
    };
</script>
