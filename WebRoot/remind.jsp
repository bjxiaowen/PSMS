<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>对不起，免费试用到期！</title> 
</head> 
<body> 
<div id="errorfrm"> 
<h3>续费提醒!!!!</h3> 
<div id="error"> 

<p>当前版本为测试版，若继续使用请更新为正式版！ 联系电话： 15810581483</p> 

<p>你好每天网络技术部</p>
<p>将在 <span id="mes">N</span> 秒钟后返回首页！</p> 
</div> 

</div> 
<style>
	#errorfrm{width:555px;height:140px;margin:0px auto;}
</style>
 
 

</body></html>