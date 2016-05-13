$(function(){
    var myChart1 = echarts.init(document.getElementById('chart001')); 
        data = [11, 11, 15, 13, 12, 13, 10,8,2,6,15,20,4,7,14,10,11,12,0,2,6,15,20,14];
        data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data2 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data3 = [13, 13,13,13,13,13,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
        data4 = [4, 23,13,7,0,33,15,14,7,4,20,1,12,20,22,26,15,20,34,12,16,15,20,14];
option1 = {
    tooltip : {
        formatter: "{a} <br/>{c} {b}"
    },
    toolbox: {
        show : true,
        feature : {        }
    },
    series : [
        {
            name:'电压',
            type:'gauge',radius : '100%',
            z: 3,
            min:0,
            max:500, center : ['50%', '60%'],
            startAngle:180,
            endAngle:0,
            splitNumber:4,
            axisLine: {            // 坐标轴线
                lineStyle: {       // 属性lineStyle控制线条样式
                    width: 10
                }
            },
            axisTick: {            // 坐标轴小标记
                length :15,        // 属性length控制线长
                lineStyle: {       // 属性lineStyle控制线条样式
                    color: 'auto'
                }
            },
            splitLine: {           // 分隔线
                length :20,         // 属性length控制线长
                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                    color: 'auto'
                }
            },
            title : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder',
                    fontSize: 20
                }
            },
            detail : {
                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                    fontWeight: 'bolder'
                }
            },
            data:[{value: vv, name: 'V'}]
        }
    ]
};
myChart1.setOption(option1,true);
                           
                    
})