$(document).ready(
		function() {
			App.init();
			// Plugins.init();
			// FormComponents.init()
			var myChart = echarts.init(document.getElementById('chart_004'));
//			data4 = [ 111, 111, 151, 110 ];
			data4 = _.map(currYearQ,"currYearQ");
			// data1 = [13,
			// 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];

			option4 = {
				title : {
					text : year+'年月发电量 kwh'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '当年月发电量' ]
				},
				toolbox : {
					show : false,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : false
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {
							show : true
						}
					}
				},
				calculable : true,
				xAxis : [ {
					type : 'category',
					// boundaryGap : false,
					data : [ '1', '2', '3', '4', '5', '6', '7', '8', '9', '10',
							'11', '12' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value}'
					}
				} ],
				series : [ {
					name : '当年月发电量',
					type : 'line',
					data : data4,
					markPoint : {
						data : [ {
							type : 'max',
							name : '最大值'
						}, {
							type : 'min',
							name : '最小值'
						} ]
					},
					markLine : {
						data : [ {
							type : 'average',
							name : '平均值'
						} ]
					}
				} ]
			};

			// 为echarts对象加载数据
			myChart.setOption(option4);
		});