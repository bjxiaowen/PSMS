<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PowerStationBase" %>
<%@ page import="com.PSMS.pojo.BIPSBaseData" %>
<%@ page import="com.PSMS.Hibernate.Inverter_parameter" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Inverter_parameter parameter=(Inverter_parameter)request.getAttribute("parameter");
	
	BIPSBaseData newes=(BIPSBaseData)request.getAttribute("newes");
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
    <title>逆变器</title>
  <%@ include file="sysJs.jsp"%>
  <!-- 公共文件引入 -->
    <script type="text/javascript">
    	//后台取出数据
    	v = '${list}';
    </script>
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="charts/js/jtopo-0.4.8-min.js"></script>
    <script type="text/javascript" src="charts/js/nibianqi-01.js"></script>
    <script type="text/javascript" src="charts/js/nibianqi-02.js"></script>
    <script>
      $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
      });
      var hourlyData = data_json["hourlyData"];
      var kw = _.map(hourlyData,"power");
    </script>
    <style>

    </style>
  </head>
  
  <body>
    <header class="header navbar navbar-fixed-top" role="banner">
      <jsp:include page="sysHead.jsp"/>
    </header>
    <div id="container">
      <div id="sidebar" class="sidebar-fixed">
        <jsp:include page="sysSidebar.jsp" />
        <div id="divider" class="resizeable">
        </div>
      </div>
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
                    <li class="list-group-item">额定功率：<%=parameter.getRate_power() %> KW</li>
                    <li class="list-group-item">额定输入电压：<%=parameter.getRated_voltage() %> V</li>
                    <li class="list-group-item">额定输出电压：FF V</li>
                    <li class="list-group-item">额定输出频率：FF Hz</li>
                  </ul>
                </div>
            </div>
          </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u2" style="background: #f1f1f1">
                  <h4><strong>输入参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">直流功率：<%=newes.getX_Coutpout_Voltage() %> KW</li>
                    <li class="list-group-item">直流电压：<%=newes.getX_Coutpout_Current() %> V</li>
                    <li class="list-group-item">直流电流：<%=newes.getX_Coutpout_Power() %> A</li>
                    <li class="list-group-item">无</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u3" style="background: #f1f1f1">
                  <h4><strong>输出参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">功率：<%=newes.getExchangeOutPower() %> KW</li>
                    <li class="list-group-item">电压：<%=newes.getOutputVoltage() %> V</li>
                    <li class="list-group-item">电流：<%=newes.getOutputCurrent()%> A</li>
                    <li class="list-group-item">频率：<%=newes.getX_AC_Frequency() %> Hz</li>
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
                <div class="widget-content topo_bg" style="text-align: center;background-color: #25a;">
                  <canvas width="850" height="550" id="canvas"></canvas>	
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>

</html>