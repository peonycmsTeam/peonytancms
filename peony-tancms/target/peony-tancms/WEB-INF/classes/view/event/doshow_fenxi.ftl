<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<form name="form1" id="form1" action="${request.getContextPath()}/event/toListEvent" method="post" >
<input type="hidden" id="pageContext" name="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="regionID" name="regionID" value="${regionID}"/>
<input type="hidden" id="eventid" name="eventid" value="${eventid}"/>
<input type="hidden" id="polarityorientation" name="polarityorientation" value=""/>
<input type="hidden" id="timeMethod" name="timeMethod" value=""/>
<input type="hidden" id="pagesize" name="pagesize" value=""/>
<input type="hidden" id="type" name="type" />
<input type="hidden" id="pageNo" name="pageNo" />
</form>
<div class="fenxileibiao">
  <div class="neirong" id="fenxi"> 
      	<div class="neirong"> 
      	<div class="k_silist2">
			<ul>
			<li ><div id="main1"style="width:320px; height:265px;"></div></li>
			<li><div id="main2"style="width:400px; height:265px;"></div></li>
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
       		option1 = {
       			title : {
		        text: '载体分布'
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
			            radius : '60%',
			            center: ['50%', 125],
			            data:${result1}
			        }
			    ]
			};
		var myChart1=ec.init(document.getElementById('main1'));
    	myChart1.setOption(option1);
    	
    	
 		option2 = {
		    title : {
		        text: 'Top5网站分布'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
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
		            data : ${result2}
		        }
		    ],
		    series : [
		        {
		            type:'bar',
		            data: ${result3}
		        }
		    ]
		};
        var myChart2=ec.init(document.getElementById('main2'));
        myChart2.setOption(option2);     
        
        
        
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
		            radius : '65%',
		            center: ['50%', 125],
		            data:${result4}
		        }
		    ]
		};
	    var myChart3=ec.init(document.getElementById('main3'));
	    myChart3.setOption(option3);     
		option4 = {
		    title : {
		        text: '媒体类型对比'
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    
		    toolbox: {
		        show : false,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            data : ['新闻','论坛','微博','博客','报刊','twiter','微信']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'负面',
		            type:'bar',
		            data:${result5}
		        },
		        {
		            name:'正面',
		            type:'bar',
		            data:${result6}
		        },
		      	{
		            name:'争议',
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
  </div>
  </div>
</div>

  
