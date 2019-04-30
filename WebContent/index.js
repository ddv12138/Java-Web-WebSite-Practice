function initLeftTab() {
    $.post("ResourceManage?method=getTabList", {parentid: null}, renderTabList);
}

function nodeClick(arg) {
    var node = arg.target;
    var data = JSON.parse(node.getAttribute("nodevalue"));
    $.post("ResourceManage?method=getTabList", {parentid: data.id}, function (arg) {
        renderSubTabList(arg, node)
    });
}

function renderSubTabList(arg, node) {
    var res = JSON.parse(arg);
    if (res.state && !node.getAttribute("expanded")) {
        var data = res.data;
        for (var i = 0; i < data.length; i++) {
            var dd = document.createElement("dd");
            var a = document.createElement("a");
            a.innerHTML = data[i].cnname;
            a.setAttribute("nodevalue", JSON.stringify(data[i]));
            a.setAttribute("href", "javascript:;");
            dd.append(a);
            node.parentElement.getElementsByClassName("layui-nav-child")[0].appendChild(dd);
            layui.use("element", function () {
                layui.element.render("nav", "main-nav-bar");
            })
            node.setAttribute("expanded", true);
        }
    }
}

function renderTabList(arg, msg, reponseobj, node) {
    var res = JSON.parse(arg);
    if (res.state) {
        var data = res.data;
        for (var i = 0; i < data.length; i++) {
            var li = document.createElement("li");
            li.setAttribute("class", "layui-nav-item layui-nav-itemed");
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