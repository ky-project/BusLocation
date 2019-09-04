window.onload = function(){
    //0.获取所需元素
    var $psw = $('#password');
    var $user = $('#workId');
    var $loginBtn = $('input[type="submit"]');
    var $inputs = $('input').not('input[type="submit"]');
    var $form = $('form');
    var $message = $('.message');
    //1.监听焦点事件
    $inputs.focus(function(){
        $(this).parent('.line').addClass('focus');
        $(this).parent('.line').siblings('.line').removeClass('focus');
    });
    //2.阻止表单默认提交事件
    $form.submit(function(event){
        return false;
    });
    //3.监听输入事件
    var flag = false;
    for(var i = 0; i < $inputs.length; i++){
        var oInput = $inputs[i];
        oInput.oninput = debounce(function(){
            //如果用户名或密码为空
            if(!$psw.val().length || !$user.val().length){
                $loginBtn.css({'backgroundColor': '#99CCFF'});
                flag = false;
            }else{
                $loginBtn.css({'backgroundColor': '#51acfe'});
                flag = true;
            }
        },300)
    }
    //4.监听提交按钮点击事件
    $loginBtn.click(function(event){
        event.preventDefault();
        if(flag){
            var workId = $user.val();
            var password = $psw.val();
            $.ajax('/busposition/user/login',{
                type: 'post',
                data: {
                    'workId':workId,
                    'password':password
                },
                success: function(data){
                    if(data.success === true){
                        //保存账号，密码
                        $.addCookie({
                            key: 'workId',
                            value: workId,
                            path: '/',
                            day: 7
                        });
                        $.addCookie({
                            key: 'password',
                            value: password,
                            path: '/',
                            day: 7
                        });
                        //页面跳转
                        location.href = './realTimeRoute.html';
                    }else {
                        var message = new Message(data.message);
                        message.render();
                        message.pop();
                    }
                },
                error: function(xhr){
                    new Message(xhr.status);
                }
            })
        }
    });
    //5.自动输入
    autoInput($user,$psw);

    //自动输入
    function autoInput($user,$psw){
        var user = $.getCookie('workId','/');
        var psw = $.getCookie('password','/');
        if(user && psw){
            $user.val(user);
            $psw.val(psw);
            $loginBtn.css({'backgroundColor': '#51acfe'});
            flag = true;
        }
    }

};