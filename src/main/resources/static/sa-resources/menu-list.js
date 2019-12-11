$.get("/resource", function (arg) {
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
	let docu = {
		id: '11',
		name: '文档说明',
		icon: 'el-icon-document-remove',
		url: 'sa-html/sa-doc.html',
		order: 3
	};
	arg.push(docu);
	parseDataTooTree(arg);
	console.log(arg);
	window.menuList = arg;
	sp.setMenuList(arg);
});