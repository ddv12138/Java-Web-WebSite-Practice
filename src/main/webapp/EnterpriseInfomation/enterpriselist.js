function init_list() {
    var renderPar = {
        elem: '#restable'
        , cols: [[
            {field: 'id', title: '序号', type: 'numbers', sort: true, width: 100},
            {field: 'name', title: '企业名称', sort: true, width: 300},
            {field: 'code', title: '标识', sort: true, width: 150},
            {field: 'regday', title: '地址', sort: true, width: 150},
            {field: 'legalRepresentative', title: '法人', sort: true},
            {field: 'capital', title: '注册资本', sort: true, width: 150},
            {field: 'province', title: '省份', sort: true},
            {field: 'city', title: '城市', sort: true},
            {field: 'address', title: '地址', sort: true, width: 300},
            {field: 'businessScope', title: '从事行业', sort: true, width: 300},
        ]],
        toolbar: 'default',
        totalRow: true,
        page: true,
        parseData: function (arg) {
            console.log(arg);
            // var data = [];
            // if (arg.data) {
            //     for (var i = 0; i < arg.data.length; i++) {
            //         data.push($.extend(arg.data[i].result, arg.data[i].server));
            //     }
            // }
            return res = {
                "code": 0,
                "msg": "sucess",
                "count": arg.data.count,
                "data": arg.data.data
            }
        },
        url: '../getEnterpriseList',
        contentType: "application/json",
        method: "post",
    };
    layui.table.render(renderPar);
}

window.onload = function () {
    init_list();
}