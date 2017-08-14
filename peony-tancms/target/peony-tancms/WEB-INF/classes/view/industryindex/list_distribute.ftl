  <div id="distribute" style="width:600px;height:400px;"></div>
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
    		'echarts/chart/scatter'

        ],

        function (ec1) {

        
       var  option = {
      
     tooltip : {
        trigger: 'axis',
        showDelay : 0,
        axisPointer:{
            show: true,
            type : 'cross',
            lineStyle: {
                type : 'dashed',
                width : 1
            }
        }
    },
    legend: {
        data:['${eventname0}','${eventname1}','${eventname2}',
        '${eventname3}','${eventname4}','${eventname5}','${eventname6}',
        '${eventname7}','${eventname8}','${eventname9}']
    },
    
     xAxis : [
        {
            type : 'value',
            splitNumber: 4,
            scale: true
        }
    ],
    yAxis : [
        {
            type : 'value',
            splitNumber: 4,
            scale: true
        }
    ],
    
   series : [
        {
            name:'${eventname0}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex0?string("#.##")},${sensitiveIndex0?string("#.##")}, ${focusIndex0?string("#.##")}] ]
           
        },
         {
            name:'${eventname1}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex1?string("#.##")},${sensitiveIndex1?string("#.##")}, ${focusIndex1?string("#.##")}] ]
           
        },
        
        {
            name:'${eventname2}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex2?string("#.##")},${sensitiveIndex2?string("#.##")}, ${focusIndex2?string("#.##")}] ]
           
        },
        {
            name:'${eventname3}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex3?string("#.##")},${sensitiveIndex3?string("#.##")}, ${focusIndex3?string("#.##")}] ]
           
        },
         {
            name:'${eventname4}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex4?string("#.##")},${sensitiveIndex4?string("#.##")}, ${focusIndex4?string("#.##")}] ]
           
        },
        {
            name:'${eventname5}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex5?string("#.##")},${sensitiveIndex5?string("#.##")}, ${focusIndex5?string("#.##")}] ]
           
        },
        {
            name:'${eventname6}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex6?string("#.##")},${sensitiveIndex6?string("#.##")}, ${focusIndex6?string("#.##")}] ]
           
        },
        {
            name:'${eventname7}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex7?string("#.##")},${sensitiveIndex7?string("#.##")}, ${focusIndex7?string("#.##")}] ]
           
        },
        {
            name:'${eventname8}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex8?string("#.##")},${sensitiveIndex8?string("#.##")}, ${focusIndex8?string("#.##")}] ]
           
        },
        {
            name:'${eventname9}',
            type:'scatter',
            symbolSize: function (value){
                return Math.round(value[2] / 5);
            },
            data: [[${heatIndex9?string("#.##")},${sensitiveIndex9?string("#.##")}, ${focusIndex9?string("#.##")}] ]
           
        }
    ]
    };

			  var myChart=ec1.init(document.getElementById('distribute'));
              myChart.setOption(option); 
 			 }
    );

    </script>

