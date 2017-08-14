
<#if userfront.userId==387 || userfront.userId==1939>
<link rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj_skin2.css">
</#if>


<#if userfront.userStyle==1>
<link  id = "cssfile1" rel="stylesheet" href="${request.getContextPath()}/css/front/mddsj.css">
<link  id = "cssfile2" rel="stylesheet" href="${request.getContextPath()}/css/jbox/jbox.css">
<link  id = "cssfile3" rel="stylesheet" href="${request.getContextPath()}/css/ztree/zTreeStyle.css">

<#elseif userfront.userStyle==2>
<link  id = "cssfile1" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lanse/css/front/mddsj.css">
<link  id = "cssfile2" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lanse/css/jbox/jbox.css">
<link  id = "cssfile3" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lanse/css/ztree/zTreeStyle.css">

<#elseif userfront.userStyle==3>
<link  id = "cssfile1" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lvse/css/front/mddsj.css">
<link  id = "cssfile2" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lvse/css/jbox/jbox.css">
<link  id = "cssfile3" rel="stylesheet" href="${request.getContextPath()}/skin/skin_lvse/css/ztree/zTreeStyle.css">
</#if>
<script type="text/javascript" language="javascript" src="${request.getContextPath()}/js/jquery-1.7.2.min.js"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox.src.js" type="text/javascript"></script>
<script src="${request.getContextPath()}/js/utils/jbox/jquery.jBox-zh-CN.js" type="text/javascript"></script>
<#import "/common/page.ftl" as selectPage>