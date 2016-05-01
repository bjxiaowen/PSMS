<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">  
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>首页</title>
  <%@ include file="sysJs.jsp"%>
  <!-- 公共文件引入 -->
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="charts/js/total-01.js"></script>
    <script type="text/javascript" src="charts/js/total-02.js"></script>
    <script type="text/javascript" src="charts/js/total-03.js"></script>
    <script type="text/javascript" src="charts/js/total-04.js"></script>
    <script type="text/javascript">
    	//后台取出数据
    	var v = '${list}';
    </script>
    
    <style>
      .navbar{
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
    </style>
  </head>
  <body>
  	<header class="header navbar navbar-fixed-top" role="banner">
    
    <jsp:include page="sysHead.html"/>
    </header>
    <div id="container">
    <jsp:include page="sysSidebar.html" />
       <div id="content">
        <div class="container">
          <div class="crumbs">
            <div class="current-time" style="    padding: 10px 15px;font-size: 14px;font-weight: bold;
    float: left;">采集时刻：
            </div>
            <!-- <ul id="breadcrumbs" class="breadcrumb">
              <li>
                <i class="icon-home">
                </i>
                <a href="index.html">
                  控制台
                </a>
              </li>
              <li class="current">
                <a href="pages_calendar.html" title="">
                  日历
                </a>
              </li>
            </ul> -->
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div class="widget box">
              <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    当天日发电量:
                    <span class="blue">
                      53.9KWh
                    </span>
                  </h4>
                </div>
                <div class="widget-content">
                  <div id="chart_001" class="chart">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="widget box" style="">
              <div class="widget-header">
                  <h4>仪表盘</h4>
                </div>
                <div class="widget-content">
                  <div id="chart_002" class="chart"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div class="widget box">
              <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    本月累计发电量:
                    <span class="blue">
                      101KWh
                    </span>
                  </h4>
                </div>
                <div class="widget-content">
                  <div id="chart_003" class="chart">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="widget box" style="border: 1px solid #2b5797;">
              <div class="widget-header">
                  <h4>历史发电量</h4>
                </div>
                <div class="widget-content" style="height: 270px;background: #2b5797;">
                <h2 style="
                font-size: 120px;
                text-align: center;
                color: white;
                line-height: 200px;
                text-shadow:3px 3px 3px black;">23.21<span style="font-size: 62px;">MWh</span></h2>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div class="widget box">
              <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    本年累计发电量:
                    <span class="blue">
                      480KWh
                    </span>
                  </h4>
                </div>
                <div class="widget-content">
                  <div id="chart_004" class="chart">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="widget box" style="border: 1px solid #00a300;">
                <div class="widget-header">
                  <h4>减排二氧化碳</h4>
                </div>
                <div class="widget-content" style="height: 270px;background: #00a300;">
                <h2 style="
                font-size: 120px;
                text-align: center;
                color: white;
                line-height: 200px;
                text-shadow:3px 3px 3px black;">17.198<span style="font-size: 62px;">ton</span></h2>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>

</html>