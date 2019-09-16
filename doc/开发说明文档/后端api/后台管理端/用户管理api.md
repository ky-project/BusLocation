[TOC]

# 修改记录

# 用户管理API(v1.0)(均以Json数据交互)

## + 添加用户所有基本信息

**【注】必须登录才可使用**

【例】${url}/m/user/add

### - 接口

POST m/user/add

### - 请求参数

|    参数名     |  类型  | 必需 | 默认值 |                             备注                             |
| :-----------: | :----: | :--: | :----: | :----------------------------------------------------------: |
| department.id |  int   |  Y   |        | 部门id，id必须与部门id对应且存在<br>例如，"department" : { "id" : 5 } |
|    workId     | String |  Y   |        |                           职工编号                           |
|   realName    | String |  Y   |        |                           真实姓名                           |
|   password    | String |  Y   |        |                             密码                             |
|    idCard     | String |  Y   |        |                            身份证                            |
|     phone     | String |  Y   |        |                           联系方式                           |
|     email     | String |  Y   |        |                    邮箱（确保是邮箱格式）                    |

### 1.3 Json请求

```json
{
	"department":{
		"id":5
	},
	"workId":20190916,
	"realName":"函数内",
	"password":"123",
	"idCard":"42123123",
	"phone":"13281313545",
	"email":"123@123.com"
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## + 根据id删除用户信息

【例】${url}/m/user/del

### - 接口

POST m/user/del

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |      备注      |
| :----: | :--: | :--: | :----: | :------------: |
| userId | int  |  Y   |        | 待删除的用户id |

### - Json请求

```json
{
	"userId":78
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## + 根据id更新用户信息

**【注】必须登录才可使用**

【例】${url}/m/user/update

### - 接口

POST m/user/update

### - 请求参数

|    参数名     |  类型  | 必需 | 默认值 |                             备注                             |
| :-----------: | :----: | :--: | :----: | :----------------------------------------------------------: |
|      id       |  int   |  Y   |        |                        待更新的用户id                        |
| department.id |  int   |      |        | 部门id，id必须与部门id对应且存在<br>例如，"department" : { "id" : 5 } |
|    workId     | String |  N   |        |                           职工编号                           |
|   realName    | String |  N   |        |                           真实姓名                           |
|   password    | String |  N   |        |                             密码                             |
|    idCard     | String |  N   |        |                            身份证                            |
|     phone     | String |  N   |        |                           联系方式                           |
|     email     | String |  N   |        |                    邮箱（确保是邮箱格式）                    |

### - Json请求

```json
{
	"id":77,
	"department":{
		"id":5
	},
	"workId":20190916,
	"realName":"卡时间段内",
	"password":"123",
	"idCard":"987654321",
	"phone":null,
	"email":null
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: null				//无返回data
}
```

## + 查询所有用户的基本信息

【例】${url}/m/user/list

### - 接口

GET m/user/list

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   无   |  无  |  N   |        | 无参数 |

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            "departmentName": "开发小组",		//部门名
            "realName": "陆宇豪",				//真实姓名
            "phone": "17758178533",				//电话号码
            "idCard": "331082177422238849",		//身份证
            "id": 1,						//id-唯一识别码
            "workId": "Xb16620208",				//职工编号
            "email": "ludaye1112@163.com"		//邮箱
        },
        {
            "departmentName": "开发小组",
            "realName": "王一帆",
            "phone": "15868163725",
            "idCard": "330825199808101000",
            "id": 2,
            "workId": "Xb16620216",
            "email": "1445830802@qq.com"
        },
        {
            "departmentName": "开发小组",
            "realName": "唐倩倩",
            "phone": "",
            "idCard": "",
            "id": 3,
            "workId": "Xb16620129",
            "email": ""
        }
    ]
}
```

## + 分页查询用户的基本信息

【例】${url}/m/user/list/pages

### - 接口

POST m/user/list/pages

### - 请求参数

|  参数名  | 类型 | 必需 | 默认值 |       备注       |
| :------: | :--: | :--: | :----: | :--------------: |
| pageNow  | int  |  Y   |        |    当前页(>0)    |
| pageSize | int  |  Y   |        | 单页显示条数(>0) |

### - Json请求

```json
{
	"pageNow":1,
	"pageSize":10
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            "departmentName": "开发小组",		//部门名
            "realName": "陆宇豪",				//真实姓名
            "phone": "17758178533",				//电话号码
            "idCard": "331082177422238849",		//身份证
            "id": 1,						//id-唯一识别码
            "workId": "Xb16620208",				//职工编号
            "email": "ludaye1112@163.com"		//邮箱
        },
        {
            "departmentName": "开发小组",
            "realName": "王一帆",
            "phone": "15868163725",
            "idCard": "330825199808101000",
            "id": 2,
            "workId": "Xb16620216",
            "email": "1445830802@qq.com"
        },
        {
            "departmentName": "开发小组",
            "realName": "唐倩倩",
            "phone": "",
            "idCard": "",
            "id": 3,
            "workId": "Xb16620129",
            "email": ""
        }
    ]
}
```

## + 获取分页查询用户的总记录数和总页数

【例】${url}/m/user/list/pages?pageSize=10

### - 接口

GET m/user/list/pages

### - 请求参数

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
| pageSize | int  |  Y   |        | 页大小 |

### - Json响应

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

## + 根据realName进行分页模糊查询用户记录

【例】${url}/m/user/f/query/name

### - 接口

POST m/user/f/query/name

### - 请求参数

|  参数名  |  类型  | 必需 | 默认值 |       备注       |
| :------: | :----: | :--: | :----: | :--------------: |
| realName | String |  Y   |        |     用户姓名     |
| pageNow  |  int   |  Y   |        |    当前页(>0)    |
| pageSize |  int   |  Y   |        | 单页显示条数(>0) |

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            "departmentName": "开发小组",		//部门名
            "realName": "陆宇豪",				//真实姓名
            "phone": "17758178533",				//电话号码
            "idCard": "331082177422238849",		//身份证
            "id": 1,						//id-唯一识别码
            "workId": "Xb16620208",				//职工编号
            "email": "ludaye1112@163.com"		//邮箱
        },
        {
            "departmentName": "无部门",
            "realName": "陆宇豪",
            "id": 16,
            "workId": "Xb16620208"
        },
        {
            "departmentName": "无部门",
            "realName": "陆沛",
            "id": 63,
            "workId": "Xb16620111"
        }	
    ]
}
```

