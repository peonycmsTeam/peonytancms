/**
 * 全选
 */
function checkAll(){
	$("input[name='checkbox']").prop("checked", $(checkbox).prop("checked"));
}
/**
 * 得到checkebox值
 * @return
 */
function getCheckVal() {
	var v = "";
	$("input[name='checkbox']:checked").each(function() {
		if (v == "") {
			v += this.value
		} else {
			v += "," + this.value
		}
	});
	return v;
}
/**
 * 点击删除按钮
 */
function delAll(){
	var ids=getCheckVal();
	var pageContext = $("#pageContext").val();
	if(ids.length==0){
		$.jBox.alert('请选择要删除的邮件!', '提示');
	}else{
		var submit = function (v, h, f) {
		    if (v == 'ok') {
		    	$.jBox.close(true);
		    	$.ajax({
		    		type : "post",
		    		url : pageContext + "/mail/delMail",
		    		dataType : "json",
		    		data : {
		    			mailId:ids,
		    			pageNo:$("#pageNo").val(),
						totalCount:$("#totalCount").val()
		    		},
		    		success : function(data) {
		    			if(data.state){
		    				jBox.tip("删除成功", "success");
		    				$("#pageNo").attr("value",data.pageNo);
		    				$("#search").submit();
		    			}else{
		    				jBox.tip("删除失败", "error");
		    			}
		    		},
		    		error : function() {
		    			jBox.tip("删除失败", "提示");
		    		}
		    	});
		    }
		    else if (v == 'cancel') {
		    }
		    return true; 
		};
		jBox.confirm("您确定要删除所选邮件吗？", "提示", submit);
	}
}
/**
 * 删除邮件
 */
function delMail(mailId){
	var pageContext = $("#pageContext").val();
	
		var submit = function (v, h, f) {
		    if (v == 'ok') {
		    	$.jBox.close(true);
		    	$.ajax({
		    		type : "post",
		    		url : pageContext + "/mail/delMail",
		    		dataType : "json",
		    		data : {
		    			mailId:mailId,
		    			pageNo:$("#pageNo").val(),
						totalCount:$("#totalCount").val()
		    		},
		    		success : function(data) {
		    			if(data.state){
		    				jBox.tip("删除成功", "success");
		    				$("#pageNo").attr("value",data.pageNo);
		    				$("#search").submit();
		    			}else{
		    				jBox.tip("删除失败", "error");
		    			}
		    		},
		    		error : function() {
		    			jBox.tip("删除失败", "error");
		    		}
		    	});
		    }
		    else if (v == 'cancel') {
		    }
		    return true; 
		};
		jBox.confirm("您确定要删除所选邮件吗？", "提示", submit);
	
	
}

/**
 * 搜索提交表单
 */
function submitForm(){
	$("#title").attr("value",$.trim($("#title").val()));
	$("#search").submit();
}
/**
 * 点击分页
 * @param pageNo
 */
function selectPage(pageNo){
	$("#pageNo").attr("value",pageNo);
	$("#title").attr("value",$.trim($("#title").val()));
	$("#search").submit();
}