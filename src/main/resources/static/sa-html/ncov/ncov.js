function loadlayer() {
    var colors = {};
    sa.ajax2("/ncov/world", null, function (arg) {
        if (arg && arg.state) {
            let data = arg.data;
            console.log(data);
            var gradient = ["#ccc", "#ffe4d9", "#ffcab3", "#ffaa85",
                "#ff7b69", "#cc2929", "#8c0d0d", "#660208"];
            console.log(gradient);//控制台输出
            let dataMap = {};
            let dataSet = {};
            for (let i = 0; i < data.length; i++) {
                let color = gradient[0];
                let count = parseInt(data[i].confirmed);
                if (count <= 9) {
                    color = gradient[1];
                } else if (count <= 49) {
                    color = gradient[2];
                } else if (count <= 99) {
                    color = gradient[3];
                } else if (count <= 499) {
                    color = gradient[4];
                } else if (count <= 999) {
                    color = gradient[5];
                } else if (count <= 9999) {
                    color = gradient[6];
                } else {
                    color = gradient[7];
                }
                dataMap[data[i].countrycode] = color;
                dataSet[data[i].countrycode] = data[i];
            }
            var getColorBySOC = function (SOC) {
                //可按需改为其他实现
                if (SOC) {
                    return dataMap[SOC] ? dataMap[SOC] : "#ccc";
                } else {
                    return 'rgb(200,200,240)'

                }

            };
            const disWorld = new AMap.DistrictLayer.World({
                zIndex: 10,
                styles: {
                    // 颜色格式: #RRGGBB、rgba()、rgb()、[r,g,b,a]
                    // 'nation-stroke':function(props){
                    //     //props:{type:Nation_Border_China/Nation_Border_Foreign}
                    //     if(props.type=='Nation_Border_China'){
                    //         return 'red'
                    //     }else{
                    //         return 'white'
                    //     }
                    // },
                    'coastline-stroke': [0.8, 0.63, 1, 1],
                    'nation-stroke': 'rgba(20, 20, 233, 0.6)',
                    'fill': function (props) {
                        //props:{NAME_CHH,NAME_ENG,SOC}
                        return getColorBySOC(props.SOC);
                    }
                }
            });
            var nationFill = 'rgba(20, 120, 230, 1)';
            var lastProps = null;
            map.on('mousemove', function (ev) {
                const px = ev.pixel;
                // 拾取所在位置的行政区
                const props = disWorld.getDistrictByContainerPos(px);
                if (props) {
                    lastProps = props;
                    const SOC = props.SOC;
                    if (SOC) {
                        // 重置行政区样式
                        disWorld.setStyles({
                            // 国境线
                            'nation-stroke': 'rgba(20, 20, 233, 1)',
                            'fill': function (props) {
                                return props.SOC == SOC ? nationFill : getColorBySOC(props.SOC);
                            }
                        });
                        updateInfo(dataSet[props.SOC], props);
                    }
                } else {
                    disWorld.setStyles({
                        // 国境线
                        //nation-stroke': nationStroke,
                        // 海岸线
                        'nation-stroke': 'rgba(20, 20, 233, 1)',
                        'fill': function (props) {
                            //props:{NAME_CHH,NAME_ENG,SOC}
                            return getColorBySOC(props.SOC)
                        }
                    });
                }
            });

            function updateInfo(data, props) {
                document.getElementById('name').innerText = props.NAME_CHN;
                document.getElementById('eng-name').innerText = props.NAME_ENG;
                document.getElementById('soc').innerText = props.SOC;
                document.getElementById('confirmed').innerText = data ? data.confirmed : "暂无数据";
                document.getElementById('suspected').innerText = data ? data.suspected : "暂无数据";
                document.getElementById('cured').innerText = data ? data.cured : "暂无数据";
                document.getElementById('dead').innerText = data ? data.dead : "暂无数据";
            }

            window.map.add(disWorld);
        }
    }, {
        type: "get"
    });
}

/*
 // 作者 yanue
 // 参数：
 // startColor：开始颜色hex
 // endColor：结束颜色hex
 // step:几个阶级（几步）
 */
function gradientColor(startColor, endColor, step) {
    startRGB = this.colorRgb(startColor);//转换为rgb数组模式
    startR = startRGB[0];
    startG = startRGB[1];
    startB = startRGB[2];
    endRGB = this.colorRgb(endColor);
    endR = endRGB[0];
    endG = endRGB[1];
    endB = endRGB[2];
    sR = (endR - startR) / step;//总差值
    sG = (endG - startG) / step;
    sB = (endB - startB) / step;
    var colorArr = [];
    for (var i = 0; i < step; i++) {
        //计算每一步的hex值
        var hex = this.colorHex('rgb(' + parseInt((sR * i + startR)) + ',' + parseInt((sG * i + startG)) + ',' + parseInt((sB * i + startB)) + ')');
        colorArr.push(hex);
    }
    return colorArr;
}

// 将hex表示方式转换为rgb表示方式(这里返回rgb数组模式)
gradientColor.prototype.colorRgb = function (sColor) {
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    var sColor = sColor.toLowerCase();
    if (sColor && reg.test(sColor)) {
        if (sColor.length === 4) {
            var sColorNew = "#";
            for (var i = 1; i < 4; i += 1) {
                sColorNew += sColor.slice(i, i + 1).concat(sColor.slice(i, i + 1));
            }
            sColor = sColorNew;
        }
        //处理六位的颜色值
        var sColorChange = [];
        for (var i = 1; i < 7; i += 2) {
            sColorChange.push(parseInt("0x" + sColor.slice(i, i + 2)));
        }
        return sColorChange;
    } else {
        return sColor;
    }
};
// 将rgb表示方式转换为hex表示方式
gradientColor.prototype.colorHex = function (rgb) {
    var _this = rgb;
    var reg = /^#([0-9a-fA-f]{3}|[0-9a-fA-f]{6})$/;
    if (/^(rgb|RGB)/.test(_this)) {
        var aColor = _this.replace(/(?:(|)|rgb|RGB)*/g, "").split(",");
        var strHex = "#";
        for (var i = 0; i < aColor.length; i++) {
            var hex = Number(aColor[i]).toString(16);
            hex = hex < 10 ? 0 + '' + hex : hex;// 保证每个rgb的值为2位
            if (hex === "0") {
                hex += hex;
            }
            strHex += hex;
        }
        if (strHex.length !== 7) {
            strHex = _this;
        }
        return strHex;
    } else if (reg.test(_this)) {
        var aNum = _this.replace(/#/, "").split("");
        if (aNum.length === 6) {
            return _this;
        } else if (aNum.length === 3) {
            var numHex = "#";
            for (var i = 0; i < aNum.length; i += 1) {
                numHex += (aNum[i] + aNum[i]);
            }
            return numHex;
        }
    } else {
        return _this;
    }
};

function loadMap() {
    if (!window.map) {
        const map = new AMap.Map("container", {
            mapStyle: 'amap://styles/1de318cbb8d12c02303a22c550b9ccc9',
            viewMode: "3D",
            pitch: 15,
            resizeEnable: true,
            center: [100, 36],
            zoom: 3,
            zooms: [3, 15],
            showIndoorMap: false,
            isHotspot: false,
            defaultCursor: 'pointer',
            touchZoomCenter: 1,
            pitch: 0,
            resizeEnable: true
        });
        window.map = map;
        loadlayer();
    } else {
        loadlayer();
    }
}

window.onload = function () {
    let dropdown = new Vue({
        el: "#container",
        data: {},
        methods: {},
        mounted: function () {
            loadMap();
        }
    });
};