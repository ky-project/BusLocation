
$(function(){
    let $menu = $(".menu");
    let $mask = $(".mask");
    let $phoneNavbar = $(".phone-navbar");

    $menu.click(function(){
        showMenu();
    });

    $mask.click(function () {
        hiddenMenu();
    });
//显示手机菜单
//参数：菜单按钮，遮罩层，菜单栏
    function showMenu(){
        $menu.click(function() {
            $mask.fadeIn();
            $phoneNavbar.animate({left: 0},500);
        })
    }
//隐藏手机菜单
//参数：菜单按钮，遮罩层，菜单栏
    function hiddenMenu(){
        $mask.click(function(){
            $phoneNavbar.animate({left: "-40%"},500);
            $(this).fadeOut();
        })
    }
})



