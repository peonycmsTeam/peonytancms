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
 * 修改简报倾向性
 * @param obj
 */
function changeOrientation(obj){
	var id=obj.id;
	var orientation=$(obj).val();
	$.ajax({
		type : "post",
		url : pageContext + "/briefreportInfo/changeOrientation",
		dataType : "json",
		data : {
			id:id,orientation:orientation
		},
		success : function(data) {
			jBox.info("修改成功", "提示");
		},
		error : function() {
			jBox.info("修改失败", "提示");
		}
	});
}
/**
 * 全选
 */
function checkAll(){
	$("input[name='checkbox']").prop("checked", $(checkbox).prop("checked"));
}
/**
 * 删除简报info
 */
function del(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择要删除的简报!', '提示');
	}else{
		var submit = function (v, h, f) {
			 if (v == 'ok') {
			$.ajax({
				type : "post",
				url : pageContext + "/briefreportInfo/delBriefreportInfo",
				dataType : "json",
				data : {
					ids:ids,
					totalCount:$("#totalCount").val(),
	    			pageSize:$("#pageSize").val(),
	    			pageNo:$("#pageNo").val()
				},
				success : function(data) {
					if(data.state){
						
						$("#pageNo").attr("value",data.pageNo);
						$("#search").submit();
					}else{
						jBox.info("删除失败", "提示");
					}
				},
				error : function() {
					jBox.info("删除失败", "提示");
				}
			});
			 }else{}
		};
		jBox.confirm("您确定要删除该简报吗？", "提示", submit);
	}
	
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
 * 搜索提交表单
 */
function submitForm(){
	$("#search").submit();
}
/**
 * 点击分页
 * @param pageNo
 */
function selectPage(pageNo){
	$("#pageNo").attr("value",pageNo);
	$("#search").submit();
}
/**
 * 页面添加操作
 * @return
 */
function addOperation(obj){
	var operation = obj.value;
	var briefreportInfoId = obj.id;
	if(operation==1){
		//加入预警
		addWarning(briefreportInfoId);
	}else if(operation==2){
		//加入专题
		addTopic(briefreportInfoId);
	}else if(operation==3){
		//加入收藏
		showAttention(briefreportInfoId);
	}else if(operation==4){
		//加入简报
		showBriefreport(briefreportInfoId);
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

				jBox("iframe:" + pageContext+"/briefreportInfo/toAddTopic/"+pageid, {
								 
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
 * 获取子页面选取的收藏夹id
 */
function setAttentionId(attentionId,attentionName,briefreportInfoId){
	var params = {};
	params.attentionId = attentionId;
	params.briefreportInfoId = briefreportInfoId;
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addAttention");
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/attention/addToAttentionByBriefreport"),
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
/**
 * 关闭iframe
 * 
 * @return
 */
function showPop() {
	$.jBox.close(true);
}