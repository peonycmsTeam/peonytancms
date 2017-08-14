<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="css/front/mddsj.css">
<script type="text/javascript" language="javascript" src="js/jquery.js"></script>
<!--[if lte IE 6]>
	<script type="text/javascript" src="js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->
</head>
<body>
<!----头部--->
<#include "/common/top.ftl">
<!----头部-结束--->
<div class="bg">
   <div class="zhanwei color_hongse_n"> <strong>当前位置：</strong><a href="${request.getContextPath()}/homepage/listHomePage">&nbsp;首页&nbsp;</a>&gt;
</div><div class="bg">
<div class="tishi_xinxi"><div class="tishi_xinxi_in">
<div class="tubiao_x1"></div>
<h3>友情提示:</h3>
<p>正在研发中...</p>
</div></div>
</div>
<div class="bg">
  <div class="fl_br_new">
    <div class="fl_br_in">
      <div class="fl_left">
        <div class="fl_left_in">
          <div class="tb2 fenlei_title color_bai">舆情分类</div>
          <div class="fenlei_cidan color_hongse_n DD_png_w">
            <dl>
              <dt class="fl_1">地区舆情</dt>
              <dd>
                <ul>
                  <li>北京</li>
                  <li>深圳</li>
                </ul>
              </dd>
            </dl>
            <dl>
              <dt class="fl_2">人物舆情</dt>
              <dd>
                <ul>
                  <li>张三</li>
                  <li>李四</li>
                </ul>
              </dd>
            </dl>
            <dl>
              <dt class="fl_3">贪污受贿</dt>
            </dl>
          </div>
        </div>
      </div>
      <div class="fl_right">
        <div class="fenxileibiao">
          <div class="title_h1">
            <ul>
              <li class="this"><span class="t_tit"><a href="定制舆情.html">文章列表</a></span></li>
              <li><span class="t_tit1"><a href="分类分析.html">分类分析</a></span></li>
            </ul>
          </div>
          <div class="yuqing_top">
            <ul>
              <li class="tb2 color_bai li_biaoti">标 题</li>
              <li>
                <input class="sousuokuang" type="text" name="textfield" id="textfield" />
              </li>
              <li style="display:none" class="tb2 color_bai li_biaoti">倾向性</li>
              <li style="display:none" class="tb2 xuanze">
                <dl>
                  <dd>菜单</dd>
                  <dd>菜单</dd>
                </dl>
                <span class="tb2">负面</span></li>
              <li>
                <input class="tb2 color_bai add" type="submit" name="button" id="button" value="提 交" />
              </li>
            </ul>
            <div class="HackBox"></div>
          </div>
          <div class="yuqing_list"> 
            <script type="text/javascript">
		 $(document).ready(function() {
	 
 $(".yuqing_list tr:nth-child(odd)").addClass("td_red")
  $(".yuqing_list tr").find("td:first,th:first").addClass("td_one")
});
        </script>
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <th><input type="checkbox" name="checkbox" id="checkbox" />
                </th>
                <th class="color_hongse_n">全选</th>
                <th>
                  <select name="type" id="type" onchange="mySubmit()">
						<option value="0">全部</option>
							<option value="1">新闻</option>
							<option value="2">论坛</option>
							<option value="3">微博</option>
							<option value="4">博客</option>
							<option value="5">报刊</option>
							<option value="6">twiter</option>
				  </select>
				  
				  <select name="polarity" id="polarity" onchange="mySubmit()">
						<option value="-2">全部</option>
						<option value="1">正面</option>
						<option value="-1">负面</option>
						<option value="0">争议</option>
				  </select>
				  
				  <select name="isRead" id="isRead" onchange="mySubmit()">
						<option value="-1">全部</option>
						<option value="0">未读</option>
						<option value="1">已读</option>
				  </select>
				  
				  <select name="time" id="time" onchange="mySubmit()">
						<option value="1">近一天</option>
						<option value="2" selected="selected">近三天</option>
						<option value="3">近七天</option>
						<option value="4">一个月</option>
				  </select>
                  
                  <select name="pagesize" id="pagesize" onchange="mySubmit()">
						<option value="10" selected="selected">10</option>
						<option value="50">50</option>
						<option value="100">100</option>
				  </select>
				  
				  <select name="export" id="export" onchange="exportSubject();">
						<option value="0">选择导出方式</option>
						<option value="1">导出所选</option>
						<option value="2">时间段导出</option>
				  </select>
                  <div class="tb2 color_bai tbfasongyouxiang"><a href="javascript:" onclick="toSendMail();"> 发送邮件</a></div>
                  <div class="tb2 color_bai tbfasongyouxiang"><a href="javascript:" onclick="delete_SubjectPage()"> 删除</a></div>
                  </th>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
              <tr>
                <td valign="top"><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
                <td colspan="2"><h1 class="color_hongse_n"><a href="文章详细.html">冀州一男子去养老院看舅舅时偷存折盗取七千元</a></h1>
                  <p>甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。甥舅关系可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一起盗窃案，受害人做梦也没想到，盗窃存折的竟然是自己的亲外甥。可以说是最亲的亲戚关系，在日常生活中彼此关爱、照顾，相互不设防是很正常的事。可最近冀州市公安局破获了一人</p>
                  <div class="laiyuan color_huise">
                    <div class="on_right color_hongse_n"><span>查看网站信息</span><span>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>负面</dd><dd>正面</dd><dd>中性</dd>
                        </dl>
                        <span class="tb2">负面</span></div>
                      <div class="tb2 xuanze3">
                        <dl>
                          <dd>加入预警</dd><dd>加入专题</dd><dd>加入收藏</dd><dd>加入简报</dd>
                        </dl>
                        <span class="tb2">加入预警</span></div>
                      </span><span> 反馈</span> </div>
                    <em>来源:猫扑网</em><em>发布时间:2014-12-1212:2:2</em><em>点击数:10</em><em>回复数：20</em></div></td>
              </tr>
            </table>
            <!--分页-出开始--->
            <div class="dataTables_wrapper no-footer">
              <div class="dataTables_paginate paging_simple_numbers" id="example_paginate"><a class="paginate_button previous disabled" aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous">Previous</a><span><a class="paginate_button " aria-controls="example" data-dt-idx="1" tabindex="0">1</a><span>…</span><a class="paginate_button " aria-controls="example" data-dt-idx="2" tabindex="0">4</a><a class="paginate_button " aria-controls="example" data-dt-idx="3" tabindex="0">5</a><a class="paginate_button " aria-controls="example" data-dt-idx="4" tabindex="0">6</a><a class="paginate_button " aria-controls="example" data-dt-idx="5" tabindex="0">7</a><a class="paginate_button current" aria-controls="example" data-dt-idx="6" tabindex="0">8</a></span><a class="paginate_button next" aria-controls="example" data-dt-idx="7" tabindex="0" id="example_next">Next</a></div>
            </div>
            <!--分页---结束---> 
          </div>
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
