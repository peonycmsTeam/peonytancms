<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/subject/list_subject_sentiment.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/subject/sendMail.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
  <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu"  style="position:relative;">
          <form   id="sendMail" menthod="post" action="${request.getContextPath()}/subject/sendMail">
          <input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
          <table border="0" cellspacing="0" cellpadding="0">
          <tr>
          	   <td>
               	 主题:<input size="35" type="text" name="title"  value="${title}"/>
              </td>
          </tr>
            <tr>
              <td>
               	 <input  type="hidden" name="ids" id="ids" />
                                                邮箱:<input size="35" type="text" name="receiveUser"  datatype="e" nullmsg="请填写正确的邮箱"/>
                 
              </td>
            </tr>
            </table>
            <div class="HackBox"></div>
            <div class="aniu_xinxi_fab">
				<input class="tb2 fabiao1 color_bai" type="button" id="subBtn" value="确定"/>
				<input class="tb2 fabiao2 color_bai" type="button" onclick="parent.closeJbox();" value="返回" />
			</form>
                </div>
                  <div id="wait"  style=" top:0px;left:140px;position:absolute;display:none;">
         				<div class="tuxing_dengdai" > </div>
         		  </div>
                <div class="HackBox"></div>
        </div>
        </div>
        </body>
  </html>
