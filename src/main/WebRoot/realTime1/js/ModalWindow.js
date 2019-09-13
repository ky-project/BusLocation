function ModalWindow($targetEle){
    //1.创建遮罩
    this.$targetEle = $targetEle;
    this.$mask = $('<div></div>');
    this.$modalBox = $('<div></div>');
    this.$modalTitle = $('<div></div>');
    this.$modalContent = $('<div></div>');
    this.$modalFooter = $('<div></div>');


    //渲染
    this.render = function(){
        //添加遮罩
        this.$mask.css({
            'width': '100%',
            'height': '100%',
            'position':'fixed',
            'left':0,
            'top':0,
            'background-color':'#000',
            'opacity':0.5,
            'z-index':999
        });
        this.$mask.addClass('modal-mask');
        this.$targetEle.append(this.$mask);
        //添加模态窗
        this.$modalBox.css({
            'width':'80%',
            'position':'absolute',
            'left':'50%',
            'top':'50%',
            'translate':'transform(-50%,-50%)',
            'border-radius':'5px',
            'border':'1px solid #fff',
            'background-color':'#fff'
        });
        this.$modalBox.addClass('modal-box');
        this.$targetEle.append(this.$modalBox);
        //添加模态窗标题
        this.$modalTitle.css({
            'width':'100%',
            'height':'20px',
            'line-height':'20px',
            'text-align':'center',
            'font-weight':'bold',
            'font-size':'16px',
            'border-bottom':'1px solid #ccc',
        });
        this.$modalTitle.addClass('modal-title');
        this.$modalBox.append(this.$modalTitle);
        //添加模态窗内容
        this.$modalContent.css({
            'padding':'10px 15px',
        });
        this.$modalContent.addClass('modal-content');
        this.$modalBox.append(this.$modalContent);
        //添加模态窗底部
        this.$modalFooter.css({
            'width':'100%',
            'height':'30px',
            'border-top':'1px solid #ccc'
        });
        this.$modalContent.addClass('modal-content');
        this.$modalBox.append(this.$modalContent);
    }
}