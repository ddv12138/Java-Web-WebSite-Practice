window.onload = function () {
    var pageMode = GetUrlParam("pageMode");
    layui.use('form', function () {
        layui.form.on("submit(insert_res_form)", function (data) {
            if (pageMode == "insert") {
                data.field.pnodeid = GetUrlParam("pnodeid");
                $.post("../ResourceManage?method=insertNodeByParent", data.field, function (arg) {
                    arg = JSON.parse(arg);
                    if (arg.state) {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }
                });
            } else if (pageMode == "update") {
                data.field.id = updateid;
                console.log(data.field);
                $.post("../ResourceManage?method=updateResNodeInfo", data.field, function (arg) {
                    arg = JSON.parse(arg);
                    if (arg.state) {
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index); //再执行关闭
                    }
                });
            }
            return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        })
    });
}
var updateid = null;
var setUpdateNodeInfo = function (arg) {
    var pageMode = GetUrlParam("pageMode");
    if (!pageMode) return;
    if (pageMode == "update") {
        layui.use('form', function () {
            arg.constructor = Object;
            updateid = arg.id;
            layui.form.val("resinsert", arg)
        });
    }
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