(function (window) {
    function MapUtil(map, routeMessage, trackMessage) {
        return new MapUtil.prototype.init(map, routeMessage, trackMessage);
    }

    MapUtil.prototype = {
        constructor: MapUtil,
        map: null,
        routeMessage: [],
        trackMessage: [],
        routeIndex: 0,
        polyline: null,
        row: null,
        stations: [],
        stationPoints: [],
        tracks: [],
        trackPoints: [],
        stationNames: [],
        stationMarkers: [],
        stationInfoWindows: [],
        test: 1,
        isRealTimePage: true,
        //初始化
        init: function (map, routeMessage, trackMessage) {
            let $this = this;
            //创建地图实例对象
            $this.map = new BMap.Map(map);
            //初始化路线数据
            $this.routeMessage = routeMessage;

            //初始化路线索引
            $this.routeIndex = 0;
            //站点解析
            $this.analyzeStation();
            //站点坐标解析
            let qqPoints = $this.analyzePosition($this.stations);
            for(let i = 0; i < qqPoints.length; i++){
                let obj = $this.qqMapTransBMap(qqPoints[i].lng,qqPoints[i].lat);
                let bdPoint = new BMap.Point(obj.lng,obj.lat);
                $this.stationPoints.push(bdPoint);
            }
            this.map.centerAndZoom($this.stationPoints[0], 15);
            //站点名称解析
            $this.analyzeRouteName();
            //初始化站点标注
            $this.addRouteMarkers();
            //轨迹数据判断
            if(trackMessage !== null){
                //初始化轨迹数据
                $this.trackMessage = trackMessage;
                //轨迹解析
                $this.analyzeTrack();
                //轨迹坐标解析
                let qqTracks = $this.analyzePosition($this.tracks);
                for(let i = 0; i < qqTracks.length; i++){
                    let obj = $this.qqMapTransBMap(qqTracks[i].lng,qqTracks[i].lat);
                    let bdPoint = new BMap.Point(obj.lng,obj.lat);
                    $this.trackPoints.push(bdPoint);
                }
                //初始化轨迹
                $this.addRoute();
                //初始化箭头角度
                let $trackPoints = $this.trackPoints;
                let len = $trackPoints.length;
                let rotate = $this.calDegree($trackPoints[len - 1], $trackPoints[len - 2]);
                //初始化箭头标注
                $this.addRow($trackPoints[len - 1], rotate);
            } else {
                $this.isRealTimePage = false;
            }
        },

        //---初始化方法---
        // 腾讯坐标转百度坐标
        qqMapTransBMap: function (lng, lat) {
            let x_pi = 3.14159265358979324 * 3000.0 / 180.0;
            let x = lng;
            let y = lat;
            let z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
            let theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
            let lngs = z * Math.cos(theta) + 0.0065;
            let lats = z * Math.sin(theta) + 0.006;
            return {lng: lngs, lat: lats}
        },

        //站点解析
        analyzeStation: function () {
            let $this = this;
            $this.stations = $this.routeMessage[$this.routeIndex].stationInfo;
        },
        //轨迹解析
        analyzeTrack: function () {
            let $this = this;
            $this.tracks = $this.trackMessage[$this.routeIndex].trackRoute;
        },
        //位置解析
        analyzePosition: function (aPoints) {
            let points = [];
            for (let i = 0; i < aPoints.length; i++) {
                let x = aPoints[i].longitude;
                let y = aPoints[i].latitude;
                let point = new BMap.Point(x, y);
                points.push(point);
            }
            return points;
        },
        //站点名称解析
        analyzeRouteName: function () {
            let $this = this;
            let $stations = $this.stations;
            let $name = $this.stationNames;
            for (let i = 0; i < $stations.length; i++) {
                $name.push($stations[i].stationName);
            }
        },
        //添加自定义标注
        addMarker: function (point, iconUrl, sizeX, sizeY, offsetX, offsetY, rotate = 0) {
            let $this = this;
            let $map = $this.map;
            let myIcon = new BMap.Icon(iconUrl, new BMap.Size(sizeX, sizeY), {anchor: new BMap.Size(offsetX, offsetY)});
            let marker = new BMap.Marker(point, {icon: myIcon});
            marker.setRotation(rotate);
            $map.addOverlay(marker);
            return marker;
        },
        //添加站点标注
        addRouteMarkers: function () {
            let $this = this;
            let $markers = $this.stationMarkers;
            let $points = $this.stationPoints;
            let marker;
            //画起点
            $this.addMarker($points[0], "./images/qidian.png", 32, 32, 16, 32);
            marker = $this.addMarker($points[0], "./images/yuan.png", 16, 16, 8, 8);
            $markers.push(marker);
            //画站点
            for (let i = 1; i < $points.length - 1; i++) {
                marker = $this.addMarker($points[i], "./images/ditu-yuan.png", 16, 16, 8, 8);
                $markers.push(marker);
            }
            //画终点
            $this.addMarker($points[$points.length - 1], "./images/zhongdian.png", 32, 32, 16, 32);
            marker = $this.addMarker($points[$points.length - 1], "./images/yuan.png", 16, 16, 8, 8);
            $markers.push(marker);
        },
        //添加轨迹
        addRoute: function () {
            let $this = this;
            let $map = $this.map;
            let $trackPoints = $this.trackPoints;
            $map.setCenter($trackPoints[0]);
            $this.polyline = new BMap.Polyline($trackPoints, {
                strokeColor: "red",
                strokeWeight: 3,
                strokeOpacity: 1
            });
            $map.addOverlay($this.polyline);
        },
        //计算角度
        calDegree: function (p1, p2) {
            //计算弧度
            let dx = p1.lng - p2.lng;
            let dy = p1.lat - p2.lat;
            let radian = Math.atan2(dy, dx);
            //转换为角度
            let degree = radian * (180 / Math.PI);
            //误差调整
            degree -= 45;
            return -degree;
        },
        //添加箭头
        addRow: function (point, rotate) {
            let $this = this;
            $this.row = $this.addMarker(point, './images/jiantou.png', 16, 16, 8, 8, rotate);
        },
        //---响应式方法---
        //添加平移控件
        addNavigationControl: function () {
            let $this = this;
            let $map = this.map;
            let navigationControl = new BMap.NavigationControl();
            $map.addControl(navigationControl);
        },
        //设置中心点
        setCenter: function (point) {
            let $this = this;
            let $map = $this.map;
            $map.panTo(point);
        },
        //创建信息窗口
        getInfoWindow: function (stationMessage) {
            let $this = this;
            let $routeMessage = $this.routeMessage;
            let oTitle = $("<div class='infoTitle'><span>" + $routeMessage[$this.routeIndex].routeName + "</span><small>" + stationMessage.stationName + "<small/></div>").get(0);
            let oContent = $("<div class='infoContent'><p>最晚发车时间：" + stationMessage.deadTime + "</p><p>上一站：" + stationMessage.prevStation + "</p><p>下一站：" + stationMessage.nextStation + "</p></div>").get(0);
            let infoWindow = new BMap.InfoWindow();
            infoWindow.setWidth(200);
            infoWindow.setHeight(100);
            infoWindow.setTitle(oTitle);
            infoWindow.setContent(oContent);
            return infoWindow;
        },
        //创建站点信息窗口
        createStationInfoWindows: function () {
            let $this = this;
            let $stations = $this.stations;
            for (let i = 0; i < $stations.length; i++) {
                let infoWindow = $this.getInfoWindow($stations[i]);
                $this.stationInfoWindows.push(infoWindow);
            }
        },
        //打开窗口
        openWindow: function (index) {
            let $this = this;
            $this.map.openInfoWindow($this.stationInfoWindows[index], $this.stationPoints[index]);
        },
        //添加站点信息窗口点击事件
        addStationMarkersClick: function () {
            let $this = this;
            let $map = $this.map;
            let $windows = $this.stationInfoWindows;
            let $points = $this.stationPoints;
            let $markers = $this.stationMarkers;
            for (let i = 0; i < $markers.length; i++) {
                $markers[i].addEventListener('click', function () {
                    $this.openWindow(i);
                }, false);
            }
        },

        //---其它控件方法---
        //初始化路线下拉列表
        initialDropdown: function ($select) {
            //创建下拉框选项
            let $this = this;
            let $message = $this.routeMessage;
            for (let i = 0; i < $message.length; i++) {
                let $option = $("<option>" + $message[i].routeName + "</option>");
                $select.append($option);
            }
        },
        //监听电脑路线下拉选择
        addDropdownChangePC: function ($select, $sidenav, selector) {
            let $this = this;
            let $map = $this.map;
            $select.change(function () {
                //切换线路
                $this.routeIndex = this.selectedIndex;
                //清空站点信息
                $this.stations = [];
                $this.stationPoints = [];
                $this.stationNames = [];
                $this.stationMarkers = [];
                $this.stationInfoWindows = [];
                //站点解析
                $this.analyzeStation();
                //站点坐标解析
                let qqPoints = $this.analyzePosition($this.stations);
                for(let i = 0; i < qqPoints.length; i++){
                    let obj = $this.qqMapTransBMap(qqPoints[i].lng,qqPoints[i].lat);
                    let bdPoint = new BMap.Point(obj.lng,obj.lat);
                    $this.stationPoints.push(bdPoint);
                }
                //站点名称解析
                $this.analyzeRouteName();
                //重构站点标注
                $map.clearOverlays();
                $this.addRouteMarkers();
                //重构站点信息
                $this.createStationInfoWindows();
                $this.addStationMarkersClick();
                //重构侧边栏
                $this.initialSidenav($sidenav, selector);
                if($this.isRealTimePage === true) {
                    $this.tracks = [];
                    $this.trackPoints= [];

                    //轨迹解析
                    $this.analyzeTrack();
                    //轨迹坐标解析
                    let qqTracks = $this.analyzePosition($this.tracks);
                    for(let i = 0; i < qqTracks.length; i++){
                        let obj = $this.qqMapTransBMap(qqTracks[i].lng,qqTracks[i].lat);
                        let bdPoint = new BMap.Point(obj.lng,obj.lat);
                        $this.trackPoints.push(bdPoint);
                    }
                    //初始化轨迹
                    $this.addRoute();
                    //初始化箭头角度
                    let $trackPoints = $this.trackPoints;
                    let len = $trackPoints.length;
                    let rotate = $this.calDegree($trackPoints[len - 1], $trackPoints[len - 2]);
                    //初始化箭头标注
                    $this.addRow($trackPoints[len - 1], rotate);
                }
            });

        },
        //监听手机路线下拉选择
        addDropdownChangePhone: function ($select, $pop, windowHeight) {
            let $this = this;
            let $map = $this.map;
            $select.change(function () {
                //切换线路
                $this.routeIndex = this.selectedIndex;
                //清空站点信息
                $this.stations = [];
                $this.stationPoints = [];
                $this.stationNames = [];
                $this.stationMarkers = [];
                $this.stationInfoWindows = [];
                //站点解析
                $this.analyzeStation();
                //站点坐标解析
                let qqPoints = $this.analyzePosition($this.stations);
                for(let i = 0; i < qqPoints.length; i++){
                    let obj = $this.qqMapTransBMap(qqPoints[i].lng,qqPoints[i].lat);
                    let bdPoint = new BMap.Point(obj.lng,obj.lat);
                    $this.stationPoints.push(bdPoint);
                }
                //站点名称解析
                $this.analyzeRouteName();
                //重构站点标注
                $map.clearOverlays();
                $this.addRouteMarkers();
                $map.panTo($this.stationPoints[0]);
                //隐藏信息弹窗
                $pop.animate({bottom: -70}, 1000);
                //添加手机站点信息弹窗点击事件
                $this.addStationMarkersPop($pop, windowHeight);
                if($this.isRealTimePage === true) {
                    //轨迹解析
                    $this.analyzeTrack();
                    //轨迹坐标解析
                    $this.trackPoints = $this.analyzePosition($this.tracks);
                    //初始化轨迹
                    $this.addRoute();
                    //初始化箭头角度（待做）
                    let $trackPoints = $this.trackPoints;
                    let len = $trackPoints.length;
                    let rotate = $this.calDegree($trackPoints[len - 1], $trackPoints[len - 2]);
                    //初始化箭头标注
                    $this.addRow($trackPoints[len - 1], rotate);
                }
                /*//轨迹解析
                $this.analyzeTrack();
                //轨迹坐标解析
                $this.trackPoints = $this.analyzePosition($this.tracks);
                //初始化轨迹
                $this.addRoute();
                //初始化箭头角度（待做）

                //初始化箭头标注
                $this.addRow($this.trackPoints[$this.trackPoints.length - 1], 0);*/
            });

        },
        //初始化站点下拉列表
        initialStationDropdown: function ($select) {
            //创建下拉框选项
            let $this = this;
            let $names = $this.stationNames;
            for (let i = 0; i < $names.length; i++) {
                let $option = $("<option>" + $names[i] + "</option>");
                $select.append($option);
            }
        },
        //监听站点下拉列表选择
        addStationDropdownChange: function($select){
            let $this = this;
            let $map = $this.map;
            let $points = $this.stationPoints;
            let $markers = $this.stationMarkers;
            $select.change(function(e1){
                //获取index
                let index = $select.get(0).selectedIndex;
                $map.panTo($points[index]);
                BMapLib.EventWrapper.trigger($markers[index], 'click')
            })
        },
        //初始化刷新按钮
        initialRefreshBtn: function ($refresh) {
            let $this = this;
            let $map = $this.map;
            let $b = $refresh.find("b");
            let timer;
            $refresh.one('click', refreshClick);

            //点击事件
            function refreshClick() {
                $refresh.addClass('disabled');
                $b.text(9);
                //jQuery中的setInterval中的函数不能右参数列表!!
                timer = setInterval(setCountDown, 1000);
                // console.log($this.polyline);
                //获取实时数据
                $.ajax("http://47.95.14.126:8080/busposition/bus/track", {
                    type: "get",
                    success: function (data) {
                        $this.test = 2;
                        //移除轨迹
                        $map.removeOverlay($this.polyline);
                        //移除箭头
                        $map.removeOverlay($this.row);
                        //获取轨迹数据
                        $this.trackMessage = data.data;
                        //清空轨迹点
                        $this.trackPoints = [];
                        //轨迹解析
                        $this.analyzeTrack();
                        //轨迹坐标解析
                        let qqTracks = $this.analyzePosition($this.tracks);
                        for(let i = 0; i < qqTracks.length; i++){
                            let obj = $this.qqMapTransBMap(qqTracks[i].lng,qqTracks[i].lat);
                            let bdPoint = new BMap.Point(obj.lng,obj.lat);
                            $this.trackPoints.push(bdPoint);
                        }
                        //初始化轨迹
                        $this.addRoute();
                        //初始化箭头角度
                        let $trackPoints = $this.trackPoints;
                        let len = $trackPoints.length;
                        let rotate = $this.calDegree($trackPoints[len - 1], $trackPoints[len - 2]);
                        //初始化箭头标注
                        $this.addRow($trackPoints[len - 1], rotate);


                        /*//轨迹解析
                        $this.analyzeTrack();
                        //轨迹坐标解析
                        $this.trackPoints = $this.analyzePosition($this.tracks);
                        //初始化轨迹
                        $this.addRoute();
                        //初始化箭头角度
                        let $trackPoints = $this.trackPoints;
                        let len = $trackPoints.length;
                        let rotate = $this.calDegree($trackPoints[len - 1], $trackPoints[len - 2]);
                        //初始化箭头标注
                        $this.addRow($trackPoints[len - 1], rotate);*/
                    },
                    async: false
                });
            }

            //设置倒计时
            function setCountDown() {
                let seconds = +$b.text() - 1;
                if (seconds === 0) {
                    clearInterval(timer);
                    $refresh.removeClass('disabled');
                    $refresh.one('click', refreshClick);
                }
                $b.text(seconds);
            }
        },
        //初始化侧边栏
        initialSidenav: function ($sidenav, selector) {
            let $this = this;
            let $map = $this.map;
            let $message = $this.routeMessage;
            let $stationNames = $this.stationNames;
            let $points = $this.stationPoints;
            //清空元素
            $sidenav.empty();
            //创建并添加站点头
            let $sideNavTitle = $this.createSideNavTitle($message[$this.routeIndex].routeName);
            $sidenav.append($sideNavTitle);
            //创建并添加站点
            for (let i = 0; i < $stationNames.length; i++) {
                let $stationItem = $this.createSideNavItem($stationNames[i]);
                $sidenav.append($stationItem);
            }
            //设置中心点
            $map.panTo($points[0]);
            //添加点击事件
            $sidenav.delegate($(selector), 'click', function (e) {
                //5.1获取索引
                let itemIndex = $(e.target).index() - 1;
                //5.2设置中心点
                $map.panTo($points[itemIndex]);
                //5.3弹出站点信息
                $this.openWindow(itemIndex);
            });
        },
        //创建站点头
        createSideNavTitle: function (routeName) {
            let $title = $('<a href="#" class="sidenav-title list-group-item active">\n' +
                '                <i class="iconfont icon-luxian1"></i>\n' +
                '                <span>' + routeName + '</span>\n' +
                '            </a>');
            return $title;
        },
        //创建站点
        createSideNavItem: function (stationName) {
            let $item = $('<a href="#" class="list-group-item sidenav-item">\n' +
                '                <i></i>\n' +
                '                ' + stationName + '\n' +
                '            </a>');
            return $item;
        },

        //添加手机站点信息弹窗点击事件
        addStationMarkersPop: function ($pop, windowHeight) {
            let $drop = $pop.find('.station-drop');
            let $this = this;
            let $markers = $this.stationMarkers;
            let $windows = $this.stationInfoWindows;
            let $points = $this.stationPoints;
            let $stations = $this.stations;
            let $message = $this.routeMessage;

            let $title = $pop.find('h4');
            let $cur = $pop.find('.cur');
            let $deadtime = $pop.find('.deadtime');
            let $prev = $pop.find('.prev');
            let $next = $pop.find('.next');

            for (let i = 0; i < $markers.length; i++) {
                $markers[i].addEventListener('click', function () {
                    //设置内容
                    $title.text($message[$this.routeIndex].routeName);
                    $cur.text($stations[i].stationName);
                    $deadtime.text($stations[i].deadTime);
                    $prev.text($stations[i].prevStation);
                    $next.text($stations[i].nextStation);
                    //弹出窗口
                    $pop.stop().animate({bottom: 50}, 500);
                    //收回窗口
                }, false);
            }
            //监听收回按钮点击事件
            $drop.click(function () {
                $pop.stop().animate({bottom: -70}, 500);
            });

            /*$drop.mousedown(function(e1){
                let oldY = e1.pageY;
                console.log(oldY);
                $drop.mousemove(function(e2){
                    let move = e2.pageY - e2.oldY;
                    let bt;
                    if(move < 60 && move > 0){
                        bt = 50 - move;
                        $pop.css({bottom: bt});
                    }
                });
                $drop.mouseup(function(){
                    if($pop.prop('bottom') > -70){
                        $pop.stop().animate({bottom: -70},500)
                    }
                });
            })*/
        },
    };
    MapUtil.prototype.init.prototype = MapUtil.prototype;
    window.MapUtil = MapUtil;
})(window);