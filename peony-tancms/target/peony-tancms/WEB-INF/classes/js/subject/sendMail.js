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
			$("#subBtn").attr("disabled","disabled");
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