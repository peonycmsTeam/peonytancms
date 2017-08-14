
  	<script type="text/javascript">
  	
var nodes = [];
var links = [];
var constMaxDepth = 2;

var constMaxRadius = 10;
var constMinRadius = 2;

function rangeRandom(min, max) {
    return Math.random() * (max - min) + min;
}

function createRandomNode(depth,val) {
    var node = {
        name : 'NODE_' + nodes.length,
        value : val,
        // Custom properties
        id : nodes.length,
        depth : depth,
        category : 0
    }
    nodes.push(node);

    return node;
}
function First(){
	 var rootNode = {
        name : 'ROOT',
        value : 50,
        // Custom properties
        id : 0,
        depth : 0,
        category : 0
    }
    nodes.push(rootNode);
	}
function Second(){
  for(var i=1;i<=5;i++){
  var childNode = createRandomNode(1,24);
    links.push({
                source : 0,
                target : childNode.id,
                weight : 1
            });

  }
   }
function Third(){
  for(var i=1;i<=5;i++){
  for(var j=1;j<=5;j++){
   var nchildNode = createRandomNode(2,2);
     links.push({
                source : i,
                target : nchildNode.id,
                weight : 1
            });
  }
  }
 }
function forceMockThreeData() {
  First();
   Second();
   Third();
}

forceMockThreeData();
        require(
        [
             
                'echarts',
                'echarts/chart/force',
                'echarts/chart/chord'
            
        ],
		function (ec) {
			option = {
    
    tooltip : {
        trigger: 'item',
        formatter: '{a} : {b}'
    },
    toolbox: {
        show : true,
        feature : {
            restore : {show: true},
            magicType: {show: false, type: ['force', 'chord']},
            saveAsImage : {show: true}
        }
    },
   
    series : [
        {
            type:'force',
            name : "Force tree",
            
            categories : [
             
                {
                    name: '名称'
                }
            ],
            itemStyle: {
                normal: {
                    label: {
                        show: true
                    },
                    nodeStyle : {
                        brushType : 'both',
                        borderColor : 'rgba(255,215,0,0.6)',
                        borderWidth : 1
                    }
                }
            },
            minRadius : 10,
            maxRadius : 30,
            coolDown: 0.995,
            steps: 10,
            nodes : ${nodes},
            links : ${links},
            steps: 1
        }
    ]
};
                    
		var myChart2=ec.init(document.getElementById('hotWordAnalysis'));
    	myChart2.setOption(option);
		});
    </script>

        