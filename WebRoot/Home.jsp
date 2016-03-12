<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
	   <title>首页</title>
	   		<base href="<%=basePath%>Home.jsp">
			<meta http-equiv="content-type" content="text/html; charset=utf-8" />
			<meta name="description" content="" />
			<meta name="keywords" content="" />
			<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
			<script src="js/skel.min.js"></script>
			<script src="js/init.js"></script>
			<noscript>
				<link rel="stylesheet" href="css/skel.css" />
				<link rel="stylesheet" href="css/style3.css" />
				<link rel="stylesheet" href="css/style-wide.css" />
				<link rel="stylesheet" href="css/style-noscript.css" />
			</noscript>
			<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
			<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
			 <script language="javascript">
			 function addHeart(title,url){
			 if(confirm("要收藏本页吗？")==true)   
				  try {
				      window.external.addFavorite(url, title);
				  }
					catch (e) {
				     try {
				       window.sidebar.addPanel(title, url, "");
				    }
				     catch (e) {
				         alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
				     }
				  }
			    

			 }
			 </script>
  </head>
  
 <body class="loading">
		<div id="wrapper">
			<div id="bg"></div>
			<div id="overlay"></div>
			<div id="main">
				<!-- Header -->
					<header id="header">
						<h1>ZONERGY中兴能源</h1>
						<p>Energy saving  &nbsp;&bull;&nbsp; Green &nbsp;&bull;&nbsp; Make life better</p>
						<nav>
							<ul>
								<li><a href="guest.jsp" class="icon-eye-open" title="电站总览"><span>Twitter</span></a></li>
								<li><a href="Home.jsp" class="icon-home"><span>Facebook</span></a></li>
								<li><a href="Login.jsp" class="icon-user" title="用户登录"><span>Dribbble</span></a></li>
							</ul>
						</nav>
					</header>

				<!-- Footer -->
					<footer id="footer">
						<span class="copyright">ZONERGY中兴能源 &copy; 2015</span>
					</footer>
				
			</div>
		</div>
	</body>

</html>
