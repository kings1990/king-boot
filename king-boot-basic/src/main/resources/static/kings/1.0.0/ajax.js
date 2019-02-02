//页面加载所要进行的操作
$(function () {
    //设置ajax当前状态(是否可以发送);
    ajaxStatus = true;
});

var DEFAULT_OPTIONS = {
    url:"",
    data:"",
    type:"post",
    dataType:"json",
    async:true,//异步请求
    alone:false,//独立提交（一次有效的提交）
    cache:false,
    success:function (data) {
        /*console.log('请求成功');*/
        // setTimeout(function () {
        //     layer.msg(data.message);//通过layer插件来进行提示信息
        // },500);
        if (data.code) {//服务器处理成功
            setTimeout(function () {
                if (data.redirectUrl) {
                    location.replace(data.redirectUrl);
                } else {
                    layer.msg(data.data, {time: 5000, icon: 1});
                }
            }, 1500);
        } else {//服务器处理失败
            if (alone) {//改变ajax提交状态
                ajaxStatus = true;
            }
        }
    },
    error:function (data) {
        /*console.error('请求成功失败');*/
        /*data.code;//错误状态吗*/
        layer.closeAll('loading');
        setTimeout(function () {
            if (data.status === 404) {
                layer.msg('请求失败，请求未找到', {time: 5000, icon: 5});
            } else if (data.status === 400) {
                layer.msg(data.responseText, {time: 5000, icon: 5});
            } else if (data.status === 401) {
                layer.msg('未登录', {time: 5000, icon: 5});
            } else {
                layer.msg('请求失败,网络连接超时', {time: 5000, icon: 5});
            }
            ajaxStatus = true;
        }, 500);
    }
};

// ajax封装
function ajax(options) {
    options = {
        url: options.url || DEFAULT_OPTIONS.url,
        data: options.data || DEFAULT_OPTIONS.data,
        type: options.type || DEFAULT_OPTIONS.type,
        dataType: options.dataType || DEFAULT_OPTIONS.dataType,
        async: options.async || DEFAULT_OPTIONS.async,
        alone: options.alone || DEFAULT_OPTIONS.alone,
        cache: options.cache || DEFAULT_OPTIONS.cache,
        success: options.success || DEFAULT_OPTIONS.success,
        error: options.error || DEFAULT_OPTIONS.error,
    };
    
    var url = options.url;
    var reg = new RegExp(("{(\\w+)}"),"gi");
    var matchArray = url.match(reg);
    if(matchArray){
        for(j = 0; j < matchArray.length; j++) {
            var paramStrWithDollar = matchArray[j];
            paramStr = paramStrWithDollar.replace("{","").replace("}","");
            url = url.replace(paramStrWithDollar,options.data[paramStr]);
        }
        options.url = url;
    }
    
    
    
    /*判断是否可以发送请求*/
    if (!ajaxStatus) {
        return false;
    }
    ajaxStatus = false;//禁用ajax请求
    /*正常情况下1秒后可以再次多个异步请求，为true时只可以有一次有效请求（例如添加数据）*/
    if (!options.alone) {
        setTimeout(function () {
            ajaxStatus = true;
        }, 1000);
    }
    if(options.url){
        $.ajax({
            'url': options.url,
            'data': options.data,
            'type': options.type,
            'dataType': options.dataType,
            'async': options.async,
            'success': options.success,
            'error': options.error,
            'jsonpCallback': 'jsonp' + (new Date()).valueOf().toString().substr(-4),
            'beforeSend': function () {
                layer.msg('加载中', {
                    //通过layer插件来进行提示正在加载
                    icon: 16,
                    shade: 0.01
                });
            },
        });
    }
    
}

// submitAjax(post方式提交)
function submitAjax(form, success, cache, alone) {
    cache = cache || true;
    var form = $(form);
    var url = form.attr('action');
    var data = form.serialize();
    var options = {
        url:url,
        data:data,
        success:success,
        cache:cache,
        alone:alone,
        async:false,
        type:'post',
        dataType:"json"
    };
    ajax(options);
}

/*//调用实例
$(function () {
    $('#form-login').submit(function () {
        submitAjax('#form-login');
        return false;
    });
});*/

// ajax提交(post方式提交)
function post(url, data, success, cache, alone) {
    var options = {
        url:url,
        data:data,
        success:success,
        cache:cache,
        alone:alone,
        async:false,
        type:'post',
        dataType:"json"
        
    };
    ajax(options);
}

// ajax提交(get方式提交)
function get(url, success, cache, alone) {
    var options = {
        url:url,
        data:{},
        success:success,
        cache:cache,
        alone:alone,
        async:false,
        type:'get',
        dataType:"json"

    };
    ajax(options);
}

// jsonp跨域请求(get方式提交)
function jsonp(url, success, cache, alone) {
    var options = {
        url:url,
        data:{},
        success:success,
        cache:cache,
        alone:alone,
        async:false,
        type:'get',
        dataType:"jsonp"

    };
    ajax(options);
}