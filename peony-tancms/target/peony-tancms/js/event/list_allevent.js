var pageContext;
var currentNode;
function zTreeOnAsyncSuccess() {
	var treeObj = $.fn.zTree.getZTreeObj("eventTree")
	var node = treeObj.getNodeByParam("id", $("#firstRegionID").val());
	treeObj.expandNode(node, true, false, true, false);
}

function initSel() {
	$("#type").find("option[value=0]").attr("selected", true);
	$("#polarityorientation").find("option[value=-2]").attr("selected", true);
	$("#timeMethod").find("option[value=2]").attr("selected", true);
	$("#pagesize").find("option[value=10]").attr("selected", true);
	$("#wenzhang").addClass("this").siblings("li").removeClass("this");
}

function loadView(regionid) {
	$("#quanbu").addClass("this").siblings("li").removeClass("this");
	$("#"+regionid).addClass("this").siblings("li").removeClass("this");
	if (regionid != null) {
		initSel();
		$("#viewDiv")
				.html(
						'<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
		// $("#viewDiv").load(pageContext +
		// "/event/toListEventPage?regionID="+currentNode.id+"&timestamp="+Date.parse(new
		// Date()));
		$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/event/toListEventPage?regionID="
					+ regionid + "&timestamp=" + Date.parse(new Date())),
			dataType : "html",
			data : null,
			success : function(data) {
				document.getElementById("viewDiv").innerHTML = data;
			}
		});
	} else {
		// $("#viewDiv").load(pageContext +
		// "/event/toListEventPage?regionID="+$("#firstRegionID").val()+"&timestamp="+Date.parse(new
		// Date()));
		$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/event/toListEventPage?regionID="
					+ $("#firstRegionID").val() + "&timeMethod="
					+ $("#timeMethod").val() + "&timestamp="
					+ Date.parse(new Date())),
			dataType : "html",
			data : null,
			success : function(data) {
				// alert(data);
				// $("#viewDiv").html(data);
				document.getElementById("viewDiv").innerHTML = data;
			}
		});
	}
}
$(function() {
	pageContext = $("#pageContext").val();

	$.ajaxSetup({
		cache : false
	// 关闭AJAX相应的缓存
	});
	jBox.setDefaults({
		defaults : {
			border : 0
		}
	})

	loadView();
});

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
 * 去分析页面
 * 
 * @return
 */
function showfenxi(obj) {
	$(obj).parent().parent().addClass("this").siblings("li")
			.removeClass("this");
	$("#fenxileibiao").hide();
	$("#yuqing_list").hide();
	var regionID = $("#regionID").val();
	var eventid = $("#eventid").val();
	$("#viewDiv")
			.html(
					'<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	$("#viewDiv").load(
			pageContext + "/event/showfenxi?regionID=" + regionID + "&eventid="
					+ eventid + "&timestamp=" + Date.parse(new Date()));
}

/**
 * 点击分类查询数据
 * 
 * @param id
 * @return
 */
function to_ListEvent(eventid, obj) {
	$("#fenxileibiao").show();
	$("#yuqing_list").show();
	$(obj).addClass("this").siblings("li").removeClass("this");
	var conditions = "&eventid="
			+ eventid
			+ "&polarityorientation=-2&timeMethod=2&pagesize=10&type=0&pageNo=1";
	var regionID = $("#firstRegionID").val();
	if (currentNode != undefined) {
		regionID = currentNode.id;
	}
	$("#viewDiv")
			.html(
					'<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	$("#viewDiv").load(
			pageContext + "/event/toListEventPage?regionID=" + regionID
					+ conditions + "&timestamp=" + Date.parse(new Date()));
}

/**
 * 表单提交
 * 
 * @return
 */
