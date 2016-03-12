<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>guest.jsp">    
    <title>电站概览</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  	 
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="easyui/demo/demo.css">  
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>    
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script language="javascript" src="http://webapi.amap.com/maps?v=1.3&key=fd261b18592019de013d5e8408a8a093"></script> 
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
				width:30%;float:left;height:100%;line-height:1.7em;font-size: 15px;
			}
			
			#north-right
			{
				width:20%;float:right;height:100%;clear:right;
			}
	</style>
	
	 <script language="javascript"> 	
		$(document).ready(function() {//页面准备方法，更新系统时间
		var time1=current();
		setInterval(function(){$("#time").html(current)},1000); 
	});
		$(function(){
			$('#mobanwang_com li').hover(function(){
				$(this).children('ul').stop(true,true).show('slow');
			},function(){
				$(this).children('ul').stop(true,true).hide('slow');
			});
			
			$('#mobanwang_com li').hover(function(){
				$(this).children('div').stop(true,true).show('slow');
			},function(){
				$(this).children('div').stop(true,true).hide('slow');
			});
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
		function opeanHtml(t){
				$("#content").find("iframe").attr("src",t.name+".action");
		}
		 
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
       			url:'getStationPosition.action',
       			type:'GET',
       			dataType:'json',
       			async:false,
	  			success:function(obj){	  			
	  				for(var i=0;i<obj[0].length;i++){
  						var longitude = obj[0][i].longitude;
  						var latitude = obj[0][i].latitude;						
  						 marker= new AMap.Marker({                
  					        icon:"http://webapi.amap.com/images/marker_sprite.png",
  					        position:new AMap.LngLat(longitude,latitude)
  						 	});
  						 
	  						marker.setMap(mapObj);  //在地图上添加点	
	  						marker.setTitle(obj[0][i].name); //设置鼠标划过点标记显示的文字提示	
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
	 function clickPic(obj)
	 {
		 var name=obj.src;
		
		 window.open('PSPicture.jsp?picName='+name);
		 
	  
	 }
	 function openInfo(lng,lat){
	
			$.ajax({
       			url:'getStationPosition.action',
       			type:'GET',
       			dataType:'json',
       			async:false,
	  			success:function(obj){
	  				 for(var i=0;i<obj[0].length;i++){
	  					//构建信息窗体中显示的内容
	  				    var info = []; 
	  					var pic="";
	  				 	var name = "<div style='padding:0px 0px 0px 4px;'><b>"+"电站名称："+obj[0][i].name+"</b></div>";
	  				 	var capacity = "<div style='padding:0px 0px 0px 4px;'><b>"+"电站容量："+obj[0][i].capacity+"</b></div>";
	  				 	var area = "<div style='padding:0px 0px 0px 4px;'><b>"+"占地面积："+obj[0][i].area+"</b></div>";
	  				 	var part_num = "<div style='padding:0px 0px 0px 4px;'><b>"+"组件数量："+obj[0][i].part_num+"</b></div>";
	  				 	var owner = "<div style='padding:0px 0px 0px 4px;'><b>"+"业主："+obj[0][i].owner+"</b></div>";
	  				 	var investor = "<div style='padding:0px 0px 0px 4px;'><b>"+"投资方："+obj[0][i].investor+"</b></div>";	  				 		  				 	
						var province = "<div style='padding:0px 0px 0px 4px;'><b>"+"所属省份："+obj[0][i].province+"</b></div>";
	  				 	var build_time = "<div style='padding:0px 0px 0px 4px;'><b>"+"建站时间："+obj[0][i].build_time+"</b></div>";	  				 	
						var longitude = "<div style='padding:0px 0px 0px 4px;'><b>"+"经度："+obj[0][i].longitude+"</b></div>";
	  				 	var latitude = "<div style='padding:0px 0px 0px 4px;'><b>"+"纬度："+obj[0][i].latitude+"</b></div>";
	  				 	if(obj[1][i]=="no"){	pic="<div style='padding:0px 0px 0px 4px;'><b> "+"无电站图片"+"</b></div>";}
	  				 	else{  pic="<div ><img style='width:160px;height:90px;' onclick='clickPic(this)' src='"+"upload/"+obj[1][i]+"'></img></div>";}	  				 	
	  				 	var l_lng = obj[0][i].longitude;
	  					var l_lat = obj[0][i].latitude;
	  					if(l_lng==lng&& l_lat==lat){
	  						info.push(name); 
							info.push(capacity );
							info.push(area);
	  						info.push(part_num); 
							info.push(owner);
							info.push(investor);
	  						info.push(province); 
							info.push(build_time);
	  						info.push(longitude); 
							info.push(latitude);
							info.push(pic);
	  					 	infoWindow = new AMap.InfoWindow({  
	  					 		content:info.join("<br/>")  //使用默认信息窗体框样式，显示信息内容
	  					 	});	  					 		
	  					}
	  				}
	  			 infoWindow.open(mapObj,new AMap.LngLat(lng,lat));
	  			}
       		}); 
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
	 </script>  
  </head>
  
	<body class="easyui-layout"  onLoad="mapInit()" style="width:100%;height:100%;">
  
        <div data-options="region:'north'" style="height:13%;background:url('images/nav.png');">
        
        		<div id="north-left">   		
  					<div  style="width:100%;height:30%;"></div>
  					<div style="color:#ffffff;font: bold 16px '宋体','微软雅黑'; font-size:40px;text-align:center;vertical-align:middle;">ZTE中兴能源</div>
  				</div>  						    					                 			
		    	<div id="north-center" >
		    		<div  style="width:100%;height:40%;">	
		    		</div>		    	
  					<div style="width:100%;height:60%;">
  						<div style="color:#ffffff;magrin-left:30px;text-align:right;font-size:15px;" >欢迎您！</div>	
		   				<div id="time" style="color:#ffffff;magrin-left:30px;text-align:right;font-size:15px;" ></div>	 						
  					</div>	
		    	</div>		    	
  				<div id="north-right"> 	
  		            <div  style="width:100%;height:59%;">	
		    		</div>	
		    		<div  style="width:100%;height:40%;">	
		    				<a href="Home.jsp"  title="首页" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;">首页&nbsp|</a>																               	                             					
							<a href="javascript:void(0)" onClick="addHeart('中兴能源','http://121.0.0.1:8080/PSMS/Home.jsp')" style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="收藏本站">收藏本站&nbsp|</a>																                	                                					
							<a href="contact_us.jsp"  style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;"  title="联系我们">联系我们&nbsp|</a>																                	                	    			                  					
							<a href="Login.jsp"  style="font-size: 12px;color:#ffffff;margin-left:5px;text-decoration:none;" title="用户登录">用户登录</a>					
		    		</div>	         					
				</div>	  
        </div>
    
    	<div data-options="region:'west',split:true,align:'center'" style="width:22%;">
    		<div style="width:100%;height:40px;background:#ffffff;font-color:#3366cc;font-size:20px;">电站概况</div>    	
  			<table id="pg" class="easyui-propertygrid" style="width:300px"  data-options="url:'getGuestPageData.action',columns: mycolumns,showGroup:true,scrollbarSize:0" 	>
  			</table>   	
    	</div>
       <div data-options="region:'center'" >
		   <div id="map" style="width:100%;height:99%;border:0px;"></div>
	   </div>
	
        <div data-options="region:'south',split:true"style="height:5%;background:white;line-height:200%;text-align:center;font-size:12px;color:999999;cursor:default;border-top:1px solid white;" >版权所有 ZONERGY &copy; 2015
       </div>  
        <script type="text/javascript">
        window.history.forward(1); 
		
		var mycolumns = [[
    		{field:'name',title:'电站名称',width:100,sortable:true},
   		    {field:'value',title:'内容',width:100,resizable:false}
        ]];
	
		
		</script>     
	</body>
</html>
