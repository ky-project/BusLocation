/**
 * 打开window
 */
var sheight=screen.height;
var swidth =screen.width;
var currentBrowser = getCurrentBrowser();//获取浏览器版本
function MM_openBrWindow(theURL,dialogHeight,dialogWidth) { 
	if("Chrome"==currentBrowser){
		window.open(theURL,window,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,height='+dialogHeight+'px,width:'+dialogWidth+'px');
	}else{
		window.showModalDialog(theURL,window,'edge: Raised; center: Yes; help: no; resizable: Yes; status: No;dialogHeight:'+dialogHeight+'px;dialogWidth:'+dialogWidth+'px;');
	}
}
function MM_openBrWindowlt(theURL,dialogHeight,dialogWidth) { 
	var dialogleft=(swidth-dialogWidth)/2;
	var dialogtop=(sheight-dialogHeight)/2;
	if("Chrome"==currentBrowser){
		window.open(theURL,window,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,height='+dialogHeight+'px,width='+dialogWidth+'px,left='+dialogleft+'px,top='+dialogtop+'px');
	}else{
		window.showModalDialog(theURL,window,'edge: Raised; center: Yes; help: no; resizable: Yes; status: No;dialogHeight:'+dialogHeight+'px;dialogWidth:'+dialogWidth+'px;dialogLeft:'+dialogleft+'px;dialogTop:'+dialogtop+'px');
	}
}
function MM_openBrWindowltnew(theURL,vArguments,dialogHeight,dialogWidth) { 
	var dialogleft=(swidth-dialogWidth)/2;
	var dialogtop=(sheight-dialogHeight)/2;
	if("Chrome"==currentBrowser){
		return window.open(theURL,vArguments,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,height='+dialogHeight+'px,width='+dialogWidth+'px,left='+dialogleft+'px,top:'+dialogtop+'px');
	}else{
		return window.showModalDialog(theURL,vArguments,'edge: Raised; center: Yes; help: no; resizable: Yes; status: No;dialogHeight:'+dialogHeight+'px;dialogWidth:'+dialogWidth+'px;dialogLeft:'+dialogleft+'px;dialogTop:'+dialogtop+'px');
	}
}
function MM_openBrWindowDefault2(theURL) {
	if("Chrome"==currentBrowser){
		window.open(theURL,window,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');
	}else{
		window.showModalDialog(theURL,window,'edge: Raised; center: Yes; help: no; resizable: Yes; status: No;');
	}
}
function MM_openBrWindowDefault(theURL) { 
	if("Chrome"==currentBrowser){
		window.open(theURL,window,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,height='+sheight+'px,width='+swidth+'px');
	}else{
		window.showModalDialog(theURL,window,'edge: Raised; center: Yes; help: no; resizable: Yes; status: No;dialogHeight:'+sheight+'px;dialogWidth:'+swidth+'px');
	}
}

function MM_openBrWindowref(theURL,vArguments) { 
	if("Chrome"==currentBrowser){
		return window.open(theURL,vArguments,'toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,height=570px;width=710px');
	}else{
		return window.showModalDialog(theURL,vArguments,'edge: Raised; center: Yes; help: Yes; resizable: Yes; status: No;dialogHeight:570px;dialogWidth:710px');
	}
}
/**
 * 处理时间格式化eg Sun Dec 25 2011 00:00:00 GMT+0800
 * @param {} datetime
 * @return {}
 */
function dateToStr(fmtCode, date) {
    var result, d, arr_d;
    var patrn_now_1 = /^y{4}-M{2}-d{2}\sh{2}:m{2}:s{2}$/;
    var patrn_now_11 = /^y{4}-M{1,2}-d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;

    var patrn_now_2 = /^y{4}\/M{2}\/d{2}\sh{2}:m{2}:s{2}$/;
    var patrn_now_22 = /^y{4}\/M{1,2}\/d{1,2}\sh{1,2}:m{1,2}:s{1,2}$/;

    var patrn_now_3 = /^y{4}年M{2}月d{2}日\sh{2}时m{2}分s{2}秒$/;
    var patrn_now_33 = /^y{4}年M{1,2}月d{1,2}日\sh{1,2}时m{1,2}分s{1,2}秒$/;

    var patrn_date_1 = /^y{4}-M{2}-d{2}$/;
    var patrn_date_11 = /^y{4}-M{1,2}-d{1,2}$/;

    var patrn_date_2 = /^y{4}\/M{2}\/d{2}$/;
    var patrn_date_22 = /^y{4}\/M{1,2}\/d{1,2}$/;

    var patrn_date_3 = /^y{4}年M{2}月d{2}日$/;
    var patrn_date_33 = /^y{4}年M{1,2}月d{1,2}日$/;

    var patrn_time_1 = /^h{2}:m{2}:s{2}$/;
    var patrn_time_11 = /^h{1,2}:m{1,2}:s{1,2}$/;
    var patrn_time_2 = /^h{2}时m{2}分s{2}秒$/;
    var patrn_time_22 = /^h{1,2}时m{1,2}分s{1,2}秒$/;

    if (!fmtCode) {
        fmtCode = "yyyy-MM-dd hh:mm:ss";
    }
    if (date) {
        d = new Date(date.toString().replace(new RegExp("-","gm"),"/"));
        if (isNaN(d)) {
            alert("时间参数非法\n正确的时间示例:\nThu Nov 9 20:30:37 UTC+0800 2006\n或\n2006/      10/17");
            return null;
        }
    } else {
        result = "";
        return result;
    }

    if (patrn_now_1.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "-" + arr_d.MM + "-" + arr_d.dd + " " + arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_now_11.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "-" + arr_d.MM + "-" + arr_d.dd + " " + arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_now_2.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "/" + arr_d.MM + "/" + arr_d.dd + " " + arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_now_22.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "/" + arr_d.MM + "/" + arr_d.dd + " " + arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_now_3.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "年" + arr_d.MM + "月" + arr_d.dd + "日" + " " + arr_d.hh + "时" + arr_d.mm + "分" + arr_d.ss + "秒";
    } else if (patrn_now_33.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "年" + arr_d.MM + "月" + arr_d.dd + "日" + " " + arr_d.hh + "时" + arr_d.mm + "分" + arr_d.ss + "秒";
    } else if (patrn_date_1.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "-" + arr_d.MM + "-" + arr_d.dd;
    } else if (patrn_date_11.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "-" + arr_d.MM + "-" + arr_d.dd;
    } else if (patrn_date_2.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "/" + arr_d.MM + "/" + arr_d.dd;
    } else if (patrn_date_22.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "/" + arr_d.MM + "/" + arr_d.dd;
    } else if (patrn_date_3.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.yyyy + "年" + arr_d.MM + "月" + arr_d.dd + "日";
    } else if (patrn_date_33.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.yyyy + "年" + arr_d.MM + "月" + arr_d.dd + "日";
    } else if (patrn_time_1.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_time_11.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.hh + ":" + arr_d.mm + ":" + arr_d.ss;
    } else if (patrn_time_2.test(fmtCode)) {
        arr_d = splitDate(d, true);
        result = arr_d.hh + "时" + arr_d.mm + "分" + arr_d.ss + "秒";
    } else if (patrn_time_22.test(fmtCode)) {
        arr_d = splitDate(d);
        result = arr_d.hh + "时" + arr_d.mm + "分" + arr_d.ss + "秒";
    } else {
        alert("没有匹配的时间格式!");
        return null;
    }
    return result;
}

function splitDate(d, isZero) {
    var yyyy, MM, dd, hh, mm, ss;
    //IE\FOX都支持
    yyyy = ( d.getYear() < 1900 ) ? ( 1900 + d.getYear() ) : d.getYear();
    if (isZero) {
        MM = (d.getMonth() + 1) < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
        dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
        hh = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
        mm = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
        ss = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
    } else {
        MM = d.getMonth() + 1;
        dd = d.getDate();
        hh = d.getHours();
        mm = d.getMinutes();
        ss = d.getSeconds();
    }
    return {
        "yyyy":yyyy,
        "MM":MM,
        "dd":dd,
        "hh":hh,
        "mm":mm,
        "ss":ss
    };
}

function error_message(message){
	$.messager.alert('错误消息提示!',message,'error');
}

function info_message(message){
	$.messager.alert('消息提示!',message,'info');
}

function warning_message(message){
	$.messager.alert('警告信息!',message,'warning');
}

function confirm_message(message,fnt){
	$.messager.confirm('选择提示框', message,function(r){
		if(r){
			fnt();
			return r;
		}
		return r;
	});
}
function input_message(){
	$.messager.prompt('输入提示框!', '请输入信息', function(r){
		if (r){
			return r;
		}
	});
}

function isEmpty(str){
	if(str!="undefined"&&""!=str&&null!=str&&"null"!=str){
		return false;
	}else{
		return true;
	}
}
function trim( strInput ) {
	var iLoop = 0;
	var iLoop2 = -1;
	var strChr;
	if( ( strInput == null) || ( strInput == "" ) )	return "";
	if( strInput )	{
		for( iLoop = 0; iLoop < strInput.length; iLoop++ ) {
			strChr = strInput.charAt( iLoop );
			if( strChr != ' ' )
			break;
		}
		for( iLoop2 = strInput.length - 1; iLoop2 >= 0; iLoop2-- ) {
			strChr = strInput.charAt( iLoop2 );
			if( strChr != ' ' )
			break;
		}
	}
	
	if( iLoop <= iLoop2 )	{
		return strInput.substring( iLoop, iLoop2 + 1 );
	}
	else 	return "";
}

//比较两个日期   startDate格式yyyy-MM-dd endDate格式yyyy-MM-DD
function compareTwoDate(a,b){
		if(a==null||a==undefined||a.length==0){
			return true;
		}
		if(b==null||b==undefined||b.length==0){
			return true;
		}
	 	var date1 = a.split("-");
	    var startTime = new Date(date1[0], date1[1], date1[2]);
	    var startTimes = startTime.getTime();
	    var date2 = b.split("-");
	    var endTime = new Date(date2[0], date2[1], date2[2]);
	    var endTimes = endTime.getTime();
	    if (startTimes > endTimes) {
	        return false;
	    }else{
	    	return true;
	    }
}
function isEmail(str){
	var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	if(myReg.test(str)) return true;
	return false;
}

//科学技术法转换
function eNumbertoNum(num){
	if(num!=''&&num!=null){
		num=num.toString().toUpperCase();
		if(num.indexOf( 'E' ) != -1){
			if(num.indexOf( 'E-' ) != -1){
				var arrNum = num.split('E-');
				var fz = arrNum[0]=='' ? 0 : arrNum[0];
				var fm = arrNum[1]=='' ? 1 : Math.pow(10, arrNum[1]);
				return parseFloat(parseFloat(fz)/parseFloat(fm)).toFixed(5);
			}else{
				return parseFloat(num);
			}
		}else{
			return num;
		}
	}else{
		return num;
	}
}

//金额保留2位小数
//将数字转换成逗号分隔的样式,保留两位小数s:value,n:小数位数 
function fmoney(s, n)
{ 
	var sign = "";
	s=eNumbertoNum(s);
	if(s!=null && s!=s.toString().replace(/[-]/g,"")){
		sign="-";
		s=s.toString().replace(/[-]/g,"");
	}
	n = n > 0 && n <= 20 ? n : 2; 
	s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + ""; 
	var l = s.split(".")[0].split("").reverse();
	r = s.split(".")[1]; 
	t = ""; 
	for(var i = 0; i < l.length; i ++ ) 
	{ 
		t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : ""); 
	} 
	return sign+t.split("").reverse().join("") + "." + r; 
} 
function getCurrentBrowser() {
	var currentBrowserType = window.navigator.userAgent ;
	if (currentBrowserType.indexOf("MSIE") >= 0) {
		return "IE";
	}else if (currentBrowserType.indexOf("Firefox") >= 0) {
		return "Firefox";
	}else if(currentBrowserType.indexOf("Chrome") >= 0){
		return "Chrome";
	}else if(currentBrowserType.indexOf("Opera") >= 0){
		return "Opera";
	}else if(currentBrowserType.indexOf("Safari") >= 0){
		return "Safari";
	}
}
