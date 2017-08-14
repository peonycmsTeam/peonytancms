
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
<form name="form1" id="form1" action="${request.getContextPath()}/subject/toListSubjectSentimentOld" method="post" >
<input type="hidden" id="currentId" name="currentId" value=""/>
<input type="hidden" id="groupseedid" name="groupseedid" value=""/>
<input type="hidden" id="userId" name="userId" value="${userId}">	
<input type="hidden" id="website" name="website" value="${subjectPage.website}"/>
</form>
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;&gt;&nbsp;文章详细 </div>
 </div>
<div class="bg">

<div class="wenzhangxiangxi_t">
	<h1>${subjectPage.title}</h1>
	<h2 class="color_huise">
		来源：<span>${subjectPage.website}</span> 
		发布时间：<span>${subjectPage.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span> 
		<!--点击数:<span>${subjectPage.clickcount}</span> 
		回复数：<span>${subjectPage.replycount}</span> -->
		<span class="color_hongse_n"><a href="javascript:" onclick="loadSnapshot('${subjectPage.pageid}');">查看快照</a></span>
		<span class="color_hongse_n"><a href="javascript:" onclick="showWebInfo('${subjectPage.url}')">查看网站信息</a></span>
	</h2>
</div>
<div class="wenzhangxiangxi" id="ckkz">
	<!--317-->
	<#if subjectPage.type!=3>
		<iframe marginWidth=0 marginHeight=0 src="${subjectPage.url}" frameBorder=0 width="956" height="700"></iframe>
	<#else>
		${subjectPage.summary}
	</#if>
	
</div>
原文地址：<a href="${subjectPage.url}" target="_blank">${subjectPage.url}</a>
<div class="xiangguan">
<div class="title_h1 color_bai">
       相关信息 
    </div>
	<div class="list">
		<#list list as subjectPage>
          <dl>
            <dd><em>${subjectPage.website}</em><span>${subjectPage.publishdate?string("yyyy-MM-dd HH:mm:ss")}</span></dd>
            <dt>
            	<a href="javascript:" onclick="loadSubjectPage('${subjectPage.id}','${subjectPage.groupseedid}')">${subjectPage.title}</a>
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
	
	$(function() {
		jBox.setDefaults({
			defaults : {
				border : 0
			}
		})
	});

	function loadSubjectPage(id,groupseedid){
		$("#currentId").val(id);
		$("#groupseedid").val(groupseedid);
		var form = document.forms[0];
		form.action = "${request.getContextPath()}/industry/loadSubjectPage";
		form.method="post";
		form.submit();
	}
	
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
</script>
</body>
</html>
