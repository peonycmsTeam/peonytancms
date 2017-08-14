 	<#list pagination.list as list>	

          <dl>
            <dd><b>
            <#if list.website?length lt 4> 
	             ${list.website}
	        <#else> 
	    		 ${list.website[0..3]}
	        </#if>
            </b><span>
            <#if list.publishdate?has_content>
            	${list.publishdate?string("MM-dd HH:mm:ss")}
            </#if>
            </span>
            </dd>
            <dt>
             <a target="_blank" title=${list.title?html} href="${request.getContextPath()}/subject/loadSubjectPage?currentId=${list.id}&groupseedid=${list.groupseedid}">
	            <#if list.title?length lt 31> 
	              ${list.title?html}
	            <#else> 
	    		 ${list.title[0..30]?html}... 
	            </#if>
	            </a>
             </dt>
          </dl>
</#list>