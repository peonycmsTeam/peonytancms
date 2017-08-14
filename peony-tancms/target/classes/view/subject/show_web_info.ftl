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
    <table border="0" cellspacing="0" cellpadding="0" class="title_tc">
      <tr>
        <td class="title_tc1"></td>
        <td class="title_tc2">${webSite.name}</td>
        <td class="guanbi" title="关闭"></td>
      </tr>
    </table>
    <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
          <table border="0" cellspacing="0" cellpadding="0">
          <#if resultMsg==1>
            <tr>
              <th>主办单位名称</th>
              <td>${webSite.company}</td>
            </tr>
            <tr>
              <th>主办单位性质
              </th>
              <td><#if webSite.nature==1 >
             		 企业
              	<#elseif webSite.nature==2>
              		政府机关
              	<#elseif webSite.nature==3>
              		事业单位
              	<#elseif webSite.nature==4>
              		社会团体
              	<#elseif webSite.nature==5>
              		个人
              	</#if>
              </td>
            </tr>
            <tr>
              <th>网站备案/许可证号
              </th>
              <td>
              ${webSite.record}</td>
            </tr>
            <#if webSite.contacts!="">
	            <tr>
	              <th>删贴联系人</th>
	              <td>${webSite.contacts}</td>
	            </tr>
            </#if>
            <#if webSite.phone!="">
	            <tr>
	              <th>联系方式</th>
	              <td>${webSite.phone}</td>
	            </tr>
            </#if>
            <#if webSite.process_description!="">
	            <tr>
	              <th>流程
	              </th>
	              <td>${webSite.process_description}</td>
	            </tr>
	        </#if>
            <#else>
            	暂无相关网站介绍
            </#if>
          </table>
        </div>
      </div>
	</div>
</body>
</html>
