// 在使用时，不建议你直接魔改模板的代码，以免在运行时出现意外bug，而是在本文件中根据模板的提供的API，来适应你的业务逻辑 
// ....


// ================================= 示例：一些基本信息 =================================

// 设置模板标题 
sp.title = "ddvudo";
sp.logo_url = 'sa-resources/admin-logo.png';    // 设置logo图标地址   默认值：sa-resources/admin-logo.png
sp.icon_url = 'sa-resources/admin-logo.png';    // 设置icon图标地址   默认值：sa-resources/admin-logo.png


// ================================= 示例：自定义菜单 =================================

// var myMenuList = window.menuList;		// window.menuList 在 menu-list.js 中定义
// sp.setMenuList(myMenuList);	// 写入菜单
// sp.setMenuList(myMenuList, [11, 1, '1-1']);	// 写入菜单，并设置应该显示哪些id的菜单（第二个参数为空时，代表默认显示所有） 

// 如果需要获得更多操作能力，如：动态添加菜单、删除菜单等
// 可直接 sp.menuList 获得菜单引用，直接操作对象 


// ================================= 示例：js控制打开某个菜单 =================================

// 打开一个 菜单，根据 id
// sp.showMenuById('1-1');	

// 关闭一个 页面，根据 id 
// sp.closePageById('');

// 打开一个自定义 页面  
// sp.showPage({id: 12345, name: '新页面', url: 'http://web.yanzhi21.com'});		// id尽量不要和已有的菜单id冲突，其它属性均可参照菜单项


// ================================= 示例：设置user信息 =================================
// 用户登录后，右上角可直接显示用户的头像和昵称
$.get("/user", function (res) {
    if (res.state) {
        let data = res.data;
        if (data.accountNonExpired && data.accountNonLocked && data.credentialsNonExpired && data.enabled) {
            sp.user = {
                username: data.username,	// 昵称
                avatar: 'sa-resources/admin-logo.png'	// 头像地址
            };
        }
    }
});


// ================================= 示例：设置登录后的头像处，下拉可以出现的选项  =================================
sp.dropList = [		// 头像点击处可操作的选项
    // {
    // 	name: '我的资料',
    // 	click: function () {
    // 		sp.$message('点击了我的资料，你可以参照文档重写此函数');
    // 	}
    // },
    // {
    // 	name: '切换账号',
    // 	click: function () {
    // 		layer.open({
    // 			type: 2,
    // 			title: '登录',
    // 			shadeClose: true,
    // 			shade: 0.8,
    // 			area: ['70%', '80%'],
    // 			resize: true,
    // 			content: '/user/login'
    // 		});
    // 	}
    // },
    {
        name: '退出登录',
        click: function () {
            //sp.$message('点击了退出登录，你可以参照文档重写此函数');
            // location="login.html";
            layer.confirm('退出登录？', function (res) {
                layer.alert('注销成功', function () {
                    location.href = "/user/logout";
                })
            });
        }
    }
];


// ================================= 示例：初始化模板(必须调用)  =================================
// sa_admin.init();
// 或者以下方式，增加配置项
sa_admin.init({
    themeDefault: '1',	// 默认的主题，可选值：1、2、3、4
    switchDefault: '4',	// 默认的切换动画，可选值：fade、slide、cube、coverflow、flip
});



