

[TOC]

# 修改记录

# 角色管理API(v1.0)(均以Json数据交互)

## + 查询所有角色信息

【例】${url}/m/role/list

### - 接口

GET m/role/list

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  无  |   无   |  无  |

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": [
        {
            "createdDate": 1555049025000,	//毫秒级时间戳
            "srLevel": 0,					//角色级别
            "srName": "超级管理员",		//角色名
            "srSource": "super",			//角色代码
            "srManage": true,				//是否是管理员
            "remark": "",					//备注
            "id": 1							//角色id
        },
        {
            "createdDate": 1555770739000,
            "srLevel": 2,
            "srName": "教师",
            "srSource": "teacher",
            "srManage": false,
            "id": 2
        }
    ]
}
```

## + 根据id删除角色

【例】${url}/m/role/del

### - 接口

POST m/role/del

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   id   | int  |  Y   |        | 角色id |

### - Json请求

```json
{
	"id":4
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": null
}
```

## + 根据id恢复角色

【例】${url}/m/role/rollback

### - 接口

POST m/role/rollback

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   id   | int  |  Y   |        | 角色id |

### - Json请求

```json
{
	"id":4
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": null
}
```

## + 添加角色

【例】${url}/m/role/add

### - 接口

POST m/role/add

### - 请求参数

|  参数名  |  类型   | 必需 | 默认值 |     备注     |
| :------: | :-----: | :--: | :----: | :----------: |
|  srName  | String  |  Y   |        |    角色名    |
| srSource | String  |  Y   |        |   角色代码   |
| srManage | boolean |  Y   |        | 是否是管理员 |
| srLevel  |   int   |  Y   |        |   角色等级   |
|  remark  | String  |  N   |        |     描述     |

### - Json请求

```json
{
	"srName":"测试角色",
	"srSource":"testRole",
	"srManage":false,
	"srLevel":10,
	"remark":null
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": {
        "createdDate": 1568742597000,
        "srLevel": 10,
        "srName": "测试角色",
        "srSource": "testRole",
        "srManage": false,
        "remark": "",
        "id": 6
    }
}
```

## + 更新角色记录

【例】${url}/m/role/update

### - 接口

POST m/role/update

### - 请求参数

|  参数名  |  类型   | 必需 | 默认值 |     备注     |
| :------: | :-----: | :--: | :----: | :----------: |
|    id    |   int   |  Y   |        |    角色id    |
|  srName  | String  |  N   |        |    角色名    |
| srSource | String  |  N   |        |   角色代码   |
| srManage | boolean |  N   |        | 是否是管理员 |
| srLevel  |   int   |  N   |        |   角色等级   |
|  remark  | String  |  N   |        |     描述     |

### - Json请求

```json
{
	"id":6,
	"srName":"测试角色",
	"srSource":"testRole",
	"srManage":false,
	"srLevel":11,
	"remark":"测试角色，待删除"
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": {
        "createdDate": 1568742597000,
        "srLevel": 11,
        "srName": "测试角色",
        "srSource": "testRole",
        "srManage": false,
        "remark": "测试角色，待删除",
        "id": 6
    }
}
```

