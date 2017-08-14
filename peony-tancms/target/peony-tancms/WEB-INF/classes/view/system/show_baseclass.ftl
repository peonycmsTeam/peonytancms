<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
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
<br/>
<div >
  <div class="shoucang_wx">
      <div class="baikuang">
        <div class="soucangjis_w">
          <div class="shoucang shoucangdiv" style="padding: 0 25px 0 60px;">
          <h2 class="tc_fenleititle">公共词库</h2>
          <#list publicBaseClassKeywsList as baseClassKeyws>  
		    <span class="tb2" style="float:left">
		    	<input type="checkbox" name="chk_list" id="chk_list" value="${baseClassKeyws.baseclassKeywsId}">${baseClassKeyws.name}
            </span>
          </#list>
          <#if listSize!=0>
          <h2 class="tc_fenleititle">${cikuName}词库</h2>
          <#list baseClassKeywsList as baseClassKeyws>  
		    <span class="tb2" style="float:left">
		    	<input type="checkbox" name="chk_list" id="chk_list" value="${baseClassKeyws.baseclassKeywsId}">${baseClassKeyws.name}
            </span>
          </#list>
          </#if>
		  </div>
        </div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<div class=" text_center">
    <input name="提交" type="button" onclick="mySubmit();" class="tb2 fabiao1 color_bai" value="提交" />
</div>
</body>
<script type="text/javascript">
/**
 * 得到checkebox值
 * @return
 */
function getCheckVal(){
	var v="";
	$("input[name='chk_list']:checked").each(function() { 
		if(v==""){
		    v+=this.value
		}else{
		    v+=","+this.value
		}  
	}); 
 	return v;
}
function mySubmit(){
	if(getCheckVal()==""){
		window.setTimeout(function () { $.jBox.tip("请至少选择一个词库", 'error',1000); }, 100);
	}else{
		window.parent.setBaseClassId(getCheckVal());
	}
}
</script>
</html>
