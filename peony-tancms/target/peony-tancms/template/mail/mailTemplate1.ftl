<HTML>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<BODY style="padding:0; margin:0;">
<div style="background:#e7e7e7;">
  <DIV style="OVERFLOW: hidden; HEIGHT: 10px"></DIV>
  <DIV style=" FONT-SIZE: 12px; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; FONT-FAMILY: '宋体'; TEXT-ALIGN: center; ">
    <table width="780" height="31" border="0" cellspacing="0" cellpadding="0" style="margin:0 auto;border:1px solid #24262c; border-bottom:none;">
      <tr>
        <td height="200">
        <table width="100%" height="200" border="0" cellpadding="0" cellspacing="0" style=" MARGIN: 0px auto; TEXT-ALIGN: left">
            <tr>
              <td height="108" valign="top" ><img src="http://rb.peonydata.com/report/img/wyfl_nl_034.jpg" height="200"></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </DIV>
  <DIV style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 12px! important; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; FONT-FAMILY: '宋体' !important; TEXT-ALIGN: center; TEXT-DECORATION: none">
    <table width="780" border="0" cellpadding="0" cellspacing="0" style="MARGIN: 0px auto;TEXT-ALIGN: left; background:#ffffff; border:1px solid #24262c; border-top:none;">
      <tr>
        <td valign="top" style=" OVERFLOW: hidden; font-size:12px;"><DIV style="padding:0 10px 10px 9px">
            <#list list as list>
            <H2 style="PADDING:0;FONT-SIZE:16px;  MARGIN:10px 0 0 0;  background:#e9f3fd; LINE-HEIGHT:29px;LIST-STYLE-TYPE:none; HEIGHT:29px;FONT-WEIGHT: normal; padding-left:10px; clear:both;"><a href="<#if list.url?has_content>${list.url}</#if>" style="COLOR: #C4151C; TEXT-DECORATION: underline;font-family:'微软雅黑';"><#if list.title?length gt 45>${list.title[0..40]}<#else>${list.title}</#if></a></H2>
            <DL style="PADDING-RIGHT: 0px; BORDER-TOP: #fff 1px solid;  PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; LIST-STYLE-TYPE: none;padding-left:10px">
              <dt style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; MARGIN: 0px; COLOR: #323335; font-size:14px; LINE-HEIGHT: 21px; PADDING-TOP: 5px; LIST-STYLE-TYPE: none; TEXT-ALIGN: justify"><#if list.summary?has_content>${list.summary}</#if></dt>
              <dd style="padding:8px 20px 10px 0; margin:0; COLOR: #666; LINE-HEIGHT: 18px;  LIST-STYLE-TYPE: none; TEXT-ALIGN: justify; font-size:12px; line-height:18px; text-indent:0;">来源：<#if list.website?has_content>${list.website}</#if>  发布时间：  <#if list.publishdate?has_content><#assign date=list.publishdate?string("MM-dd HH:mm")>${date[0..1]}月${date[3..4]}日${date[6..7]}时${date[9..10]}分</#if></dd>
            </DL>
            </#list>
          </DIV>
          <DIV style=" WIDTH:100%; HEIGHT: 10px; clear:both;"></DIV></td>
      </tr>
    </table>
  </div>
  <DIV style="MARGIN: 0px auto; WIDTH: 780px; HEIGHT: 10px"></DIV>
  <DIV  
 
style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; FONT-SIZE: 12px! important; PADDING-BOTTOM: 0px; MARGIN: 0px; PADDING-TOP: 0px; FONT-FAMILY: '宋体'; TEXT-ALIGN: center; TEXT-DECORATION: none">
    <DIV style="MARGIN: 0px auto; WIDTH: 780px; TEXT-ALIGN: center; font-size:12px;">
      <!--<DIV style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 20px; COLOR: #666; PADDING-TOP: 5px; TEXT-ALIGN: center">版权所有<SPAN  
 
style="FONT-FAMILY: Arial, Helvetica, sans-serif">©1998-2014</SPAN> 牡丹舆情  
        
        中国北京</DIV>-->
    </DIV>
  </DIV>
</div>
</BODY>
</HTML>
