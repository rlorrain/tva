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

	<table class="table">
		<thead>
			<tr>
				<th>Planned Tournaments</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tournaments}" var="tournament">
					<tr class="tournamentRow">
						<td>
							<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  								<div class="panel panel-default">
    								<div class="panel-heading row" role="tab" id="headingOne">
    									<div class="custom-col-1">
    										<a class="btn btn-primary btn-xs" role="button" data-toggle="collapse" data-parent="#accordion" href="#${tournament.name}" aria-expanded="true" aria-controls="${tournament.name}">
          									<span class="glyphicon glyphicon-info-sign"></span> Info
        									</a>
    									</div>
    									<div class="custom-col-2">
    										<h4 class="panel-title">
        										<c:out value="${tournament.name}" />	
      										</h4>
    									</div>
    									<div class="custom-col-3">
    										<a href="#" class="">inschrijven</a>
    									</div>
    								</div>
    								<div id="${tournament.name}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      									<div class="panel-body">
        								Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      									</div>
    								</div>
  								</div>
  							</div>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>

</div><!-- /container -->


