var pageContext;
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
});
function del(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.ajax({
				type : "post",
				url : pageContext + "/topic/deleteTopic",
				dataType : "json",
				data : {
					id : id,
					totalCount:$("#totalCount").val(),
	    			pageNo:$("#pageNo").val()
				},
				success : function(data) {
					if(data.state){
						jBox.tip("删除成功", "success");
						$("#pageNo").attr("value",data.pageNo);
						$("#search").submit();
					}else{
						jBox.tip("删除失败", "error");
					}
					
				},
				error : function() {
					jBox.tip("删除失败", "error");
				}
			});
		}
		return true;
	};

	$.jBox.confirm("您确定要删除吗？", "提示", submit);
}

/**
 * 添加专题
 */
function add() {
	$.ajax({
		type : "post",
		url : pageContext + "/topic/isTopicEnough",
		dataType : "json",
		data : {},
		success : function(data) {
			if(data<3){
			jBox("iframe:" + pageContext + "/topic/addTopic", {
				title : "添加专题",
				width : 500,
				id : "editDiv",
				height : 645,
				buttons : {}
			});
			}else{
				jBox.info("已经到达添加专题总数!", "提示");
			}
		},
		error : function() {
		}
	});
	
}
/**
 * 修改专题
 */
function edit(id) {

	jBox("iframe:" + pageContext + "/topic/editTopic/" + id, {
		title : "修改专题",
		width : 500,
		id : "editDiv",
		height : 645,
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