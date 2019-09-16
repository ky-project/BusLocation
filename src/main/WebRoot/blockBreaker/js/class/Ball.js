function Ball(oCanvas, ctx, bar, option) {
    this.oCanvas = oCanvas;
    this.ctx = ctx;
    this.bar = bar;
    this.option = option || {};
    this.x = this.option.x || 250;
    this.y = this.option.y || 450;
    this.radius = this.option.radius || 10;
    this.color = this.option.color || 'white';
    this.lineWidth = this.option.lineWidth || 2;
    this.vy = this.option.vx || 0;
    this.vx = this.option.vy || 0;
    // this.isLaunch = false;

    this.top = this.y - this.radius;
    this.bottom = this.y + this.radius;
    this.left = this.x - this.radius;
    this.right = this.x + this.radius;

    var self = this;
    //渲染
    this.render = function () {
        self.ctx.beginPath();
        self.ctx.arc(self.x, self.y, self.radius, 0, (Math.PI * 2), true);
        self.ctx.closePath();
        self.ctx.lineWidth = self.lineWidth;
        self.ctx.fillStyle = self.color;
        self.ctx.fill();
        self.ctx.stroke();
    };
    //监听键盘左右按下事件
    this.moveListener = function () {
        document.addEventListener('keydown', self.move, false);
    };
    this.move = function (event) {
        event = event || window.event;
        var keycode = event.keyCode;
        var minX = self.bar.width / 2 + self.bar.radius;
        var maxX = oCanvas.offsetWidth - self.bar.radius - self.bar.width / 2;
        if (keycode === 37 || keycode === 39) {
            //左右移动
            if (keycode === 37) {
                self.x -= self.bar.vx;
                self.x = self.x < minX ? minX : self.x;
            } else {
                self.x += self.bar.vx;
                self.x = self.x > maxX ? maxX : self.x;
            }
        }
    };
    //监听键盘↑↓按下事件
    this.launchListener = function () {
        document.addEventListener('keydown', function(event){
            // if (!self.isLaunch) {
                event = event || window.event;
                if (event.keyCode === 38) {
                    console.log('上');
                    //设置速度
                    self.vy -= 3;
                    self.vx += 3;
                    //修改发射状态
                    // self.launch = true;
                    //移除事件
                    document.removeEventListener('keydown', self.move);
                }else if(event.keyCode === 40){
                    console.log('下');
                    //设置速度
                    self.vy += 3;
                    self.vx -= 3;
                    //移除事件
                    document.removeEventListener('keydown', self.move);
                }
            // }
        }, false)
    };
    //改变位置
    this.changePos = function () {
        self.x += self.vx;
        self.y += self.vy;
        self.top = self.y - self.radius;
        self.bottom = self.y + self.radius;
        self.left = self.x - self.radius;
        self.right = self.x + self.radius;
    };
    //边界检测
    this.boundaryCollisionListeners = function () {
        var flag = false;
        if (self.right >= self.oCanvas.offsetWidth) {
            //撞到右边界
            self.x = self.oCanvas.offsetWidth - self.radius;
            self.vx = -self.vx;
        } else if (self.left <= 0) {
            //撞到左边界
            self.x = self.radius;
            self.vx = -self.vx;
        } else if (self.top <= 0) {
            //撞到上边界
            self.y = self.radius;
            self.vy = -self.vy;
        } else if (self.y >= self.oCanvas.offsetHeight + self.radius) {
            //超出下边界
            flag = true;
        }
        return flag;
    };
    //滑棒碰撞检测
    this.barCollisionListener = function () {
        var barTop = self.bar.y;
        var barBottom = self.bar.y + self.bar.radius * 2;
        var barLeft = self.bar.x - self.bar.radius;
        var barRight = self.bar.x + self.bar.radius + self.bar.width;
        if (self.bottom >= barTop && self.x >= barLeft && self.x <= barRight) {
            //上面碰撞
            self.y = barTop - self.radius;
            self.vy = -self.vy;
        }else if(self.right >= barLeft && self.right <= barLeft + 10 && self.y <= barBottom && self.y >=barTop){
            self.x = self.bar.x - self.bar.radius - self.radius;
            self.vx = self.vx > 0 ? -self.vx : self.vx;
        }else if(self.left <= barRight && self.left >= barRight - 10 && self.y <= barBottom && self.y >=barTop){
            self.x = self.bar.x + self.bar.width + self.bar.radius + self.radius;
            self.vx = self.vx < 0 ? -self.vx : self.vx;
        }
    };
    //初始化
    this.init = function () {
        self.moveListener();
        self.launchListener();
    }
}

