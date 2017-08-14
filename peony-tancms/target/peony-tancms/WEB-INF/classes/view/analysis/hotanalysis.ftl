<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>综合分析</title>
<#include "/common/global_css.ftl">
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
</head>
<body>
<!----头部--->
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<!----头部-结束--->
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;首页&nbsp;&gt;&nbsp;综合分析 </div>
</div>
<div class="bg zh_fx">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
        <div class="fl_left_in">
          <div class="tb2 fenlei_title color_bai">综合分析</div>
          <div class="fenlei_cidan color_hongse_n DD_png_w">
            <dl class="this">
              <dt class="fl_1"><a href="${request.getContextPath()}/analysis/toHotAnalysis">热点分析</a></dt>
    			
            </dl>
            <dl ><dt class="fl_2"><a href="${request.getContextPath()}/analysis/toAnalysisList">图表分析</a></dt></dl>
          </div>
        </div>
      </div>
      <div class="fl_right">
        <div class="fl_right_in">
        <!--
          <div class="baikuang">
            <div class="sj_zx_b">
              <ul>
                <li>
                  <input class="inputxsf" type="text" name="textfield" id="textfield" />
                </li>
                <li class="tb2 color_bai timek">开始时间</li>
                <li>
                  <input class="inputxsf" type="text" name="textfield" id="textfield" />
                </li>
                <li class="tb2 color_bai timek">结束时间</li>
                <li class="on_right">
                  <input class="tb2 color_bai tongji" type="submit" name="button" id="button" value="开始统计" />
                </li>
              </ul>
              <div class="HackBox"></div>
            </div>
          </div>-->
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
		</script>
              <div class="title_h1"><div class="zh_yq_dx"><span><!--<input name="shenfen" type="radio" id="RadioGroup1_0" value="定制舆情" checked="checked">定制舆情</span>--><span><!--<input type="radio" name="shenfen" value="政务舆情" id="RadioGroup1_1">政务舆情</span>--></div><span class="t_tit t_tit_10">热点分类 </span> </div>
              
              	<div class="kuang_in" id="hotClassification" style="width:715;height:299px;" >
		              			<div class="x_jiazai">
				        		<div class="tuxing_dengdai"></div>
				        		<p>正在加载</p>
					  		</div>
              
            </div>
            </div>       
            <script>
				$(document).ready(function() {
					$('.zh_fx_table').find('tr:odd').addClass('zh_odd')
		 			$('.zh_fx_list').find('dl:odd').addClass('zh_odd')
				});
			</script>  
			<!--
			 <div class="baikuang">
			  <div class="yq_zs_k">
            <div class="title_h1"> <span class="t_tit t_tit_12">热词分析</span> </div>
            <iframe src="${request.getContextPath()}/analysis/words" width="750;" height="180px;" allowtransparency="true" style="background-color=transparent" title="test" frameborder="0"  scrolling="no"> </iframe>
             
            </div>
          </div>
          
 			<div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1"> <span class="t_tit t_tit_12">热词分析</span> </div>
              
              <div class="kuang_in" id="hotWordAnalysis" style="width:715;height:299px;" >
		              			<div class="x_jiazai">
				        		<div class="tuxing_dengdai"></div>
				        		<p>正在加载</p>
			  </div>
            
          </div>
          -->
          <div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1"> <span class="t_tit t_tit_7">地域热词分析 </span> </div>
              <div class="kuang_in" id="areaHotWordAnalysis" style="width:715;height:299px;" >
		              			<div class="x_jiazai">
				        		<div class="tuxing_dengdai"></div>
				        		<p>正在加载</p>
			  </div>
            </div>
          </div>
          <!--
          <div class="baikuang">
            <div class="yq_zs_k">
              <div class="title_h1"> <span class="t_tit t_tit_11">地域热点分析 </span> </div>
              <div class="kuang_in">
              	<div style="float:left; width:388px;height:400px;" >shkjfhdsjkhfjksd </div>
      			<div class="zh_fx_list"></div> 
      			<div class="HackBox"></div>
      			</div>
            </div>
          </div>
         -->
          <div class="blank8px"></div>
          
        </div>
      </div>
      <div class="HackBox"></div>
    		</div>
    	</div>
    </div>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
 <script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script> 
<script src="${request.getContextPath()}/js/utils/echarts/echarts.js" type="text/javascript"></script>
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script>
$(document).ready(function() {
		//热点分析
		$('#hotClassification').load('${request.getContextPath()}/analysis/hotClassification');
		//热词分析
		//$('#hotWordAnalysis').load('${request.getContextPath()}/analysis/hotWordAnalysis');
		$('#areaHotWordAnalysis').load('${request.getContextPath()}/analysis/areaHotWordAnalysis');
		//$('#words').load('${request.getContextPath()}/analysis/words');
	});
	
</script>
</body>
</html>
