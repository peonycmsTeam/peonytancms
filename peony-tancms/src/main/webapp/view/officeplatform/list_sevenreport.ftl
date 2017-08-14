<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办公平台</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/officeplatform/mail.js"></script>
<script>
function view(reportId){
jBox("iframe:${request.getContextPath()}/report/loadView?reportId="+reportId, {
	    title: "日报预览",
	    width: 930,
	    height: 450,
		top: '15%',
		id:'showWebInfo',
	    buttons: {}
	});
	}
</script>
</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;办公平台&nbsp;&gt;&nbsp;近七日日报 </div>
</div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
        <div class="fl_left_in">
          <div class="fenleitou"></div>
          <div class="bgpt_left color_hongse_n">
            <ul>
               <li class="tb2 bgpt_1"><a href="${request.getContextPath()}/mail/toSendMail">写 邮 件</a></li>
              <li class="tb2 bgpt_3"><a href="${request.getContextPath()}/mail/listMail">发 件 箱</a></li>
              <li class="tb2 bgpt_4"><a href="${request.getContextPath()}/warning/listSevenWarning">近七日预警</a></li>
              <li class="tb2 bgpt_5 this"><a href="${request.getContextPath()}/report/listSevenReport">近七日日报</a></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="fl_right">
        <div class="shoucang_wx">
          <div class="fl_right_in">
        <div class="baikuang"> 
          <script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
   $(".web_zhuanzai tr").find("th:first,td:first").addClass("add_left2")
  });
        </script>
         <form action="listSevenReport" method="post" id="search">
            	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
         </form>
        <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th>名称</th>
    <th>下 载</th>
    </tr>
    <#list pagination.list as report>
  <tr>
    <td><a onClick="view(${report.reportId});" href="#">${report.title}</a></td>
    <td class="sc_w"><span class="tb2 xsc_1"><a href="#" onClick="view(${report.reportId});">预览</a></span><span class="tb2 xsc_2"><a href="http://rb.peonydata.com/report/${report.address}" >word下载</a></span></td>
    </tr>
  </#list>
 
        </table>

 
      </div>
       
      </div></div>
        </p>
       
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
