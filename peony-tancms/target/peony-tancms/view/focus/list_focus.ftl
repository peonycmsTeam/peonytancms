
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公共舆情</title>
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
<input type="hidden" id="firstRegionID" value="${firstRegionID}"/>
 <input type="hidden" id="pageNo" name="pageNo" value="1"/>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong>&nbsp;公共专题</div>
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
				        <ul class="tree_ul ztree" id="eventTree"></ul>
			    	</td>
				</tr>
		</table>
        </div>
      </div>
      <div class="fl_right" id="viewDiv">
			<div class="x_jiazai">
			<div class="tuxing_dengdai"></div>
				<p>正在加载</p>
			</div>	      
	  </div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/focus/focus.js"></script>
</body>
</html>
