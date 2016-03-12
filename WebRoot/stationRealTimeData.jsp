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
    <title>实时数据</title>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css" /> 
    <link rel="stylesheet" type="text/css" href="easyui/demo.css">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script> 
    <script src="HighCharts/highcharts.js"></script>
	<script src="HighCharts/modules/exporting.js"></script> 
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
   	var i;
	$(document).ready(function() {//页面准备方法，更新系统时间	
		var time1=current();
		setInterval(function(){$("#time").html(current)},1000);  
		var Request = new Object();
		Request = GetRequest();
		var psId = Request['ps_id'];
		var period = Request['period_id'];
		if(username=="null"){
			window.location.href="<%=basePath%>"+"Login.jsp";
		}
		
		$('#pg').propertygrid({//获取实时气象站数据
			url:'getWSRealTimeData.action?ps_id='+psId+'&period='+period
		});
	
		$.ajax({//获取电站名称并显示
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
			 
		$.ajax({//获取该电站所属逆变器列表，获取实时数据
			url:'getInverterNamesByPSId.action?ps_id='+psId+'&period='+period,	  	       
				type:'GET',
				dataType:'json',
				async:false,
		  	    success:function(obj){
		  	    	$('#inverter_name').combobox('clear'); 
		  	    	if(obj[0].length>0){
			  	    	$('#inverter_name').combobox('setValue',obj[0][0]); 	
				  	  	var data = [];
					    for(var i=0;i<obj[0].length;i++){
			  				var inverter_name = obj[0][i];		  						
							data.push({ "text":inverter_name, "id":inverter_name ,"value":inverter_name});
				  		}
						$('#inverter_name').combobox('loadData',data);
						 var inverter_name = document.getElementsByName("inverter_name")[0].value;        				
        				 realTimePower(psId,period);//实时功率、发电量方法调用
		  	    	}
		  	    	else{
		  	    		$('#inverter_name').combobox('setValue','无设备'); 
		  	    	}		       				
			  	}
			
		});
    	 Highcharts.setOptions({                                                     
            global: {                                                               
                useUTC: false                                                       
            }                                                                       
        });             
	});
	function getPower(){//选择逆变器名称时，获取该逆变器实时数据、发电量数据
		var Request = new Object();
		Request = GetRequest();
		var psId = Request['ps_id'];
		var period = Request['period_id'];
		var inverter_name = document.getElementsByName("inverter_name")[0].value;        				 
		clearInterval(i);
		realTimePower(psId,period);
	}
	function realTimePower(psId,period){
		var name = document.getElementsByName("inverter_name")[0].value; 
	 	InverterPower(psId,period,name);//显示实时功率
		AccPower(psId,period,name);//显示发电量
		i= setInterval(function(){
	 	    var inverter_name = document.getElementsByName("inverter_name")[0].value; //需要重新获取
	 		InverterPower(psId,period,inverter_name);
	 		AccPower(psId,period,inverter_name);
		 },10000);    		
	}
	
	
	function InverterPower(ps_id,period_id,inverterName){
		var psId = ps_id;
		var period = period_id;
		var inverter_Name = inverterName;
		var data_Power = [];
		var data_Irrad = [];
		var index = [];       
		var notzero=0;
		var power_beginzero=24;
		var irrad_beginzero=24;
		var myDate = new Date();
                    $.ajax({
		       			url:'getInverterRealTimeDataByPSId.action?ps_id='+psId+'&period='+period+'&inverter_name='+encodeURI(encodeURI(inverter_Name)),	  	       
						type:'GET',
						dataType:'json',
						async:false,
		  	    		success:function(obj){
		  	    			//for(var i=0;i<obj[0].length;i++){
		  	    			/*for(var i=myDate.getHours();i>=7;i--){
		  	    			    if(obj[0][i]!=0.0){
		  	    			       notzero=i;
		  	    			       break;
		  	    			    }
		  	    			}*/
		  	    			for(var i=7;i<=myDate.getHours();i++){
		  	    			    if(obj[0][i]!=0.0){
		  	    			       power_beginzero=i;
		  	    			       break;
		  	    			    }
		  	    			}
		  	    			for(var i=7;i<=myDate.getHours();i++){
		  	    			    if(obj[1][i]!=0.0){
		  	    			       irrad_beginzero=i;
		  	    			       break;
		  	    			    }
		  	    			}
		  	    			for(var i=power_beginzero;i<=myDate.getHours();i++)
		  	    			{
		  	    			    if(obj[0][i]==0.0){
		  	    			       obj[0][i]=null;
		  	    			    }
		  	    			}
		  	    			for(var i=irrad_beginzero;i<=myDate.getHours();i++)
		  	    			{
		  	    			    obj[1][i]=obj[1][i]*20*1.5*0.8/10000;
		  	    			    if(obj[1][i]==0.0){
		  	    			       obj[1][i]=null;
		  	    			    }
		  	    			}
		  	    			for(var i=7;i<=myDate.getHours();i++){
		  	    				data_Power.push(obj[0][i]);
		  	    				data_Irrad.push(obj[1][i]);
		  	    			}	       	
		  	    			for(var i=7;i<23;i++){
		  	    			   //data_Irrad.push(obj[1][i]);
		  	    			   index.push(obj[2][i]);
		  	    			}	       				
			  	   		}
			  		});
			  		 $('#power_chart').highcharts({                                                
                   chart: {
		                type: 'line',
		                backgroundColor: 'rgba(68, 170, 213, 0.1)'
		            },                                                              
            title: {                                                                
                text: '逆变器'+inverter_Name+'功率与光功率实时数据' ,  
                 style: {
			    	font: '16px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			    	}                                         
            }, 
            credits : {
            enabled:false//不显示highCharts版权信息
          	},                                                                    
           xAxis: {
			    categories: index,
			    min: 0,
                max: 15,//标签个数-1
			    type: 'category',
			    gridLineWidth: 0,
			    lineColor: '#999',
			    tickColor: '#999',
			    labels: {
			    	style: {
			    		color: '#999',
			    		fontWeight: 'bold',
			    		fontSize: '13px',
	                    fontFamily: 'Verdana, sans-serif'
			    	}
			    },
			    title: {
			        text: '时间',
			        //align: 'right'
			    }
               
			},                                                                      
            yAxis: 
				 //alternateGridColor: null,
				 //minorTickInterval: null,
				 //gridLineColor: '#999',
				 //minorGridLineColor: 'rgba(25,255,255,0.07)',
				 //lineWidth: 0,
				 //tickWidth: 0,
			    [{title: {
			        text: '光功率(KW)',
			        style: {
			        	color: '#333',
			        	font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			        	}
			    },
			    labels: {
				 	style: {
				 		color: '#999',
				 	}
				 },
				  min:0,
			     opposite: true
			    },
			    {title: {
			        text: '功率 （KW)',
			        style: {
			        	color: '#333',
			        	font: '12px Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif'
			        	}
			    },
			    labels: {
				 	style: {
				 		color: '#999',
				 	}
				 },
				  min:0
			    }],
		         min:0,
			     plotLines: [{
			        value: 0,
			        width: 1,
			        color: '#808080'
			    }]
			,                                                                   
             tooltip: {
             	formatter: function() {  
				//return '<b>逆变器'+ inverter_Name +'</b><br/>时间：'+this.x +' 功率： '+ this.y+'KW'; 
				return '<b>逆变器'+ inverter_Name +'</b><br/>时间：'+this.x+' '+this.series.name+':'+ this.y;  
				} 
        	 },
        	 
        	
        	 
        	 
        	legend: {
			    borderWidth: 0,
			    itemStyle: {
			    	color: '#CCC'
			    	},
			    	itemHoverStyle: {
			    	color: '#606060'
			    	},
			    	itemHiddenStyle: {
			    	color: '#333'
			    	}
			},    
			plotOptions: {
            series: {
                connectNulls: true
            }
        },                                                                                                                                    
           series: [{
			    name: '功率(KW)',
			    type: 'line',
			    yAxis: 1,
			    data: data_Power
			}, {
			    name: '光功率(KW)',
			    type: 'line',
			    data: data_Irrad
			}]                                                                    
        });   
	}
	
	function AccPower(ps_id,period_id,inverter_Name){
	   var psId = ps_id;
		var period = period_id;
		var data = [];
				var index = [];       
                    $.ajax({
		       			url:'getPMRealTimeDataByPSId.action?ps_id='+psId+'&period='+period+'&inverter_name='+encodeURI(encodeURI(inverter_Name)),	  	       
						type:'GET',
						dataType:'json',
						async:false,
		  	    		success:function(obj){
		  	    			for(var i=obj[0].length-1;i>=0;i--){
		  	    				data.push(obj[0][i].accPower);
		  	    			   index.push(getformatdate(obj[0][i].time));
		  	    			}	       				
			  	   		}
			  		});
			  		 $('#powerh_chart').highcharts({                                                
                                                                                
            title: {                                                                
                text: '逆变器'+inverter_Name+'发电量实时数据' ,                                          
            }, 
            credits : {
            enabled:false//不显示highCharts版权信息
          	},                                                                     
            xAxis: { 
             labels: { 
                    rotation: -30 
                } ,                                                               
                 categories: index                                        
            },                                                                      
            yAxis: {                                                                
                title: {                                                            
                    text: '发电量(Kwh)'                                                   
                },                                                                  
                plotLines: [{                                                       
                    value: 0,                                                       
                    width: 1,                                                       
                    color: '#808080'                                                
                }]                                                                  
            },                                                                      
             tooltip: {
             	formatter: function() {  
				return '时间：'+this.x +' 发电量： '+ this.y+'Kwh';  
				} 
        	 },
        	 legend: {
             	layout: 'vertical',
             	align: 'right',
             	verticalAlign: 'middle',
             	borderWidth: 0
        	 },                                                                                                                                         
            series: [{                                                              
                name: 'data',                                                
                data: data                                                               
            }]                                                                      
        });     
	}
	//传入从数据库中取出的日期类型数据 
	function getformatdate(d){ 
		var formate_datetime; 
		var array = d.split(" "); 
		var date = array[0]; 
		var time = array[1]; 
		var array_date = date.split("-"); 
		var array_time = time.split(":"); 
		var second = array_time[2].split(".")[0]; 
		formate_datetime = array_time[0] + ":" + array_time[1] + ":" + second ; 
		
		return formate_datetime; 
	} 
	
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
	
<body>    
    <div class="easyui-layout" style="width:100%;height:100%;">
        <div data-options="region:'north'"  style="height:13%;background:url('images/nav.png');">					    					                 			
		    	<div id="north-left">
		    	<div  style="width:100%;height:30%;">	
		    		</div><div style="color:#ffffff;font: bold 16px '宋体','微软雅黑'; font-size:40px;text-align:center;vertical-align:middle;">实时数据  		
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
					
					<div class="easyui-panel" id = "container"  style="width:100%;height:99%;padding:0px;">
					<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'west'" style="width:30%;padding:0px">
						<div class="easyui-layout" data-options="fit:true">
							<div id="WS_panel" data-options="region:'north'" style="width:100%;height:35%;padding:0px">
									<table id="pg" class="easyui-propertygrid" style="width:100%;height:95%;padding:0px"  data-options="showGroup:true,scrollbarSize:0,showHeader:false" 	>
  									</table> 		
							</div>
							<div id="JB_panel" data-options="region:'south'" style="width:100%;height:65%;padding:0px">
									<div class="easyui-panel" title="发电量实时数据" style="width:100%">
									<div id="powerh_chart" style="width:95%;height:90%;margin-top:12px;"></div>
									</div>	
							</div>
						</div>
					</div>
					<div data-options="region:'east'" style="width:70%;padding:0px">
							<div id="PM_panel" data-options="region:'north'" style="width:100%;height:50%;padding:0px">
								
                					
   			 					
								<div class="easyui-panel" title="功率实时数据" style="width:100%">
								
									<div style="width:100%;margin-top:10px;">
									<label>逆变器名称</label>
                					<select id="inverter_name"  class="easyui-combobox" name="inverter_name" style="width:160px;"  editable="false" data-options="onSelect:getPower, valueField: 'id',textField: 'text'">           		 
   			 						</select>
									</div>
									<div  id="power_chart" style="width:100%;margin-top:10px;"></div>
								</div>		
							</div>
							<div id="Inverter_panel" data-options="region:'south'" style="width:100%;height:50%;padding:0px">
											
							</div>				
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
