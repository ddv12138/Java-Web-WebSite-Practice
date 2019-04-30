var zTreeObj;
// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
var setting = {
    data: {
        key: {
            name: "cnname",
            isParent: "parent",
        },
        keep: {
            parent: true
        }
    },
    async: {
        enable: true,
        contentType: "application/json",
        dataType: "text",
        dataFilter: treeValueFilter,
        url: "/WEB/ResourceManage?method=getTabList",
        type: "post",
        autoParam: ["id=parentid"]
    }
};
// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
// var zNodes = [
//     {name:"test1", open:true, children:[
//             {name:"test1_1"}, {name:"test1_2"}]},
//     {name:"test2", open:true, children:[
//             {name:"test2_1"}, {name:"test2_2"}]}
// ];
$(document).ready(function () {
    console.log(1);
    $.post("/WEB/ResourceManage?method=getTabList", {parentid: null}, renderNodeTree);
})

function renderNodeTree(arg) {
    console.log(arg);
    arg = JSON.parse(arg);
    if (arg.state) {
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, arg.data);
    }
}

function treeValueFilter(data) {
    console.log(data);
}