
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
      <form name="form1" id="form1" action="${request.getContextPath()}/system/doUpdateWarningkeyws" method="post" >
		  <input type="hidden"  id="warningkeywsId" name="warningkeywsId" value="${warningkeyws.warningkeywsId}" />
          <div class="baikuang"> 
	         <div class="bushi_x bushi_x2">
		         <table border="0" cellspacing="0" cellpadding="0">
		            <tr>
		              <th>分类名称：</th>
		              <td><input type="text" class="input_text" id="name" name="name" value="${warningkeyws.name}" /></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">地域名称：</th>
		              <td><textarea class="input_text2 input_text_r" name="area" id="area" cols="45" rows="5">${warningkeyws.area}</textarea></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">主关键词:</th>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="mainKeyws" id="mainKeyws" cols="45" rows="5" >${warningkeyws.mainKeyws}</textarea>
		              </td>
		            </tr>
		            <tr>
		              <th valign="top">倾向性分析词：</th>
		              <td>
		              	<textarea class="input_text2 input_text_r" name="deputyKeyws" id="deputyKeyws" cols="45" rows="5" >${warningkeyws.deputyKeyws}</textarea>
		              </td>
		            </tr>
		            <tr>
		              <th valign="top">过滤词：</th>
		              <td>
		              	<textarea class="input_text2 input_text_r" name="ruleoutKeyws" id="ruleoutKeyws" cols="45" rows="5" >${warningkeyws.ruleoutKeyws}</textarea>
		              </td>
		            </tr>
		        </table>
		        <div class=" text_center">
				    <input name="修改" type="button" onclick="mySubmit()" class="tb2 fabiao1 color_bai" value="修改" />
		        </div>
	        </div>
        	<div class="HackBox"></div>
        	<div class=" blank20px"></div>
          </div>
      <div class="HackBox"></div>
	</form>
  </div>
<div class="blank5px"></div>
<script type="text/javascript">
	var pageContext = $("#pageContext").val();
	/**
	 * 表单提交
	 * @return
	 */
	function mySubmit(){
		var params = {};
		if($.trim($("#name").val())==""){
			$.jBox.tip("分类名称不能为空", 'error',1000);
			return;
		}
		if($.trim($("#area").val())==""){
			$.jBox.tip("地域名称不能为空", 'error',1000);
			return;
		}
		params.warningkeywsId = $("#warningkeywsId").val();
		params.name = $.trim($("#name").val());
		params.area = $.trim($("#area").val());
		params.mainKeyws = $.trim($("#mainKeyws").val());
		params.deputyKeyws = $.trim($("#deputyKeyws").val());
		params.ruleoutKeyws = $.trim($("#ruleoutKeyws").val());
		
		$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/system/doUpdateWarningkeyws"),
			dataType : "json",
			data : params,
			success : function(data) {
				if(data>0){
					window.setTimeout(function () { $.jBox.tip("修改成功", 'success',1000); }, 100);
					window.setTimeout(window.parent.closeAndsubmit,1000);
				}else{
					window.setTimeout(function () { $.jBox.tip("添加失败", 'error',1000); }, 100);
				}
			}
		});
	}
</script>
</body>
</html>
