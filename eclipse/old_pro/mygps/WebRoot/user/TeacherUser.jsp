<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.servlet.*" %>
<%@ page import="com.hibernatespring.*" %>
<%@ page import="org.hibernate.Query" %>
<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
   %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师信息列表</title>
    
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
				leibie: $('#leibie').combobox('getValue'),
				neirong: $('#neirong').textbox('getValue')
			});
			
		}
  
            function downloadFile(){
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "servlet/downloadFile");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "fileName");
            input1.attr("value", "Teacher.xls");
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            form.remove();
        }
        function checkNo()
        {
              var form = document.getElementById('fm');  
            //one表单中第一个元素属性值  
             var content = form.elements(3).value;   
              //user的值  
             alert(content);
        }
       function a(){
             //取出id为“one”表单中所有的属性的数据  
             var form = document.getElementById('fm');  
            //one表单中第一个元素属性值  
             var content = form.elements(3).value;   
              //user的值  
             alert(content);
   
        }
         
          $("input",$("#jobNumber").next("span")).blur(function(){  
    alert("登录名已存在");  
         });   
      
          
	</script>
	
  </head>
  
  <body>
	<h3 style="color:#646464; margin-top:15px;">教师信息管理</h3>
	<table id="dg" class="easyui-datagrid" title="教师分组记录" style="width:700px;height:420px"
			data-options="rownumbers:true,singleSelect:false,pagination:true,pagesize:10,
			toolbar:'#tb',remoteSort:false,multiSort:true">
			
		<thead>
			<tr>
				<th field="ck" checkbox="true"></th>
				<th data-options="field:'teaName',width:120,sortable:true">教师姓名</th>
				<th data-options="field:'jobNumber',width:120,sortable:true">工号</th>
				<th data-options="field:'department',width:120,sortable:true">所在系部</th>
				<th data-options="field:'mobile',width:120,sortable:true">手机号码</th>
				
			</tr>
		</thead>
	
	</table>
	
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
        	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newTeacher()">新增教师</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editTeacher()">编辑教师</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyTeacher()">删除教师</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="detailTeacher()">教师详情</a>
		    <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-import" plain="true" onclick="openUploadDlg()">从Excel导入教师名单</a>
		</div>
		<div>
			类别：<select align="left" name="leibie" id="leibie" class="easyui-combobox" style="width:100px;" >
			 <option selected value="0">工号</option> 
			 <option  value="1">姓名</option> 
			 <option  value="2">部门</option> 
							
             </select>&nbsp;&nbsp;
                 输入字段：<input name="neirong" id="neirong" class="easyui-textbox" style="width: 120px;"  >&nbsp;&nbsp;
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">查询</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:600px;height:280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">教师信息</div>
		<form id="fm" method="post" novalidate>
			<div class="fitem">
				<table>
				<tr>
				<td align="right" style="width: 100px; ">
				<label>教师姓名:</label>
				</td>
				<td align="left">
				<input name="teacherName" class="easyui-textbox" style="width: 120px; " required="true">
				</td>
				<td align="right">
				<label>教师工号:</label>
				</td>
				<td align="left">
				<input id="tt" name="jobNumber" class="easyui-textbox" style="width: 120px;" required="true" > 
				
				</td> 
				</tr>
				
				<tr>
				<td align="right">
				<label>系      部:</label>
				</td>
				<td align="left">
					<select  name="department" id="department" style="width:120px;"  >
			 
		 <option selected value="纺织服装系">纺织服装系</option> 
			 <option  value="机电系">机电系</option> 
			 <option  value="经管系">经管系</option> 
			 <option value="建筑系">建筑系</option> 
			 <option  value="人文系">人文系</option> 
			 <option  value="外语系">外语系</option> 
			 <option  value="艺术与设计系">艺术与设计系</option> 
			 <option  value="现代教育技术中心">现代教育技术中心</option> 
			 <option  value="图书馆">图书馆</option>
			 <option  value="党政办">党政办</option> 
			 <option  value="组织人事部">组织人事部</option>
			 <option  value="教务处">教务处</option> 
			 <option  value="科研设备处">科研设备处</option> 
			 <option  value="学工部">学工部</option>
			 <option  value="总务处">总务处</option> 
			 <option  value="团委">团委</option>  
    
 </select>
				
				</td>
				<td align="right">
				<label>性  别:</label>
				</td>
				<td align="left">
				<select align="left" name="gender" id="gender" style="width:120px;" >
			        <option selected value="0">男</option> 
					<option  value="1">女</option>
             </select>
				</td>
				</tr>
				<tr>
