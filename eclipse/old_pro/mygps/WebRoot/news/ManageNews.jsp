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
    
    <title>通知新闻</title>
    
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
	<script type="text/javascript" src="/mygps/ckeditor/ckeditor.js"></script>
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
	<h3 style="color:#646464; margin-top:15px;">新闻信息</h3>
	<table id="dg" class="easyui-datagrid" title="校车信息" style="width:640px;height:420px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pagesize:10,url:'servlet/getNews',
			toolbar:'#tb',remoteSort:false,multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'newTitle',width:80,sortable:true">标题</th>
				<th data-options="field:'newContent',width:300,sortable:true">内容</th>
				<th data-options="field:'faTime',width:80,sortable:true">发布时间</th>
				<th data-options="field:'faPeople',width:80,sortable:true">发布人</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newNews()">新增新闻</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNews()">编辑新闻</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyNews()">删除新闻</a>
		</div>
		
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:800px;height:480px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">新闻通知</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<label>标题</label>
				<!--  <input name="newTitle" class="easyui-textbox" required="true">-->
				<input name="newTitle" id="newTitle" > 
			</div>
			<div class="fitem">
				<label>内容</label>
				<!--  <textarea id="newContent" name="newContent" class="easyui-validatebox"  style="height:150px; width: 500px;"></textarea>-->
				  
			<textarea rows="30" cols="100" id="newContent" name="newContent" class="easyui-textarea" requied="true"></textarea><br/>
			<script type="text/javascript">var editor=CKEDITOR.replace('overviewContent',
    		{filebrowserUploadUrl:'servlet/collegeInfoUploader?Type=File',
    		 filebrowserImageUploadUrl:'/mygps/servlet/collegeInfoUploader?Type=Image',
    		 filebrowserFlashUploadUrl:'/mygps/servlet/collegeInfoUploader?Type=Flash',
    		 filebrowserWindowWidth : '600',  
			 filebrowserWindowHeight : '400'  
			});
    		// editor.sync(); 
    		
    		</script>
			</div>
			
				<div class="fitem">
				<label>发布人</label>
				<!--  <input name="newTitle" class="easyui-textbox" required="true">-->
				<input name="faPeople" id="faPeolpe" > 
			</div>
		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveNews()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	

	<script type="text/javascript"> 
        var url;
        function newNews(){
            $('#dlg').dialog('open').dialog('setTitle','新增新闻');
            $('#fm').form('clear');
           
            url = 'servlet/newNews';
        }
        function editNews(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改新闻信息');
                $('#fm').form('load',row);
               
                url = 'servlet/updateNews?id='+row.newId;
            }
        }
        function saveNews(){
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
        function destroyNews(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除此分组信息？',function(r){
                    if (r){
                        $.post('servlet/deleteNews',{id:row.newId},function(result){
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
