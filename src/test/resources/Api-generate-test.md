<h2>1.外置类测试</h2>
<h3>1.1.添加人员</h3>
<ul>
    <li>作者 ：mengqiang</li><li>请求地址 ：/out-class/add-user</li><li>请求方式 ：POST</li>
    <li><strong>请求参数：</strong></li>
</ul>
<table>
    <thead>
    <tr style="background-color: #D4EEFC;">
        <th colspan="1"><strong>参数</strong></th>
        <th width="10%"><strong>类型</strong></th>
        <th width="10%"><strong>必须</strong></th>
        <th width="10%"><strong>长度</strong></th>
        <th><strong>描述</strong></th>
    </tr>
    </thead>
    <tbody>
                <tr>
                <td>userName</td>
                <td>String</td>
                <td>是 </td>
                <td>10-128</td>
                <td>用户名 </td>
                </tr>
                <tr>
                <td>userPassword</td>
                <td>String</td>
                <td>是 </td>
                <td>20-200</td>
                <td>密码 </td>
                </tr>
                <tr>
                <td>realName</td>
                <td>String</td>
                <td>否 </td>
                <td>15-35</td>
                <td>真实姓名 </td>
                </tr>
                <tr>
                <td>sexType</td>
                <td>Integer</td>
                <td>是 </td>
                <td></td>
                <td>性别类型<p>1-男，2-女，3-不详 </td>
                </tr>
                <tr>
                <td>mobile</td>
                <td>String</td>
                <td>是 </td>
                <td>11</td>
                <td>手机号 </td>
                </tr>
                <tr>
                <td>remark</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>remark测试 </td>
                </tr>
                <tr>
                <td>children</td>
                <td>List<T><T></td>
                <td>否 </td>
                <td></td>
                <td>子集 </td>
                </tr>
                <tr>
                <td>operateBy</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td> </td>
                </tr>
                <tr>
                <td>operateName</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>操作人名称 </td>
                </tr>
    </tbody>
</table>
<ul><li>请求示例：</li></ul>
<pre data-lang="json"><code>
{
	"userName":"kayleigh.langosh",
	"userPassword":"3a0702uri2a5",
	"realName":"绍辉.赵",
	"sexType":1,
	"mobile":"14732350798",
	"remark":"this is msg cg06x1",
	"children":[
		"s9wvmfg12f"
	],
	"operateBy":"z162fmtp9vc7",
	"operateName":"弘文.吕"
}
</code></pre>
<ul><li><strong>响应参数：</strong></li></ul>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无
<h3>1.2.修改人员</h3>
<ul>
    <li>作者 ：mengqiang</li><li>请求地址 ：/out-class/update-user</li><li>请求方式 ：POST</li>
    <li><strong>请求参数：</strong></li>
</ul>
<table>
    <thead>
    <tr style="background-color: #D4EEFC;">
        <th colspan="1"><strong>参数</strong></th>
        <th width="10%"><strong>类型</strong></th>
        <th width="10%"><strong>必须</strong></th>
        <th width="10%"><strong>长度</strong></th>
        <th><strong>描述</strong></th>
    </tr>
    </thead>
    <tbody>
                <tr>
                <td>userId</td>
                <td>String</td>
                <td>是 </td>
                <td></td>
                <td>人员id </td>
                </tr>
                <tr>
                <td>userName</td>
                <td>String</td>
                <td>是 </td>
                <td>128</td>
                <td>用户名 </td>
                </tr>
                <tr>
                <td>userPassword</td>
                <td>String</td>
                <td>是 </td>
                <td>64</td>
                <td>密码 </td>
                </tr>
                <tr>
                <td>realName</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>真实姓名 </td>
                </tr>
                <tr>
                <td>sexType</td>
                <td>Integer</td>
                <td>是 </td>
                <td></td>
                <td>性别类型1-男，2-女，3-不详 </td>
                </tr>
                <tr>
                <td>mobile</td>
                <td>String</td>
                <td>是 </td>
                <td>11</td>
                <td>手机号 </td>
                </tr>
                <tr>
                <td>remark</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>remark测试 </td>
                </tr>
                <tr>
                <td>operateBy</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td> </td>
                </tr>
                <tr>
                <td>operateName</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>操作人名称 </td>
                </tr>
    </tbody>
</table>
<ul><li>请求示例：</li></ul>
<pre data-lang="json"><code>
{
	"userId":"8636569406",
	"userName":"jazmyn.ondricka",
	"userPassword":"kcpyjgcscar5",
	"realName":"睿渊.姜",
	"sexType":1,
	"mobile":"14732350798",
	"remark":"this is msg cg06x1",
	"operateBy":"55wqla5iltbn",
	"operateName":"伟宸.侯"
}
</code></pre>
<ul><li><strong>响应参数：</strong></li></ul>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无
<h3>1.3.删除人员</h3>
<ul>
    <li>作者 ：mengqiang</li><li>请求地址 ：/out-class/delete-user</li><li>请求方式 ：POST</li>
    <li><strong>请求参数：</strong></li>
</ul>
<table>
    <thead>
    <tr style="background-color: #D4EEFC;">
        <th colspan="1"><strong>参数</strong></th>
        <th width="10%"><strong>类型</strong></th>
        <th width="10%"><strong>必须</strong></th>
        <th width="10%"><strong>长度</strong></th>
        <th><strong>描述</strong></th>
    </tr>
    </thead>
    <tbody>
                <tr>
                <td>userId</td>
                <td>String</td>
                <td>是 </td>
                <td></td>
                <td>人员id </td>
                </tr>
                <tr>
                <td>operateBy</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td> </td>
                </tr>
                <tr>
                <td>operateName</td>
                <td>String</td>
                <td>否 </td>
                <td></td>
                <td>操作人名称 </td>
                </tr>
    </tbody>
</table>
<ul><li>请求示例：</li></ul>
<pre data-lang="json"><code>
{
	"userId":"8321875961",
	"operateBy":"3c4mk9k7if32",
	"operateName":"智宸.王"
}
</code></pre>
<ul><li><strong>响应参数：</strong></li></ul>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;无
