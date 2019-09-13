$(function(){
    //全局变量
    axios.defaults.baseURL = 'http://60.12.85.238:8081/busposition'; //根路径
    //获取总页数并创建分页器
    axios.get('/admin/info/list/pages/10')
        .then(function(result){
            return result.data;
        })
        .then(function(obj){
            if(obj.success){
                let data = obj.data;
                let totalPages = data.totalPages;
                //创建分页器
                let pagination = new MyPagination({
                    pageSize: totalPages, //总页数
                    maxDisplayPages: 5, //最大显示页数
                    selector: '#myPaginator', //分页器选择器
                })
            }
        });
    //创建数据表
    let dt = new MyDataTable({
        selector: '#dataTable',
        rowSize: 10,
        pageNumber: 2,
        showOrder: true,
        showOperator: true,
        titles: {
            departmentName: '部门名',
            realName: '真实姓名',
            phone: '电话号码',
            idCode: '身份证',
            workId: '职工编号',
            email: '邮箱'
        },
    });
    // freshDataTable(dt,2);
    /*let oDeletBtns = $('.myDataTable .delete');
    //监听删除按钮点击事件
    oDeletBtns.click(function(){
        let oTr = $(this).parents('tr');
        let id = dt.getId(oTr);
        deleteDataRow(dt,id);
    });
    //删除数据项
    function deleteDataRow(dataTable,id){
        console.log(id);
        //发送ajax
        axios.post('/admin/del/id',`userId=${id}`)
            .then(function(response){
                return response.data;
            })
            .then(function(obj){
                if(obj.success){
                    let pageNumber = dataTable.getPageNumber();
                    freshDataTable(dataTable,pageNumber);
                }else {
                    console.log(obj.message);
                }
            })
            .catch(function(e){
                console.log(e);
            })
    }*/
    //刷新数据表
    function freshDataTable(dataTable,pageNumber){
        axios.post('/admin/info/list/pages','pageNow=2&pageSize=10')
            .then(function(res){
                return res.data;
            })
            .then(function(obj){
                if(obj.success){
                    let data = obj.data;
                    dataTable.fillDataRow(data,pageNumber);
                }else {
                    console.log(obj.message);
                }
            })
            .catch(function(e){
                console.log(e);
            });
    }


});