、
				<td align="right">
				<label>电  话:</label>
				</td>
				<td align="left">
				<input name="telephone" class="easyui-textbox" style="width: 120px;"  >
				</td>
				<td align="right">
				<label>手  机:</label>
				</td>
				<td align="left">
				<input name="mobile" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				<tr>
				
				<td align="right">
				<label>邮  箱:</label>
				</td>
				<td align="left">
			<input name="email" class="easyui-textbox"  style="width: 120px;">
				</td>
				</tr>
				
				</table>
			</div> 
	
		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveTeacher()" style="width:90px">保存</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	
	<div id="dlg2" class="easyui-dialog" style="width:600px;height: 280px;padding:10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">教师详细信息</div>
		<form id="fm2" method="post" novalidate>
			<div class="fitem">
				<table>
				<tr>
				<td align="right" style="width: 100px; ">
				<label>教师姓名:</label>
				</td>
				<td align="left">
				<input name="teaName" class="easyui-textbox" style="width: 120px; " required="true">
				</td>
				<td align="right">
				<label>教师工号:</label>
				</td>
				<td align="left">
				<input id="tt" name="jobNumber" class="easyui-textbox" style="width: 120px;" required="true" > 
				
				</td> 
				</tr>
				
				<tr>
				<td align="right">
				<label>系      部:</label>
				</td>
				<td align="left">
					<select  name="department" id="department" style="width:120px;"  >
			 
		 <option selected value="纺织服装系">纺织服装系</option> 
			 <option  value="机电系">机电系</option> 
			 <option  value="经管系">经管系</option> 
			 <option value="建筑系">建筑系</option> 
			 <option  value="人文系">人文系</option> 
			 <option  value="外语系">外语系</option> 
			 <option  value="艺术与设计系">艺术与设计系</option> 
			  <option  value="现代教育技术中心">现代教育技术中心</option> 
			 <option  value="图书馆">图书馆</option>
			 <option  value="党政办">党政办</option> 
			 <option  value="组织人事部">组织人事部</option>
			 <option  value="教务处">教务处</option> 
			 <option  value="科研设备处">科研设备处</option> 
			 <option  value="学工部">学工部</option>
			 <option  value="总务处">总务处</option> 
			 <option  value="团委">团委</option>  
    
 </select>
				
				</td>
				<td align="right">
				<label>性  别:</label>
				</td>
				<td align="left">
				<select align="left" name="gender" id="gender" style="width:120px;" >
			        <option selected value="0">男</option> 
					<option  value="1">女</option>
             </select>
				</td>
				</tr>
				<tr>
、
				<td align="right">
				<label>电  话:</label>
				</td>
				<td align="left">
				<input name="telphone" class="easyui-textbox" style="width: 120px;"  >
				</td>
				<td align="right">
				<label>手  机:</label>
				</td>
				<td align="left">
				<input name="mobile" class="easyui-textbox" style="width: 120px;" required="true">
				</td>
				</tr>	
				
				<tr>
				
				<td align="right">
				<label>邮  箱:</label>
				</td>
				<td align="left">
			<input name="EMail" class="easyui-textbox"  style="width: 120px;">
				</td>
				<td align="right">
				<label>随机码:</label>
				</td>
				<td align="left">
				<input name="idToken" class="easyui-textbox" style="width: 120px;">
				</td>
				</tr>

				</table>
			</div> 
	
		</form>
	</div>
	<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')" style="width:90px">返回</a>
	</div>
