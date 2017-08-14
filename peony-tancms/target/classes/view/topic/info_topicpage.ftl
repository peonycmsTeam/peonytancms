<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章详细</title>
<#include "/common/global_css.ftl">
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="website" name="website" value="${topicpage.website}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>  事件专题&nbsp;&gt;&nbsp;事件详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t">
<h1>${topicpage.title}</h1>
<h2 class="color_huise">来源：<span>${topicpage.website}</span> 发布时间：<span>${topicpage.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span> <!--点击数:<span>${topicpage.clickcount}</span> 回复数：<span>${topicpage.replycount}</span>-->
<#if topicpage.type!=3 && topicpage.type!=6 && topicpage.newslevel!=1 && topicpage.newslevel!=2><span class="color_hongse_n"><a href="javascript:" onclick="loadSnapshot('${topicpage.pageid}');">查看快照</a></span></#if>
<#if topicpage.type!=6 && topicpage.newslevel!=1 && topicpage.newslevel!=2>
	<span class="color_hongse_n"><a href="javascript:" onclick="showWebInfo('${topicpage.url}')">查看网站信息</a></span>
</#if>
</h2>
</div><div class="wenzhangxiangxi">
<#if topicpage.type!=3 && topicpage.type!=6>
	<#if topicpage.newslevel!=1 && topicpage.newslevel!=2>
		<iframe src="${topicpage.url}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
	<#else>
		<iframe src="${request.getContextPath()}/subject/loadSnapshot?pageid=${topicpage.pageid}" width="956" height="700" frameborder="0" scrolling="yes"></iframe>
	</#if>
<#else>
<div style="width:956px; height:700px;">
${topicpage.summary}
</div>
</#if>
<div class="HackBox"></div></div><div class="HackBox"></div>
<div style="float:left;"><a href='#' onclick="openInfo();">原文链接:${topicpage.url}</a></div>
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
		window.open("${topicpage.url}");
	}
</script>
</body>
</html>
