<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/general/taglib.jsp"%>

<div class="container">
	
		<h1><c:out value="${user.name}" /></h1> 
	
		<br>
	
	<!-- Nav tabs -->
	<ul class="nav nav-tabs">
		<li><a href="#overview" data-toggle="tab">Overview</a></li>
		<li><a href="#roles" data-toggle="tab">Roles</a></li>
	</ul><!-- /Nav tabs -->

	<!-- Tab panes -->
	<div class="tab-content">
	
		<div class="tab-pane" id="overview">
		
		</div><!-- /Tab overview -->
		
		<div class="tab-pane" id="roles">
			<br>
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>Role</th>
						<th>Remove</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${user.roles}" var="role">
						<tr>
							<td><c:out value="${role.name}" /></td>
							<td><c:if test="${user.name != 'admin'}">
								<a href='<spring:url value="/users/${user.id}/${role.name}.html" />' class="btn btn-danger triggerRemove">
									<span class="glyphicon glyphicon-trash" aria-hidden="true" ></span>
								</a>
							</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target=".bs-example-modal-sm">Add role</button>
		</div><!-- /Tab roles -->

	</div><!-- /Tab panes -->
</div><!-- /container -->

<!-- Model add blog -->
<form:form commandName="role" cssClass="form-horizontal blogForm">
	<div class="modal fade bs-example-modal-sm" id="modalAddRole" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-sm" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add new role to user.</h4>
				</div>
				<div class="modal-body">
				
					<div class="form-group">
					<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:select path="name">
								<form:option value="ROLE_ADMIN">ROLE_ADMIN</form:option>
								<form:option value="ROLE_SUPERUSER">ROLE_SUPERUSER</form:option>
								<form:option value="ROLE_USER">ROLE_USER</form:option>
							</form:select>
						</div>
					</div><!-- /form-group -->
															
				</div><!-- /modal-body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="save">
				</div><!-- /modal-footer -->
			</div>
		</div>
	</div>
</form:form><!-- /modal add blog -->

<!-- Modal remove -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  	<div class="modal-dialog">
    	<div class="modal-content">
	      	<div class="modal-header">
	        	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
	        	<h4 class="modal-title" id="myModalLabel">Remove Blog</h4>
	      	</div>
	      	<div class="modal-body">
	        Really want to remove?
	      	</div>
	      	<div class="modal-footer">
	        	<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	        	<a href="" class="btn btn-danger removeBtn">Remove</a>
	      	</div>
    	</div>
  	</div>
</div><!-- /Modal remove -->

<script type="text/javascript">
	$(document).ready(function() {
		$('.nav-tabs a:first').tab('show'); // select first tab
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>