<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<#include "/common/global_css.ftl">
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script>
function view(reportId){
jBox("iframe:${request.getContextPath()}/report/loadView?reportId="+reportId, {
	    title: "预览",
	    width: 910,
	    height: 600,
		top: '15%',
		id:'showWebInfo',
	    buttons: {}
	});
}
</script>
</head>

<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;舆情日报</div>
</div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
       
        <div class="shoucang_wx">
          <div class="fl_right_in">
        <div class="baikuang"> 
          <script type="text/javascript">
			 $(document).ready(function() {
	 		 $(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
	   		 $(".web_zhuanzai tr").find("th:first,td:first").addClass("add_left2")
  			});
          </script>
          <form action="listReport" method="post" id="search">
	        	 <input type="hidden" id="pageNo" name="pageNo" value="1">
	      </form>
	      
         <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			   <th class="td_left">名称</th>
			   <th>下 载</th>
			</tr>
		    <#list pagination.list as list>	
			<tr>
				<td class="td_left">${list.title}</td>
				<td class="sc_w"><span class="tb2 xsc_1"><a href="#" onClick="view(${list.reportId});">预览</a></span><span class="tb2 xsc_2"><a href="http://rb.peonydata.com/report/${list.address}" >word下载</a></span><!--<span class="tb2 xsc_3">pdf下载</span>--></td>
			</tr>
			</#list>
			<#if pagination.list?size<10 >
				<#list 0..9-pagination.list?size as i>
	            	<tr>
					    <td class="td_left"><span class=""></span></td>
					    <td class="sc_w"><span class=""></span><span class=""></span></td>
	  				</tr>
	  			</#list>
  			</#if>
        </table>
      </div>
        <!--分页-出开始--->
        <@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
      </div></div>
      
 
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
