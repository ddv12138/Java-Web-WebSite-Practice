$(document).ready(function () {
    $.ajax({
        url: "../getTabList",
        data: JSON.stringify({parentid: null, ismanage: true}),
        type: "post",
        contentType: "application/json",
        complete: renderNodeTree
    });
    layui.layer.config({
        success: frameResize,
        full: frameResize,
        min: frameResize,
        restore: frameResize,
        resizing: frameResize
    });
});

function renderNodeTree(arg) {
    arg = JSON.parse(arg.responseText);
    arg = JSON.parse(arg.result);
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
                contentType: "application/json",
                dataFilter: treeValueFilter,
                url: "../getTabList",
                type: "post",
                autoParam: ["id=parentid"]
            },
            edit: {
                enable: true,
                removeTitle: "删除节点",
                renameTitle: "编辑节点",
                showRenameBtn: false,
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
            },
            callback: {
                beforeRemove: removeResNode
            }
        };
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, arg.data);
    }
}

var newCount = 0;

function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var modifyStr = "<span class='button edit' id='updateBtn_" + treeNode.tId
        + "' title='修改' onfocus='this.blur();'></span>";
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='新增子节点' onfocus='this.blur();'></span>";
    sObj.after(modifyStr);
    sObj.after(addStr);
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {
        layui.layer.open({
            type: 2,
            title: '资源详细信息',
            anim: 1,
            maxmin: true,
            content: ["./resinsert.html?pageMode=insert&pnodeid=" + treeNode.id],
            id: "resinsert_frame",
            area: ['500px', '300px'],
            resize: false,
            end: function () {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                if (treeNode.getParentNode()) {
                    zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh", false);
                } else {
                    zTree.rrefresh();
                }
                zTree.reAsyncChildNodes(treeNode, "refresh", false);
            }
        });
        return false;
    });

    var modifybtn = $("#updateBtn_" + treeNode.tId);
    if (modifybtn) modifybtn.bind("click", function () {
        layui.use('layer', function () {
            layui.layer.open({
                type: 2,
                title: '资源详细信息',
                anim: 1,
                maxmin: true,
                content: ["./resinsert.html?pageMode=update"],
                id: "resinsert_frame",
                area: ['500px', '300px'],
                resize: false,
                end: function () {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    if (treeNode.getParentNode()) {
                        zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh", false);
                    } else {
                        zTree.rrefresh();
                    }
                },
                success: function (layero, index) {
                    if (layero.find("#resinsert_frame").find("iframe")[0])
                        layero.find("#resinsert_frame").find("iframe")[0].contentWindow.setUpdateNodeInfo(treeNode);
                }
            });
        });
        return false;
    });
}
function removeHoverDom(treeId, treeNode) {
    $("#addBtn_" + treeNode.tId).unbind().remove();
    $("#updateBtn_" + treeNode.tId).unbind().remove();
}
function treeValueFilter(treeid, pnode, res) {
    if (res.state) {
        return JSON.parse(res.data);
    }
}

function removeResNode(treeId, treeNode) {
    var flag = false;
    $.ajax({
        url: "../deleteNode",
        contentType: "application/json",
        data: JSON.stringify({id: treeNode.id}),
        type: "post",
        async: false,
        complete: function (arg) {
            if (!arg instanceof Object) {
                try {
                    arg = JSON.parse(arg.responseText);
                } catch (e) {
                    flag = false;
                    return;
                }
            }
            if (arg.state) {
                var zTree = $.fn.zTree.getZTreeObj(treeId);
                zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh", false);
                flag = true;
            } else {
                alert(arg.msg);
                flag = false;
                return;
            }
            flag = false;

        }
    });
    return flag;
}

function frameResize(layero, index) {
    layer.iframeAuto(index);
}