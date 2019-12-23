[TOC]

# 修改记录

# 权限管理API(v1.0)(均以Json数据交互)

## + 查询所有权限

【例】${url}/m/authority/list

### - 接口

GET m/authority/list

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
    "data": {
        "部门管理": [						//权限组
            {
                "id": 1,					//权限id
                "saDisplayName": "部门查询",	//权限名
                "checked": false			//是否选中
            }
        ],
        "用户管理": [
            {
                "id": 3,
                "saDisplayName": "用户筛选",
                "checked": false
            },
            {
                "id": 4,
                "saDisplayName": "用户添加",
                "checked": false
            }
        ],
        "权限管理": [
            {
                "id": 12,
                "saDisplayName": "权限重载",
                "checked": false
            },
            {
                "id": 13,
                "saDisplayName": "权限查询",
                "checked": false
            }
        ],
        "角色管理": [
            {
                "id": 2,
                "saDisplayName": "角色查询",
                "checked": false
            }
        ]
    }
}
```

## + 重新加载权限

【例】${url}/m/authority/reload

### - 接口

GET m/authority/reload

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
    "data": null
}
```

## + 重新加载权限

【例】${url}/m/authority/no

### - 接口

GET m/authority/no

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  无  |   无   |  无  |

### - Json响应

```json
{
    "code": 0,
    "success": false,
    "message": "无操作权限",
    "data": null
}
```

