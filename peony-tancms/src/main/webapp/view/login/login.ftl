<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj.css">
<link href="${request.getContextPath()}/css/jbox/jbox.css" rel="stylesheet" type="text/css" id="jbox"/>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>

<!--[if lte IE 6]>
	<script type="text/javascript" src="${request.getContextPath()}/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->
</head>
<body class="denglu_body" onLoad="checkCookie()">
 <input type="hidden" id="pageContext" value="${request.getContextPath()}">
<div class="bg denglu_w_br"><div class="denglu_w_in">
<div class="denglu_logo on_left DD_png">
</div><div class="denglu_k on_left">
<div class="denglu_top DD_png"></div>
<div class="denglu_center DD_png">
<div class="denglu_top_t tb"></div>
<form id="loginForm">
  <p><label>用户账号:</label>
    <input class="denglu_input denglu_input1 tb" type="text" name="loginName" id="loginName" value="用户名/手机号/邮箱"  onfocus="javascript:if(this.value=='用户名/手机号/邮箱')this.value='';"/></p>
  <p><label>用户密码:</label>
    <input class="denglu_input denglu_input2 tb" type="password" name="password" id="password" /></p>
    
  <!--<p><label>验 证 码:</label>
    <input class="yanzheng" type="text" name="textfield" id="textfield" />
    <div class="on_left"><img src="${request.getContextPath()}/images/img/mddsj_login/idenglu_08.gif" width="60" height="25" /></div>
    <span class="color_lanse"><a href="#">换一个</a> </span>
    </p>
    <p class="color_huise font_13px">
      <label><input type="checkbox" name="checkbox" id="checkbox" /> </label>
      <a href="#">记住用户名</a> | <a href="#">忘记密码	?</a>
    </p>-->
    <p class=" font_13px">
      <label><input type="checkbox" name="checkbox" id="checkbox" /> </label>
     	 记住用户名
    </p>
  <div class="denglu_anniuw"><input name="提交" type="button" class="denglu_anniu1 color_bai tb" value="登录" id="subBtn"/><input name="重置" type="reset"  class="denglu_anniu2 color_bai tb" value="重置"/></div>
</form>
</div>
<div class="denglu_bottom DD_png"></div>
</div>
<div class="blank15px"></div>
</div>

</div><div class="HackBox"></div>
<div class="foot color_bai" id="foot"><div class="foot_in">
  <p>北京牡丹电子集团有限责任公司   </p>
  <p>联系电话：400-650-9297  地址：北京市海淀区花园路2号
    京ICP备14013722号    京公网安备110401000087</p>
  <p>Copyright © 2007-2010 Firdot All Rights Reserved. </p>
</div></div>
<script src="${request.getContextPath()}/js/foot.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/login/login.js" type="text/javascript"></script>
</body>
</html>
<script type="text/javascript">
$(document).ready(function(){
$(".erweima_sm .span_kaiqi2").click(function(){
	$(".erweima_sm").stop().animate({"right":-180}, 800);
	$(".erweima_sm .span_kaiqi").show();
	$(this).hide()
});
$(".erweima_sm .span_kaiqi").click(function(){
	$(".erweima_sm").stop().animate({"right":0}, 800);
	$(".erweima_sm .span_kaiqi2").show();
	$(this).hide()
});
});
    </script>
<div class="erweima_sm">
<h1 class="title_h11"><span class="span_kaiqi"></span><span class="span_kaiqi2"></span>扫描下载手机端</h1>
<div class="erweima_in">
<img src="${request.getContextPath()}/images/img/mddsj_login3/mdyqios.gif" width="125" height="125" />ios
<div class="line_1">
</div><img src="${request.getContextPath()}/images/img/mddsj_login3/android_peony.gif" width="125" height="125" />android
</div>
</div>