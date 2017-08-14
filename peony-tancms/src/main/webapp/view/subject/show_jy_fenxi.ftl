<div id="viewDiv" class="fl_right">
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<form name="form1" id="form1" action="${request.getContextPath()}/event/toListEvent" method="post" >
<input type="hidden" id="pageContext" name="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="subjectid" name="subjectid" value="${subjectid}"/>
<input type="hidden" id="pageNo" name="pageNo" value=""/>
<input type="hidden" id="title" name="title" value=""/>
<input type="hidden" id="type" name="type" value=""/>
<input type="hidden" id="polarity" name="polarity" value=""/>
<input type="hidden" id="isRead" name="isRead" value=""/>
<input type="hidden" id="time" name="time" value=""/>
<input type="hidden" id="pagesize" name="pagesize" value="10"/>
</form>
  <div class="neirong" id="fenxi"> 
      	<div class="k_silist2">
			<ul>
			<div class="x_jiazai">
	        <div class="tuxing_dengdai">
	        </div>
	        <p>正在加载</p>
	        </div>
			</ul>
      	</div>
		<div class="blank5px"></div>
        <div class="blank10px"></div>
	<script type="text/javascript">
    $(function() {
    	var pageContext = $("#pageContext").val();
    	$("#fenxi").load(pageContext + "/subject/JYshowfenxi?subjectid="+$("#subjectid").val()+"&timestamp="+Date.parse(new Date()));
	}) 
    </script>
</div>