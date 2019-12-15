$.get("/resource?pid=0", function (arg) {
	if (!arg || !arg.state) {
		return;
	} else {
		arg = arg.data;
	}
	let parseDataTooTree = function (arg) {
		for (let i = 0; i < arg.length; i++) {
			arg[i].icon = 'el-icon-document-remove';
			arg[i].label = arg[i].name;
			if (arg[i].childList && arg[i].childList == 0) {
				delete arg[i].childList;
				continue;
			}
			if (arg[i].childList) {
				parseDataTooTree(arg[i].childList);
				arg[i].children = arg[i].childList;
			}
		}
	};
	parseDataTooTree(arg);
	window.menuList = arg;
	sp.setMenuList(arg);
});