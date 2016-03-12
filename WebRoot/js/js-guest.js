

//显示灰色 jQuery 遮罩层 
function showBg(t) { 
	var bh = $("body").height(); 
	var bw = $("body").width(); 
	$("#fullbg").css({ 
	height:bh, 
	width:bw, 
	display:"block" }); 

	var this_id = $(t).attr("id");
	switch(this_id)
	{
		case "td1":$("#dialog1").show();break;
		case "td2":$("#dialog2").show();break;
		case "td3":$("#dialog3").show();break;
		case "td4":$("#dialog4").show();break;	
	}			
} 
//关闭灰色 jQuery 遮罩 
function closeBg(t) { 	
	var this_id = $(t).attr("id");
	switch(this_id)
	{
		case "a1":$("#fullbg,#dialog1").hide();break;
		case "a2":$("#fullbg,#dialog2").hide();break;
		case "a3":$("#fullbg,#dialog3").hide();break;
		case "a4":$("#fullbg,#dialog4").hide();break;
		case "aa":$("#fullbg,#dialogC").hide();break;
	}	  	
} 
function closeBg1() { 
	
	
	$("#fullbg,#Map_disp_data").hide();  
	
	
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
  //addMarker();
}

function addMarker()
{//添加坐标点

       		$.ajax({
       			url:'getStationPosition.action',
       			type:'GET',
       			dataType:'json',
       			async:false,
	  			success:function(obj){	  			
	  				var arr = new Array();
	  				for(var i=0;i<obj[0].length;i++){
  						var longitude = obj[0][i].longitude;
  						var latitude = obj[0][i].latitude;
  						
  						 marker= new AMap.Marker({                
  					        icon:"http://webapi.amap.com/images/marker_sprite.png",
  					        position:new AMap.LngLat(longitude,latitude),
  						 	});
  						 
  						marker.setMap(mapObj);  //在地图上添加点	
  						marker.setTitle(obj[0][i].name); //设置鼠标划过点标记显示的文字提示
  						AMap.event.addListener(marker,"click",function eventHandler()
  						{ 
							
  							var bh = $("body").height(); 
							var bw = $("body").width(); 
							$("#fullbg").css({ 
							height:bh, 
							width:bw, 
							display:"block" }); 
					    	var	str="";					    
					    	var lng= this.getPosition().getLng();
					 		var lat= this.getPosition().getLat(); 
					 	
			  				for(var i=0;i<obj[0].length;i++){
		  						var longitude = obj[0][i].longitude;
		  						var latitude = obj[0][i].latitude;
		  						if(longitude==lng&& latitude==lat)
		  						{		  							
		  							str = str + "<tr><td>电站名称:"+obj[0][i].name+"</td></tr>"+
		  							"<tr><td>装机容量："+obj[0][i].capacity+"</td></tr>"+
		  							"<tr><td>占地面积："+obj[0][i].area+"</td></tr>"+
		  							"<tr><td>组件数量："+obj[0][i].part_num+"</td></tr>"+
		  							"<tr><td>业主："+obj[0][i].owner+"</td></tr>"+
		  							"<tr><td>投资方："+obj[0][i].investor+"</td></tr>"+
		  							"<tr><td>所属省份："+obj[0][i].province+"</td></tr>"+
		  							"<tr><td>建厂时间："+obj[0][i].build_time+"</td></tr>";
		  						}
			  				}					 		
							$("#mapData").append(str);
							$("#dialogMap_disp_data").show(); 							
						}) ;						
  						mapObj.setFitView(); //调整到合理视野
  				    }
					
					
	  			}
       			}); 
}

	

