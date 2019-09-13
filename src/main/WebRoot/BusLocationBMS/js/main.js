$(function(){
    //获取所需元素
    let oArrow = $('.main-left>b');
    let oListGroup = $('.main-left .list-group');
    let oMenuItems = $('.main-left .list-group-item');
    let oMenuIcon = $('.main-left .list-group-item i');
    //监听菜单图标点击事件
    oMenuIcon.click(function(){
        // oListGroup.hide(500);
        oListGroup.stop().animate({left: -50},500,function(){
            oArrow.stop().show(300);
        });
    });
    //监听右箭头鼠标点击事件
    oArrow.click(function(){
        oArrow.stop().hide(300,function(){
            oListGroup.stop().animate({left: 0},500);
        });
    })
});