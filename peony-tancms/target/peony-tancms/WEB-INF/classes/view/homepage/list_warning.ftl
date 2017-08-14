<#list warnings.list as warnings>
        	 	<dl>
            		<dd>
            			<b>
            			 <#if warnings.website?length lt 4> 
	              			${warnings.website}
	            		 <#else> 
	    		 			${warnings.website[0..3]}
	            	 	 </#if>
            			</b>
            			<em><#if warnings.type=0>手工预警</#if><#if warnings.type=1>自动预警</#if></em>
            			<span>${warnings.warnTime?string("MM-dd HH:mm:ss")}</span>
            		</dd>
            		<dt><a title="${warnings.title}" target="_blank" href="${request.getContextPath()}/warning/getWarningInfo/${warnings.warningId}">
            		 <#if warnings.title?length lt 27> 
	              		${warnings.title}
	            	 <#else> 
	    		 		${warnings.title[0..26]}... 
	            	 </#if>
            		</a></dt>
          		</dl>
       		 </#list>