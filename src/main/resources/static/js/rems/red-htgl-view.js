function view(apiUrl) {
    layui.use(['form', 'layedit', 'laydate'], function () {
        let layer = layui.layer, laydate = layui.laydate;
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
                for (let param in res) {
                    $('#' + param + '').val(res[param]);
                }
                $('.layui-unselect').val(res.type);
            },
            error: (err) => {
                console.log(err);
                layer.msg("查询失败", {
                    icon: 2, time: 1500
                });
            }
        })
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

