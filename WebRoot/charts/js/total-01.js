  $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
 var myChart = echarts.init(document.getElementById('chart_001')),
        data = [0,0,0,0,0,0,0,0,1,5, 10, 12, 13, 15, 16, 14,12,11,8,4,1,0,0,0];
        // data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];

option = {
    title : {
        text: '日输出电量'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['日发电量']
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
            // boundaryGap : false,
            data : ['01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}(kWh)'
            }
        }
    ],
    series : [
        {
            name:'日发电量',
            type:'bar',
            data:data,
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
        myChart.setOption(option); 
      });