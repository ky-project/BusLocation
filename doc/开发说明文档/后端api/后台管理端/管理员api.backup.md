[TOC]

# 修改记录

1. 2019-05-19
   - 更新各个接口的请求参数格式（Json格式或非Json格式）
2. 2019-07-17

    - 添加根据用户工号、姓名进行分页模糊查询接口，根据部门id分页查询用户信息接口
3. 2019-07-19

    - 添加根据页大小查询总页数和总记录数接口
    - 添加根据workId进行分页模糊查询的总页数和总记录数接口
    - 添加根据realName进行分页模糊查询的总页数和总记录数接口
    - 添加根据部门id进行分页查询的总页数和总记录数接口
4. 2017-07-30

    - 修改10,11,12,13,14号接口，使用普通的传递参数的形式进行查询
5. 2019-09-09
    - 管理员登录验证：添加提交字段"verifyCode"(验证码)
6. 2019-09-13
    - 管理员登录验证：修改管理员登录接口名
    - 管理员登录验证：添加参数`loginType`
7. 2019-09-15
    - 添加管理员修改自身基本信息接口
    - 添加管理员修改自身密码接口

# 管理员API(v1.0)

## + 查询所有用户的基本信息

【例】${url}/admin/info/list

### - 接口

GET admin/info/list

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   无   |  无  |  N   |        | 无参数 |

### - Json响应

```json
{
    code: 1, 				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            departmentName: "开发小组",		//部门名
            realName: "陆宇豪",			//真实姓名
            phone: "17758178532",		  //电话号码
            idCard: "33108211111111111",  //身份证
            id: 1,						  //id-唯一识别码
            workId: "Xb16620208",		  //职工编号
            email: "ludaye1112@163.com"	  //邮箱
        },
        {
            departmentName: "开发小组",
            realName: "王一帆",
            phone: "",
            idCard: "",
            id: 2,
            workId: "Xb16620216",
            email: ""
        },
        {
            departmentName: "开发小组",
            realName: "唐倩倩",
            phone: "",
            idCard: "",
            id: 3,
            workId: "Xb16620129",
            email: ""
        },
        {
            departmentName: "开发小组",
            realName: "许鑫磊",
            phone: "",
            idCard: "",
            id: 13,
            workId: "Xb16620218 ",
            email: ""
        }
    ]
}

```

## +分页查询用户的基本信息

【例】${url}/admin/info/list/pages

### - 接口

POST admin/info/list/pages

### - 请求参数（非Json格式）

