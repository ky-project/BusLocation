function initialize(){
    //获取所需元素
    var $main = $('.main');
    var $header = $('.header');
    var $footer = $('.footer');

    var $link = $('.header a');
    var $scrollMask = $('.scroll-mask');
    var $scrollList = $('.scroll-list');
    var $scrollItems = null;
    var $rollList = $('.roll-list');
    var $rollItems = $('.roll-list li');
    var $prev = $('.roll-prev');
    var $next = $('.roll-next');
    var $rollBox = $('.roll-box');
    var $map = $('#map');
    var $backBtn = $('.header .back');
    var $my = $('.my');

    // var $btnsSpan = $('.main .btns span');
    var $btnsBox = $('.main .btns');

    //获取高度
    var height = $(window).height();
    var width = $('.body-in').innerWidth();
    var headerHeight = $header.outerHeight();
    var footerHeight = $footer.outerHeight();
    var rollBoxHeight = $rollBox.outerHeight();


    //设置main和#map的高
    $main.css({'height': height - headerHeight - footerHeight});
    $map.css({'height': height - headerHeight - footerHeight});

    //阻止浏览器默认下拉事件
    document.body.addEventListener('touchmove', function(evt) {
        evt.preventDefault();
    },false);




    var routeMessage = [];
    var routeId = 0;
    var route = null;
    var timer;

    //0.初始化地图
    var map = new BMap.Map('map');
    var point = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point, 11);
    map.enableScrollWheelZoom();
    map.setMinZoom(10);


    //1.获取站点信息
    //获取站点
    $.ajax('/busposition/bus/route/station', {
        type: 'get',
        success: function (data) {
            if (data.success === true){
                routeMessage = data.data;
            }
            else
                alert(data.message);
        },
        error: function (xhr) {
            alert(xhr.status);
        },
        async: false
    });

    //添加站点属性
    for (var i = 0; i < routeMessage.length; i++) {
        var item1 = routeMessage[i];
        item1.stationMarkers = [];
        item1.stationLabels = [];
        item1.stationBothMarker = [];
        item1.stationNum = item1.stationInfo.length;

        item1.trackMarkers = [];
        item1.trackIndex = 0;
        item1.trackRoute = [];
        item1.curArrowMarker = null;
        addStaionMarkerAndLabel(item1);
        addBothMarker(item1);
    }
    //监听地图缩放事件 显示隐藏站点标注
    BMapLib.EventWrapper.addListener(map, 'zoomend', function(){
        //显示图标内容
        // console.log('地图缩放后的起点标注:'+ route.stationMarkers[0].getLabel());
        if (this.getZoom() >= 14) {
            for(var i = 1; i < route.stationMarkers.length - 1; i++){
                route.stationMarkers[i].getLabel().show();
            }
        } else {
            for(var i = 1; i < route.stationMarkers.length - 1; i++){
                route.stationMarkers[i].getLabel().hide();
            }
        }
    });


    //2.初始化路线滚动列表
    for (var i = 0; i < routeMessage.length; i++) {
        var route1 = routeMessage[i];
        //获取路线名和路线id
        var name = route1.routeName;
        var id = route1.routeId;
        //创建选项
        var $li = $(`<li data-id="${id}">${name}</li>`);
        //添加选项
        $scrollList.append($li);
    }
    //获取元素
    $scrollItems = $('.scroll-list li');
    //添加默认选中样式
    $scrollItems.eq(0).addClass('cur');


    //3.添加滚动列表组件
    var script = document.createElement("script");
    script.src = "js/scrollList.js";
    document.body.appendChild(script);

    //4.监听next,prev按钮按下抬起事件
    var $btns = $('.roll-btns div');
    for (var i = 0; i < $btns.length; i++) {
        $btns.get(i).addEventListener('touchstart', function () {
            this.style.backgroundColor = '#ccc';
            this.style.color = '#fff';
        });
        $btns.get(i).addEventListener('touchend', function () {
            this.style.backgroundColor = '#fff';
            this.style.color = '#7c7c7c';
        });
    }

    //5.监听next,prev按钮点击事件
    var minLeft;
    var curStationIndex = 0;

    $next.get(0).onclick = debounce(function () {
        moveRollWindow(-width);
    }, 350);
    $prev.get(0).onclick = debounce(function () {
        moveRollWindow(width);
    }, 350);

    var isFirst = true;
    //6.监听路线滚动列表点击事件
    var $nameSpan = $('.header h3');
    $scrollItems.click(function () {
        //6.1地图相关
        //修改routeId和route对象
        routeId = $(this).data('id');
        //添加routeId cookie
        $.addCookie({
            key: 'routeId',
            value: routeId,
            path: '/'
        });
        for (var i = 0; i < routeMessage.length; i++) {
            var item2 = routeMessage[i];
            if (item2.routeId === routeId) {
                route = item2;
            }
        }
        //修改路线名
        $nameSpan.text(route.routeName);
        //清空图标
        map.clearOverlays();
        //重设站点相关图标
        for (var i = 0; i < route.stationMarkers.length; i++) {
            var stationMarker1 = route.stationMarkers[i];
            bindMarkerAndLabel();
            map.addOverlay(stationMarker1);
            BMapLib.EventWrapper.trigger(map, 'zoomend');
        }
        for (var i = 0; i < route.stationBothMarker.length; i++) {
            var item3 = route.stationBothMarker[i];
            map.addOverlay(item3);
        }
        //设置起点站为中心
        var stations = route.stationInfo;
        var point = new BMap.Point(stations[0].longitude, stations[0].latitude);
        map.setCenter(point);


        //6.2滚动切换窗相关
        //清空滚动切换窗内容
        $rollList.empty();
        //重新设置切换窗内容
        addRollItem();
        //重设rollList宽度
        $rollList.css({'width': width * route.stationNum});
        //修改minLeft值
        minLeft = -width * (route.stationNum - 1);



        //6.3事件相关
        //添加站点点击事件
        addStationClick();

        //重设当前站点索引
        if(!$.getCookie('stationId')){
            curStationIndex = 0;
        } else {
            //如果有stationId
            curStationIndex = parseInt($.getCookie('stationId'));
            $.delCookie('stationId','/');
            showRollBox();
            moveRollWindow(-curStationIndex * width);
        }
        //显示滚动切换窗
        if (!isFirst) {
            showRollBox();
        } else {
            isFirst = false;
        }

        //6.4轨迹相关
        //重新绘制轨迹
        if(route.trackMarkers.length !== 0){
            for (var i = 0; i < route.trackMarkers.length; i++) {
                var line1 = route.trackMarkers[i];
                map.addOverlay(line1);
            }
        }
        //重新添加箭头
        if(route.curArrowMarker)
            map.addOverlay(route.curArrowMarker);

        //发送轨迹请求
        clearInterval(timer);
        var errorCount = 0;
        timer = setInterval(function () {
            // console.log('发送前的索引:'+route.trackIndex);
            $.ajax('/busposition/route/one/position', {
                type: 'post',
                //包头不包尾
                data: {routeId: routeId, startIndex: route.trackIndex},
                success: function (data) {
                    errorCount = 0;
                    if (data.success === true && data.data.trackRoute.length !== 0) {
                        // console.log('发送来的轨迹条数:'+data.data.trackRoute.length);
                        //添加轨迹信息
                        route.trackRoute = route.trackRoute.concat(data.data.trackRoute);
                        //获取新增轨迹图标
                        var newLine = getTrackMarker(routeId);
                        route.trackMarkers.push(newLine);
                        //添加新增轨迹图标
                        map.addOverlay(newLine);
                        //添加箭头
                        addArrow();
                    } else {
                        console.log(data.message);
                        // clearInterval(timer);
                    }
                },
                error: function (xhr) {
                    errorCount++;
                    if(errorCount === 10){
                        alert('连接中断！');
                        clearInterval(timer);
                    }
                }
            });
        }, 3000);
    });
    //初始化
    if(!$.getCookie('routeId')){
        $scrollItems.eq(0).trigger('click');
    } else {
        var id1 = parseInt($.getCookie('routeId'));
        // console.log(id1);
        for(var i = 0; i < $scrollItems.length; i++){
            if(parseInt($scrollItems.eq(i).data('id')) === id1){
                // console.log($scrollItems.eq(i));
                $scrollItems.eq(i).trigger('click');
            }
        }
    }

    //7.监听地图点击事件
    BMapLib.EventWrapper.addListener(map, 'click', function () {
        hideRollBox();
        map.removeOverlay(curStationMarker);
    }, false);

    //8.按钮组相关
    //监听按钮组按下抬起事件
    var $btnsSpan = $btnsBox.find('span');
    // console.log($btnsSpan);
    for(var i = 0; i < $btnsSpan.length; i++){
        $btnsSpan.get(i).addEventListener('touchstart',function(){
            $(this).css({
                'backgroundColor':'#ccc',
            })
        },false);
        $btnsSpan.get(i).addEventListener('touchend',function(){
            $(this).css({
                'backgroundColor':'#fff',
            })
        },false);
    }
    //监听放大按钮点击事件
    var $enlargeBtn = $('.main .enlarge-btn');
    $enlargeBtn.click(function(){
        var zoom = map.getZoom() + 1;
        zoom = zoom >= 19 ? 19 : zoom;
        map.setZoom(zoom);
    });
    //监听缩小按钮点击事件
    var $narrowBtn = $('.main .narrow-btn');
    $narrowBtn.click(function(){
        var zoom = map.getZoom() - 1;
        zoom = zoom <= 10 ? 10 : zoom;
        map.setZoom(zoom);
    });
    //监听位置按钮点击事件
    var $posBtn = $('.main .pos-btn');
    $posBtn.click(function(){
        var point;
        //没有轨迹移动到起点
        if(route.trackIndex === 0){
            point = route.stationBothMarker[0].getPosition();
            map.panTo(point);
        } else {
            //有位置移动到箭头
            point = route.curArrowMarker.getPosition();
            map.panTo(point);
        }
    });

    //9.添加站点图标点击事件
    var curStationMarker = null;
    function addStationClick() {
        //站点点击事件
        for (var i = 0; i < route.stationMarkers.length; i++) {
            stationChange(route.stationMarkers[i],route.stationMarkers[i].index);
        }
        //起点点击事件
        stationChange(route.stationBothMarker[0],route.stationBothMarker[0].index);
        //终点点击事件
        stationChange(route.stationBothMarker[1],route.stationBothMarker[1].index);
    }
    function stationChange(marker,index){
        BMapLib.EventWrapper.addListener(marker, 'click', function (event) {
            console.log(event);
            //显示滚动切换窗
            showRollBox();
            //添加地标
            addDibiaoMarker(index);
            //移动滚动切换窗
            var dis = (curStationIndex - index) * width;
            moveRollWindow(dis);
            //获取点击标注索引
            curStationIndex = index;
            //禁止冒泡
            event.domEvent.stopPropagation();
        }, false);
    }
    //10.监听列表按钮点击事件
    $link.click(function(){
        location.href = './stationDetails.html';
    });
    //11.监听回退按钮点击事件
    $backBtn.click(function(){
        history.go(-1);
    });
    //12.监听我的按钮点击事件
    $my.click(function(){
        location.href = './wode.html';
    });

    //添加站点图标和站点标注
    function addStaionMarkerAndLabel(route) {
        var stations = route.stationInfo;
        // console.log(stations);
        for (var i = 0; i < stations.length; i++) {
            var station = stations[i];
            //添加站点图标
            var point = new BMap.Point(station.longitude, station.latitude);
            // console.log(point);
            var options = {
                point: point,
                iconUrl: './images/zhandian.png',
                sizeX: 12,
                sizeY: 12,
                imageOffsetX: -12,
                imageOffsetY: -139,
                offsetX: 6,
                offsetY: 6,
                zindex: 1
            };
            var stationMarker = addMarker(options);
            route.stationMarkers.push(stationMarker);
            //添加站点标注
            stationMarker.index = i; //添加索引
            var size = new BMap.Size(15, -5);
            var label = new BMap.Label(station.stationName, {
                offset: size,
                position: point
            });
            label.setStyle({
                border: 'none',
                backgroundColor: 'rgba(0,0,0,0)',
                color: '#333'
            });
            route.stationLabels.push(label);
            // stationMarker.setLabel(label);
        }
    }

    //绑定站点图标和站点标注
    function bindMarkerAndLabel(){
        for(var i = 0; i < route.stationMarkers.length; i++){
            route.stationMarkers[i].setLabel(route.stationLabels[i]);
        }
    }

    //添加起点终点图标
    function addBothMarker(route) {
        var stations = route.stationInfo;
        var startPoint = new BMap.Point(stations[0].longitude, stations[0].latitude);
        var endPoint = new BMap.Point(stations[stations.length - 1].longitude, stations[stations.length - 1].latitude);
        var startOptions = {
            point: startPoint,
            iconUrl: './images/poi-marker.png',
            sizeX: 26,
            sizeY: 37,
            imageOffsetX: -11,
            imageOffsetY: -4,
            offsetX: 13,
            offsetY: 34,
            zindex: 3
        };
        var startMarker = addMarker(startOptions);
        startMarker.index = 0;
        route.stationBothMarker.push(startMarker);
        var endOptions = {
            point: endPoint,
            iconUrl: './images/poi-marker.png',
            sizeX: 26,
            sizeY: 37,
            imageOffsetX: -112,
            imageOffsetY: -4,
            offsetX: 13,
            offsetY: 34,
            zindex: 3
        };
        var endMarker = addMarker(endOptions);
        endMarker.index = stations.length - 1;
        route.stationBothMarker.push(endMarker);
    }

    //添加图标
    //point,iconUrl,sizeX,sizeY,offsetX,offsetY,rotate = 0
    function addMarker(options) {
        options.rotate = options.rotate || 0;
        options.imageOffsetX = options.imageOffsetX || 0;
        options.imageOffsetY = options.imageOffsetY || 0;
        options.zindex = options.zindex || 0;
        var imgOffset = new BMap.Size(options.imageOffsetX,options.imageOffsetY);
        var imgSize = new BMap.Size(options.sizeX, options.sizeY);
        var imgAnchor = new BMap.Size(options.offsetX, options.offsetY);
        var myIcon = new BMap.Icon(options.iconUrl, imgSize, {anchor: imgAnchor,imageOffset:imgOffset});
        var marker = new BMap.Marker(options.point, {icon: myIcon, rotation: options.rotate});
        // marker.setZindex();
        marker.zIndex =options.zindex;
        map.addOverlay(marker);
        // marker.setZindex(options.zindex);
        return marker;
    }

    //创建滚动切换窗选项
    function addRollItem() {
        var stations = route.stationInfo;
        for (var i = 0; i < stations.length; i++) {
            var station = stations[i];
            var $rollItem = $(`<li>
                        <h3>${station.stationName}</h3>
                        <p>最晚到站:<span>${station.departTime}</span></p>
                    </li>`);
            $rollItem.css({'width': width});
            $rollList.append($rollItem);
        }
    }

    //移动滚动切换窗口
    function moveRollWindow(dis) {
        var oldLeft = parseInt($rollList.css('left'));
        var left = oldLeft + dis;
        left = left >= minLeft ? left : minLeft;
        left = left <= 0 ? left : 0;
        $rollList.stop().animate({'left': left + 'px'}, 300, 'swing', function () {
            var left = parseInt($rollList.css('left'));
            curStationIndex = -left / width;
            // console.log(curStationIndex);
            //添加地标
            addDibiaoMarker(curStationIndex);
            //设置中心
            var point = route.stationMarkers[curStationIndex].getPosition();
            map.panTo(point);
            // if (map.getZoom() < 15)
            //     map.setZoom(15);
        });
    }

    //添加地标
    function addDibiaoMarker(index) {
        var stationMarkers = route.stationMarkers;
        if (index !== 0 && index !== stationMarkers.length - 1) {
            map.removeOverlay(curStationMarker);
            var point = stationMarkers[index].getPosition();
            var options = {
                point: point,
                iconUrl: './images/poi-marker.png',
                sizeX: 26,
                sizeY: 37,
                imageOffsetX: -61,
                imageOffsetY: -4,
                offsetX: 13,
                offsetY: 34,
                zindex: 3
            };
            curStationMarker = addMarker(options);
        } else {
            map.removeOverlay(curStationMarker);
        }
    }

    //添加箭头
    function addArrow() {
        //如果没有轨迹
        if (route.trackId === 0) {
            //有箭头就删除
            route.curArrowMarker && map.removeOverlay(route.curArrowMarker);
            //返回
            return null;
        }
        //如果有箭头，删除
        if (route.curArrowMarker)
            map.removeOverlay(route.curArrowMarker);
        //添加箭头
        var track = route.trackRoute[route.trackIndex];
        var point = new BMap.Point(track.longitude, track.latitude);
        var options = {
            point: point,
            iconUrl: './images/bashi.png',
            sizeX: 27,
            sizeY: 27,
            offsetX: 13.5,
            offsetY: 13.5,
        };
        route.curArrowMarker = addMarker(options);
    }

    //获取轨迹标注
    //参数表：起始索引，结束索引
    //返回：轨迹标注
    function getTrackMarker() {
        //1.获取轨迹点区间
        var startIndex = route.trackIndex;
        var endIndex = route.trackRoute.length - 1;
        //2.将轨迹信息转为轨迹点数组
        var tracks = route.trackRoute;
        var trackPoints = [];
        for (var i = startIndex; i <= endIndex; i++) {
            var point = new BMap.Point(tracks[i].longitude, tracks[i].latitude);
            trackPoints.push(point);
        }
        //3.修改起始索引
        route.trackIndex = endIndex;
        //4.创建轨迹图标并返回
        return new BMap.Polyline(trackPoints, {
            strokeColor: "#534cef",
            strokeWeight: 3,
            strokeOpacity: 1
        });
        // return line2;
    }

    //显示滚动切换窗
    function showRollBox(){
        if ($rollBox.css('display') === 'none') {
            var triggerBottom = parseInt($rollBox.css('bottom')) + footerHeight;
            var targetBottom = rollBoxHeight - footerHeight + parseInt($btnsBox.css('bottom'));
            $rollBox.css({'display':'block'});
            $rollBox.stop().animate({'bottom':0},'normal','linear');
            var timer = setInterval(function(){
                if(parseInt($rollBox.css('bottom'))>=triggerBottom){
                    clearInterval(timer);
                    $btnsBox.stop().animate({'bottom':targetBottom},'fast','linear');
                }
            },300);
        }
    }

    //隐藏滚动切换窗
    function hideRollBox(){
        if ($rollBox.css('display') === 'block') {
            var triggerBottom = rollBoxHeight - footerHeight;
            // console.log(triggerBottom);
            $btnsBox.stop().animate({'bottom':15},'normal','linear');
            // $rollBox.stop().animate({'bottom':-rollBoxHeight},'normal','linear');
            var timer = setInterval(function(){
                if(parseInt($btnsBox.css('bottom'))<=triggerBottom){
                    // console.log(parseInt($btnsBox.css('bottom')));
                    clearInterval(timer);
                    $rollBox.stop().animate({'bottom':-rollBoxHeight},'fast','linear',function(){
                        $rollBox.css({'display':'none'});
                    });
                }
            },300);
        }
    }
}

//异步加载百度api
function loadScript() {
    var script = document.createElement("script");
    script.src = "http://api.map.baidu.com/api?v=2.0&ak=Hn8yCUyLlsPxzhCIdG8wETGiGtViLCHI&callback=initialize";
    document.body.appendChild(script);
}

//在所有内容加载完毕后执行
window.onload = loadScript;