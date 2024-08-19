// layui 的全局table对象
let table = null
let dropdown = null // 下拉框
let $ = null
let pageTitle = document.title

/**
 * @function layui的页面初始化
 * @param tableId 页面table的id
 * @param cols 字段列表
 */
function initPage(tableId, cols) {
  layui.use(['table', 'layer'], function () {
    table = layui.table
    dropdown = layui.dropdown //下拉菜单
    $ = layui.jquery
    // 页面初始化 --> 加载数据
    table.render({
      elem: '#' + tableId,
      id: 'dataTable',
      height: 'full-82',
      url: apiUrl + '/list',
      page: true, // 开启分页
      toolbar: 'default',
      size: 'lg', // 大尺寸表格
      cols: cols,
      done: function (res, curr, count) {
        //解决操作栏因为内容过多换行问题
        let tableMainTr = $('.layui-table-main tr')
        tableMainTr.each(function (index, val) {
          $($('.layui-table-fixed-l .layui-table-body tbody tr')[index]).height(
              $(val).height()
          )
          $($('.layui-table-fixed-r .layui-table-body tbody tr')[index]).height(
              $(val).height()
          )
        })
        //解决fixed固定，而导致的th高度不一致
        tableMainTr.each(function (index, val) {
          $('.layui-table-fixed').each(function () {
            $($(this).find('.layui-table-header thead th')[index]).height(
                $(val).height() - 1
            )
          })
        })
        tableMainTr.each(function (index, val) {
          $('.layui-table-fixed').each(function () {
            $($(this).find('.layui-table-header thead tr')[index]).height(
                $(val).height()
            )
          })
        })
        tableMainTr.each(function (index, val) {
          $('.layui-table-fixed').each(function () {
            $($(this).find('.layui-table-body tbody tr')[index]).height(
                $(val).height()
            )
          })
        })
        tableMainTr.each(function (index, val) {
          $('.layui-table-fixed').each(function () {
            $($(this).find('.layui-table-body tbody th')[index]).height(
                $(val).height() - 1
            )
          })
        })
      }
    })
    table.resize('dataTable')
    // 顶部工具栏事件
    table.on('toolbar(tabComponent)', function (obj) {
      let checkStatus = table.checkStatus(obj.config.id),
          data = checkStatus.data // 获取选中的数据
      switch (obj.event) {
          // 添加用户
        case 'add':
          openWindow(apiUrl + '/to-add', '添加' + pageTitle)
          break
          // 编辑用户
        case 'update':
          if (data.length === 0) {
            layer.msg('请选择一行')
          } else if (data.length > 1) {
            layer.msg('只能同时编辑一个')
          } else {
            openWindow(apiUrl + '/to-edit/' + data[0].id, '编辑' + pageTitle)
          }
          break
          // 删除多个用户
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
                layer.msg(
                    '删除成功！',
                    {
                      icon: 1,
                      time: 1500
                    },
                    function () {
                      table.reload('dataTable', {})
                    }
                )
              } else {
                layer.msg('删除失败！', {
                  icon: 2,
                  time: 1500
                })
              }
            },
            error: (error) => {
              console.log(error)
              layer.msg('删除失败！', {
                icon: 2,
                time: 1500
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
        // 下拉菜单
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
                    layer.msg(
                        '删除成功',
                        {
                          icon: 1,
                          time: 1500
                        },
                        function () {
                          table.reload('dataTable', {})
                        }
                    )
                  },
                  error: () => {
                    layer.msg('删除失败', {
                      icon: 2,
                      time: 1500
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
