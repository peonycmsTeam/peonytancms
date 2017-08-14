<#list pagination.list as list>	
 		<dl>
            <dd><b> </b><span><a target="_blank" href="${request.getContextPath()}/briefreport/downloadBrifreport?briefreportId=${list.briefreportId}" >下载</a></span></dd>
            <dt>${list.name}</dt>
        </dl>
</#list>