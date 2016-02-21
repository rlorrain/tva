<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tilesx:useAttribute name="current" />	
<tilesx:useAttribute name="breadcrumb1" />
<tilesx:useAttribute name="breadcrumb2" />
<tilesx:useAttribute name="breadcrumb3" />
<tilesx:useAttribute name="breadcrumb4" />
<tilesx:useAttribute name="breadcrumb5" />

<br>
<br>
<br>

<div>
	<ol class="breadcrumb">
		<c:if test="${breadcrumb1 != 'none'}">
			<li><a href="#">${breadcrumb1}</a></li>
		</c:if>
	  	<c:if test="${breadcrumb2 != 'none'}">
			<li><a href="#">${breadcrumb2}</a></li>
		</c:if>
	  	<c:if test="${breadcrumb3 != 'none'}">
			<li><a href="#">${breadcrumb3}</a></li>
		</c:if>
		<c:if test="${breadcrumb4 != 'none'}">
			<li><a href="#">${breadcrumb4}</a></li>
		</c:if>
		<c:if test="${breadcrumb5 != 'none'}">
			<li><a href="#">${breadcrumb5}</a></li>
		</c:if>
	  	<li class="active">${current}</li>
	</ol>
</div>