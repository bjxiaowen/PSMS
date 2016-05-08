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
    <script type="text/javascript">
    	//后台取出数据
    	v = '${list}';
    </script>
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="charts/js/kongzhiqi-01.js"></script>
    <script>
      $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
      });
    </script>
    <style>
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
          </div>
          <div class="page-header">
          </div>
          <div class="row">
          <div class="col-md-4">
          <div class="widget box" >
                <div class="widget-content u1" style="background: #f1f1f1">
                  <h4><strong  style="">设备基本参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">额定功率：32 KW</li>
                    <li class="list-group-item">额定直流电压：2 V</li>
                    <li class="list-group-item">充电电流：33 A</li>
                    <li class="list-group-item">无</li>
                  </ul>
                </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="widget box">
                <div class="widget-content u2" style="background: #f1f1f1">
                  <h4><strong>输入参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">组件电压：4,344 V</li>
                    <li class="list-group-item">组件电流：2,232 A</li>
                    <li class="list-group-item">蓄电池电压：22 V</li>
                    <li class="list-group-item">蓄电池电流：22 A</li>
                  </ul>
                </div>
            </div>
          </div>
          <div class="col-md-4">
          <div class="widget box">
                <div class="widget-content u3" style="background: #f1f1f1">
                  <h4><strong>输出参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">电压：32 V</li>
                    <li class="list-group-item">电流：2 A</li>
                    <li class="list-group-item">功率：22 KW</li>
                    <li class="list-group-item">无</li>
                  </ul>
                </div>
            </div>
          </div>
          </div>
          <div class="row">
            <div class="col-md-6">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart001" class="chart" style="height: 422px;">
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="widget box">
                <div class="widget-content">
                  <img src="./assets/img/demo/kongzhiqi.gif" alt="" width="900">
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>

</html>