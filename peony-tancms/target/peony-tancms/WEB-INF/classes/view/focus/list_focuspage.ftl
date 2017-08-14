<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>专题快报</title>
<#include "/common/global_css.ftl">
<script src="${request.getContextPath()}/js/my97date/WdatePicker.js"></script>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/focus/focuspage.js"></script>

</head>
<body>
<!----头部--->
<#include "/common/top.ftl">
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<div class="bg">
  <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong>&nbsp;公共专题&nbsp;&gt;&nbsp;专题快报 </div>
</div>
<div class="bg">
  <div class="fl_br">
    <div class="fl_br_in">
      <div class="fl_left">
        <div class="fl_left_in">
          <div class="fenleitou"></div>
          <div class="sj_zs_l">
            <ul>
            <li><a href="${request.getContextPath()}/focus/listFocusInfo/${focusPage.focusid}">事件综述</a></li>
       		 <li  class="this"><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focusPage.focusid}">专题快报</a></li>
      
            </ul>
          </div>
        </div>
      </div>
      	<form  id="search" action="${request.getContextPath()}/focuspage/listFocusPage" method="post" >
      	<input type="hidden" id="pageNo" name="pageNo" value="1"/>
      	<input type="hidden" id="pageNo" name="focusid" value="${focusPage.focusid}"/>
      	<input type="hidden"  name="exportStime"  id="exportStime"/>
      	<input type="hidden"  name="exportEtime"  id="exportEtime"/>
      <div class="fl_right">
        <div class="fl_right_in">
          <div class="baikuang">
            <div class="sj_zx_b">
              <ul>
                <li>
                  <input class="inputxsf" type="text" readonly="true" name="stime" id="stime" value="${focusPage.stime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})"/>
                </li>
                <li class="tb2 color_bai timek">开始时间</li>
                <li>
                  <input class="inputxsf" type="text" readonly="true" name="etime" id="etime" value="${focusPage.etime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd '})" />
                </li>
                <li class="tb2 color_bai timek">结束时间</li>
                <li class="on_right">
                  <input class="tb2 color_bai chaxun" type="button" name="button" id="button" value="查 询" onclick="mySubmit();" />
                </li>
              </ul>
              <div class="HackBox"></div>
            </div>
          </div> 
          <div class="yuqing_list zhuanti_kuaibao"> 
            <script type="text/javascript">
		 $(document).ready(function() {
	 $(".yuqing_list tr:nth-child(odd)").addClass("td_huise")
  $(".yuqing_list th").addClass("td_red")
  });
        </script>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
            
            </tr>
              <tr>
                <th><input type="checkbox" id="chk_all" onclick="checkAll(this);" />
                </th>
                <th class="color_hongse_n" style="width:40px;">全选</th>
                
                <th>
                <div class="tb2 xuanze2">
                    <select name="type" id="type" onchange="mySubmit();">
						<option value="0">全部</option>
						<#list webDictionaryList as webDictionary>
							<option value="${webDictionary.value}" <#if webDictionary.value==focusPage.type> selected="selected" </#if> >${webDictionary.name?default('&nbsp;')}</option>
						</#list>
				  </select>
               </div>
                              
                  <div class="tb2 xuanze2 on_right">
                     <select name="export" id="export" onchange="exportFocus();">
						<option value="0">选择导出方式</option>
						<option value="1">导出所选(word)</option>
						<option value="2">时间段导出(word)</option>
						<option value="3">导出所选(excel)</option>
						<option value="4">时间段导出(excel)</option>
				  </select>
                  </div>
                </th>
              </tr>
              </form>
         <#list list.list as focuspage>  
              <tr>
                <td valign="top"><input type="checkbox" name="chk_list" id="chk_list" value="${focuspage.id}" /></td>
                <td colspan="2"><a  onclick="openInfo('${focuspage.id}');" href="#">${focuspage.title}</a>
                  <div class="laiyuan color_huise">
                    <div class="on_right"> </div>
                    <em>来源: ${focuspage.website}</em><em>发布时间: ${focuspage.publishdate?string("yyyy-MM-dd HH:mm:ss")}</em><!--<em>点击数:${focuspage.clickcount}</em><em>回复数：${focuspage.replycount}</em>--></div></td>
              </tr>
            </#list>
            </table>
            <!--分页-出开始--->
             <@selectPage.pagination pagination=list formName="search"/>
            <!--分页---结束---> 
          </div>
        </div>
       
        <div class="fenxileibiao"> </div>
      </div>
      <div class="HackBox"></div>
    </div>
  </div>
</div>
<div class="blank5px"></div>
<!----底部-开始--->
<#include "/common/bottom.ftl">
<!----底部-结束--->
<script type="text/javascript" language="javascript">
function openInfo(pageid){
		window.open("${request.getContextPath()}/focuspage/getFocusPageById/"+pageid);
	}
	
</script>
</body>
</html>

