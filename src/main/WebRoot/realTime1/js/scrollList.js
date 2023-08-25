//名称：滚动列表
/*html格式：
*<div class="scroll-box">
*    <ul class="scroll-list">
*    <li class="cur">路线一</li>
*    <li>路线二</li>
*    <li>路线三</li>
*    </ul>
*</div>
*/
/*前提
*js前提：
*1.jquery-1.12.4.js
*2.tools.js
*css前提：
*1.设置scrollList为绝对定位
*2.给scrollItem设置高度
*/
/*作用：
*1.根据滚动项数目设置scrollList和scrollBox大小
*2.实现鼠标拖动移动scrollList效果，类似于滚动条
*3.点击遮罩或任意滚动项关闭遮罩和滚动窗口
*/
(function(window){
    var isPc = IsPC();
    //0.获取所需元素
    var $scrollMask = $('.scroll-mask');
    var $scrollBox = $('.scroll-box');
    var $scrollList = $('.scroll-list');
    var $scrollItems = $('.scroll-list li');
    var $scrollTrigger = $('.scroll-trigger');

    //1.点击显示遮罩和路线选择列表
    $scrollTrigger.click(function(){
        $scrollMask.stop().fadeIn(200,function(){
            $scrollBox.stop().slideDown(200);
        });
    });
    //2.获取滚动项数目
    var itemNum = $scrollItems.length;
    //3.获取滚动项高度
    var itemHeight = $scrollItems.eq(0).outerHeight();
    //4.设置滚动列表和滚动盒子高度
    $scrollList.css({'height': itemNum * itemHeight});
    if(itemNum > 4){
        $scrollBox.css({'height':4*itemHeight + Math.floor(itemHeight/2)});
    } else {
        $scrollBox.css({'height': itemNum * itemHeight});
    }

    //5.添加拖动效果
    if(!isPc)
        addPhoneDrag($scrollList);
    else
        addPcDrag($scrollList);

    //6.监听路线选择列表点击事件
    $scrollItems.click(function(event){
        console.log(this.nodeName);
        //4.1关闭遮罩和路线选择列表
        $scrollBox.stop().slideUp(250,function(){
            $scrollMask.stop().fadeOut(250);
        });
        event.stopPropagation();
    });

    //7.监听遮罩点击事件
    $scrollMask.click(function(){
        //5.1关闭遮罩和路线选择列表
        $scrollBox.stop().slideUp(200,function(){
            $scrollMask.stop().fadeOut(200);
        });
    });

    //判断是否是电脑端
    function IsPC() {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone",
            "SymbianOS", "Windows Phone",
            "iPad", "iPod"];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    //添加手机拖动事件
    function addPhoneDrag($scrollList){

        var topMin = $scrollBox.innerHeight() -  $scrollList.innerHeight();
        var curIndex = 0;
        var oldY;
        var oldTop;
        var isDrag = false;
        var oScrollList = $scrollList.get(0);

        oScrollList.addEventListener('touchstart',function(touchEvent){
            //获取当前触摸对象
            var touch = touchEvent.changedTouches.item(0);
            oldY = touch.clientY;
            oldTop = parseInt($(this).css('top'));
            isDrag = true;
            var $curItem = $(touch.target);
            //设置鼠标按下选项样式
            $scrollItems.eq(curIndex).removeClass('cur');
            $curItem.addClass('cur');
            curIndex = $scrollItems.index($curItem);
        },false);
        oScrollList.addEventListener('touchmove',function(touchEvent){
            if(isDrag){
                //获取当前触摸对象
                var touch = touchEvent.changedTouches.item(0);
                //设置ul位置
                var newY = touch.clientY;
                var newTop = newY - oldY + oldTop;
                newTop =  newTop <= 0 ? newTop : 0;
                newTop =  newTop >= topMin ? newTop : topMin;
                $(this).css({'top':newTop});
                touchEvent.stopPropagation();
                touchEvent.preventDefault();
            }
        },false);
        document.addEventListener('touchend',function(touchEvent){
            isDrag = false;
        });

    }

    //添加电脑拖动事件
    function addPcDrag($scrollList){
        var topMin = $scrollBox.innerHeight() -  $scrollList.innerHeight();
        var oldY;
        var newY;
        var oldTop;
        var isDrag = false;
        var oScrollList = $scrollList.get(0);

        oScrollList.addEventListener('mousedown',function(event){
            event = event || window.event;
            oldY = event.clientY;
            oldTop = parseInt($(this).css('top'));
            isDrag = true;
            event.stopPropagation();
        },true);
        oScrollList.addEventListener('mousemove',function(event){
            if(isDrag){
                event = event || window.event;
                var newY = event.clientY;
                var newTop = newY - oldY + oldTop;
                newTop =  newTop <= 0 ? newTop : 0;
                newTop =  newTop >= topMin ? newTop : topMin;
                $(this).css({'top':newTop});
                event.stopPropagation();
            }
        },true);
        oScrollList.addEventListener('mouseup',function(event){
            event = event || window.event;
            newY = event.clientY;
            isDrag = false;
            event.stopPropagation();
        },true);
        oScrollList.addEventListener('click',function(event){
            event = event || window.event;
            if(newY !== oldY)
                event.stopPropagation();
        },true);

    }
})(window);

