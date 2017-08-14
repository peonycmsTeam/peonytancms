var pageContext ;
var currentNode;
function loadView(){
	if(currentNode){
		//timestamp为时间戳，避免走缓存
		$("#viewDiv").load(pageContext + "/system/toViewSubject?id="+currentNode.id+"&timestamp="+Date.parse(new Date()));
	}else{
		$("#viewDiv").load(pageContext + "/system/toViewSubject?id=1&timestamp="+Date.parse(new Date()));
	}
}
function  zTreeOnAsyncSuccess(){
	
	var treeObj = $.fn.zTree.getZTreeObj("subjectTree")
	var node = treeObj.getNodeByParam("id",1);
	treeObj.expandNode(node, true, false, true, false);
}
function refreshTree(){
	var zTree = $.fn.zTree.getZTreeObj("subjectTree");
//	var nodes = zTree.getSelectedNodes();
//	if (nodes.length>0) {
//		zTree.reAsyncChildNodes(nodes[0], "refresh",true);
//	}
	zTree.reAsyncChildNodes(null, "refresh");
}
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
	function configTree() {
		setting = {
			async : {
				enable : true,
				url :  pageContext + "/system/findChildren",
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
	$.fn.zTree.init($("#subjectTree"), configTree());
	loadView();
});



/**
 * 表单提交
 * @return
 */
function mySubmit(id){
	var form = document.forms[0];
	$("#subjectid").val(id);
	form.action = pageContext+"/system/toEditSubject";
	form.method="post";
	form.submit();
}

/**
 * 新增
 * @return
 */
function addSubject(){
	jBox("iframe:"+pageContext + "/system/toAddSubject", {
	    title: "添加定制舆情分类",
	    width: 600,
	    height: 900,
		top: '15%',
		id:'addSubject',
	    buttons: {}
	});
}

function closeIframe(iframeId){
	closeJbox(iframeId);
	mySubmit();
}
/**
 * 根据id关闭jbox弹窗
 * @return
 */
function closeJbox(id){
	$.jBox.close(id);
}
/**
 * 词库选词
 * @return
 */
function toShowBaseClass(){
	jBox("iframe:"+pageContext + "/system/toShowBaseClass", {
	    title: "请选择词库分类",
	    width: 600,
	    height: 276,
		top: '15%',
		id:'showBaseClass',
	    buttons: {}
	});
}

/**
 * 词库选词
 * @return
 */
function toShowAreaClass(){
	jBox("iframe:"+pageContext + "/system/toShowArea", {
	    title: "请选择您所关心的区域",
	    width: 500,
	    height: 400,
		top: '15%',
		id:'showBaseArea',
	    buttons: {}
	});
}
/**
 * 词库回显
 * @return
 */
function setBaseClassId(baseClassId){
	var params = {};
	params.baseClassId = baseClassId;
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/system/doFindBaseclassKeyws"),
		dataType : "text",
		data : params,
		success : function(data) {
			if(data != "" && data != null){
				$("#qxx").val(data);
				closeJbox("showBaseClass");
			}else{
				window.setTimeout(function () { $.jBox.tip("当前词库下没有词汇", 'error',1000); }, 100);
			}
		}
	});
}

/**
 * 词库回显
 * @return
 */
function setArea(regionId){
	var params = {};
	params.regionId = regionId;
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/system/doFindRegionKeyWord"),
		dataType : "text",
		data : params,
		success : function(data) {
			if(data != "" && data != null){
				$("#area").val(data);
				closeJbox("showBaseArea");
			}else{
				window.setTimeout(function () { $.jBox.tip("当前地域没有关键词", 'error',1000); }, 100);
			}
		}
	});
}

/**
 * 修改
 * @return
 */
function updateSubjectKeyWords(){
	var form = document.forms[0];
	form.action = pageContext+"/system/doUpdateSubjectKeyWords";
	form.method="post";
	form.submit();
}
/**
 * 删除
 * @return
 */
function deleteSubject(){
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	var form = document.forms[0];
	    	form.action = pageContext+"/system/doDeleteSubject";
	    	form.method="post";
	    	form.submit();
	    }else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定要删除当前定制舆情分类？", "提示", submit);
}
