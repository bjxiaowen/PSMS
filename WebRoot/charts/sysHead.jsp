<%@ page contentType="text/html; charset=utf-8"%>
<%
	String psName=request.getParameter("psName");
	
%>
<div class="container">
	<ul class="nav navbar-nav">
		<li class="nav-toggle"><a href="javascript:void(0);" title="">
				<i class="icon-reorder"> </i>
		</a></li>
	</ul>
	<a class="navbar-brand" href="index.html"> <img
		src="charts/assets/img/logo.png" alt="logo" height="30" width="30" /> <strong>
			中兴能源 PSMS</strong>
	</a> 
	<span href="#" class="toggle-sidebar bs-tooltip" data-placement="bottom" data-original-title="Toggle navigation" style="margin-left:100px;">
		 <%=psName %>
	</span>
</div>