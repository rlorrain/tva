<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/general/taglib.jsp" %>

<div class="container">
	<form:form commandName="user" class="form-horizontal registrationForm">
		
		<c:if test="${param.success eq true}">
			<div class="alert alert-success">Registration successful!</div>
		</c:if>
			
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="name" cssClass="form-control" />
				<form:errors path="name" />
			</div>
		</div><!-- /form-group -->
		<div class="form-group">
			<label for="email" class="col-sm-2 control-label">E-mail:</label>
			<div class="col-sm-10">
				<form:input path="email" cssClass="form-control" />
				<form:errors path="email" />
			</div>
		</div><!-- /form-group -->
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Password:</label>
			<div class="col-sm-10">
				<form:password path="password" cssClass="form-control" />
				<form:errors path="password" />
			</div>
		</div><!-- /form-group -->
		<div class="form-group">
			<label for="password" class="col-sm-2 control-label">Re-type password:</label>
			<div class="col-sm-10">
				<input type="password" name="retype_password" id="retype_password" class="form-control">
			</div>
		</div><!-- /form-group -->
		<div class="form-group">
			<div class="col-sm-2">
				<input type="submit" value="Save" class="btn btn-lg btn-primary" />
			</div>
		</div><!-- /form-group -->
	</form:form>
</div><!-- /container -->

<script type="text/javascript">
$(document).ready(function() {
	$(".registrationForm").validate(
		{
			rules: {
				name: {
					required: true,
					minlength: 3,
					remote: {
						url: "<spring:url value='/register/validateUsername.html'/>",
						type: "get",
						data: {
							username: function() {
								return $("#name").val();
							}
						}
					}
				},
				email: {
					required: true,
					email: true,
					remote: {
						url: "<spring:url value='/register/validateEmail.html'/>",
						type: "get",
						data: {
							email: function() {
								return $("#email").val();
							}
						}
					}
				},
				password: {
					required: true,
					minlength: 5					
				},
				retype_password: {
					required: true,
					minlength: 5,
					equalTo: "#password" 
				}
			},
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element) {
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			},
			messages: {
				name: {
					remote: "Username already in use!"
				},
				email: {
					remote: "E-mail already in use!"
				}
			}
		}		
	)
});
</script>
