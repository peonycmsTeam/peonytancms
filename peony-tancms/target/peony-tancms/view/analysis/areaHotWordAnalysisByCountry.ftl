
      <script src=" ${request.getContextPath()}/js/utils/newEcharts/china-main-city-map.js"></script>

    
    <script type="text/javascript">
    
    require.config({
    paths:{ 
        echarts:'echarts',
        'echarts/chart/map':'${request.getContextPath()}/js/utils/echarts/echarts-map'
       
    }
    });
    require(
        [
            'echarts',
            'echarts/chart/map',
          
        ],


    function(ec) {
var mapGeoData = require('echarts/util/mapData/params');
var curIndx = 0;
var mapType = [];
console.log(mapGeoData)
for (var city in cityMap) {
    mapType.push(city);
    // 自定义扩展图表类型
    mapGeoData.params[city] = {
        getGeoJson: (function (c) {
        	   var geoJsonName = cityMap[c];
            return function (callback) {
            		
                $.getJSON('${request.getContextPath()}/js/utils/newEcharts/citysJson/' + geoJsonName + '.json', callback);
            }
        })(city)
    }
}
    var myChart = ec.init(document.getElementById('areaHotWordAnalysis'));
    myChart.setOption({
    title: {
        text : '${area}',
        
        subtext : ''
    },
    tooltip : {
        trigger: 'item',
        formatter: function (params,ticket,callback){
        var area=params[1];
        var words;
        
        <#list arealist as area>
        	if(params[1]=="${area}"){
        		words=params[1]+':'+"${map[area]}";
        	}
        </#list>
        	return words;
        	
        	}
    },
    series : [
        {
            name: '',
            type: 'map',
            mapType: '${area}',
            selectedMode : 'single',
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
            	 <#list arealist as area>
        			{name: "${area}",value: "${map[area]}"},
        		  </#list>
            ]
        }
    ]
            });
        }
    );
    </script>
