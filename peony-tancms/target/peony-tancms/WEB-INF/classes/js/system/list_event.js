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
 * 全选
 * @param checkbox
 * @return
 */
function checkAll(checkbox){ 
	$("input[name='chk_list']").prop("checked",$(checkbox).prop("checked")); 
}
/**
 * 得到checkebox值
 * @return
 */
function getCheckVal(){
	var v="";
	$("input[name='chk_list']:checked").each(function() { 
		if(v==""){
		    v+=this.value
		}else{
		    v+=","+this.value
		}  
	}); 
 	return v;
}
/**
 * 新增舆情关系
 * @return
 */
function addSubscription(){
	var params = {};
	params.eventId = getCheckVal();
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/system/doAddSubscription"),
		dataType : "json",
		data : params,
		success : function(data) {
			if(data>0){
				window.setTimeout(function () { $.jBox.tip("添加成功", 'success',1000); }, 100);
			}else{
				window.setTimeout(function () { $.jBox.tip("添加失败", 'error',1000); }, 100);
			}
		}
	});
}
/**
 * 表单提交
 * @return
 */
function mySubmit(){
	var form = document.forms[0];
	form.action = pageContext+"/system/toEditEvent";
	form.method="post";
	form.submit();
}
