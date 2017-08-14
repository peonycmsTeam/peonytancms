  <div id="mediaconut" style="width:600px;height:400px;"></div>
  <div id="mediaconut3" style="width:600px;height:400px;"></div>
    <div id="mediaconut2" style="width:600px;height:400px;"></div>
  <div id="mediaconut1" style="width:600px;height:400px;"></div>
  
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
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)",
        enterable:true
    },
    legend: {
        orient : 'vertical',
        x : 'left',
        data:['新闻','微信','微博','博客','报刊','论坛','twitter']
    },
    
    calculable : true,
    series : [
        {
            name:'访问来源',
            type:'pie',
            radius : '45%',
            center: ['40%', '50%'],
            data:[
                {value:${countmumber0}, name:'新闻'},
                {value:${countmumber1}, name:'论坛'},
                {value:${countmumber2}, name:'微博'},
                {value:${countmumber3}, name:'博客'},
                {value:${countmumber4}, name:'报刊'},
                {value:${countmumber5}, name:'twitter'},
                {value:${countmumber6}, name:'微信'}
            ]
        }
    ]
    };
    
	  optiontwo = {
    	title : {
        text: '影响力:博客TOP10',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['']
    },
    
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.01]
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['${webSites9}','${webSites8}','${webSites7}','${webSites6}',
            '${webSites5}','${webSites4}','${webSites3}','${webSites2}','${webSites1}','${webSites0}']
        }
    ],
    series : [
        {
            name:'',
            type:'bar',
            itemStyle: {
                normal: {
                    label : {
                        show: true,
                    }
                }
            },
            data:[${countmumbers9},${countmumbers8},${countmumbers7},${countmumbers6}, ${countmumbers5},
             ${countmumbers4},${countmumbers3},${countmumbers2}, ${countmumbers1},${countmumbers0}]
        }
    ]
};

option3 = {
    title : {
        text: '影响力:论坛TOP10',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['']
    },
    
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.01]
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['${webSiteb9}','${webSiteb8}','${webSiteb7}','${webSiteb6}',
            '${webSiteb5}','${webSiteb4}','${webSiteb3}','${webSiteb2}','${webSiteb1}','${webSiteb0}']
        }
    ],
    series : [
        {
            name:'',
            type:'bar',
            itemStyle: {
                normal: {
                    label : {
                        show: true,
                    }
                }
            },
            data:[${countmumberb9},${countmumberb8},${countmumberb7},${countmumberb6}, ${countmumberb5},
             ${countmumberb4},${countmumberb3},${countmumberb2}, ${countmumberb1},${countmumberb0}]
        }
    ]
};

option4 = {
    title : {
        text: '影响力:新闻TOP10',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['']
    },
    
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.01]
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : ['${webSitec9}','${webSitec8}','${webSitec7}','${webSitec6}',
            '${webSitec5}','${webSitec4}','${webSitec3}','${webSitec2}','${webSitec1}','${webSitec0}']
        }
    ],
    series : [
        {
            name:'',
            type:'bar',
            itemStyle: {
                normal: {
                    label : {
                        show: true,
                    }
                }
            },
            data:[${countmumberc9},${countmumberc8},${countmumberc7},${countmumberc6}, ${countmumberc5},
             ${countmumberc4},${countmumberc3},${countmumberc2}, ${countmumberc1},${countmumberc0}]
        }
    ]
};
    

	var myChart=ec1.init(document.getElementById('mediaconut'));
            myChart.setOption(option);
 	var myChart2=ec1.init(document.getElementById('mediaconut1'));
            myChart2.setOption(optiontwo);
    var myChart2=ec1.init(document.getElementById('mediaconut2'));
            myChart2.setOption(option3);
    var myChart2=ec1.init(document.getElementById('mediaconut3'));
            myChart2.setOption(option4);
        }

    );


    </script>

