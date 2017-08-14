<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定制舆情</title>
<#include "/common/global_css.ftl">
<!--[if lte IE 6]>
	<script type="text/javascript" src="style/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->
</head>
<body>
<!--头部-->
<#include "/common/top.ftl">
<!--头部结束-->
<div class="bg">
   <div class="tishi_xinxi">
  	<div class="tishi_xinxi_in">
  		<div class="tubiao_x1"></div>
  		<div class="tubiao_x2"></div>
  		<h3>友情提示:</h3>
  		<p>暂无用户定制舆情订阅，点击“<a href="${request.getContextPath()}/system/toEditSubject">关联</a>”进行关联!</p>
  	</div>
  </div>
</div>
<div class="blank5px"></div>
<!--底部开始-->
<#include "/common/bottom.ftl">
<!--底部结束-->
</body>
</html>
