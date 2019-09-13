;(function($,window,document){
    class MyPagination{
        constructor(options){
            this.pageSize = options.pageSize;
            this.oPaginator = $(`${options.selector}`);
            this.maxDisplayPages = options.maxDisplayPages || 5;
            // console.log(this.oPaginator);
            this.oPagination = this.oPaginator.children('.pagination');
            // console.log(this.oPagination);
            this.oPrev = this.oPagination.children('.prev');
            this.oNext = this.oPagination.children('.next');
            this.oFirst = this.oPagination.children('.first');
            this.oLast = this.oPagination.children('.last');
            this.oI = $('<i class="iconfont icon-more"></i>');
            this.curPageBtn = null;
            this.oPageBtns = [];
            this.initPagination();
        }
        //初始化分页器
        initPagination(){
            //1.清空页按钮
            this.oPagination.children('.page-btn').remove();
            //2.页数小于显示最大值
            if(this.pageSize <= this.maxDisplayPages){
                //创建页按钮
                for(let i = 0; i < this.pageSize; i++){
                    let oPageBtn = this._createPageBtn(i+1);
                    this.oNext.before(oPageBtn);
                    //设置isMore标记
                    oPageBtn.data('isMore',false);
                    this.oPageBtns.push(oPageBtn);
                }
            }else {
                //创建页按钮
                for(let i = 0; i < this.maxDisplayPages; i++){
                    let oPageBtn = this._createPageBtn(i+1);
                    this.oNext.before(oPageBtn);
                    //设置isMore标记
                    oPageBtn.data('isMore',false);
                    this.oPageBtns.push(oPageBtn);
                }
                //替换第5个按钮为更多
                let oBtn5 = this.oPageBtns[this.maxDisplayPages - 1];
                this._toggleMore(oBtn5,this.oI.clone());
            }
            //设置第一个按钮为当前页
            this.curPageBtn = this._setActivePage(this.oPageBtns[0]);

            //页码点击事件
            this.pageClick();
            //切换到下一页
            this.next();
            //切换到上一页
            this.prev();
            //回到首页
            this.toTop();
            //转向尾页
            this.toLast()
        }

        //页码点击事件
        pageClick(){
            for(let i = 0; i < this.oPageBtns.length; i++){
                let oPageBtn = this.oPageBtns[i];
                oPageBtn.click(()=>{
                    if(oPageBtn.data('isMore')) return;  //如果为more,直接返回
                    this.curPageBtn = this._setActivePage(oPageBtn); //设置当前页
                })
            }
        }
        //切换到下一页
        next(){
            //点击下一页切换
            this.oNext.click(()=>{
                //判断下一页是否为more
                let oNextPage = this.curPageBtn.next('.page-btn');
                if(oNextPage.length === 0) return; //如果下一项不是pageBtn直接返回
                let isMore = oNextPage.data('isMore');
                if(!isMore){
                    //设置当前选中页
                    this.curPageBtn = this._setActivePage(oNextPage);
                }else {
                    //设置oMore1图标
                    this._toggleMore(this.oPageBtns[0],this.oI.clone());
                    //改变页码
                    for(let i = 1; i < this.maxDisplayPages - 1; i++){
                        let oPage = this.oPageBtns[i];
                        this._pagecode(oPage,this._pagecode(oPage)+1);
                    }
                    //是否改变最后一项图标
                    let curpage = this._pagecode(this.curPageBtn);
                    if(curpage + 1 === this.pageSize){
                        this._toggleMore(this.oPageBtns[this.maxDisplayPages - 1],curpage + 1);
                    }
                }
            });
        }
        //切换到上一页
        prev(){
            //点击上一页切换
            this.oPrev.click(()=>{
                //判断上一页是否为more
                let oPrevPage = this.curPageBtn.prev('.page-btn');
                if(oPrevPage.length === 0) return;
                let isMore = oPrevPage.data('isMore');
                if(!isMore){
                    //设置当前选中页
                    this.curPageBtn = this._setActivePage(oPrevPage);
                }else {
                    //设置oMore2图标
                    this._toggleMore(this.oPageBtns[this.maxDisplayPages - 1],this.oI.clone());
                    //改变页码
                    for(let i = 1; i < this.maxDisplayPages - 1; i++){
                        let oPage = this.oPageBtns[i];
                        this._pagecode(oPage,this._pagecode(oPage)-1);
                    }
                    //是否改变第一项图标
                    let curpage = this._pagecode(this.curPageBtn);
                    if(curpage - 1 === 1){
                        this._toggleMore(this.oPageBtns[0],curpage - 1);
                    }
                }
            });
        }
        //回到首页
        toTop(){
            //点击转到第一页
            this.oFirst.click(()=>{
                //如果页数<=5
                if(this.pageSize <= this.maxDisplayPages){
                    this.curPageBtn = this._setActivePage(this.oPageBtns[0]);
                    return;
                }
                //1.修改页码
                //判断第1按钮是否为more，再设置数字
                let oBtn1 = this.oPageBtns[0];
                if(oBtn1.data('isMore')){
                    this._toggleMore(oBtn1,1);
                }
                //2-4按钮设置为数字
                for(let i = 1; i < this.maxDisplayPages - 1; i++){
                    let oBtn =  this.oPageBtns[i];
                    this._pagecode(oBtn,i+1);
                }
                //如果页数>5设置5按钮为more
                let oBtn5 = this.oPageBtns[this.maxDisplayPages - 1];
                if(this.pageSize > this.maxDisplayPages){
                    this._toggleMore(oBtn5,this.oI.clone());
                }
                //2.设置当前选中页
                this.curPageBtn = this._setActivePage(this.oPageBtns[0]);
            });
        }
        //转向尾页
        toLast(){
            //点击转到最后一页
            this.oLast.click(()=>{
                //如果页数<=5
                if(this.pageSize <= this.maxDisplayPages){
                    let btnSize = this.oPageBtns.length;
                    this.curPageBtn = this._setActivePage(this.oPageBtns[btnSize - 1]);
                    return;
                }
                //1.修改页码
                //判断第5按钮是否为more，再设置数字
                let oBtn5 = this.oPageBtns[this.maxDisplayPages - 1];
                if(oBtn5.data('isMore')){
                    this._toggleMore(oBtn5,this.pageSize);
                }
                //2-4按钮设置为数字
                for(let i = 1; i < this.maxDisplayPages - 1; i++){
                    let oBtn =  this.oPageBtns[i];
                    let diffPage = (this.maxDisplayPages - 2) - i + 1;
                    this._pagecode(oBtn,this.pageSize - diffPage);
                }
                //如果页数>5设置1按钮为more
                let oBtn1 = this.oPageBtns[0];
                if(this.pageSize > this.maxDisplayPages){
                    this._toggleMore(oBtn1,this.oI.clone());
                }
                //2.设置当前选中页
                this.curPageBtn = this._setActivePage(this.oPageBtns[this.maxDisplayPages - 1]);
            });
        }

        //创建页按钮
        _createPageBtn(pageNumber){
            let oPageBtn = $(`<li class="page-item page-btn"><a class="page-link" href="javascript:;">${pageNumber}</a></li>`);
            return oPageBtn;
        }
        //切换更多图标和页码
        _toggleMore(oPageBtn,content){
            //标记
            if(typeof content === 'number'){
                oPageBtn.data('isMore',false);
            }else {
                oPageBtn.data('isMore',true);
            }
            //替换内容
            oPageBtn.children('a').html(content);
        }
        //设置为当前选中页
        _setActivePage(oPageBtn){
            oPageBtn.addClass('active').siblings('.page-btn').removeClass('active');
            return oPageBtn;
        }
        //获取设置页码
        _pagecode(oPageBtn,number){
            if(arguments.length === 1){
                return parseInt(oPageBtn.children('a').text());
            }else{
                oPageBtn.children('a').text(number);
            }
        }
    }
    /*class Pagination{
        constructor(obj){
            obj = obj || {};
            this.dataUrl = obj.dataUrl;
            this.pageNumberUrl = obj.pageNumberUrl;
            this.oTable = obj.oTable;
            this.totalPage = 0;
            this.oPageMessage = obj.pageMessage || null;
            this.dataBind = obj.dataBind;
            this.oPageBtns = $('.pagination .page-btn');
            this.oCurPage = this.oPageBtns.eq(0);
            this.oNext = $('.pagination .next');
            this.oPrev = $('.pagination .prev');
            this.oFirst = $('.pagination .first');
            this.oLast = $('.pagination .last');
            this.oMore1 = this.oPageBtns.eq(0);
            this.oMore2 = this.oPageBtns.eq(4);
            this.oI = $('<i class="iconfont icon-more"></i>');
            this.data = [];
            this.totalRecording = 0;
            this.init();
        }
        init(){
            let initPagination = this.initPagination.bind(this);

            this.pageMessageRequest(initPagination);
        }
        //初始化分页器
        initPagination(data,callback){
            this.totalPage  = data.totalPages;
            //添加ismore标记
            for(let i = 0; i < this.oPageBtns.length; i++){
                let oPage = this.oPageBtns.eq(i);
                oPage.data("isMore", false);
            }
            //根据页面总数,确定是否添加...图标
            if(this.totalPage > 5)
                this._toggleMore(this.oMore2,this.oI.clone());
            //初始化事件
            this.pageClick();
            this.next();
            this.prev();
            this.toTop();
            this.toLast();
        }
        //页码点击事件
        pageClick(callback){
            //点击页码切换
            let that = this;
            this.oPageBtns.click(function(event){
                console.log(event.target.dataset.isMore);
                //判断是否为more
                let isMore = $(this).data('isMore');
                if(!isMore){
                    //设置当前选中页
                    that.oCurPage = that._setActivePage($(this));
                }
                //执行回调函数
                callback && callback();
            });
        }
        //切换到下一页
        next(callback){
            //点击下一页切换
            this.oNext.click(()=>{
                //判断下一页是否为more
                let oNextPage = this.oCurPage.next('.page-btn');
                if(oNextPage.length === 0) return;
                let isMore = oNextPage.data('isMore');
                if(!isMore){
                    //设置当前选中页
                    this.oCurPage = this._setActivePage(oNextPage);
                }else {
                    //设置oMore1图标
                    this._toggleMore(this.oMore1,this.oI.clone());
                    //改变页码
                    for(let i = 1; i < 4; i++){
                        let oPage = this.oPageBtns.eq(i);
                        this._pagecode(oPage,this._pagecode(oPage)+1);
                    }
                    //是否改变最后一项图标
                    let curpage = this._pagecode(this.oCurPage);
                    if(curpage + 1 === this.totalPage){
                        this._toggleMore(this.oMore2,curpage + 1);
                    }
                }
                //执行回调函数
                callback && callback();
            });
        }
        //切换到上一页
        prev(callback){
            //点击上一页切换
            this.oPrev.click(()=>{
                //判断上一页是否为more
                let oPrevPage = this.oCurPage.prev('.page-btn');
                if(oPrevPage.length === 0) return;
                let isMore = oPrevPage.data('isMore');
                if(!isMore){
                    //设置当前选中页
                    this.oCurPage = this._setActivePage(oPrevPage);
                }else {
                    //设置oMore2图标
                    this._toggleMore(this.oMore2,this.oI.clone());
                    //改变页码
                    for(let i = 1; i < 4; i++){
                        let oPage = this.oPageBtns.eq(i);
                        this._pagecode(oPage,this._pagecode(oPage)-1);
                    }
                    //是否改变最后一项图标
                    let curpage = this._pagecode(this.oCurPage);
                    if(curpage - 1 === 1){
                        this._toggleMore(this.oMore1,curpage - 1);
                    }
                }
                //执行回调函数
                callback && callback();
            });
        }
        //回到首页
        toTop(callback){
            //点击转到第一页
            this.oFirst.click(()=>{
                //1.修改页码
                //判断第1按钮是否为more，再设置数字
                let oBtn1 = this.oPageBtns.eq(0);
                if(oBtn1.data('isMore')){
                    this._toggleMore(oBtn1,1);
                }
                //2-4按钮设置为数字
                for(let i = 1; i <= 3; i++){
                    let oBtn =  this.oPageBtns.eq(i);
                    this._pagecode(oBtn,i+1);
                }
                //如果页数>5设置5按钮为more
                let oBtn5 = this.oPageBtns.eq(4);
                if(this.totalPage > 5){
                    this._toggleMore(oBtn5,this.oI.clone());
                }
                //2.设置当前选中页
                this.oCurPage = this._setActivePage(this.oPageBtns.eq(0));
                //执行回调函数
                callback && callback();
            });
        }
        //转向尾页
        toLast(callback){
            //点击转到最后一页
            this.oLast.click(()=>{
                //1.修改页码
                //判断第5按钮是否为more，再设置数字
                let oBtn5 = this.oPageBtns.eq(4);
                if(oBtn5.data('isMore')){
                    this._toggleMore(oBtn5,this.totalPage);
                }
                //2-4按钮设置为数字
                for(let i = 1; i <= 3; i++){
                    let oBtn =  this.oPageBtns.eq(i);
                    let diffPage = 3 - i + 1;
                    this._pagecode(oBtn,this.totalPage - diffPage);
                }
                //如果页数>5设置1按钮为more
                let oBtn1 = this.oPageBtns.eq(0);
                if(this.totalPage > 5){
                    this._toggleMore(oBtn1,this.oI.clone());
                }
                //2.设置当前选中页
                this.oCurPage = this._setActivePage(this.oPageBtns.eq(4));
                //执行回调函数
                callback && callback();
            });
        }

        //显示当前页信息
        _showPageMessage(pageNumber,pageDataSize,totalDataSize){
            //获取所需元素
            let oStart = this.oPageMessage.find('.order-start');
            let oEnd = this.oPageMessage.find('.order-end');
            let oTotal = this.oPageMessage.find('.total-number');
            //填充信息
            let start = (pageNumber - 1) * 10 + 1;
            let end = start + pageDataSize - 1;
            oStart.text(start);
            oEnd.text(end);
            oTotal.text(totalDataSize);
        }
        //设置为当前选中页
        _setActivePage(oPageBtn){
            oPageBtn.addClass('active').siblings('.page-btn').removeClass('active');
            return oPageBtn;
        }
        //获取设置页码
        _pagecode(oPageBtn,number){
            if(arguments.length === 1){
                return parseInt(oPageBtn.children('a').text());
            }else{
                oPageBtn.children('a').text(number);
            }
        }
        //切换更多图标和页码
        _toggleMore(oMore,content){
            //标记
            if(content instanceof Object){
                oMore.data('isMore',true);
            }
            else{
                oMore.data('isMore',false);
            }
            //替换内容
            oMore.children('a').html(content);
        }
        //清空表格数据
        _clearTable(){
            //获取所需元素
            let oContents = this.oTable.children('tbody').find('th,td').not('.operator');
            oContents.text('');
        }
        //显示操作项
        _showOperator(dataSize){
            //获取操作项
            let oOperators = this.oTable.find('.operator');
            //隐藏所有操作项
            oOperators.find('a').css('display','none');
            //显示所需项
            for(let i = 0; i < dataSize; i++){
                oOperators.eq(i).find('a').css('display','inline-block');
            }
        }
        //修改分页器
        /!*_modifyPagination(){
            if(this.totalPage > 5)
                this._toggleMore(this.oMore2,this.oI.clone());
        }*!/
        //发送页面数据请求
        pageRequest(){
            let pageNumber = this._pagecode(this.oCurPage)
            $.ajax(this.dataUrl,{
                method: 'post',
                data: {pageNow: pageNumber,pageSize: 10},
                dataType: 'text',
                success: (obj)=>{
                    obj = eval('('+obj+')');
                    console.log(obj);
                    if(obj.success){
                        //清空表格
                        this._clearTable();
                        //获取数据
                        let data = obj.data;
                        //显示操作项
                        let dataSize = data.length;
                        this._showOperator(dataSize);
                        //更新页面数据
                        this._showPageMessage(pageNumber,dataSize,this.totalRecoding);
                        //绑定数据
                        this.dataBind(data,this.oTable,pageNumber);
                    }else {
                        console.log(obj.message);
                    }
                },
                error: function(xhr){
                    console.log(xhr.status);
                }
            })
        }
        //发送页面信息(总页数，总数据量)请求
        pageMessageRequest(callback){
            $.ajax(this.pageNumberUrl,{
                method: 'get',
                dataType: 'json',
                success: (obj)=>{
                    if(obj.success){
                        let data = obj.data;
                        callback && callback(data);
                    }else {
                        console.log(obj.message);
                    }
                },
                error: function(xhr){
                    console.log(xhr.status);
                }
            });
        }
    }*/
    window.MyPagination = MyPagination;
})(jQuery,window,document);