layui.use([ 'form', 'layer', 'upload', 'laydate'], function () {
    var $ = layui.$,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload,
        laydate = layui.laydate;

    var updateFlag = $(".updateFlag").val().valueOf();//0:添加 1:更新
    //渲染下拉框
    $.post("/teacher/biz/match_findAll.action", function (data) {//--赛事
        $.each(data.data, function (index, item) {
            $('#matchBunk').append(new Option(item.matchName, item.matchId));
        });
        if (updateFlag === '1') $('#matchBunk').val($('.matchHide').val()).prop("disabled",true);//使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });
    var deptId = window.sessionStorage.getItem("deptId");
    $.post("/teacher/biz/teacher_findAllByDept.action?deptId="+deptId, function (data) {//--指导老师
        $.each(data.data, function (index, item) {
            $('#teacherBunk').append(new Option(item.teacherName+"_"+item.deptName, item.teacherId));
        });
        //如果是新增，指导老师老师默认选中自己
        if (updateFlag === '0') $('#teacherBunk').val(window.sessionStorage.getItem("teacherId"));
        else $('#teacherBunk').val($('.teacherHide').val());//使用中间变量让其默认选中
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher/biz/competitionPrizeLevel_findAll.action", function (data) {//--竞赛获奖级别
        $.each(data.data, function (index, item) {
            $('#prizeLevel').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#prizeLevel').val($('.prizeLevelHide').val());
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher/biz/competitionPrizeGrade_findAll.action", function (data) {//--竞赛获奖等次
        $.each(data.data, function (index, item) {
            $('#prizeGrade').append(new Option(item.title, item.id));
        });
        if (updateFlag === '1') $('#prizeGrade').val($('.prizeGradeHide').val());
        //重新渲染select
        form.render('select');
    });

    //执行一个laydate实例
    laydate.render({elem: '#prizeTime',trigger: 'click', type: 'year', done: function (value, date, endDate) {}});

    form.on("submit(addUser)", function (data) {
        //弹出loading
        var index = layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
        // 实际使用时的提交信息
        $.post(updateFlag === '0' ? "/teacher/biz/competition_save.action" : "/teacher/biz/competition_update.action", {

            itemId: updateFlag === '0' ? null : $(".Id").val(),//id
            itemName: $(".itemName").val(),
            itemPrizeTime: $(".prizeTime").val(),
            prizeImg: $(".prizeImg").val(),
            awardee: $(".awardee").val(),//参赛队员

            manyFilePath: $(".manyFilePath").val(),//文件路径
            manyFileName: $(".manyFileName").val(),//文件原名称
            manyFileType: $(".manyFileType").val(),//文件类型

            prizeLevelId: data.field.prizeLevel,
            prizeGradeId: data.field.prizeGrade,
            matchId: data.field.matchId,
            teacherId: data.field.teacherId
        }, function (res) {
            if (res.code === 0) {
                top.layer.close(index);
                top.layer.msg(updateFlag === '0' ? "添加成功！" : "修改成功！");
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            } else {
                layer.close(index);
                top.layer.msg("操作失败！");
            }
        });
        return false;
    });

    //当用户选中赛事时，内容根据竞赛类型改变  -- competitionType:lay-filter绑定的名称
    form.on("select(matchType)", function (data) {
        var matchId = data.value;
        $.ajax({
            type: "POST",
            url: '/teacher/biz/match_findById.action',//数据接口
            data: {matchId: matchId},
            success: function (data) {
                if (data.code === 0) {
                    var match = data.data;
                    //1学生赛事,teacherId用作指导老师；2教师赛事,teacherId用作主持人
                    if (match.matchAttribute === 1) {
                        $('#workName1').html("作品名称");
                        $('#workName2').html("指导老师");
                        // $('#workName3').html("获奖学生");
                        // $('#instructor').show();    //指导老师显示
                    } else {
                        $('#workName1').html("比赛科目");
                        $('#workName2').html("主持人");
                        // $('#workName3').html("成员");
                        // $('#instructor').hide();    //指导老师隐藏
                    }
                    //如果是新增，指导老师老师默认选中自己
                    if (updateFlag === '0') $('#teacherBunk').val(window.sessionStorage.getItem("teacherId"));
                }
            }});
    });

    /**
     * 附件上传接口
     */
    var fileUrls = "";  //用于保存所有文件返回的地址
    var fileNames = "";  //用于保存所有文件返回的原文件名
    var fileTypes = "";  //用于保存所有文件返回的文件类型(后缀)
    var fileLoad = "";  //弹出loading
    var demoListView = $('#demoList'),
        uploadListIns = upload.render({
            elem: '#testList',
            // url: '/teacher/upload_uploadPaperFile.action',
            url: '/teacher/upload_uploadAnnex.action',
            accept: 'file', //指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            size: 10240,    //设置单个文件最大可允许上传的大小，单位 KB
            number:10,    //设置单最大上传的数量
            multiple: true, //是否允许多文件上传
            auto: false,    //是否选完文件后自动上传。如果设定 false，那么需要设置 bindAction 参数来指向一个其它按钮提交上传
            bindAction: '#testListAction',
            choose: function(obj){  //选择文件后回调
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    var tr = $(['<tr id="upload-'+ index +'">',
                        '<td>'+ file.name +'</td>',
                        '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                        '<td>等待上传</td>',
                        '<td>',
                        '<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>',
                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                        '</td>',
                        '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            ,before: function(obj){
                fileLoad = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});
            }
            ,done: function(res, index, upload){    //执行上传请求后回调
                top.layer.close(fileLoad);
                if(res.code == 0){                  //上传成功
                    //保存这些文件的路径、原文件名、文件类型（后缀）
                    fileUrls = fileUrls+""+res.src+",";
                    fileNames = fileNames+""+res.fileName+",";
                    fileTypes = fileTypes+""+res.fileType+",";
                    $('#manyFilePath').val(fileUrls);
                    $('#manyFileName').val(fileNames);
                    $('#manyFileType').val(fileTypes);

                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html('');                 //清空操作
                    return delete this.files[index];    //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            ,error: function(index, upload){    //执行上传请求出现异常回调
                top.layer.close(fileLoad);
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }
        });
    // 附件上传要求--添加验证规则
    form.verify({
        userFile: function (value) {if (value == null || value=="") return "请上传获奖证书！";}
    })

    //上传获奖证书图片
    // var uploadInst = upload.render({
    //     elem: '#imgUpload',
    //     // url: '/teacher/upload_uploadPrizeImg.action',
    //     url: '/teacher/upload_uploadImage.action',
    //     acceptMime: 'image/*',  //只显示图片文件
    //     auto: false,
    //     bindAction: '#startUpload',
    //     //文件提交上传前的回调
    //     before: function(obj){
    //         layer.load(); //上传loading
    //         //预读本地文件示例，不支持ie8
    //         obj.preview(function(index, file, result){
    //             // console.log(file);//里面的名称是选中时文件的名称
    //             $('#imgPreview').attr('src', result); //图片链接（base64）
    //         });
    //     },
    //     //执行上传请求后的回调。
    //     done: function(res){
    //         if (res.code===0){
    //             layer.msg('上传成功!');
    //             $('.prizeImg').val(res.src);
    //         }else {
    //             layer.msg('上传失败!');
    //         }
    //         layer.closeAll('loading'); //关闭loading
    //     },
    //     //执行上传请求出现异常的回调
    //     error: function(){
    //         //演示失败状态，并实现重传
    //         var reUpload = $('#reUpload');
    //         reUpload.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
    //         reUpload.find('.demo-reload').on('click', function(){
    //             uploadInst.upload();
    //         });
    //     }
    // });
    // 附件上传要求--添加验证规则

    //拖拽上传pdf
    // upload.render({
    //     elem: '#fileUpload',
    //     url: '/teacher/upload_uploadFile.action',
    //     accept: 'file',//指定允许上传时校验的文件类型，可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
    //     exts: 'pdf',
    //     //文件提交上传前的回调
    //     before: function(obj){
    //         layer.load(); //上传loading
    //     },
    //     //执行上传请求后的回调
    //     done: function (res) {
    //         if (res.code === 0) {
    //             layer.msg('上传成功!');
    //             $('#filePath').val(res.src);
    //         } else {
    //             layer.msg('上传失败!');
    //         }
    //         layer.closeAll('loading'); //关闭loading
    //     }
    // });
});