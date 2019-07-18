function init_list() {
    var renderPar = {
        elem: '#restable'
        , cols: [[
            {field: 'id', title: '序号', type: 'numbers', sort: true, width: 100},
            {field: 'name', title: '企业名称', sort: true, width: 300},
            {field: 'code', title: '标识', sort: true, width: 150},
            {field: 'regday', title: '地址', sort: true, width: 150},
            {field: 'legalrepresentative', title: '法人', width: 150},
            {field: 'capital', title: '注册资本', sort: true, width: 150},
            {field: 'province', title: '省份', sort: true},
            {field: 'city', title: '城市', sort: true},
            {field: 'address', title: '地址', width: 300},
            {field: 'businessscope', title: '从事行业', width: 300},
        ]],
        toolbar: 'default',
        totalRow: true,
        page: true,
        parseData: function (arg) {
            console.log(arg)
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
        id: "enterpriselist"
    };
    layui.table.render(renderPar);
}

window.onload = function () {
    $("#searchbtn").click(function () {
        layui.table.reload('enterpriselist', {
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                name: $("#searchname").val()
            }
        });
    });
    init_list();
}