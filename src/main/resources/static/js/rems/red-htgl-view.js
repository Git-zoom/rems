function view(apiUrl) {
    layui.use(['form', 'laydate'], function () {
        let layer = layui.layer, laydate = layui.laydate;
        let form = layui.form;
        let $ = layui.jquery;
        let recordId = $("#recordId").val();
        // 日期
        laydate.render({
            elem: '#date'
        });

        // 回显数据
        $.ajax({
            url: apiUrl + '/get',
            data: JSON.stringify(recordId),
            dateType: "json",
            contentType: 'application/json',
            type: 'post',
            success: (res) => {
                form.val('user-view-filter', {
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
            },
            error: (err) => {
                console.log(err);
                layer.msg("查询失败", {
                    icon: 2, time: 1500
                });
            }
        })
    });
}

