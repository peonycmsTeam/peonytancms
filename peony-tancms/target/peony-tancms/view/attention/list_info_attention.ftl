<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的收藏</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/attention/attention.js"></script>
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript"  src="${request.getContextPath()}/js/attention/addOther.js"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;我的收藏&nbsp;&gt;&nbsp;${attention.name}</div>
 </div>
 <div class="blank8px"></div>
<div class="bg">
 
    <div class="shoucang_wx yqjb_new">
  <div class="fl_right_in">
          
        <div class="yuqing_list">
    
	<script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".yuqing_list tr:nth-child(odd)").addClass("td_huise")
  $(".yuqing_list tr").find("td:first,th:first").addClass("td_one")
});
        </script>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <th><input type="checkbox" name="checkAll" id="checkbox" onclick="checkAll(this);"/>
                </th>
                <th class="color_hongse_n">全选</th>
              <th>
              <form action="listAttentionInfoByAttentionId" method="post" id="search">
              <input type="hidden" name="attentionId" value="${attention.attentionId}">
              <input type="hidden" id="pageNo" name="pageNo" value="${pagination.pageNo}"/>
      		  <input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
              <div class="tb2 xuanze2">
                    <select name="type" onChange="submitForm();">
                    	<option value="0" <#if type=0>selected="selected"</#if>>全部</option>
                    	<#list dictonary as dictonary> 
                    		<option value=${dictonary.value} <#if type=dictonary.value>selected="selected"</#if>>${dictonary.name}</option>
                    	</#list>
                    </select>
                    
               </div>
               <div class="tb2 xuanze2" >
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
                    <select id="pageSize" name="pageSize" onChange="submitForm();">
                    	<option value="10" <#if pageSize='10'>selected="selected"</#if>>10</option>
                    	<option value="50" <#if pageSize='50'>selected="selected"</#if>>50</option>
 						<option value="100" <#if pageSize='100'>selected="selected"</#if>>100</option>
 						<option value="200" <#if pageSize='200'>selected="selected"</#if>>200</option>
                    </select>
                 </div>
                  <div class="tb2 xuanze2">
                    <select  name="time" onChange="submitForm();">
                    	<option value="1" <#if time=='1'>selected="selected"</#if>>加入时间</option>
                    	<option value="2" <#if time=='2'>selected="selected"</#if>>发布时间</option>
                    </select>
                    </div>
                 </form>
                 <input type="button" value="删除" onClick="del();">
              </tr>
              <#list pagination.list as attentionInfo>
            <tr>
              <td valign="top"><input type="checkbox" name="checkbox" id="checkbox" value="${attentionInfo.attentionInfoId}"/></td>
              <td colspan="2"><h1 <#if attentionInfo.isRead=0>class="color_hongse_n"</#if>><a href="javascript:void(0);"  onclick="openInfo(${attentionInfo.attentionInfoId});" title="${attentionInfo.title}"><#if attentionInfo.title?length lt 30>${attentionInfo.title}<#else>${attentionInfo.title[0..29]}...</#if></a></h1>
                <p>${attentionInfo.summary}</p>
                <div class="laiyuan color_huise"><div class="on_right color_hongse_n">
                    <div class="tb2 xuanze3">
                    	<select id="${attentionInfo.attentionInfoId}" onchange="addOperation(this)">
                    		<option value ="0" >操作</option>
  							<option value ="1" >加入预警</option>
 							<option value ="2" >加入专题</option>
  							<option value="4" >加入简报</option>
						</select>
						
                       </div>
                   
                  </div>
                <em>来源:${attentionInfo.website}</em><em>加入时间:${attentionInfo.ptime?string("yyyy-MM-dd HH:mm:ss")}</em>&nbsp;&nbsp;<em>发布时间:${attentionInfo.publishdate?string("yyyy-MM-dd HH:mm:ss")}</em><!--<em>点击数:${attentionInfo.visitcount}</em><em>回复数：${attentionInfo.reply}</em>--></div></td>
            </tr>
          </#list>
          <#if pagination.list?size<10 >
			<#list 0..9-pagination.list?size as i>
             	<tr>
    				<td valign="top">&nbsp;</td>
    				<td colspan="2">&nbsp;</td>
  				</tr>
  			</#list>
  			</#if>
          </table>
         
</div></div> 

      <!--分页-出开始--->
      <@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
      	</div></div>
	</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript">
function openInfo(pageid){
		window.open("${request.getContextPath()}/attention/getAttentionInfoCnt/"+pageid);
	}
</script>
</body>
</html>
