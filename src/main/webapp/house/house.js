window.onload = function () {
    let cityStr = "武汉";
    $.post("../getCityInfo", {cityName: cityStr}, function (cityData, status) {
        cityData = JSON.parse(JSON.parse(cityData).data).results[0];
        var map = new AMap.Map("container", {
            resizeEnable: true,
            center: [cityData.location.lng, cityData.location.lat],
            zoom: 11
        });
        $.post("../getCommunitiesByCity", {cityName: cityStr}, function (commData, status) {
            let points = [];
            commData = JSON.parse(JSON.parse(commData).data);
            commData.forEach(function (loc) {
                points.push({lng: loc.gaode_lng, lat: loc.gaode_lat, count: loc.unit_price});
            });
            console.log(points);
            if (!isSupportCanvas()) {
                alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
            }
            var heatmap;
            map.plugin(["AMap.Heatmap"], function () {
                //初始化heatmap对象
                heatmap = new AMap.Heatmap(map, {
                    radius: 25, //给定半径
                    opacity: [0, 0.8]
                    /*,
                    gradient:{
                        0.5: 'blue',
                        0.65: 'rgb(117,211,248)',
                        0.7: 'rgb(0, 255, 0)',
                        0.9: '#ffea00',
                        1.0: 'red'
                    }
                     */
                });
                //设置数据集：该数据为北京部分“公园”数据
                heatmap.setDataSet({
                    data: points,
                    max: 80000
                });
            });
        })
    })
};

function isSupportCanvas() {
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}

function setGradient() {
    /*格式如下所示:
   {
         0:'rgb(102, 255, 0)',
         .5:'rgb(255, 170, 0)',
         1:'rgb(255, 0, 0)'
   }*/
    var gradient = {
        .1: '#82B8DA',
        .2: '#66A9D7',
        .3: '#4394C2',
        .4: '#3B8BBE',
        .5: '#2F7EB9',
        .6: '#226DB0',
        .7: '#125CA3',
        .8: '#18639E',
        .9: '#073067',
    };
    heatmapOverlay.setOptions({"gradient": gradient});
}