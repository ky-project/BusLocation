<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
    <meta name="keywords" content="jquery,ui,easy,easyui,web">  
    <meta name="description" content="easyui help you build your web page easily!">  
    <title>Expand row in DataGrid to show subgrid - jQuery EasyUI Demo</title>  
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/default/easyui.css">  
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/themes/icon.css">  
    <link rel="stylesheet" type="text/css" href="http://www.jeasyui.com/easyui/demo/demo.css">  
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>  
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>  
    <script type="text/javascript" src="http://www.jeasyui.com/easyui/datagrid-detailview.js"></script>  
</head>  
<body>  
    <h2>Expand row in DataGrid to show subgrid</h2>  
    <div class="demo-info" style="margin-bottom:10px">  
        <div class="demo-tip icon-tip"> </div>  
       <div>Click the expand button to expand row and view subgrid.</div>  
    </div>  
 <table id="acquisitionTab"></table> 
    <script type="text/javascript">  
$('#acquisitionTab').datagrid({
            view: detailview,//注意1
            title: '我的取数',
            url:'../json/queryAcquisitionList.action',
            fitColumns: true,
            singleSelect:true,
            height: 340,
            pagination: true,
            columns: [
                [
                    {field: 'id', checkbox: true },
                    {field: 'code', title: '任务编号'},
                    {field: 'templateName', title: '主 题',width:50},
                    {field: 'topic', title: '取数目的',width:50}
                ]
            ],
            detailFormatter:function(index,row){//注意2
                return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
            },
            onExpandRow:function(index,row){//注意3
                $('#ddv-'+index).datagrid({
                    url:'../statisticJson/getStatisticTaskByAcqu.action?idapStatisticTask.taskGroupId='+(row.id),
                    fitColumns:true,
                    singleSelect:true,
                    height:'auto',
                    columns:[[
                        {field:'taskId',title:'统计任务ID'},
                        {field:'taskName',title:'任务名称',width:50},
                        {field:'taskStatue',title:'任务状态',formatter:function(value, row, index){
                            if (value) {
                                switch (value) {
                                    case '0':
                                        return '任务创建';
                                        break;
                                    case '1':
                                        return '待执行';
                                        break;
                                    case '2':
                                        return '执行中';
                                        break;
                                    case '3':
                                        return '执行成功';
                                        break;
                                    case '4':
                                        return '执行失败';
                                        break;
                                    case '-1':
                                        return '任务取消';
                                        break;
                                    default :
                                        return '已删除';
                                        break;
                                }
                            }
                        }},
                        {field:'taskType',title:'任务说明',width:100},
                        {field:'statisticOpera',title:'任务操作',width:50,align:'center',
                            formatter:function(value, row, index){
                                if(row.taskStatue != '-2'){
                                    var tdContext = '<a href="#this" onclick="removeStatistic('+(row.taskId)+')">删除</a>&nbsp;&nbsp;';
                                }
                                return tdContext;
                        }}
                    ]],
                    onResize:function(){
                        $('#acquisitionTab').datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        setTimeout(function(){
                            $('#acquisitionTab').datagrid('fixDetailRowHeight',index);
                        },0);
                    }
                });
                $('#acquisitionTab').datagrid('fixDetailRowHeight',index);
            }

        });
        
         </script>  
     
     
</body>  
</html> 
     