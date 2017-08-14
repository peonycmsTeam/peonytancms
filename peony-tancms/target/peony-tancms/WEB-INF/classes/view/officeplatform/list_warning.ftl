<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>办公平台</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/officeplatform/mail.js"></script>
<script type="text/javascript"  src="${request.getContextPath()}/js/officeplatform/sevenWaring.js"></script>
</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;办公平台&nbsp;&gt;&nbsp;近七日预警 </div>
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
              <li class="tb2 bgpt_4 this"><a href="${request.getContextPath()}/warning/listSevenWarning">近七日预警</a></li>
              <li class="tb2 bgpt_5"><a href="${request.getContextPath()}/report/listSevenReport">近七日日报</a></li>
            </ul>
          </div>
        </div>
      </div>
      <form action="listSevenWarning" method="post" id="search">
             <input type="hidden" id="pageNo" name="pageNo" value="${pagination.pageNo}"/>
      <input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
      <div class="fl_right">
      <div class="fenxileibiao">
         <div class="yuqing_top">
        <ul><li class="tb2 color_bai li_biaoti">标 题</li>
           <li>
          <input class="sousuokuang" type="text" name="title" id="title" value="${title}"/></li>
          
             <input  type="button" name="button" id="button" value="提 交" onclick="submitForm();"/>
           </li>
           </ul> <div class="HackBox"></div></div>
          
        <div class="yuqing_list">
    
	<script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".yuqing_list tr:nth-child(odd)").addClass("td_red")
  $(".yuqing_list tr").find("td:first,th:first").addClass("td_one")
});
        </script>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <th><input type="checkbox" name="checkbox" id="checkbox" onclick="checkAll();"/>
                </th>
              <th class="color_hongse_n">全选</th>
              <th>
              <div style="float: left; height: 18px;margin-right: 12px;">
              		<input type="button" value="删除" onClick="del();">
             </div>
              
              		<select name="waringType" onChange="submitForm();">
                    	<option value="0" <#if waringType=0>selected="selected"</#if>>全部</option>
                    	<#list dictonary as dictonary>
                    	<option value="${dictonary.value}" <#if waringType=dictonary.value>selected="selected"</#if>>${dictonary.name}</option>
                    	</#list>
                    </select>
             
            
             	 <select name="polarity" onChange="submitForm();">
                    	<option value="2" <#if polarity='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if polarity='1'>selected="selected"</#if>>正面</option>
 						<option value="-1" <#if polarity='-1'>selected="selected"</#if>>负面</option>
 						<option value="0" <#if polarity='0'>selected="selected"</#if>>争议</option>
                 </select>
            
             
             	 <select name="type" onChange="submitForm();">
                    	<option value="2" <#if type='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if type='1'>selected="selected"</#if>>自动预警</option>
 						<option value="0" <#if type='0'>selected="selected"</#if>>手工预警</option>
                 </select>
            
             
             	<select name="isRead" onChange="submitForm();">
                    	<option value="2" <#if isRead='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if isRead='1'>selected="selected"</#if>>已读</option>
 						<option value="0" <#if isRead='0'>selected="selected"</#if>>未读</option>
                </select>
            
            
             	 <select id="pageSize" name="pageSize" onChange="submitForm();">
                    	<option value="10" <#if pageSize='10'>selected="selected"</#if>>10</option>
                    	<option value="50" <#if pageSize='50'>selected="selected"</#if>>50</option>
 						<option value="100" <#if pageSize='100'>selected="selected"</#if>>100</option>
 						<option value="200" <#if pageSize='200'>selected="selected"</#if>>200</option>
                  </select>
            
             
                    <select  name="time" onChange="submitForm();">
                    	<option value="1" <#if time=='1'>selected="selected"</#if>>预警时间</option>
                    	<option value="2" <#if time=='2'>selected="selected"</#if>>发布时间</option>
                    </select>
            
              <select name="export" id="export" onchange="exportWarning();">
						<option value="0">选择导出方式</option>
						<option value="1">导出所选(word)</option>
						<option value="2">时间段导出(word)</option>
						<option value="3">导出所选(excel)</option>
						<option value="4">时间段导出(excel)</option>
				  </select>
             </th>
            </tr>
             <#list pagination.list as warning>
            <tr>
              <td valign="top"><input type="checkbox" name="checkbox" value="${warning.warningId}" /></td>
              <td colspan="2"><h1 <#if warning.isRead!=1>class="color_hongse_n"</#if>><a  onclick="openInfo('${warning.warningId}');" href="javascript:void(0);" title="${warning.title}"><#if warning.title?length lt 30>${warning.title}<#else>${warning.title[0..29]}...</#if></a></h1>
                <p>${warning.summary}</p>
                <div class="laiyuan color_huise"><div class="on_right color_hongse_n">
                   
                      <div class="tb2 xuanze2">
                       <select id="${warning.warningId}" onchange="addOperation(this)">
                    	<option value ="0" >操作</option>
  							<option value="2" >加入专题</option>
  							<option value ="3" >加入收藏</option>
  							<option value ="4" >加入简报</option>
						</select>
                      </div>
                      </span>
                       </div>
                <em>来源:${warning.website}</em><em>预警时间:${warning.warnTime?string("yyyy-MM-dd HH:mm:ss")}</em>&nbsp;&nbsp;<em>发布时间:${warning.publishdate?string("yyyy-MM-dd HH:mm:ss")}</em></div></td>
            </tr>
          </#list>
          </table>
          </form>
         <!--分页-出开始--->
      		 <@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
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
<script type="text/javascript" language="javascript">
function openInfo(pageid){
		window.open("${request.getContextPath()}/warning/getWarningInfoToOffice/"+pageid);
	}
</script>
</body>
</html>
