﻿var pageContext;
$(function() {
	pageContext = $("#pageContext").val();
	jQuery.focus = function(slid) {
		var sWidth = $(slid).width(); //获取焦点图的宽度（显示面积）
		var len = $(slid).find("ul li").length; //获取焦点图个数
		var index = 0;
		var picTimer;
		
		//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮
		var btn = "<div class='btnBg'></div><div class='btn'>";
		for(var i=0; i < len; i++) {
			var ii = i+1;
			btn += "<span>"+ii+"</span>";
		}
		btn += "</div><div class='preNext pre'></div><div class='preNext next'></div>";
		$(slid).append(btn);
		$(slid).find("div.btnBg");
	
		//为小按钮添加鼠标滑入事件，以显示相应的内容
		$(slid+" div.btn span").mouseenter(function() {
			index = $(slid+" .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");
	
		//上一页、下一页按钮透明度处理
		$(slid+" .preNext").hover(function() {
			$(this).stop(true,false);
		},function() {
			$(this).stop(true,false);
		});
	
		//上一页按钮
		$(slid+" .pre").click(function() {
			index -= 1;
			if(index == -1) {index = len - 1;}
			showPics(index);
		});
	
		//下一页按钮
		$(slid+" .next").click(function() {
			index += 1;
			if(index == len) {index = 0;}
			showPics(index);
		});
	
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度
		$(slid+" ul").css("width",sWidth * (len));
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放
		$(slid).hover(function() {
		 clearInterval(picTimer);
		},function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if(index == len) {index = 0;}
			},4000); //此4000代表自动播放的间隔，单位：毫秒
		}).trigger("mouseleave");
		
		//显示图片函数，根据接收的index值显示相应的内容
		function showPics(index) { //普通切换
			var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
			$(slid+" ul").stop(true,false).animate({"left":nowLeft},300); //通过animate()调整ul元素滚动到计算出的position
			$(slid+" .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果
			$(slid+" .btn span").stop(true,false).eq(index).stop(true,false); //为当前的按钮切换到选中的效果
		}
	
	};
	
});
/*滚动：*/
	$(document).ready(function(){
	  	$.focus("#focus001");
	});
	 


/*----------滚动门-------------------*/
$(document).ready(function() {
	jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
		$(tab_conbox).find("li").hide();
		$(tabtit).find("li:first").addClass("this").show(); 
		$(tab_conbox).find("div:first").show();
	
		$(tabtit).find("li").bind(shijian,function(){
		  $(this).addClass("this").siblings("li").removeClass("this"); 
			var activeindex = $(tabtit).find("li").index(this);
			$(tab_conbox).children().eq(activeindex).show().siblings().hide();
			return false;
		});
	
	};
	/*调用方法如下  click：*/
$.jqtab(".dq_yq .title_h1",".dq_yq .kuang_in","mouseenter");
$.jqtab(".yq_xw .title_h1",".yq_xw .kuang_in","mouseenter");
});


$(document).ready(function(){
$('#listreport').load(pageContext+'/homepage/listReport?timestamp='+Date.parse(new Date()));
$('#bar').load(pageContext+'/homepage/listCount?timestamp='+Date.parse(new Date()));
$('#listfocus').load(pageContext+'/homepage/listFocus?timestamp='+Date.parse(new Date()));
$('#listeventnews').load(pageContext+'/homepage/listEventnews?timestamp='+Date.parse(new Date()));
$('#listpubinfo').load(pageContext+'/homepage/listPubinfo?timestamp='+Date.parse(new Date()));
$('#listsubjectsentiment').load(pageContext+'/homepage/listSubjectSentiment?timestamp='+Date.parse(new Date()));
$('#listindustrynews').load(pageContext+'/homepage/listIndustryNews?timestamp='+Date.parse(new Date()));
$('#listBriefreport').load(pageContext+'/homepage/listBriefreport?timestamp='+Date.parse(new Date()));
$('#listWarning').load(pageContext+'/homepage/listWarning?timestamp='+Date.parse(new Date()));
$.ajax({
 url: pageContext+"/homepage/listWorkCount", 
 type: "POST", 
  dataType: "json",
  data: "{}", 
  contentType: "application/json; charset=utf-8",
  success: function(json) {

$("#mailCount").text(json.mailCount);
$("#warnCount").text(json.warnCount);
   }
   });
   
   $.ajax({
		 url: pageContext+"/homepage/selectTotalCount", 
		 type: "POST", 
		 dataType:"json",
		 success: function(data) {
			 $("#totalcount").text(data.totalcount);
			 $("#newscount").text(data.newscount);
			 $("#bbscount").text(data.bbscount);
			 $("#blogcount").text(data.blogcount);
			 $("#weibocount").text(data.weibocount);
			 $("#twittercount").text(data.twittercount);
			 $("#journalcount").text(data.journalcount);
			 $("#blogcount").text(data.blogcount);
			 $("#weixincount").text(data.weixincount);
			}
		   });
   
   
   $("#totalcount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment";
	});
	$("#newscount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=1";
	});
	$("#bbscount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=2";
	});
	$("#weibocount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=3";
	});
	$("#blogcount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=4";
	});
	$("#journalcount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=5";
	});
	$("#twittercount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=6";
	});
	$("#weixincount").on("click", function() {
		window.location.href=pageContext+"/subject/toListSubjectSentiment?type=7";
	});
}); 

   
