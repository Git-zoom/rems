// layui 的全局table对象
let table = null
// 下拉框
let dropdown = null
//jQuery 对象
let $ = null
// 页面标题
let pageTitle = document.title

/**
 * @function layui的页面初始化
 * @param tableId 页面table的id
 * @param cols 字段列表
 * @param params 查询参数
 */
function initPage(tableId, cols, params) {
    layui.use(['table', 'layer'], function () {
        table = layui.table
        dropdown = layui.dropdown
        $ = layui.jquery
        // 表格初始化
        table.render({
            id: 'dataTable',
            elem: '#' + tableId,
            url: apiUrl + '/list',
            method: 'get',
            where: params,
            height: 'full-82',
            page: true,
            toolbar: 'default',
            size: 'lg', // 大尺寸表格
            cols: cols,
        })
        table.resize('dataTable')
        // 顶部工具栏事件
        table.on('toolbar(tabComponent)', function (obj) {
            let checkStatus = table.checkStatus(obj.config.id), data = checkStatus.data // 获取选中的数据
            switch (obj.event) {
                // 添加
                case 'add':
                    openWindow(apiUrl + '/to-add', '添加' + pageTitle)
                    break
                // 编辑用
                case 'update':
                    if (data.length === 0) {
                        layer.msg('请选择一行')
                    } else if (data.length > 1) {
                        layer.msg('只能同时编辑一个')
                    } else {
                        openWindow(apiUrl + '/to-edit/' + data[0].id, '编辑' + pageTitle)
                    }
                    break
                // 删除
                case 'delete':
                    let ids = []
                    for (let i = 0; i < data.length; i++) {
                        ids.push(data[i].id)
                    }
                    $.ajax({
                        url: apiUrl + '/delete-batch',
                        data: JSON.stringify(ids),
                        type: 'post',
                        contentType: 'application/json',
                        success: (res) => {
                            console.log(res)
                            if (res.code === 0) {
                                layer.msg('删除成功！', {
                                    icon: 1, time: 1500
                                }, function () {
                                    table.reload('dataTable', {})
                                })
                            } else {
                                layer.msg('删除失败！', {
                                    icon: 2, time: 1500
                                })
                            }
                        },
                        error: (error) => {
                            console.log(error)
                            layer.msg('删除失败！', {
                                icon: 2, time: 1500
                            })
                        }
                    })
                    break
            }
        })
        // 行内编辑查看事件
        table.on('tool(tabComponent)', function (obj) {
            let data = obj.data, // 获得当前行数据
                layEvent = obj.event // 获得 lay-event 对应的值
            // 查看事件
            if (layEvent === 'detail') {
                openWindow(apiUrl + '/to-view/' + data.id, '查看' + pageTitle)
            // 更多事件
            } else if (layEvent === 'more') {
                dropdown.render({
                    elem: this, // 触发事件的 DOM 对象
                    show: true, // 外部事件触发即显示
                    data: [
                        {title: '编辑', id: 'edit'},
                        {title: '删除', id: 'del'}
                    ],
                    click: function (menuData) {
                        // 删除一条记录
                        if (menuData.id === 'del') {
                            layer.confirm('真的删除行么', function (index) {
                                layer.close(index)
                                console.log(data.id)
                                $.ajax({
                                    url: apiUrl + '/delete',
                                    data: JSON.stringify(data.id),
                                    type: 'delete',
                                    dateType: 'json',
                                    contentType: 'application/json',
                                    success: () => {
                                        layer.msg('删除成功', {
                                            icon: 1, time: 1500
                                        }, function () {
                                            table.reload('dataTable', {})
                                        })
                                    },
                                    error: () => {
                                        layer.msg('删除失败', {
                                            icon: 2, time: 1500
                                        })
                                    }
                                })
                            })
                            // 编辑按钮事件
                        } else if (menuData.id === 'edit') {
                            openWindow(apiUrl + '/to-edit/' + data.id, '编辑' + pageTitle)
                        }
                    },
                    align: 'right', // 右对齐弹出
                    style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' // 设置额外样式
                })
            }
        })
    })
}

/**
 * @function 刷新表格
 */
function refreshTable() {
    layui.table.reload('dataTable', {})
}

/**
 * @function 打开新的页面
 * @param url 跳转的路径
 * @param title 跳转之后的页面标题
 */
function openWindow(url, title) {
    let page = window
    let iWidth = window.screen.availWidth
    let iHeight = window.screen.availHeight
    window.open(url, title, 'width=' + iWidth + ',height=' + iHeight, page)
}
