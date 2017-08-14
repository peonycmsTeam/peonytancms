$(function() {
	var pageContext = $("#pageContext").val();
	$('#topicEditForm').ajaxForm();
	$('#topicEditForm').Validform({
		btnSubmit : "#subBtn",
		tiptype : 1,
		ajaxPost : true,
		postonce : true,
		beforeSubmit : function(curform) {
			var options = {
				url : pageContext + "/topic/updateTopic",
				type : 'post',
				data : null,
				dataType : 'script',
				async : false,
				success : function() {
					parent.jBox.tip('保存成功', 'success');

					parent.showPop(); // 关闭窗体
					$(window.parent.document).find("#search").submit();
				},
				error : function() {
					parent.jBox.tip('保存失败', 'error');
					parent.showPop(); // 关闭窗体
				}
			};
			var mainKeywords=jQuery.trim($("#mainKeywords").val()).split(/\s+/);
			var deputyKeywords=jQuery.trim($("#deputyKeywords").val()).split(/\s+/);
			var area=jQuery.trim($("#area").val()).split(/\s+/);
			
			if((jQuery.trim($("#mainKeywords").val())==0&&(jQuery.trim($("#deputyKeywords").val()).length==0||jQuery.trim($("#area").val()).length==0))
					||(jQuery.trim($("#deputyKeywords").val())==0&&(jQuery.trim($("#mainKeywords").val()).length==0||jQuery.trim($("#area").val()).length==0))
					||(jQuery.trim($("#area").val())==0&&(jQuery.trim($("#mainKeywords").val()).length==0||jQuery.trim($("#deputyKeywords").val()).length==0))
					){
				parent.jBox.info("地域关键词、事件关键词、主体关键词三者不能同时为空!", "提示");
			}else{
			if(mainKeywords.length<3){
				if(deputyKeywords.length<3){
					if(area.length<3){
						$("#wait").css("display",'');
						$("#subBtn").attr('disabled',true);
						curform.ajaxSubmit(options);
					}else{
						parent.jBox.info("地域关键词个数不能超过两个!", "提示");
						}
				}else{
					parent.jBox.info("事件关键词个数不能超过两个!", "提示");
				}
			}else{
				parent.jBox.info("主题关键词个数不能超过两个!", "提示");
				}
			}
			return false;
		}
	});
});
