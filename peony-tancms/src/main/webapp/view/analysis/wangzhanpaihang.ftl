  	<script type="text/javascript">
		require.config({
            paths: {
                echarts:'${request.getContextPath()}/js/utils/echarts/echarts',
 				'echarts/chart/pie':'${request.getContextPath()}/js/utils/echarts/echarts',
                'echarts/chart/bar':'${request.getContextPath()}/js/utils/echarts/echarts',
                'echarts/chart/line':'${request.getContextPath()}/js/utils/echarts/echarts'
            }
        });
        require(
        [
            'echarts',
            'echarts/chart/bar',
 			'echarts/chart/pie',
            'echarts/chart/line'
        ],
		function (ec) {
			option = {
			    tooltip : {
			        trigger: 'axis'
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            axisLabel : {
                			rotate : 40
            			},
			            data : ${name}
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'数量',
			            type:'bar',
			            data:${value}
			            
			        }
			    ]
			};
			var myChart3=ec.init(document.getElementById('wangzhanpaihang'));
    		myChart3.setOption(option);
		});
    </script>

        