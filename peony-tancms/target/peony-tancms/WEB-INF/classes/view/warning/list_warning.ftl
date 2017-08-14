<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>舆情预警</title>
<#include "/common/global_css.ftl">
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/warning/warning.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;舆情预警</div>
</div>
<div class="bg">
  <div class="fenxileibiao_2">
    <div class="gongji">
    <form id="search" action="listWarning" method="post">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <input type="hidden" id="pageNo" name="pageNo" value="${warnings.pageNo}"/>
      <input type="hidden" id="totalCount" name="totalCount" value="${warnings.totalCount}">
        
        <tr>
                <th>&nbsp;</th>
                <th><div class="tb2 xuanze2">
                    <select name="waringType" onChange="submitForm();">
                    	<option value="0" <#if waringType=0>selected="selected"</#if>>全部</option>
                    	<#list dictonary as dictonary>
                    	<option value="${dictonary.value}" <#if waringType=dictonary.value>selected="selected"</#if>>${dictonary.name}</option>
                    	</#list>
                    </select>
                    </div>
                  <div class="tb2 xuanze2">
                    <select name="polarity" onChange="submitForm();">
                    	<option value="2" <#if polarity='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if polarity='1'>selected="selected"</#if>>正面</option>
 						<option value="-1" <#if polarity='-1'>selected="selected"</#if>>负面</option>
 						<option value="0" <#if polarity='0'>selected="selected"</#if>>争议</option>
                    </select>
                   </div>
                  <div class="tb2 xuanze2">
                     <select name="isRead" onChange="submitForm();">
                    	<option value="2" <#if isRead='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if isRead='1'>selected="selected"</#if>>已读</option>
 						<option value="0" <#if isRead='0'>selected="selected"</#if>>未读</option>
                    </select>
                    </div>
                  <div class="tb2 xuanze2">
                     <select name="selectDate" onChange="submitForm();">
                    	<option value="1" <#if selectDate='1'>selected="selected"</#if>>全部</option>
                    	<option value="2" <#if selectDate='2'>selected="selected"</#if>>近一天</option>
 						<option value="3" <#if selectDate='3'>selected="selected"</#if>>近三天</option>
 						<option value="4" <#if selectDate='4'>selected="selected"</#if>>近七天</option>
 						<option value="5" <#if selectDate='5'>selected="selected"</#if>>一个月</option>
                    </select>
                    </div>
                  <div class="tb2 xuanze2">
                    <select name="type" onChange="submitForm();">
                    	<option value="2" <#if type='2'>selected="selected"</#if>>全部</option>
                    	<option value="1" <#if type='1'>selected="selected"</#if>>自动预警</option>
 						<option value="0" <#if type='0'>selected="selected"</#if>>手工预警</option>
                    </select>
                    </div>
                  <div class="tb2 xuanze2">
                    <select id="pageSize" name="pageSize" onChange="submitForm();">
                    	<option value="10" <#if pageSize='10'>selected="selected"</#if>>10</option>
                    	<option value="50" <#if pageSize='50'>selected="selected"</#if>>50</option>
 						<option value="100" <#if pageSize='100'>selected="selected"</#if>>100</option>
 						<option value="200" <#if pageSize='200'>selected="selected"</#if>>200</option>
                    </select>
                    </div>
                     <div class="tb2 xuanze2">
                    <select  name="time" onChange="submitForm();">
                    	<option value="1" <#if time=='1'>selected="selected"</#if>>预警时间</option>
                    	<option value="2" <#if time=='2'>selected="selected"</#if>>发布时间</option>
                    </select>
                    </div>
                  <div class="tb2 xuanze2">
                     <input type="button" value="删除" onClick="del();">
                 </div>
                 </th>
              </tr>
      </table>
      </form>
    </div>
</script>
    <script type="text/javascript">
		 $(document).ready(function() {
 $(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
   $(".web_zhuanzai tr").find("td:first").addClass("add_left").addClass("color_hongse_n")
  });
        </script>
   <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <tr>
       <th><input type="checkbox" name="checkAll" id="checkbox" onclick="checkAll(this);"/>全选</th>
    <th>预警标题</th>
    <th>来源</th>
     <th>发布时间</th>
      <th>预警时间</th>
     <th>预警类型</th>
    </tr>
    
  <#list warnings.list as warnings>
  	<tr>
  		<td><input type="checkbox" name="checkbox" id="checkbox" value="${warnings.warningId}"/></td>
    	 <td class="td_left <#if warnings.isRead!=1>color_hongse</#if>" ><a title="${warnings.title}" onclick="openInfo('${warnings.warningId}');" href="javascript:void(0);"><#if warnings.title?length lt 25>${warnings.title}<#else>${warnings.title[0..24]}...</#if></a></td>
    	<td><span title="${warnings.website}"><#if warnings.website?length lt 8>${warnings.website}<#else>${warnings.website[0..7]}...</#if></span></td>
    	<td>${warnings.publishdate?string("MM-dd HH:mm:ss")}</td>
    	<td>${warnings.warnTime?string("MM-dd HH:mm:ss")}</td>
    	<td class="caozuo"><#if warnings.type=0>手工预警</#if><#if warnings.type=1>自动预警</#if></td>
    </tr>
  </#list>
  <#if warnings.list?size<10 >
			<#list 0..9-warnings.list?size as i>
             	<tr>
    				<td></td>
    				 <TD class="td_left color_hongse"></td>
    				<TD class="caozuo"></td>
    			</tr>
  			</#list>
  			</#if>
   </table>

  </div>
</div>
      	</div></div>
	</div>
 		<!--分页-出开始--->
       <@selectPage.pagination pagination=warnings formName="search"/>
        <!--分页---结束---> 
      	</div></div>
	</div>
<div class="blank5px"></div>

<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript">
function openInfo(pageid){
		window.open("${request.getContextPath()}/warning/getWarningInfo/"+pageid);
	}
</script>
</body>
</html>
