<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <title>电站设备</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" href="easyui/demo.css">
    
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    
    <style>
    	body{
    		border:0px;
    		margin: 0;padding: 0;font-family: Arial, Verdana, Helvetica, sans-serif;font-family:"雅黑宋体", "微软雅黑", "新宋体", "宋体";
    	}
    	    
			#north-left
			{
			 width:45%;float:left;height:100%;border:0px;		
			}
			
			#north-center
			{
				width:32%;float:left;height:100%;line-height:1.7em;font-size: 15px;
			}
			
			#north-right
			{
				width:23%;float:right;height:100%;clear:right;
			}
			
    </style>
   
    <script type=text/javascript>
     var username = "<%=session.getAttribute("User_name")%>";
   
	$(document).ready(function() {//页面准备方法，更新系统时间	
		var time1=current();
		setInterval(function(){$("#time").html(current)},1000);  
		var Request = new Object();
		Request = GetRequest();
		var psId;
		psId = Request['ps_id'];
		
		//$("#sdfsd").value(psId);
		if(username=="null"){
			window.location.href="<%=basePath%>"+"Login.jsp";
		}
		$("#dg").datagrid({  
             url:'getStationDeviceList.action?ps_id='+psId});
		$.ajax({
		       url:'getPSNameByPSId.action?ps_id='+psId,	  	       
				type:'GET',
				dataType:'json',
				async:false,
		  	    success:function(obj){
		  	    	$('#container').panel({ 
		  				title: obj[0] 
		  			});		       				
			  	   }
			    });
    	
             
	});
	
	function GetRequest() {
   		var url = location.search; //获取url中"?"符后的字串
   		var theRequest = new Object();
   		if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      		}
   		}
   		return theRequest;
	}
	
	function onLoadSuccess(data){
		var Request = new Object();
		Request = GetRequest();
		var psId;
		psId = Request['ps_id'];
		var a;
		$.ajax({
		       url:'getPeriodIndexByPsId.action?ps_id='+psId,	  	       
				type:'GET',
				dataType:'json',
				async:false,
		  	    success:function(obj){		  	    
		  	    	for(var i=0; i<obj[0].length; i++)		  	    		
						$("#dg").datagrid('mergeCells',{
							index: obj[0][i],
							field: 'period',
							rowspan: obj[1][i]
						});									       				
			  	}		
			 });					
		}

		function current()//获取系统时间的方法
		{ 
			var d=new Date(),str='今天是  '; 
			str +=d.getFullYear()+'-'; //获取当前年份 
			str +=d.getMonth()+1+'-'; //获取当前月份（0——11）
			str +=d.getDate()+'日 '; 
			str +=d.getHours()+':'; 
			str +=d.getMinutes()+':'; 
			str +=d.getSeconds()+'';
			return str;
		} 
		function opeanHtml(t){
			$("#content").find("iframe").attr("src",t.name+".action");
		}
		 function addHeart(title,url){
			 if(confirm("要收藏本页吗？")==true)   
				  try {
				      window.external.addFavorite(url, title);
				  }
					catch (e) {
				     try {
				       window.sidebar.addPanel(title, url, "");
				    }
				     catch (e) {
				         alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
				     }
				  }	
			}  
		
	        function  go(val,row){
	        	return '<a href="javascript:void(0)" style="text-decoration:none;" onclick="constructionManager(\'' + row.period_id+ '\')">查看电站实时数据</a>'
	        }
	        function constructionManager(id){
	        	var Request = new Object();
	    		Request = GetRequest();
	    		var psId;
	    		psId = Request['ps_id'];
	    		 window.location.href="stationRealTimeData.jsp?period_id="+id+"&ps_id="+psId;
	        	
	        }		
</script>
</head>
	
<body>    
    <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'"  style="height:13%;background:url('images/nav.png');">					    					                 			
		    	<div id="north-left">
		    	<div  style="width:100%;height:30%;">	
		    		</div><div style="color:#ffffff;font: bold 16px '宋体','微软雅黑'; font-size:40px;text-align:center;vertical-align:middle;">电站设备列表   		
  				</div></div>
  				<div id="north-center" >
		    		<div  style="width:100%;height:40%;">	
		    		</div>		    	
  					<div style="width:100%;height:60%;">
  						<div style="color:#ffffff;font: bold 16px '宋体','微软雅黑';magrin-left:30px;text-align:right;" >欢迎您！${User_name}</div>	
		   				<div id="time" style="color:#ffffff;font: bold 16px '宋体','微软雅黑';magrin-left:30px;text-align:right;" ></div>	 						
  					</div>	
		    	</div>		    	
  				<div id="north-right"> 	
  		            <div  style="width:100%;height:59%;">	
		    		</div>	
		    		<div  style="width:100%;height:40%;">	
		    				<a href="Home.jsp"  title="首页" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;">&nbsp &nbsp首页&nbsp|</a>																               	                             					
							<a href="javascript:void(0)" onclick="toDesktop()" title="返回桌面" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;">返回桌面&nbsp|</a>
							<a href="javascript:void(0)"  onclick="addHeart('中兴能源首页','http://121.0.0.1:8080/PSMS/Home.jsp')" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="收藏本站">收藏本站&nbsp|</a>																	                	                                					
							<a href="contact_us.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;"  title="联系我们">联系我们&nbsp|</a>																                	                	    			                  					
							<a href="Login.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="用户登录">用户登出</a>															
		    		</div>	         																																		                				
  				</div>	  
        </div>       
        <div id="content" data-options="region:'center'">
					
					<div class="easyui-panel" id = "container"  style="width:100%;height:97%;padding:0px;">
					<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west'" style="width:100px;padding:10px">					
					</div>
					<div data-options="region:'east'" style="width:100px;padding:10px">						
					</div>
					<div data-options="region:'center'" style="padding:10px">
						<table class="easyui-datagrid" id="dg" title="设备列表" 
						style="width:100%;height:95%;text-align:center"
						
						data-options="
						rownumbers: true,
						singleSelect: true,
						method: 'get',
						onLoadSuccess: onLoadSuccess">
							<thead>
								<tr>
									<th data-options="field:'period',width:200,align:'center'">电站期数</th>
									<th data-options="field:'inverter_name',width:150,align:'center'">逆变器</th>
									<th data-options="field:'pm_name',width:180,align:'center'">电表</th>
									<th data-options="field:'ws_name',width:180,align:'center'">气象站</th>
									<th data-options="field:'jb_name',width:180,align:'center'">汇流箱</th>
									<th data-options="field:'aa',width:185,formatter:go,align:'center'">操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
        </div>
         <div data-options="region:'south'" style="height:5%;text-align:center;padding:3px;font-size:12px;color:#999999;">版权所有 ZONERGY &copy; 2015</div>
    </div>
 
<script type=text/javascript>
	 function toDesktop(){
       			$.ajax({
       				 url:'toDesktop.action?uName='+username,
       				 type:'GET',
             		 dataType:'json',
             		 async:false,
             		 success:function(obj){
	           			var result = obj[0];
	           			if(result=="Role1"){
	           				location.href = 'superManageDesk.jsp';
	           			}
	           			else if(result=="Role2"){
	           				location.href = 'superResearchDesk.jsp';
	           			}
	           			else if(result=="Role3"){
	           				location.href = 'psResearchDesk.jsp';
	           			}
	           			else 
	           				location.href = 'Login.jsp';
           			}          
    			});	
         } 	 
</script>
</body>
</html>
