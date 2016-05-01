$(function(){
    var myChart8 = echarts.init(document.getElementById('chart8')); 
        data = [11, 11, 15, 13, 12, 13, 10,8,2,6,15,20,4,7,14,10,11,12,0,2,6,15,20,14];
        data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data2 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data3 = [13, 13,13,13,13,13,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data4 = [4, 23,13,7,0,33,15,14,7,4,20,1,12,20,22,26,15,20,34,12,16,15,20,14];

option8 = {
    title : {
        text: '交流输出',
        subtext: '纯属虚构'
    },
    tooltip : {
        trigger: 'axis',
        formatter : function (params) {
          var d = params[0].name;
          if (d.substr(0,1)=='0') d=d.substr(1);
          arr = d.split(":");
          var index = arr[0]-1 ;
          var a,b;
          $.each(data2,function(i,v){
            if(index == i){
                a = v;
            }
          })
          $.each(data,function(i,v){
            if(index == i){
                b = v;
            }
          })
          return "蓄电池电压："+ a + '<br/>'+"蓄电池电流："+ b+'<br/>'+"功率："+params[0].value
        }
    },
    legend: {
        data:['交流输出']
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
            data : ['01:00','02:00','03:00','04:00','05:00','06:00','07:00','08:00','09:00','10:00','11:00','12:00','13:00','14:00','15:00','16:00','17:00','18:00','19:00','20:00','21:00','22:00','23:00','24:00']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value}千瓦(kW)'
            }
        }
    ],
    series : [
        {
            name:'交流输出',
            type:'line',
            data:data3,
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
        myChart8.setOption(option8); 
})