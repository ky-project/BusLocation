var object=window.dialogArguments;
var returnObject = new Object();
$(function(){
	alert(26);
      treeBuild();
      function treeBuild() {
             switch(object.treeType) {
	             case "resource" : {
	                 document.title= "资源选择" ;
	                 treeBuild1();
	             	}; break;
	             case "all" : {
	            	  var lawLevel=object.lawLevel;
	                  document.title= "全部类别选择" ;
	                  treeBuild1();
	             }
            }
             
      }
      
       //单选树构建
       function treeBuild1(data) {
          var setting = {
                    view : {
                          showIcon: false
                    },
                    check: {
                          enable: true,
                          chkStyle : "checkbox",
                          radioType: "all"
                    },
                    data: {
                          simpleData:{
                                enable: true
                          }
                    }
         };
         $.ajax({
               type: "POST" ,
                 dataType :"json" ,
                 url      : "resource_queryResourceTreeData.do" ,
                 cache: false,
                 data : {
                             "roleId"  :object.roleId
                         } ,
                 success : function(data){
                       if(data== undefined ||data==null) return;
                       $.fn.zTree.destroy("tt");
                       $.fn.zTree.init($("#tt"), setting, data);
                       var treeObj = $.fn.zTree.getZTreeObj( "tt");
                       $.each(treeObj.getCheckedNodes(true),function(){
                    	   treeObj.checkNode(this,true,true,false);
                       })
                 }
         });
       }
       //单选树构建
       function treeBuild2(data) {
          var setting = {
                    view : {
                          showIcon: false
                    },
                    check: {
                          enable: true,
                          chkStyle : "radio",
                          radioType: "all"
                    },
                    data: {
                          simpleData:{
                                enable: true
                          }
                    }
         };
         $.ajax({
               type: "POST" ,
                 dataType :"json" ,
                 url      : "lawMain_queryTreeDataIssure.do" ,
                 cache: false,
                 data : {
                             "propertyBean.catType"  :object.treeType,
                             "propertyBean.catFlag"  :object.treeFlag
                         } ,
                 success : function(data){
                       if(data== undefined ||data==null) return;
                       $.fn.zTree.destroy("tt");
                       $.fn.zTree.init($("#tt"), setting, data);
                       var treeObj = $.fn.zTree.getZTreeObj( "tt");
                       $.each(treeObj.getCheckedNodes(true),function(){
                    	   treeObj.checkNode(this,true,true,false);
                       })
                 }
         });
       }
      $("#define").click(function(){
           var treeObj = $ .fn.zTree.getZTreeObj("tt");
           var checkedNodesArray = treeObj.getCheckedNodes(true);
           var indexSelectNode   = checkedNodesArray[0];
           if(indexSelectNode == null || indexSelectNode==undefined ){
                 returnObject.indexName= "";
                 returnObject.code= "";
           } else{
                 returnObject.indexName=indexSelectNode.name;
                 returnObject.code=indexSelectNode.id;
           }
          window.returnValue = returnObject;
          window.close();
      })
      
      $("#reset").click(function(){
            treeBuild();
      });
})
