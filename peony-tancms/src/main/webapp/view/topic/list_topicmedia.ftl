<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<link rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj.css">

       <div class="k_silist2">
      <ul><li ><div id="main1"style="width:340px; height:265px;"></div></li>
      <li><div id="main2"style="width:380px; height:265px;"></div></li>
      <li><div id="main3"style="width:360px; height:265px;"></div></li>
      <li><div id="main4"style="width:360px; height:265px;"></div></li>
      </ul></div>
       
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

        
       option1 = {
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['新闻','论坛','微博','博客','电子报刊','twitter']
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '50%',
            center: ['50%', 125],
            data:${result1}
        }
    ]
};

    var myChart1=ec.init(document.getElementById('main1'));
            myChart1.setOption(option1);
 option2 = {
   title : {
        text: '新闻top5',
        x:'center'
    },
    tooltip : {
        trigger: 'axis'
    },

    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ${result2}
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
            name:'数量',
            type:'bar',
       
          barCategoryGap :'80%',
            data:${result3}
           
        }
    ]
};
            var myChart2=ec.init(document.getElementById('main2'));
            myChart2.setOption(option2);     
option3 = {
    title : {
        text: '论坛top5',
     x:'center'
    },
    tooltip : {
        trigger: 'axis'
    },
   
   
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ${result4}
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
            name:'数量',
            type:'bar',
            data:${result5}
           
        }
    ]
};
            var myChart3=ec.init(document.getElementById('main3'));
            myChart3.setOption(option3);     
option4 = {
    title : {
        text: '报刊top5',
     x:'center'
    },
    tooltip : {
        trigger: 'axis'
    },
   
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : ${result6}
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
            name:'数量',
            type:'bar',
            data:${result7}
           
        }
    ]
};
            var myChart4=ec.init(document.getElementById('main4'));
            myChart4.setOption(option4);          
        }

    );

    </script>