|  参数名  | 类型 | 必需 | 默认值 |       备注       |
| :------: | :--: | :--: | :----: | :--------------: |
| pageNow  | int  |  Y   |        |    当前页(>0)    |
| pageSize | int  |  Y   |        | 单页显示条数(>0) |

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            departmentName: "开发小组",		//部门名
            realName: "陆宇豪",			//真实姓名
            phone: "17758178532",		  //电话号码
            idCard: "33108211111111111",  //身份证
            id: 1,						  //id-唯一识别码
            workId: "Xb16620208",		  //职工编号
            email: "ludaye1112@163.com"	  //邮箱
        },
        {
            departmentName: "开发小组",
            realName: "王一帆",
            phone: "",
            idCard: "",
            id: 2,
            workId: "Xb16620216",
            email: ""
        },
        {
            departmentName: "开发小组",
            realName: "唐倩倩",
            phone: "",
            idCard: "",
            id: 3,
            workId: "Xb16620129",
            email: ""
        },
        {
            departmentName: "开发小组",
            realName: "许鑫磊",
            phone: "",
            idCard: "",
            id: 13,
            workId: "Xb16620218 ",
            email: ""
        }
    ]
}
```

## + 添加用户所有基本信息（需登录）

**【注】必须登录才可使用**

【例】${url}/admin/info/add

### - 接口

POST admin/info/add

### - 请求参数（Json格式）

|    参数名     |  类型  | 必需 | 默认值 |               备注               |
| :-----------: | :----: | :--: | :----: | :------------------------------: |
| department.id |  int   |  Y   |        | 部门id，id必须与部门id对应且存在 |
|    workId     | String |  Y   |        |             职工编号             |
|   realName    | String |  Y   |        |             真实姓名             |
|   password    | String |  Y   |        |               密码               |
|    idCard     | String |  Y   |        |              身份证              |
|     phone     | String |  Y   |        |             联系方式             |
|     email     | String |  Y   |        |      邮箱（确保是邮箱格式）      |

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## + 根据id删除用户信息（需登录）

**【注】必须登录才可使用**

【例】${url}/admin/del/id

### 5.1 接口

POST admin/del/id

### 5.2 请求参数（非Json格式）

| 参数名 | 类型 | 必需 | 默认值 |      备注      |
| :----: | :--: | :--: | :----: | :------------: |
| userId | int  |  Y   |        | 待删除的用户id |

### 5.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## 6. 根据id更新用户信息（需登录）

**【注】必须登录才可使用**

【例】${url}/admin/update/info

### 6.1 接口

POST admin/update/info

### 6.2 请求参数（Json格式）

|    参数名     |  类型  | 必需 | 默认值 |                             备注                             |
| :-----------: | :----: | :--: | :----: | :----------------------------------------------------------: |
|      id       |  int   |  Y   |        |                        待更新的用户id                        |
| department.id |  int   |      |        | 部门id，id必须与部门id对应且存在<br>"department" : { "id" : 5 } |
|    workId     | String |  N   |        |                           职工编号                           |
|   realName    | String |  N   |        |                           真实姓名                           |
|   password    | String |  N   |        |                             密码                             |
|    idCard     | String |  N   |        |                            身份证                            |
|     phone     | String |  N   |        |                           联系方式                           |
|     email     | String |  N   |        |                    邮箱（确保是邮箱格式）                    |

### 6.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## 7. 根据workId进行分页模糊查询用户记录（必须登录才能使用）

【例】${url}/admin/f/query/workId

### 7.1 接口

POST admin/f/query/workId

### 7.2 请求参数（非Json格式）

|  参数名  |  类型  | 必需 | 默认值 |       备注       |
| :------: | :----: | :--: | :----: | :--------------: |
|  workId  | String |  Y   |        |     用户工号     |
| pageNow  |  int   |  Y   |        |    当前页(>0)    |
| pageSize |  int   |  Y   |        | 单页显示条数(>0) |

### 7.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        { 
        	id: 1,							////id-唯一识别码
            content: {
                departmentName: "开发小组",		//部门名
                realName: "陆宇豪",			//真实姓名
                phone: "17758178532",		  //电话号码
                idCard: "33108211111111111",  //身份证					  
                workId: "Xb16620208",		  //职工编号
                email: "ludaye1112@163.com"	  //邮箱
            }
        },
        {
            id: 2,
            content: {
                departmentName: "开发小组",
                realName: "王一帆",
                phone: "15868163725",
                idCard: "330825199808101000",
                workId: "Xb16620216",
                email: "1445830802@qq.com"
            }
        },
        {
            id: 4,
            content: {
                departmentName: "开发小组",
                realName: "管理员",
                phone: "",
                idCard: "",
                workId: "admin",
                email: ""
            }
        }	
    ]
}
```

## 8. 根据realName进行分页模糊查询用户记录（必须登录才能使用）

【例】${url}/admin/f/query/name

### 8.1 接口

POST admin/f/query/name

### 7.2 请求参数（非Json格式）

|  参数名  |  类型  | 必需 | 默认值 |       备注       |
| :------: | :----: | :--: | :----: | :--------------: |
| realName | String |  Y   |        |     用户姓名     |
| pageNow  |  int   |  Y   |        |    当前页(>0)    |
| pageSize |  int   |  Y   |        | 单页显示条数(>0) |

### 8.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        { 
        	id: 1,							////id-唯一识别码
            content: {
                departmentName: "开发小组",		//部门名
                realName: "陆宇豪",			//真实姓名
                phone: "17758178532",		  //电话号码
                idCard: "33108211111111111",  //身份证					  
                workId: "Xb16620208",		  //职工编号
                email: "ludaye1112@163.com"	  //邮箱
            }
        },
        {
            id: 2,
            content: {
                departmentName: "开发小组",
                realName: "王一帆",
                phone: "15868163725",
                idCard: "330825199808101000",
                workId: "Xb16620216",
                email: "1445830802@qq.com"
            }
        },
        {
            id: 4,
            content: {
                departmentName: "开发小组",
                realName: "管理员",
                phone: "",
                idCard: "",
                workId: "admin",
                email: ""
            }
        }	
    ]
}
```

## 9. 根据部门ID进行分页模糊查询用户记录（必须登录才能使用）

【例】${url}/admin/f/query/dep

### 9.1 接口

POST admin/f/query/dep

### 9.2 请求参数（非Json格式）

|  参数名  | 类型 | 必需 | 默认值 |       备注       |
| :------: | :--: | :--: | :----: | :--------------: |
|  depId   | int  |  Y   |        |      部门id      |
| pageNow  | int  |  Y   |        |    当前页(>0)    |
| pageSize | int  |  Y   |        | 单页显示条数(>0) |

### 9.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        { 
        	id: 1,							////id-唯一识别码
            content: {
                departmentName: "开发小组",		//部门名
                realName: "陆宇豪",			//真实姓名
                phone: "17758178532",		  //电话号码
                idCode: "33108211111111111",  //身份证					  
                workId: "Xb16620208",		  //职工编号
                email: "ludaye1112@163.com"	  //邮箱
            }
        },
        {
            id: 2,
            content: {
                departmentName: "开发小组",
                realName: "王一帆",
                phone: "15868163725",
                idCode: "330825199808101000",
                workId: "Xb16620216",
                email: "1445830802@qq.com"
            }
        },
        {
            id: 4,
            content: {
                departmentName: "开发小组",
                realName: "管理员",
                phone: "",
                idCode: "",
                workId: "admin",
                email: ""
            }
        }	
    ]
}
```

