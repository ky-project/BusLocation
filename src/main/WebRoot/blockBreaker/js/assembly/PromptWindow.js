function PromptWindow(option) {
        this.$ele = option.$ele;
        this.text = option.text;
        this.deviation = option.deviation;
        this.color = option.color || 'green';
        this.$messageBox = $(`<div class="message-box">${this.text}</div>`);
        var self = this;

        this.render = function(){
            self.$messageBox.css({
                'height': '30px',
                'line-height': '30px',
                'padding': '0 10px',
                'color': self.color,
                'font-size': '16px',
                'text-align': 'center',
                'display': 'inline-block',
                'position': 'absolute',
                'left': `50%`,
                'bottom': '100px',
                'transform':`translateX(${self.deviation}px)`,
                'opacity':'0.5',
                'z-index':'10000'
            });
            self.$ele.append(self.$messageBox);
        };
        this.pop = function(){
            self.$messageBox.animate({'bottom':'120px'},'normal','linear');
            self.$messageBox.fadeOut('normal','swing',self.bomb);
        };
        this.bomb = function(){
            self.$messageBox.remove();
        };
        //初始化
        this.init = function(){
            self.render();
            self.pop(self.bomb);
        }
}
