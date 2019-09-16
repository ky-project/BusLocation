[TOC]

# 登录登出相关API(v1.0)(均以Json数据交互)

# + 获取后台生成的验证码

例】${url}/code/getVerifyCode

### - 接口

GET code/getVerifyCode

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   无   |  无  |  无  |   无   | 无参数 |

### - 页面调用

```html
<img src="/busposition/code/getVerifyCode"/>
```

## + 登录验证

【例】${url}/login

【注】调用该接口前一定要先调用获取验证码api

### - 接口

POST login

### - 请求参数

|   参数名   |  类型  | 必需 | 默认值 |            备注            |
| :--------: | :----: | :--: | :----: | :------------------------: |
|   workId   | String |  Y   |        |          职工编号          |
|  password  | String |  Y   |        |          登录密码          |
| verifyCode | String |  Y   |        |           验证码           |
| loginType  | String |  Y   |        | manager/simple只有两种类型 |

### - Json请求

```json
{
	"workId":"Xb16620208",
	"password":"123",
	"verifyCode":"39496",
	"loginType":"manager"
}
```

### - Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: {
        "departmentName": "开发小组",	//所在部门
        "realName": "陆宇豪",			//真实姓名
        "phone": "17758178533",			//手机号码
        "idCard": "331082177422238849",	//身份证
        "sysUserId": 1,					//用户id
        "workId": "Xb16620208",			//工号
        "email": "ludaye1112@163.com"	//邮箱
    }
}
```

## + 账号注销

【例】${url}/logout

【注】无任何返回，访问即注销

### - 接口

GET logout

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  N   |        |  无  |

### - 无响应



