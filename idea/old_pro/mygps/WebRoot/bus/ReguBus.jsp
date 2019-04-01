<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.servlet.*" %>
<%@ page import="com.hibernatespring.*" %>
<%@ page import="com.persistencelayer.PersistenceLayerLiuhx" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		PersistenceLayerLiuhx persistenceLayer = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
		//返回所有的系部记录
		List<Bus> list;
		list = persistenceLayer.findBus();

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>校车线路记录</title>
    
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
	<h3 style="color:#646464; margin-top:15px;">校车线路信息</h3>
	<table id="dg" class="easyui-datagrid" title="校车信息" style="width:640px;height:420px"
			data-options="rownumbers:true,singleSelect:true,pagination:true,pagesize:10,url:'servlet/getReguBus',
			toolbar:'#tb',remoteSort:false,multiSort:true">
		<thead>
			<tr>
				<th data-options="field:'regId',width:100,sortable:true">线路编号</th>
				<th data-options="field:'regStart',width:100,sortable:true">始点站</th>
				<th data-options="field:'regEnd',width:100,sortable:true">终点站</th>
			    <th data-options="field:'regStartTime',width:100,sortable:true">开车时间</th>
			     <th data-options="field:'regContent',width:100,sortable:true">校车编号</th>
			    <th data-options="field:'busId',width:100,sortable:true">校车编号</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newReguBus()">新增线路</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editReguBus()">编辑线路</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyReguBus()">删除线路</a>
		</div>
		
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:380px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">线路信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
			<table>
				<tr>
				<td align="right" style="width: 90px; ">
				<label>线路编号:</label>
				</td>
				<td align="left">
				<input name="regId" class="easyui-textbox" style="width: 120px; " required="true">
				</td>
				<td align="right" style="width: 90px; ">
				<label>校车编号:</label>
				</td>
				<td align="left">
				<select align="left" name="busId" id="type" style="width:120px;" >
					<option selected value="0">请选择</option> 
					<%
         Bus pro=null;
         for(int i=0;i<list.size();i++)
         {
            pro=(Bus)list.get(i);
          %>
                   <option value=<%=pro.getBusId()%>><%=pro.getLicense()%></option>    
                    <%
 }
    %> 
             </select>
				</td> 
				</tr>
				<tr>
、			<td align="right">
				<label>始发站:</label>
				</td>
				<td align="left">
				<input name="regStart" class="easyui-textbox" style="width: 120px;"  >
				</td>
				<td align="right">
				<label>终点站:</label>
				</td>
				<td align="left">
				<input name="regEnd" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				<tr>
				
				<td align="right">
				<label>始发时间:</label>
				</td>
				<td align="left">
			<input name="regStartTime" class="easyui-textbox"  style="width: 120px;">
				</td>
					<td align="right">
				<label>备注:</label>
				</td>
				<td align="left">
				
				<textarea id="regNote" name="regNote" class="easyui-textbox"  style="height:50px;"></textarea>
				</td>
				</tr>	
				<tr>
				<td align="right">
				<label>内容:</label>
				</td>
				<td align="left">
				
				<textarea id="regContent" name="regContent" class="easyui-validatebox"  style="height:150px; width: 120px;"></textarea>
				</td>
				
				</tr>
				</table>
			

		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveReguBus()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	

	<script type="text/javascript"> 
        var url;
        function newReguBus(){
            $('#dlg').dialog('open').dialog('setTitle','新增校车信息');
            $('#fm').form('clear');
             $("#busId option").each(function(){ 
              if($(this).text() == "请选择"){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
            url = 'servlet/newReguBus';
        }
        function editReguBus(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改校车信息');
                $('#fm').form('load',row);
                  $("#busId option").each(function(){ 
              if($(this).text() == row.busId){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
                url = 'servlet/updateReguBus?id='+row.regId;
            }
        }
        function saveReguBus(){
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
        function destroyReguBus(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','确定要删除此分组信息？',function(r){
                    if (r){
                        $.post('servlet/deleteReguBus',{id:row.regId},function(result){
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