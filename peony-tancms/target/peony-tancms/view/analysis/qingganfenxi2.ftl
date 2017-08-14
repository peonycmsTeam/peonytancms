      	<div class="k_silist2">
			<ul>
			<li><div id="main3"style="width:320px; height:265px;"></div></li>
			<li><div id="main4"style="width:400px; height:265px;"></div></li>
			</ul>
      	</div>
		<div class="blank5px"></div>
        <div class="blank10px"></div>
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
			option4 = {
			    title : {
			        text: '媒体类型对比'
			    },
			    tooltip : {
			        trigger: 'axis'
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
			            axisLabel : {
                			rotate : 40
            			},
			            data : ${Name}
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'负面',
			            type:'bar',
			            data:${fString}
			        },
			        {
			            name:'争议',
			            type:'bar',
			            data:${zhengyiString}
			        },
			        {
			            name:'正面',
			            type:'bar',
			            data:${zString}
			        }
			      	
			    ]
			};
		    var myChart4=ec.init(document.getElementById('main4'));
		    myChart4.setOption(option4);          
	}
);
    </script>
