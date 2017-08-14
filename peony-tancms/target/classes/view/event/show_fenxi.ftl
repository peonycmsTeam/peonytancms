<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<form name="form1" id="form1" action="${request.getContextPath()}/event/toListEvent" method="post" >
<input type="hidden" id="pageContext" name="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="regionID" name="regionID" value="${regionID}"/>
<input type="hidden" id="eventid" name="eventid" value="${eventid}"/>
<input type="hidden" id="polarityorientation" name="polarityorientation" value=""/>
<input type="hidden" id="timeMethod" name="timeMethod" value=""/>
<input type="hidden" id="pagesize" name="pagesize" value=""/>
<input type="hidden" id="type" name="type" />
<input type="hidden" id="pageNo" name="pageNo" />
</form>
<div class="fenxileibiao">
  <div class="title_h1">
    <ul>
      <li><span class="t_tit"><a href="javascript:" onclick="fenximySubmit();" >文章列表</a></span></li>
      <li class="this"><span class="t_tit1"><a href="javascript:" onclick="showfenxi();">分类分析</a></span></li>
    </ul>
  </div>
  <div class="neirong" id="fenxi"> 
      	<div class="k_silist2">
			<ul>
			<ul>
			<div class="x_jiazai">
	        <div class="tuxing_dengdai">
	        </div>
	        <p>正在加载</p>
	        </div>
			</ul>
			</ul>
      	</div>
		<div class="blank5px"></div>
        <div class="blank10px"></div>
  </div>
</div>