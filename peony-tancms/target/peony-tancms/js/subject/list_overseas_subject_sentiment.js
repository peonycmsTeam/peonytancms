var pageContext ;
$(function() {
	pageContext = $("#pageContext").val();
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
})

/**
 * 全选
 * @param checkbox
 * @return
 */
function checkAll(checkbox){ 
	$("input[name='chk_list']").prop("checked",$(checkbox).prop("checked")); 
}
/**
 * 得到checkebox值
 * @return
 */
function getCheckVal(){
	var v="";
	$("input[name='chk_list']:checked").each(function() { 
		if(v==""){
		    v+=this.value
		}else{
		    v+=","+this.value
		}  
	}); 
 	return v;
}

/**
 * 通过左侧导航查询信息
 * @param id
 * @return
 */
function to_subject_sentiment(id){
	$("#pageNo").val(1);
	$("#title").val("");
	$("#type").val(0);
	$("#polarity").val(-2);
	$("#isRead").val(-1);
	$("#time").val(1);
	$("#subjectid").val(id);
	mySubmit();
}

/**
 * 表单提交
 * @return
 */
function mySubmit(){
	var form = document.forms[0];
	form.action = pageContext+"/subject/toListOverseasSubjectSentiment";
	form.method="post";
	form.submit();
}
/**
 * 设置页数
 * @param i
 * @return
 */
function setPageNo(i){
	$("#pageNo").val(i);
	mySubmit();
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
/**
 * 执行删除操作
 * @return
 */
function delete_SubjectPage(){
	if(getCheckVal()==""){
		$.jBox.info("请先选择要删除的信息", '提示');
		return;
	}
	var arrayID=getCheckVal().split(",");
	var arrayIdLength=arrayID.length;
	var params = {};
	params.ids = new Array();
	
	for (i=0 ; i<arrayIdLength ; i++){
		params.ids.push(arrayID[i]);
	}
	
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/subject/deleteSubjectPage"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("信息删除成功", 'success',1000); }, 100);
	    				window.setTimeout(mySubmit,1000);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("信息删除失败", 'success',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	jBox.confirm("您确定要删除以下"+arrayIdLength+"条信息？", "提示", submit);
}

/**
 * 修改信息正负面性
 * @param id
 * @return
 */
function updateSubjectPagePolarity(id){
	var polarity = $("#polarity_"+id+"").val();
	var showChar = "";
	if(polarity==1){
		showChar = "正面";
	}else if(polarity==-1){
		showChar = "负面";
	}else{
		showChar = "争议";
	}
	var params = {};
	params.id = id;
	params.polarity = polarity;
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/subject/updateSubjectPagePolarity"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("修改成功", 'success',1000); }, 100);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("修改失败", 'success',1000); }, 100);
	    			}
	    		}
	    	});
	    }
	    else if (v == 'cancel') {
	    }
	    return true; 
	};
	// 1：正面  -1：负面  0：争议
	jBox.confirm("您确定将当前信息修改为"+showChar+"吗？", "提示", submit);
}
/**
 * 页面添加操作
 * @return
 */
function addOperation(pageid){
	var operation = $("#operation_"+pageid+"").val();
	if(operation==1){
		//加入预警
		addWarning(pageid);
	}else if(operation==2){
		//加入专题
		addTopic(pageid);
	}else if(operation==3){
		//加入收藏
		showAttention(pageid);
	}else if(operation==4){
		//加入简报
		showBriefreport(pageid);
	}else{
		return false;
	}
}
/**
 * 添加预警操作
 */
function addWarning(pageid){
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/warning/saveWarningByPageId/"+pageid),
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
	jBox("iframe:"+pageContext + "/subject/toAddTopicInfo/"+pageid, {
	    title: "添加专题信息",
	    width: 600,
	    height: 320,
		top: '15%',
		id:'addTopic',
	    buttons: {}
	});
}
/**
 * 添加收藏
 */
function showAttention(pageId){
	jBox("iframe:"+pageContext + "/subject/showAttention/"+pageId, {
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
function showBriefreport(pageId){
	jBox("iframe:"+pageContext + "/subject/showBriefreport/"+pageId, {
	    title: "请选择要添加的简报",
	    width: 600,
	    height: 276,
		top: '15%',
		id:'addbriefreport',
	    buttons: {}
	});
}
/**
 * 获取子页面选取的收藏夹id
 */
function setAttentionId(attentionId,attentionName,pageId){
	var params = {};
	params.attentionId = attentionId;
	params.pageId = pageId;
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addAttention");
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/attention/addToAttention"),
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
	jBox.confirm("您确定将信息添加到"+attentionName+"下吗？", "提示", submit);
}

/**
 * 获取子页面选取的简报id
 */
function setBriefreportId(briefreportId,briefreportName,pageId){
	var params = {};
	params.briefreportId = briefreportId;
	params.pageId = pageId;
	
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addbriefreport");
    		$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/briefreportInfo/addToBriefreport"),
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
 * 查看信息
 */
function loadSubjectPage(id){
	$("#currentId").val(id);
	var form = document.forms[0];
	form.action = pageContext+"/subject/loadSubjectPage";
	form.method="post";
	form.submit();
}
/**
 * 导出
 */
function exportSubject(){
	var val=$("#export").val();
	if(val=='1'){
		var ids=getCheckVal();
		if(ids.length==0){
			$.jBox.alert('请选择导出项!', '提示');
		}else{
			parent.location.href=pageContext+"/subject/downloadByIds/"+ids;
		}
	}
	if(val=='2'){
		jBox("iframe:"+pageContext + "/subject/toExportByTime", {
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
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}
/**
 * 发送邮件
 */
function sendMail(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择发送项!', '提示');
	}else{
		$.ajax({
    		type : "post",
    		url : encodeURI(pageContext + "/subject/sendMail/"+ids),
    		dataType : "json",
    		data : null,
    		success : function(data) {
    			if(data>0){
    				window.setTimeout(function () { $.jBox.tip("发送成功", 'success',1000); }, 100);
    			}else{
    				window.setTimeout(function () { $.jBox.tip("发送失败", 'error',1000); }, 100);
    			}
    		}
    	});
	}
}