function mySubmit() {
	$("#fenxileibiao").show();
	$("#yuqing_list").show();
	var conditions = "&eventid=" + $("#eventid").val()
			+ "&polarityorientation=" + $("#polarityorientation").val()
			+ "&timeMethod=" + $("#timeMethod").val() + "&pagesize="
			+ $("#pagesize").val() + "&type=" + $("#type").val() + "&pageNo="
			+ $("#pageNo").val();
	var regionID = $("#regionID").val();
	if (currentNode != undefined) {
		regionID = currentNode.id;
	}
	$("#viewDiv")
			.html(
					'<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	$("#viewDiv").load(
			pageContext + "/event/toListEventPage?regionID=" + regionID
					+ conditions + "&timestamp=" + Date.parse(new Date()));
}

function fenximySubmit(obj) {
	$(obj).parent().parent().addClass("this").siblings("li")
			.removeClass("this");
	var eventid = $("#eventid").val();
	var regionID = $("#regionID").val();
	$("#viewDiv")
			.html(
					'<div class="x_jiazai"><div class="tuxing_dengdai"></div><p>正在加载</p></div>');
	$("#fenxileibiao").show();
	$("#yuqing_list").show();
	var conditions = "&eventid=" + eventid + "&polarityorientation="
			+ $("#polarityorientation").val() + "&timeMethod="
			+ $("#timeMethod").val() + "&pagesize=" + $("#pagesize").val()
			+ "&type=" + $("#type").val() + "&pageNo=1";
	if (currentNode != undefined) {
		regionID = currentNode.id;
	}
	$("#viewDiv").load(
			pageContext + "/event/toListEventPage?regionID=" + regionID
					+ conditions + "&timestamp=" + Date.parse(new Date()));
}
/**
 * 设置页数
 * 
 * @param i
 * @return
 */
function setPageNo(i) {
	$("#pageNo").val(i);
	mySubmit();
}
/**
 * 显示网站信息
 * 
 * @param url
 * @return
 */
function showWebInfo(url) {
	var array_url = url.split('.');
	jBox("iframe:" + pageContext + "/subject/toShowWebInfo/" + array_url[1], {
		title : "查看网站信息",
		width : 900,
		height : 266,
		top : '15%',
		id : 'showWebInfo',
		buttons : {}
	});
}
/**
 * 页面添加操作 实际页面传入的是 eventnews.id
 * 
 * @return
 */
function addOperation(pageid) {
	var operation = $("#operation_" + pageid + "").val();
	if (operation == 1) {
		// 加入预警
		addWarning(pageid);
	} else if (operation == 2) {
		// 加入专题
		addTopic(pageid);
	} else if (operation == 3) {
		// 加入收藏
		showAttention(pageid);
	} else if (operation == 4) {
		// 加入简报
		showBriefreport(pageid);
	} else {
		return false;
	}
}
/**
 * 添加预警操作
 */
function addWarning(pageid) {
	var regionID = $("#regionID").val();
	var params = {};
	params.id = pageid;
	params.regionID = regionID;
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/warning/saveWarningByByEvennews"),
		dataType : "json",
		data : params,
		success : function(data) {
			if (data > 0) {
				window.setTimeout(function() {
					$.jBox.tip("添加预警成功", 'success', 1000);
				}, 100);
			} else {
				window.setTimeout(function() {
					$.jBox.tip("添加预警失败", 'error', 1000);
				}, 100);
			}
		}
	});
}
/**
 * 添加专题(等待树涛接口)
 */
