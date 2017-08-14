function GetTestStatisticsByCategory() {
    $.ajax({
        type: "post",
        dataType: "text", traditional: true,
        data: { oper: "bycategory" },
        url: AjaxUrl,
        async: false,//表示同步执行
        success: function (data, textStatus) {
            if (data != null) {
                  
                if (data) {
                    datapie_category = eval('(' + data + ')');
                }
                else {
                    alert("获取测试类型统计数据失败！");
                }
            }
        },
        complete: function (XMLHttpRequest, textStatus) { },
        error: function (e) {
              
            alert("获取测试类型统计数据失败，请刷新页面重新加载！");
        }
    });
}