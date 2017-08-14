<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>舆情关键词</title>
<script src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/subject/subject.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
<form id="addUserkeywsform" method="post" >
<input type="hidden" id="pageContext" value="${request.getContextPath()}">
<input type="hidden" id="userkeywsId" name="userkeywsId" value="${userkeyws.id}">
<table id="table">
<tr>
	<td align="right">
		分类名称：
	</td>
	<td>
		<span  >${userkeyws.name}</span>
	</td>
</tr>
<tr>
	<td align="right">
		地域名称：
	</td>
	<td>
		<textarea type="text" name="area" id="area" <#if role> disabled="disabled" </#if> >${area}</textarea>
	</td>
	<!--
	<td>
		<#if role==true>
			<input type="button" id="selectArea" value="选择地域"/>
		</#if>
	</td>
	-->
</tr>
<tr>
	<td align="right">
		主关键词：
	</td>
	<td>
		<textarea type="text" name="mainKeywords"  id="mainKeywords" >${mainKeywords}</textarea>
	</td>
	
</tr>
<tr>
	<td align="right">
		倾向性分析词：
	</td>
	<td>
		<textarea type="text" name="deputyKeywords" id="deputyKeywords">${deputyKeywords}</textarea>
	</td>
	<td>
		<input type="button"  value="添加" onclick="addruleout(this);"/>
	</td>
</tr>
<tr>
	<td align="right">
		过滤词：
	</td>
	<td>
		<textarea type="text" name="ruleoutKeywords" id="ruleoutKeywords">${ruleoutKeywords}</textarea>
	</td>
	<td>
		<input type="button"  value="添加" onclick="addruleout(this);"/>
	</td>
</tr>
<#if ruleoutKeywordsAppendOne?exists>  
	<tr>
	<td >
	</td>
	<td>
		<textarea type="text" name="ruleoutKeywordsAppendOne" id="ruleoutKeywordsAppendOne">${ruleoutKeywordsAppendOne}</textarea>
	</td>
</tr>
</#if>
<#if ruleoutKeywordsAppendTwo?exists>  
	<tr>
	<td >
	</td>
	<td>
		<textarea type="text" name="ruleoutKeywordsAppendTwo" id="ruleoutKeywordsAppendTwo">${ruleoutKeywordsAppendTwo}</textarea>
	</td>
</tr>
</#if>
<tr>
<td>
<input type="button" id="save"  value="修改" onclick="saveSubjectKeywords();"/>
<input type="button" onclick="changeNode();" value="置为根节点" />
</td>
</tr>
</table>
</form>
</body>
</html>