function addTopic(pageid) {
	$.ajax({
		type : "post",
		url : pageContext + "/topic/isTopicEnough",
		dataType : "json",
		data : {},
		success : function(data) {
			if (data < 3) {

				jBox("iframe:" + pageContext
						+ "/event/toAddTopic/?currentEventnewsId=" + pageid
						+ "&regionID=" + $("#regionID").val(), {
					title : "添加专题",
					width : 500,
					id : "addTopic",
					height : 550,
					buttons : {}
				});

			} else {
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
function showAttention(pageId) {
	jBox("iframe:" + pageContext + "/subject/showAttention/" + pageId, {
		title : "请选择要添加的收藏夹",
		width : 600,
		height : 276,
		top : '15%',
		id : 'addAttention',
		buttons : {}
	});
}
/**
 * 添加简报
 */
function showBriefreport(pageId) {
	jBox("iframe:" + pageContext + "/subject/showBriefreport/" + pageId, {
		title : "请选择要添加的简报",
		width : 600,
		height : 276,
		top : '15%',
		id : 'addbriefreport',
		buttons : {}
	});
}
/**
 * 获取子页面选取的收藏夹id
 */
function setAttentionId(attentionId, attentionName, pageId) {
	var params = {};
	params.attentionId = attentionId;
	params.id = pageId;
	params.regionID = $("#regionID").val();
	var submit = function(v, h, f) {
		if (v == 'ok') {
			closeJbox("addAttention");
			$.ajax({
				type : "post",
				url : encodeURI(pageContext
						+ "/attention/addToAttentionByEvennews"),
				dataType : "json",
				data : params,
				success : function(data) {
					if (data > 0) {
						window.setTimeout(function() {
							$.jBox.tip("添加成功", 'success', 1000);
						}, 100);
					} else {
						window.setTimeout(function() {
							$.jBox.tip("添加失败", 'error', 1000);
						}, 100);
					}
				}
			});
		} else if (v == 'cancel') {
		}
		return true;
	};
	jBox.confirm("您确定将信息添加到" + attentionName + "下吗？", "提示", submit);
}

/**
 * 获取子页面选取的简报id
 */
function setBriefreportId(briefreportId, briefreportName, pageId) {
	var params = {};
	params.briefreportId = briefreportId;
	params.id = pageId;
	params.regionID = $("#regionID").val();
	var submit = function(v, h, f) {
		if (v == 'ok') {
			closeJbox("addbriefreport");
			$.ajax({
				type : "post",
				url : encodeURI(pageContext
						+ "/briefreportInfo/saveToBriefreportByEvennews"),
				dataType : "json",
				data : params,
				success : function(data) {
					if (data > 0) {
						window.setTimeout(function() {
							$.jBox.tip("添加成功", 'success', 1000);
						}, 100);
					} else {
						window.setTimeout(function() {
							$.jBox.tip("添加失败", 'error', 1000);
						}, 100);
					}
				}
			});
		} else if (v == 'cancel') {
		}
		return true;
	};
	jBox.confirm("您确定将信息添加到" + briefreportName + "下吗？", "提示", submit);
}
/**
 * 关闭jbox弹窗
 * 
 * @return
 */
function closeJbox() {
	$.jBox.close(true);
}
/**
 * 根据id关闭jbox弹窗
 * 
 * @return
 */
function closeJbox(id) {
	$.jBox.close(id);
}
/**
 * 查看信息
 */
function loadEventnews(eventnewsId) {
	window.open(pageContext + "/event/loadEventnews?currentEventnewsId="
			+ eventnewsId + "&regionID=" + $("#regionID").val());
}
/**
 * 导出
 */
function exportEvent() {
	var val = $("#export").val();
	$("#export").val('0');
	if (val == '1' || val == '3') {
		var ids = getCheckVal();
		if (ids.length == 0) {
			$.jBox.alert('请选择导出项!', '提示');
		} else {
			if (val == '1') {
				parent.location.href = pageContext + "/event/downloadByIds/"
						+ ids;
			} else {
				parent.location.href = pageContext + "/event/downloadXmlByIds/"
						+ ids;
			}

		}
	}
	if (val == '2' || val == '4') {
		jBox("iframe:" + pageContext + "/event/toExportByTime/" + val, {
			title : "选择导出时间",
			width : 600,
			height : 184,
			top : '15%',
			id : 'toExportByTime',
			buttons : {}
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
function toSendMail() {
	var ids = getCheckVal();
	if (ids.length == 0) {
		$.jBox.alert('请选择发送项!', '提示');
	} else {
		jBox("iframe:" + pageContext + "/event/toWriteEmail", {
			title : "填写邮箱",
			width : 400,
			height : 190,
			top : '15%',
			id : 'toWriteEmail',
			buttons : {}
		});
	}
}
