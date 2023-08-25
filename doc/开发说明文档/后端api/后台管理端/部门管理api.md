[TOC]

# 修改记录

# 部门管理API(v1.0)(均以Json数据交互)

## + 查询所有部门的id和name

【例】${url}/m/department/base/info

### - 接口

GET /m/department/base/info

### - 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  无  |   无   |  无  |

### - Json响应

```json
{
    code: 1,	//0-失败；1-成功
    success: true,	//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//数据
        {
            deptName: "开发小组", 	//部门名
            deptId: 1				//部门id
        },
        {
            deptName: "院领导",
            deptId: 2
        },
        {
            deptName: "经销商",
            deptId: 3
        },
        {
            deptName: "无部门",
            deptId: 5
        },
        {
            deptName: "党政办",
            deptId: 6
        }
    ]
}
```

