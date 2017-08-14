$(function() {
	var pageContext = $("#pageContext").val();
	var num=1;
$("#addAttention").Validform({
		btnSubmit:"#addBtn",
		tiptype:1,
		ajaxPost:true,
		postonce:true,
		beforeSubmit: function(curform){
			var options = {
				url: pageContext + "/attention/saveAttention",
				type: 'post',
				data: null,
				dataType: 'script',
				async: false,
		     	success: function() {
		     		closeJbox();
		     		$(window.parent.document).find("#search").submit();
		     	},
				error: function(){
					jBox.tip('保存失败', 'error');
				}
			};
			
				if(num==1){
					num=2;
				$("#name").attr("value",$("#name").val().replace(/\s+/g, ""));
				curform.ajaxSubmit(options);
				}
						
			return false;
		}
	});
});

function closeJbox(){
	window.parent.showPop();
}