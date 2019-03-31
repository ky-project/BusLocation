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
    
    <title>校车信息记录</title>
    
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
	<h3 style="color:#646464; margin-top:15px;">校车信息</h3>
	<table id="dg" class="easyui-datagrid" title="校车信息" style="width:640px;height:420px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pagesize:10,url:'servlet/getBus',
			toolbar:'#tb',remoteSort:false,multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'busId',width:100,sortable:true">校车编号</th>
				<th data-options="field:'type',width:100,sortable:true">校车类型</th>
				<th data-options="field:'seatSum',width:100,sortable:true">座位数</th>
			    <th data-options="field:'license',width:100,sortable:true">车牌号</th>
			    <th data-options="field:'driverName',width:100,sortable:true">司机姓名</th>
			    <th data-options="field:'driverTel',width:100,sortable:true">联系电话</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newBus()">新增校车</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editBus()">编辑校车</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyBus()">删除校车</a>
		</div>
		
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">校车信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
			<table>
				<tr>
				<td align="right" style="width: 90px; ">
				<label>校车编号:</label>
				</td>
				<td align="left">
				<input name="busId" class="easyui-textbox" style="width: 120px; " required="true">
				</td>
				<td align="right" style="width: 90px; ">
				<label>校车类型:</label>
				</td>
				<td align="left">
				<select align="left" name="type" id="type" style="width:120px;" >
			        <option selected value="大客车">大客车</option> 
					<option  value="中客车">中客车</option>
					<option  value="小客车">小客车</option>
             </select>
				</td> 
				</tr>
				<tr>
、			<td align="right">
				<label>座位数:</label>
				</td>
				<td align="left">
				<input name="seatSum" class="easyui-textbox" style="width: 120px;"  >
				</td>
				<td align="right">
				<label>车牌号:</label>
				</td>
				<td align="left">
				<input name="license" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				<tr>
				
				<td align="right">
				<label>司机姓名:</label>
				</td>
				<td align="left">
			<input name="driverName" class="easyui-textbox"  style="width: 120px;">
				</td>
					<td align="right">
				<label>联系电话:</label>
				</td>
				<td align="left">
				<input name="driverTel" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				</table>

		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveBus()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	

	<script type="text/javascript"> 
        var url;
        function newBus(){
            $('#dlg').dialog('open').dialog('setTitle','新增校车信息');
            $('#fm').form('clear');
             $("#type option").each(function(){ 
              if($(this).text() == "大客车"){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
            url = 'servlet/newBus';
        }
        function editBus(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改校车信息');
                $('#fm').form('load',row);
                  $("#type option").each(function(){ 
              if($(this).text() == row.type){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
                url = 'servlet/updateBus?id='+row.busId;
            }
        }
        function saveBus(){
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
        function destroyBus(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除此分组信息？',function(r){
                    if (r){
                        $.post('servlet/deleteBus',{id:row.busId},function(result){
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