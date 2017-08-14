var pageContext;
$(function (){
	pageContext=$("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
});

/**
 * 删除预警
 */
function del(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择要删除的预警!', '提示');
	}else{
		var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.jBox.close(true);
	    	$.ajax({
	    		type : "post",
	    		url : pageContext + "/warning/delWarning",
	    		dataType : "json",
	    		data : {
	    			ids:ids,
	    			totalCount:$("#totalCount").val(),
	    			pageSize:$("#pageSize").val(),
	    			pageNo:$("#pageNo").val()
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
	jBox.confirm("您确定要删除所选预警吗？", "提示", submit);
	}
}

/**
 * 导出
 */
function exportWarning(){
	var val=$("#export").val();
	$("#export").val('0');
	if(val=='1'||val=='3'){
		var ids=getCheckVal();
		if(ids.length==0){
			$.jBox.alert('请选择导出项!', '提示');
		}else{
			if(val=='1'){
				parent.location.href=pageContext+"/warning/downloadByIds/"+ids;
			}else{
				parent.location.href=pageContext+"/warning/downloadExcelByIds/"+ids;
			}
		}
	}
	if(val=='2'||val=='4'){
		jBox("iframe:"+pageContext + "/warning/toSelectTime/"+val, {
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
			    	if(val=='2'){
			    		parent.location.href=pageContext+"/warning/downloadBySelectTime/?btime="+btime+"&"+"etime="+etime;
			    	}else{
			    		parent.location.href=pageContext+"/warning/downloadExcelBySelectTime/?btime="+btime+"&"+"etime="+etime;
			    	}
			    	}
		}
	}
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
 * 页面添加操作
 * @return
 */
function addOperation(obj){
	var operation = obj.value;
	var warningId = obj.id;
	 if(operation==2){
		//加入专题
		addTopic(warningId);
	}else if(operation==3){
		//加入收藏
		showAttention(warningId);
	}else if(operation==4){
		//加入简报
		showBriefreport(warningId);
	}else{
		return false;
	}
}
/**
 * 添加预警操作
 */
function addWarning(briefreportInfoId){
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/warning/saveWarningByBriefreportInfoId/"+briefreportInfoId),
		dataType : "json",
		data : null,
		success : function(data) {
			if(data>0){
				window.setTimeout(function () { $.jBox.tip("添加预警成功", 'success',1000); }, 100);
			}else{
				window.setTimeout(function () { $.jBox.tip("添加预警失败", 'success',1000); }, 100);
			}
		}
	});
}
/**
 * 添加专题(等待树涛接口)
 */
function addTopic(pageid){
	$.ajax({
		type : "post",
		url : pageContext + "/topic/isTopicEnough",
		dataType : "json",
		data : {},
		success : function(data) {
			if(data<3){
				jBox("iframe:" + pageContext+"/warning/toAddTopic/"+pageid, {
								 
					title : "添加专题",
					width : 500,
					id : "addTopic",
					height : 550,
					buttons : {}
				});
				
		
			}else{
				jBox.info("已经到达添加专题总数!", "提示");
			}
		},
		error : function() {
		}
	});
}
/**
 * 添加收藏
 */
function showAttention(warningId){
	jBox("iframe:"+pageContext + "/subject/showAttention/"+warningId, {
	    title: "请选择要添加的收藏夹",
	    width: 600,
	    height: 276,
		top: '15%',
		id:'addAttention',
	    buttons: {}
	});
}
/**
 * 添加简报
 */
function showBriefreport(warningId){
	jBox("iframe:"+pageContext + "/subject/showBriefreport/"+warningId, {
	    title: "请选择要添加的简报",
	    width: 600,
	    height: 276,
		top: '15%',
		id:'addbriefreport',
	    buttons: {}
	});
}
/**
 * 关闭jbox弹窗
 * @return
 */
function closeJbox(){
	$.jBox.close(true);
}
/**
 * 根据id关闭jbox弹窗
 * @return
 */
function closeJbox(id){
	$.jBox.close(id);
}
/**
 * 获取子页面选取的简报id
 */
function setBriefreportId(briefreportId,briefreportName,warningId){
	var params = {};
	params.briefreportId = briefreportId;
	params.warningId = warningId;
	
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addbriefreport");
    		$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/briefreportInfo/saveToBriefreportByWarningId"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("添加成功", 'success',1000); }, 100);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("添加失败", 'success',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定将信息添加到"+briefreportName+"下吗？", "提示", submit);
}
/**
 * 获取子页面选取的收藏夹id
 */
function setAttentionId(attentionId,attentionName,warningId){
	var params = {};
	params.attentionId = attentionId;
	params.warningId = warningId;
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addAttention");
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/attention/addToAttentionByWarningId"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("添加成功", 'success',1000); }, 100);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("添加失败", 'error',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定将信息添加到"+attentionName+"下吗？", "提示", submit);
}
/**
 * 显示网站信息
 * @param url
 * @return
 */
function showWebInfo(url){
	var array_url = url.split('.');
	jBox("iframe:"+pageContext + "/subject/toShowWebInfo/"+array_url[1], {
	    title: "查看网站信息",
	    width: 900,
	    height: 266,
		top: '15%',
		id:'showWebInfo',
	    buttons: {}
	});
}