<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/modify_pwd.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
         <form   id="editPswForm">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>用户名:</th>
              <td>
                 <input class="inputxsf" type="text" name="loginName" value="${userfront.loginName}" disabled="true"/>
              </td>
            </tr>
             <tr>
              <th>原始密码:</th>
              <td>
                  <input class="inputxsf" type="password" name="oldPassword" id="oldPassword"  datatype="*" nullmsg="请填写原始密码"/>
              </td>
            </tr>
             <tr>
              <th>新密码:</th>
              <td>
                  <input class="inputxsf" type="password" name="newPassword" id="newPassword"  datatype="*" nullmsg="请填写新密码"/>
              </td>
            </tr>
            <tr>
              <th>重复输入新密码:</th>
              <td>
                  <input class="inputxsf" type="password" name="newPasswordconfirm" id="newPasswordconfirm"  datatype="*" nullmsg="请重复填写新密码"  recheck="newPassword" errormsg="您两次输入的密码不一致！"/>
              </td>
            </tr>
            </table>
            <div class="HackBox"></div><div class="aniu_xinxi_fab">
			<input class="tb2 fabiao1 color_bai" type="button" id="subBtn" value="确定" />
			<input class="tb2 fabiao2 color_bai" type="button" onclick="parent.$.jBox.close(true);" value="返回" />
			</form>
                </div>
                <div class="HackBox"></div>
        </div>