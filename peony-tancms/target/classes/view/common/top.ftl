<!----头部--->
<script>
  function loginOut(){

    var submit = function(v, h, f) {
		if (v == 'ok') {
		 	var base=window.location.href;
    		if(base.indexOf("mdyq.peonydata.com")>0){
    			window.location.href="${request.getContextPath()}/logout";
    		}else{
    			window.location.href="${request.getContextPath()}/userLogout";
   			 }
		
 		 }
		
  }
    $.jBox.setDefaults({
		defaults : {
			border : 0
		}
	})
  
  $.jBox.confirm("您确定要退出吗？", "提示", submit);
}

	var t;
	function show() {
	var date = new Date(); //日期对象
	var now = "";
	now = date.getFullYear() + "年"; //读英文就行了
	now = now + (date.getMonth() + 1) + "月"; //取月的时候取的是当前月-1如果想取当前月+1就可以了
	now = now + date.getDate()  + "日   ";
	now = now + date.getHours() + ":";
	if(date.getMinutes()<10){
		now = now +'0'+ date.getMinutes() + ":";
	}else{
		now = now + date.getMinutes() + ":";
	}
	if(date.getSeconds()<10){
		now = now +'0'+ date.getSeconds() ;
	}else{
		now = now + date.getSeconds() ;
	}
	
	document.getElementById("nowDiv").innerHTML = now; //div的html是now这个字符串
	t= setTimeout("show()", 1000); //设置过1000毫秒就是1秒，调用show方法
	}
	
	$(function() {
	show();
	var isAgentUser="${userfront.agentId}";
	var useAgentLogo="${userfront.useAgentLogo}";
	var agentlogo="${agent.logo}";
	if(isAgentUser>0){
		if(agentlogo!="" && useAgentLogo==1){
			$(".logo").css("background","url(http://user.peonydata.com/peonydata/${agent.logo})  no-repeat scroll center center rgba(0, 0, 0, 0)");
		}else{
			$(".logo").css("background","url('${request.getContextPath()}/images/img/mddsj_index/mddsj_logo.png')  no-repeat scroll center center rgba(0, 0, 0, 0)");
		}
	}else{
		$(".logo").css("background","url('${request.getContextPath()}/images/img/mddsj_index/mddsj_logo.png')  no-repeat scroll center center rgba(0, 0, 0, 0)");
	}	
		
	})
</script>
  
<div class="header">
  <div class="bg">
  <!----皮肤切换新增开始--->
<link rel="stylesheet" href="${request.getContextPath()}/css/front/qskin.css">
<div class="qskin"><li <#if userfront.userStyle==1>class="this"</#if> id = "1" ><span class="skin1" ></span></li>
<li <#if userfront.userStyle==2>class="this"</#if> id = "2"><span class="skin2" ></span></li>
<li <#if userfront.userStyle==3>class="this"</#if> id = "3"><span class="skin3" ></span></li></div>
<script type="text/javascript" language="javascript">
$(function() {
$(".qskin li").click(function() {
$(this).addClass("this").siblings().removeClass("this");
var claid=this.id ;

if(claid=='1'){
  $("#cssfile1" ).attr( "href" ,  "${request.getContextPath()}/css/front/mddsj.css" ); 
   $("#cssfile2" ).attr( "href" ,  "${request.getContextPath()}/css/jbox/jbox.css" ); 
   $("#cssfile3" ).attr( "href" ,  "${request.getContextPath()}/css/ztree/zTreeStyle.css" ); 
  } //设置不同皮肤    
if(claid=='2'){
  $("#cssfile1" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lanse/css/front/mddsj.css" );
   $("#cssfile2" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lanse/css/jbox/jbox.css" ); 
   $("#cssfile3" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lanse/css/ztree/zTreeStyle.css" ); 
   } //设置不同皮肤    
if(claid=='3'){
  $("#cssfile1" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lvse/css/front/mddsj.css" ); 
   $("#cssfile2" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lvse/css/jbox/jbox.css" ); 
   $("#cssfile3" ).attr( "href" ,  "${request.getContextPath()}/skin/skin_lvse/css/ztree/zTreeStyle.css" ); 

  } //设置不同皮肤    
  //后台ajax更新数据库及session
$.ajax({url:"${request.getContextPath()}/updateUserStyle?userStyle="+claid+"&time="+new Date(),async:false});

})
});
</script>

<!----皮肤切换新增结束--->
  
    <div class="logo"></div>
    <input type="hidden" id="user_logout_type" value="${userfront.userSource}"/>
     <!--试用用户到期提示 -->
	  <#if userfront.platformType=='2'>
	  <div  class="color_bai tishi_djs">
	  	<#if days lt 0>您当前为试用用户,试用已到期
	  	<#else>
	  		您当前为试用用户,<br/>距试用截止日还有<span>${days}</span>天
	    </#if>
	  </div>
	  </#if>
    <div class="yonghuzhongxin">
      <div class="huany">欢迎 ${userfront.name} 使用本系统 </div>
      <div class="gaimxib"><span class="dengluxb" ><a href="#" onClick="loginOut();" >退出</a></span><span class=gmxf><a href="#" onClick="modifyPwd();" >密码修改</a></span> <span class="timex" id="nowDiv">当前时间：<b>-</b></span></div>
    </div>
  </div>
</div>
<div class="tb2 doanghuang color_bai">
  <div class="bg">
    <ul>
    	<#list menuSet as menu>
       		<li> <a id="${menu.menuId}" href="${request.getContextPath()}${menu.url}">${menu.name}</a></li>
        </#list>
      <!--<li class="home"><a href="${request.getContextPath()}/homepage/listHomePage">首页</a></li>
      <li><a href="${request.getContextPath()}/warning/listWarning">舆情预警</a></li>
      <li><a href="${request.getContextPath()}/event/toListEvent">政务舆情</a></li>
      <li><a href="${request.getContextPath()}/subject/toListSubjectSentiment">定制舆情</a></li>
      <li><a href="${request.getContextPath()}/subject/toListOverseasSubjectSentiment">境外舆情</a></li>
      <li><a href="${request.getContextPath()}/focus/listFocus">公共专题</a></li>
      <li><a href="${request.getContextPath()}/topic/listTopic">事件专题</a></li>
      <li><a href="${request.getContextPath()}/briefreport/listBriefreport">舆情简报</a></li>
      <li><a href="${request.getContextPath()}/mail/listMail">办公平台</a></li>
      <li><a href="${request.getContextPath()}/attention/listAttention">我的收藏</a></li>
      <li><a href="${request.getContextPath()}/thinktank/listChannel">智库</a></li>
      <li><a href="${request.getContextPath()}/system/toEditEvent">系统配置</a></li>-->
    </ul>
  </div>
  
</div>
<!----头部-结束--->
<script>

$(function() {
	    $("ul li a").on("click", function() {
  			$(this).parent().removeClass("home this"); 
            $(this).parent().addClass("home this"); 
            var id=$(this).attr('id'); 
            if(id==1013){
            var userType="${userfront.userType}";
            	if(userType==1){
            		$(this).attr("href","${request.getContextPath()}/system/toEditIndustry");
            	}else{
            	    $(this).attr("href","${request.getContextPath()}/system/toEditEvent");
            	}
            }
        });
})


function modifyPwd(){
		jBox("iframe:${request.getContextPath()}/toModifyPwd", {
		    title: "修改密码",
		    width: 600,
		    height: 252,
			top: '15%',
			id:'moidfypwd',
		    buttons: {}
		});

}
</script>
