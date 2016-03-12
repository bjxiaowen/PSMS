<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>desktop3.jsp">

		<title>电站管理人员</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="shortcut icon" href="./favicon.ico" />

		<link href="css/bootstrap.css" rel="stylesheet" media="screen" />
		<link href="css/style-desktop1.css" rel="stylesheet" media="screen" />
		<link href="css/bootstrap-responsive.css" rel="stylesheet"
			media="screen" />
		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
		<style>
@font-face {
	font-family: 'JournalRegular';
	src: url('fonts/journal-webfont.eot');
	src: url('fonts/journal-webfont_162a16fe.eot')
		format('embedded-opentype'), url('fonts/journal-webfont.woff')
		format('woff'), url('fonts/journal-webfont.ttf') format('truetype'),
		url('fonts/journal-webfont.svg') format('svg');
	font-weight: normal;
	font-style: normal;
}

body {
	background: #3366cc;
}
</style>
	</head>


	<body class="page-bg">
		<%
			String username = session.getAttribute("User_name").toString();
		%>


		<div class="container" style="width: 100%; border: 0px;">
			<div class="social-links">
				<div class="span9">

					<ul id="social-icons">
						<li class="facebook">
							<a href="accountSetting.jsp" data-toggle="tooltip" title="个人信息修改"></a>
						</li>
						<li class="flickr">
							<a onclick="routeToMonitor()" data-toggle="tooltip"
								title="监测系统平台"></a>
						</li>
						<li class="pinterest">
							<a href="psData.jsp" data-toggle="tooltip" title="历史数据"></a>
						</li>
						<li class="dribble">
							<a href="" id="manage" onclick="getAuthority()"
								data-toggle="tooltip" title="系统管理"></a>
						</li>

					</ul>
				</div>
			</div>

		</div>
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap-transition.js"></script>
		<script src="js/bootstrap-modal.js"></script>
		<script src="js/bootstrap-tooltip.js"></script>
		<script type="text/javascript" src="js/jquery.custom.js"></script>
		<script type=text/javascript>	 
	 	var username ='<%=username%>';
	 
	   	function getAuthority(){
	   		$.ajax({
				url : 'getAuthority.action?username=' + username,
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
				
				if(obj[0][0].user_manage==0 && obj[0][0].station_manage==0 && obj[0][0].device_manage==0)
					{
					alert("没有开通任何系统管理权限");
					}
				else {document.getElementById("manage").href="page2.jsp";

					}
				}

			});
	   	}	
	  
		 function routeToMonitor(){			
			 var ps_id;
		   	 $.ajax({
					url : 'getPSIDByUserName.action?username=' +encodeURI(encodeURI(username)),
					type : 'GET',
					dataType : 'json',
					async : false,
					success : function(obj) {						
							ps_id=obj[0];
					}
				});
		   
	           window.location.href="/PSMS/Ext/project/RealTimeMonitor/iframeNavi.html?user_name="+username+"&ps_id="+ps_id+"&page_id=1"; 
	    }
	</script>
		<script language="javascript" type="text/javascript">

    </script>
	</body>
</html>
