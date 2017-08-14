var pageContext;
$(function (){
	pageContext=$("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
});
/**
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}

/**
 * 点击分页
 * @param pageNo
 */
function selectPage(pageNo){
	$("#pageNo").attr("value",pageNo);
	$("#search").submit();
}
/**
 * 删除收藏info
 */
function del(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择要删除的收藏!', '提示');
	}else{
		var submit = function (v, h, f) {
			 if (v == 'ok') {
				 $.ajax({
						type : "post",
						url : pageContext + "/attention/delAttentionInfo",
						dataType : "json",
						data : {
							ids:ids,
							pageNo:$("#pageNo").val(),
							totalCount:$("#totalCount").val(),
							pageSize:$("#pageSize").val()
						},
						success : function(data) {
							if(data.state){
								$("#pageNo").attr("value",data.pageNo);
								$("#search").submit();
							}else{
								jBox.tip("删除失败", "success");
							}
						},
						error : function() {
							jBox.tip("删除失败", "error");
						}
					});
			 }else{}
		};
		jBox.confirm("您确定要删除选中的收藏吗？", "提示", submit);
	}
	
}
/**
 * 全选
 */
function checkAll(){
	$("input[name='checkbox']").prop("checked", $(checkbox).prop("checked"));
}
/**
 * 得到checkebox值
 * @return
 */
function getCheckVal() {
	var v = "";
	$("input[name='checkbox']:checked").each(function() {
		if (v == "") {
			v += this.value
		} else {
			v += "," + this.value
		}
	});
	return v;
}
/**
 * 搜索提交表单
 */
function submitForm(){
	$("#search").submit();
}
/**
 * 删除收藏夹
 */
function delAttention(attentionId){
	var submit = function (v, h, f) {
		 if (v == 'ok') {
		$.ajax({
			type : "post",
			url : pageContext + "/attention/delAttentionByAttentionId",
			dataType : "json",
			data : {
				attentionId:attentionId,
				pageNo:$("#pageNo").val(),
				totalCount:$("#totalCount").val()
			},
			success : function(data) {
				jBox.tip("删除成功", "success");
				if(data.state){
					$("#pageNo").attr("value",data.pageNo);
    				$("#search").submit();
				}
			},
			error : function() {
				jBox.tip("删除失败", "error");
			}
		});
	}else{}
	};
	jBox.confirm("您确定要删除该收藏夹吗？", "提示", submit);
}
/**
 * 弹出编辑窗口
 * @param attentionId
 */
function editAttention(attentionId){
	jBox("iframe:" + pageContext + "/attention/editAttentionByAttentionId/"+attentionId, {
		title : "编辑收藏夹",
		width : 450,
		height : 150,
		top : '25%',
		id : 'editAttention',
		buttons : {}
	});
}
/**
 * 弹出添加收藏夹窗口
 */
function toAddAttention(){
	jBox("iframe:" + pageContext + "/attention/toAddAttention", {
		title : "添加收藏夹",
		width : 450,
		height : 150,
		top : '25%',
		id : 'addAttention',
		buttons : {}
	});
}

