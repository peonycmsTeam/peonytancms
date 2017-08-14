
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script>  
<script type="text/javascript" language="javascript">
function openInfo(pageid){
		window.open("${request.getContextPath()}/focuspage/getFocusPageById/"+pageid);
	}
	
</script>
        <div class="list">
         <#list list.list as focuspage>  
          <dl>
            <dd><b>来源：${focuspage.website}</b><span>发布时间：${focuspage.publishdate?datetime}</span></dd>
            <dt><a onclick="openInfo('${focuspage.id}');"  href="#">${focuspage.title}</a></dt>
          </dl>
         </#list>
        </div>
 