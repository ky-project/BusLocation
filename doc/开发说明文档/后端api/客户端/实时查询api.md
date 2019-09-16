[TOC]

# 修改记录

1. 2019-04-17

   - (API-1)查询所有路线的车辆位置API请求方式增加：POST
   
2. 2019-04-20

   - (API-1)查询所有路线的车辆位置API中，如果该路线的车辆无定位信息，则返回的json文件无任何关于该路线的信息。
   - (API-2)查询各路线站点信息API中，如果该路线没有任何站点信息，则不返回任何关于该路线的信息。
   
3. 2019-04-22
   - (API-2)查询各路线站点信息返回的json数据中，增加routId字段。
   - 添加：根据路线id和已查询数量查询路线定位信息(API-3)、查询所有路线的id和路线名(API-4)、根据路线id查询该路线的所有站点信息(API-5)
   
4. 2019-04-26

   - (API-1)查询所有路线的车辆位置：返回的json数据中增加routId字段
   - (API-3)根据路线id和已查询数量查询路线定位信息：返回的json数据中增加routeId字段
   - (API-5)根据路线id查询该路线的所有站点信息：返回的json数据中增加routeId字段
   
5. 2019-05-11

   - (API-2)路线信息添加字段startTime和endTime，分别为路线的开始时间和结束时间
   
6. 2019-05-19

   ​	- 更新各个接口的请求参数格式（Json格式或非Json格式）

# 实时查询API(v1.0)

## 1. 查询所有路线的车辆位置(数据量过大，不建议使用)

【例】${url}/bus/track

### 1.1 接口
GET/POST bus/track

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
    data: [					//返回的数据,如果该路线的车辆无定位信息，则不返回任何关于该路线的信息
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
            routeId: 7,					   //路线id
			routeName: "H5测试路线"			//路线名
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
            routeId: 8,
			routeName: "H5测试路线0422"
		}
    ]
}

```

## 2. 查询各路线站点信息

【例】${url}/bus/route/station

### 2.1 接口

GET/POST bus/route/station

### 2.2 请求参数

| 参数名 | 类型 | 必需 | 默认值 |  备注  |
| :----: | :--: | :--: | :----: | :----: |
|   无   |  无  |  无  |   无   | 无参数 |

### 2.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//返回的数据,如果该路线没有任何站点信息，则不返回任何关于该路线的信息
        {
            stationInfo: [	//站点信息
                {
                    prevStation: "无",	//上一站名
                    departTime: "7:00",	//最晚发出时间
                    longtidue: 104.038748,	//经度
                    latitude: 30.641821,	//维度
                    stationName: "文一校区",	//站点名
                    nextStation: "清雅苑"	//下一站名
                },
                {
                    prevStation: "文一校区",
                    departTime: "10:20",
                    longtidue: 104.067264,
                    latitude: 30.664438,
                    stationName: "清雅苑",
                    nextStation: "大学城北"
                },
                {
                    prevStation: "清雅苑",
                    departTime: "10:30",
                    longtidue: 104.064008,
                    latitude: 30.665316,
                    stationName: "大学城北",
                    nextStation: "无"
                }
            ],
            routeId: 2,				//路线id
            startTime: "6:00",		//路线开始运营时间
			endTime: "24:00",		//路线结束运营时间
            routeName: "下沙二号线"	//路线名
        },
        {
            stationInfo: [
                {
                    prevStation: "无",
                    departTime: "7:00",
                    longtidue: 105.038748,
                    latitude: 31.641821,
                    stationName: "金沙学府",
                    nextStation: "金隅观澜时代"
                },
                {
                    prevStation: "金沙学府",
                    departTime: "9:00",
                    longtidue: 105.067264,
                    latitude: 31.660307,
                    stationName: "金隅观澜时代",
                    nextStation: "下沙理工北门"
                },
                {
                    prevStation: "金隅观澜时代",
                    departTime: "10:00",
                    longtidue: 105.067264,
                    latitude: 31.664438,
                    stationName: "下沙理工北门",
                    nextStation: "无"
                }
            ],
            routeId: 3,
            startTime: "0:00",			//0:00说明路线未安排行驶车辆
			endTime: "0:00",			//0:00说明路线未安排行驶车辆
            routeName: "余杭一号线"
        }
    ]
}
```

## 3. 根据路线id和已查询数量查询路线定位信息

