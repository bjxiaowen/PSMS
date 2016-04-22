<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="keywords" content="js代码网">
<meta name="description" content="js代码网">
<title>用户登录</title>
<meta name="keywords" content="网站模板,登录页面模板,登陆页面模板,登录界面模板,登录页面表单验证">
<meta name="description" content="JS代码网提供大量的登录页面模板的学习和下载">
<link rel="shortcut icon" href="resources/images/favicon.ico" />
<link href="resources/style/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/js/jquery.js"></script>
<script type="text/javascript"
	src="resources/js/jquery.i18n.properties-1.0.9.js"></script>
<script type="text/javascript"
	src="resources/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/md5.js"></script>
<script type="text/javascript" src="resources/js/page_login.js?lang=zh"></script>



<!--[if IE]>
  <script src="resources/js/html5.js"></script>
<![endif]-->
<!--[if lte IE 6]>
	<script src="resources/js/DD_belatedPNG_0.0.8a-min.js" language="javascript"></script>
	<script>
	  DD_belatedPNG.fix('div,li,a,h3,span,img,.png_bg,ul,input,dd,dt');
	</script>
<![endif]-->
<script>
	function load() {
		var user_name = $("#l-username").attr("value");
		var password = $("#l-password").attr("value");
		if(user_name==""){alert("请输入用户名");return false;}
		if(password==""){alert("请输入密码");return false;}
		if (checkUsernameAndPassword(user_name, password)) {

			$.ajax({
				url : 'userLogin.action?user_name=' + user_name + '&password='
						+ password,
				type : 'GET',
				dataType : 'json',
				async : false,
				success : function(obj) {
					var result = obj[0];
					if (result == "Role1") {
						location.href = 'superManageDesk.jsp';
					} else if (result == "Role2") {
						location.href = 'superResearchDesk.jsp';
					} else if (result == "Role3") {
						location.href = 'psResearchDesk.jsp';
					} else if (result == "Role4") {
						location.href = 'psResearchDesk.jsp';
					} else if (result == "Role5") {
						location.href = 'psResearchDesk.jsp';
					}else {
						location.href = 'Login.jsp';
					}
				}
			});
		} else {
			location.href = 'Login.jsp';
		}
		
	
	}
	function checkUsernameAndPassword(user_name, password) {

		var result = "";
		$.ajax({
			url : 'checkUsernameAndPassword.action?user_name=' + user_name
					+ '&password=' + password,
			type : 'GET',
			dataType : 'json',
			async : false,
			success : function(obj) {
				result = obj[0];
			}

		});
		if (result == "usernameIsNotExist") {
			alert("用户名不存在!");
			return false;
		} else {
			if (result == "pw_wrong") {
				alert("密码输入错误!请重新输入");
				return false;
			}
		}
		return true;
	}
</script>
</head>
<body class="loginbody">

	<div class="dataEye">
		<div class="loginbox" style="opacity:0.7">

			<div class="login-content">
				<div class="loginbox-title" style="opacity:1">
					<h3>登录</h3>
				</div>
				<form id="signupForm">
					<div class="login-error"></div>
					<div class="row" style="opacity:1">
						<label class="field"></label> <input type="text"
							class="input-text-user input-click" id="l-username">
					</div>
					<div class="row" style="opacity:1">
						<label class="field"></label> <input type="password"
							class="input-text-password input-click" id="l-password" onkeypress="if(event.keyCode==13) {load();return false;}" >
					</div>
					<div class="row btnArea" style="opacity:1">
						<a class="login-btn" onclick="load()" >登录</a>
					</div>
				</form>
			</div>
		</div>

		<div class="footer">
			<p>
				<span class="copyright">版权所有 ZONERGY &copy; 2015</span>
			</p>
		</div>
	</div>
<script type='text/javascript'>
    window.history.forward();
    window.onbeforeunload=function (){ 
}
</script>
</body>
</html>
