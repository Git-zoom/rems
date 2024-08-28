layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.jquery;

    // 表单校验
    form.verify({
        nickname: function (value) {
            if (value.length === 0) {
                return '昵称不能为空！'
            }
            if (value.length > 20) {
                return '昵称不能超过20个字符！'
            }
        }, mobile: function (value) {
            if (value.length > 11) {
                return '手机号码不能超过11位！'
            }
        }, email: function (value) {
            if (value.length > 50) {
                return '邮箱不能超过50个字符！'
            }
        }, remark: function (value) {
            if (value.length > 200) {
                return '备注不能超过200个字符！'
            }
        }
    });

    // 表单初始化
    $.ajax({
        url: '/user/get',
        type: 'post',
        data: JSON.stringify($('#recordId').val()),
        dataType: 'json',
        contentType: 'application/json;charset=UTF-8',
        success: function (res) {
            form.val('user-info-filter', {
                'id': res.id,
                'role': res.role,
                'username': res.username,
                'nickname': res.nickname,
                'gender': res.gender,
                'avatar': res.avatar,
                'mobile': res.mobile,
                'email': res.email,
                'remark': res.remark
            });
            $(function () {
                let userRole = res.role;
                if (userRole === '管理员') {
                    $('select[name="role"] option[value!="管理员"]').prop('disabled', true);
                } else {
                    $('select[name="role"] option[value="管理员"]').prop('disabled', true);
                }
            })
            form.render();
        },
        error: function (err) {
            layMsg.error('获取用户信息失败！' + err);
        }
    });

    // 提交修改
    form.on('submit(set-my-info)', function (data) {
        $.ajax({
            url: '/user/update',
            type: 'post',
            data: JSON.stringify(data.field),
            dateType: 'json',
            contentType: 'application/json',
            success: (res) => {
                if (res.code === 0) {
                    layer.msg('修改成功！', {
                        icon: 1, time: 1500
                    })
                } else {
                    layer.msg(res.msg, {
                        icon: 2, time: 1500
                    })
                }
            },
            error: (error) => {
                layer.msg('修改失败！' + error, {
                    icon: 2, time: 1500
                })
            }
        })
        return false
    })

    // 重置表单
    $("#reset-btn").on('click', function () {
        form.val('user-info-filter', {
            'nickname': '',
            'gender': '',
            'avatar': '',
            'mobile': '',
            'email': '',
            'remark': ''
        });
        form.render();
    })

    // 头像选择
    $(function () {
        $('.face img').addClass('change2')
        let user = $('.path_face')
        $('.face img').on('click', function () {
            let src = $(this).attr('src').substring(8)
            user.val(src)
            $(this)
                .addClass('change')
                .removeClass('change2')
                .siblings()
                .addClass('change2')
                .removeClass('change')
        })
    })
});