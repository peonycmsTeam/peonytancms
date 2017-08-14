var pageContext;
var nameTemp="";
var pageSize=10;

$(function(){
	pageContext = $("#pageContext").val();
	
	// 设置全局jbox属性
	jBox.setDefaults({ defaults: { top: '40%', border: 0, opacity: 0.2} }); // 修改全局 border
})

function initResult(total){
    var optInit = getOptionsFromForm();
    $("#Pagination").pagination(total, optInit);
}

function getOptionsFromForm(){
    var opt = {
        callback: pageselectCallback
    };
    opt.prev_text = "上一页";
    opt.next_text = "下一页";
    opt.items_per_page = pageSize;
    return opt;
}

function pageselectCallback(page_index, jq){
    var params = "date=" + new Date() + "&currentPage=" + (page_index + 1);
    $.ajax({
        type: "get",
        url: pageContext + "/user/findUsers/" + nameTemp,
        dataType: "json",
        data: params,
		beforeSend : function() {
			jBox.tip("系统正在加载中...", 'loading');
		},
        success: function(data){
			jBox.closeTip();
            var users = data.list;
            var text = getText(users);
            $("#result").html(text);
        }
    });
    return false;
}

function findUsers(){
    var params = "date=" + new Date() + "&currentPage=1";
    $.ajax({
        type: "get",
        url: pageContext + "/user/findUsers/liu/11",
        dataType: "json",
        data: params,
        success: function(data){
            if (data != null && data.list != null && data.list.length>0) {
                var users = data.list;
                if (users != null && users.length > 0) {
                	document.getElementById("blank").style.display = "none";
					document.getElementById("result").style.display = "";
					document.getElementById("result").innerText = "";
					initResult(data.totalCount);
					
		            var text = getText(users);
		            $("#result").html(text);
                }
                else {
                    document.getElementById("blank").style.display = "";
                    document.getElementById("result").style.display = "none";
                }
            }else{
            	jBox.tip("没有找到您要搜索的数据！", 'info');
			}
        }
    });
}

function search(){
    var userName = $('#userName').val().replace(/(^\s*)|(\s*$)/g, "");
    nameTemp = userName;
    var params = "date=" + new Date() + "&currentPage=1";
    $.ajax({
        type: "get",
        url: pageContext + "/user/findUsers/" + userName,
        dataType: "json",
        data: params,
        success: function(data){
            if (data != null && data.list != null && data.list.length>0) {
                var users = data.list;
                if (users != null && users.length > 0) {
                	document.getElementById("blank").style.display = "none";
					document.getElementById("result").style.display = "";
					document.getElementById("result").innerText = "";
					initResult(data.totalCount);
					
		            var text = getText(users);
		            $("#result").html(text);
                }
                else {
                    document.getElementById("blank").style.display = "";
                    document.getElementById("result").style.display = "none";
                }
            }else{
            	jBox.tip("没有找到您要搜索的数据！", 'info');
			}
        }
    });
}

function getText(users){
    var text = "";
    for (var i = 0; i < users.length; i++) {
        var user = users[i];
        text += "<tr>";
        
        text += "<td height='30' align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += user.id;
        text += "</td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += user.userName;
        text += "</td>";
		
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += user.age;
        text += "</td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'><a href='javascript:;' onclick='delUser(";
        text += user.id;
        text += ",this)'>删除</a></td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'><a href='javascript:;' onclick='loadUser(";
        text += user.id;
        text += ")'>查看详细</a></td>";
        
        text += "</tr>";
    }
    
    if (users.length < 10) {
        for (var i = 0; i < 10 - users.length; i++) {
            text += "<tr>";
            
            text += "<td height='30' align='center' valign='middle' bgcolor='#FFFFFF'>";
            text += "&nbsp;";
            text += "</td>";
            
            text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
            text += "&nbsp;";
            text += "</td>";
            
            text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
            text += "&nbsp;";
            text += "</td>";
            
            text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
            text += "&nbsp;";
            text += "</td>";
            
            text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
            text += "&nbsp;";
            text += "</td>";
            
            text += "</tr>";
        }
    }
    
    return text;
}

function delUser(id, obj){
	var submit = function (v, h, f) {  
	    if (v == 'ok'){
	    	$.ajax({
	            type: "delete",
	            url: pageContext + "/user/deleteUser/" + id,
	            dataType: "json",
	            data: null,
	            success: function(data){
	                $(obj).parent().parent().remove();
	            }
	        }); 
	    }
	    return true;
	}; 
	
	$.jBox.confirm("您确定要删除吗？", "提示", submit);
}

function loadUser(id){
	jBox("iframe:" + pageContext + "/user/loadUser/" + id, {
	    title: "查看详细信息",
	    width: 610,
	    height: 300,
		top: '30%',
	    buttons: { '关闭': true }
	});
}

function batchUpdate(){
	var params = {};
	params.ids = new Array();
	params.ids.push(1);
	params.ids.push(2);
	params.ids.push(3);
	params.ids.push(4);
	params.userName = "test";
	params.age = 11;
	
	$.ajax({
		type : "post",
		url : encodeURI(pageContext + "/user/batchUpdateUser"),
		dataType : "json",
		data : params,
		success : function(data) {
			jBox.tip("更新成功！", 'success');
		},
		error: function(data){
			jBox.tip("更新失败！", 'error');
		}
	});
}

			