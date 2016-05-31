
  $(document).ready(function() {
        App.init();
        // Plugins.init();
        // FormComponents.init()
 var myChart = echarts.init(document.getElementById('chart_001')),

        data = _.map(currDayQ,"currDayQ");
 		//data1=_.map(currDayQ,"groupHour");
 		//console.log(data1);
        // data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
 		
option = {
    title : {
        text: '日输出电量 kwh'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['日发电量']
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
            // boundaryGap : false,
            data : xdata
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
            name:'日发电量',
            type:'line',
            data:data,
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
                    

        // 为echarts对象加载数据 
        myChart.setOption(option); 
      });