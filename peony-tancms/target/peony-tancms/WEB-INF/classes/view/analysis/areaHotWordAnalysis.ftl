  
  	<script type="text/javascript">
  	
		 require.config({
           paths: {
				'echarts/chart/map':'${request.getContextPath()}/js/utils/echarts/echarts-map'
				}
        });
        require(
        [
                'echarts',
                'echarts/chart/map',
        ],
		function (ec) {
			option = {
    
    tooltip : {
        trigger: 'item',
        formatter: function (params,ticket,callback){
        var area=params[1];
        var words;
        
        <#list arealist as area>
        	if(params[1]=="${area}"){
        		words=params[1]+':'+"${map[area]}";
        	}
        </#list>
        	return words;
        	
        	}
    },
   
    dataRange: {
        min: 0,
        max: 1000,
        color:['orange','yellow'],
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true,
        x: '-500',
        y: '-500'
       
    },
   
    series : [
        {
            name: '随机数据',
            type: 'map',
            mapType: '${area}',
            selectedMode : 'single',
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
            	  <#list arealist as area>
        			{name: "${area}",value: "${map[area]}"},
        		  </#list>
            ]
                }
    ]
};
                    
		var myChart2=ec.init(document.getElementById('areaHotWordAnalysis'));
    	myChart2.setOption(option);
		});
    </script>

        