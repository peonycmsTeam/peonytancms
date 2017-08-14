<div class="fl_left">
<div class="fl_left_in">
  <#if userfront.userType==2 ||userfront.userType==5>
  	<div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/system/toEditEvent">政务舆情定制</a></div>
  <#else> 
  	<div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/system/toEditIndustry">行业舆情</a></div>
  </#if>
  <div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/system/toEditSubject">定制舆情</a></div>
  <div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/system/toListWarningkeyws">预警关键词设置</a></div>
  <div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/reportconfig/listReportConfig">日报定制</a></div>
  <div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/briefreportconfig/listBriefReportConfig">简报模板管理</a></div>
  <div class="tb2 fenlei_title2 color_bai"><a href="${request.getContextPath()}/mail/listMailConfig">邮件模板定制</a></div>
 <!-- <div class="tb2 fenlei_title2 color_bai"><a href="系统配置_信息订阅.html">信息订阅</a></div>
  <div class="tb2 fenlei_title2 color_bai"><a href="系统配置_专报模板定制.html">专报模板定制</a></div>-->
</div>
</div>