<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>政务舆情</title>
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
<!--头部-->
<#include "/common/top.ftl">
<!--头部结束-->
<div class="bg">
  <div class="zhanwei color_hongse_n">
    <strong>当前位置：</strong><a href="${request.getContextPath()}/homepage/listHomePage">&nbsp;首页&nbsp;</a>&gt;<a href="${request.getContextPath()}/event/toListEvent">&nbsp;政务舆情&nbsp;</a>&gt;&nbsp;文章列表 
  </div>
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
        <div class="fenlei_cidan color_hongse_n DD_png_w">
          <dl>
            <dt class="fl_1">政务舆情</dt>
            <dd>
              <ul>
              <#list list as x>
                <li id="${x.regionid}"><span  onclick="loadView('${x.regionid}')">${x.regionname}</span></li>
                </#list>
               
              </ul>
            </dd>
          </dl>
           
        </div>

			    	</td>
				</tr>
			</table>
	      </div>
		</div>
		<div class="fl_right" >
			<div class="fenxileibiao" >
		        <div class="title_h1">
		          <ul>
		            <li class="this" id="wenzhang">
		            	<span class="t_tit"><a href="javascript:" onclick="fenximySubmit(this);" >文章列表</a></span>
		            </li>
      				<li >
      					<span class="t_tit1"><a href="javascript:" onclick="showfenxi(this);">分类分析</a></span>
      				</li>
		          </ul>
		        </div>
				<div class="zw_yq_top" id="fenxileibiao">
			         <div class="baikuang">
			         <ul>
			         	<li style="cursor:pointer;" class="this"  onclick="to_ListEvent(-1,this)" id="quanbu">全部</li>
			         <#list eventList as event>  
			         	<li style="cursor:pointer;"  onclick="to_ListEvent('${event.eventid}',this)" >${event.eventname}</li>
			         </#list>    
			         </ul>
			         <!--<li>更多&gt;&gt;</li>-->
			         <div class="HackBox"></div>
		        </div>
      		</div>
          
			<div class="yuqing_list" id="yuqing_list">
				<script type="text/javascript">
					 $(document).ready(function() {
						$(".yuqing_list tr:nth-child(odd)").addClass("td_red")
						$(".yuqing_list tr").find("td:first,th:first").addClass("td_one")
					});
				</script>
	          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <th><input type="checkbox" id="chk_all" onclick="checkAll(this);" /></th>
	                <th class="color_hongse_n">全选</th>
	                <th>
	                  <select name="type" id="type" onchange="mySubmit()">
							<option value="0">全部</option>
							<#list webDictionaryList as webDictionary>
								<option value="${webDictionary.value}" >${webDictionary.name?default('&nbsp;')}</option>
							</#list>
					  </select>
	                   <select name="polarityorientation" id="polarityorientation" onchange="mySubmit()">
							<option value="-2" >全部</option>
							<option value="1"  >正面</option>
							<option value="-1"   >负面</option>
							<option value="0"    >争议</option>
					  </select>
	                   
	                  <select name="timeMethod" id="timeMethod" onchange="mySubmit()">
	                  		<option value="5" <#if timeMethod==1> selected="selected" </#if> >全部</option>
							<option value="1" >近一天</option>
							<option value="2" <#if timeMethod==0> selected="selected" </#if> >近三天</option>
							<option value="3" >近七天</option>
							<option value="4" >一个月</option>
					  </select>
					  
					   <select name="pagesize" id="pagesize" onchange="mySubmit()">
							<option value="10" >10</option>
							<option value="50" >50</option>
							<option value="100" >100</option>
					  </select>
					  <select name="export" id="export" onchange="exportEvent();">
							<option value="0">选择导出方式</option>
							<option value="1">导出所选(word)</option>
							<!--<option value="2">时间段导出(word)</option>-->
							<option value="3">导出所选(excel)</option>
							<!--<option value="4">时间段导出(excel)</option>-->
					  </select>
	                  <a class="tb2 color_bai tbfasongyouxiang" style="color:white;" onmouseover="this.style='color:black;'" onmouseout="this.style='color:white;'" href="javascript:" onclick="toSendMail()">发送邮件</a>
	              	</tr>
	            </table>
        	</div>
		</div>
		<div id="viewDiv">
			<div class="x_jiazai">
			<div class="tuxing_dengdai"></div>
				<p>正在加载</p>
			</div>	
		</div>    
	</div>
	</div>
   </div>
</div>
<div class="blank5px"></div>
<!--底部开始-->
<#include "/common/bottom.ftl">
<!--底部结束-->
<script src="${request.getContextPath()}/js/utils/ztree/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/event/list_allevent.js"></script>

</body>
</html>
