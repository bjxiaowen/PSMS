 $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
 var myChart2 = echarts.init(document.getElementById('chart_002')); 
        data2 = [0, 0, 0, 0, 0, 0, 110,120,150,170,190,160,160,150,140,100,80,50,40,30,10,0,0,0];
        // data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];

option2 = {
    title : {
        text: '组件电压(V)'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['组件电压']
    },
    toolbox: {
        show : false,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data :xdata// ['01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}'
            }
        }
    ],
    series : [
        {
            name:'组件电压',
            type:'line',
            data:vv,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            }/*,
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }*/
        }
    ]
};
                    
$(".widget-collapse-2").on("click",function(){
    myChart2.resize();
})
        // 为echarts对象加载数据 
        myChart2.setOption(option2); 
      });