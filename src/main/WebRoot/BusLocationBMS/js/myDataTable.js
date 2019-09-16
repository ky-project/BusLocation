;(function($,window,document){
    class MyDataTable{
        constructor(options){
            this.oDataTable = $(options.selector); //数据表
            this.titles = options.titles || {}; //标题
            this.rowSize = options.rowSize || 10; //行数
            this.oThead = this.oDataTable.children('thead');
            this.oTbody = this.oDataTable.children('tbody');
            this.showOperator = options.showOperator || false; //是否显示操作栏
            this.showOrder = options.showOrder || false;  //是否显示序号
            this.dataNames = Object.keys(this.titles); //数据名称数组(依照这个填充数据)
            this.dataCols = this.dataNames.length; //数据列的数量
            this.pageNumber = 0; //当前页号
            // this.delete = options.delete || new Function();
            this.initDataTable();
        }
        //获取id
        getId(oTr){
            return oTr.data('id');
        }
        //获取页号
        getPageNumber(){
            return this.pageNumber;
        }
        //初始化表格
        initDataTable(){
            //清空表格
            this.oThead.find('tr').remove();
            this.oTbody.find('tr').remove();
            //创建标题行
            let oTitleRow = this._createTitle();
            this.oThead.append(oTitleRow);
            //创建数据行
            for(let i = 0; i < this.rowSize; i++){
                let oDataRow = this._createDataRow();
                this.oTbody.append(oDataRow);
            }
            //填充标题行
            this._fillTitleRow();
        }
        //创建标题行
        _createTitle(){
            let cols = this.dataCols;
            //创建标题行
            let oTr = $('<tr class="dt-title"></tr>');
            for(let i = 0; i < cols; i++){
                let oTh = $('<th class="title-fieldName"></th>');
                oTr.append(oTh);
            }
            //判断是否显示序号或操作
            if(this.showOrder){
                let oTh = $('<th class="title-order"></th>');
                oTr.prepend(oTh);
            }
            if(this.showOperator){
                let oTh = $('<th class="title-operator"></th>');
                oTr.append(oTh);
            }
            return oTr;
        }
        //创建数据行
        _createDataRow(){
            let cols = this.dataCols;
            let oTr = $('<tr class="dt-item"></tr>');
            for(let i = 0; i < cols; i++){
                let oTd = $('<td class="item-data"></td>');
                oTr.append(oTd);
            }
            if(this.showOperator){
                let oTd = $(`<td class="item-operator">
                                        <a href="#" class="delete" style="display:none;"><i class="iconfont icon-delete"></i></a>
                                        <a href="#" class="modify" style="display:none;"><i class="iconfont icon-xiugai"></i></a>
                                    </td>`);
                oTr.append(oTd);
            }
            if(this.showOrder){
                let oTh = $('<th class="item-order"></th>');
                oTr.prepend(oTh);
            }
            return oTr;
        }
        //填充标题行
        _fillTitleRow(){
            //获取所需元素
            let oTitleOrder = this.oThead.find('.title-order');
            console.log(oTitleOrder);
            let oTitleOperator = this.oThead.find('.title-operator');
            let oFieldNames = this.oThead.find('.title-fieldName');

            //判断是否显示序号或操作
            if(this.showOperator){
                oTitleOrder.text('#');
            }
            if(this.showOperator){
                oTitleOperator.text('操作');
            }
            //填充数据项
            for(let i = 0; i < oFieldNames.length; i++){
                let oFieldName = oFieldNames.eq(i);
                let dataName = this.dataNames[i];
                oFieldName.text(this.titles[dataName]);
            }
        }
        //填充数据行
        fillDataRow(data,pageNumber){
            //保存当前页
            this.pageNumber = pageNumber;
            //获取所需元素
            let oDataRows = this.oTbody.find('tr');
            let dataRows = data.length;
            let startOrder = (pageNumber - 1 ) * this.rowSize + 1;
            for(let i = 0; i < this.rowSize; i++){
                let oDataRow = oDataRows.eq(i);
                if(i >= 0 && i <= dataRows - 1){
                    //填充序号
                    if(this.showOrder){
                        oDataRow.find('.item-order').text(startOrder++);
                    }
                    //填充操作
                    if(this.showOperator){
                        oDataRow.find('.item-operator a').css('display','inline');
                    }
                    //填充数据
                    let oItemDatas = oDataRow.find('.item-data');
                    //设置数据
                    let curData = data[i];
                    for(let j = 0; j < oItemDatas.length; j++){
                        let oItemData = oItemDatas.eq(j);
                        let curName = this.dataNames[j];
                        oItemData.text(curData.content[curName]);
                    }
                    //设置id
                    oDataRow.data('id',curData.id);
                }else{
                    oDataRow.children().not('.item-operator').text('');
                    oDataRow.children('.item-operator a').css('display','none');
                }
            }
        }
    }
    window.MyDataTable = MyDataTable;
})(jQuery,window,document);