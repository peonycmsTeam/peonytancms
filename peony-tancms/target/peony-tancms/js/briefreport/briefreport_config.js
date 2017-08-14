var pageContext;
$(function() {
		pageContext = $("#pageContext").val();
		$("#reprotConfigForm").ajaxForm();
		$("#reprotConfigForm").Validform({
			btnSubmit:"#subBtn",
			tiptype:1,
			ajaxPost:true,
			beforeSubmit: function(curform){
				var options = {
					url: pageContext + "/briefreportconfig/updateBriefReportConfig",
					type: 'POST',
					async: false,
			     	success: function(data) {
			     		jBox.tip('设置成功','success');
			     	},
					error: function(){
						jBox.tip('设置失败','error');
					}
				};
				curform.ajaxSubmit(options);
				return false;
			}
		});
	});

function view(name){
	jBox("iframe:"+pageContext+"/template/briefreport/"+name+".html", {
		    title: "预览",
		    width: 930,
		    height: 470,
			top: '15%',
			id:'showMailTemp',
		    buttons: {}
		});
}