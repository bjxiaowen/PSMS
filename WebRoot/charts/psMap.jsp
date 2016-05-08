<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PSTotal" %>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	PSTotal psTotal=(PSTotal)request.getAttribute("psTotal");
%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>地图</title>
<jsp:include page="chartsHead.jsp" />
<!-- ECharts单文件引入 -->
<script type="text/javascript" src="charts/js/echarts-require.js"></script>
<script type="text/javascript" src="charts/js/psMap.js"></script>
<script>
	
	var v = '${list}';
	$(document).ready(function() {
		App.init();
	});
	

	//
</script>
<style>
.navbar {
	background: #2677af;
	border-bottom: 2px solid #2a4053;
}

ul.stats li strong, ul.stats li small {
	/* padding: 5px 0; */
	display: inline;
	/* text-align: center; */
}

ul.stats li {
	display: table-cell;
	padding: 10px;
}

ul.stats li strong {
	font-size: 15px;
	font-weight: bold;
}

.page-title h3 {
	font-weight: bold;
}
</style>
</head>

<body>
	<header class="header navbar navbar-fixed-top" role="banner">
	<div class="container">
		<ul class="nav navbar-nav">
			<li class="nav-toggle"><a href="javascript:void(0);" title="">
					<i class="icon-reorder"> </i>
			</a></li>
		</ul>
		<a class="navbar-brand" href="#"> <img
			src="charts/assets/img/logo.png" alt="logo" height="30" width="30" />
			<strong> 中兴能源PSMS </strong>
		</a> 
	</div>
	</header>
	<div id="container">
		<div id="content" style="margin-left: 0px;">
			<div class="container">
				<div class="row">
					<div class="col-md-2" id="tableDiv"></div>
					<div class="col-md-10">
						<div class="widget-content">
							<div id="chart1" class="chart" style="height: 100%;padding:10px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="psNum" name="psNum" value="<%=psTotal.getTotalPS() %>">
	<input type="hidden" id="Capacity" name="Capacity" value="<%=psTotal.getTotalCapacity()%>">
	<input type="hidden" id="HistoryQ" name="HistoryQ" value="<%=psTotal.getTotalHistoryQ() %>">
	<script id="alertTpl" type="text/html">
    				 <table class="table table-hover">
                            <thead>
                              <tr>
                                <th width="70%">
                                  电站
                                </th>
                                <th width="30%">
                                  报警
                                </th>
                              </tr>
                            </thead>
                            <tbody>
                            {{each list as value i}}
                                <tr>
                                  <td>
                                    <div style="height: 30px; line-height: 30px;"><a target="_blank" href="toBiIndex.action?psId={{value.id}}">{{value.name}}</a></div>
                                  </td>
                                  <td>
                                    <div class="au">
                                    <audio class="music" src="charts/assets/img/icons/901095.wav" autoplay="autoplay" loop>报警</audio>
                                      <a class="audio_btn"><img src="charts/assets/img/icons/play.gif" width="30" height="30" class="music_btn" border="0"></a>
                                      </div>
                                  </td>
                                </tr>
                            {{/each}}

                            </tbody>
                          </table>
               
	</script>
</body>

</html>