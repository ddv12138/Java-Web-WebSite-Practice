window.onload = function () {
    let cityStr = "武汉";
    window.countScale = 10;
    $.post("/city/getCityInfo", {cityName: cityStr}, function (cityData, status) {
        cityData = JSON.parse(cityData.data).results[0];
        var map = new AMap.Map("container", {
            mapStyle: 'amap://styles/1de318cbb8d12c02303a22c550b9ccc9',
            viewMode: '3D',
            pitch: 15,
            resizeEnable: true,
            center: [cityData.location.lng, cityData.location.lat],
            zoom: 12,
        });
        window.map = map;
        $.post("/community/list", {cityName: cityStr}, function (commData) {
            let points = [];
            let height = 0;
            let viewMode = '3D';
            commData = commData.data;
            commData.forEach(function (loc) {
                if ((parseFloat(loc.unit_price) / window.countScale) > height) {
                    height = (parseFloat(loc.unit_price) / window.countScale);
                }
                points.push({
                    lng: loc.gaode_lng,
                    lat: loc.gaode_lat,
                    count: parseFloat(loc.unit_price) / window.countScale,
                    lnglat: loc.gaode_lng + "," + loc.gaode_lat
                });
            });
            console.log(height);
            initPointAdcode(points, height, viewMode);
        })
    })
};

function initPointAdcode(points, height, viewMode) {
    let mapLayer = new Loca.HexagonLayer({
        map: window.map,
        visible: true,
        zIndex: 777,
        // fitView: true,
        eventSupport: true,
    });
    mapLayer.setData(points, {
        type: 'json',
        lnglat: function (obj) {
            return [parseFloat(obj.value.lng), parseFloat(obj.value.lat)];
        },
        value: 'count'
    });
    mapLayer.setOptions({
        unit: 'meter',
        mode: 'mean',
        style: {
            radius: 100,
            opacity: 0.9,
            gap: 1,
            height: [0, height],
            // color: {
            //     scale: 'quantize',
            //     value: ['rgb(255,237,160)', 'rgb(254,217,118)', 'rgb(254,178,76)', 'rgb(253,141,60)', 'rgb(252,78,42)', 'rgb(227,26,28)', 'rgb(189,0,38)', ],
            // }
        }
    }).render();
    // mapLayer.setFitView();
    // 事件 legendupdate: 图例数据更新完成回调此函数
    mapLayer.on('legendupdate', function (ev) {
        var colorLegend = ev.colorLegend;

        var legends = colorLegend.map(item => {
            // color 为 gradient 传入的格式
            return `<li class="color-item" style="background-color: ${item.color}"></li>`
        });

        var ranges = colorLegend.map((item, index) => {
            // range 可能为小数，可以自行取整计算
            console.log(item.range);
            item.range[0] = Math.ceil(item.range[0] * window.countScale);
            item.range[1] = Math.ceil(item.range[1] * window.countScale);

            if (index == colorLegend.length - 1) {
                return `<li class="label-item">${item.range[0]}</li><li class="label-item">${item.range[1]}</li>`;
            }
            return `<li class="label-item">${item.range[0]}</li>`;
        });

        document.getElementById('legend-color').innerHTML = legends.join('');
        document.getElementById('legend-label').innerHTML = ranges.join('');
    });
    mapLayer.on('mousemove', function (ev) {
        updateInfo(ev);
    });
    onHeight(viewMode, mapLayer, height);
    // initContourLayer(points);
}

function updateInfo(ev) {
    console.log(ev);
    let $val = document.getElementById('val');
    let $lngLat = document.getElementById('lng-lat');
    $val.innerText = ev.value;
    $lngLat.innerText = ev.lngLat.valueOf();
}

function initContourLayer(points) {
    var contourLayer = new Loca.ContourLayer({
        shape: 'isoline',
        map: window.map
    });
    contourLayer.setData(points, {
        type: 'json',
        value: 'count'
    });
    contourLayer.setOptions({
        smoothNumber: 3,
        threshold: 50,
        interpolation: {
            step: 1000,
            effectRadius: 800,
        },
    });
    contourLayer.render();
}

function onHeight(viewMode, gridLayer, height) {
    var heightBtn = document.querySelector('#heightBtn');

    heightBtn.onclick = function () {
        viewMode = viewMode === '2D' ? '3D' : '2D';
        heightBtn.innerHTML = viewMode;

        gridLayer.setOptions({
            style: {
                height: [0, viewMode === '2D' ? 0 : height]
            }
        }).render();
    }
}