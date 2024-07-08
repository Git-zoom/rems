function edit(apiUrl,verifys){
    layui.use(['form', 'layedit', 'laydate'], function () {
        let form = layui.form
            , layer = layui.layer
            , laydate = layui.laydate;
        let $ = layui.jquery;
        // 日期
        laydate.render({
            elem: '#quesDate'
        });
        laydate.render({
            elem: '#msgDate'
        });

        // 表单校验
        form.verify(verifys);

        let url;
        // 判断是新建页面还是编辑页面
        if (window.location.href.indexOf("add") > -1) {
            url = apiUrl+"/addData";
        }else{
            url = apiUrl+"/update";
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
                    if (res == 'ok') {
                        layer.msg("保存成功！", {
                            icon: 1,
                            time: 1000
                        });
                        setTimeout(() => {
                            window.opener.refreshList();
                            window.close();
                            window.history.go(-1);
                        }, 2000)
                    } else if (res == 'error') {
                        layer.msg("保存失败！", {
                            icon: 2,
                            time: 1000
                        });
                    } else {
                        layer.msg("用户名已存在！", {
                            icon: 2,
                            time: 1000
                        });
                    }
                },
                error: (error) => {
                    layer.msg("保存失败！", {
                        icon: 2,
                        time: 1000
                    });
                }
            })
            return false;
        });

        // 编辑页面需要给表单赋值
        if (window.location.href.indexOf("edit") > -1) {
            let recordId = $("#recordId").val();
            $.ajax({
                url: apiUrl+'/viewData',
                data: JSON.stringify(recordId),
                dateType: "json",
                contentType: 'application/json',
                type: 'post',
                success: (res) => {
                    for(let param in res){
                        $('#' + param + '').val(res[param]);
                    }
                    $('.layui-unselect').val(res.type);
                },
                error: () => {
                    layer.msg("查询失败", {
                        icon: 2,
                        time: 1000
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