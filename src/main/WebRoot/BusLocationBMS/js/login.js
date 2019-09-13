$(function(){
    initVerify();
    isVisible();
    isEmpty();
    submit();
    let verifyResult = new Object(); //获取验证结果
    axios.defaults.baseURL = 'http://172.20.10.2:8080/busposition'; //请求根路径
    //创建初始化验证码
    function initVerify(){
        //验证码配置
        $('#mpanel1').codeVerify({
            type : 1,
            width : '75px',
            height : '30px',
            btnId : 'submit',
            fontSize: '14px',
            codeLength: 4,
            success : function() {
                verifyResult.result = true;
            },
            error: function(){
                let oError = $('.error');
                oError.text('验证码错误');
                oError.css('visibility','visible');
                verifyResult.result = false;
            }
        });
        //修改验证码样式
        $('.verify-input-area').css({'width': '100%'});
        $('.verify-change-area').css({
            position: 'absolute',
            visibility: 'hidden',
        });

        $('.line:nth-child(3) input').attr('name','verify');
    }
    //切换密码显示模式
    function isVisible(){
        let oPsw = $('input[name="password"]');
        let oIcon = $('.line:nth-child(2) i');
        let isShow = false;
        oIcon.click(function(){
            if(isShow){
                $(this).removeClass('icon-ai44').addClass('icon-yincang');
                oPsw.attr('type','password');
                isShow = false;
            }else {
                $(this).addClass('icon-ai44').removeClass('icon-yincang');
                oPsw.attr('type','text');
                isShow = true;
            }
        });
    }
    //判断输入是否为空
    function isEmpty(){
        let flag = true;
        //0.获取所需元素
        let oLines = $('.line');
        let oInputs = $('input');
        let oPs = $('.line p');
        let tipMes = ['工号','密码','验证码']
        let errorMes = ['请输入工号','请输入密码','请输入验证码'];
        //1.设置索引
        for(let i = 0; i < oLines.length; i++){
            oLines.eq(i).data('index',i);
        }
        //2.监听焦点事件
        oInputs.focus(function(){
            let index = $(this).parents('.line').data('index');
            let value = $(this).val();
            oPs.eq(index).css({
                'display': 'block',
                'color': '#ccc'
            });
            if(value){
                oPs.eq(index).css('display','none');
            }
            oPs.eq(index).text(tipMes[index]);
        });
        //3.监听失去焦点事件
        oInputs.blur(function(){
            let value = $(this).val();
            let index = $(this).parents('.line').data('index');
            if(value === ''){
                oPs.eq(index).css({
                    'display': 'block',
                    'color': 'red'
                });
                oPs.eq(index).text(errorMes[index]);
            }
        });
        //4.监听表单输入事件
        for(let i = 0; i < oInputs.length; i++){
            oInputs.get(i).oninput = function(){
                let value = oInputs.eq(i).val();
                if(value)
                    oPs.eq(i).css('display','none');
                else{
                    oPs.eq(i).css('display','block');
                    oPs.eq(i).text(tipMes[i]);
                }
            }
        }
    }
    //监听登录按钮点击事件
    function submit(){
        let oSubmit = $('#submit');
        let count = 0;
        oSubmit.click(function(){
            //0.获取所需元素
            let oInputs = $('input');
            let oLines = $('.line');
            let oError = $('.error');
            let hasVerify = oLines.eq(2).css('display') !== 'none';
            oError.css('visibility','hidden');
            //1.判空(工号和密码)
            for(let i = 0; i < 2; i++){
                let oInput = oInputs.eq(i);
                if(oInput.val().trim() === ''){
                    console.log('存在空值');
                    return;
                }
            }
            //2.获取工号密码
            let workId = $('input[name="workId"]').val();
            let psw = $('input[name="password"]').val();
            console.log(workId, psw);
            //3.判断验证码是否正确
            if(hasVerify){
                let result = verifyResult.result;
                if(!result){
                    oError.css('visibility','visible');
                    console.log(typeof oInputs.eq(2).val());
                    oError.text('验证码错误');
                    return;
                }
            }
            //4.登录验证
            axios.post('/user/admin/login',`workId=${workId}&password=${psw}`)
                .then(function (res) {
                    return res.data; //获取响应数据
                })
                .then(function(obj){
                    console.log(obj);
                    if(obj.success){
                        window.location.href ='myDataTable.html';
                    }else {
                        count++;
                        oError.text('工号或密码错误');
                        oError.css('visibility','visible');
                        //5.错误三次显示验证码
                        if(count === 3){
                            oLines.eq(2).stop().show(500);
                        }
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });
        })

    }
});