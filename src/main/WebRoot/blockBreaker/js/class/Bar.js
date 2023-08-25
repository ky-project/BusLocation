function Bar(oCanvas,ctx,option){
    this.oCanvas = oCanvas;
    this.ctx = ctx;
    this.option = option || {};
    this.x = this.option.x || 200;
    this.y = this.option.y || 460;
    this.color = this.option.color || 'white';
    this.width = this.option.width || 100;
    this.lineWidth = this.option.lineWidth || 2;
    this.vx = this.option.vx || 20;
    this.radius = this.option.radius || 5;

    var self = this;
    //渲染
    this.render = function(){
        self.ctx.beginPath();
        self.ctx.moveTo(self.x,self.y);
        self.ctx.lineTo(self.x + self.width,self.y);
        self.ctx.arc(self.x + self.width,self.y + self.radius,self.radius,-0.5*Math.PI,0.5*Math.PI,false);
        self.ctx.lineTo(self.x,self.y + self.radius * 2);
        self.ctx.arc(self.x,self.y + self.radius,self.radius,0.5*Math.PI,1.5*Math.PI,false);
        self.ctx.lineWidth = self.lineWidth;
        self.ctx.fillStyle = self.color;
        self.ctx.fill();
        self.ctx.stroke();
    };
    //监听左右按键按下事件
    this.moveListener = function(){
        document.addEventListener('keydown',function(event){
            event = event || window.event;
            var keycode =event.keyCode;
            if(keycode === 37 || keycode === 39){
                var max = self.oCanvas.offsetWidth - self.radius - self.width;
                //左右移动
                if(keycode === 37){
                    self.x -= self.vx;
                    //边界检测
                    self.x = self.x < self.radius ? self.radius : self.x;
                }else {
                    self.x += self.vx;
                    //边界检测
                    self.x = self.x > max ? max : self.x;
                }
            }
        },false);
    };
    //初始化
    this.init = function(){
        self.moveListener();
    }
}