function loadlayer() {
    var colors = {};
    sa.ajax2("/ncov/world", null, function (arg) {
        console.log(arg);
    }, {
        type: "get"
    });
    var getColorBySOC = function (SOC) {
        //可按需改为其他实现
        if (SOC) {
            if (!colors[SOC]) {
                var R = SOC.charCodeAt(0) * 1;
                var G = SOC.charCodeAt(1) * 3;
                var B = SOC.charCodeAt(2) * 5;
                colors[SOC] = 'rgb(' + R + ',' + G + ',' + B + ')';
            }
            return colors[SOC]
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
                return getColorBySOC(props.SOC)
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
                updateInfo(props);
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

    function updateInfo(props) {
        document.getElementById('name').innerText = props.NAME_CHN;
        document.getElementById('eng-name').innerText = props.NAME_ENG;
        document.getElementById('soc').innerText = props.SOC;
    }

    window.map.add(disWorld);
}

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