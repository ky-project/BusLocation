[TOC]

# 修改记录

# 站点管理API(v1.0)(均以Json数据交互)

## + 查询所有站点基本信息

【例】${url}/m/station/list

### - 接口

GET m/station/list

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
            "sbsLongitude": 104.038748,		//经度
            "sbsDesc": null,				//站点描述
            "id": 1,						//站点id
            "sbsStation": "文一校区",			//站点名
            "sbsLatitude": 30.641821		//纬度
        },
        {
            "sbsLongitude": 120.336508,
            "sbsDesc": null,
            "id": 2,
            "sbsStation": "德信早城北门",
            "sbsLatitude": 30.320811
        },
        {
            "sbsLongitude": 120.354534,
            "sbsDesc": null,
            "id": 3,
            "sbsStation": "老物美",
            "sbsLatitude": 30.312813
        }
    ]
}
```

## + 根据站点名模糊查询所有站点基本信息

【例】${url}/m/station/list

### - 接口

POST m/station/list

### - 请求参数

|   参数名   |  类型  | 必需 | 默认值 |  备注  |
| :--------: | :----: | :--: | :----: | :----: |
| sbsStation | string |  Y   |        | 站点名 |

### - Json请求

```json
{
	"sbsStation":"一"
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
            "sbsLongitude": 104.038748,		//经度
            "sbsDesc": null,				//站点描述
            "id": 1,						//站点id
            "sbsStation": "文一校区",			//站点名
            "sbsLatitude": 30.641821		//纬度
        },
        {
            "sbsLongitude": 120.144837,
            "sbsDesc": null,
            "id": 27,
            "sbsStation": "白荡海公交站（文一校区）",
            "sbsLatitude": 30.294749
        }
    ]
}
```

## + 添加站点记录

【例】${url}/m/station/add

### - 接口

POST m/station/add

### - 请求参数

|    参数名    |  类型  | 必需 | 默认值 |   备注   |
| :----------: | :----: | :--: | :----: | :------: |
|  sbsStation  | string |  Y   |        |  站点名  |
| sbsLongitude | double |  Y   |        |   经度   |
| sbsLatitude  | double |  Y   |        |   纬度   |
|   sbsDesc    | string |  N   |        | 站点描述 |

### - Json请求

```json
{
	"sbsLongitude": 104.038748,
    "sbsDesc": "",
    "sbsStation": "测试站点002",
    "sbsLatitude": 30.641821
}
```

### - Json响应

```json
{
    "code": 1,
    "success": true,
    "message": "操作成功！",
    "data": {
        "sbsLongitude": 104.038748,		//经度
        "sbsDesc": "",					//站点描述
        "id": 71,						//站点id
        "sbsStation": "测试站点002",	//站点名
        "sbsLatitude": 30.641821		//纬度
    }
}
```

## + 根据id删除对应的站点信息

【例】${url}/m/station/del

### - 接口

POST m/station/del

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   id   | int  |  Y   |        | 站点id |

### - Json请求

```json
{
	"id":71
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

## + 根据id更新站点信息

【例】${url}/m/station/del

### - 接口

POST m/station/del

### - 请求参数

|    参数名    |  类型  | 必需 | 默认值 |   备注   |
| :----------: | :----: | :--: | :----: | :------: |
|      id      |  int   |  Y   |        |  站点id  |
|  sbsStation  | string |  Y   |        |  站点名  |
| sbsLongitude | double |  Y   |        |   经度   |
| sbsLatitude  | double |  Y   |        |   纬度   |
|   sbsDesc    | string |  N   |        | 站点描述 |

### - Json请求

```json
{
    "sbsLongitude": 104.00001,
    "sbsDesc": "测试",
    "id": 70,
    "sbsStation": "测试001",
    "sbsLatitude": 30.00001
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

