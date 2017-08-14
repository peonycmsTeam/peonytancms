var pageContext ;
var currentNode;

function initSel(){
	$("#title").val("");
	$("#type").find("option[value=0]").attr("selected",true);
	$("#polarity").find("option[value=-2]").attr("selected",true);
	$("#time").find("option[value=2]").attr("selected",true);
	$("#pagesize").find("option[value=10]").attr("selected",true);
	$("#isRead").find("option[value=-1]").attr("selected",true);
	$("#wenzhang").addClass("this").siblings("li").removeClass("this");
	$("#yuqing_top").show(); 
	$("#yuqing_list").show(); 
}

function loadView(){
	if(currentNode){
		//$("#viewDiv").load(pageContext + "/event/toListEventPage?regionID="+currentNode.id+"&timestamp="+Date.parse(new Date()));
		initSel();
		$("#viewDiv").html('<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
		$.ajax({
    		type : "post",
    		url : encodeURI(pageContext + "/industry/toListIndustryPage?IndustryId="+currentNode.id+"&timestamp="+Date.parse(new Date())),
    		dataType : "html",
    		data : null,
    		success : function(data) {
				document.getElementById("viewDiv").innerHTML=data;
    		}
    	});
	}else{
		//$("#viewDiv").load(pageContext + "/event/toListEventPage?regionID="+$("#firstRegionID").val()+"&timestamp="+Date.parse(new Date()));
		$.ajax({
    		type : "post",
    		url : encodeURI(pageContext + "/industry/toListIndustryPage?IndustryId="+$("#firstIndustryId").val()+"&time="+$("#time").val()+"&timestamp="+Date.parse(new Date())),
    		dataType : "html",
    		data : null,
    		success : function(data) {
				document.getElementById("viewDiv").innerHTML=data;
    		}
    	});
	}
}

function  zTreeOnAsyncSuccess(){
	var treeObj = $.fn.zTree.getZTreeObj("industryTree")
	var node = treeObj.getNodeByParam("id",1);
	treeObj.expandNode(node, true, false, true, false);
}

$(function() {
	pageContext = $("#pageContext").val();
	
	$.ajaxSetup ({
	    cache: false //关闭AJAX相应的缓存
	});
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
	function configTree() {
		setting = {
			async : {
				enable : true,
				url :  pageContext + "/industry/findChildren",
				autoParam : [ "id" ]
			},
			view : {
				expandSpeed : ""
			},
			callback : {
				onAsyncSuccess: zTreeOnAsyncSuccess,
				onClick : function(event, treeId, treeNode) {
					currentNode=treeNode;
					loadView();
				}
			}
		};
		return setting;
	}
	$.fn.zTree.init($("#industryTree"), configTree());
	loadView();
});

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
 * 去分析页面
 * @return
 */
function showfenxi(obj){
	$(obj).parent().parent().addClass("this").siblings("li").removeClass("this");
	$("#yuqing_top").hide(); 
	$("#yuqing_list").hide(); 
	$("#viewDiv").load(pageContext + "/industry/toshowfenxi?subjectid="+$("#subjectid").val()+"&userId="+$("#userId").val()+"&timestamp="+Date.parse(new Date()));
}

function fenxisubmit(obj){
	var subjectid = $("#subjectid").val();
	initSel();
	$(obj).parent().parent().addClass("this").siblings("li").removeClass("this");
	$("#viewDiv").html('<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	$("#yuqing_top").show(); 
	$("#yuqing_list").show(); 
	$("#viewDiv").load(pageContext + "/industry/toListIndustryPage?IndustryId="+subjectid+"&timestamp="+Date.parse(new Date()));
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
	
	var conditions ="&title="+$("#title").val()+"&type="+$("#type").val()+"&polarity="+$("#polarity").val()+"&isRead="+$("#isRead").val()+"&time="+$("#time").val()+"&pageNo="+$("#pageNo").val()+"&pagesize="+$("#pagesize").val();
	var subjectId = 1;
	if(currentNode!=undefined){
		subjectId = currentNode.id;
	}
	$("#viewDiv").html('<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	//$("#viewDiv").load(pageContext + "/subject/toListSubjectSentimentOld?subjectid="+subjectId+conditions+"&timestamp="+Date.parse(new Date()));
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/industry/toListIndustryPage?IndustryId="+subjectId+conditions+"&timestamp="+Date.parse(new Date())),
		dataType : "html",
		data : null,
		success : function(data) {
			document.getElementById("viewDiv").innerHTML=data;
		}
	});
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
	params.totalCount= $("#totalCount").val();
	params.pagesize = $("#pagesize").val();
	params.pageNo = $("#pageNo").val();
	params.userId = $("#userId").val();
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/industry/deleteSubjectPage"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				$("#pageNo").val(data);
	    				window.setTimeout(function () { $.jBox.tip("信息删除成功", 'success',1000); }, 100);
	    				window.setTimeout(mySubmit,1000);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("信息删除失败", 'error',1000); }, 100);
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
	params.userId = $("#userId").val();
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/industry/updateSubjectPagePolarity"),
	    		dataType : "json",
	    		data : params,
	    		success : function(data) {
	    			if(data>0){
	    				window.setTimeout(function () { $.jBox.tip("修改成功", 'success',1000); }, 100);
	    			}else{
	    				window.setTimeout(function () { $.jBox.tip("修改失败", 'error',1000); }, 100);
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
		url : encodeURI(pageContext + "/industry/saveWarningByPageId/"+pageid+"/"+$("#userId").val()),
		dataType : "json",
		data : null,
		success : function(data) {
			if(data>0){
				window.setTimeout(function () { $.jBox.tip("添加预警成功", 'success',1000); }, 100);
			}else{
				window.setTimeout(function () { $.jBox.tip("添加预警失败", 'error',1000); }, 100);
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
				jBox("iframe:" + pageContext+"/industry/toAddTopic?currentId="+pageid+"&userId="+$("#userId").val(), {
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
 * 获取子页面选取的收藏夹id
 */
function setAttentionId(attentionId,attentionName,pageId){
	var params = {};
	params.attentionId = attentionId;
	params.id = pageId;
	params.userId = $("#userId").val();
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addAttention");
	    	$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/industry/addToAttention"),
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
 * 获取子页面选取的简报id
 */
function setBriefreportId(briefreportId,briefreportName,pageId){
	var params = {};
	params.briefreportId = briefreportId;
	params.id = pageId;
	var eventId=$("#eventId").val();
	params.eventId=eventId;
	params.userId = $("#userId").val();
	var submit = function (v, h, f) {
	    if (v == 'ok') {
	    	closeJbox("addbriefreport");
    		$.ajax({
	    		type : "post",
	    		url : encodeURI(pageContext + "/industry/addToBriefreport"),
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
function loadSubjectPage(id,groupseedid){
	$("#currentId").val(id);
	$("#groupseedid").val(groupseedid);
	window.open(pageContext+"/industry/loadSubjectPage?currentId="+id+"&groupseedid="+groupseedid+"&userId="+$("#userId").val());
}
/**
 * 导出
 */
function exportSubject(){
	var val=$("#export").val();
	$("#export").val('0');
	if(val=='1'){
		var ids=getCheckVal();
		if(ids.length==0){
			$.jBox.alert('请选择导出项!', '提示');
		}else{
			parent.location.href=pageContext+"/subject/downloadByIds/"+ids;
		}
	}
	if(val=='2'){
		jBox("iframe:"+pageContext + "/industry/toExportByTime", {
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
function selectTime(){
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
			    	var subjectId = 1;
			    	if(currentNode!=undefined){
			    		subjectId = currentNode.id;
			    	}
			    	$("#startTime",window.parent.document).val(btime);
			    	$("#overTime",window.parent.document).val(etime);
			    	var form = parent.document.forms[0];
			    	form.action = pageContext+"/industry/downloadBySelectTime?IndustryId="+subjectId;
			    	form.method="post";
			    	form.submit();
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
 * 发送邮件
 */
function toSendMail(){
	var ids=getCheckVal();
	if(ids.length==0){
		$.jBox.alert('请选择发送项!', '提示');
	}else{
		jBox("iframe:"+pageContext + "/subject/toWriteEmail", {
		    title: "填写邮箱",
		    width: 400,
		    height: 175,
			top: '15%',
			id:'toWriteEmail',
		    buttons: {}
		});
	}
}


