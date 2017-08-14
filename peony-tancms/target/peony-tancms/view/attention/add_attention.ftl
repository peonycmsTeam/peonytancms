<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/attention/addAttention.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
         <form   id="addAttention">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>收藏夹名称:</th>
              <td>
                 <input type="text" name="name" id="name" datatype="*" nullmsg="请填收藏夹名称" /></td>
            </tr>
            </table>
            <div class="HackBox"></div><div class="aniu_xinxi_fab">
			<input class="tb2 fabiao1 color_bai" type="button" id="addBtn" value="保存" />
			<input class="tb2 fabiao2 color_bai" type="button" onclick="closeJbox();" value="返回" />
			</form>
                </div>
                <div class="HackBox"></div>
        </div>
  
