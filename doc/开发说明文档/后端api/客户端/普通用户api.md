[TOC]

# 修改记录

1. 2019-04-17
- (API-1)修改"登录验证"的参数：userName->workId
   - (API-2)"登录验证"的Json响应返回data添加：workId(职工编号)
2. 2019-05-19
	- 更新各个接口的请求参数格式（Json格式或非Json格式）

# 普通用户API(v1.0)

## 1. 用户登录验证

【例】${url}/user/login

### 1.1 接口
POST user/login

### 1.2 请求参数（非Json格式）

|参数名|类型|必需|默认值|备注|
|:--:|:--:|:--:|:--:|:--:|
|workId|String|Y||职工编号|
|password|String|Y||登录密码|

### 1.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: {
        departmentName: "开发小组",	//所属部门
        realName: "管理员",	//真实姓名
        sysUserId: 4,	//用户id
        workId: "admin"	//教工号
    }
}
```

## 2. 用户获取自己的基本信息（需登录）

**【注】必须登录才可使用**

【例】${url}/user/self/info

### 2.1 接口

GET user/self/info

### 2.2 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   无   |  无  |  N   |        | 无参数 |

### 2.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [
        {
            departmentName: "开发小组",		//所在部门名
            realName: "Mr.Darren",			//真实姓名
            phone: "17758178532",		  //手机号
            idCard: "33108211111111111",  //身份证
            id: 1,						  //用户id-唯一识别码
            workId: "Xb16620208",		  //教工编号
            email: "ludaye1112@163.com"	  //邮箱
        }
    ]
}
```

## 3. 更新自己的基本信息（需登录）

**【注】必须登录才可使用**

【例】${url}/user/update/info

### 3.1 接口

POST user/update/info

### 3.2 请求参数（Json格式）

|  参数名  |  类型  | 必需 | 默认值 |   备注   |
| :------: | :----: | :--: | :----: | :------: |
| realName | String |  N   |        | 真实姓名 |
|  idCard  | String |  N   |        |  身份证  |
|  phone   | String |  N   |        |  手机号  |
|  email   | String |  N   |        |   邮箱   |

### 3.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: null		//无返回内容
}
```

## 4.更改密码（需登录）

**【注】必须登录才可使用**

【例】${url}/user/modify/pwd

### 4.1 接口

POST user/modify/pwd

### 4.2 请求参数（非Json格式）

|   参数名    |  类型  | 必需 | 默认值 |  备注  |
| :---------: | :----: | :--: | :----: | :----: |
| oldPassword | String |  Y   |        | 原密码 |
| newPassword | String |  Y   |        | 新密码 |

### 4.3 Json响应

1. 修改成功

   ```json
   {
       code: 1,	//0-失败；1-成功
       success: true,	//true-成功；false-失败
       message: "操作成功！",	//提示信息
       data: null		//无返回内容
   }
   ```

2. 原密码错误

   ```json
   {
   code: 0,
   success: false,
   message: "更新失败 原密码错误",
   data: null
   }
   ```

3. 操作异常

   ```json
   {
   code: 0,
   success: false,
   message: "操作异常",
   data: null
   }
   ```

