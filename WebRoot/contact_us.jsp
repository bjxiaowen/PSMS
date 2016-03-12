<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Contact_us.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="css/style-Contact.css">
	<script type="text/javascript" src="js/cufon-yui.js"></script>
	<script type="text/javascript" src="js/arial.js"></script>
	<script type="text/javascript" src="js/cuf_run.js"></script>

  </head>
  
  <body>
    <div class="main">
  	<div class="header">
    <div class="header_resize">
	      <div class="menu_nav">
		      <ul>
		          <li><a href="Home.jsp">首页</a></li>
		          <li><a href="Login.jsp">登陆</a></li>
		          <li class="active"><a href="contact_us.jsp">联系我们</a></li>
		      </ul>     
	      </div>
	      <div class="clr"></div>
	      <div class="logo">
	        <h1><a href="Home.jsp">版权所有 ZONERGY &copy; 2015<span>Energy</span></a></h1><small>Make life better</small>
	      </div>
	      <img src="images/header_img.png" alt="image" width="431" height="220" />
	      <div class="clr"></div>
	      <div class="clr"></div>
	      <div class="header_img"></div>
	    </div>
  </div>
  <div class="clr"></div>
  <div class="content">
	    <div class="content_resize">
		      <div class="mainbar">
			        <div class="article">
			          <p style="font-size:180%;font-family:verdana;font-weight: bold">中兴能源有限公司简介</p>
			          <div class="clr"></div>
			          <p><strong>中兴能源有限公司（简称&ldquo;中兴能源&rdquo;），是国家级高新技术企业。专注于新能源及节能环保领域的资源集成服务。</strong></p>
			          <p>公司注册资本12.9亿元人民币。主要经营范围涵盖云计算全产业链、太阳能光伏技术研发及工程承包、生物质能源研究开发、节能技术研发及相关综合性服务、
			         	 棕榈种植及油脂加工等贸易等相关领域。公司主要股东中兴通讯股份有限公司于1985年成立，拥有近二十年的全球化运营经验，是中国上市公司中最大的通讯设备销售商。
			         	中兴能源自2007年成立以来，一直坚持以&ldquo;可持续发展&rdquo;为目标，不断吸纳和整合相关产业的要素资源。通过全球化渠道、科技化手段、集约化资本运作及管理，
			         	为推动全球重点城市中的重大新能源及节能环保项目形成规模化发展，提供具有国际一流竞争力的资源集成服务。 
			         	</p> 
			        </div>
			        <div class="article">
			           <p style="font-size:180%;font-family:verdana;font-weight: bold">产业与服务</p><div class="clr"></div>
			          <p><strong>太阳能产业、生物能源产业、节能产业、棕榈产业、海外农业</strong></p>
			          <p>在太阳能领域，中兴能源致力于发展成为“服务全球的太阳能EPC服务商”。并力争在五年内建设拥有自主知识产权的新一代技术薄膜生产线，实现中兴能源对光伏产业的全产业链布局</p>
			          <p>在生物能源领域，力争在5年内建设起我国北方地区最大的能源作物种植和生物燃料生产研发基地，逐步打造中国最大的非粮生物能源全产业链。</p>
			          <p>在节能减排领域，中兴能源致力于发展成为国内具有绝对领先优势的“节能减排投资机构及集成服务商”。中兴能源拥有技术、资金、管理、国际营运等综合实力和竞争条件，能帮助用能单位有效降低节能改造的资金和技术风险，为节能减排工作不断注入创新活力。</p>
			          <p>棕榈项目是中兴能源的重点拓展业务之一。依托潜力巨大的中国棕榈油市场，凭借雄厚的资金优势和技术应用优势，公司在东南亚和非洲大力开发棕榈产业，进行棕榈种植和棕榈油加工。</p>
			          <p>中兴能源积极响应并参与国家战略部署，致力于进行海外大规模农业综合开发，在非洲刚果（金）和苏丹等地开展多项大型综合性农业项目，在中非交流中起到了积极的推动作用和示范作用。</p>
			        </div>
		      </div>
	      <div class="sidebar">
	           <div class="gadget">
	           		<p style="font-size:150%;font-family:verdana;font-weight: bold">联系我们</p>
	           <div class="clr"></div>
	             <p><strong>中兴能源有限公司</strong></p>
	             <p><strong>Zonergy Company Limited</strong></p>
		          <ul class="sb_menu">
		            <li>地址：北京市朝阳区裕民路12号元辰鑫大厦E3座</li>
		            <li>电话：+86 10 82066300</li>
		            <li>传真：+86 10 82066399</li>
		            <li>邮箱：zte-e@zte.com.cn</li>
		          </ul>
	          </div>
	      </div>
	      <div class="clr"></div>
	    </div>
  </div>
  <div class="fbg">
    <div class="fbg_resize">
      <div  style="margin:0 0 50px 0; pading:0 16px 0 0">
       <p style="font-size:150%;font-family:verdana">图片展区</p>
        <a href="#"><img src="images/gallery_1.jpg" width="100" height="100" alt="pix" /></a>
        <a href="#"><img src="images/gallery_2.jpg" width="100" height="100" alt="pix" /></a> 
        <a href="#"><img src="images/gallery_3.jpg" width="100" height="100" alt="pix" /></a> 
        <a href="#"><img src="images/gallery_4.jpg" width="100" height="100" alt="pix" /></a> 
        <a href="#"><img src="images/gallery_5.jpg" width="100" height="100" alt="pix" /></a> 
        <a href="#"><img src="images/gallery_6.jpg" width="100" height="100" alt="pix" /></a> </div>
      
      <div class="clr"></div>
    </div>
    <div class="footer">
      <p class="lr"></p>
    </div> 
    </div> 
  </body>
</html>
