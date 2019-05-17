window.onload = function () {
    layui.form.render();
    layui.form.on("submit(searchform)", function (data) {
        $.ajax({
            url: "../getSSRDectionResult",
            contentType: "application/json",
            data: JSON.stringify(data.field),
            type: "post",
            complete: function (arg) {
                arg = JSON.parse(arg.responseText);
                console.log(arg);
            }
        })
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })
}