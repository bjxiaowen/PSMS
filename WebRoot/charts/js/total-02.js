  $(document).ready(function() {
		 var myChart = echarts.init(document.getElementById('chart_002')); 
		        // data = [11, 11, 15, 13, 12, 13, 10,8,2,6,15,20,4,7,14,10,11,12,0,2,6,15,20,14];
		        // data1 = [13, 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];
		option = {
		    tooltip : {
		        formatter: "{a} <br/>{c} {b}"
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    series : [
		        {
		            name:'功率',
		            center : ['50%', '65%'],
		            type:'gauge',radius : screen.width=='1920'?'120%':'80%',
		            z: 3,
		            min:0,
		            max:30,
		            startAngle:180,
		            endAngle:0,
		            splitNumber:3,
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    width: 30
		                }
		            },
		            axisTick: {            // 坐标轴小标记
		                length :35,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                length :40,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'auto'
		                }
		            },
		            title : {
		                offsetCenter: [-70, '25%'], 
		                show:false,
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontWeight: 'bolder'
		                }
		            },
		            detail : { 
		                offsetCenter: [10, '10%'], 
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                },
		                formatter:"{value} KW"
		            },
		            data:[{value: currKw, name: '功率'}]
		        },
		        {
		            name:'电压',
		            type:'gauge',
		            center : ['25%', '65%'],    // 默认全局居中
		            radius : screen.width=='1920'?'100%':'80%',
		            min:0,
		            max:300,
		            startAngle:180,
		            endAngle:90,
		            splitNumber:3,
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    width: 30
		                }
		            },
		            axisTick: {            // 坐标轴小标记
		                length :35,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                length :40,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'auto'
		                }
		            },
		            pointer: {
		                width:5,length : '80%'
		            },
		            title : {
		                offsetCenter: [-110, '25%'],       // x, y，单位px
		                show:false,
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontWeight: 'bolder',
		                    shadowBlur: 10
		                }
		            },
		            detail : {
		                width:20,
		                offsetCenter: [-40, '10%'], 
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontWeight: ''
		                },
		                formatter:"{value} V"
		            },
		            data:[{value: currV,name:"电压"}]
		        },
		        {
		            name:'电流',
		            type:'gauge',
		            center : ['75%', '65%'],    // 默认全局居中
		            radius : screen.width=='1920'?'100%':'80%',
		            min:0,
		            max:100,
		            startAngle:90,
		            endAngle:0,
		            splitNumber:5,
		            axisLine: {            // 坐标轴线
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    width: 30
		                }
		            },
		            axisTick: {            // 坐标轴小标记
		                length :35,        // 属性length控制线长
		                lineStyle: {       // 属性lineStyle控制线条样式
		                    color: 'auto'
		                }
		            },
		            splitLine: {           // 分隔线
		                length :40,         // 属性length控制线长
		                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
		                    color: 'auto'
		                }
		            },
		            pointer: {
		                width:5,length : '80%'
		            },
		            title : {
		                offsetCenter: [0, '25%'],       // x, y，单位px
		                show:false,
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontWeight: 'bolder',
		                    shadowBlur: 10
		                }
		            },
		            detail : {
		                offsetCenter: [50, '10%'], 
		                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                    fontWeight: ''
		                },
		                formatter:"{value} A"
		            },
		            data:[{value: currC,name:"电流"}]
		        }
		    ]
		};
		myChart.setOption(option,true);
      });