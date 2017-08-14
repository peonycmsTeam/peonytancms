
var currentNode;
var pageContext;
$(function() {
	 pageContext = $("#pageContext").val();
});
function addruleout(obj){
	var trs=$("table#table tr");
	 var index=trs.index($(obj).closest("tr"));
	 var tr = '<tr><td ></td>	<td><textarea type="text" name="ruleoutKeywordsAppendTwo" id="ruleoutKeywordsAppendTwo"></textarea></td></tr>';
	 $("table tr:eq("+index+")").after(tr);
	 var tr = '<tr><td ></td>	<td><textarea type="text" name="ruleoutKeywordsAppendOne" id="ruleoutKeywordsAppendOne"></textarea></td></tr>';
	 $("table tr:eq("+index+")").after(tr);
	 $(obj).parent().html('');
}
/**
 * 显示该节点下的信息
 */
function loadView() {
	if (currentNode) {
		$.ajax({
			type : "post",
			url : pageContext + "/subject/isChildnodes/"+ currentNode.id,
			dataType : "json",
			data : {
				id : currentNode.id
			},
			success : function(data) {
				if(data.isChild){
				$('#viewframe').attr("src",pageContext + "/subject/editSubject/" + currentNode.id);  
				}else{
				$("#viewframe").attr("src",null);
				}
			},
			error : function() {
				jBox.info("查询失败", "提示");
			}
		});
		
	}else{
		jBox.info("请先选择节点", "提示");
	}
}
/**
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}
/**
 * 节点设为根节点
 */
function changeNode(){
	var pageContext = $("#pageContext").val();
	var id=$("#userkeywsId").val();
	$.ajax({
		type : "post",
		url : pageContext + "/subject/changeNode/"+ id,
		dataType : "json",
		data : {
			id : id
		},
		success : function(data) {
			jBox.info("修改成功", "提示");
			$('#addUserkeywsform')[0].reset() ;
		},
		error : function() {
			jBox.info("修改失败", "提示");
		}
	});
}
/**
 * 删除子节点
 */
function delNode(){
	var pageContext = $("#pageContext").val();
	if(currentNode){
	$.ajax({
		type : "post",
		url : pageContext + "/subject/delNode/"+ currentNode.id,
		dataType : "json",
		data : {
			id :currentNode.id
		},
		success : function(data) {
			if(data.hasChild){
				var zTree = $.fn.zTree.getZTreeObj("userkeywsTree");
				zTree.removeNode(currentNode);
				currentNode = null;
				$('#viewframe').attr("src","");  
				jBox.info("删除成功", "提示");
			}else{
				jBox.info("删除失败，请先删除该节点下的子节点", "提示");
			}
			
		},
		error : function() {
			jBox.info("删除失败", "提示");
		}
	});
	}else{
		jBox.info("请先选择节点", "提示");
	}
}
/**
 * 弹出添加节点框
 */
function addNodes(){
	if(currentNode){
	$.ajax({
		type : "post",
		url : pageContext + "/subject/addSubject/"+ currentNode.id ,
		dataType : "json",
		data : {},
		success : function(data) {
			if(data.hasKeywords){
				jBox.info("该节点下以有关键词，请先置为根节点再添加", "提示");
			}else{
				jBox("iframe:" + pageContext + "/subject/addSubjectKeywords/"+ currentNode.id , {
					title : "添加节点",
					width : 450,
					height : 300,
					top : '25%',
					id : 'addUserkeyws',
					buttons : {}
				});
			}
		},
		error : function() {
			jBox.info("查询失败", "提示");
		}
	});
	}else{
		jBox.info("请先选择节点", "提示");
	}
}
/**
 * 子节点添加数据
 
function savekeyws(){
	var userkeywsId=$('#userkeywsId').val();
	var area=$('#area').val();
	var mainKeyws=$('#mainKeyws').val();
	var deputKeyws=$('#deputyKeyws').val();
	var ruleoutKeyws=$('#ruleoutKeyws').val();
	$.ajax({
		type : "post",
		url : pageContext + "/subject/saveSubject",
		dataType : "json",
		data : {
			userkeywsId :userkeywsId,
			area:area,
			mainKeyws:mainKeyws,
			deputyKeyws:deputyKeyws,
			ruleoutKeyws:ruleoutKeyws
		},
		success : function(data) {
			 
			jBox.info("保存成功", "提示");
		},
		error : function() {
			jBox.info("保存失败", "提示");
		}
	});
}
*/
/**
 * 保存关键词
 */
function saveSubjectKeywords(){
	var area=$('#area').val();
	var userkeywsId=$('#userkeywsId').val();
	var mainKeywords=$('#mainKeywords').val();
	var ruleoutKeywords=$('#ruleoutKeywords').val();
	var ruleoutKeywordsAppendOne=$('#ruleoutKeywordsAppendOne').val();
	var ruleoutKeywordsAppendTwo=$('#ruleoutKeywordsAppendTwo').val();
	$.ajax({
		type : "post",
		url : pageContext + "/subject/saveSubject",
		dataType : "json",
		data : {
			area :area,
			userkeywsId:userkeywsId,
			mainKeywords:mainKeywords,
			ruleoutKeywords:ruleoutKeywords,
			ruleoutKeywordsAppendOne:ruleoutKeywordsAppendOne,
			ruleoutKeywordsAppendTwo:ruleoutKeywordsAppendTwo
		},
		success : function(data) {
		 
			jBox.info("保存成功", "提示");
		},
			error : function() {
			jBox.info("保存失败", "提示");
	}
	});
}

/**
 * 修改分类节点名称
 */
function editNodes(){
	if(currentNode){
		jBox("iframe:" + pageContext + "/subject/editNodes/"+ currentNode.id , {
			title : "添加节点",
			width : 450,
			height : 300,
			top : '25%',
			id : 'editNodes',
			buttons : {}
		});
	}else{
		jBox.info("请先选择节点", "提示");
	}
}
