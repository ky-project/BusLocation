[TOC]

# 修改记录

# 角色权限管理API(v1.0)(均以Json数据交互)

## + 添加角色指定权限

【例】${url}/m/roleAuthority/add

### - 接口

POST m/roleAuthority/add

### - 请求参数

|  参数名   |      类型      | 必需 | 默认值 |   备注   |
| :-------: | :------------: | :--: | :----: | :------: |
|    id     |      int       |  Y   |        |  角色id  |
| authority | `List<Object>` |  Y   |        | 权限集合 |

### - Json请求

```json
{
	"id":10,	//角色id
	"authority":[
		{
            "id": 3,	//权限id
            "saDisplayName": "用户筛选",	//权限名
            "checked": true		//是否有权限
        },
        {
            "id": 4,
            "saDisplayName": "用户添加",
            "checked": false
        },
        {
            "id": 12,
            "saDisplayName": "权限重载",
            "checked": true
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

## + 根据角色id查询该角色的所有权限

【例】${url}/m/roleAuthority/role/authority

### - 接口

POST m/roleAuthority/role/authority

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   id   | int  |  Y   |        | 角色id |

### - Json请求

```json
{
	"id":10	//角色id
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": {
        "角色权限管理": [
            {
                "id": 14,						//权限id
                "saDisplayName": "角色权限添加",	//权限名
                "checked": true					//是否有权限-有:true;无-false
            },
            {
                "id": 15,
                "saDisplayName": "角色权限查询",
                "checked": false
            }
        ],
        "用户管理": [
            {
                "id": 3,
                "saDisplayName": "用户筛选",
                "checked": true
            },
            {
                "id": 7,
                "saDisplayName": "用户删除",
                "checked": false
            }
        ]
    }
}
```