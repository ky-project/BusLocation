[TOC]

# 普通用户API(v1.0)

## 1. 登录验证

【例】${url}/user/login

### 1.1 接口
POST user/login

### 1.2 请求参数

|参数名|类型|必需|默认值|备注|
|:--:|:--:|:--:|:--:|:--:|
|userName|String|Y||用户账号/职工编号|
|password|String|Y||登录密码|

### 1.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: {
        departmentName: "开发小组",	//所属部门
        realName: "admin",	//真实姓名
        sysUserId: 1	//用户id
	}
}
```
