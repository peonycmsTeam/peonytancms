  <div id="growthrate" style="width:600px;height:400px;"></div>
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

        
      var   option = {
         
         title : {
        text: '',
        subtext: ''
    },
         tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['增长率','本期','上期']
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
            data :  ${regionname}
        }
    ],
    yAxis : [
        {
            type : 'value',
            position: 'left',
            //min: 0,
            //max: 300,
            //splitNumber: 5,
            boundaryGap: [0,0.1],
            axisLine : {    // 轴线
                show: true,
                lineStyle: {
                    color: 'red',
                    type: 'dashed',
                    width: 2
                }
            },
            axisTick : {    // 轴标记
                show:true,
                length: 10,
                lineStyle: {
                    color: 'green',
                    type: 'solid',
                    width: 2
                }
            },
            axisLabel : {
                show:true,
                interval: 'auto',    // {number}
                rotate: -45,
                margin: 18,
                formatter: '{value}',    // Template formatter!
                textStyle: {
                    color: '#1e90ff',
                    fontFamily: 'verdana',
                    fontSize: 10,
                    fontStyle: 'normal',
                    fontWeight: 'bold'
                }
            },
            splitLine : {
                show:true,
                lineStyle: {
                    color: '#483d8b',
                    type: 'dotted',
                    width: 2
                }
            },
            splitArea : {
                show: true,
                areaStyle:{
                    color:['rgba(205,92,92,0.3)','rgba(255,215,0,0.3)']
                }
            }
        },
        {
            type : 'value',
            splitNumber: 10,
            axisLabel : {
                formatter: function (value) {
                    // Function formatter
                    return value + ''
                }
            },
            splitLine : {
                show: false
            }
        }
    ],
    series : [
        {
            name: '增长率',
            type: 'bar',
            data:${growthrate}
        },
        {
            name:'上期',
            type: 'line',
            data: ${compositeindexs}
        },
        {
            name:'本期',
            type: 'line',

            data:  ${compositeindex}
        }
    ]
    };

			  var myChart=ec1.init(document.getElementById('growthrate'));
              myChart.setOption(option); 
 			 }
    );

    </script>

