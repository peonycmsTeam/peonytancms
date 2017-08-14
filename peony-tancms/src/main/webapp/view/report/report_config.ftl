<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日报定制</title>
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
<form id="reprotConfigForm">
	<input type="hidden" name="reportConfigId" value="<#if reportConfig.reportConfigId?has_content>${reportConfig.reportConfigId}</#if>"/>
		<div class="bg">
		  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;系统配置&nbsp;&gt;&nbsp;日报定制</div>
		</div>
    	<div class="bg">
	  	<div class="fl_br fl_br_peizhi">
	    <div class="fl_br_in">
	      <!--左侧导航-->
	      <#include "/common/left.ftl">
      
	      	<div class="fl_right">
	        <div class="fl_right_in">
	          <div class="baikuang">
	            <div class="fl_right_in">
	              <div class="yuqing_top">
	                <ul>
	                  <li class="color_hongse_n">生成时间:</li>
	                  <li><div class="tb2 xuanze2">
	                  <select name="createTime">
	                  <#if reportConfig.createTime?has_content>
	                  	<#list timeList as time>
	                  		<option value="${time}" 
	                  			<#if time==reportConfig.createTime>selected</#if>>${time}
	                  		</option>
		                </#list>
		                <#else>
		                
		                <#list timeList as time>
	                  		<option value="${time}" 
	                  			<#if time==17>selected</#if>>${time}
	                  		</option>
		                </#list>
		                </#if>  
		              </select><span>时</span>
	                  </div>
                    	<!--<input class="sousuokuang" type="text" name="textfield" id="textfield" />-->
                  	 </li>
	                 	<!-- <li>
	                    <input class="tb2 color_bai add" type="submit" name="button" id="button" value="提 交" />
	                  	</li>-->
                	</ul>
                	<div class="HackBox"></div>
              	</div>
            </div>
          </div>
          <div class="blank8px"></div>
          <div class="baikuang">
            <div class="fl_right_in">
              <div class="yuqing_top">
                <ul>
                <#if reportConfig.reportConfigId?has_content>
	                  <li class="color_hongse_n">定制接收模式:</li>
	                  <li class="checkbox">
	                  	<input type="checkbox"  <#if reportConfig.reportCreate=='1'>checked</#if> name="reportCreate" value="1" <#if reportConfig.reportCreate=='2'>disabled </#if>/>
	                  </li>
	                  <li>生成word文档<#if reportConfig.reportCreate=='2'>(未开通)</#if></li>
	                  <li class="checkbox">
	                    <input type="checkbox"  <#if reportConfig.reportSend=='1'>checked  </#if>  name="reportSend" value="1" <#if reportConfig.reportSend=='2'>disabled </#if> />
	                  </li>
	                  <li>邮件接收<#if reportConfig.reportSend=='2'>(未开通)</#if></li>
                   <#else>
	                  <li class="color_hongse_n">定制接收模式:</li>
	                  <li class="checkbox">
	                    	
	                  </li>
	                  <li>生成word文档(未开通)</li>
	                  <li class="checkbox">
	                  </li>
	                  <li>邮件接收(未开通)</li>
                 </#if>
                </ul>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
          <div class="blank8px"></div>
          <div class="baikuang">
            <div class="fl_right_in">
				<!--<input class="tb2 add4 color_bai" type="submit" value="创建模板" />-->
            <div class="HackBox"></div>
            <input type="hidden" name="reportTempId" value="1" />
            
            <div class="HackBox"></div>
            <div class="text_center">
            <#if reportConfig.reportConfigId?has_content>
            	<input class="tb2 fabiao1 color_bai" type="button" value="确 定" id="subBtn"/>
            	<#else>
            	<input class="tb2 fabiao1 color_bai" type="button" value="确 定" id="btn"/>
            	</#if>
            	</div><div class="blank15px"></div>
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
<script type="text/javascript" src="${request.getContextPath()}/js/report/report_config.js"></script>

<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>