<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>allStationMonitor.jsp">   
    <title>电站预警地图页面</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    	
	<link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/font-awesome-ie7.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/font-awesome-ie7.min.css">  
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">   
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script language="javascript"
	 src="http://webapi.amap.com/maps?v=1.3&key=fd261b18592019de013d5e8408a8a093"></script> 
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
	<script>
	 var username = "<%=session.getAttribute("User_name")%>";
	$(document).ready(function() {//页面准备方法，更新系统时间
			if(username=="null"){
				window.location.href="<%=basePath%>"+"Login.jsp";
			}
			var time1=current();
			setInterval(function(){$("#time").html(current)},1000); 
		});
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
	           			else
	           				location.href = 'Login.jsp';
           			}          
    			});	
         } 	 
	</script>
	
	   </head>
  
	<body class="easyui-layout"  onLoad="mapInit()" style="width:100%;height:100%;">
	  
        <div data-options="region:'north'" style="height:13%;background:url('images/nav.png') ;">
        
        		<div id="north-left">   		
  					<div style="width:100%;height:30%;"></div>
  					<div style="color:#ffffff;font:bold 16px '宋体','微软雅黑'; font-size:40px;text-align:center;vertical-align:middle;">监  测 平  台</div>
  				</div>  						    					                 			
		    	<div id="north-center" >
		    		<div style="width:100%;height:40%;"></div>		    	
  					<div style="width:100%;height:60%;">
  						<div style="color:#ffffff;magrin-left:30px;text-align:right;font-size:15px;" >欢迎您！${User_name}</div>	
		   				<div id="time" style="color:#ffffff;magrin-left:30px;text-align:right;font-size:15px;" ></div>	 						
  					</div>	
		    	</div>		    	
  				<div id="north-right"> 	
  		            <div  style="width:100%;height:59%;"></div>	
		    		<div  style="width:100%;height:40%;">	
		    				<a href="Home.jsp"  title="首页" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;">&nbsp &nbsp首页&nbsp|</a>																               	                             					
							<a href="javascript:void(0)" onclick="toDesktop()" title="返回桌面" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;">返回桌面&nbsp|</a>
							<a href="guest.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="电站概览">电站概览&nbsp|</a>																	                	                                					
							<a href="contact_us.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;"  title="联系我们">联系我们&nbsp|</a>																                	                	    			                  					
							<a href="Login.jsp" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="用户登录">用户登出</a>
					</div>	         						                				
  				</div>	  
        </div>
    
    	<div data-options="region:'west',split:true,align:'center'" style="width:22%;">
    		<div style="width:100%;height:40px;background:#ffffff;font-color:#3366cc;font-size:20px;">电站信息</div>    	
  			<table id="pg" class="easyui-propertygrid" style="width:300px"  data-options="url:'getWholeData.action',showGroup:true,scrollbarSize:0,showHeader:false" 	>
  			</table>   	
    	</div>
       <div data-options="region:'center'" >
		   <div id="map" style="width:100%;height:99%;border:0px;"></div>
	   </div>
	
       <div data-options="region:'south',split:true"style="height:5%;background:white;line-height:200%;text-align:center;font-size:12px;color:#999999;cursor:default;border-top:1px solid white;" >版权所有 ZONERGY &copy; 2015
       </div> 
       	<script src="js/jquery.js"></script>
		<script src="js/bootstrap-transition.js"></script>
		<script src="js/bootstrap-modal.js"></script>
		<script src="js/bootstrap-tooltip.js"></script>
		<script type="text/javascript" src="js/jquery.custom.js"></script>
	<script language="javascript">
	 
		 
		var mapObj;
		//初始化地图对象，加载地图
		function mapInit() {
		  mapObj = new AMap.Map("map",{
		      rotateEnable:true,
		      dragEnable:true,
		      zoomEnable:true,
		      zooms:[3,18],
		      //二维地图显示视口
		      view: new AMap.View2D({
		          center:new AMap.LngLat(116.397428,39.90923),//地图中心点
		          zoom:4 //地图显示的缩放级别
		      })
		  });  
		  addMarker();	  
		}
	 function addMarker(){
			 $.ajax({
		 		url:'getAllStationMonitor.action',
       			type:'GET',
       			dataType:'json',
       			async:false,
	  			success:function(obj){	  			
	  				for(var i=0;i<obj[1].length;i++){
	  				
  						var longitude = obj[1][i].longitude;
  						var latitude = obj[1][i].latitude;	
  						var id=obj[1][i].id;
  						 marker= new AMap.Marker({                
  					        icon:"http://webapi.amap.com/images/marker_sprite.png",
  					        position:new AMap.LngLat(longitude,latitude)
  						 	});
  						 
	  						marker.setMap(mapObj);  //在地图上添加点	
	  						marker.setTitle(obj[1][i].name); //设置鼠标划过点标记显示的文字提示	
	  						AMap.event.addListener(marker,"click",function eventHandler()
	  						{
	  							var lng= this.getPosition().getLng();
						 		var lat= this.getPosition().getLat(); 
	  							openInfo(lng,lat);
	  						});
	  						mapObj.setFitView(); //调整到合理视野						
	  				}
	  				
	  			}
       		}); 
	}
	 function openInfo(lng,lat){
			
			$.ajax({
       			url:'getAllStationMonitor.action',
       			type:'GET',
       			dataType:'json',
       			async:false,
	  			success:function(obj){
	  			 for(var i=0;i<obj[0].length;i++){
	  					//构建信息窗体中显示的内容
	  					
	  				    var info = []; 
	  					var ps_id=obj[1][i].id;
	  					var str_IP = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].inverterPower+"</b></div>";
	  				 	var str_IV = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].irradiationValue+"</b></div>";	 
	  				 	var str_temp = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].temperature+"</b></div>";	 
	  				 	var str_accPower = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].accPower+"</b></div>";
	  				 	var onlineVoltage = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].activePower+"</b></div>"; 
	  				 	var state = "<div style='padding:0px 0px 0px 4px;'><b>"+obj[0][i].state+"</b></div>";  				 	
	  				 	var l_lng = obj[1][i].longitude;
	  					var l_lat = obj[1][i].latitude;
	  					var str_a="<div><a href='javascript:void(0)' onclick='routeToMonitor("+ps_id+")'>实时监测</a></div>";
	  					if(l_lng==lng&& l_lat==lat){
	  						info.push(str_IP); 
	  					 	info.push(str_IV); 
	  					 	info.push(str_temp);
	  					 	info.push(str_accPower);
	  					 	info.push(onlineVoltage);
	  					 	info.push(state);
	  					 	info.push(str_a);
	  					 	infoWindow = new AMap.InfoWindow({  
	  					 		content:info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
	  					 	});	
	  					 		
	  					}
	  				}
	  				
	  			 infoWindow.open(mapObj,new AMap.LngLat(lng,lat));
	  			}
       		}); 
		}

		 
		
		 function routeToMonitor(id){			
			 var ps_id=id;
	         //window.location.href="/PSMS/Ext/project/RealTimeMonitor/DeviceList.html?user_name="+username+"&ps_id="+ps_id+"&page_id=3"; 
	         window.location.href="stationDeviceList.jsp?user_name="+username+"&ps_id="+ps_id; 
		 }
		 function addHeart(title,url){
			 if(confirm("要收藏本站吗？")==true)   
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
	</script>
	</body>
</html>
