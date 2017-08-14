
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政务舆情定制</title>
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
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->

<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong><a href="${request.getContextPath()}/homepage/listHomePage">&nbsp;首页&nbsp;</a>&gt;
  <a href="${request.getContextPath()}/system/toEditEvent">&nbsp;系统配置&nbsp;</a>&gt;
  <a href="${request.getContextPath()}/system/toEditEvent">&nbsp;政务舆情定制</a></div>
</div>

<div class="bg">
  <div class="fl_br fl_br_peizhi">
    <div class="fl_br_in">
      <!--左侧导航-->
      <#include "/common/left.ftl">
      
      <div class="fl_right">
      	<form name="form1" id="form1" action="${request.getContextPath()}/system/doAddSubscription" method="post" >
      	<input type="hidden" id="eventId" name="eventId" value=""/>
        <div class="fl_right_in">
          <div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1"> 政府公共 </div>
              <div class="kuang_in">
                <ul class="yq_fl_a">
                <#list publicEventList as event>  
                  <li>
                    <input type="checkbox" name="chk_list" id="chk_list" value="${event.eventid}" <#if userSubscription?contains(","+event.eventid+",")> checked </#if> />${event.eventname}
                  </li>
                </#list>
                </ul>
                <div class="blank10px">
                
                </div>
              </div>
            </div>
            <div class="yq_zs_k">
              <div class="title_h1">其它舆情 </div>
              <div class="kuang_in">
                <ul class="yq_fl_a">
                <#list Eventlist as event> 
                  <li>
                    <input type="checkbox" name="chk_list" id="chk_list" value="${event.eventid}" <#if userSubscription?contains(","+event.eventid+",")> checked </#if> />${event.eventname}
                  </li>
                </#list>  
                </ul>
                <div class="blank10px"></div>
              </div>
            </div>
          </div>
        </div>
        <div class=" text_center">
			<input name="提交" type="button" onclick="addSubscription()" style="cursor:pointer;" class="tb2 fabiao1 color_bai" value="提交" />
        </div>
      </div>
      </form>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/system/list_event.js"></script>
</body>
</html>
