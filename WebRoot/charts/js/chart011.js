$(function(){
    var myChart = echarts.init(document.getElementById('chart1')); 
option = {
    title : {
        text: '电站分布及预警信息',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: function (v) {
            console.log(v);
            return v[1].replace(':', ' > ');
        }
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['']
    },
    dataRange: {
        min : 0,
        max : 500,
        calculable : true,
        x: 'left',
        y: 'bottom',
        color: ['lightgreen','red']
    },
    toolbox: {
        show : true,
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
    roamController: {
        show: true,
        x: 'right',
        mapTypeControl: {
            'china': true
        }
    },
    series : [
        {
            name: '功率',
            type: 'map',
            mapType: 'china',
            hoverable: true,
            roam:false,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data : [],
            markPoint : {
                symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
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
                data : [
                    {name: "海门", value: 9},
                    {name: "鄂尔多斯", value: 12},
                    {name: "招远", value: 12},
                    {name: "舟山", value: 12},
                    {name: "齐齐哈尔", value: 14},
                    {name: "盐城", value: 15},
                    {name: "赤峰", value: 16},
                    {name: "青岛", value: 18},
                    {name: "乳山", value: 18},
                    {name: "金昌", value: 19},
                    {name: "泉州", value: 21},
                    {name: "莱西", value: 21},
                    {name: "日照", value: 21},
                    {name: "胶南", value: 22},
                    {name: "南通", value: 23},
                    {name: "廊坊", value: 193},
                    {name: "菏泽", value: 194},
                    {name: "合肥", value: 229},
                    {name: "武汉", value: 273},
                    {name: "大庆", value: 279}
                ]
            },
            geoCoord: {
                "海门":[121.15,31.89],
                "鄂尔多斯":[109.781327,39.608266],
                "招远":[120.38,37.35],
                "舟山":[122.207216,29.985295],
                "齐齐哈尔":[123.97,47.33],
                "盐城":[120.13,33.38],
                "赤峰":[118.87,42.28],
                "青岛":[120.33,36.07],
                "乳山":[121.52,36.89],
                "金昌":[102.188043,38.520089],
                "泉州":[118.58,24.93],
                "莱西":[120.53,36.86],
                "日照":[119.46,35.42],
                "胶南":[119.97,35.88],
                "南通":[121.05,32.08],
                "廊坊":[116.7,39.53],
                "菏泽":[115.480656,35.23375],
                "合肥":[117.27,31.86],
                "武汉":[114.31,30.52],
                "大庆":[125.03,46.58]
            }
        },
        {
            name: '电站信息',
            type: 'map',
            mapType: 'china',
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[],
            markPoint : {
                symbol:'emptyCircle',
                symbolSize : function (v){
                    return 10 + v/100
                },
                effect : {
                    show: true,
                    shadowBlur : 0
                },
                itemStyle:{
                    normal:{
                        label:{show:true}
                    }
                },
                data : [
                    {name: "廊坊", value: 193},
                    {name: "菏泽", value: 194},
                    {name: "合肥", value: 229},
                    {name: "武汉", value: 273},
                    {name: "大庆", value: 27}
                ]
            }
        }
    ]
};
                    
            
// $(".copyText").on("click",function(){
//     $(".chart99_block").show();
//     myChart100.resize();
// })
        // 为echarts对象加载数据 
        myChart.setOption(option);
        myChart.setTheme("roma");
        var au = $(".au"); 
    au.each(function(){
        var audio_btn = $(this).find(".audio_btn");
        var m = $(this).find(".music");
        audio_btn.on("click",function(){
             if(m[0].paused){
                m[0].play();
                $(this).find(".music_btn").attr("src","./assets/img/icons/play.gif");
             }else{
                m[0].pause();
                $(this).find(".music_btn").attr("src","./assets/img/icons/pause.png");
             }
        })
    })
})