<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>舆情简报</title>
<#include "/common/global_css.ftl">
<script src="${request.getContextPath()}/js/utils/strUtils/dateUtil.js" type="text/javascript"></script>
</head>
<body>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;舆情简报</div>
 </div>
<div class="bg"><div class="blank8px"></div><div class="shoucang_wx">
  <div class="fl_right_in">
      
    <div class="yuqing_top">
   <form method="post" action="listBriefreport" id="search">
      <ul><li class="tb2 color_bai li_biaoti">标 题</li>
           <li>
         	 <input class="sousuokuang" type="text" name="name" id="textfield" value="${briefreport.name}"/>
         	 <input  type="hidden" name="pageNo" id="pageNo" value="${pagination.pageNo}"/>
         	  <input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
          </li>
           <li> 
             <input class="tb2 color_bai chaxun" type="submit" name="button"  value="搜 索"  />
           </li>
    </ul> 
     </form>
     <div class="HackBox"></div></div></div></div>
    <div class="blank8px"></div><div class="shoucang_wx">
          <div class="fl_right_in">
        <div class="tianjiax">

       <span onclick="toAddBriefreport();"> <div class="tb2 color_bai zt_lb_add2xx grzx_znxx3_a">添加</div></span>
        
        <div class="grzx_znxx3 yincang">
  <div class="tneirong_1">
    <table border="0" cellspacing="0" cellpadding="0" class="title_tc">
      <tr>
        <td class="title_tc1"></td>
        <td class="title_tc2">添加</td>
        <td class="guanbi" title="关闭"></td>
      </tr>
    </table>
    <div class="tneirong_2">
      <div class="tneirong_2_in">
        <div class="table_tanchu">
      
         <div class="HackBox"></div>
        </div>
      </div>
    </div>
  </div>
  
</div>
        
        
        <div class="HackBox"></div></div><div class="baikuang"> 
        
        
        
          <script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".web_zhuanzai tr:nth-child(odd)").addClass("add_odd")
   });
        </script>
        <table class="web_zhuanzai web_zhiku" width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th>简报名称</th>
    <th>期数
</th>
    <th>创建时间</th>
    <th>简报操作</th>
    </tr>
    <#list pagination.list as briefreport>
 	 <tr>
   		 <td><a href="${request.getContextPath()}/briefreportInfo/listBriefreportInfo?briefreportId=${briefreport.briefreportId}">${briefreport.name}</a></td>
   		  <td>${briefreport.periods}</td>
    	 <td>${briefreport.time?string("yyyy-MM-dd")}</td>
    	 <td class="sc_w">
    		<a href="#" onclick="delBriefreport(${briefreport.briefreportId});"> <span class="tb2 sc_1">删除</span></a>
    		<a href="#" onclick="toEditBriefreport(${briefreport.briefreportId});"> <span class="tb2 sc_2" >编辑</span></a>
    		<span class="tb2 sc_3"><a href="${request.getContextPath()}/briefreport/downloadBrifreport?briefreportId=${briefreport.briefreportId}">下载</a></span>
    	 </td>
    </tr>
 	</#list>
 		<#if pagination.list?size<10 >
			<#list 0..9-pagination.list?size as i>
             	<tr>
    				<td ></td>
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
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/briefreport/briefreport.js"></script>
</body>
</html>
