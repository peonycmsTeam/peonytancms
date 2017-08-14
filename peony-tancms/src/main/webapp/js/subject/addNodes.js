$(function() {
	var pageContext = $("#pageContext").val();
	$("#addNodeform").Validform({
		btnSubmit:"#addBtn",
		tiptype:1,
		ajaxPost:true,
		postonce:true,
		beforeSubmit: function(curform){
			var options = {
				url: pageContext + "/subject/saveNodes",
				type: 'post',
				data: null,
				dataType: 'script',
				async: false,
		     	success: function() {
		     		parent.showPop();
		     		 parent.location.reload();
		     	},
				error: function(){
					jBox.tip('保存失败', 'error');
				}
			};
			curform.ajaxSubmit(options);
			
			return false;
		}
	});
});
