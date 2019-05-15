function initLeftTab() {
    $.ajax({
        url: "./getTabList",
        data: JSON.stringify({parentid: null}),
        type: "post",
        contentType: "application/json",
        complete: renderTabList
    })
}

function nodeClick(arg) {
    var node = arg.target;
    if (node.className == 'layui-nav-more') node = node.parentElement;
    //点击时如果未展开过则加载子节点
    if (!node.getAttribute("expanded")) {
        var data = JSON.parse(node.getAttribute("nodevalue"));
        $.ajax({
            url: "./getTabList",
            data: JSON.stringify({parentid: data.id}),
            type: "post",
            contentType: "application/json",
            complete: function (arg) {
                arg = JSON.parse(arg.responseText);
                arg = JSON.parse(arg.result);
                renderSubTabList(arg, node);
            }
        })
    }
}

function renderSubTabList(arg, node) {
    var res = arg;
    if (res.state && !node.getAttribute("expanded")) {
        var data = res.data;
        for (var i = 0; i < data.length; i++) {
            var dd = document.createElement("dd");
            var a = document.createElement("a");
            a.innerHTML = data[i].cnname;
            a.setAttribute("nodevalue", JSON.stringify(data[i]));
            a.setAttribute("href", "javascript:;");
            $(a).click(nodeClick);
            if (data[i].haschild) {
                var span = document.createElement("span");
                span.setAttribute("class", "layui-nav-more");
                a.appendChild(span);
            }
            dd.append(a);
            var dls = node.parentElement.getElementsByClassName("layui-nav-child");
            if (!dls || dls.length <= 0) {
                var dl = document.createElement("dl");
                dl.setAttribute("class", "layui-nav-child");
                node.parentElement.appendChild(dl);
            }
            node.parentElement.getElementsByClassName("layui-nav-child")[0].appendChild(dd);
            layui.use("element", function () {
                layui.element.render("nav", "main-nav-bar");
            })
            node.setAttribute("expanded", true);
        }
    }
}

function renderTabList(arg, msg, reponseobj, node) {
    var res = JSON.parse(arg.responseText);
    res = JSON.parse(res.result);
    if (res.state) {
        var data = res.data;
        for (var i = 0; i < data.length; i++) {
            var li = document.createElement("li");
            li.setAttribute("class", "layui-nav-item");
            var a = document.createElement("a");
            a.innerHTML = data[i].cnname;
            a.setAttribute("nodevalue", JSON.stringify(data[i]));
            a.setAttribute("href", "javascript:;");
            $(a).click(nodeClick);
            li.append(a);
            var dl = document.createElement("dl");
            dl.setAttribute("class", "layui-nav-child");
            li.appendChild(dl);
            if (!node) node = $("#main-nav-bar");
            node.append(li);
            layui.use("element", function () {
                layui.element.render("nav", "main-nav-bar");
            })
            if (i == 0) {
                $("#main-content-frame").attr("src", data[i].urlpath);
            }
        }
    }
}
window.onload = function(){
    initLeftTab();
};