
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>预警关键词设置</title>
<#include "/common/global_css.ftl">
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;系统配置&nbsp;&gt;&nbsp;预警关键词列表
</div>
</div>
<div class="bg">
  <div class="fl_br fl_br_peizhi">
    <div class="fl_br_in">
       <!--左侧导航-->
      <#include "/common/left.ftl">
      <div class="fl_right">
      	<form name="form1" id="form1" action="${request.getContextPath()}/system/toListWarningkeyws" method="post" >
      	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
        <div class="fl_right_in">
        	<div class="tianjiax">
				<div class="tb2 color_bai zt_lb_add2xx"><a style="cursor:pointer;" href="javascript:" onclick="addWarningkeyws();">添加</a></div>
        		<div class="HackBox"></div>
        	</div>
        	<div class="baikuang"> 
          	<script type="text/javascript">
		 	$(document).ready(function() {
	 			$(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
   			});
        </script>
        <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr>
		    <th class="td_left">名称</th>
		    <th>操作</th>
		    </tr>
		    <#list pagination.list as Warningkeyws>
		  	<tr>
			    <td class="td_left"><a href="javascript:" onclick="showWarningkeyws(${Warningkeyws.warningkeywsId});" >${Warningkeyws.name}</a></td>
			    <td class="sc_w">
			    	<span class="tb2 sc_1" style="cursor:pointer;" onclick="deleteWarningkeyws(${Warningkeyws.warningkeywsId})" >删除</span>
			    	<span class="tb2 sc_2" style="cursor:pointer;" onclick="updateWarningkeyws(${Warningkeyws.warningkeywsId})" >修改</span>
			    </td>
		    </tr>
		    </#list>
        </table>
      </div>
        <!--分页-出开始--->
            <#if pagination.totalCount gt 0 >
            <div class="dataTables_wrapper no-footer">
              <div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
              <a <#if pagination.pageNo==1 > class="paginate_button previous disabled" <#else> class="paginate_button previous" </#if> 
              aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous"
              href="javascript:" onclick="setPageNo(1);" >首页</a>
              <span>
              	<#if pagination.totalPage gt 5 >
					<#if pagination.totalPage-pagination.pageNo gt 2 >
						<#if pagination.pageNo gt 2 >
							<#list pagination.pageNo-2..pagination.pageNo+2 as i>
								<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             			<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  		href="javascript:" onclick="setPageNo(${i});">${i}</a>
							</#list>
						<#else>
							<#list 1..5 as i>
			             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
			             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
			             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  							</#list>
						</#if>
					<#else>
						<#list pagination.totalPage-4..pagination.totalPage as i>
							<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
						</#list>
					</#if>
				<#else>
					<#list 1..pagination.totalPage as i>
	             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
	             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
	             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  					</#list>
				</#if>
              </span>
              <a <#if pagination.pageNo==pagination.totalPage > class="paginate_button next disabled" <#else> class="paginate_button next" </#if> 
              aria-controls="example" data-dt-idx="7" tabindex="0" id="example_next"
               href="javascript:" onclick="setPageNo(${pagination.totalPage});" >尾页</a>
              </div>
            </div>
            </#if>
            <!--分页---结束---> 
      	</div>
      </div>
      </form>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/system/list_warningkeyws.js"></script>
</body>
</html>
