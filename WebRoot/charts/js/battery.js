$(function(){
    var myChart4 = echarts.init(document.getElementById('chart4')); 
        
option4 = {
    title : {
        text: '电池输出功率'
    },
    tooltip : {
        trigger: 'axis',
        formatter : function (params) {
          return "功率："+params[0].value
        }
    },
    legend: {
        data:['输出功率']
    },
    toolbox: {
        show : true,
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
            data : ['00:00','01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} kW'
            }
        }
    ],
    series : [
        {
            name:'输出功率',
            type:'line',
            data:arr_lodash,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
};
                    

        // 为echarts对象加载数据 
        myChart4.setOption(option4); 
})