<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>regionList.jsp">
    <title>region</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/datagrid-detailview.js"></script> 
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <style>
    	body{
    		border:0px;padding:5px;
    	}
    </style>
</head>
<body>
    <p style="font-weight:bold;color:#0E2D5F;font: bold 16px '宋体','微软雅黑';font-size:12px">故障管理->逾期运维</p>  
    <table id="dg" title="逾期管理" class="easyui-datagrid" style="width:100%;height:95%;text-align:center"
    		url="getInspectionOverdue.action"
            toolbar="#toolbar" pagination="true" pageSize=20 pageList="[ 20, 30, 40 ]" //读取分页条数，即向后台读取数据时传过去的值
            autoRowHeight="true" striped="true" rownumbers="true" fitColumns="true" singleSelect="true"
            striped="true" sortName="areaId"  sortOrder="asc" remoteSort="flase" >
        <thead>
            <tr>              
            	<th field="inspectionId" hidden="hidden" align="center" width="20" >区域id</th>   
                <th field="areaId" hidden="hidden" align="center" width="20" sortable="true">区域编号</th>
                <th field="areaName" align="center" width="20" sortable="true">区域名称</th>
                <th field="psName" align="center" width="20" sortable="true">电站名称</th>
                <th field="equipmentId" align="center" hidden="hidden" width="20" sortable="true">设备ID</th>
                <th field="userName" align="center" width="20" sortable="true">维护工程师</th>
                <th field="shouldDate" align="center" width="20" sortable="true">应检日期</th>
                <th field="overdueDays" align="center" width="20" sortable="true">逾期（天）</th>
                <th field="tel" align="center" width="20" sortable="true">联系方式</th>
            </tr>
        </thead>
    </table>
    <div id="toolbar">
    </div>
    
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            
            
            width:80px;
        }
        .fitem input{
            width:160px;
        }
    </style>
</body>

</html>
