var pageContext ;
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
})

/**
 * 跳转到添加简报页面
 */
function toAddBriefreport(){
	jBox("iframe:" + pageContext + "/briefreport/toAddBriefreport", {
		title : "添加简报",
		width : 450,
		height : 218,
		top : '25%',
		id : 'addBriefreport',
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
/**
 * 跳转到修改简报页面
 */
function toEditBriefreport(briefreportId){
	jBox("iframe:" + pageContext + "/briefreport/toEditBriefreport/"+briefreportId, {
		title : "编辑简报",
		width : 450,
		height : 218,
		top : '25%',
		id : 'editBriefreport',
		buttons : {}
	});
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
 * 删除简报
 * @param briefreportId
 */
function delBriefreport(briefreportId){
	var params = {};
	params.briefreportId = briefreportId;
	params.pageNo=$("#pageNo").val();
	params.totalCount=$("#totalCount").val();
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	showPop();
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/briefreport/delBriefreport"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data.state){
	    				$("#pageNo").attr("value",data.pageNo);
	    				$("#search").submit();
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("删除失败", 'error',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定要删除该简报吗？", "提示", submit);
}