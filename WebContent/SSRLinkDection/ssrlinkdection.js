window.onload = function () {
    layui.form.render();
    var parseSSRResult = function (arg) {
        var data = [];
        if (arg.data) {
            for (var i = 0; i < arg.data.length; i++) {
                data.push($.extend(arg.data[i].result, arg.data[i].server));
            }
        }
        console.log(data);
        var res = {
            code: arg.state, //解析接口状态
            msg: arg.msg, //解析提示文本
            data: data //解析数据列表
        }
        return res;
    }
    layui.form.on("submit(searchform)", function (data) {
        var renderPar = {
            elem: '#restable'
            , cols: [[
                {field: 'group', title: '分组', sort: true,},
                {field: 'remarks', title: '标识', sort: true},
                {field: 'server', title: '地址', sort: true},
                {field: 'address', title: 'IP地址', sort: true},
                {field: 'port', title: '端口', sort: true},
                {field: 'passwd', title: '密码', sort: true},
                {field: 'method', title: '加密方法', sort: true},
                {field: 'obfs', title: 'obfs', sort: true},
                {field: 'obfsparam', title: 'obfsparam', sort: true},
                {field: 'rtt_avg', title: '平均延迟', sort: true}
            ]],
            url: '../getSSRDectionResult',
            contentType: "application/json",
            method: "post",
            where: data.field,
            parseData: parseSSRResult
        };
        console.log(renderPar)
        layui.table.render(renderPar);
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
}