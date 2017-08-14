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
<#include "/common/top.ftl">
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
						<dl><dt class="fl_1"><a href="${request.getContextPath()}/analysis/toHotAnalysis">热点分析</a></dt></dl>

            			<dl class="this"><dt class="fl_2"><a href="${request.getContextPath()}/analysis/toAnalysisList">图表分析</a></dt></dl>
          			</div>
        		</div>
			</div>
			<div class="fl_right">
				<div class="fl_right_in">
				
					<form  id="analysisForm" action="${request.getContextPath()}/analysis/toAnalysisList" method="post"  >
					<input type="hidden" id="quyu" name="quyu" value="${quyu}"/>
					<input type="hidden" id="quyuValue" name="quyuValue" value="${quyuValue}"/>
					<input type="hidden" id="quyuNameValue" name="quyuNameValue" value="${quyuNameValue}"/>
					<input type="hidden" id="meiti" name="meiti" value="${meiti}"/>
					<input type="hidden" id="meitiValue" name="meitiValue" value="${meitiValue}"/>
					<input type="hidden" id="mokuai" name="mokuai" value="${mokuai}"/>
					<input type="hidden" id="mokuaiValue" name="mokuaiValue" value="${mokuaiValue}"/>
					<input type="hidden" id="zhengwufenlei" name="zhengwufenlei" value="${zhengwufenlei}"/>
					<input type="hidden" id="zhengwufenleiValue" name="zhengwufenleiValue" value="${zhengwufenleiValue}"/>
					<input type="hidden" id="dingzhifenlei" name="dingzhifenlei" value="${dingzhifenlei}"/>
					<input type="hidden" id="dingzhifenleiValue" name="dingzhifenleiValue" value="${dingzhifenleiValue}"/>
					<input type="hidden" id="userType" name="userType" value="${userType}"/>
					
					<div class="baikuang">
						<div class="sj_zx_b">
							<ul>
								<li><input class="inputxsf" type="text" id="stime" name="stime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${beginTime}"/></li>
								<li class="tb2 color_bai timek">开始时间</li>
								<li><input class="inputxsf" type="text" id="etime" name="etime"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${endTime}"/></li>
								<li class="tb2 color_bai timek">结束时间</li>
							</ul>
							<div class="HackBox"></div>
							
<script>
$(function () {

/*---改变input 以及反选---*/

$(".zzhe_dl dd").each(function(){
	var dd_height = $(this).parent().height();
	$(this).siblings("dt").height(dd_height);
	
	if(
		$(this).find("input").is(':checked')){$(this).addClass('this');
	}else{
		$(this).removeClass('this');
	}

	if($("#mk_all").find("input").is(':checked')){
		$("#yqfl,#yqf2").show().css({"height":"auto"}); 
	}
	if($("#mk_zw").find("input").is(':checked')){
		$("#yqfl").show().css({"height":"auto"}); 
	}
	if($("#mk_dz").find("input").is(':checked')){
		$("#yqf2").show().css({"height":"auto"}); 
	}
})
 
$('.zzhe_dl dl').find("dd:eq(0)").siblings("dd").on('click',function(){
	if($(this).find("input").is(':checked')){
        $(this).removeClass('this').find("input").removeAttr('checked');
		$(this).siblings("dd:eq(0)").removeClass('this').find("input").removeAttr('checked');
    }
    else{
        $(this).addClass('this');
        $(this).find("input").attr('checked','checked')
		$(this).siblings("dd:eq(0)").removeClass('this').find("input").removeAttr('checked')
    }
});

$('.zzhe_dl dl').find("dd:eq(0)").on('click',function(){
	if($(this).find("input").is(':checked')){
        $(this).removeClass('this');
        $(this).find("input").removeAttr('checked')
		$(this).siblings("dd").removeClass('this').find("input").removeAttr('checked')
    }else{
        $(this).addClass('this');
        $(this).find("input").attr('checked','checked')
		$(this).siblings("dd").addClass('this').find("input").attr('checked','checked')
    }
});
/*---改变模块---*/

$("#mk_all").on('click',function(){
	if($(this).find("input").is(':checked')){
		$mk_zw("#yqfl,#yqf2"); 
    }else{
		$mk_zw2("#yqfl,#yqf2"); 
    }
});

$("#mk_zw").on('click',function(){
	if($(this).find("input").is(':checked')){
		$mk_zw("#yqfl"); 
    }else{
		$mk_zw2("#yqfl"); 
    }
});

$("#mk_dz").on('click',function(){
	if($(this).find("input").is(':checked')){
		$mk_zw("#yqf2"); 
    }else{
		$mk_zw2("#yqf2"); 
    }
});

 /*---变换---*/
 $mk_zw = function (bianling) {
	$(bianling).show().css({"height":"auto"}); 
 }
  $mk_zw2 = function (bianling) {
	$(bianling).hide(); 
	$(bianling).find("dd").removeClass('this');
    $(bianling).find("input").removeAttr('checked')
 }

/*---隔行换色---*/
$(".zzhe_dl dl:odd").addClass("zzhe_dl_odd");
 });
