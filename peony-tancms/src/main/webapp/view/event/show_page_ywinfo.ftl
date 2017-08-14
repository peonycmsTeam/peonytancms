
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文章详情</title>
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
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<form name="form1" id="form1" action="${request.getContextPath()}/event/loadEventnews" method="post" >
<input type="hidden" id="currentEventnewsId" name="currentEventnewsId" value=""/>
<input type="hidden" id="regionID" name="regionID" value=""/>
</form>
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;政务舆情&nbsp;&gt;&nbsp;文章详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t">
	<h1>${eventnews.title}</h1>
	<h2 class="color_huise">
		来源：<span>${eventnews.website}</span> 
		发布时间：<span>${eventnews.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span> 
	</h2>
</div>
<div class="wenzhangxiangxi">
	<!--317-->
	${html}
</div>
原文地址：<a href="${eventnews.url}" target="_blank">${eventnews.url}</a>
<div class="xiangguan">
<div class="title_h1 color_bai">
       相关信息 
    </div>
	<div class="list">
		<#list list as eventnews>
          <dl>
            <dd><em>${eventnews.website}</em><span>${eventnews.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span></dd>
            <dt>
            	<a href="javascript:" onclick="loadEventnews('${eventnews.id}','${eventnews.provinceid}')">${eventnews.title}</a>
            </dt>
          </dl>
		</#list>
    </div>
</div>
<div class="HackBox"></div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript">
	function loadEventnews(id,provinceid){
		$("#currentEventnewsId").val(id);
		$("#regionID").val(provinceid);
		var form = document.forms[0];
		form.action = "${request.getContextPath()}/event/loadEventnews";
		form.method="post";
		form.submit();
	}
	
	function loadSnapshot(pageid){
		window.open("${request.getContextPath()}/subject/loadSnapshot?pageid="+pageid);
	}
	
	function showWebInfo(url){
		var array_url = url.split('.');
		jBox("iframe:${request.getContextPath()}/subject/toShowWebInfo/"+array_url[1], {
		    title: "查看网站信息",
		    width: 900,
		    height: 266,
			top: '15%',
			id:'showWebInfo',
		    buttons: {}
		});
	}
</script>
</body>
</html>
