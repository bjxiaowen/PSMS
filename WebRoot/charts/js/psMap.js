$(function(){
	var vv = $.parseJSON(v);
	var data = vv['list'];
	
	//经纬度
	var jingwei = _.chain(data).map(function(o) {
		var arr = [], arr2 = [];
		arr.push(o.name);
		arr2.push(parseFloat(o.longitude));
		arr2.push(parseFloat(o.latitude));
		arr.push(arr2);
		return arr;
	}).fromPairs().value();

	//echart报警信息
	var data_alert = _.map(data, function(o) {
		var obj = {};
		obj.name = o.name;
		obj.value = o.machineState;
		
		_.forIn(o, function(value, key) {
			  obj[key] = value;
			});
		obj.ps_name = o.name;
		obj.build_time = o.build_time;
		return obj;
	})
	
	//tpl信息
	var name = _.filter(data, [ 'machineState', 1 ])
	var data11 = {
		list : name
	};
	
	
	//require 模块化
     require.config({
    	 paths:{
    		 echarts:'charts/js'
    	 }
     })
     require(
    		 [
              'echarts',
              'echarts/template',
              'echarts/chart/line',
              'echarts/chart/bar',
              'echarts/chart/scatter',
              'echarts/chart/k',
              'echarts/chart/pie',
              'echarts/chart/radar',
              'echarts/chart/force',
              'echarts/chart/chord',
              'echarts/chart/gauge',
              'echarts/chart/funnel',
              'echarts/chart/eventRiver',
              'echarts/chart/venn',
              'echarts/chart/treemap',
              'echarts/chart/tree',
              'echarts/chart/wordCloud',
              'echarts/chart/heatmap',
              'echarts/chart/map'
          ],function(ec,t){
    	    //tpl调用
			var html = t('alertTpl', data11);
			document.getElementById('tableDiv').innerHTML = html;
    				
			//echart调用
	    	var myChart = ec.init(document.getElementById('chart1'));
	    	var option = {
                    backgroundColor: '#044062',
                    color: ['gold','aqua','lime'],
                    title : {
                    text: '光伏电站分布图\r\n总电站数：'+$("#psNum").val()+'\r\n总装机容量：'+$("#Capacity").val()+' KW\r\n历史发电总量：'+$("#HistoryQ").val()+' mwh',
                        subtext:'',
                        x:'left',
                        textStyle : {
                            color: '#fff'
                        }
                    },
                    tooltip : {//提示框样式
                        trigger: 'item',
                        // borderRadius:4,
                       // showContent:true,//只需tooltip触发事件或显示axisPointer而不需要显示内容时可配置该项为false
                        // show:true,
                      //  transitionDuration:0.4,//动画变换时长，单位s，如果你希望tooltip的跟随实时响应，showDelay设置为0是关键，同时transitionDuration设0也会有交互体验上的差别。
                        formatter: function(params, ticket, callback){
                        	if(params[5].build_time != undefined ){
                        		return "<div style='color:#33CCCC'> 电站名称："+params[5].name+"<br/>装机容量："+params[5].capacity +" kw/h"+"<br/>建站时间："+params[5].build_time+"</div>"
                           }else{
                        		return params[5].name;
                        	}
                        	},
                        enterable: false,
                        showDelay:0,
                        hideDelay:0,
//                        transitionDuration:0,
                       backgroundColor:'rgba(0,0,0,0.5)'
                    },
                    legend: {//图例，每个图表最多仅有一个图例，混搭图表共享
                        orient: 'vertical',
                        x:'right',
                        show:false,
                        data:['光伏电站全国分布','故障电站'],
                        selectedMode: 'single',
                        textStyle : {
                            color: '#fff'
                        }
                    },
                    toolbox: {///工具栏
                        show : false,
                        orient : 'vertical',
                        x: 'right',
                        y: 'center',
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    dataRange: {//值域选择,值域范围
                        min : 0,
                        max : 1,
                        calculable : true,
                        color: ['#ff3333', '#3ed06e'],
                        textStyle:{
                            color:'#fff'
                        }
                    },
    series : [
        {
            name: '光伏电站全国分布',
            type: 'map',
            roam: false,
            hoverable: false,//非数值显示（如仅用于显示标注标线时），可以通过hoverable:false关闭区域悬浮高亮
            mapType: 'china',
            roam: true,
            markPoint : {
                symbolSize: 8,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
                itemStyle: {
                    normal: {
                        borderColor: '#87cefa',
                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
                        label: {
                            show: false
                        }
                    },
                    emphasis: {
                        borderColor: '#1e90ff',
                        borderWidth: 5,
                        label: {
                            show: false
                        }
                    }
                },
                data :data_alert
            },
            itemStyle:{
                normal:{
                	label:{show:true,textStyle:{color:'rgba(229,229,229,1)',fontSize:14}},
                    borderColor:'rgba(16,142,192,1)',
                    borderWidth:0.5,
                    areaStyle:{
                        color: '#004882'
                    }
                }, 
                emphasis: {                 // 也是选中样式
                    borderWidth:2,
                    borderColor:'#fff',
                    color: '#000000',
                    label: {
                          show: true,
                          textStyle: {
                        	  color:'rgba(229,229,229,1)',
                        	  fontSize:14
                          }
                    }
                }
            },
            data:[],
            geoCoord:jingwei
        },
        {
            name: '故障电站',
            type: 'map',
            mapType: 'china',
            data:[],
            tooltip : {},
            markPoint : {
                symbol:'emptyCircle',
                symbolSize : function (v){
                    return 15 + v/10
                },
                effect : {//标注图形炫光特效
                    show: true,
                    shadowBlur : 0,
                    type:'scale',
                    scaleSize :1
                },
                itemStyle:{
                    normal:{
                        label:{show:true}
                    },
                    emphasis: {
                        label:{position:'top'}
                    }
                },
                data :data_alert
            }
        }
    ]
};
	    	function linkToUrl(param){
	    		if(param.data.id != undefined){
	    			console.log("param:"+param.data.name);
		    		window.open("toBiIndex.action?psId="+param.data.id+"&psName="+encodeURI(param.data.name));
	    		}
	    	}
            var ecConfig = require('echarts/config')
            myChart.on(ecConfig.EVENT.CLICK,linkToUrl);
            
	    	myChart.setOption(option);
	    	
	    	var au = $(".au"); 
	        au.each(function(){
	            var audio_btn = $(this).find(".audio_btn");
	            var m = $(this).find(".music");
	            audio_btn.on("click",function(){
	                 if(m[0].paused){
	                    m[0].play();
	                    $(this).find(".music_btn").attr("src","charts/assets/img/icons/play.gif");
	                 }else{
	                    m[0].pause();
	                    $(this).find(".music_btn").attr("src","charts/assets/img/icons/pause.png");
	                 }
	            })
	        })

	    }		 
    )
})