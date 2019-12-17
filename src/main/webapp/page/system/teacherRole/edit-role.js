layui.use(['form','layer','upload','laydate'],function(){
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        laydate = layui.laydate;

    //管理员可以查看全部角色
    $.post("/teacher/role_findBySysAdmin.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#roleId').append(new Option(item.roleName, item.roleId));
        });
        $('#roleId').val($('.roleHide').val());
        form.render('select');
    });

    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post("/teacher/biz/teacher_updateRole.action",{

            teacherId : $(".Id").val(),//id
            roleId : $("#roleId").val(),//角色
            roleName : $("#roleId option:checked").text(),//角色名称
        },function(res){
            if (res.code === 0){
                top.layer.close(index);
                top.layer.msg("修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

});