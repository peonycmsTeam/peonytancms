<script type="text/javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/subject/addNodes.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<link href="${request.getContextPath()}/css/jbox/jbox.css" rel="stylesheet" type="text/css" id="jbox"/>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<form id="addNodeform" >
<table >
	<tr>
		<td align="right">所属节点：</td>
		<td>
			${subject.name }
			<input type="hidden" id="subjectId" name="pid" value="${subject.id }"/>
		</td>
	</tr>
	<tr>
		<td align="right">分类名称：</td>
		<td>
			<input type="text" name="name"   />
		</td>
	</tr>
	
	<tr>
		<td><input type="button" id="addBtn" value="添加" /></td>
	</tr>
	</table>
</form>
