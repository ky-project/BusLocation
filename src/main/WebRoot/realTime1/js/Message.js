function Message(test) {
        this.test = test;
        this.oMessageBox = document.createElement('div');
        this.oMessageBox.className = 'message-box';
        this.oMessageBox.innerText = test;

        this.render = function(){
            this.css(this.oMessageBox,{
                'height': '30px',
                'line-height': '30px',
                'padding': '0 10px',
                'border-radius': '3px',
                'background-color': '#000',
                'color': '#fff',
                'font-size': '14px',
                'display': 'inline-block',
                'position': 'absolute',
                'left': '50%',
                'bottom': '-30px',
                'transform': 'translateX(-50%)',
                'opacity':'0.5'
            });
            document.body.appendChild(this.oMessageBox);
        };
        this.pop = function(){
            $(this.oMessageBox).animate({'bottom':'50px'},'fast','swing')
                .delay(2000).fadeOut('fast','swing',this.bomb);
        };
        this.bomb = function(){
            document.body.removeChild(document.querySelector('.message-box'));
        };
        this.css = function(ele,options){
            for(var key in options){
                ele.style[key] = options[key];
            }
        }
}
