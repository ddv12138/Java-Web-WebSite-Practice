function initLeftTab() {
    $.post("ResourceManage?method=getTabList", {parentid: null}, renderTabList);
}

function renderTabList(arg) {
    console.log(arg);
}
window.onload = function(){
    initLeftTab();
};