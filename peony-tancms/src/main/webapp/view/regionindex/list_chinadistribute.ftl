  <div id="chinadistribute" style="width:500px;height:300px;"></div>
    <div id="chinadistribute1" style="width:240px;height:400px;position: absolute;top:720px;left:1000px"></div>
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
    
    tooltip : {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        x:'left',
        data:['舆情综合指数']
    },
    dataRange: {
        min: 0,
        max: 100,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true
    },
    
    roamController: {
        show: true,
        x: 'right',
        mapTypeControl: {
            'china': true
        }
    },
    series : [
        {
            name: '舆情综合指数',
            type: 'map',
            mapType: 'china',
            roam: false,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
               {name: '${regionname0[0..1]}',value: ${compositeindex0?string("#.##")}},
               {name: '${regionname1[0..1]}',value: ${compositeindex1?string("#.##")}},
               {name: '${regionname2[0..1]}',value: ${compositeindex2?string("#.##")}},
               {name: '${regionname3[0..1]}',value: ${compositeindex3?string("#.##")}},
               {name: '${regionname4[0..1]}',value: ${compositeindex4?string("#.##")}},
               {name: '${regionname5[0..1]}',value: ${compositeindex5?string("#.##")}},
               {name: '${regionname6[0..1]}',value: ${compositeindex6?string("#.##")}},
               {name: '${regionname7[0..1]}',value: ${compositeindex7?string("#.##")}},
               {name: '${regionname8[0..1]}',value: ${compositeindex8?string("#.##")}},
               {name: '${regionname9[0..1]}',value: ${compositeindex9?string("#.##")}},
               {name: '${regionname10[0..1]}',value: ${compositeindex10?string("#.##")}},
               {name: '${regionname11[0..1]}',value: ${compositeindex11?string("#.##")}},
               {name: '${regionname12[0..1]}',value: ${compositeindex12?string("#.##")}},
               {name: '${regionname13[0..1]}',value: ${compositeindex13?string("#.##")}},
               {name: '${regionname14[0..1]}',value: ${compositeindex14?string("#.##")}},
               {name: '${regionname15[0..1]}',value: ${compositeindex15?string("#.##")}},
               {name: '${regionname16[0..1]}',value: ${compositeindex16?string("#.##")}},
               {name: '${regionname17[0..1]}',value: ${compositeindex17?string("#.##")}},
               {name: '${regionname18[0..1]}',value: ${compositeindex18?string("#.##")}},
               {name: '${regionname19[0..1]}',value: ${compositeindex19?string("#.##")}},
               {name: '${regionname20[0..1]}',value: ${compositeindex20?string("#.##")}},
               {name: '${regionname21[0..1]}',value: ${compositeindex21?string("#.##")}},
               {name: '${regionname22[0..1]}',value: ${compositeindex22?string("#.##")}},
               {name: '${regionname23[0..1]}',value: ${compositeindex23?string("#.##")}},
               {name: '${regionname24[0..1]}',value: ${compositeindex24?string("#.##")}},
               {name: '${regionname25[0..1]}',value: ${compositeindex25?string("#.##")}},
               {name: '${regionname26[0..1]}',value: ${compositeindex26?string("#.##")}},
               {name: '${regionname27[0..1]}',value: ${compositeindex27?string("#.##")}},
               {name: '${regionname28[0..1]}',value: ${compositeindex28?string("#.##")}},
               {name: '${regionname29[0..1]}',value: ${compositeindex29?string("#.##")}},
               {name: '${regionname30[0..1]}',value: ${compositeindex30?string("#.##")}}
               
              
               
            ]
        }
    ]
    		
    };
    
    optiontwo = {
     title : {
        text: 'TOP10',
        subtext: ''
    },
  
    tooltip : {
        trigger: 'axis'
    },
  grid :{
                x:45,
                y:40,
                x2:99,
                y2:90
                },
    
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
            },
        }
    ],
    yAxis : [
        {
            type : 'category',
            data :
            ['${regionnames9}','${regionnames8}','${regionnames7}','${regionnames6}','${regionnames5}',
            '${regionnames4}','${regionnames3}','${regionnames2}','${regionnames1}','${regionnames0}']
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
            data:['${compositeindexs9?string("#.##")}','${compositeindexs8?string("#.##")}',
           	 	  '${compositeindexs7?string("#.##")}','${compositeindexs6?string("#.##")}',
           		  '${compositeindexs5?string("#.##")}','${compositeindexs4?string("#.##")}',
           		  '${compositeindexs3?string("#.##")}','${compositeindexs2?string("#.##")}',
           		  '${compositeindexs1?string("#.##")}','${compositeindexs0?string("#.##")}']
            
        }
    ]
};

			  var myChart=ec1.init(document.getElementById('chinadistribute'));
              myChart.setOption(option); 
               var myChart=ec1.init(document.getElementById('chinadistribute1'));
              myChart.setOption(optiontwo); 
 			 }
    );

    </script>

