$(document).ready(
		function() {
			var myChart = echarts.init(document.getElementById('chart_003'));
			data3 = [ 11, 11, 15, 13, 12, 13, 10, 8, 2, 6 ];
			// data1 = [13,
			// 10,18,12,6,15,20,14,17,14,20,21,12,10,12,16,15,20,14,12,16,15,20,14];

			option3 = {
				title : {
					text : '当月日发电量'
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					data : [ '当月日发电量' ]
				},
				toolbox : {
					show : true,
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
							'11', '12', '13', '14', '15', '16', '17', '18',
							'19', '20', '21', '22', '23', '25', '26', '27',
							'28', '29', '30' ]
				} ],
				yAxis : [ {
					type : 'value',
					axisLabel : {
						formatter : '{value}(kWh)'
					}
				} ],
				series : [ {
					name : '当月日发电量',
					type : 'bar',
					data : data3,
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
			myChart.setOption(option3);
		});