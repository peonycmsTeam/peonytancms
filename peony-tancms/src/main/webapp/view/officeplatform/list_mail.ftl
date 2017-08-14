<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办公平台</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/officeplatform/mail.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;办公平台&nbsp;&gt;&nbsp;发件箱 </div>
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
              <li class="tb2 bgpt_3 this"><a href="${request.getContextPath()}/mail/listMail">发 件 箱</a></li>
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
            <form action="listMail" method="post" id="search">
            	 <input type="hidden" id="pageNo" name="pageNo" value="${pagination.pageNo}"/>
      			<input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
            </form>
              <div class="fl_right_in color_hongse_n tb2 fajian_3">发件箱 &gt; 共 ${pagination.totalCount} 封邮件</div>
            </div>
            <div class="blank8px"></div>
            <div class="baikuang">
              <div class="fl_right_in">
              <div class="bgpt_top">
               
               		<input type="checkbox" name="checkbox" id="checkbox" onclick="checkAll();"/>
               		<input type="button" name="delBtn" id="delBtn"  value="删除" onclick="delAll();"/>
              
              
              </div>
                <div class="HackBox"></div>
                <div class="table_bgt">
               <script type="text/javascript">
	 $(document).ready(function() {
	 
 $(".bgpt_biqozhi").click(function(){   
   $(this).toggleClass("bgpt_biqozhi2")
    }) 
	 $(".table_bgt tr").hover(function(){   
   $(this).toggleClass("tr_addx")
   $(this).find(".bgpt_del").toggle()
    }) 
	
	
});


</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
					 <#list pagination.list as mail>
					 <tr >
                      <td class="fuxuan"><input type="checkbox" name="checkbox" id="checkbox" value="${mail.mailId}"/></td>
                      <td class="fuxuan2">
                      	<#if mail.state='1'><span class="tb2 bgpt_mail2"></span></#if>
                      	<#if mail.state='0'><span class="tb2 bgpt_mail4"></span></#if>
                      </td>
                      <td class="fuxuan3">${mail.receiveUser}</td>
                      <td class="fuxuan4_1"><span class="tb2 bgpt_del bgpt_del2" onclick="delMail(${mail.mailId});"></span></td>
                      <td class="fuxuan5">${mail.title}</td>
                      <td class="color_huise" style="width:300px;">${mail.sendTime?string("MM-dd HH:mm:ss")}</td>
                    </tr>
					 </#list>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
        </p>
        <!--内容输出---结束---> 
         <!--分页-出开始--->
      		<@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
         
      	</div></div>
	</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
