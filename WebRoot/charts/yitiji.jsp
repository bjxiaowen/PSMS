<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PowerStationBase" %>
<%@ page import="com.PSMS.pojo.InParameter" %>
<%@ page import="com.PSMS.Hibernate.Inverter_parameter" %>
<%@ page import="java.util.*" %>
<%@ page import="java.math.BigDecimal" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Inverter_parameter parameter=(Inverter_parameter)request.getAttribute("parameter");
	PowerStationBase outData=(PowerStationBase)request.getAttribute("outData");
	
	InParameter inParameter=(InParameter)request.getAttribute("inParameter");

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
    <script>
		  //后台取出数据
		  v = '${list}';
  	</script>
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="charts/js/jtopo-0.4.8-min.js"></script>
    <script type="text/javascript" src="charts/js/yitiji-01.js"></script>
    <script type="text/javascript" src="charts/js/yitiji-02.js"></script>
    <script>
    	var hourlyData = data_json["hourlyData"];
    </script>
  </head>
  
  <body>
    <header class="header navbar navbar-fixed-top" role="banner">
      <jsp:include page="sysHead.html"/>
    </header>
    <div id="container">
     <div id="sidebar" class="sidebar-fixed">
        <jsp:include page="sysSidebar.html" />
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
                    <li class="list-group-item">额定直流电压：<%=parameter.getRated_voltage() %> V</li>
                    <li class="list-group-item">额定输出电压：FF VC</li>
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
                    <li class="list-group-item">组件电压：<%=inParameter.getModelInVoltage() %> V</li>
                    <li class="list-group-item">组件电流：<%=inParameter.getModelInCurrent() %> A</li>
                    <li class="list-group-item">蓄电池电压：<%=inParameter.getBatteryInVoltage() %> V</li>
                    <li class="list-group-item">蓄电池电流：FF A</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u3" style="background: #f1f1f1">
                  <h4><strong>输出参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">功率：<%=outData.getTotalPower() %> KW</li>
                    <li class="list-group-item">电压：<%=outData.getTotalVoltage() %> V</li>
                    <li class="list-group-item">电流：<%=outData.getTotalCurrent() %> A</li>
                    <li class="list-group-item">频率：FF Hz</li>
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
                <div class="widget-content" style="text-align: center;">
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