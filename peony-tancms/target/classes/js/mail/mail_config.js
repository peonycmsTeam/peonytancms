var pageContext;
$(function() {
	    pageContext = $("#pageContext").val();
		$("#mailConfigForm").ajaxForm();
		$("#mailConfigForm").Validform({
			btnSubmit:"#subBtn",
			tiptype:1,
			ajaxPost:true,
			beforeSubmit: function(curform){
				var options = {
					url: pageContext + "/mail/updateMailConfig",
					type: 'POST',
					async: false,
			     	success: function(data) {
			     		jBox.tip('设置成功','success');
			     	},
					error: function(){
					}
				};
				curform.ajaxSubmit(options);
				
				return false;
			}
		});
	});


function view(name){
	jBox("iframe:"+pageContext+"/template/mail/"+name+".html", {
		    title: "预览",
		    width: 930,
		    height: 600,
			top: '15%',
			id:'showMailTemp',
		    buttons: {}
		});
}