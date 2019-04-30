$(document).ready(function () {
    $.post("/WEB/ResourceManage?method=getTabList", {parentid: null}, renderNodeTree);
})

function renderNodeTree(arg) {
    arg = JSON.parse(arg);
    if (arg.state) {
        var zTreeObj;
        var setting = {
            data: {
                key: {
                    name: "cnname",
                    isParent: "haschild"
                },
                keep: {
                    parent: true
                }
            },
            async: {
                enable: true,
                dataType: "text",
                dataFilter: treeValueFilter,
                url: "../ResourceManage?method=getTabList",
                type: "post",
                autoParam: ["id=parentid"]
            },
            edit: {
                enable: true,
                removeTitle: "删除节点",
                renameTitle: "编辑节点",
                drag: {
                    isMove: false,
                    isCopy: false
                }
            },
            view: {
                dblClickExpand: false,
                showLine: true,
                selectedMulti: false,
            }
        };
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, arg.data);
        console.log(arg)
    }
}

function treeValueFilter(treeid, pnode, res) {
    if (res.state) {
        return res.data
    }
}