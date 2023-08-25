[TOC]

# 修改记录

# 用户角色管理API(v1.0)(均以Json数据交互)

## + 查询所有用户的基本信息和其所拥有的所有权限

【例】${url}/m/userRole/list

### - 接口

GET m/userRole/list

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  无  |        |  无  |

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": [
        {
            "departmentName": "开发小组",	//部门名
            "realName": "Mr.Darren",		//用户名
            "srNames": "超级管理员,教师",	//用户拥有的所有角色名，以逗号分隔
            "id": 1,					//用户id
            "workId": "Xb16620208"		//用户工号
        },
        {
            "departmentName": "开发小组",
            "realName": "管理员",
            "srNames": "超级管理员,管理员",
            "id": 4,
            "workId": "admin"
        },
        {
            "departmentName": "开发小组",
            "realName": "123",
            "id": 83,
            "workId": "4989498"
        }
    ]
}
```

## + 根据用户id查询用户所有角色的拥有状态

【例】${url}/m/userRole/list/user

### - 接口

POST m/userRole/list/user

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   id   | int  |  Y   |        | 用户id |

### - Json请求

```json
{
	"id":1
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": [
        1,		//角色id
        9,
        10
    ]
}
```

## + 根据用户id更新用户角色

【例】${url}/m/userRole/update

### - 接口

POST m/userRole/update

### - 请求参数

| 参数名 |      类型      | 必需 | 默认值 |   备注   |
| :----: | :------------: | :--: | :----: | :------: |
|   id   |      int       |  Y   |        |  用户id  |
| roles  | `List<Object>` |  Y   |        | 角色集合 |

### - Json请求

```json
{
	"id":1,
	"roles":[
		1, 9, 10
	]
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

## + 根据workId, realName, departmentId筛选用户角色list

【例】${url}/m/userRole/f/query

### - 接口

POST m/userRole/f/query

### - 请求参数

|    参数名    |  类型  | 必需 | 默认值 |   备注   |
| :----------: | :----: | :--: | :----: | :------: |
|    workId    | string |  N   |        | 用户工号 |
|   realName   | string |  N   |        | 真实姓名 |
| departmentId |  int   |  N   |        |  部门id  |

### - Json请求

```json
{
	"workId":"Xb",
	"realName":"", 
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
            "departmentName": "开发小组",		//用户部门
            "realName": "Mr.Darren",				//用户姓名
            "srNames": "超级管理员,测试角色,测试角色",	//拥有角色
            "id": 1,						//用户id
            "workId": "Xb16620208"			//用户工号
        }
    ]
}
```