## + 获取根据realName进行分页模糊查询用户的总记录数和总页数

【例】${url}/m/user/f/query/name?realName=陆&pageSize=10

### - 接口

GET m/user/f/query/name

### - 请求参数

|  参数名  |  类型  | 必需 | 默认值 |   备注   |
| :------: | :----: | :--: | :----: | :------: |
| realName | String |  Y   |        | 真实姓名 |
| pageSize |  int   |  Y   |        |  页大小  |

### - Json响应

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

## + 根据workId进行分页模糊查询用户记录

【例】${url}/m/user/f/query/workId

### - 接口

POST m/user/f/query/workId

### - 请求参数

|  参数名  |  类型  | 必需 | 默认值 |       备注       |
| :------: | :----: | :--: | :----: | :--------------: |
|  workId  | String |  Y   |        |     用户工号     |
| pageNow  |  int   |  Y   |        |    当前页(>0)    |
| pageSize |  int   |  Y   |        | 单页显示条数(>0) |

### - Json请求

```json
{
	"workId":"Xb",
	"pageNow":1,
	"pageSize":10
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            "departmentName": "开发小组",		//部门名
            "realName": "陆宇豪",				//真实姓名
            "phone": "17758178533",				//电话号码
            "idCard": "331082177422238849",		//身份证
            "id": 1,						//id-唯一识别码
            "workId": "Xb16620208",				//职工编号
            "email": "ludaye1112@163.com"		//邮箱
        },
        {
            "departmentName": "开发小组",
            "realName": "王一帆",
            "phone": "15868163725",
            "idCard": "330825199808101000",
            "id": 2,
            "workId": "Xb16620216",
            "email": "1445830802@qq.com"
        }	
    ]
}
```

