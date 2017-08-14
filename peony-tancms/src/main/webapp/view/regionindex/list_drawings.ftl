<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政务分析</title>
<#include "/common/global_css.ftl">
</head>
<body>
<!----头部--->
<input type="hidden" id="type" value="${type?default('3')}"/>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->

<script src="${request.getContextPath()}/js/drawing.js" type="text/javascript"></script>

<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;政务分析 </div>
</div>
<div class="bg zh_fx">
  <div class="fl_br">
    <div class="fl_br_in"> 
      <script>
    //底部公告隐藏
var nt = !1;
			$(window).bind("scroll",
				function() {
				var st = $(document).scrollTop();//往下滚的高度
				nt = nt ? nt: $("#fl_xn").offset().top;
				 //document.title=st;
				var sel=$("#fl_xn");
				if (nt < st) {
					sel.addClass("fl_xn");
				} else {
					sel.removeClass("fl_xn");
				}
			});
 
    </script>
      <div class="fl_left" id="fl_xn" >
        <div class="fl_left_in">
          <div class="fenleitou"></div>
          <div class="sj_zs_l">
            <ul>
              <li ><a href="${request.getContextPath()}/regionindex/listDrawing">省内舆情指数</a></li>
              <li class="this"><a href="${request.getContextPath()}/regionindex/listDrawings">全国舆情指数</a></li>
            </ul>
          </div>
          <div class="fenlei_cidan color_hongse_n DD_png_w">
            <div class="yindao">
              <ul>
                <li><a href="#zhishu1">舆情指数</a></li>
                <li><a href="#zhishu2">舆情走势</a></li>
                <li><a href="#zhishu3">媒体指数</a></li>
                <li><a href="#zhishu4">行业指数</a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="fl_right">
        <div class="fl_right_in">
          <div class="baikuang">
            <div class="yq_zs_k"> 
              <script>
$(document).ready(function() {
		 $(".zh_yq_dx span").each(function(){
 if($(this).find("input").is(':checked')){
        $(this).addClass('this');
    }
    else{
        $(this).removeClass('this');
    }
 })
	$('.zh_yq_dx span').click(function() {
		$(this).addClass('this');
		$(this).siblings().removeClass('this');
		$(this).find('input').prop("checked",true);
	});
});

function month(){
		window.location.href=pageContext+"/regionindex/listDrawings?type=3";
}
 
 function lastmonth(){
		window.location.href=pageContext+"/regionindex/listDrawings?type=2";
}

function day(){
		window.location.href=pageContext+"/regionindex/listDrawings?type=1";
}


</script>
 <span>	
             <input type="button" name="shenfen" id="day" value="近七天" style=" color:red" onclick="day()">                 
                  </span><span>
                <input type="button" name="shenfen" value="上月" id="lastmonth" style=" color:red" onclick="lastmonth()">
                  </span><span>
                <input type="button" name="shenfen" value="本月" id="month" style=" color:red" onclick="month()" >
                   </span>
              <div class="title_h1">
<style>
.h12_z span.on_right a{ color:#999; font-size:14px;}
.t_titbx{ padding-left:12px;}
h1.h12_z{ font-size:16px; text-align:center; padding:5px 0 10px 0}
.linexsw_1{ border-bottom:1px solid #999; margin:10px 0; width:100%;}
.yindao{ padding-left:30px;}
img.imgxss{ max-width:100%; height:auto; }
select.on_right{ margin:5px 10px 0 0}
.xsfw{ height:32px;}
.fl_xn{ position:fixed; top:0;}
</style>
                <span class="t_tit t_titbx"><a name="zhishu1" id="zhishu1"></a>国内舆情指数 </span> </div>
              <div class="kuang_in">
                <h1 class="h12_z color_hongse">国内指数排行</h1>
               <div class="list" id="listregionchina">
        	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                <div class="linexsw_1"></div>
                <h1 class="h12_z color_hongse">全国舆情分布图</h1>
               <div class="list" id="listchinadistribute">
          <div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                           
                        </table>
                      </div></td>
                  </tr>
                </table>
               
                <div class="linexsw_1"></div>
                <h1 class="h12_z color_hongse">国内热点事件TOP10 </h1>
                  <div class="list" id="listeventindex">
          <div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
          <script>
$(document).ready(function() {
		 
	$('.zh_fx_table').find('tr:odd').addClass('zh_odd')
		 	$('.zh_fx_list').find('dl:odd').addClass('zh_odd')
});
</script>
          <div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1"> <span class="t_tit t_titbx">国内舆情走势<a name="zhishu2" id="zhishu2"></a></span></div>
              <div class="kuang_in">
                <h1 class="h12_z color_hongse">全国舆情走势</h1>
               <div class="list" id="listtendencychina">
         	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                <div class="HackBox"></div>
                <div class="linexsw_1"></div>
                <h1 class="h12_z color_hongse">全国舆情增长率排行</h1>
               <div class="list" id="listgrowthratechina">
         	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
          <div class="baikuang">
            <div class="yq_zs_k">
               <div class="title_h1"> 
               	<span class="t_tit t_titbx">国内媒体占比<a name="zhishu3" id="zhishu3"></a></span></div>
              <div class="kuang_in">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td valign="top"><h1 class="h12_z color_hongse">媒体占比 </h1>
                      <div class="list" id="listmediacount">
         	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div></td>
                    <td><h1 class="h12_z color_hongse"><span class="on_right"><a href="#"></a> </span> </h1>
                      <div class="zh_fx_table zh_fx_table2 zh_fx_table22">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                        </table>
                      </div></td>
                  </tr>
                </table>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
          <div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1">
                <span class="t_tit t_titbx">行业指数<a name="zhishu4" id="zhishu4"></a> </span> </div>
              <div class="kuang_in">
                <h1 class="h12_z color_hongse">热点舆情行业TOP10 </h1>
                <div class="list" id="listindustryindex">
          	<div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
        			 <p>正在加载</p>
         	</div>
        </div>
                <div class="linexsw_1"></div>
                <h1 class="h12_z color_hongse">行业舆情分布图
                  TOP10 </h1>
                <div class="list" id="listdistribute">
       		 <div style=" padding-top: 40px; text-align: center;">
          		<div class="tuxing_dengdai"></div>
         		<p>正在加载</p>
         	</div>
        </div>
                <div class="HackBox"></div>
              </div>
            </div>
          </div>
          <div class="blank8px"></div>
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
</body>
</html>
