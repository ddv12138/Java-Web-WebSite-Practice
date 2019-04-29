function initLeftTab() {
    $.post("ResourceManage?method=getTabList", {parentid: null}, renderTabList);
}

function renderTabList(arg) {
    console.log(JSON.parse(arg));
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
            li.append(a);
            $("#main-nav-bar").append(li);
            layui.use("element(main-nav-bar)", function () {
                layui.element.render("nav", "main-nav-bar");
            })
        }
    }
}
window.onload = function(){
    initLeftTab();
};