<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章详细</title>
<link rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj.css">
<link href="${request.getContextPath()}/css/jbox/jbox.css" rel="stylesheet" type="text/css" id="jbox"/>
<script src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>

<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/briefreport/briefreportInfo.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="website" name="website" value="${warning.website}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;办公平台&nbsp;&gt;&nbsp;七日预警 &nbsp;&gt;&nbsp;文章详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t"> 
<h1>${warning.title}</h1>
<h2 class="color_huise">来源：<span>${warning.website}</span> 发布时间：<span>${warning.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span><!-- 点击数:<span>${warning.clickcount}</span> 回复数：<span>${warning.replycount}</span> --> 
<#if warning.waringType!=3 && warning.waringType!=6 && warning.newsLevel!=1 && warning.newsLevel!=2><span class="color_hongse_n"><a href="javascript:" onclick="loadSnapshot('${warning.pageId}');">查看快照</a></span></#if>
<#if warning.waringType!=6 && warning.newsLevel!=1 && warning.newsLevel!=2>
<span class="color_hongse_n"><a href="javascript:" onclick="showWebInfo('${warning.url}')">查看网站信息</a></span>
</#if>
</h2>
</div><div class="wenzhangxiangxi">    
<#if warning.waringType!=3 && warning.waringType!=6>
	<#if warning.newsLevel!=1 && warning.newsLevel!=2>
			<iframe src="${warning.url}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
		<#else>
			<iframe src="${request.getContextPath()}/subject/loadSnapshot?pageid=${warning.pageId}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
	</#if>
<#else>
		<div style="width:956px; height:700px;">${warning.summary}</div>
</#if>
<div class="HackBox"></div></div><div class="HackBox"></div>
<div style="float:left;"><a href='#' onclick="openInfo();">原文链接:${warning.url}</a></div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript">
function loadSnapshot(pageid){
		window.open("${request.getContextPath()}/subject/loadSnapshot?pageid="+pageid);
	}
	function showWebInfo(url){
		var result_url = "";
		var name = $("#website").val();
		if(name=="新浪微博"){
			result_url = "weibo.cn";
		}else{
			var array_url = url.split('.');
			result_url = array_url[1];
		}
		jBox("iframe:${request.getContextPath()}/subject/toShowWebInfo/"+result_url, {
		    title: "查看网站信息",
		    width: 900,
		    height: 266,
			top: '15%',
			id:'showWebInfo',
		    buttons: {}
		});
	}
	function openInfo(){
		window.open("${warning.url}");
	}
</script>
</body>
</html>