window.onload = function () {
    layui.use('form', function () {
        layui.form.on("submit(insert_res_form)", function (data) {
            console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
            var res;
            data.field.pnodeid = GetUrlParam("pnodeid")
            $.post("../ResourceManage?method=insertNodeByParent", data.field, function (arg) {
                res = JSON.parse(arg);
                if (res.state) {
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭
                }
            })
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        })
    });
}

function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");

    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;

        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");

            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    } else {
        return "";
    }
}