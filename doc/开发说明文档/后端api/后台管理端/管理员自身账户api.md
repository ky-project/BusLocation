[TOC]

# 修改记录

# 管理员自身账户API(v1.0)(均以Json数据交互)

## + 修改自身密码

【例】${url}/m/self/modify/pwd

### - 接口

POST /m/self/modify/pwd

### - 请求参数

|   参数名    |  类型  | 必需 | 默认值 |  备注  |
| :---------: | :----: | :--: | :----: | :----: |
| oldPassword | String |  Y   |        | 旧密码 |
| newPassword | String |  Y   |        | 新密码 |

### - Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: null		//无返回内容
}
```

## + 更新自身信息

【例】${url}/m/self/update/info

### - 接口

POST /m/self/update/info

### - 请求参数

|  参数名  |  类型  | 必需 | 默认值 |   备注   |
| :------: | :----: | :--: | :----: | :------: |
| realName | String |  N   |        | 真实姓名 |
|  idCard  | String |  N   |        |  身份证  |
|  phone   | String |  N   |        |  手机号  |
|  email   | String |  N   |        |   邮箱   |

### - Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: null		//无返回内容
}
```

