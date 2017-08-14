$(function() {
		var pageContext = $("#pageContext").val();
		$("#loginForm").ajaxForm();
		$("#loginForm").Validform({
			btnSubmit:"#subBtn",
			tiptype:1,
			ajaxPost:true,
			beforeSubmit: function(curform){
				var options = {
					url: pageContext + "/loginCheck",
					type: 'POST',
					dataType: 'json',
					async: false,
			     	success: function(data) {
			     		if(data==1){
			     				savepsw();
			     				window.location.href=pageContext+"/homepage/listHomePage";
			     		}else{
			     			jBox.tip('用户名或密码错误','error');
			     		}
			     	},
					error: function(){
						jBox.tip('登陆失败', 'error');
					}
				};
				curform.ajaxSubmit(options);
				
				return false;
			}
		});
		
	    document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	$("#subBtn").click();
	     }
	    }
			
	});
/**
 * 检查cookie是否存在，如果存在，在用户名和密码中赋值
 */
function checkCookie(){
	//获取用户名
	username=getCookie('peony_username');
	//如果用户名存在
	if(username!=null&&username!="")
	{
		psw=getCookie('peony_psw');
		document.getElementById("loginName").value=decodeURI(decodeURI(username));
		document.getElementById("password").value=decodeURI(decodeURI(psw));
		 $("#checkbox").attr("checked",'true');
	}
 
}
/**
 * 查找cookie中是否存在该值
 */
function getCookie(c_name){
	if(document.cookie.length>0){
		c_start=document.cookie.indexOf(c_name+"=");
		if(c_start!=-1){
			c_start=c_start+c_name.length+1;
			c_end=document.cookie.indexOf(";",c_start);
				if(c_end==-1)c_end=document.cookie.length;
	return unescape(document.cookie.substring(c_start,c_end));
		}
	}
return"";
}
/**
 * 用户选择记住密码后，将密码保存近cookie
 */
function savepsw(){
	if(document.getElementById("checkbox").checked){
    var exp = new Date(); 
    exp.setTime(exp.getTime() + 5*365*24*60*60*1000);
	document.cookie='peony_username='+encodeURI(encodeURI(document.getElementById("loginName").value))+';expires='+exp.toGMTString()+";path=/";
	document.cookie='peony_psw='+encodeURI(encodeURI(document.getElementById("password").value))+';expires='+exp.toGMTString()+";path=/";
	}else{
			var exp = new Date();
			exp.setTime(exp.getTime() - 1); 
			document.cookie='peony_username='+encodeURI(encodeURI(document.getElementById("loginName").value))+';expires='+exp.toGMTString()+";path=/";
			document.cookie='peony_psw='+encodeURI(encodeURI(document.getElementById("password").value))+';expires='+exp.toGMTString()+";path=/";
	}
	}