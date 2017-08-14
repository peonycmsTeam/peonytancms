<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智库</title>
<#include "/common/global_css.ftl">
<script type="text/javascript"  src="${request.getContextPath()}/js/thinktank/thinktank.js"></script>
</head>
<body>
<!----头部--->
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;智库&nbsp;&gt;&nbsp;处置经验</div>
 </div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
      <div class="fl_left_in">
      <div class="fenleitou"></div>
      <div class="sj_zs_l">
      <ul>
        <li ><a href="${request.getContextPath()}/thinktank/listPubinfo">案例库</a></li>
        <li class="this"><a href="${request.getContextPath()}/thinktank/listAllPubinfo">处置经验</a></li>
        </ul>
        </div>
      </div>
      </div>
      <div class="fl_right">
      <div class="fl_right_in"><div class="gongjux">
    		<form action="listAllPubinfo" method="post" id="search">
    			<input type="hidden" name="pageNo" id="pageNo" />
    		</form>
      	<div class="HackBox"></div>
      </div>
        <div class="baikuang">
            <script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
   $(".web_zhuanzai tr").find("td:first").addClass("add_left").addClass("color_hongse_n")
  });
        </script>
        <form action="listAllPubinfo" method="post" id="search">
  			<input type="hidden" id="pageNo" name="pageNo" value="1"/>
  		</form>
        <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th class="td_left">标题</th>
    <th>作者</th>
    <th>撰写时间</th>
    
    </tr>
    <#list pagination.list as pubinfo>
  		<tr>
   			 <td class="td_left"><a onclick="openInfo('${pubinfo.pubinfoId}');" href="javascript:void(0);">${pubinfo.title}</a></td>
    		 <td>${pubinfo.author}</td>
    		 <td>${pubinfo.time?string("MM-dd HH:mm:ss")}</td>
    	</tr>
 	</#list>
 	<#if pagination.list?size<10 >
			<#list 0..9-pagination.list?size as i>
             	<tr>
   				  <td>&nbsp;</td>
    			  <td>&nbsp;</td>
    			  <td>&nbsp;</td>
    			</tr>
  			</#list>
  	</#if>
  
        </table>
 
 
      </div>
              <!--分页-出开始--->
       <@selectPage.pagination pagination=pagination formName="search"/>
        <!--分页---结束---> 
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
		window.open("${request.getContextPath()}/thinktank/getInfoPubinfo?pubinfoId="+pageid);
	}
</script>
</body>
</html>
