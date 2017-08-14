<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>舆情分类</title>
<#include "/common/global_css.ftl">
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<div class="container">
	
    
    	
    <div class="header">
        <input type="button" value="新建" onclick="addNodes();">
        <input type="button" value="修改" onclick="editNodes();">
        <input type="button" onclick="delNode();" value="删除节点" />
     </div>
        
	<div style="float:left;"> 	
		<ul class="tree_ul ztree" id="userkeywsTree"></ul>
	</div>
	
	<div style="float:left;">     	
	 <iframe width="450" height="350" frameborder="0" id="viewframe" scrolling="auto" src=""></iframe>
	</div>		    	
		    
	
</div>
<script src="${request.getContextPath()}/js/subject/subject.js"></script>
<script src="${request.getContextPath()}/js/subject/addSubject.js"></script>
<SCRIPT type="text/javascript">

$(function() {
	 pageContext = $("#pageContext").val();
	function configTree() {
		setting = {data: {
				simpleData: {
					enable: true
				}
			},
			view : {
				expandSpeed : ""
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					currentNode = treeNode;
					loadView();
				}
			}
		};
		return setting;
	}
	$.fn.zTree.init($("#userkeywsTree"), configTree(),${nodeList});
});
	</SCRIPT>
</body>
</html>
