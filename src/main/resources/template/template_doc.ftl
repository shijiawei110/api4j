<#list pojos as pojo>
##${pojo.index} : ${pojo.title}
* 接口路径 : ${pojo.path}
* 接口类型 : ${pojo.type}
* 接口作者 : ${pojo.author}
* 接口注释 : ${pojo.desc}
* 接口标注 : ${pojo.tagNote}

<#--入参表格-->
<table>
    <tr>
        <th colspan="${pojo.inputDeepMax}">参数</th>
        <th>类型</th>
        <th>必须</th>
        <th>是否为数组</th>
        <th>长度</th>
        <th>描述</th> 
    </tr>
    <#list pojo.inputParams as param>
    <tr>
        <#list 1..param.hbNum as i>
            <td><blockquote></td>
        </#list>
        <td colspan="${param.sjNum}">${param.name}</td>
        <td>${param.type}</td>
        <td>${param.isRequired}</td>
        <td>${param.isArray}</td>
        <td>${param.lengthLimit}</td>
        <td>${param.desc}</td> 
    </tr>
    </#list>
</table>
<#--入参json mock-->

<#--出参表格-->
<#--出参json mock-->

---------
</#list>
