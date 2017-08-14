var pageContext;
var currentNode;
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
	$.ajaxSetup ({
	    cache: false //关闭AJAX相应的缓存
	});
	function configTree() {
		setting = {
			async : {
				enable : true,
				url :  pageContext + "/event/findChildren",
				autoParam : [ "id" ]
			},
			view : {
				expandSpeed : ""
			},
			callback : {
				onAsyncSuccess: zTreeOnAsyncSuccess,
				onClick : function(event, treeId, treeNode) {
					currentNode=treeNode;
					loadView();
				}
			}
		};
		
		return setting;
	}
	$.fn.zTree.init($("#eventTree"), configTree());
	loadView();
});
function loadView(){
	$("#viewDiv").html('<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	if(currentNode){
		$("#viewDiv").load(pageContext + "/focus/toListFocusPage?regionID="+currentNode.id);
	}else{
		$("#viewDiv").load(pageContext + "/focus/toListFocusPage?regionID="+$("#firstRegionID").val());
	}
}
function  zTreeOnAsyncSuccess(){
	var treeObj = $.fn.zTree.getZTreeObj("eventTree")
	var node = treeObj.getNodeByParam("id",$("#firstRegionID").val());
	treeObj.expandNode(node, true, false, true, false);
}
/**
 * 表单提交
 * @return
 */
function mySubmit(){
	//var conditions ="&eventid="+$("#eventid").val()+"&polarityorientation="+$("#polarityorientation").val()+"&timeMethod="+$("#timeMethod").val()+"&pagesize="+$("#pagesize").val()+"&type="+$("#type").val()+"&pageNo="+$("#pageNo").val();
	var regionID = $("#firstRegionID").val();
	if(currentNode!=undefined){
		regionID = currentNode.id;
	}
	$("#viewDiv").load(pageContext + "/focus/toListFocusPage?regionID="+regionID+"&pageNo="+$("#pageNo").val());
}
/**
 * 设置页数
 * @param i
 * @return
 */
function setPageNo(i){
	$("#pageNo").val(i);
	$("#viewDiv").html('<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	mySubmit();
}
function del(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.ajax({
				type : "post",
				url : pageContext + "/focus/deleteFocus",
				dataType : "json",
				data : {
					id : id
				},
				success : function(data) {
					jBox.tip("删除成功", "success");
					window.location.reload();
				},
				error : function() {
					jBox.info("删除失败", "提示");
				}
			});
		}
		return true;
	};

	$.jBox.confirm("您确定要删除吗？", "提示", submit);
}


/**
 * 修改专题
 */
function edit(id) {

	jBox("iframe:" + pageContext + "/focus/editFocus/" + id, {
		title : "修改专题",
		width : 500,
		id : "editDiv",
		height : 550,
		buttons : {}
	});
}

/**
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}
