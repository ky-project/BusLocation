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
            "realName": "陆宇豪",		//用户名
            "srNames": "超级管理员,教师",	//用户拥有的所有角色名，以逗号分隔
            "workId": "Xb16620208"		//用户工号
        },
        {
            "realName": "管理员",
            "srNames": "管理员,超级管理员",
            "workId": "admin"
        },
        {
            "realName": "许国成",
            "srNames": "教师",
            "workId": "20112240"
        },
        {
            "realName": "卡萨诺",
            "srNames": "领班",
            "workId": "N20190420"
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
        {
            "srName": "超级管理员",	//角色名
            "checked": true,		//是否拥有该角色
            "id": 1					//角色id
        },
        {
            "srName": "教师",
            "checked": true,
            "id": 2
        },
        {
            "srName": "领班",
            "checked": false,
            "id": 3
        }
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
		{
            "srName": "超级管理员",
            "checked": true,
            "id": 1
        },
        {
            "srName": "教师",
            "checked": false,
            "id": 2
        },
        {
            "srName": "领班",
            "checked": false,
            "id": 3
        }
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