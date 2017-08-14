<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/topic/topicpage.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
         <form   id="selecttime">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>开始时间:</th>
              <td>
                 <input class="inputxsf" type="text" name="stime" id="stime" readonly="true"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*" nullmsg="请选择开始时间"/>
              </td>
            </tr>
             <tr>
              <th>结束时间:</th>
              <td>
                  <input class="inputxsf" type="text" name="etime" id="etime" readonly="true"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*" nullmsg="请选择结束时间"/>
              </td>
            </tr>
            </table>
            <div class="HackBox"></div><div class="aniu_xinxi_fab">
			<input class="tb2 fabiao1 color_bai" type="button" id="selBtn" value="确定" onclick="selectTime(${val});"/>
			<input class="tb2 fabiao2 color_bai" type="button" onclick="parent.closeJbox();" value="返回" />
			</form>
                </div>
                <div class="HackBox"></div>
        </div>