$(document).ready(function () {
    $.post("/WEB/ResourceManage?method=getTabList", {parentid: null, ismanage: true}, renderNodeTree);
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
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
            }
        };
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, arg.data);
        console.log(arg)
    }
}

var newCount = 0;

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        // var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        // zTree.addNodes(treeNode, {id: (100 + newCount), pId: treeNode.id, name: "new node" + (newCount++)});
        var frameResize = function (layero, index) {
            layer.iframeAuto(index);
        }
        layui.use('layer', function () {
            layui.layer.config({
                success: frameResize,
                full: frameResize,
                min: frameResize,
                restore: frameResize,
                resizing: frameResize
            });
            layui.layer.open({
                type: 2,
                title: '资源详细信息',
                anim: 1,
                maxmin: true,
                content: ["./resinsert.html?pnodeid=" + treeNode.id],
                id: "resinsert_frame",
                area: ['500px', '300px'],
                resize: false
            })
        });
        return false;
    });
};

function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
};
function treeValueFilter(treeid, pnode, res) {
    if (res.state) {
        return res.data
    }
}