//$(window).load(function() {
//   fixfoot();
//});
//
//$(window).resize(function() {
//  fixfoot();
//});
//
//function fixfoot() {
//var foot= $("#foot");
//   var height =$(document.body).height()+foot.height();
//   var height2 =$(window).height();
//   if(height<height2){
//		foot.addClass("foot_cur");
//    }else{
//		foot.removeClass("foot_cur")//恢复原有样式
//   };
//	
//};


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

/*----------滚动门-------------------*/
$(document).ready(function() {
	jQuery.jqtab2 = function(tabtit,shijian) {
		$(tabtit).children("dl").hide();
		
	 $(tabtit).bind(shijian,function(){
		  var width =$(this).width(); 
		  $(this).children("dl").css("min-width",width).css("_width",width).toggle()
		  
		  $(this).children("dl").css("background-position-x",width)
		  $(this).children("span").toggleClass("this")
          return false;
		});
	 $(tabtit).find("dd").bind(shijian,function(){
		  var oValue=$(this).text();
		   $(this).parents().children("span.tb2").text(oValue);
		});	
		
	
	};
	/*调用方法如下  click：*/
$.jqtab2(".xuanze","click");
$.jqtab2(".xuanze2","click");
$.jqtab2(".xuanze3","click");
});

/*----------滚动门-------------------*/
$(document).ready(function() {
	jQuery.jqtab3 = function(tabtit,shijian) {
		$(tabtit).children("dl").hide();
	 $(tabtit).bind(shijian,function(){
		  
		  $(this).children("dl").toggle()
		  
		
		  $(this).children("span").toggleClass("this")
          return false;
		});
	 	
	};
	/*调用方法如下  click：*/
$.jqtab3(".all_xz2","click");
});


/*----------滚动门-------------------*/
$(document).ready(function() {
	jQuery.jqtab4 = function(tabtit,tabtit2,tabtit3,shijian) {
		$(tabtit).parent().children(tabtit2).hide();
	 $(tabtit).bind(shijian,function(){
		  $(tabtit2).toggle()
		  $(tabtit).toggleClass("this")
          return false;
		});
		
		 $(tabtit3).bind(shijian,function(){
		  $(tabtit2).toggle()
		  $(tabtit).toggleClass("this")
          return false;
		});
	 	 
	};
	/*调用方法如下  click：*/
$.jqtab4(".more_shezhi span",".yincang2",".more1","click");
});

/*----------滚动门-------------------*/
$(document).ready(function() {
	$(".yz:first").addClass("fxdfw");
	jQuery.jqtab5 = function(tabtit,shijian) {
		$(tabtit).find(".yz2").hide();
		$(tabtit).find(".yz2:first").show(); 
		$(tabtit).find(".yz:first .yz2").show();
	$(tabtit).find(".yz:first h2").addClass("this")
		$(tabtit).find("h2").bind(shijian,function(){
			  $(this).parent().siblings(".yz").children("h2").removeClass("this")
			   $(this).parent(".yz").children("h2").addClass("this")
		  $(this).parent().siblings(".yz").children(".yz2").hide();
		  $(this).parent(".yz").children(".yz2").show();
			return false;
		});
	
	};
	/*调用方法如下  click：*/
$.jqtab5(".yincang2","click");
});


/*----------th适配宽度-------------------*/
$(document).ready(function() {
	 $(".zhuanti_kuaibao th input[type='checkbox']").parent("th").css("width","20")
});


$(".tbfasongyouxiang a:contains(发送邮件)").each(function(){
	$(this).parent(".tbfasongyouxiang").addClass("tbfasongyouxiang60");
 });