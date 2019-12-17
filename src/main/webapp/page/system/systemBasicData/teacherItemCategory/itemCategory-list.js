layui.use(['form', 'layer', 'table', 'laytpl'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;

    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher/biz/teacherItemCategory_findByPage.action',//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "radio", fixed: "left", width: 50},
            {field: 'id', title: '编号', align: 'center', width: 100},
            {field: 'title', title: '教师项目类别', minWidth: 100, align: 'center'},
            // {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]],done:function (res) {
            layer.close(tableLoad);    //返回数据关闭loading
        }
    });

    $(".addBtn").click(function () {
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此教师项目类别？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/teacher/biz/teacherItemCategory_delete.action", {
                    id: data.id  //将需要删除的deptId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        }
    });

    //添加用户
    function addUser(edit) {
        var index = layui.layer.open({
            title: "添加",
            type: 2,
            content: "itemCategory-add.html",
            maxmin: true,
            btn: ['确定', '取消'],
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                if (edit) {
                    body.find(".Id").val(edit.id);
                    body.find(".title").val(edit.title);
                    body.find(".updateFlag").val(1);//更新
                    form.render();
                }
            },
            yes: function (index, layero) {
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    // 权限控制
    var roleId = window.sessionStorage.getItem("roleId");
    $(function () {
        if (roleId === '6') {
            $('.editBtn').removeClass('layui-btn-disabled');
            $('.delBtn').removeClass('layui-btn-disabled');
        } else {
            $('.editBtn').addClass('layui-btn-disabled').prop("disabled", true);
            $('.delBtn').addClass('layui-btn-disabled').prop("disabled", true);
        }
    });

    //删除
    $(".delBtn").click(function(){
        if (roleId === '6') {
            var checkStatus = table.checkStatus('infoListTable'),
                data = checkStatus.data;
            if (data.length > 0) {
                layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                    $.get("/teacher/biz/teacherItemCategory_delete.action", {
                        id: data[0].id  //将需要删除的deptId作为参数传入
                    }, function (data) {
                        if (data.code === 0) {
                            layer.msg("删除成功");
                        } else {
                            layer.msg("删除失败");
                        }
                        tableIns.reload();
                        layer.close(index);
                    })
                })
            } else {
                layer.msg("请选择需要删除的记录");
            }
        } else {
            layer.msg('您没有权限操作，有问题请联系管理员！', { icon: 5, time: 2000, shade: [0.6, '#000', true] });
        }
    });

    //编辑
    $(".editBtn").click(function(){
        if (roleId === '6') {
            var checkStatus = table.checkStatus('infoListTable'),
                data = checkStatus.data;
            if(data.length > 0) {
                addUser(data[0]);
            }else{
                layer.msg("请选择需要编辑的记录");
            }
        } else {
            layer.msg('您没有权限操作，有问题请联系管理员！', { icon: 7, time: 2000, shade: [0.6, '#000', true] });
        }
    });
});