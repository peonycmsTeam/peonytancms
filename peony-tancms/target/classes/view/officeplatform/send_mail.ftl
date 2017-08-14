<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办公平台</title>
<#include "/common/global_css.ftl">

</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;办公平台&nbsp;&gt;&nbsp;写邮件 </div>
</div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
        <div class="fl_left_in">
          <div class="fenleitou"></div>
          <div class="bgpt_left color_hongse_n">
            <ul>
              <li class="tb2 bgpt_1 this"><a href="${request.getContextPath()}/mail/toSendMail">写 邮 件</a></li>
              <li class="tb2 bgpt_3"><a href="${request.getContextPath()}/mail/listMail">发 件 箱</a></li>
              <li class="tb2 bgpt_4"><a href="${request.getContextPath()}/warning/listSevenWarning">近七日预警</a></li>
              <li class="tb2 bgpt_5"><a href="${request.getContextPath()}/report/listSevenReport">近七日日报</a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="fl_right">
        <div class="fenxileibiao">
          <div class="fl_right_in">
            <div class="baikuang">
              <div class="fl_right_in">
                <div class="xie_mail">
                <form action="sendMail" method="post" id="sendMail">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <th>收件人</th>
                      <td><div class="xie_mail_input">
                          <input type="text" name="receiveUser" id="textfield2" datatype="e" errormsg="请输入正确的邮箱" nullmsg="请填写信息"/>
                        </div></td>
                    </tr>
                    <tr>
                      <th>主　题</th>
                      <td><div class="xie_mail_input">
                          <input type="text" name="title" id="textfield2" datatype="*" nullmsg="请填写主题"/>
                        </div></td>
                    </tr>
                    <tr>
                      <td colspan="2"><label for="textarea"></label>
                        <textarea class="xie_textarea" name="content" id="textarea" cols="45" rows="5" datatype="*" nullmsg="邮件内容不能为空"></textarea></td>
                    </tr>
                    <tr>
                      <td colspan="2"><div class="aniu_xinxi_fab">
                          <input id="sendBtn" class="tb2 fabiao1 color_bai" type="button" value="发送" />
                          <input name="重置" type="reset" class="tb2 fabiao2" value="取消" />
                        </div></td>
                    </tr>
                  </table>
                  </form>
                </div>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript"  src="${request.getContextPath()}/js/officeplatform/sendMail.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
</body>
</html>
