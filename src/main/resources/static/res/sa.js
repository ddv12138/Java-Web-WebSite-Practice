const sa = {};
$.fn.serializeObject = function () {
    const ct = this.serializeArray();
    const obj = {};
    $.each(ct, function () {
        if (obj[this.name] !== undefined) {
            if (!obj[this.name].push) {
                obj[this.name] = [obj[this.name]];
            }
            obj[this.name].push(this.value || "");
        } else {
            obj[this.name] = this.value || "";
        }
    });
    return obj;
};

// ===========================  服务器环境配置  ======================================= 

(function () {

    // 公司开发环境
    const cfg_dev = {
        api_url: 'http://localhost:8091',
        web_url: 'http://www.baidu.com'
    };

    // 服务器测试环境
    const cfg_test = {
        api_url: 'http://www.baidu.com',
        web_url: 'http://www.baidu.com'
    };

    // 服务器正式环境
    const cfg_prod = {
        api_url: 'http://www.baidu.com',
        web_url: 'http://www.baidu.com'
    };

    sa.cfg = cfg_dev; // 最终环境 , 上限前请选择正确的环境


})();


// ===========================  第三方框架扩展  =======================================


/**
 * ajax的再封装,
 * @param {Object} url 请求地址
 * @param {Object} data
 * @param type
 * @param callback
 * （success500=状态码500函数，errorfn=请求异常函数，async=是否异步）
 */
sa.ajax = function (url, data, type, callback) {
    let load = 0;
    // 默认配置
    const defaultCfg = {
        msg: '努力加载中...',	// 提示语
        // baseUrl: (url.indexOf('/') == 0 ? sa.cfg.api_url : ''),// 父url，拼接在url前面
        baseUrl: url,// 父url，拼接在url前面
        // 回调函数处理
        success500: function (res) {
            return layer.alert('失败：' + res.msg);
        },
        // code=402, 代表密码错误
        success402: function (res) {
            return layer.alert("登陆失败", {icon: 5});
        },
        // code=401, 代表未登录
        success401: function (res) {
            return layer.confirm("您当前暂未登录，是否立即登录？", {}, function () {
                layer.closeAll();
                // return layer.open({
                //     type: 2,
                //     title: '登录',
                //     shadeClose: true,
                //     shade: 0.8,
                //     area: ['90%', '90%'],
                //     content: cfg.login_url || '/login.html'
                // });
                window.location.href = '/login.html';
            });
        },
        // ajax发生异常时的默认处理函数
        errorfn: function (xhr, type, errorThrown) {
            if (xhr.status === 0) {
                return layer.alert('无法连接到服务器，请检查网络');
            }
            return layer.alert("异常：" + JSON.stringify(xhr));
        },
        // 成功失败都执行
        complete: function (XHR, TS) {
            layer.close(load);
        }
    };
    // 日志
    // console.trace("请求地址：" + url);
    // console.trace("请求参数：" + JSON.stringify(data));
    // console.trace("请求类型：" + type);

    //
    // 开始ajax
    if (defaultCfg.msg != null) {
        load = layer.msg(defaultCfg.msg, {icon: 16, shade: 0.01, time: 1000 * 20, skin: 'ajax-layer-load'});
    }

    return $.ajax({
        url: url,
        type: type,
        data: data,
        dataType: 'json',
        contentType: "application/json",
        // xhrFields: {
        // 	withCredentials: true // 携带跨域cookie
        // },
        // crossDomain: true,
        // beforeSend: function (xhr) {
        // 	xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');
        // },
        success: function (res) {
            layer.close(load);
            // 业务成功的函数
            if (res.state === 1) {
                return callback(res);
            }
            // 如果相应的处理函数存在
            if (defaultCfg['success' + res.state] !== undefined) {
                return defaultCfg['success' + res.state](res);
            }
            layer.alert('未知状态码：' + JSON.stringify(res));
        },
        error: function (xhr, type, errorThrown) {
            return defaultCfg.errorfn(xhr, type, errorThrown);
        },
        complete: defaultCfg.complete
    });

};
sa.get = function (url, data, callback) {
    sa.ajax(url, data, "GET", callback);
};
sa.post = function (url, data, callback) {
    sa.ajax(url, data, "POST", callback);
};
sa.put = function (url, data, callback) {
    sa.ajax(url, data, "PUT", callback);
};
sa.delete = function (url, data, callback) {
    sa.ajax(url, data, "DELETE", callback);
};

// ===========================  弹窗相关   =======================================


