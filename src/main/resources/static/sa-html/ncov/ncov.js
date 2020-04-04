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
    } else {
        window.map.setCenter([cityData.location.lng, cityData.location.lat]);
    }
}

window.onload = function () {
    let dropdown = new Vue({
        el: "#map",
        data: {},
        methods: {},
        mounted: function () {
            loadMap();
        }
    });
};