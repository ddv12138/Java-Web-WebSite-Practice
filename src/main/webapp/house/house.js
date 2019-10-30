window.onload = function () {
    const dom = document.getElementById("container");
    const myChart = echarts.init(dom);
    const app = {};
    let option = null;
    app.title = '热力图与百度地图扩展';

    var city = "武汉";

    var cityCenter = null;

    $.ajax({
        url: "../getCommunitiesByCity",
        dataType: "text",
        contentType: "application/json",
        data: city,
        type: "post",
        complete: function (arg) {
            console.log(arg);
        }
    });

    const points = null;
    console.log(points);
    myChart.setOption(option = {
        animation: false,
        bmap: {
            center: "wuhan",
            zoom: 14,
            roam: true
        },
        visualMap: {
            show: false,
            top: 'top',
            min: 0,
            max: 5,
            seriesIndex: 0,
            calculable: true,
            inRange: {
                color: ['blue', 'blue', 'green', 'yellow', 'red']
            }
        },
        series: [{
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            data: points,
            pointSize: 5,
            blurSize: 6
        }]
    });
    if (!app.inNode) {
        // 添加百度地图插件
        var bmap = myChart.getModel().getComponent('bmap').getBMap();
        bmap.addControl(new BMap.MapTypeControl());
    }
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
};