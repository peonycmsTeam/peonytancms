<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>简报定制</title>
<#include "/common/global_css.ftl">
<!--[if lte IE 6]>
	<script type="text/javascript" src="style/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->

<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<form id="mailConfigForm">
	<input type="hidden" name="mailConfigId" value="${mailConfig.mailConfigId}"/>
		<div class="bg">
		  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;系统配置&nbsp;&gt;&nbsp;邮件模板定制</div>
		</div>
    	<div class="bg">
	  	<div class="fl_br fl_br_peizhi">
	    <div class="fl_br_in">
	      <!--左侧导航-->
	      <#include "/common/left.ftl">
      
	     <div class="fl_right">
	     <div class="fl_right_in">
          <div class="blank8px"></div>
          <div class="baikuang">
            <div class="fl_right_in">
				<!--<input class="tb2 add4 color_bai" type="submit" value="创建模板" />-->
            <div class="HackBox"></div>
            
            <div class="moban_list">
	            <ul>
	            <#if ( mailConfig.mailTempId>0 )>
	            <#list listMailTemp as mailTemp>
	            <li>
		           <a title="预览" href="#" onClick="view('${mailTemp.name}')"> 
		           		<img src="${request.getContextPath()}/${mailTemp.url}" width="156" height="156" />
		           </a>
	               <label for="radio">
	              		<input type="radio" name="mailTempId" <#if mailConfig.mailTempId?string?contains("${mailTemp.mailTempId}")>checked</#if>  value="${mailTemp.mailTempId}" />
	               </label>
	              		${mailTemp.name}
	            </li>
	            </#list>
	            <#else>
	            <#list listMailTemp as mailTemp>
		            <li>
			              <a href="#" title="预览" onClick="view('${mailTemp.name}')"> 
			              	  <img src="${request.getContextPath()}/${mailTemp.url}" width="156" height="156" /></a>
				              <label for="radio">
				              	<input type="radio" name="mailTempId" <#if mailTemp_index==0 >checked</#if>  value="${mailTemp.mailTempId}" />
				              </label>
		              	${mailTemp.name}
		            </li>
	            </#list>
	           </#if>
	          </ul>
            </div>
            <div class="HackBox"></div>
            <div class="text_center">
            	<input class="tb2 fabiao1 color_bai" type="button" value="确 定" id="subBtn"/></div><div class="blank15px"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
</form>
<div class="blank5px"></div>
<!----底部-开始--->
<div class="HackBox"></div>
<script type="text/javascript" src="${request.getContextPath()}/js/mail/mail_config.js"></script>

<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>