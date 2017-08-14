
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
		              <td >选择父节点：</th>
		              <td >
		              	<select name="pid" id="pid" >
							<option value="0" >顶级节点</option>
							subjectList
							<#list subjectList as subject>
								<option value="${subject.id}" >${subject.name}</option>
							</#list>  
				  		</select>
				  	  </td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th>分类名称：</th>
		              <td><input name="textfield2" type="text" class="input_text" id="subjectName" name="subjectName" value="" /></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">地域名称：</th>
		              <td><textarea class="input_text2 input_text_r" name="area" id="area" cols="45" rows="5" disabled="disabled" >${region.regionname}</textarea></td>
		              <td>&nbsp;</td>
		            </tr>
		            <tr>
		              <th valign="top">主关键词:</th>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="main" id="main" cols="45" rows="5"></textarea>
		              </td>
		              <td nowrap="nowrap">使用","分隔</td>
		            </tr>
		            <tr>
		              <th valign="top">倾向性分析词：</th>
		              <td>
		              	<textarea class="input_text2 input_text_r" name="qxx" id="qxx" cols="45" rows="5"></textarea>
		              </td>
		              <td valign="bottom">
		              	<input type="button" value="词库">
		                <span class="jiajia color_bai" onclick="showqxx()">+</span></td>
		            </tr>
		            
		            <!--display:block-->
		            <tr id="qxx1" style="display:none";>
		              <td valign="top"></td>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="qxx1textarea" id="qxx1textarea" cols="45" rows="5"></textarea>
		              </td>
		              <td nowrap="nowrap"></td>
		            </tr>
		            <tr id="qxx2" style="display:none";>
		              <td valign="top"></td>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="qxx2textarea" id="qxx2textarea" cols="45" rows="5"></textarea>
		              </td>
		              <td nowrap="nowrap"></td>
		            </tr>
		            
		            
		            <tr>
		              <th valign="top">过滤词：</th>
		              <td><textarea class="input_text2 input_text_r" name="glc" id="glc" cols="45" rows="5"></textarea></td>
		              <td valign="bottom"><span class="jiajia color_bai" onclick="showglc()">+</span></td>
		            </tr>
		            
		            <!--display:block-->
		            <tr id="glc1" style="display:none";>
		              <td valign="top"></td>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="glc1textarea" id="glc1textarea" cols="45" rows="5"></textarea>
		              </td>
		              <td nowrap="nowrap"></td>
		            </tr>
		            <tr id="glc2" style="display:none";>
		              <td valign="top"></td>
		              <td>
		              	  <textarea class="input_text2 input_text_r" name="glc2textarea" id="glc2textarea" cols="45" rows="5"></textarea>
		              </td>
		              <td nowrap="nowrap"></td>
		            </tr>
		            
		        </table>
		  	    <div class=" text_center">
				    <input name="提交" type="button" onclick="mySubmit();" class="tb2 fabiao1 color_bai" value="提交" />
				    <input name="重置" type="reset" class="tb2 fabiao2 color_bai" value="重置" />
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
	function showqxx(){
		if($("#qxx1").attr("style")=="display:none"){
			$("#qxx1").attr("style","");
		}else{
			$("#qxx2").attr("style","");
		}
	}
	function showglc(){
		if($("#glc1").attr("style")=="display:none"){
			$("#glc1").attr("style","");
		}else{
			$("#glc2").attr("style","");
		}
	}
	
	/**
	 * 表单提交
	 * @return
	 */
	function mySubmit(){
//		alert($("#pid").val());
//		alert($("#subjectName").val());
	//	alert($("#area").val());
	//	alert($("#main").val());
	//	alert($("#qxx").val());
	//	alert($("#qxx1textarea").val());
	//	alert($("#qxx2textarea").val());
	//	alert($("#glc").val());
	//	alert($("#glc1textarea").val());
	//	alert($("#glc2textarea").val());
		var params = {};
		//父节点id
		params.pid = $("#pid").val();
		//分类名称
		if($.trim($("#subjectName").val())==""){
			$.jBox.tip("分类名称不能为空", 'warning',1000);
			return;
		}
		params.subjectName = $.trim($("#subjectName").val());
		
		//地域名称
		if($.trim($("#area").val())==""){
			$.jBox.tip("地域名称不能为空", 'warning',1000);
			return;
		}
		params.area = $.trim($("#area").val());
		params.main = $.trim($("#main").val());
		params.qxx = $.trim($("#qxx").val());
		params.qxx1textarea = $.trim($("#qxx1textarea").val());
		params.qxx2textarea = $.trim($("#qxx2textarea").val());
		params.glc = $.trim($("#glc").val());
		params.glc1textarea = $.trim($("#glc1textarea").val());
		params.glc2textarea = $.trim($("#glc2textarea").val());
		$.ajax({
			type : "post",
			url : encodeURI(pageContext + "/system/doAddSubject"),
			dataType : "json",
			data : params,
			success : function(data) {
				if(data>0){
					window.setTimeout(function () { $.jBox.tip("添加成功", 'success',1000); }, 100);
					window.setTimeout(window.parent.closeIframe("addSubject"),1000);
				}else{
					window.setTimeout(function () { $.jBox.tip("添加失败", 'error',1000); }, 100);
				}
			}
		});
	}
</script>
</body>
</html>
