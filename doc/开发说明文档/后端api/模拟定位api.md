[TOC]

# GPS模拟定位API(v1.0)

## 1. 接收发送到服务器的GPS模拟数据

【例】${url}/position/gain

### 1.1 接口
POST position/gain

### 1.2 请求参数

【注】顺序不要求，参数名要求一致

```json
{
    "sbpRecodeTime": "2019-04-15 01:57:02", //发送时间
	"sbpLongitude": 104.038748,	//经度
	"sbpLatitude": 30.641821,	//维度
	"sbpVelocity": 65.25,	//方向
	"sbpDirection": 60.4	//方向角
}
```



### 1.3 Json响应

无