<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>事件专题</title>
<#include "/common/global_css.ftl">		
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/topic/topic.js"></script>

</head>
<body>
<!----头部--->
<#include "/common/top.ftl">
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;事件专题</div>
 </div>
<div class="bg">
<div class="sj_zt_t">
<script type="text/javascript" language="javascript" src="style/js/tankuang_qt.js"></script>
  <input class="tb2 color_bai add grzx_znxx4_a" style="cursor:pointer;" type="button" name="button" id="button" onclick="add();"value="添 加" />
<div class="HackBox"></div>
</div>
<form action="${request.getContextPath()}/topic/listTopic" method="post" id="search">
	<input type="hidden" id="pageNo" name="pageNo" value="${list.pageNo}"/>
	 <input type="hidden" id="totalCount" name="totalCount" value="${list.totalCount}">
</form>
<div class="sj_ztw">
  	<#list list.list as topic>  
  <div class="sj_zt">
<h2 class="color_bai">
<div class="on_right">
	<span class="tb2 del" onclick="del('${topic.id}');"><a href="#">删除</a></span>
	<span class="tb2 xiugai" onclick="edit('${topic.id}');"><a href="#">修改</a></span>
</div>
<a href="${request.getContextPath()}/topic/listTopicInfo/${topic.id}">${topic.name}</a>
<div class="on_right"><span>开始时间: ${topic.startTime?string("yyyy-MM-dd")[0..9]}&nbsp;&nbsp;&nbsp;&nbsp;结束时间: ${topic.endTime?string("yyyy-MM-dd")[0..9]}</span></div>
</h2>
<div class="sj_zt_in">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th class="left_th_w">&nbsp;</th>
    <th>新闻</th>
    <th>论坛</th> 
    <th>微博</th>
    <th>博客</th>
    <th>电子报刊</th> 
    <th>twitter</th>
     <th>微信</th>
  </tr>
  <tr>
    <td>今日</td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=1&daytime=0">${topic.dayMap.type1?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=2&daytime=0">${topic.dayMap.type2?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=3&daytime=0">${topic.dayMap.type3?default('0')}</a></td>
     <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=4&daytime=0">${topic.dayMap.type4?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=5&daytime=0">${topic.dayMap.type5?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=6&daytime=0">${topic.dayMap.type6?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=7&daytime=0">${topic.dayMap.type7?default('0')}</a></td>
  </tr>
  <tr>
    <td>总量</td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=1">${topic.countMap.type1?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=2">${topic.countMap.type2?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=3">${topic.countMap.type3?default('0')}</a></td>
     <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=4">${topic.countMap.type4?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=5">${topic.countMap.type5?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=6">${topic.countMap.type6?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}&type=7">${topic.countMap.type7?default('0')}</a></td>
  </tr>
</table>
</div>
  </div></#list>
         <!--分页-出开始--->
         <@selectPage.pagination pagination=list formName="search"/>
        <!--分页---结束---> 
  </div>
</div>
<div class="blank5px"></div>

<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
