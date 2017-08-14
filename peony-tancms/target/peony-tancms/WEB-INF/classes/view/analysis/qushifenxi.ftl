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
			    legend: {
			        data:['整体趋势','定制舆情','政务舆情']
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            boundaryGap : false,
			            data : ${dateString}
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'政务舆情',
			            type:'line',
			            data:${zhengwuyuqing}
			        },
			        {
			            name:'定制舆情',
			            type:'line',
			            data:${dingzhiyuqing}
			        },
			        {
			            name:'整体趋势',
			            type:'line',
			            data:${zhengtiqushi}
			        }
			    ]
			}
		var myChart1=ec.init(document.getElementById('qushifenxi'));
    	myChart1.setOption(option);
		});
    </script>

        