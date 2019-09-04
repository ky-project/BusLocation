function Brick(oCanvas,ctx,ball,option){
    this.oCanvas = oCanvas;
    this.ctx = ctx;
    this.ball = ball;
    this.option = option || {};
    this.width = this.option.width || 50;
    this.lineWidth = this.option.lineWidth || 2;
    this.height = this.option.height || 15;
    this.color = this.option.color || 'white';
    this.x = this.option.x || 0;
    this.y = this.option.y || 0;

    this.maxX = this.x + this.width;
    this.maxY = this.y + this.height;

    var self = this;

    //渲染
    this.render = function(){
        self.ctx.fillStyle = self.color;
        self.ctx.rect(self.x,self.y,self.width,self.height);
        self.ctx.fillRect(self.x + 1,self.y + 1,self.width - 2,self.height - 2);
        self.ctx.stroke();
    };
    //碰撞检测
    this.ballCollision = function(isBound){
        var flag = false;
        if(ball.top <= self.maxY && ball.top >= self.maxY - self.height / 2 && self.ball.x <= self.maxX && self.ball.x >= self.x){
            //下面
            if(isBound){
                self.ball.y = self.maxY + self.ball.radius;
                self.ball.vy = -self.ball.vy;
            }
            flag = true;
        }else if(ball.bottom >= self.y && ball.bottom <= self.y + self.height / 2 && self.ball.x <= self.maxX && self.ball.x >= self.x){
            //上面
            if(isBound) {
                self.ball.y = self.y - self.ball.radius;
                self.ball.vy = -self.ball.vy;
            }
            flag = true;
        }else if(ball.right <= self.maxX && ball.right >= self.maxX - self.height / 2 && self.ball.y >= self.y && self.ball.y <= self.maxY){
            //右面
            if(isBound) {
                self.ball.x = self.maxX + self.ball.radius;
                self.ball.vx = -self.ball.vx;
            }
            flag = true;
        }else if(ball.left >= self.x && ball.left <= self.x + self.height / 2 && self.ball.y >= self.y && self.ball.y <= self.maxY){
            //左面
            if(isBound){
                self.ball.x = self.x - self.ball.radius;
                self.ball.vx = -self.ball.vx;
            }
            flag = true;
        }
        return flag;
    }
}