【例】${url}/route/one/position

### 3.1 接口

POST /route/one/position

### 3.2 请求参数(非Json格式)

|   参数名   |  类型   | 必需 | 默认值 |              备注              |
| :--------: | :-----: | :--: | :----: | :----------------------------: |
|  routeId   | Integer | 必需 |   无   |             路线id             |
| startIndex | Integer | 必需 |   无   | 开始查询位置，不包含该索引数据 |

### 3.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//返回的数据,如果该路线没有任何定位信息，则trackRoute为[]
        routeId: 2,						//路线id
        trackRoute: [
            {
                latitude: 30.641821,	//维度
                velocity: 65.25,		//速度
                longitude: 104.038748,	//经度
                direction: 60.4			//方向角
            },
            {
                latitude: 30.642,
                velocity: 66.44,
                longitude: 104.041,
                direction: 90.5
            }
		]
    ]
}
```

## 4. 查询所有路线的id和路线名

【例】${url}/route/base/info

### 4.1 接口

GET /route/base/info

### 4.2 请求参数

| 参数名 | 类型 | 必需 | 默认值 | 备注 |
| :----: | :--: | :--: | :----: | :--: |
|   无   |  无  |  无  |   无   |  无  |

### 4.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//返回的数据
        {
            id: 1,			//路线id
            routeName: "下沙一号线"	//路线名
        },
        {
            id: 2,
            routeName: "下沙二号线"
        },
        {
            id: 3,
            routeName: "余杭一号线"
        }
    ]
}
```

## 5. 根据路线id查询该路线的所有站点信息

【例】${url}/route/station/info

### 5.1 接口

POST /route/station/info

### 5.2 请求参数（非Json格式）

| 参数名  |  类型   | 必需 | 默认值 |  备注  |
| :-----: | :-----: | :--: | :----: | :----: |
| routeId | Integer | 必需 |   无   | 路线id |

### 5.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: [					//返回的数据
        stationInfo: [
            {
                prevStation: "无",			//上一站
                departTime: "7:00",			//最晚发车时间
                longtidue: 104.038748,		//经度
                latitude: 30.641821,		//维度
                stationName: "文一校区",	 //站点名
                nextStation: "德信早城北门"	//下一站
            },
            {
                prevStation: "文一校区",
                departTime: "8:00",
                longtidue: 104.047789,
                latitude: 30.648128,
                stationName: "德信早城北门",
                nextStation: "无"
            }
		], 
		routeId: 2	//路线id
    ]
}
```

## 6. 根据路线id查询路线行驶轨迹

【例】${url}/routeposition/find/one

### 6.1 接口

POST /routeposition/find/one

### 6.2 请求参数（非Json格式）

| 参数名  |  类型   | 必需 | 默认值 |  备注  |
| :-----: | :-----: | :--: | :----: | :----: |
| routeId | Integer | 必需 |   无   | 路线id |

### 6.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: {
        routeId: 5,			//路线id
        routePosition: [	//路线轨迹定位
            {
                latitude: 30.286916,	//纬度
                longitude: 120.146791	//经度
            },
            {
                latitude: 30.287342,
                longitude: 120.147058
            },
            {
                latitude: 30.287592,
                longitude: 120.14722
            },
            {
                latitude: 30.287892,
                longitude: 120.1474
            },
            {
                latitude: 30.288161,
                longitude: 120.147512
            },
            {
                latitude: 30.288512,
                longitude: 120.147692
            }
        ]
    }
}
```

## 7. 根据路线id查询最新的定位点

【例】${url}/bus/route/track

### 7.1 接口

POST /bus/route/track

### 7.2 请求参数（非Json格式）

| 参数名  |  类型   | 必需 | 默认值 |  备注  |
| :-----: | :-----: | :--: | :----: | :----: |
| routeId | Integer | 必需 |   无   | 路线id |

### 7.3 Json响应

```json
{
    code: 1,				//0-失败；1-成功
    success: true,			//true-成功；false-失败
    message: "操作成功！",	//提示信息
    data: {					//只包含最新的定位点
        routeId: 5,			//路线id
        trackRoute: {
            latitude: 30.19143822182585,	//纬度
            velocity: 0.5,					//速度
            longitude: 120.82580722465978,	//经度
            direction: 0					//经度
        }
	}
}
```