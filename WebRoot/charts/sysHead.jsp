<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.net.*" %>
<%
	String psName=(String)session.getAttribute("psName");
	if(psName!=null){
		psName =URLDecoder.decode(psName, "UTF-8");
	}else{
		psName="";
	}
	
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="container">
	<ul class="nav navbar-nav">
		<li class="nav-toggle"><a href="javascript:void(0);" title="">
				<i class="icon-reorder"> </i>
		</a></li>
	</ul>
	<a class="navbar-brand" href="#"> <img
		src="charts/assets/img/logo.png" alt="logo" height="30" width="30" /> <strong>
			中兴能源 PSMS</strong>
	</a> 
	<div class="navbar navbar-brand asdf" style="border:0;">
		 <%=psName %>
	</div>
	<input type="hidden" id="psName" value="<%=psName %>">
</div>
<script>
	var psName = $("#psName").val();
</script>