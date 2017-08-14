 	<#list pagination.list as list>	
 		 <dl>
            <dd><em>(${list.region})</em><span>${list.producedate?string("MM-dd HH:mm:ss")}</span></dd>
            <dt>
            	<a target="_blank" title="${list.name}" href="${request.getContextPath()}/focus/listFocusInfo/${list.id}" >
	             <#if list.name?length lt 9> 
	              	${list.name}
	             <#else> 
	    		 	${list.name[0..8]}... 
	            </#if>
            </a>
            </dt>
          </dl>
</#list>