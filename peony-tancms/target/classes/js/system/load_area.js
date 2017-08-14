
var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

var zNodes = $("#region").val();

var code;

function setCheck() {
	var zTree = $.fn.zTree.getZTreeObj("areaTree"),
	py = $("#py").attr("checked")? "p":"",
	sy = $("#sy").attr("checked")? "s":"",
	pn = $("#pn").attr("checked")? "p":"",
	sn = $("#sn").attr("checked")? "s":"",
	type = { "Y" : "ps", "N" :  "s" };
	zTree.setting.check.chkboxType = type;
}

$(document).ready(function(){
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
	testJson = eval("(" + zNodes + ")");  
	$.fn.zTree.init($("#areaTree"), setting, testJson);
	setCheck();
	$("#py").bind("change", setCheck);
	$("#sy").bind("change", setCheck);
	$("#pn").bind("change", setCheck);
	$("#sn").bind("change", setCheck);
});

function mySubmit(){
	var treeObj = $.fn.zTree.getZTreeObj("areaTree");
	var nodes = treeObj.getCheckedNodes(true);
	var result = "";
	if(nodes.length==0){
		window.setTimeout(function () { $.jBox.tip("请至少选择一个地域", 'error',1000); }, 100);
	}else{
		for(var i=0;i<nodes.length;i++){
			if(i==0){
				result = nodes[i].id;
			}else{
				result = result+","+nodes[i].id
			}
		}
		window.parent.setArea(result);
	}
}
