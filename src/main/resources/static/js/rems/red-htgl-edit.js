function edit(apiUrl, verifys) {
    layui.use(['form'], function () {
        let form = layui.form, layer = layui.layer;
        let $ = layui.jquery;

        // 表单校验
        form.verify(verifys);

        let url;
        // 判断是新建页面还是编辑页面
        if (window.location.href.indexOf("add") > -1) {
            url = apiUrl + "/add";
        } else {
            url = apiUrl + "/update";
        }

        // 监听提交
        form.on('submit(demo2)', function (data) {
            $.ajax({
                url: url,
                type: 'post',
                data: JSON.stringify(data.field),
                dateType: "json",
                contentType: 'application/json',
                success: (res) => {
                    if (res.code === 0) {
                        layer.msg("保存成功！", {
                            icon: 1, time: 1500
                        });
                        setTimeout(() => {
                            window.opener.refreshTable();
                            window.close();
                            window.history.go(-1);
                        }, 1000)
                    } else {
                        layer.msg(res.msg, {
                            icon: 2, time: 1500
                        });
                    }
                },
                error: (error) => {
                    layer.msg("保存失败！", {
                        icon: 2, time: 1500
                    });
                }
            })
            return false;
        });

        // 编辑页面需要给表单赋值
        if (window.location.href.indexOf("edit") > -1) {
            let recordId = $("#recordId").val();
            $.ajax({
                url: apiUrl + '/get',
                data: JSON.stringify(recordId),
                dateType: "json",
                contentType: 'application/json',
                type: 'post',
                success: (res) => {
                    form.val('user-edit-filter', {
                        "username": res.username, // "name": "value"
                        "nickname": res.nickname,
                        "password": res.password,
                        "gender": res.gender,
                        "avatar": res.avatar,
                        "email": res.email,
                        "mobile": res.mobile,
                        "status": res.status,
                        "role": res.role,
                        "remark": res.remark,
                    });

                    // 表单渲染
                    form.render();
                },
                error: () => {
                    layer.msg("查询失败", {
                        icon: 2, time: 1500
                    });
                }
            })
        }

        // 头像选择
        $(function () {
            $(".face img").addClass("change2");
            let user = $(".path_face");
            $(".face img").on('click', function () {
                let src = $(this).attr("src").substring(8);
                user.val(src);
                $(this).addClass("change").removeClass("change2").siblings().addClass("change2").removeClass("change");
            });
        })
    });
}