$(function() {
	var pageContext = $("#pageContext").val();
	$("#sendMail").Validform({
		btnSubmit:"#sendBtn",
		tiptype:1,
		ajaxPost:true,
		postonce:true,
		beforeSubmit: function(curform){
			var options = {
				url: pageContext + "/mail/sendMail",
				type: 'post',
				data: null,
				dataType: 'script',
				async: false,
		     	success: function(data) {
		     		if(data=='1'){
		     			jBox.tip('发送成功', 'success');
		     		}else{
		     			jBox.tip('发送失败', 'error');
		     		}
		     	},
				error: function(){
					jBox.tip('发送失败', 'error');
				}
			};
			curform.ajaxSubmit(options);
			
			return false;
		}
	});
});