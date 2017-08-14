<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<form name="form1" id="form1" action="${request.getContextPath()}/event/toListEvent" method="post" >
<input type="hidden" id="pageContext" name="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="subjectid" name="subjectid" value="${subjectid}"/>
<input type="hidden" id="userId" name="userId" value="${userId}"/>
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
		$("#fenxi").load(pageContext + "/industry/showfenxi?subjectid="+$("#subjectid").val()+"&userId="+$("#userId").val()+"&timestamp="+Date.parse(new Date()));
	}) 
	</script>
 </div>
