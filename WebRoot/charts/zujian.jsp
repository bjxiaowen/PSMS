<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.PSMS.pojo.PowerStationBase" %>
<%@ page import="com.PSMS.Hibernate.Inverter_parameter" %>
<%@ page import="com.PSMS.pojo.BIPSBaseData" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	Inverter_parameter parameter=(Inverter_parameter)request.getAttribute("parameter");
	BIPSBaseData newes=(BIPSBaseData)request.getAttribute("newes");
	String psName=(String)session.getAttribute("psName");
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
    <title>组件</title>
  <%@ include file="sysJs.jsp"%>
    <!-- 公共文件引入 -->
    <script type="text/javascript">
    	//后台取出数据
    	v = '${list}';
    </script>
    <script type="text/javascript" src="charts/js/chart-pub.js"></script>
    <!-- ECharts单文件引入 -->
    <script type="text/javascript" src="charts/plugins/echart/echarts2.js"></script>
    <script type="text/javascript" src="charts/js/zujian-01.js"></script>
    <script type="text/javascript" src="charts/js/zujian-02.js"></script>
    <script type="text/javascript" src="charts/js/zujian-03.js"></script>
    <script>
    	var current = _.map(data_json["hourlyData"],"current");
    	console.log(current);
    	var vv = _.map(data_json["hourlyData"],"voltage");
    	var power = _.map(data_json["hourlyData"],"power");
    </script>
  </head>
  
  <body>
    <header class="header navbar navbar-fixed-top" role="banner">
     <jsp:include page="sysHead.jsp">
    	<jsp:param value="<%=psName %>" name="psName"/>
    </jsp:include>
    </header>
    <div id="container">
      <jsp:include page="sysSidebar.jsp" />
      <div id="content">
        <div class="container">
          <div class="crumbs">
            <div class="current-time" style="    padding: 10px 15px;font-size: 14px;font-weight: bold;
    float: left;">系统时间：
            </div>
          </div>
          <div class="page-header">
            <div class="page-title" style="padding: 8px 0 0px 0px;">
              <h3 style="margin-bottom: 0px;">
              </h3>
            </div>
          </div>
          <div class="row">
            <div class="col-md-4">
              <div class="widget box" >
                <div class="widget-content u1" style="background: #f1f1f1">
                  <h4><strong  style="">设备基本参数</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">组件额定功率：<%=parameter.getRate_power() %> KW</li>
                    <li class="list-group-item">无</li>
                    <li class="list-group-item">无</li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="widget box">
                <div class="widget-content u2" style="background: #f1f1f1">
                  <h4><strong>输出</strong></h4>
                  <ul class="list-group ">
                    <li class="list-group-item">组件功率：<%=newes.getX_TPV_Power() %> KW</li>
                    <li class="list-group-item">组件电压：：<%=newes.getX_TPV_Voltage() %> V</li>
                    <li class="list-group-item">组件电流：<%=newes.getX_TPV_Current() %> A</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-12">
              <div class="widget box">
                <div class="widget-content">
                  <div id="chart_filled_blue" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
<div class="row">
            <div class="col-md-12">
              <div class="widget box widget-closed">
                <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    组件电压(
                    <span class="blue">
                     <%=newes.getX_TPV_Voltage() %>V
                    </span>
                    )
                  </h4>
                  <div class="toolbar no-padding">
                    <div class="btn-group">
                      <span class="btn btn-xs widget-collapse widget-collapse-2">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="widget-content">
                  <div id="chart_002" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>
<div class="row">
            <div class="col-md-12">
              <div class="widget box widget-closed">
                <div class="widget-header">
                  <h4>
                    <i class="icon-reorder">
                    </i>
                    组件电流(
                    <span class="blue">
                     <%=newes.getX_TPV_Current() %>A
                    </span>
                    )
                  </h4>
                  <div class="toolbar no-padding">
                    <div class="btn-group">
                      <span class="btn btn-xs widget-collapse widget-collapse-3">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                    </div>
                  </div>
                </div>
                <div class="widget-content">
                  <div id="chart_003" class="chart">
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>

  </body>

</html>