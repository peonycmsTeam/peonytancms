<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/subject/list_subject_sentiment_tree.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
         <form   id="selecttime">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
          <input type="hidden" id="ids" value="${ids}"/>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td colspan="2">
            <input type="radio"  name="isColspan" value="0" checked/>相似信息合并显示
           <input type="radio"  name="isColspan" value="1"/>相似信息单独显示
           </td>
            </tr>
            
            </table>
            <div class="HackBox"></div>
            <div class="aniu_xinxi_fab" style="float:left;">
			<input class="tb2 fabiao1 color_bai" type="button" id="selBtn" value="确定" onclick="selectWay();"/>
			<input class="tb2 fabiao2 color_bai" type="button" onclick="parent.closeJbox();" value="返回" />
			</div>
			</form>
                </div>
                <div class="HackBox"></div>
        </div>