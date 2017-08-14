$(function() {
	var demo=$('#sendMail').Validform({
		btnSubmit : "#subBtn",
		tiptype : 1,
		ajaxPost : true,
		postonce : true,
		beforeSubmit : function(curform) {
			var ids=parent.getCheckVal();
			$("#ids").val(ids);
			$("#wait").css("display",'');
		},
	callback:function(data){
		if(data==0){
			parent.jBox.tip('发送失败', 'error');
		}else{
			parent.jBox.tip('发送成功', 'success');
		}
		parent.showPop(); // 关闭窗体
	}
	});
});
function test(){
	
	var ids=parent.getCheckVal();
	$("#ids").val(ids);
	var options = {
		url : pageContext + "/event/sendMail",
		type : 'post',
		data : null,
		dataType : 'script',
		async : false,
		success : function(data) {
			if(data==0){
				parent.jBox.tip('发送失败', 'error');
			}else{
				parent.jBox.tip('发送成功', 'success');
			}
			parent.showPop(); // 关闭窗体
		},
		error : function() {
			parent.jBox.tip('发送失败', 'error');
		}
	};
	$("#wait").css("display",'');
	curform.ajaxSubmit(options);
	return false;
}