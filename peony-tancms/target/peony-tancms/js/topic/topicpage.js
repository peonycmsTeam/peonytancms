var pageContext;
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	});
});
function del(id) {
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.ajax({
				type : "post",
				url : pageContext + "/topicpage/deleteTopicPage",
				dataType : "json",
				data : {
					id : id,
					pageNo:$("#pageNo").val(),
					totalCount:$("#totalCount").val()
				},
				success : function(data) {
					jBox.tip("删除成功", "success");
					$("#pageNo").attr("value",data);
					mySubmit();
				},
				error : function() {
					jBox.info("删除失败", "提示");
				}
			});
		}
		return true;
	};

	$.jBox.confirm("您确定要删除吗？", "提示", submit);
}

/**
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}

/**
 * 全选
 * 
 * @param checkbox
 * @return
 */
function checkAll(checkbox) {
	$("input[name='chk_list']").prop("checked", $(checkbox).prop("checked"));
}
/**
 * 得到checkebox值
 * 
 * @return
 */
function getCheckVal() {
	var v = "";
	$("input[name='chk_list']:checked").each(function() {
		if (v == "") {
			v += this.value
		} else {
			v += "," + this.value
		}
	});
	return v;
}
/**
 * 删除所选项
 */
function delAll(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择导出项!', '提示');
	}else{
	var submit = function(v, h, f) {
		if (v == 'ok') {
			$.ajax({
				type : "post",
				url : pageContext + "/topicpage/deleteTopicPageByIds",
				dataType : "json",
				data : {
					ids : ids,
					pageNo:$("#pageNo").val(),
					totalCount:$("#totalCount").val()
				},
				success : function(data) {
					jBox.tip("删除成功", "success");
					$("#pageNo").attr("value",data);
					mySubmit();
				},
				error : function() {
					jBox.info("删除失败", "提示");
				}
			});
		}
		return true;
	};

	$.jBox.confirm("您确定要删除所选项吗？", "提示", submit);
	}
}
/**
 * 表单提交
 * 
 * @return
 */
function mySubmit() {
	var form = document.forms[0];
	form.action = pageContext + "/topicpage/listTopicPage";
	
	form.method = "post";
	form.submit();
}
/**
 * 导出
 */
function exportTopic(){
	var val=$("#export").val();
	$("#export").val('0');
	if(val=='1'||val=='3'){
		var ids=getCheckVal();
		if(ids.length==0){
			$.jBox.alert('请选择导出项!', '提示');
		}else{
			if(val=='1'){
				parent.location.href=pageContext+"/topicpage/downloadByIds/"+ids;
			}else{
				parent.location.href=pageContext+"/topicpage/downloadExcelByIds/"+ids;
			}
		}
	}
	if(val=='2'||val=='4'){
		jBox("iframe:"+pageContext + "/topicpage/toExportByTime/"+val, {
		    title: "选择导出时间",
		    width: 600,
		    height: 184,
			top: '15%',
			id:'toExportByTime',
		    buttons: {}
		});
	}
}
/**
 * 按选择时间导出
 */
function selectTime(val){
	var btime=$("#stime").val();
	var etime=$("#etime").val();
	if(btime==''){
		jBox.info("开始时间不能为空!", "提示");
	}else{
		if(etime==''){
			jBox.info("结束时间不能为空!", "提示");
		}else{
			var start=new Date(btime.replace("-", "/").replace("-", "/"));  
			var end=new Date(etime.replace("-", "/").replace("-", "/"));  
			 if(end<start){  
				 jBox.info("结束日期要大于开始日期!", "提示");
			    } else{
			    	parent.showPop();
			    	$("#exportStime",window.parent.document).val(btime);
			    	$("#exportEtime",window.parent.document).val(etime);
			    	var form = parent.document.forms[0];
			    	if(val=='2'){
			    		form.action = pageContext+"/topicpage/downloadBySelectTime";
			    	}else{
			    		form.action = pageContext+"/topicpage/downloadExcelBySelectTime";
			    	}
			    	form.method="post";
			    	form.submit();
			    	form.action = pageContext + "/topicpage/listTopicPage";
//			    	alert(pageContext+"/topicpage/downloadBySelectTime?stime="+btime+"&etime="+etime+"&topicid="+topicid);
//			    	window.parent.location.href=pageContext+"/topicpage/downloadBySelectTime?stime="+btime+"&etime="+etime+"&topicid="+topicid;
			    }
		}
	}
}