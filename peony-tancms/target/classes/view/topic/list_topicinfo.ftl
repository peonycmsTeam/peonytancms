<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">   
<title>事件综述</title>
<#include "/common/global_css.ftl">
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script src="${request.getContextPath()}/js/topic/topicinfo.js"></script>
   
</head>
<body>
<!----头部--->
<#include "/common/top.ftl">
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;事件专题&nbsp;&gt;&nbsp;事件综述 </div>
 </div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
      <div class="fl_left_in">
      <div class="fenleitou"></div>
      <div class="sj_zs_l">
      <ul>
        <li class="this"><a href="${request.getContextPath()}/topic/listTopicInfo/${topic.id}">事件综述</a></li>
        <li><a href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}">专题快报</a></li>
      
        </ul>
        </div>
      </div>
      </div>
      <div class="fl_right">
      <div class="fl_right_in"> <div class="baikuang"><div class="shijianzongsu_t">
       <h1><div class="on_right"><input class="tb2 color_bai bianji_vs2" type="button" name="button" id="button" value="导 出" onclick="saveImg();"/></div>
       <div class="on_right"></div>
       	专题名称：<span class="color_hongse_n">${topic.name}</span></h1>
       <div class="neitong_sj">${topic.abstruct} </div>
       </div></div>
       <div class="blank8px"></div>
       <form name="from1" id="from1" action="${request.getContextPath()}/topic/listTopicInfo/${topic.id}" method="post" >
       <div class="baikuang">
       <input type="hidden" id="topicid" name="topicid" value="${topic.id}"/>
<div class="sj_zx_b">
        <ul>
           <li>
          <input class="inputxsf" type="text" name="stime" id="stime" value="${stime}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></li>
           <li class="tb2 color_bai timek">开始时间</li><li>
          <input class="inputxsf" type="text" name="etime" id="etime" value="${etime}"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></li>
           <li class="tb2 color_bai timek">结束时间</li>
           <li class="on_right">
             <input class="tb2 color_bai chaxun" type="submit" name="button" id="button" value="查 询" onclick="selectByTime();"/>
           </li>
           </ul> <div class="HackBox"></div>
       </div>
       </div>
 <div class="baikuang">
      
      <div class="yq_zs_k">
      <div class="title_h1">
       <span class="t_tit t_tit_1">事件传播总量走势图
  
</span>
      </div>
      <div class="kuang_in" id="yqzst" style="height: 1120px;">
		<div class="x_jiazai">
        <div class="tuxing_dengdai"></div>
        <p>正在加载</p>
        </div>
      </div>
    </div>
    <!--
    <div class="yq_zs_k">
      <div class="title_h1">
       <span class="t_tit t_tit_2">媒体分布分析
</span>
      </div>
      <div class="kuang_in" id="yqfbt" style="height: 510px;">  
 
       </div>
    </div>
-->
      <div class="yq_zs_k">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}">MORE</a></span>
        <span class="t_tit t_tit_4"><a target="_blank" href="${request.getContextPath()}/topicpage/listTopicPage?topicid=${topic.id}"><font color="white">按发布时间排序前10条信息</font></a></span>
      </div>
      <div class="kuang_in" id="yqxxlb">

      </div>
    </div>
    </div>
    </form>
      </div>
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
 <script type="text/javascript">
  $.ajaxSetup ({
    cache: false,
    async: false
});

$(document).ready(function(){
$('#yqxxlb').load('${request.getContextPath()}/topicpage/selectTopicPages?topicid=${topic.id}');

$('#yqzst').load('${request.getContextPath()}/topicpage/selectByCount?id=${topic.id}&stime=${stime}&etime=${etime}');
} 
); 
 </script>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
