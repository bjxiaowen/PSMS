$(function(){
     var myChart = echarts.init(document.getElementById('chart1')); 

var jingwei = _.chain(data).map(function(o){
	var arr = [],arr2 = [];
	arr.push(o.province);
	arr2.push(parseFloat(o.longitude));
	arr2.push(parseFloat(o.latitude));
	arr.push(arr2);
	return arr;
}).fromPairs().value();


var data_alert = _.map(data,function(o){
	var obj = {};
	obj.name = o.province;
	obj.value = o.machineState;
	obj.ps_name = o.name;
	obj.build_time = o.build_time;
	return obj;
})
var name = _.filter(data, ['machineState', 1])
var data11 = {
    list: _.map(name, 'name')
};
var html = template('alertTpl', data11);
document.getElementById('tableDiv').innerHTML = html;
         var option = option = {
                    backgroundColor: '#044062',
                    color: ['gold','aqua','lime'],
                    title : {
                        text: '光伏电站',
                        subtext:'',
                        x:'center',
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
                        		return "<a target = '_blank' href='www.baidu.com'><font style='color:#33cccc'>点击查看</font></a>"+'电站名称：'+params[5].name
                           }else{
                        		return params[5].name;
                        	}
                        	},
                        enterable: true,
                        showDelay:300,
                        hideDelay:4000,
//                        transitionDuration:0,
                       backgroundColor:'rgba(0,0,0,0.5)'
                    },
                    legend: {//图例，每个图表最多仅有一个图例，混搭图表共享
                        orient: 'vertical',
                        x:'left',
                        show:true,
                        data:['光伏电站全国分布'],
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
            name: '全国',
            type: 'map',
            roam: false,
            hoverable: false,//非数值显示（如仅用于显示标注标线时），可以通过hoverable:false关闭区域悬浮高亮
            mapType: 'china',
            itemStyle:{
                normal:{
                	label:{show:true,textStyle:{color:'rgba(229,229,229,1)'}},
                    borderColor:'rgba(16,142,192,1)',
                    borderWidth:0.5,
                    areaStyle:{
                        color: '#004882'
                    }
                }
            },
            data:[],
            geoCoord:jingwei
        },
        {
            name: '光伏电站全国分布',
            type: 'map',
            mapType: 'china',
            data:[],
            tooltip : {},
            markPoint : {
                symbol:'emptyCircle',
                symbolSize : function (v){
                    return 10 + v/10
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
                      var ecConfig = require('echarts/config')
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
})