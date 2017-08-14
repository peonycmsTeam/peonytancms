<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
<br/>
<div class="">
  <div class="shoucang_wx">
      <div class="baikuang">
        <div class="soucangjis_w">
          <div class="shoucang shoucangdiv" style="padding: 0 25px 0 80px;">
          <#list attentionList as attention>  
		    <span class="tb2" style="float:left">
            	<a href="javascript:" onclick="window.parent.setAttentionId('${attention.attentionId}','${attention.name}','${pageId}')">${attention.name}</a>
            </span>
          </#list>
		  </div>
        </div>
    </div>
  </div>
</div>
</body>
</html>
