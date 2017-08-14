$(function() {
		var pageContext = $("#pageContext").val();
		$("#reprotConfigForm").ajaxForm();
		$("#reprotConfigForm").Validform({
			btnSubmit:"#subBtn",
			tiptype:1,
			ajaxPost:true,
			beforeSubmit: function(curform){
				var receive=getCheckVal();
				var options = {
					url: pageContext + "/reportconfig/updateReportConfig",
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


/**
 * 得到checkebox值
 * @return
 */
function getCheckVal() {
	var v = "";
	$("input[name='chk_list']:checked").each(function() {
		if (v == "") {
			v += this.value;
		} else {
			v += "," + this.value;
		}
	});
	return v;
}