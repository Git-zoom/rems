document.write("<script src='../../layui/layui.js'></script>")

function successMsg(msg) {
    layer.msg(msg, {
        icon: 1, time: 1500, offset: ['20px'], anim: 0
    });
}

function errorMsg(msg) {
    layer.msg(msg, {
        icon: 2, time: 1500, offset: ['20px'], anim: 0
    });
}

function warnMsg(msg) {
    layer.msg(msg, {
        icon:0, time: 1500, offset: ['20px'], anim: 0
    });
}