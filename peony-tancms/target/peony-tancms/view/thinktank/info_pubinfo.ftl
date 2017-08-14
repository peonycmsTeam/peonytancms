<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智库</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/thinktank/thinktank.js"></script>
</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;智库&nbsp;&gt;&nbsp;文章详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t">
	<h1>${pubinfo.title}</h1>
	<h2 class="color_huise"><#if pubinfo.author?exists><#if pubinfo.author?length gt 0>作者：<span>${pubinfo.author}</span> </#if></#if>发布时间：<span>${pubinfo.time?string("MM-dd HH:mm:ss")}</span></h2>
</div>
<div class="wenzhangxiangxi">
	<div class="neirong">
	${pubinfo.content}
</div>
	<div class="HackBox"></div>
	</div>
	<div class="HackBox"></div>
	</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
