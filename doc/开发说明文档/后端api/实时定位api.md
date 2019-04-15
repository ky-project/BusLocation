[TOC]

# 实时定位API(v1.0)

## 1. 查询所有路线的车辆位置(速度和方向角数据不可靠)

【例】${url}/bus/track

### 1.1 接口
GET bus/track

### 1.2 请求参数

|参数名|类型|必需|默认值|备注|
|:--:|:--:|:--:|:--:|:--:|
|   无   |  无  |  无  |   无   | 无参数 |

### 1.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//返回的数据
        {
            trackRoute: [	//位置信息
                {
                    latitude: 30.641821,	//维度
                    velocity: 65.25,		//速度，测试时数据忽略，乱造的
                    longitude: 104.038748,	//经度
                    direction: 60.4			//方向角，测试时数据忽略，乱造的
                },
                {
                    latitude: 30.642,
                    velocity: 66.44,
                    longitude: 104.041,
                    direction: 90.5
                },
                {
                    latitude: 30.648128,
                    velocity: 79.23,
                    longitude: 104.047789,
                    direction: 60.59
                }
			],
			routeName: "下沙二号线"			//路线名
		},
		{
            trackRoute: [
                {
                    latitude: 31.642,
                    velocity: 12.04,
                    longitude: 105.041,
                    direction: 156.21
                },
                {
                    latitude: 31.644,
                    velocity: 45.04,
                    longitude: 105.045,
                    direction: 123.12
                },
                {
                    latitude: 31.647,
                    velocity: 35.12,
                    longitude: 105.047,
                    direction: 76.04
                }
			],
			routeName: "余杭一号线"
		},
		{
            trackRoute: [ ],	//如果该路线的车辆还未出发，则trackRoute内无数据
            routeName: "余杭三号线"
		}
    ]
}

```
