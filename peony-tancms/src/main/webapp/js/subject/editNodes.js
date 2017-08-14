$(function() {
	var pageContext = $("#pageContext").val();
	$("#editNodeform").Validform({
		btnSubmit:"#editBtn",
		tiptype:1,
		ajaxPost:true,
		postonce:true,
		beforeSubmit: function(curform){
			var options = {
				url: pageContext + "/subject/updateNodes",
				type: 'post',
				data: null,
				dataType: 'script',
				async: false,
		     	success: function() {
		     		parent.showPop();
		     		 parent.location.reload();
		     	},
				error: function(){
					jBox.info('保存失败', '提示');
				}
			};
			curform.ajaxSubmit(options);
			
			return false;
		}
	});
});