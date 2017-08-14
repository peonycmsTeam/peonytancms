$(function(){
var pageContext = $("#pageContext").val();
 $('#editPswForm').ajaxForm();
		$("#editPswForm").Validform({
			btnSubmit:"#subBtn",
			tiptype:1,
			ajaxPost:true,
			postonce:true,
			beforeSubmit: function(curform){
				var options = {
					url: pageContext + "/updatePassword",
					type: 'post',
					data: null,
					dataType: 'json',
					async: false,
			     	success: function(data) {
			     	if(data.status==1){
			     		parent.$.jBox.tip('修改成功', 'success');
			     		parent.$.jBox.close(true);
			     		}
			     	if(data.status==0){
			     		parent.$.jBox.tip('请先登录系统', 'error');
			     		parent.$.jBox.close(true);
			     	}	
					if(data.status==2){
						parent.$.jBox.tip('原始密码输入不正确', 'error');
			     		parent.$.jBox.close(true);
			     	}
			     	},
					error: function(){
						parent.$.jBox.tip('修改失败', 'error');
						parent.$.jBox.close(true);
					}
				};
				curform.ajaxSubmit(options);
				
				return false;
			}
		});

});