<div id="dlgUpload" class="easyui-dialog" style="width:500px;height:250px;padding:10px 20px" closed="true"
		buttons="#dlgUpload-buttons">
		<div class="ftitle">从Excel文件导入教师信息</div>
		<form id="fmUpload" method="post" action="servlet/doTeacherUpload" enctype="multipart/form-data">
			<div class="fitem">
				<label style="width:40px">文件:</label>
				<input id="fileUpload" name="fileUpload" class="easyui-filebox" 
					data-options="prompt:'请选择一个xls文件...', buttonText:'选择文件'" style="width:400px">
			</div>
			<div class="fitem" style="text-align:right;margin-top:15px;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="downloadFile()" style="width:180px;">下载教师信息模板文件</a>
			</div>
			<div class="fitem" id="progress">
				<img alt="" src="images/Progress1.gif">
			</div>
			
		</form>
	</div>
	<div id="dlgUpload-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="doFileUpload()" style="width:90px">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlgUpload').dialog('close')"
			style="width:90px">取消</a>
	</div>
	<script type="text/javascript"> 
        var url;
      
        //获得教师信息
		$(document).ready(
				function() {
					//初始化datagrid控件
					$('#dg').datagrid({
						url : 'servlet/getTeacher',
					});
					
			$('#tt').textbox({
			onChange: function(newValue, oldValue){		

		  $.ajax({ 
           type: "post", 
                                        url: "servlet/yanzheng?jobNo="+newValue, 
                                        dataType: "json", 
                                        success: function (result) { 
                                                //$("input#showTime").val(data[0].demoData); 
                                                // var result = eval('('+result+')');
                                                alert(result);
                                        }, 
                                        error: function (XMLHttpRequest, textStatus, errorThrown) { 
                                                alert(errorThrown); 
                                        } 
                                });
		//alert(newValue);
			}
		});
				
	});

         
        function newTeacher(){
            $('#dlg').dialog('open').dialog('setTitle','新增教师');
            $('#fm').form('clear');
              //取得系部放入下拉框
            
              $("#department option").each(function(){ 
              if($(this).text() == "纺织服装系"){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
              $("#gender option").each(function(){ 
              if($(this).text() == "男"){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
             
            url = 'servlet/newTeacher';
        }
        function editTeacher(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg').dialog('open').dialog('setTitle','修改教师');
                $('#fm').form('load',row);
            //  alert(row.groupName);
          
              $("#department option").each(function(){ 
              if($(this).text() == row.department){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
                $("#gender option").each(function(){ 
              if($(this).text() == row.gender){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
                url = 'servlet/updateTeacher?id='+row.teacherId;
            }
        }
        function detailTeacher(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $('#dlg2').dialog('open').dialog('setTitle','教师详细信息');
                $('#fm2').form('load',row);
                $("#gender option").each(function(){ 
              if($(this).text() == row.gender){ 
              $(this).attr("selected","selected"); 
              } 
             }); 
            }
        }
        function saveTeacher(){
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
      function destroyTeacher(){
            var rows = $('#dg').datagrid('getSelections');
            if (rows.length>0) {
				var ids = new Array();
				var i;
				for(i=0; i<rows.length; i++) {
					ids.push(rows[i].teacherId);
				}
				var postIdString = ids.join(",");
                $.messager.confirm('确认','确定要删除此教师信息？',
                  function(r){
                    if (r){
                        $.post('servlet/deleteTeacher',{id:postIdString},function(result){
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
        
        function openUploadDlg() {
			$('#dlgUpload').dialog('open').dialog('setTitle', 'Excel教师信息导入');
		}
		
		function doFileUpload() {
			var file = ($("#fileUpload").textbox('getValue'));
    		if (file == "") {
        		$.messager.alert('Excel教师数据导入', '请选择将要上传的教师信息文件!');        
    		}
    		else {
        		var stuff = file.substring(file.lastIndexOf(".")).toLowerCase();
        		if (stuff != '.xls') {
            		$.messager.alert('Excel教师信息导入','文件类型不正确，请选择.xls文件!'); 
        		}
        		else {
        			$("#progress").show();
					$('#fmUpload').form('submit', {
                		onSubmit: function() {
                    		return $(this).form('validate');
                		},
                		success: function(data) {  
                			var data = eval('(' + data + ')');             										
 							$.messager.alert('Excel教师信息数据导入导入', data.success, 'info');
 							$('#dlgUpload').dialog('close'); // close the dialog
 							$('#dg').datagrid('reload'); // reload the user data
 							$("#progress").hide();
                		}
					}); 
            
        		}
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