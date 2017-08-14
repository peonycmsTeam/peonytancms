$(function() {
	var pageContext = $("#pageContext").val();
	$('#focusEditForm').ajaxForm();
	$('#focusEditForm').Validform({
		btnSubmit : "#subBtn",
		tiptype : 1,
		ajaxPost : true,
		postonce : true,
		beforeSubmit : function(curform) {
			var options = {
				url : pageContext + "/focus/updateFocus",
				type : 'post',
				data : null,
				dataType : 'script',
				async : false,
				success : function() {
					parent.jBox.tip('保存成功', 'success');

					parent.showPop(); // 关闭窗体
					window.location.reload();
				},
				error : function() {
					parent.jBox.tip('保存失败', 'error');
				}
			};
			curform.ajaxSubmit(options);

			return false;
		}
	});
});