</script> 
							
							
							<div class="zzhe_dl">
								<dl>
          							<dt>模　　块：</dt>
          							<dd id="mk_all" ><input  name="model" type="checkbox"  <#if mokuai == "-1"> checked="checked" </#if> value="-1"/>全选</dd>
          							<#if userType == "2">
          							<dd id="mk_zw"><input name="model" type="checkbox"  <#if mokuai == "-1" || mokuai?contains(",2,")> checked="checked" </#if> value="2"  />政务舆情</dd>
          							</#if>
          							<dd id="mk_dz"><input name="model" type="checkbox" <#if mokuai == "-1" || mokuai?contains(",1,")> checked="checked" </#if> value="1" />定制舆情</dd>
          						</dl>
          						
          						<dl>
          							<dt>媒体类型：</dt>
      								<dd><input name="webDictionary" type="checkbox" <#if meiti == "-1"> checked="checked" </#if>  value="-1"/>全选</dd>
					          	<#list webDictionaryList as webDictionary>
									<dd><input name="webDictionary" type="checkbox" <#if meiti == "-1" || meiti?contains(",${webDictionary.value},") > checked="checked" </#if>  value="${webDictionary.value}"/>${webDictionary.name?default('&nbsp;')}</dd>
								</#list>
          						</dl>
							
								<div id="yqfl" style=" height:0px;overflow:hidden;">
								<#if userType == "2">
								<dl>
									<dt>地　　域：</dt>
									<dd><input name="area" type="checkbox" <#if quyu == "-1"> checked="checked" </#if>  value="-1" />全选</dd>
					          	<#list regionList as region>
									<dd><input name="area" type="checkbox" <#if quyu == "-1" || quyu?contains(",${region.regionid},") > checked="checked" </#if> 
									 value="${region.regionid}&${region.regionname?default('&nbsp;')}"/>${region.regionname?default('&nbsp;')}</dd>
								</#list>
          						</dl>
          						
          						<dl>
          							<dt>政务分类：</dt>
          							<dd><input name="event" type="checkbox" <#if zhengwufenlei == "-1"> checked="checked" </#if> value="-1" />全选</dd>
					          	<#list eventList as event>
									<dd><input name="event" type="checkbox" <#if zhengwufenlei == "-1" || zhengwufenlei?contains(",${event.eventid},") > checked="checked" </#if> value="${event.eventid}" />${event.eventname?default('&nbsp;')}</dd>
								</#list>
          						</dl>
          						</#if>
          						</div>
          						
          						<div id="yqf2" style=" height:0px; overflow:hidden;">
					          	<dl>
					          		<dt>定制分类：</dt>
					          		<dd><input name="subject" type="checkbox" <#if dingzhifenlei == "-1"> checked="checked" </#if> value="-1"  />全选</dd>
					          	<#list subjectList as subject>
									<dd><input name="subject" type="checkbox" value="${subject.id}" <#if dingzhifenlei == "-1" || dingzhifenlei?contains(",${subject.id},") > checked="checked" </#if> />${subject.name?default('&nbsp;')}</dd>
								</#list>
					          	</dl>  
					          	</div>
							</div>
							
							<div class="zhfx_gundong"></div>
							<div class="HackBox"></div>
							<div class="dd_center">
								<input class="tb2 color_bai tongji" type="button" onclick="mySubmit();" style="cursor:pointer;" value="开始统计" />
							</div>
							<div class="HackBox"></div>
						</div>
					</div>
          			</form>
          			
					<div class="baikuang">
		            	<div class="yq_zs_k">
		              		<div class="title_h1"><span class="t_tit t_tit_5">趋势分析 </span></div>
		              		<div class="kuang_in" id="qushifenxi" style="width:715;height:299px;" >
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
          
					<div class="baikuang">
					    <div class="yq_zs_k">
							<div class="title_h1"> <span class="t_tit t_tit_9">媒体分布</span> </div>
							<div class="kuang_in" id="meitifenbu" style="width:715;height:250px;" >
								<div class="x_jiazai">
						        <div class="tuxing_dengdai"></div>
						        <p>正在加载</p>
							</div>
					    </div>
					</div>
			
					<div class="baikuang">
					    <div class="yq_zs_k">
					      <div class="title_h1"> <span class="t_tit t_tit_6">网站排行 </span> </div>
							<div class="kuang_in" id="wangzhanpaihang" style="width:715;height:250px;" >
								<div class="x_jiazai">
						        <div class="tuxing_dengdai"></div>
						        <p>正在加载</p>
							</div>
					      </div>
					</div>
			
					<div class="baikuang">
					    <div class="yq_zs_k">
					      <div class="title_h1"> <span class="t_tit t_tit_8">情感分析 </span> </div>
					      <div class="kuang_in" style="width:361;height:250px;" id="qingganfenxi">
					      	<div class="x_jiazai">
							<div class="tuxing_dengdai"></div>
							<p>正在加载</p>
						  </div>
						  <div class="kuang_in" style="width:361;height:250px;" id="qingganfenxi2">
					      	<div class="x_jiazai">
							<div class="tuxing_dengdai"></div>
							<p>正在加载</p>
						  </div>
					    </div>
					</div>
					<!--
					<div class="baikuang">
			            <div class="yq_zs_k">
			              <div class="title_h1"> <span class="t_tit t_tit_8">地域分析 </span> </div>
			              <div class="kuang_in" style="width:722;height:250px;" id="diyufenxi">
			              	<div class="x_jiazai">
							<div class="tuxing_dengdai"></div>
							<p>正在加载</p>
			  			  </div>
			          	</div>
		        	</div>
		        	-->
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
<script type="text/javascript">
	$(document).ready(function(){
		jBox.setDefaults({
			defaults : {
				border : 0
			}
		})
		
		var quyuValue = $("#quyuValue").val();
		var meitiValue = $("#meitiValue").val();
		var mokuaiValue = $("#mokuaiValue").val();
		var zhengwufenleiValue = $("#zhengwufenleiValue").val();
		var dingzhifenleiValue = $("#dingzhifenleiValue").val();
		var quyuNameValue =  $("#quyuNameValue").val();
		var stime = $("#stime").val();
		var etime = $("#etime").val();
		$('#qushifenxi').load('${request.getContextPath()}/analysis/qushifenxi?quyuNameValue='+quyuNameValue+"&meitiValue="+meitiValue+"&mokuaiValue="+mokuaiValue+"&zhengwufenleiValue="+zhengwufenleiValue+"&dingzhifenleiValue="+dingzhifenleiValue+"&stime="+stime+"&etime="+etime+"&quyuValue="+quyuValue);
		$('#meitifenbu').load('${request.getContextPath()}/analysis/meitifenbu?quyuNameValue='+quyuNameValue+"&meitiValue="+meitiValue+"&mokuaiValue="+mokuaiValue+"&zhengwufenleiValue="+zhengwufenleiValue+"&dingzhifenleiValue="+dingzhifenleiValue+"&stime="+stime+"&etime="+etime+"&quyuValue="+quyuValue);
		$('#wangzhanpaihang').load('${request.getContextPath()}/analysis/wangzhanpaihang?quyuNameValue='+quyuNameValue+"&meitiValue="+meitiValue+"&mokuaiValue="+mokuaiValue+"&zhengwufenleiValue="+zhengwufenleiValue+"&dingzhifenleiValue="+dingzhifenleiValue+"&stime="+stime+"&etime="+etime+"&quyuValue="+quyuValue);
		$('#qingganfenxi').load('${request.getContextPath()}/analysis/qingganfenxi?quyuNameValue='+quyuNameValue+"&meitiValue="+meitiValue+"&mokuaiValue="+mokuaiValue+"&zhengwufenleiValue="+zhengwufenleiValue+"&dingzhifenleiValue="+dingzhifenleiValue+"&stime="+stime+"&etime="+etime+"&quyuValue="+quyuValue);
		$('#qingganfenxi2').load('${request.getContextPath()}/analysis/qingganfenxi2?quyuNameValue='+quyuNameValue+"&meitiValue="+meitiValue+"&mokuaiValue="+mokuaiValue+"&zhengwufenleiValue="+zhengwufenleiValue+"&dingzhifenleiValue="+dingzhifenleiValue+"&stime="+stime+"&etime="+etime+"&quyuValue="+quyuValue);
	}); 
