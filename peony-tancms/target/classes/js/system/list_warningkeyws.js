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
 * 表单提交
 * @return
 */
function mySubmit(){
	var form = document.forms[0];
	form.action = pageContext+"/system/toListWarningkeyws";
	form.method="post";
	form.submit();
}
/**
 * 设置页数
 * @param i
 * @return
 */
function setPageNo(i){
	$("#pageNo").val(i);
	mySubmit();
}
/**
 * 执行删除操作
 * @return
 */
function deleteWarningkeyws(warningkeywsId){
	var params = {};
	params.warningkeywsId = warningkeywsId;
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/system/deleteWarningkeyws"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("信息删除成功", 'success',1000); }, 100);
	    				window.setTimeout(mySubmit,1000);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("信息删除失败", 'success',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定要删除当前预警关键词吗？", "提示", submit);
}

/**
 * 
 * @param id
 * @return
 */
function updateWarningkeyws(warningkeywsId){
	jBox("iframe:"+pageContext + "/system/toUpdateWarningkeyws/"+warningkeywsId, {
	    title: "修改预警关键词",
	    width: 600,
	    height: 780,
		top: '15%',
		id:'updateWarningkeyws',
	    buttons: {}
	});
}

/**
 * 
 * @param id
 * @return
 */
function addWarningkeyws(){
	jBox("iframe:"+pageContext + "/system/addWarningkeyws", {
	    title: "新增预警关键词",
	    width: 600,
	    height: 780,
		top: '15%',
		id:'addWarningkeyws',
	    buttons: {}
	});
}

/**
 * 关闭jbox弹窗
 * @return
 */
function closeJbox(){
	$.jBox.close(true);
}
/**
 * 根据id关闭jbox弹窗
 * @return
 */
function closeJbox(id){
	$.jBox.close(id);
}
/**
 * 关闭jbox同时提交表单
 * @return
 */
function closeAndsubmit(){
	closeJbox();
	mySubmit();
}
/**
 * 查看预警关键词设置
 */
function showWarningkeyws(warningkeywsId){
	jBox("iframe:"+pageContext + "/system/showWarningkeyws/"+warningkeywsId, {
	    title: "查看预警关键词设置",
	    width: 600,
	    height: 780,
		top: '15%',
		id:'showWarningkeyws',
	    buttons: {}
	});
}
