<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ManageMenu.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
	<!--
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/icon.css">
	  <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/easyui/demo/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4/datagrid-detailview.js"></script>
	-->
	 <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/default/easyui.css">  
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/themes/icon.css">  
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4/demo/demo.css">  
    <script type="text/javascript" src="jquery-easyui-1.4/jquery.min.js"></script>  
    <script type="text/javascript" src="jquery-easyui-1.4/jquery.easyui.min.js"></script>  
    <script type="text/javascript" src="jquery-easyui-1.4/datagrid-detailview.js"></script>
	<script type="text/javascript">
		var subMenuTableName = "#ddv-0";
		var reloadSub = false;
		$(function(){
			$('#dg').datagrid({
				view: detailview,
				detailFormatter:function(index,row){
					return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
				},
				onExpandRow: function(index,row){
					$('#ddv-'+index).datagrid({
						url:'servlet/getMenuList?menuLevel=sub&fatherId=' + row.menuId,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						loadMsg:'',
						height:'auto',
						columns:[[
							{field:'menuName',title:'菜单标题',width:100},
							{field:'menuType',title:'菜单类型',width:100},
							{field:'menuKeyOrUrl',title:'菜单指令码或链接地址',width:440}//,
							//{field:'fatherMenuId',title:'父菜单ID',width:100}
						]],
						onResize:function(){
							$('#dg').datagrid('fixDetailRowHeight',index);
						},
						onLoadSuccess:function(){
							setTimeout(function(){
								$('#dg').datagrid('fixDetailRowHeight',index);
							},0);
						},
						onClickRow: function(rowIndex,row) {
							//记下最后选择数据行的所在Datagrid的名字
							subMenuTableName = "#ddv-" + index;
						}
					});
					$('#dg').datagrid('fixDetailRowHeight',index);
				}
			});
		});
	</script>
	
  </head>
  
  <body>
  	<h3 style="color:#646464; margin-top:15px;">微信菜单管理</h3>
	  <table id="dg" class="easyui-datagrid" title="微信界面菜单定义" style="width:640px;height:420px"
		data-options="singleSelect:true,fitColumns:true,url:'servlet/getMenuList?menuLevel=main',
		toolbar:'#tb',remoteSort:false,multiSort:true">	
	<!-- <table id="dg" style="width:640px;height:500px"	url="servlet/getMenuList?menuLevel=main" 
		title="微信界面菜单定义" singleSelect="true" fitColumns="true" toolbar="#tb">-->
		<thead>
			<tr>
				<th field="menuName" width="100">菜单标题</th>
				<th field="menuType" width="100">菜单类型</th>
				<th field="menuKeyOrUrl" width="440">菜单指令码或链接地址</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto;">
		<div style="margin-bottom:5px">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="editMenu()">编辑父菜单</a>
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSubMenu()">添加子菜单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editSubMenu()">修改子菜单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroySubMenu()">删除子菜单</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="refreshMenu()">确认并提交给微信（慎用）</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:500px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">微信界面菜单定义</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>菜单标题:</label>
				<input name="menuName" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>菜单类型:</label>
				<select name="menuType" class="easyui-combobox" data-options="editable:false" required="true">
					<option value="Click">Click</option>
					<option value="View">View</option>
				</select>
			</div>
			<div class="fitem">
				<label>指令或链接:</label>
				<input name="menuKeyOrUrl" class="easyui-textbox" data-options="width:320" required="true">
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveMenu()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	

	<script type="text/javascript"> 
        var url;
        function newSubMenu(){
        	var row = $(subMenuTableName).datagrid('getSelected');
        	//必须先选中一个父菜单并展开父菜单，才能新建一个子菜单
        	if (row) {
	            $('#dlg').dialog('open').dialog('setTitle','添加子菜单');
	            $('#fm').form('clear');
	            url = 'servlet/newMenu?fatherMenuId=' + row.fatherMenuId;
	            reloadSub = true;
           }
        }
        function editMenu(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改父菜单');
                $('#fm').form('load',row);
                url = 'servlet/updateMenu?menuId='+row.menuId;
                reloadSub = false;
            }
        }
        function editSubMenu(){
            var row = $(subMenuTableName).datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改子菜单');
                $('#fm').form('load',row);
                url = 'servlet/updateMenu?menuId='+row.menuId;
                reloadSub = true;
            }
        }
        function saveMenu(){
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
                        // reload the user data
                        if (!reloadSub)
                        	$('#dg').datagrid('reload');    
                        else
                        	$(subMenuTableName).datagrid('reload');
                    }
                }
            });
        }
        function destroySubMenu(){
            var row = $(subMenuTableName).datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除此菜单？',function(r){
                    if (r){
                        $.post('servlet/deleteMenu',{menuId:row.menuId},function(result){
                            if (result.success){
                                //$('#dg').datagrid('reload');    // reload the user data
                                $(subMenuTableName).datagrid('reload');
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
        function refreshMenu(){
            $.messager.confirm('确认','确定要更新微信用户端的操作菜单吗？',function(r){
				if (r){
					$.post('servlet/createMenu',function(result){
						if (result.success){
							$.messager.show({    // show success message
								title: 'Success',
								msg: result.success
							});
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
    </script>
	
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 20px;
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
            width:80px;
        }
        .fitem input{
            width:160px;
        }
        .fitem select{
            width:160px;
        }
    </style>
  </body>
</html>
