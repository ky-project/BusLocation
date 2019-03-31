<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.servlet.*" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员信息记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	    function doSearch(){
			$('#dg').datagrid('load',{
				startDate: $('#startDate').datebox('getValue'),
				endDate: $('#endDate').datebox('getValue')
			});
		}
	</script>
  </head>
  
  <body>
	<h3 style="color:#646464; margin-top:15px;">管理员信息管理</h3>
	<table id="dg" class="easyui-datagrid" title="管理员信息" style="width:640px;height:420px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pagesize:10,url:'servlet/getAdmin',
			toolbar:'#tb',remoteSort:false,multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'adminId',width:150,sortable:true">用户名</th>
				<th data-options="field:'password',width:150,sortable:true">密码</th>
				<th data-options="field:'adminName',width:150,sortable:true">管理员姓名</th>
			    <th data-options="field:'mobile',width:150,sortable:true">联系电话</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdmin()">新增管理员</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAdmin()">编辑管理员</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyAdmin()">删除管理员</a>
		</div>
		
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">管理员信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
			<table>
				<tr>
				<td align="right" style="width: 90px; ">
				<label>用户名:</label>
				</td>
				<td align="left">
				<input name="adminID" class="easyui-textbox" style="width: 120px; " required="true">
				</td>
				<td align="right" style="width: 90px; ">
				<label>密码:</label>
				</td>
				<td align="left">
				<input type="password" name="password" class="easyui-validatebox" style="width: 120px;" required="true" > 
				
				</td> 
				</tr>
				<tr>
、			<td align="right">
				<label>管理员姓名:</label>
				</td>
				<td align="left">
				<input name="adminName" class="easyui-textbox" style="width: 120px;"  >
				</td>
				<td align="right">
				<label>联系电话:</label>
				</td>
				<td align="left">
				<input name="telphone" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				<tr>
				
				<td align="right">
				<label>手机:</label>
				</td>
				<td align="left">
			<input name="mobile" class="easyui-textbox"  style="width: 120px;">
				</td>
				</tr>
				
				</table>

		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveAdmin()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	

	<script type="text/javascript"> 
        var url;
        function newAdmin(){
            $('#dlg').dialog('open').dialog('setTitle','新增分组');
            $('#fm').form('clear');
            url = 'servlet/newAdmin';
        }
        function editAdmin(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改分组');
                $('#fm').form('load',row);
                url = 'servlet/updateAdmin?id='+row.id;
            }
        }
        function saveAdmin(){
            $('#fm').form('submit',{
                url: url,
                onSubmit: function(){
                    return $(this).form('validate');
                },
                success: function(result){
                    var result = eval('('+result+')');
                    if (result.errorMsg){
                        $.messager.show({
                            title: 'Error',
                            msg: result.errorMsg
                        });
                    } else {
                        $('#dlg').dialog('close');        // close the dialog
                        $('#dg').datagrid('reload');    // reload the user data
                    }
                }
            });
        }
        function destroyAdmin(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除此分组信息？',function(r){
                    if (r){
                        $.post('servlet/deleteAdmin',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');    // reload the user data
                            } else {
                                $.messager.show({    // show error message
                                    title: 'Error',
                                    msg: result.errorMsg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
	
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:120px;
        }
        .fitem input{
            width:160px;
        }
    </style>
  </body>
</html>
