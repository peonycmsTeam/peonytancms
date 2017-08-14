
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>定制舆情</title>
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
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>
  <a href="${request.getContextPath()}/homepage/listHomePage">&nbsp;首页&nbsp;</a>&gt;
  <a href="${request.getContextPath()}/system/toEditEvent">&nbsp;系统配置&nbsp;</a>&gt;
  <a href="${request.getContextPath()}/system/toEditSubject">&nbsp;定制舆情</a></div>
</div>
<div class="bg">
  <div class="fl_br fl_br_peizhi">
    <div class="fl_br_in">
      <!--左侧导航-->
      <#include "/common/left.ftl">
      <div class="fl_right">
        <div class="fl_right_in">
         <div class="baikuang"> 
	         <div class="on_lft_x">
	         	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="treeTable">
	         		<tr >
	         			<td colspan="2" align="center" id="KeyWordsCount">
	         				当前用户总共可以设置<font color="red">${total}</font>个关键词，已设置<font color="red">${currentKeyWords}</font>个，剩余<font color="red">${total-currentKeyWords}</font>个
  						</td>
	         		</tr>
					<tr>
			    		<!--左侧目录树-->
				    	<td class="treeTd" width="160" valign="top">
					        <ul class="tree_ul ztree" id="subjectTree">
					        </ul>
				    	</td>
				    	<td class="org_Table" valign="top" id="viewDiv"></td>
					</tr>
				</table>
	         </div>
          <div class="HackBox"></div>
          <div class=" blank20px"></div>
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
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/system/list_subject.js"></script>
<script type="text/javascript">

</script>
</body>
</html>
