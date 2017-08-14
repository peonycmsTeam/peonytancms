<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章详细</title>
<#include "/common/global_css.ftl">
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/briefreport/briefreportInfo.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="website" name="website" value="${briefreportInfo.website}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;舆情简报&nbsp;&gt;&nbsp;文章详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t">
<h1>${briefreportInfo.title}</h1>
<h2 class="color_huise">来源：<span>${briefreportInfo.website}</span> 发布时间：<span>${briefreportInfo.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span> <!--点击数:<span>${briefreportInfo.visitcount}</span> 回复数：<span>${briefreportInfo.reply}</span>--> 
 <#if briefreportInfo.type!=3 && briefreportInfo.type!=6 && briefreportInfo.newsLevel!=1 && briefreportInfo.newsLevel!=2><span class="color_hongse_n"><a href="javascript:" onclick="loadSnapshot('${briefreportInfo.pageId}');">查看快照</a></span></#if>
  <#if briefreportInfo.type!=6 && briefreportInfo.newsLevel!=1 && briefreportInfo.newsLevel!=2>
 		<span class="color_hongse_n"><a href="javascript:" onclick="showWebInfo('${briefreportInfo.url}')">查看网站信息</a></span>
 </#if>
 </h2>
</div><div class="wenzhangxiangxi">
<#if briefreportInfo.type!=3 && briefreportInfo.type!=6>
	<#if briefreportInfo.newsLevel!=1 && briefreportInfo.newsLevel!=2>
		<iframe src="${briefreportInfo.url}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
	<#else>
		<iframe src="${request.getContextPath()}/subject/loadSnapshot?pageid=${briefreportInfo.pageId}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
	</#if>
<#else>
<div style="width:956px; height:700px;">
		${briefreportInfo.summary}
</div>
</#if>
<div class="HackBox"></div></div><div class="HackBox"></div>
<div style="float:left;"><a href='#' onclick="openInfo();">原文链接:${briefreportInfo.url}</a></div>
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
		window.open("${briefreportInfo.url}");
	}
</script>
</body>
</html>
