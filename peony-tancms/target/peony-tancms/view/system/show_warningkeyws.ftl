
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
  <div class="fl_br fl_br_peizhi">
      <form name="form1" id="form1" action="${request.getContextPath()}/system/toListSubjectSentiment" method="post" >
          <div class="baikuang"> 
	         <div class="bushi_x bushi_x2">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <th>分类名称：</th>
		              <td><input name="textfield2" type="text" class="input_text" id="subjectName" name="subjectName" value="${warningkeyws.name}" /></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">地域名称：</th>
		              <td><textarea class="input_text2 input_text_r" name="area" id="area" cols="45" rows="5" disabled="disabled" >${warningkeyws.area}</textarea></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">主关键词:</th>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="main" id="main" cols="45" rows="5" disabled="disabled">${warningkeyws.mainKeyws}</textarea>
		              </td>
		            </tr>
		            <tr>
		              <th valign="top">倾向性分析词：</th>
		              <td>
		              	<textarea class="input_text2 input_text_r" name="qxx" id="qxx" cols="45" rows="5" disabled="disabled">${warningkeyws.deputyKeyws}</textarea>
		              </td>
		            </tr>
		            <tr>
		              <th valign="top">过滤词：</th>
		              <td>
		              	<textarea class="input_text2 input_text_r" name="glc" id="glc" cols="45" rows="5" disabled="disabled">${warningkeyws.ruleoutKeyws}</textarea>
		              </td>
		            </tr>
		        </table>
	        </div>
        	<div class="HackBox"></div>
        	<div class=" blank20px"></div>
          </div>
      <div class="HackBox"></div>
	</form>
  </div>
<div class="blank5px"></div>
</body>
</html>
