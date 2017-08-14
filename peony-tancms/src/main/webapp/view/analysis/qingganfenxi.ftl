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
			option3 = {
				title : {
			        text: '正负面倾向性分析'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
				calculable : true,
			    series : [
			        {
			            type:'pie',
			            radius : '60%',
			            center: ['50%', 125],
			            data:${zhengfumian}
			        }
			    ]
			};
		    var myChart4=ec.init(document.getElementById('main3'));
		    myChart4.setOption(option3);     
	}
);
    </script>
