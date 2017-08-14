<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
 <script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/briefreport/editBriefreport.js"></script>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
         <form   id="editBriefreport">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
           <input type="hidden" name="briefreportId" value="${briefreport.briefreportId}"/>
 			<input type="hidden" name="userId" value="${briefreport.userId}"/>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>简报名称:</th>
              <td>
                <input type="text" name="name" id="name"  value="${briefreport.name}" datatype="*" nullmsg="请填简报名称"/></td>
            </tr>
            <tr>
              <th>期数：</th>
               <td><input type="text" name="periods" value="${briefreport.periods}" datatype="*" nullmsg="请填简报期数"/></td>
            </tr>
            <tr>
              <th>单位名称：
                </th>
             <td><input type="text" name="company"  value="${briefreport.company}" datatype="*" nullmsg="请填单位名称"/></td>
            </tr>
            </table>
            
            <div class="HackBox"></div><div class="aniu_xinxi_fab">
			<input class="tb2 fabiao1 color_bai" type="button" id="editBtn" value="修改"  />
			<input class="tb2 fabiao2 color_bai" type="button" onclick="closeJbox();" value="返回" />
			</form>
                </div>
                <div class="HackBox"></div>
        </div>
