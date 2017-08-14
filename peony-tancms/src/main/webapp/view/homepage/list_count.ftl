<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  

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

        function (ec1) {

        
         option = {
  				tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['负面','争议','正面']
                },
                toolbox: {
                    show : true,
                    feature : {
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                grid :{
                x:45,
                y:20,
                x2:5,
                y2:50
                },
                  dataZoom : {
      			  show : true,
        		  realtime : true,
       			  start : 0,
       			  end : 100
  				  },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ${mytimes}
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
                        name:'负面',
                        type:'bar',
                        data:${count2}
                    }
                    ,
                    {
                        name:'争议',
                        type:'bar',
                        data:${count3}
                    },
                    {
                        name:'正面',
                        type:'bar',
                        data:${count1}
                    }
                 
                ]
};
option2 = {
   
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    calculable : true,
    series : [
        {
           
            type:'pie',
            radius : '50%',
            center: ['54%', 100],
            data:[
                {value:${allcount2}, name:'负面'},
                {value:${allcount3}, name:'争议'},
                {value:${allcount1}, name:'正面'}
              
            ]
        }
    ]
};

                               

    var myChart=ec1.init(document.getElementById('count1'));
            myChart.setOption(option);
 	var myChart2=ec1.init(document.getElementById('count2'));
            myChart2.setOption(option2);
        }

    );

    </script>

