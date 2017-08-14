/**
 * 点击分页
 * @param pageNo
 */
function selectPage(pageNo){
	$("#pageNo").attr("value",pageNo);
	$("#search").submit();
}
/**
 * 搜索提交表单
 */
function submitForm(){
	$("#search").submit();
}
/**
 * 全选
 */
function checkAll(){
	$("input[name='checkbox']").prop("checked", $(checkbox).prop("checked"));
}
/**
 * 删除预警info
 */
function del(){
	var ids=getCheckVal();
	var pageContext = $("#pageContext").val();
	if(ids.length==0){
		$.jBox.alert('请选择要删除的预警!', '提示');
	}else{
		var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	showPop();
	    	$.ajax({
	    		type : "post",
	    		url : pageContext + "/warning/delWarning",
	    		dataType : "json",
	    		data : {
	    			ids:ids,
	    			totalCount:$("#totalCount").val(),
	    			pageSize:$("#pageSize").val(),
	    			pageNo:$("#pageNo").val()
	    		},
	    		success : function(data) {
	    			if(data.state){
	    				jBox.tip("删除成功", "success");
	    				$("#pageNo").attr("value",data.pageNo);
	    				$("#search").submit();
	    			}else{
	    				jBox.info("删除失败", "提示");
	    			}
	    		},
	    		error : function() {
	    			jBox.info("删除失败", "提示");
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定要删除所选预警吗？", "提示", submit);
	}
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
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}