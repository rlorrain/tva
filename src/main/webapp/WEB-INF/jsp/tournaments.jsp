<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/general/taglib.jsp" %>

<div class="container">

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>Planned tournaments</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${tournaments}" var="tournament">
					<tr>
						<td>
							<div>
								<div>
									<a class="btn btn-primary" role="button" data-toggle="collapse" 
									href="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><c:out value="${tournament.name}" />
  									</a>
								</div>
								<div>
									Startdate: <c:out value="${tournament.startDate}" /> Enddate: <c:out value="${tournament.endDate}" />
								</div>
  							</div>	
  							<div class="collapse" id="collapseExample">
  								<div class="well">
  									<a href='<spring:url value="/tournaments/${tournament.id}.html" />'>
										<c:out value="${tournament.name}" />
									</a>
  								</div>
							</div>
						</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>

</div><!-- /container -->


