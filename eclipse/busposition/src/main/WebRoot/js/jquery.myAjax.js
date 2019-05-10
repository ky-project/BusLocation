;(function($,window){
    $.extend({
        myAjax: function(option){
                        //解析参数
                        let res = obj2str(option.data);
                        //创建对象
                        let xhr;
                        if(XMLHttpRequest){
                            xhr = new XMLHttpRequest();
                        } else {
                            xhr = new ActiveXObject("Microsoft.XMLHTTP");
                        }
                        //url地址和传输方式
                        if(option.type.toLowerCase() === 'get'){
                            xhr.open(option.type,option.url+'?'+res,true);
                            //发送请求
                            xhr.send();
                        } else {
                            xhr.open(option.type,option.url,true);
                            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
                            xhr.send(res);
                        }

                        //监听请求状态
                        xhr.onreadystatechange = function(ev2){
                            //判断连接请求是否完成
                            if(xhr.readyState === 4){
                                //判断状态码
                                if(xhr.status >= 200 && xhr.status < 300 || xhr.status === 304){
                                    option.success(xhr);
                                } else {
                                    option.error(xhr);
                                }
                            }
                        };
                        function obj2str(data){
                            /*
                            {
                                "userName":"lnj",
                                "userPwd":"123456",
                                "t":"3712i9471329876498132"
                            }
                            */
                            data = data || {};
                            data.t = new Date().getTime();
                            let arr = [];
                            for(let key in data){
                                arr.push(encodeURIComponent(key) + "=" + encodeURIComponent(data[key]));  //['name=zs','age=ls']
                            }
                            return arr.join('&');
                        }
        }
    })
})(jQuery,window);