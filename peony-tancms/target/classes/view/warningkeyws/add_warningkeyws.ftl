<script type="text/javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/warningkeyws/addWarningkeyws.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<link href="${request.getContextPath()}/css/jbox/jbox.css" rel="stylesheet" type="text/css" id="jbox"/>

<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<form id="addWarningkeywsform" method="post" >
<input type="hidden" id="pageContext" value="${request.getContextPath()}">

<table >
<tr>
	<td align="right">
		分类名称：
	</td>
	<td>
		<input type="text" name="name"   />
	</td>
</tr>
<tr>
	<td align="right">
		地域名称：
	</td>
	<td>
		<textarea type="text" name="area"  ></textarea>
	</td>
</tr>
<tr>
	<td align="right">
		主关键词：
	</td>
	<td>
		<textarea type="text" name="mainKeyws"  ></textarea>
	</td>
</tr>
<tr>
	<td align="right">
		倾向性分析词：
	</td>
	<td>
		<textarea type="text" name="deputyKeyws"  ></textarea>
	</td>
</tr>
<tr>
	<td align="right">
		过滤词：
	</td>
	<td>
		<textarea type="text" name="ruleoutKeyws"  ></textarea>
	</td>
</tr>
<tr>
<td>
<input type="button" id="addBtn" value="添加" />
</td>
</tr>
</form>