(function () {

    // 提示文字
    sa.msg = function (msg) {
        layer.msg(msg);
    };

})();


// ===========================  $util 常用util函数封装   ======================================= 

// 共用js代码库
(function () {

    // 超级对象
    const me = {};
    sa.$util = me;


    // ===========================  常用util函数封装   =======================================
    // 判断一个变量是否为null
    me.isNull = function (obj, return_obj) {
        const flag = [null, undefined, '', 'null', 'undefined'].indexOf(obj) !== -1;
        if (return_obj === undefined) {
            return flag;
        } else {
            if (flag) {
                return return_obj;
            } else {
                return obj;
            }
        }
    };
    me.forDate = function (inputTime, way) {
        if (me.isNull(inputTime) === true) {
            return "";
        }
        const date = new Date(inputTime);
        const y = date.getFullYear();
        let m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        let d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        let h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        let minute = date.getMinutes();
        let second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        if (way === 2) {
            return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
        }
        return y + '-' + m + '-' + d;
    };
    me.forDate2 = function (d) {

        let hou = "前";

        if (d == null || d === '') {
            return '';
        }
        const timestamp = new Date(d).getTime() - 1000;
        let mistiming = Math.round((Date.now() - timestamp) / 1000);
        if (mistiming < 0) {
            mistiming = 0 - mistiming;
            hou = '后'
        }
        const arrr = ['年', '月', '周', '天', '小时', '分钟', '秒'];
        const arrn = [31536000, 2592000, 604800, 86400, 3600, 60, 1];
        for (let i = 0; i < arrn.length; i++) {
            const inm = Math.floor(mistiming / arrn[i]);
            if (inm !== 0) {
                return inm + arrr[i] + hou;
            }
        }
    };
    me.JSONParse = function (obj, default_obj) {
        try {
            return JSON.parse(obj) || default_obj;
        } catch (e) {
            return default_obj || {};
        }
    };
    me.maxLength = function (str, length) {
        length = length || 50;
        if (!str) {
            return "";
        }
        return (str.length > length) ? str.substr(0, length) + ' ... ' : str;
    };

    // 过滤掉标签
    me.text = function (str) {
        if (!str) {
            return "";
        }
        return str.replace(/<[^>]+>/g, "");
    };
    me.listAU = function (list) {
        return list;
    };
    me.getSrcList = function (str) {
        try {
            const imgReg = /<img.*?(?:>|\/>)/gi;	//匹配图片（g表示匹配所有结果i表示区分大小写）
            const srcReg = /src=[\'\"]?([^\'\"]*)[\'\"]?/i;	//匹配src属性
            const arr = str.match(imgReg);	// 图片数组
            const srcList = [];
            for (let i = 0; i < arr.length; i++) {
                const src = arr[i].match(srcReg);
                srcList.push(src[1]);
            }
            return srcList;
        } catch (e) {
            return [];
        }
    };
    me.accMul = function (arg1, arg2) {
        var m = 0,
            s1 = arg1.toString(),
            s2 = arg2.toString(),
            t;

        t = s1.split(".");
        // 判断有没有小数位，避免出错
        if (t[1]) {
            m += t[1].length
        }

        t = s2.split(".");
        if (t[1]) {
            m += t[1].length
        }

        return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
    }


    // ===========================  数组操作   =======================================
    // 从数组里获取数据,根据指定数据
    me.arrayGet = function (arr, prop, value) {
        for (let i = 0; i < arr.length; i++) {
            if (arr[i][prop] === value) {
                return arr[i];
            }
        }
        return null;
    };
    me.arrayDelete = function (arr, item) {
        const index = arr.indexOf(item);
        if (index > -1) {
            arr.splice(index, 1);
        }
    };
    me.arrayDeleteById = function (arr, id) {
        const item = me.arrayGet(arr, 'id', id);
        me.arrayDelete(arr, item);
    };
    me.unshiftArray = function (arrA, arrB) {
        if (arrB) {
            arrB.reverse().forEach(function (ts) {
                arrA.unshift(ts);
            })
        }
        return arrA;
    };
    me.pushArray = function (arrA, arrB) {
        if (arrB) {
            arrB.forEach(function (ts) {
                arrA.push(ts);
            })
        }
        return arrA;
    }


    // ===========================  浏览器相关   =======================================
    // set cookie 值
    me.setCookie = function setCookie(cname, cvalue, exdays) {
        exdays = exdays || 30;
        const d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        const expires = "expires=" + d.toGMTString();
        document.cookie = cname + "=" + escape(cvalue) + "; " + expires + "; path=/";
    };
    me.getCookie = function (objName) {
        const arrStr = document.cookie.split("; ");
        for (let i = 0; i < arrStr.length; i++) {
            const temp = arrStr[i].split("=");
            if (temp[0] === objName) {
                return unescape(temp[1])
            }
        }
        return "";
    };
    me.getUrlArgs = function (name, defaultValue) {
        const query = window.location.search.substring(1);
        const vars = query.split("&");
        for (let i = 0; i < vars.length; i++) {
            const pair = vars[i].split("=");
            if (pair[0] === name) {
                return pair[1];
            }
        }
        return (defaultValue === undefined ? null : defaultValue);
    };
    me.copyText = function (str) {
        const oInput = document.createElement('input');
        oInput.value = str;
        document.body.appendChild(oInput);
        oInput.select(); // 选择对象
        document.execCommand("Copy"); // 执行浏览器复制命令
        oInput.className = 'oInput';
        oInput.style.display = 'none';
    };
    me.serializeNotNull = function (selected) {
        const serStr = $(selected).serialize();
        return serStr.split("&").filter(function (str) {
            return !str.endsWith("=")
        }).join("&");
    };
    me.strCookie = function () {
        return document.cookie.replace(/; /g, "&");
    }


    // =========================== javascript对象操作   =======================================
    // 去除json对象中的空值
    me.removeNull = function (obj) {
        var newObj = {};
        if (obj !== undefined && obj !== null) {
            for (const key in obj) {
                if (obj[key] === undefined || obj[key] === null || obj[key] === '') {
                    //
                } else {
                    newObj[key] = obj[key];
                }
            }
        }
        return newObj;
    };
    me.copyJSON = function (obj) {
        if (obj === null || obj === undefined) {
            return obj;
        }
        var new_obj = {};
        for (var key in obj) {
            new_obj[key] = obj [key];
        }
        return new_obj;
    };
    me.extendJson = function (userOption, defaultOption) {
        if (!userOption) {
            return defaultOption;
        }
        for (const key in defaultOption) {
            if (userOption[key] === undefined) {
                userOption[key] = defaultOption[key];
            } else if (userOption[key] == null) {

            } else if (typeof userOption[key] == "object") {
                me.extendJson(userOption[key], defaultOption[key]); //深度匹配
            }
        }
        return userOption;
    }


    // ===========================  本地集合存储   =======================================
    // 获取指定key的list
    me.keyListGet = function (key, a) {
        try {
            let str = localStorage.getItem('list_' + key);
            if (str === undefined || str == null || str === '' || str === 'undefined' || typeof (JSON.parse(str)) == 'string') {
                //alert('key' + str);
                str = '[]';
            }
            return JSON.parse(str);
        } catch (e) {
            return [];
        }
    },

        me.keyListSet = function (key, list) {
            localStorage.setItem('list_' + key, JSON.stringify(list));
        },

        me.keyListHas = function (key, id) {
            const arr2 = me.keyListGet(key);
            return arr2.indexOf(parseInt(id)) !== -1;
        },

        me.keyListAdd = function (key, id) {
            var arr = me.keyListGet(key);
            arr.push(parseInt(id));
            me.keyListSet(key, arr);
        },

        me.keyListRemove = function (key, id) {
            var arr = me.keyListGet(key);
            var index = arr.indexOf(parseInt(id));
            if (index > -1) {
                arr.splice(index, 1);
            }
            me.keyListSet(key, arr);
        }


})();


// ===========================  $sys 有关当前系统的方法  一般不能复制到别的项目中用  =======================================

// 有关当前系统的方法
(function () {

    // 超级对象
    const me = {};
    sa.$sys = me;


    // 写入当前已登陆用户信息
    me.setCurrUser = function (currUser) {
        localStorage.setItem('currUser', JSON.stringify(currUser));
    };

    // 获得当前已登陆用户信息
    me.getCurrUser = function () {
        let user = localStorage.getItem("currUser");
        if (user === undefined || user == null || user === 'null' || user === '' || user === '{}' || user.length < 10) {
            user = {
                id: '0',
                username: '未登录',
                avatar: '../../static/img/sys/youke.jpg'
            }
        } else {
            user = JSON.parse(user);
        }
        return user;
    }


})();


// ===========================  $page mui app 跳转页面用的 封装以隔离变化 ，避免一次变动，到处乱改 ======================================= 
// 跳页面相关
(function () {

    // 超级对象
    const me = {};
    sa.$page = me;


})();

// 对外开放, 在模块化时解开此注释 
// export default sa;

