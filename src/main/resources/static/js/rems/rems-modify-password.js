layui.use(['form'], function () {
    let form = layui.form;
    let $ = layui.jquery;

    form.verify({
        password: function (value) {
            if (value.length < 6 || value.length > 16) {
                return '密码长度在6到16个字符之间';
            }
        }, rePassword: function (value) {
            if ($('#password').val() !== value) {
                return '两次密码输入不一致';
            }
        }
    })

    $('#set-modify-password').on('click', function () {
        form.submit('modify-password-filter', function (data) {
            let userId = data.field.id;
            let salt = '1a2b3c4d'
            let oldPasswordStr = '' + salt.charAt(0) + salt.charAt(2) + data.field.oldPassword + salt.charAt(5) + salt.charAt(4)
            let passwordStr = '' + salt.charAt(0) + salt.charAt(2) + data.field.password + salt.charAt(5) + salt.charAt(4)
            let oldPasswordEncrypt = md5(oldPasswordStr)
            let passwordEncrypt = md5(passwordStr)
            let reqParam = {
                userId: userId, oldPassword: oldPasswordEncrypt, password: passwordEncrypt
            }
            $.ajax({
                url: '/user/modify-password',
                type: 'POST',
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(reqParam),
                success: function (res) {
                    if (res.code === 0) {
                        layer.msg('修改成功，请重新登录！', {
                            icon: 1, time: 1500
                        })
                        form.val('modify-password-filter', {
                            oldPassword: '', password: '', rePassword: ''
                        })
                    } else {
                        layer.msg('修改失败！' + res.msg, {
                            icon: 2, time: 1500
                        })
                    }
                },
                error: function (error) {
                    layer.msg('修改失败！' + error.responseJSON.msg, {
                        icon: 2, time: 1500
                    })
                }
            })
        })
        return false;
    })

})