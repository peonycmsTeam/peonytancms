<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<body>
<!----头部--->
	
<input type="hidden" id="pageContext" value="${request.getContextPath()}"/>
<!----头部--->


    <table class="zh_fx_table" width="100%" border="0" cellspacing="5" cellpadding="5">
                  <tr>
                    <th>排行</th>
                    <th>事件名称</th>
                    <th>综合指数</th>
                    <th>媒体关注指数</th>
                    <th>网民关注指数</th>
                  </tr>
                  <tr>
                    <th>TOP1</th>
                    <td>${eventname0}</td>
                    <td>${compositeindex0}</td>
                    <td>${mediaAttentionIndex0?string("#.##")}</td>
                    <td>${netizensAttentionIndex0?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP2</th>
                    <td>${eventname1}</td>
                    <td>${compositeindex1}</td>
                    <td>${mediaAttentionIndex1?string("#.##")}</td>
                    <td>${netizensAttentionIndex1?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP3</th>
                    <td>${eventname2}</td>
                    <td>${compositeindex2}</td>
                    <td>${mediaAttentionIndex2?string("#.##")}</td>
                    <td>${netizensAttentionIndex2?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP4</th>
                    <td>${eventname3}</td>
                    <td>${compositeindex3}</td>
                    <td>${mediaAttentionIndex3?string("#.##")}</td>
                    <td>${netizensAttentionIndex3?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP5</th>
                    <td>${eventname4}</td>
                    <td>${compositeindex4}</td>
                    <td>${mediaAttentionIndex4?string("#.##")}</td>
                    <td>${netizensAttentionIndex4?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP6</th>
                    <td>${eventname5}</td>
                    <td>${compositeindex5}</td>
                    <td>${mediaAttentionIndex5?string("#.##")}</td>
                    <td>${netizensAttentionIndex5?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP7</th>
                    <td>${eventname6}</td>
                    <td>${compositeindex6}</td>
                    <td>${mediaAttentionIndex6?string("#.##")}</td>
                    <td>${netizensAttentionIndex6?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP8</th>
                    <td>${eventname7}</td>
                    <td>${compositeindex7}</td>
                    <td>${mediaAttentionIndex7?string("#.##")}</td>
                    <td>${netizensAttentionIndex7?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP9</th>
                    <td>${eventname8}</td>
                    <td>${compositeindex8}</td>
                    <td>${mediaAttentionIndex8?string("#.##")}</td>
                    <td>${netizensAttentionIndex8?string("#.##")}</td>
                  </tr>
                  <tr>
                    <th>TOP10</th>
                    <td>${eventname9}</td>
                    <td>${compositeindex9}</td>
                    <td>${mediaAttentionIndex9?string("#.##")}</td>
                    <td>${netizensAttentionIndex9?string("#.##")}</td>
                  </tr>
                </table>
                
                
                
                
                
       
</body>
</html>
