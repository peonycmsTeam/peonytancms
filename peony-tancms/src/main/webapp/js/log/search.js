var pageContext;
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
        url: pageContext + "/log/findAllLogs/",
        dataType: "json",
        data: params,
		beforeSend : function() {
			jBox.tip("系统正在加载中...", 'loading');
		},
        success: function(data){
			jBox.closeTip();
            var logs = data.list;
            var text = getText(logs);
            $("#result").html(text);
        }
    });
    return false;
}

function search(){
    var params = "date=" + new Date() + "&currentPage=1";
    $.ajax({
        type: "get",
        url: pageContext + "/log/findAllLogs/",
        dataType: "json",
        data: params,
        success: function(data){
            if (data != null && data.list != null && data.list.length>0) {
                var logs = data.list;
                if (logs != null && logs.length > 0) {
					for (var i = 0; i < logs.length; i++) {
						var log = logs[i];
						document.getElementById("blank").style.display = "none";
						document.getElementById("result").style.display = "";
						document.getElementById("result").innerText = "";
						initResult(data.totalCount);
						pageselectCallback(0);
					}
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

function getText(logs){
    var text = "";
    for (var i = 0; i < logs.length; i++) {
        var log = logs[i];
        text += "<tr>";
        
        text += "<td height='30' align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += log.description;
        text += "</td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += log.operateMode;
        text += "</td>";
		
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += log.operateType;
        text += "</td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += log.userName + "/" + log.fullName;
        text += "</td>";
        
        text += "<td align='center' valign='middle' bgcolor='#FFFFFF'>";
        text += log.operateDate == null ? '' : log.operateDate;
        text += "</td>";
        
        text += "</tr>";
    }
    
    if (logs.length < 10) {
        for (var i = 0; i < 10 - logs.length; i++) {
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
			