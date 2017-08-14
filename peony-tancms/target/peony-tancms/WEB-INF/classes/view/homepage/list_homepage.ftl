<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<script src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
</head>
<body>
<#include "/common/global_css.ftl">		
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<script src="${request.getContextPath()}/js/index.js" type="text/javascript"></script>
<div class="bg">
  <div class="bg_gg">
    <div id="focus001" class="focus1" >
      <ul>
	       <#list pagination.list as list>
	         <li><a target="_blank" href="${request.getContextPath()}/thinktank/getInfoPubinfo?pubinfoId=${list.pubinfoId}" ><img src="http://user.peonydata.com/peonydata/${list.image}" width="1000" height="148" /></a></li>
	       </#list>
      </ul>
    </div>
  </div>
</div>
<div class="bg">
  <div class="gonggao color_hongse_n">
    <div class="on_left"><b class="tb2">我的办公平台</b><span>今日预警<a href="${request.getContextPath()}/warning/listWarning?selectDate=2"><span id="warnCount"></span>条</a></span></div>
    <div class="on_right"><b class="tb2">统计信息</b> <span>定制舆情采集<a href="#"><span id="totalcount" style="padding:0 0 0 0"></span>条</a></span><span>新闻<a href="#"><span id="newscount" style="padding:0 0 0 0"></span>条</a></span><span>论坛<a href="#"><span id="bbscount" style="padding:0 0 0 0"></span>条</a></span>
    <span>微博<a href="#"><span id="weibocount" style="padding:0 0 0 0"></span>条</a></span><span>博客<a href="#"><span id="blogcount" style="padding:0 0 0 0"></span>条</a></span><span>电子报刊<a href="#"><span id="journalcount" style="padding:0 0 0 0"></span>条</a></span><span>微信<a href="#"><span id="weixincount" style="padding:0 0 0 0"></span>条</a></span></div>
  </div>
</div>
<div class="bg">
  <div class="sj_tj">
    <div class="title_h1"><!--<span class="more"><a href="#">MORE</a></span>--> <span class="t_tit">数据统计</span> </div>
    <div class="kuang_in">
      <div class="on_left" id="count1" style="width:700px; height:222px;">
      		<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
      </div>
      <div class="on_right" id="count2" style="width:263px; height:222px;"></div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="bg">
  <div class="kuang_1">
  <!-- 舆情预警-->
    <div class="yq_yj">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/warning/listWarning">MORE</a></span> <span class="t_tit">舆情预警</span> </div>
      <div class="kuang_in">
        <div class="list" id="listWarning">
        	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
    
    <!-- 定制舆情-->
    <div class="yq_yq">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/subject/toListSubjectSentiment?timeMethod=5">MORE</a></span> <span class="t_tit">定制舆情</span> </div>
      <div class="kuang_in">
        <div class="list" id="listsubjectsentiment">
          <div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
    <#if userfront.userType==2>
    <!-- 政务舆情-->
    <div class="yq_rbi">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/event/toListEvent?timeMethod=5">MORE</a></span> <span class="t_tit">政务舆情</span> </div>
      <div class="kuang_in">
        <div class="list" id="listeventnews">
         	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>

  </div>
  <#else>
  <!-- 行业舆情-->
   <div class="yq_rbi">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/industry/toListIndustry?timeMethod=5">MORE</a></span> <span class="t_tit">行业舆情</span> </div>
      <div class="kuang_in">
        <div class="list" id="listindustrynews">
         	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>

  </div>
  
  </#if>
  <div class="kuang_2">
    <div class="al_dy">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/thinktank/listPubinfo">MORE</a></span> <span class="t_tit">案例推荐</span> </div>
      <div class="kuang_in">
        <div class="list" id="listpubinfo">
          	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
    <!--舆情日报 -->
    <div class="rd_xw">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/report/listReport">MORE</a></span> <span class="t_tit">舆情日报</span> </div>
      <div class="kuang_in">
        <div class="list" id="listreport">
       		 <div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
         		<p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
   <#if userfront.userType==2>
    <div class="gg_zt">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/focus/listFocus">MORE</a></span> <span class="t_tit">公共专题</span> </div>
      <div class="kuang_in">
        <div class="list" id="listfocus">
        	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
    <#else>
    <!--企业舆情简报 -->
    <div class="gg_zt">
      <div class="title_h1"><span class="more"><a target="_blank" href="${request.getContextPath()}/briefreport/listBriefreport">MORE</a></span> <span class="t_tit">舆情简报</span> </div>
      <div class="kuang_in">
        <div class="list" id="listBriefreport">
        	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
      </div>
    </div>
    
    </#if>
  </div>
  
</div>
<div id="bar"></div>
 
 
 
 
 
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
