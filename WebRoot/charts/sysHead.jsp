<%@ page contentType="text/html; charset=utf-8"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.net.*" %>
<%
	String psName=request.getParameter("psName");
%>
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