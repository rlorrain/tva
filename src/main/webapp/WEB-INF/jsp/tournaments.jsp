<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/general/taglib.jsp" %>

<style>
table.table tr.tournamentRow {
	margin: 0px;
}
table.table tr.tournamentRow td {
	border: none;
	margin: 0px;
	padding-top: 6px;
	padding-left: 2px;
	padding-right: 2px;
	padding-bottom: 0px;
}
table.table tr.tournamentRow td div {
	margin: 0px;
}
table.table tr.tournamentRow td div.custom-col-1 {
	float: left;
	width: 70px;
}
table.table tr.tournamentRow td div.custom-col-2 {
	float: left;
	width: auto;
}
table.table tr.tournamentRow td div.custom-col-3 {
	float: right;
	width: 50p;
}
table.table tr.tournamentRow td div a.btn {
	text-decoration: none;
	color: white;
}
</style>

<div class="container">

	<div id="bannerTournament" class="row">
		<img class="img img-responsive" alt="bannerTournament" src="/static/img/claycourt_5.png">
	</div>

	<table class="table">
		<thead>
			<tr>
				<th>Geplande Toernooien</th>
			</tr>
		</thead>
		<tbody>
			<tr class="tournamentRow">
				<td>
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
						<c:forEach items="${tournaments}" var="tournament">
	  						<div class="panel panel-default">
	    						<div class="panel-heading row" role="tab" id="headingOne">
	    							<div class="custom-col-1">
	    								<a class="btn btn-primary btn-xs" role="button" data-toggle="collapse" data-parent="#accordion" href="#${tournament.id}" aria-expanded="true" aria-controls="${tournament.name}">
	          								<span class="glyphicon glyphicon-info-sign"></span> Info
	        							</a>
	    							</div>
	    							<div class="custom-col-2">
	    								<h3 class="panel-title">
	        								<span><c:out value="${tournament.name}" /></span>	
	      								</h3>
	    							</div>
	    							<div class="custom-col-3">
	    								<a href="#" data-toggle="tooltip" title="Schrijf je in via toernooiklapper.nl!"> >> inschrijven</a>
	    							</div>
	    						</div>
	    						<div id="${tournament.id}" class="panel-collapse collapsing" role="tabpanel" aria-labelledby="headingOne">
	      							<div class="panel-body">
	        							<c:out value="${tournament.infoText}" />
	      							</div>
	    						</div>
	  						</div>
  						</c:forEach>
  					</div>
				</td>
			</tr>
		</tbody>
	</table>

</div><!-- /container -->

<script type="text/javascript">
	$(function () {
		$('[data-toggle="tooltip"]').tooltip()
	})
</script>

