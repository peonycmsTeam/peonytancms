 	<#list pagination.list as list>	
          <dl>
            <dd><!--<em>(${list.author})</em>--><span>${list.time?string("MM-dd HH:mm:ss")}</span></dd>
            <dt><a title=${list.title} target="_blank" href="${request.getContextPath()}/thinktank/getInfoPubinfo?pubinfoId=${list.pubinfoId}" >
            <#if list.title?length lt 13> 
              ${list.title}
            <#else> 
    		 ${list.title[0..12]}... 
            </#if>
            </a></dt>
          </dl>
        
</#list>