## 10. 获取分页查询用户的总记录数和总页数

【例】${url}/admin/info/list/pages?pageSize=10

### 10.1 接口

GET admin/info/list/pages

### 10.2 请求参数（非Json格式）

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
| pageSize | int  |  Y   |        | 页大小 |

### 10.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: {
        totalPages: 2,		//总页数
        totalRecoding: 11	//总记录数
    }
}
```

## 11. 获取根据realName进行分页模糊查询用户的总记录数和总页数

【例】${url}/admin/f/query/name?realName=陆&pageSize=10

### 11.1 接口

GET admin/f/query/name

### 11.2 请求参数（非Json格式）

|  参数名  |  类型  | 必需 | 默认值 |   备注   |
| :------: | :----: | :--: | :----: | :------: |
| realName | String |  Y   |        | 真实姓名 |
| pageSize |  int   |  Y   |        |  页大小  |

### 11.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: {
        totalPages: 2,		//总页数
        totalRecoding: 11	//总记录数
    }
}
```

## 12. 获取根据部门ID进行分页模糊查询用户的总记录数和总页数

【例】${url}/admin/f/query/dep?depId=1&pageSize=10

### 12.1 接口

GET admin/f/query/dep

### 12.2 请求参数（非Json格式）

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
|  depId   | int  |  Y   |        | 部门id |
| pageSize | int  |  Y   |        | 页大小 |

### 12.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: {
        totalPages: 2,		//总页数
        totalRecoding: 11	//总记录数
    }
}
```

## 13. 获取根据workId进行分页模糊查询用户的总记录数和总页数

【例】${url}/admin/f/query/workId?workId=Xb&pageSize=10

### 13.1 接口

GET admin/f/query/workId

### 13.2 请求参数（非Json格式）

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
|  workId  | int  |  Y   |        |  工号  |
| pageSize | int  |  Y   |        | 页大小 |

### 13.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: {
        totalPages: 2,		//总页数
        totalRecoding: 11	//总记录数
    }
}
```

## 14. 根据Id查询用户基本信息

【例】${url}/admin/find/user

### 14.1 接口

GET admin/find/user

### 14.2 请求参数（非Json格式）

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
| userId | int  |  Y   |        | 用户id |

### 14.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: {
        departmentName: "开发小组",		//部门名
        realName: "陆宇豪",			//真实姓名
        phone: "17758178532",		  //电话号码
        idCode: "33108211111111111",  //身份证					  
        workId: "Xb16620208",		  //职工编号
        email: "ludaye1112@163.com"	  //邮箱
        password: "456",				//密码
        id: 1,							//用户id
    }
}
```

## 15. 修改自身密码

【例】${url}/m/self/modify/pwd

### 15.1 接口

POST /m/self/modify/pwd

### 15.2 请求参数（Json格式）

|   参数名    |  类型  | 必需 | 默认值 |  备注  |
| :---------: | :----: | :--: | :----: | :----: |
| oldPassword | String |  Y   |        | 旧密码 |
| newPassword | String |  Y   |        | 新密码 |

### 15.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: null		//无返回内容
}
```

## 16. 修改自身密码

【例】${url}/m/self/update/info

### 16.1 接口

POST /m/self/update/info

### 16.2 请求参数（Json格式）

|  参数名  |  类型  | 必需 | 默认值 |   备注   |
| :------: | :----: | :--: | :----: | :------: |
| realName | String |  N   |        | 真实姓名 |
|  idCard  | String |  N   |        |  身份证  |
|  phone   | String |  N   |        |  手机号  |
|  email   | String |  N   |        |   邮箱   |

### 16.3 Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: null		//无返回内容
}
```

