$(function() {
	var pageContext = $("#pageContext").val();
	$("#addBriefreport").Validform({
		btnSubmit:"#addBtn",
		tiptype:1,
		ajaxPost:true,
		postonce:true,
		beforeSubmit: function(curform){
			var options = {
				url: pageContext + "/briefreport/saveBriefreport",
				type: 'post',
				data: null,
				dataType: 'script',
				async: false,
		     	success: function() {
		     		parent.showPop();
		     		$(window.parent.document).find("#search").submit();
		     	},
				error: function(){
					jBox.tip('保存失败', 'error');
				}
			};
			$("#name").attr("value",$("#name").val().replace(/\s+/g, ""));
			curform.ajaxSubmit(options);
			
			return false;
		}
	});
	
});

function closeJbox(){
	window.parent.showPop();
}