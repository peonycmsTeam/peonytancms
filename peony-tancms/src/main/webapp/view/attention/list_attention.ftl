<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的收藏</title>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"  src="${request.getContextPath()}/js/attention/attention.js"></script>
<script type="text/javascript"  src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" ></script>
</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;我的收藏</div>
 </div>
<div class="bg"><div class="blank8px"></div><div class="shoucang_wx">
  	<div class="fl_right_in">
  	<!-- -->
        <div class="tianjiax">
       <span onclick="toAddAttention();"> <div class="tb2 color_bai zt_lb_add2xx grzx_znxx3_a">添加</div></span>
         <div class="HackBox"></div>
        </div>
  	<!-- -->
    	<div class="baikuang">
	        <script type="text/javascript">
			 	$(document).ready(function() {
		 			$(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
		   			$(".web_zhuanzai tr").find("th:first,td:first").addClass("add_left").addClass("color_hongse_n")
		  		});
	        </script>
	        <form action="listAttention" method="post" id="search">
	        	  <input type="hidden" id="pageNo" name="pageNo" value="${pagination.pageNo}"/>
      		  <input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
	        </form>
        	<table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
			  	<tr>
			    	<th>收藏夹名称</th>
			    	<th>创建时间</th>
			    	<th>操作</th>
			  	</tr>
		  	<#list pagination.list as attention>  
  				<tr>
    				<td class="shoucang"><span class="tb2"><a href="listAttentionInfoByAttentionId?attentionId=${attention.attentionId}">${attention.name}</a></span></td>
    				<td><#if attention.time?has_content>${attention.time?string("yyyy-MM-dd HH:mm:ss")}</#if> </td>
				    <td class="sc_w">
				    	<a href="javascript:delAttention(${attention.attentionId});"><span class="tb2 sc_1">删除</span></a>
				    	<a href="javascript:editAttention(${attention.attentionId});"><span class="tb2 sc_2">编辑</span></a>
				    	<span class="tb2 sc_3"><a href="${request.getContextPath()}/attention/downloadAttention?attentionId=${attention.attentionId}">下载</a></span>
					</td>
  				</tr>
			</#list>
			<#if pagination.list?size<10 >
			<#list 0..9-pagination.list?size as i>
             	<tr>
    				<td ></td>
    				<td></td>
				    <td class="sc_w">
				    	<span class=""></span>
				    	<span class=""></span>
				    	<span class=""></span>
					</td>
  				</tr>
  			</#list>
  			</#if>
  			</table>
      	</div>
          <!--分页-出开始--->
        <@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
      	</div></div>
	</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
