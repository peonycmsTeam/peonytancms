<form name="form1" id="form1" action="${request.getContextPath()}/subject/toListSubjectSentimentOld" method="post" >
<input type="hidden" id="subjectid" name="subjectid" value="${CurrentSubjectPage.subjectid}"/>
<input type="hidden" id="currentId" name="currentId" value=""/>
<input type="hidden" id="pageNo" name="pageNo" value="${pagination.pageNo}"/>
<input type="hidden" id="startTime" name="startTime" />
<input type="hidden" id="overTime" name="overTime" />
<input type="hidden" id="newslev" name="newslev" value="${CurrentSubjectPage.newslev}"/>
<input type="hidden" id="groupseedid" name="groupseedid"  value=""/>
<input type="hidden" id="totalCount" name="totalCount" value="${pagination.totalCount}">
<input type="hidden" id="type" name="type" value="${CurrentSubjectPage.type}">
  <div class="yuqing_list">
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <#list pagination.list as subjectPage>
          <tr>
            <td valign="top"><input type="checkbox" name="chk_list" id="chk_list" value="${subjectPage.id}" /></td>
            <td colspan="2">
                <h1 <#if subjectPage.isRead != 1>  class="color_hongse_n" </#if> >
                    <a href="javascript:void(0);" onclick="loadSubjectPage('${subjectPage.id}','${subjectPage.groupseedid}')" title="${subjectPage.title}">
                        <#if subjectPage.title?length lt 30>
                            ${subjectPage.title}
                        <#else>
                             ${subjectPage.title[0..29]}...
                        </#if>
                    </a>
                </h1>
              <p>${subjectPage.summary}</p>
              <div class="laiyuan color_huise">
                <div class="on_right color_hongse_n">
                <span>
                    <select name="polarity_${subjectPage.id}" id="polarity_${subjectPage.id}" onchange="updateSubjectPagePolarity('${subjectPage.id}')">
                        <option value="1" <#if subjectPage.polarity==1> selected="selected" </#if> >正面</option>
                        <option value="-1" <#if subjectPage.polarity==-1> selected="selected" </#if> >负面</option>
                        <option value="0" <#if subjectPage.polarity==0> selected="selected" </#if> >争议</option>
                      </select>
                </span> 
                <span>
                    <select name="operation_${subjectPage.id}" id="operation_${subjectPage.id}" onchange="addOperation('${subjectPage.id}')">
                        <option value="0">操作</option>
                        <option value="1">加入预警</option>
                        <option value="2">加入专题</option>
                        <option value="3">加入收藏</option>
                        <option value="4">加入简报</option>
                      </select>
                </span>
                </div>
                <em>来源:${subjectPage.website}</em>
                <em>发布时间:${subjectPage.publishdate?string("yyyy-MM-dd HH:mm:ss")}</em>
                <em>相似信息个数:${subjectPage.groupcount}</em>
                <!--
                <em>点击数：${subjectPage.clickcount}</em>
                <em>回复数：${subjectPage.replycount}</em>
                  -->
              </div>
            </td>
          </tr>
      </#list>
    </table>
    <!--分页开始-->
    <#if pagination.totalCount gt 0 >
        <div class="dataTables_wrapper no-footer">
          <div class="dataTables_paginate paging_simple_numbers" id="example_paginate">
          <a <#if pagination.pageNo==1 > class="paginate_button previous disabled" <#else> class="paginate_button previous" </#if>
          aria-controls="example" data-dt-idx="0" tabindex="0" id="example_previous" href="javascript:" onclick="setPageNo(1);" >首页</a>
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
    <!--分页结束-->
  </div>
</form>
<div class="HackBox"></div>

