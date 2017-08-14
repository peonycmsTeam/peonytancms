  <div id="shengdistribute" style="width:500px;height:350px;"></div>
     <div id="shengdistribute1" style="width:240px;height:400px;position: absolute;top:720px;left:1000px"></div>
<script src="${request.getContextPath()}/js/utils/dist/echarts.js" type="text/javascript"></script>  

  <script type="text/javascript">

        require.config({
            paths: {
                echarts:'${request.getContextPath()}/js/utils/dist'				
            }

        });  

        require(
        [
            'echarts',
            'echarts/chart/bar',
 			'echarts/chart/pie',
            'echarts/chart/line',
            'echarts/chart/map'

        ],

        function (ec1) {

        
       var   option = {
   
    dataRange: {
        min: 0,
        max: 100,
        color:['orange','yellow'],
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true
    },
    series : [
        {
            name: '省内舆情综合指数',
            type: 'map',
            mapType: '${regionnamed}',
            selectedMode : 'single',
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
            
				<#list map2?keys as key>  
				<#if key_index==0>
				{name: '${map2[key]}',value:${map4['${map2[key]}']?string("#.##")}}<#else>
				,{name: '${map2[key]}',value:${map4['${map2[key]}']?string("#.##")}}
				</#if>
	            	
				</#list>  
                
            ]
        }
    ]
    };
	
	var optiontwo = {
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.01],
            axisLabel : {
                show:true,
                interval: 'auto',    // {number}
                rotate: 45,
                margin: 3,
                formatter: '{value}',
                textStyle: {
                    color: 'blue',
                    fontFamily: 'sans-serif',
                    fontSize: 14,
                    fontStyle: 'italic',
                    fontWeight: 'bold'
                }
            }
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['${regionnames9}','${regionnames8}','${regionnames7}','${regionnames6}','${regionnames5}',
            '${regionnames4}','${regionnames3}','${regionnames2}','${regionnames1}','${regionnames0}']
        }
    ],
    series : [
        {
           
            type:'bar',
            itemStyle: {
                normal: {
                    label : {
                        show: true
                    }
                }
            },
            data:['${compositeindexs9?string("#.##")}','${compositeindexs8?string("#.##")}',
           		  '${compositeindexs7?string("#.##")}','${compositeindexs6?string("#.##")}',
            	  '${compositeindexs5?string("#.##")}','${compositeindexs4?string("#.##")}',
           		  '${compositeindexs3?string("#.##")}','${compositeindexs2?string("#.##")}',
           		  '${compositeindexs1?string("#.##")}','${compositeindexs0?string("#.##")}']
        }
    ]
};
			  var myChart=ec1.init(document.getElementById('shengdistribute'));
              myChart.setOption(option); 
               var myChart=ec1.init(document.getElementById('shengdistribute1'));
              myChart.setOption(optiontwo); 
 			 }
    );

    </script>

