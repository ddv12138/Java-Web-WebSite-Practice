function init_list() {
    var renderPar = {
        elem: '#restable'
        , cols: [[
            {field: 'id', title: '序号', type: 'numbers', sort: true, width: 100},
            {field: 'name', title: '企业名称', sort: true, width: 250},
            {field: 'code', title: '标识', sort: true, width: 150},
            {
                field: 'regday',
                title: '注册时间',
                sort: true,
                width: 100,
                templet: "<div>{{layui.util.toDateString(d.ordertime, 'yyyy-MM-dd')}}</div>"
            },
            {field: 'legalrepresentative', title: '法人', width: 150},
            {field: 'capital', title: '注册资本', sort: true, width: 150},
            {field: 'province', title: '省份', sort: true, width: 100},
            {field: 'city', title: '城市', sort: true, width: 100},
            {field: 'address', title: '地址', width: 300},
            {field: 'businessscope', title: '从事行业'},
        ]],
        height: 'full-100',
        limit: 30,
        limits: [30, 60, 90],
        cellMinWidth: 80,
        toolbar: 'default',
        totalRow: true,
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
            layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            , groups: 1 //只显示 1 个连续页码
            , first: false //不显示首页
            , last: false //不显示尾页

        },
        parseData: function (arg) {
            console.log(arg);
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
        id: "enterpriselist",
        skin: 'line' //行边框风格
        , even: true //开启隔行背景
        , size: 'sm' //小尺寸的表格
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
};