</script>
 
<script>
	function mySubmit(){
	
		//模块
		if(getMokuaiVal()==""){
			window.setTimeout(function () { $.jBox.tip("请选择模块", 'warning',1000); }, 100);
			return null;
		}else{
			var mokuai = "";
			if(getMokuaiVal()=="-1"){
				$("input[name^='model']").each(function() { 
					if(mokuai==""){
					    mokuai+=this.value
					}else{
					    mokuai+=","+this.value
					}  
				}); 
				$("#mokuai").val(mokuai);
			}else{
				$("#mokuai").val(getMokuaiVal());
			}
		}
	
		//媒体
		if(getMeitiVal()==""){
			window.setTimeout(function () { $.jBox.tip("请选择媒体类型", 'warning',1000); }, 100);
			return null;
		}else{
			var webDictionary = "";
			if(getMeitiVal()=="-1"){
				$("input[name^='webDictionary']").each(function() { 
					if(area==""){
					    webDictionary+=this.value
					}else{
					    webDictionary+=","+this.value
					}  
					
				}); 
				$("#meiti").val(webDictionary);
			}else{
				$("#meiti").val(getMeitiVal());
			}
		}
	
		if(getMokuaiVal().indexOf(2)>=0){
			//区域判断
			if($("#userType").val()=="2"){
				if(getAreaVal()==""){
					window.setTimeout(function () { $.jBox.tip("请选择地域信息", 'warning',1000); }, 100);
					return null;
				}else{
					var area = "";
					var areaName = "";
					if(getAreaVal()=="-1"){
						$("input[name^='area']").each(function() { 
							if(area==""){
							    area+=this.value.split("&")[0];
							    areaName+=this.value.split("&")[1];
							}else{
							    area+=","+this.value.split("&")[0];
							    areaName+=","+this.value.split("&")[1];
							}  
							
						}); 
						$("#quyu").val(area);
						$("#quyuNameValue").val(areaName);
					}else{
						$("#quyu").val(getAreaVal());
						$("#quyuNameValue").val(getAreaNameVal());
					}
				}
			}
			
			//政务分类
			if($("#userType").val()=="2"){
				if(getEventVal()==""){
					window.setTimeout(function () { $.jBox.tip("请选择政务分类", 'warning',1000); }, 100);
					return null;
				}else{
					var zhengwufenlei = "";
					if(getEventVal()=="-1"){
						$("input[name^='event']").each(function() { 
							if(zhengwufenlei==""){
							    zhengwufenlei+=this.value
							}else{
							    zhengwufenlei+=","+this.value
							}  
						}); 
						$("#zhengwufenlei").val(zhengwufenlei);
					}else{
						$("#zhengwufenlei").val(getEventVal());
					}
				}
			}
		}
		if(getMokuaiVal().indexOf(1)>=0){
			//定制分类
			if(getSubjectVal()==""){
				window.setTimeout(function () { $.jBox.tip("请选择定制分类", 'warning',1000); }, 100);
				return null;
			}else{
				var dingzhifenlei = "";
				if(getSubjectVal()=="-1"){
					$("input[name^='subject']").each(function() { 
						if(dingzhifenlei==""){
						    dingzhifenlei+=this.value
						}else{
						    dingzhifenlei+=","+this.value
						}  
					}); 
					$("#dingzhifenlei").val(dingzhifenlei);
				}else{
					$("#dingzhifenlei").val(getSubjectVal());
				}
			}
		}
		$("#analysisForm").submit();
	}
	
	function getAreaVal(){
		var v="";
		$("input[name='area']:checked").each(function() { 
			if(v==""){
		    	v+=this.value.split("&")[0];
			}else{
			    v+=","+this.value.split("&")[0];
			}  
		}); 
	 	return v;
	}
	
	function getAreaNameVal(){
		var v="";
		$("input[name='area']:checked").each(function() { 
			if(v==""){
		    	v+=this.value.split("&")[1];
			}else{
			    v+=","+this.value.split("&")[1];
			}  
		}); 
	 	return v;
	}
	
	function getMeitiVal(){
		var v="";
		$("input[name='webDictionary']:checked").each(function() { 
			if(v==""){
		    	v+=this.value
			}else{
			    v+=","+this.value
			}  
		}); 
	 	return v;
	}
	
	function getMokuaiVal(){
		var v="";
		$("input[name='model']:checked").each(function() { 
			if(v==""){
		    	v+=this.value
			}else{
			    v+=","+this.value
			}  
		}); 
	 	return v;
	}
	
	function getEventVal(){
		var v="";
		$("input[name='event']:checked").each(function() { 
			if(v==""){
		    	v+=this.value
			}else{
			    v+=","+this.value
			}  
		}); 
	 	return v;
	}
	
	function getSubjectVal(){
		var v="";
		$("input[name='subject']:checked").each(function() { 
			if(v==""){
		    	v+=this.value
			}else{
			    v+=","+this.value
			}  
		}); 
	 	return v;
	}
	
</script>
   
<div class="blank5px"></div>
<!----底部-开始--->
<script src="${request.getContextPath()}/js/utils/echarts/esl.js" type="text/javascript"></script> 
<script src="${request.getContextPath()}/js/utils/echarts/echarts.js" type="text/javascript"></script> 
<#include "/common/bottom.ftl">
<!----底部-结束--->
</body>
</html>
