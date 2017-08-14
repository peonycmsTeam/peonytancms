<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj.css">
<script src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.getContextPath()}/js/utils/validform/Validform_v5.3.2_min.js"></script>
<script src="${request.getContextPath()}/js/topic/topic_edit.js"></script>
<link href="${request.getContextPath()}/css/validform/style.css" rel="stylesheet" type="text/css" media="all"/>
<!--[if lte IE 6]>
	<script type="text/javascript" src="style/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
        DD_belatedPNG.fix("img,.logo,.DD_png,.DD_png_w li,.DD_png_w dt,.DD_png_w span,.doanghuang li,.tb,.tb2,.title_h1,.title_h1 li,.more,.t_tit,.t_tit1,.t_tit2,.t_tit3,.t_tit4");
        </script>
<![endif]-->
</head>
<body>

<form  id="topicEditForm">
<input type="hidden" id="id" name="id" value="${topic.id}"/>
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
    <div class="tneirong_2"  style="Z-index:1; position:inherit;">
      <div id="wait"  style="Z-index:2;  position:absolute; width:480px; height:500px; display:none;">
         	<div class="tuxing_dengdai" style="margin-top:170px;"> </div>
         	正在保存...
      </div>
      <div class="tneirong_2_in">
        <div class="table_tanchu">
          <table border="0" cellspacing="0" cellpadding="0">
            <tr>
              <th>标题:</th>
              <td>
                <input type="text" name="name" id="name" value="${topic.name}" datatype="*" nullmsg="请填写标题"/></td>
            </tr>
            <tr>
              <th>摘要：
</th>
              <td><textarea style="height:50px; width:100%" name="abstruct" id="abstruct" >${topic.abstruct}</textarea></td>
            </tr>
             <tr>
              <th>地域关键词： </th>
              <td><textarea style="height:100px; width:100%" name="area" id="area" >${area}</textarea><div class="x_tishi1">例：北京 上海;多个关键词请用空格隔开</div></td>
            </tr>
            <tr>
              <th>主体关键词： </th>
              <td><textarea style="height:100px; width:100%" name="mainKeywords" id="mainKeywords" >${main_keywords}</textarea><div class="x_tishi1">例:义务教育(采集包含"义务教育"的信息);多个关键词用空格隔开</div></td>
            </tr>
            <tr>
              <th>事件关键词： </th>
              <td><textarea style="height:100px; width:100%" name="deputyKeywords" id="deputyKeywords" >${deputy_keywords}</textarea><div class="x_tishi1">例:捐资助学 乱收费(采集包含"义务教育",并且包含"捐资助学"或"乱收费"的信息);多个关键词用空格隔开</div></td>
            </tr>
            <tr>
              <th>过滤关键词：
                </th>
              <td><textarea style="height:50px; width:100%" name="ruleoutKeywords" id="ruleoutKeywords" >${ruleout_keywords}</textarea><div class="x_tishi1">例:治理乱收费(满足上述条件，但是包含"治理乱收费"的信息除外);多个关键词用空格隔开</div></td>
            </tr>
            </table><div class="HackBox"></div><div class="aniu_xinxi_fab">
<input class="tb2 fabiao1 color_bai" type="button" value="保存" id="subBtn" />

                </div><div class="HackBox"></div>
        </div>
      </div>
</form>

</body>
</html>
