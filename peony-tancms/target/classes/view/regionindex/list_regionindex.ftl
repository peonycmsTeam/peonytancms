  <div id="regionindex" style="width:600px;height:400px;"></div>
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
            'echarts/chart/line'

        ],

        function (ec1) {

        
         option = {
        		title : {
        		text: '',
   			 },
  				tooltip : {
                    trigger: 'axis'
                },
                 
    			calculable : true,
                legend: {
                    data:['热度指数','敏感指数','关注指数','综合指数']
                },
                
                grid :{
                x:40,
                y:40,
                x2:5,
                y2:70
                },
                  
                xAxis : [
        {
            type : 'category',
            position: 'bottom',
            boundaryGap: true,
            axisLine : {    // 轴线
                show: true,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 2
                }
            },
            axisTick : {    // 轴标记
                show:true,
                length: 10,
                lineStyle: {
                    color: 'red',
                    type: 'solid',
                    width: 2
                }
            },
            axisLabel : {
                show:true,
                interval: 'auto',    // {number}
                rotate: 45,
                margin: 8,
                formatter: '{value}',
                textStyle: {
                    color: 'blue',
                    fontFamily: 'sans-serif',
                    fontSize: 15,
                    fontStyle: 'italic',
                    fontWeight: 'bold'
                }
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#483d8b',
                    type: 'dashed',
                    width: 1
                }
            },
            splitArea : {
                show: true,
                areaStyle:{
                    color:['rgba(144,238,144,0.3)','rgba(135,200,250,0.3)']
                }
            },
                        data : ${regionname}
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'热度指数',
                        type:'bar',
                        data:${heatindex}
                    }
                    ,
                    {
                        name:'敏感指数',
                        type:'bar',
                        data:${sensitiveindex}
                    },
                    {
                        name:'关注指数',
                        type:'bar',
                        data:${focusindex}
                    },
                    {
                        name:'综合指数',
                        type:'line',
                        data:${compositeindex}
                    }
                 
                ]
};

			  var myChart=ec1.init(document.getElementById('regionindex'));
              myChart.setOption(option); 
 			 }
    );

    </script>

