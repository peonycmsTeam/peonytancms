
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
 	<#list pagination.list as list>	
 		<dl>
            <dd><b> <a href="#" onClick="view(${list.reportId});"> 预览</a></b><span><a target="_blank" href="http://rb.peonydata.com/report/${list.address}" >下载</a></span></dd>
            <dt>${list.title}</dt>
          </dl>
</#list>


<script>
function view(reportId){
jBox("iframe:${request.getContextPath()}/report/loadView?reportId="+reportId, {
	    title: "预览",
	    width: 930,
	    height: 450,
		top: '15%',
		id:'showWebInfo',
	    buttons: {}
	});
	}
</script>