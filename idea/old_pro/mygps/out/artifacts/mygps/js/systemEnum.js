var resTypeEnum={
		1:"模块",
		2:"菜单",
		3:"按钮"
}
function getResType(mykey){
	var result="";
	if(mykey==null||mykey==undefined||mykey==""||mykey=="null")return result;
	for(key in resTypeEnum){
		if(key==mykey){
			result = resTypeEnum[key];
			break;
		}
	}
    return result;
}

var catTypeEnum={
		10: "专业名称"
}
function getcatType(mykey){
	var result="";
	if(mykey==null||mykey==undefined||mykey==""||mykey=="null")return result;
	for(key in catTypeEnum){
		if(key==mykey){
			result = catTypeEnum[key];
			break;
		}
	}
    return result;
}

var zhiyuanSortTBEnum={
		"V1": "第一志愿",
		"V2": "第二志愿",
		"V3": "第三志愿",
		"V4": "第四志愿",
		"V5": "第五志愿"
}

var zhiyuanSortLQEnum={
		"V1Status": "第一志愿",
		"V2Status": "第二志愿",
		"V3Status": "第三志愿",
		"V4Status": "第四志愿",
		"V5Status": "第五志愿"
}
