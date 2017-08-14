<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>行业舆情</title>
<#include "/common/global_css.ftl">
<!--[if lte IE 6]>
	<script type="text/javascript" src="style/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<input type="hidden" id="firstIndustryId" value="${firstIndustryId}"/>
<!--头部-->
<#include "/common/top.ftl">
<!--头部结束-->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>
     <a href="${request.getContextPath()}/homepage/listHomePage">&nbsp;首页&nbsp;</a>&gt;
    <a href="${request.getContextPath()}/industry/toListIndustry">&nbsp;行业舆情&nbsp;</a>&gt;
    &nbsp;文章列表 
  </div>
</div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
		<div class="fl_left">
	      <div class="fl_left_in">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="treeTable">
				<tr>
		    		<!--左侧目录树-->
			    	<td class="treeTd" width="160" valign="top">
				        <ul class="tree_ul ztree" id="industryTree"></ul>
			    	</td>
				</tr>
			</table>
	      </div>
		</div>
		<div class="fl_right" >
			<div class="fenxileibiao">
		          <div class="title_h1">
		            <ul>
		              <li class="this" id="wenzhang"><span class="t_tit"><a href="javascript:" onclick="fenxisubmit(this)">文章列表</a></span></li>
		              <li><span class="t_tit1"><a href="javascript:" onclick="showfenxi(this)">分类分析</a></span></li>
		            </ul>
		          </div>
		          
		          <div class="yuqing_top" id="yuqing_top">
		            <ul>
		              <li class="tb2 color_bai li_biaoti">标 题</li>
		              <li>
		                <input class="sousuokuang" type="text" name="title" id="title" value=""/>
		              </li>
		              <li>
		                <input class="tb2 color_bai add" type="button" name="button" id="button" onclick="setPageNo(1)" value="提 交" style="cursor:pointer;"/>
		              </li>
		            </ul>
		            <div class="HackBox"></div>
		          </div>
		  		  <div class="yuqing_list" id="yuqing_list"> 
					<script type="text/javascript">
						$(document).ready(function() {
					 		$(".yuqing_list tr:nth-child(odd)").addClass("td_red")
					  		$(".yuqing_list tr").find("td:first,th:first").addClass("td_one")
						});
					</script>
		            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		              <tr>
		                <th><input type="checkbox" id="chk_all" onclick="checkAll(this);"/></th>
		                
		                <th class="color_hongse_n">全选</th>
		                <th>
		                  <select name="type" id="type" onchange="setPageNo(1)">
								<option value="0">全部</option>
								<#list webDictionaryList as webDictionary>
									<option value="${webDictionary.value}" >${webDictionary.name?default('&nbsp;')}</option>
								</#list>
						  </select>
						  <select name="polarity" id="polarity" onchange="setPageNo(1)">
								<option value="-2" >全部</option>
								<option value="1"  >正面</option>
								<option value="-1" >负面</option>
								<option value="0" >争议</option>
						  </select>
						  <select name="isRead" id="isRead" onchange="setPageNo(1)">
								<option value="-1">全部</option>
								<option value="0" >未读</option>
								<option value="1" >已读</option>
						  </select>
						  <select name="time" id="time" onchange="setPageNo(1)">
						  		<option value="5" <#if timeMethod==1> selected="selected" </#if> >全部</option>
								<option value="1" >近一天</option>
								<option value="2" <#if timeMethod==0> selected="selected" </#if> >近三天</option>
								<option value="3" >近七天</option>
								<option value="4" >一个月</option>
						  </select>
		                  <select name="pagesize" id="pagesize" onchange="setPageNo(1)">
								<option value="10" >10</option>
								<option value="50" >50</option>
								<option value="100">100</option>
						  </select>
						  <select name="export" id="export" onchange="exportSubject();" >
								<option value="0">选择导出方式</option>
								<option value="1">导出所选</option>
								<option value="2">时间段导出</option>
						  </select>
		                  <div class="tb2 color_bai tbfasongyouxiang"><a href="javascript:" onclick="toSendMail();"> 发送邮件</a></div>
		                  <div class="tb2 color_bai tbfasongyouxiang"><a href="javascript:" onclick="delete_SubjectPage()"> 删除</a></div>
		                  </th>
		              	</tr>
		          	  </table>
		           </div>
		     </div>	      
			<div id="viewDiv">
				<div class="x_jiazai">
					<div class="tuxing_dengdai"></div>
					<p>正在加载</p>
				</div>
			</div>
		</div>
	</div>
   </div>
</div>
<div class="blank5px"></div>
<!--底部开始-->
<#include "/common/bottom.ftl">
<!--底部结束-->
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/industry/list_industry.js"></script>
</body>
</html>
