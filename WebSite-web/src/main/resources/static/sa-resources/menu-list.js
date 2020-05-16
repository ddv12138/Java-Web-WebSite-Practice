sa.get("/resource?pid=1", null, function (arg) {
    if (!arg || !arg.state) {
        return;
    } else {
        arg = arg.data;
    }
    let parseDataTooTree = function (arg) {
        for (let i = 0; i < arg.length; i++) {
            arg[i].label = arg[i].name;
            if (arg[i].childList && arg[i].childList.length === 0) {
                delete arg[i].childList;
                continue;
            }
            if (arg[i].childList) {
                parseDataTooTree(arg[i].childList);
                arg[i].children = arg[i].childList;
            }
        }
    };
    console.log(arg)
    parseDataTooTree(arg);
    window.menuList = arg;
    if (sp) {
        sp.setMenuList(arg);
    }
});