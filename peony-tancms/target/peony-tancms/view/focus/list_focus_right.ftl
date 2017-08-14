 <div class="sj_ztw">
        
           
            	
	<#list pagination.list as focus>  
  <div class="sj_zt">
<h2 class="color_bai"><div class="on_right"></div><a href="${request.getContextPath()}/focus/listFocusInfo/${focus.id}">${focus.name}</a></h2>
<div class="sj_zt_in">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <th class="left_th_w">&nbsp;</th>
    <th>新闻</th>
    <th>论坛</th> 
    <th>微博</th>
    <th>博客</th>
    <th>电子报刊</th> 
    <th>twitter</th>
     <th>微信</th>
  </tr>
  <tr>
    <td>今日</td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=1&daytime=0">${focus.dayMap.type1?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=2&daytime=0">${focus.dayMap.type2?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=3&daytime=0">${focus.dayMap.type3?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=4&daytime=0">${focus.dayMap.type4?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=5&daytime=0">${focus.dayMap.type5?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=6&daytime=0">${focus.dayMap.type6?default('0')}</a></td>
   <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=7&daytime=0">${focus.dayMap.type7?default('0')}</a></td>
  
  </tr>
  <tr>
    <td>总量</td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=1">${focus.countMap.type1?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=2">${focus.countMap.type2?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=3">${focus.countMap.type3?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=4">${focus.countMap.type4?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=5">${focus.countMap.type5?default('0')}</a></td>
    <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=6">${focus.countMap.type6?default('0')}</a></td>
       <td><a href="${request.getContextPath()}/focuspage/listFocusPage?focusid=${focus.id}&type=7">${focus.countMap.type7?default('0')}</a></td>
  </tr>
</table>
</div>
  </div></#list>
          <!--分页-出开始--->
      <#if pagination.totalCount gt 0 >
            <div class="dataTables_wrapper no-footer">
              <div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
              <a <#if pagination.pageNo==1 > class="paginate_button previous disabled" <#else> class="paginate_button previous" </#if> 
              aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous"
              href="javascript:" onclick="setPageNo(1);" >首页</a>
              <span>
              	<#if pagination.totalPage gt 5 >
					<#if pagination.totalPage-pagination.pageNo gt 2 >
						<#if pagination.pageNo gt 2 >
							<#list pagination.pageNo-2..pagination.pageNo+2 as i>
								<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             			<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  		href="javascript:" onclick="setPageNo(${i});">${i}</a>
							</#list>
						<#else>
							<#list 1..5 as i>
			             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
			             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
			             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  							</#list>
						</#if>
					<#else>
						<#list pagination.totalPage-4..pagination.totalPage as i>
							<a  aria-controls="example" data-dt-idx="1" tabindex="0"
		             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
		             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
						</#list>
					</#if>
				<#else>
					<#list 1..pagination.totalPage as i>
	             		<a  aria-controls="example" data-dt-idx="1" tabindex="0"
	             		<#if  pagination.pageNo==i> class="paginate_button current" <#else> class="paginate_button" </#if>
	             	  	href="javascript:" onclick="setPageNo(${i});">${i}</a>
  					</#list>
				</#if>
              </span>
              <a <#if pagination.pageNo==pagination.totalPage > class="paginate_button next disabled" <#else> class="paginate_button next" </#if> 
              aria-controls="example" data-dt-idx="7" tabindex="0" id="example_next"
               href="javascript:" onclick="setPageNo(${pagination.totalPage});" >尾页</a>
                <!--显示总页数-->
           		  共<a   class="paginate_buttonzhyz"  
              	aria-controls="example" data-dt-idx="1" tabindex="0">${pagination.totalCount}</a>条
              </div>
            </div>
            </#if>
        <!--分页---结束---> 
  </div>