## + 根据workId进行分页模糊查询用户的总记录数和总页数

【例】${url}/m/user/f/query/workId?workId=Xb&pageSize=10

### - 接口

GET m/user/f/query/workId

### - 请求参数

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
|  workId  | int  |  Y   |        |  工号  |
| pageSize | int  |  Y   |        | 页大小 |

### - Json响应

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

## + 根据部门ID进行分页模糊查询用户记录

【例】${url}/m/user/f/query/dep

### - 接口

POST m/user/f/query/dep

### - 请求参数

|  参数名  | 类型 | 必需 | 默认值 |       备注       |
| :------: | :--: | :--: | :----: | :--------------: |
|  depId   | int  |  Y   |        |      部门id      |
| pageNow  | int  |  Y   |        |    当前页(>0)    |
| pageSize | int  |  Y   |        | 单页显示条数(>0) |

### - Json请求

```json
{
	"depId":1,
	"pageNow":1,
	"pageSize":10
}
```

### - Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	 //提示信息
    data: [
        {
            "departmentName": "开发小组",		//部门名
            "realName": "陆宇豪",				//真实姓名
            "phone": "17758178533",				//电话号码
            "idCard": "331082177422238849",		//身份证
            "id": 1,						//id-唯一识别码
            "workId": "Xb16620208",				//职工编号
            "email": "ludaye1112@163.com"		//邮箱
        },
        {
            "departmentName": "开发小组",
            "realName": "王一帆",
            "phone": "15868163725",
            "idCard": "330825199808101000",
            "id": 2,
            "workId": "Xb16620216",
            "email": "1445830802@qq.com"
        }	
    ]
}
```

## + 根据部门ID进行分页模糊查询用户的总记录数和总页数

【例】${url}/m/user/f/query/dep?depId=1&pageSize=10

### - 接口

GET m/user/f/query/dep

### - 请求参数

|  参数名  | 类型 | 必需 | 默认值 |  备注  |
| :------: | :--: | :--: | :----: | :----: |
|  depId   | int  |  Y   |        | 部门id |
| pageSize | int  |  Y   |        | 页大小 |

### - Json响应

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

## + 根据Id查询用户基本信息

【例】${url}/m/user/find

### - 接口

GET m/user/find

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
| userId | int  |  Y   |        | 用户id |

### - Json请求

```json
{
	"userId":4
}
```

### - Json响应

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

## + 根据realName,workId和departmentId模糊查询用户信息

【例】${url}/m/user/f/query

### - 接口

POST m/user/f/query

### - 请求参数

|    参数名    |  类型  | 必需 | 默认值 |   备注   |
| :----------: | :----: | :--: | :----: | :------: |
|   realName   | String |  N   |        | 真实姓名 |
|    workId    | String |  N   |        |   工号   |
| departmentId |  int   |  N   |        |  部门id  |

### - Json请求

```json
{
	"realName":"陆",
    "workId":"Xb",
    "departmentId":null
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": [
        {
            "departmentName": "开发小组",	//部门
            "realName": "陆宇豪",			//真实姓名
            "phone": "17758178533",			//电话号码
            "idCard": "331082177422238849",	//身份证
            "id": 1,						//id
            "workId": "Xb16620208",			//工号
            "email": "ludaye1112@163.com"	//邮箱
        },
        {
            "departmentName": "无部门",
            "realName": "陆宇豪",
            "id": 16,
            "workId": "Xb16620208"
        },
        {
            "departmentName": "无部门",
            "realName": "陆沛",
            "id": 63,
            "workId": "Xb16620111"
        }
    ]
}
```

