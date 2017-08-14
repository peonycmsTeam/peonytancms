
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript">  
function openInfo(pageid){
		window.open("${request.getContextPath()}/topicpage/getFocusPageById/"+pageid);
	}
	</script>
        <div class="list">
         <#list list.list as topicpage>  
          <dl>
            <dd><b>来源：${topicpage.website}</b><span>发布时间：${topicpage.publishdate?datetime}</span></dd>
            <dt><a onclick="openInfo('${topicpage.id}');" href="#"><#if topicpage.title?has_content>${topicpage.title}<#else>${topicpage.summary}</#if></a></dt>
          </dl>
         </#list>
        </div>
 