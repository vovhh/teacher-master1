layui.use(['form','layer','upload','util','laydate'],function(){
    var $ = layui.$,
        form = layui.form,
        layer = layui.layer,
        upload = layui.upload,
        util = layui.util,
        laydate = layui.laydate;

    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新

    //渲染复选框数据
    $.post("/teacher/biz/dept_findAllParent.action", function (data) {
        $.each(data.data, function (index, item) {
            $('#deptId').append(new Option(item.deptName, item.deptId));
        });
        if (updateFlag === '1')
            $('#deptId').val($('.deptHide').val());//如果是更新，使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });


    form.on("submit(addUser)",function(data){
        //弹出loading
        var index = layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        // 实际使用时的提交信息
        $.post(updateFlag==='0'?"/teacher/biz/teacher_save.action":"/teacher/biz/teacher_update.action",{

            teacherId : updateFlag==='0'?null:$(".Id").val(),//id
            teacherCode : $(".teacherCode").val(),  //登录名
            teacherName : $(".teacherName").val(),
            deptId : $(".deptId").val(),//部门
            // unitIds : unitId.join(','),//教研室
            roleId : 5,//角色
            roleName : '学院管理员',//角色名称
            teacherSex : 1,  //性别
            theTeacherBirth : util.toDateString(new Date(), "yyyy-MM-dd"),
            theTeacherEntryTime : util.toDateString(new Date(), "yyyy-MM-dd"),
            teacherImg : "/teacher/images/face.jpg",  //头像
            highEdu : "暂无",
            firstEdu : "暂无",
            technicalPost : "暂无",
            administPost : "暂无",
            teacherResume : "暂无",
            other : "暂无",
            canLook : 0,
        },function(res){
            if (res.code === 0){
                top.layer.close(index);
                top.layer.msg(updateFlag==='0'?"添加成功！":"修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }else if (res.code === 444) {
                layer.close(index);
                top.layer.msg(res.msg);
            }else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });
});