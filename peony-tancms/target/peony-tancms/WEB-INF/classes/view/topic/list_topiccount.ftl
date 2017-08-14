       
        <div id="count"style="height:500px;border:1px solid #ccc;padding:10px;"></div>
        <div class="blank5px"></div><p class="font_12px" id="fenxi">${fenxi}   </p>
        <div class="blank10px"></div>

  <script type="text/javascript">
		var img;
		var img1;
		var img2;
		var img3;
		var img4;
        require.config({

            paths: {
                echarts:'${request.getContextPath()}/js/utils/echarts/echarts',
 				'echarts/chart/pie':'${request.getContextPath()}/js/utils/echarts/echarts',
                'echarts/chart/bar':'${request.getContextPath()}/js/utils/echarts/echarts',
                'echarts/chart/line':'${request.getContextPath()}/js/utils/echarts/echarts'
            }

        });
		
		var that = this;
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
        text: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['全部','新闻','论坛','微博','博客','电子报刊','twitter','微信']
    },
    toolbox: {
        show : true,
        feature : {
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    animation : false,
    xAxis : [
        {
            type : 'category',
             axisLabel : {
                rotate : 40
            },
            boundaryGap : false,
            data : ${mytimes}
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} '
            },
            splitArea : {show : true}
        }
    ],
    series : [
        {
            name:'全部',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${zonghe},
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'新闻',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count1}
        },
        {
            name:'论坛',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count2}
        },
        {
            name:'微博',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count3}
        },
        {
            name:'博客',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count4}
        },
        {
            name:'电子报刊',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count5}
        },
        {
            name:'twitter',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count6}
        },
        {
            name:'微信',
            type:'line',
            itemStyle: {
                normal: {
                    lineStyle: {
                        shadowColor : 'rgba(0,0,0,0.4)',
                        shadowBlur: 5,
                        shadowOffsetX: 3,
                        shadowOffsetY: 3
                    }
                }
            },
            data:${count7}
        }
        
    ]
};
    var myChart=ec1.init(document.getElementById('count'));
            myChart.setOption(option);
  option1 = {
    legend: {
        orient : 'vertical',
        x : 'left',
        y : 'center',
        data:['新闻','论坛','微博','博客','电子报刊','twitter','微信']
   },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    calculable : true,
    animation : false,
        series : [
        {
            name:'访问来源',
            type:'pie',
            radius : ['50%', '70%'],
            center: ['60%', '50%'],
            itemStyle : {
                normal : {
                    label : {
                        show : false
                    },
                    labelLine : {
                        show : false
                    }
                },
                emphasis : {
                    label : {
                        show : true,
                        position : 'center',
                        textStyle : {
                            fontSize : '30',
                            fontWeight : 'bold'
                        }
                    }
                }
            },
            data:${result1}
        }
    ]
};

    var myChart1=ec1.init(document.getElementById('main1'));
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
    	animation : false,
   		 xAxis : [
       		 {
           		 type : 'value',
		         splitArea : {show : false}
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
            	data:${result3}
           
        	}
    	 ]
		};
            var myChart2=ec1.init(document.getElementById('main2'));
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
    	animation : false,
    	xAxis : [
        	{
        	 	type : 'value',
		     	splitArea : {show : false}
        	}
    	],
   		 yAxis : [
        	{
            	type : 'category',
            	data : ${result4}
        	}
    	],
    	series : [
        	{
        		color:'blue',
            	type:'bar',
            	itemStyle: {
             		normal: {
       					 color:'#87cefa'	
   					 }
            	},
            	data:${result5}
        	}
        	
    	]
	};
            var myChart3=ec1.init(document.getElementById('main3'));
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
    	animation : false,
    	xAxis : [
        	{
            	type : 'value',
            	splitArea : {show : false}
        	}
    	],
    	yAxis : [
        	{
        		type : 'category',
            	data : ${result6}
        	}
    	],
    	series : [
        	{
            	name:'数量',
            	type:'bar',
            	itemStyle: {
             		normal: {
       					 color:'#6495ed'	
   					 }
            	},
            	data:${result7}
        	}
    	]
	};
            var myChart4=ec1.init(document.getElementById('main4'));
            myChart4.setOption(option4);  
        
 	 img = myChart.getDataURL('png');//获取base64编码
	 img1 = myChart1.getDataURL('png');//获取base64编码
	 img2 = myChart2.getDataURL('jpeg');//获取base64编码
	 img3 = myChart3.getDataURL('jpeg');//获取base64编码
	 img4 = myChart4.getDataURL('jpeg');//获取base64编码
    });
    
    function saveImg(){
 		if(img==''){
 			var submit = function(v, h, f) {
				if (v == 'ok') {
					getImg();
				}
				return true;
			};

				$.jBox.confirm("该版本浏览器不支持图片导出，导出的文档内图片会没有图片，您确定要继续吗？", "提示", submit);
 			}else{
 				getImg();
 			}
 	}
 	
 function getImg(){
$("#img0").val(img);
$("#img1").val(img1);
$("#img2").val(img2);
$("#img3").val(img3);
$("#img4").val(img4);
$("#statistics").val($("#fenxi").html());
	  var form = document.forms[0];
	form.action =  $("#pageContext").val()+"/topicpage/getImg";
	form.method="post";
	form.submit();
}
    </script>
<div class="title_h1">
       <span class="t_tit t_tit_2">媒体分布分析
</span>
</div>
  <div class="k_silist2">
      <ul><li ><div id="main1"style="width:340px; height:265px;"></div></li>
      <li><div id="main2"style="width:380px; height:265px;"></div></li>
      <li><div id="main3"style="width:360px; height:265px;"></div></li>
      <li><div id="main4"style="width:360px; height:265px;"></div></li>
      </ul></div>
       
        <div class="blank5px"></div>
        <div class="blank10px"></div>
        <input type="hidden" id="img0" name="img0"/>
        <input type="hidden" id="img1" name="img1"/>
        <input type="hidden" id="img2" name="img2"/>
        <input type="hidden" id="img3" name="img3"/>
        <input type="hidden" id="img4" name="img4"/>
        <input type="hidden" id="statistics" name="statistics"/>