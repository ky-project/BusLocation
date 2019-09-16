window.onload = function () {
    //0.获取所需元素
    var $departmentName = $('.department-name');
    var $realName = $('.real-name');
    var $phone = $('.phone');
    var $idCode = $('.id-code');
    var $workId = $('.work-id');
    var $email = $('.email');
    var $modalMask = $('.modal-mask');
    var $modalBox = $('.modal-box');
    var $modalTitle = $('.modal-title');
    var $modalContent = $('.modal-content');
    var $modalFooter = $('.modal-footer');
    var $btnCancel = $('.btn-cancel');
    var $btnOk = $('.btn-ok');
    var $infoItems = $('.info-list li').not('.department-name').not('.work-id');
    // console.log($infoItems);

    var userInfo = null;
    //1.获取数据
    $.ajax('/busposition/user/self/info', {
        type: 'get',
        success: function (data) {
            // console.log(data);
            if (data.success === true) {
                userInfo = data.data[0];
                //2.填充用户数据
                fillData();
            } else {
                var message = new Message(data.message);
                message.render();
                message.pop();
            }
        },
        error: function (xhr) {
            new Message('请求信息发送失败');
        }
    });
    //3.模态窗相关
    //3.1监听信息列表点击事件
    $infoItems.click(function () {
        openModalWindow($modalMask, $modalBox);
        //3.2初始化模态窗内容
        var text = $(this).children('h3').text();
        initModalWindow(text);
        //3.3初始化确定按钮点击事件
        var key = toHumpString(this.className);
        initBtnOk($btnOk, $modalContent, key);
    });
    //3.4监听遮罩点击事件
    $modalMask.click(function (event) {
        closeModalWindow($modalMask, $modalBox);
    });
    //3.5监听取消按钮点击事件
    $btnCancel.click(function () {
        closeModalWindow($modalMask, $modalBox);
    });
    //3.6设置模态窗按钮按下样式
    setBtnStyle($modalFooter.children('button'));

    //初始化确认按钮点击事件
    function initBtnOk($btnOk, $modalContent, key) {
        var $input = $modalContent.children('input').eq(0);
        $btnOk.click(function () {
            //输入检测
            var value = $input.val();
            if (value === '') {
                $input.css({
                    'border-color': '#5bc0de',
                });
                $input.focus();
                $input.attr('placeholder','输入为空!');
                return;
            }
            //发送请求
            var data = {};
            data[key] = value;
            console.log(data);
            $.ajax('/busposition/user/update/info', {
                type: 'post',
                data: JSON.stringify(data),
                contentType:'application/json; charset=utf-8',
                success: function (data) {
                    if (data.success === true) {
                        var message = new Message(data.message);
                        message.render();
                        message.pop();
                        //重新加载账号资料页面

                    } else {
                        var message = new Message(data.message);
                        message.render();
                        message.pop();
                    }
                },
                error: function (xhr) {
                    var message = new Message('请求发送失败');
                    message.render();
                    message.pop();
                }
            });
            closeModalWindow($modalMask, $modalBox)
        });
    }

    //字符串转驼峰形式
    function toHumpString(string) {
        var arr = string.split('-');
        for (var i = 1; i < arr.length; i++) {
            var curStr = arr[i];
            arr[i] = curStr.replace(curStr[0], curStr[0].toUpperCase());
        }
        return arr.join('');
    }

    //填充用户数据
    function fillData() {
        $departmentName.children('p').text(userInfo.departmentName);
        $realName.children('p').text(userInfo.realName);
        $phone.children('p').text(userInfo.phone);
        $idCode.children('p').text(userInfo.idCode);
        $workId.children('p').text(userInfo.workId);
        $email.children('p').text(userInfo.email);
    }

    //打开模态窗
    function openModalWindow($modalMask, $modalBox) {
        $modalMask.stop().fadeIn(300);
        $modalBox.stop().fadeIn(300);
    }

    //关闭模态窗
    function closeModalWindow($modalMask, $modalBox) {
        $modalMask.stop().fadeOut(300);
        $modalBox.stop().fadeOut(300);
    }

    //设置模态窗按下抬起样式
    function setBtnStyle(oBtns) {
        for (var i = 0; i < oBtns.length; i++) {
            oBtns[i].addEventListener('touchstart', function () {
                // alert('被点击了');
                $(this).css({
                    'background-color': '#5bc0de',
                    'color': '#fff'
                })
            }, false);
            oBtns[i].addEventListener('touchend', function () {
                // alert('被点击了');
                $(this).css({
                    'background-color': '#fff',
                    'color': '#5bc0de'
                })
            }, false);
        }
    }

    //初始化模态窗内容
    function initModalWindow(text) {
        $modalTitle.text(text);
        $modalContent.children('input').attr('placeholder', '请输入要修改的' + text);
    }


};