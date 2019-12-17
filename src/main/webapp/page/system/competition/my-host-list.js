layui.config({
    base: '/teacher/layui/layui_exts/'
}).extend({
    excel: 'excel'
}).use(['form','layer','table','excel','laytpl','util'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        excel = layui.excel,
        laytpl = layui.laytpl,
        util = layui.util,
        table = layui.table;

    var teacherId = window.sessionStorage.getItem("teacherId");
    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher/biz/competition_findByCaptain.action?teacherId='+teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        // toolbar: '#toolbar',//头部工具栏
        title: '我的竞赛获奖记录',//用于导出
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            {field: 'itemName', title: '比赛科目', minWidth:100, align:"center"},
            {field: 'matchName', title: '赛事名称', minWidth:100, align:"center", templet:function(d){
                    return d.match.matchName;
                }},
            {field: 'teacherName', title: '主持人', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.teacherName;
                }},
            {field: 'awardee', title: '参赛队员', minWidth:100, align:"center"},
            {field: 'prizeTime', title: '获奖时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.prizeTime, "yyyy年")
                }},
            {field: 'prizeLevel', title: '获奖级别', minWidth:100, align:'center', templet:function(d){
                    return d.prizeLevel.title;
                }},
            {field: 'prizeGrade', title: '获奖等次', minWidth:100, align:'center', templet:function(d){
                    return d.prizeGrade.title;
                }},
            {title: '操作', minWidth:100, templet:'#infoListBar',fixed:"right",align:"center"}
        ]],done:function (res) {
            layer.close(tableLoad);    //返回数据关闭loading
        }
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {key: dataReload.val()}
            }, 'data');
        }
    };

    $('.reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function(){
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher/biz/competition_delete.action",{
                    itemId : data.itemId  //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code === 0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        // } else if (layEvent === 'addStudent') { //编辑项目成员--弃用
        //     addStudent(data);
        } else if (layEvent === 'detail') { //详情
            detailUser(data);
        }
    });

    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].itemId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher/biz/competition_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的记录");
        }
    });

    /**
     * exportCode:
     *  1-系统管理员导出整个表            --只要传个1过去
     *  2-部门领导和部门负责人导出本学院全部 --要传部门id和2过去
     *  3-教师个人导出全部               --要传教师id和3过去
     *  4-根据id导出                    --传id和4过去
     */
    //批量导出
    $(".exportBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].itemId);
            }
            $.ajax({
                url: '/teacher/biz/competition_findByExport.action',
                data: {ids: newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        var dataIndex = 0;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            itemId: function (value, line, data) {
                                dataIndex += 1;
                                return dataIndex;
                            },
                            match: function (value, line, data) {
                                return value.matchName;
                            },
                            itemName: function(value, line, data) {
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            },
                            teacher: function (value, line, data) {
                                return value.teacherName;
                            },
                            awardee: 'awardee',
                            prizeTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年");
                            },
                            prizeLevel: function (value, line, data) {
                                return value.title;
                            },
                            prizeGrade: function (value, line, data) {
                                return value.title;
                            }

                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({itemId: '编号', match: '赛事名称', itemName: '比赛科目', teacher: '主持人', awardee: '参赛队员', prizeTime: '获奖时间', prizeLevel: '获奖级别', prizeGrade: '获奖等次'});

                        excel.exportExcel({
                            sheet1: data
                        }, '我主持的教师竞赛.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });

    //添加用户
    function addUser(edit){
        // area: ['800px', '700px'],
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "competition-add.html",
            maxmin: true,
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.itemId);
                    body.find(".itemName").val(edit.itemName);
                    body.find(".prizeTime").val(util.toDateString(edit.prizeTime, "yyyy"));
                    body.find('#workName1').html("比赛科目");
                    body.find('#workName2').html("主持人");
                    body.find(".awardee").val(edit.awardee);//参赛队员
                    body.find(".teacherHide").val(edit.teacher.teacherId);//使用中间变量记录--因为赋值的时候competition-add.html的下拉框渲染还没开始，无法选中
                    body.find(".matchHide").val(edit.match.matchId);//使用中间变量记录赛事名称--因为赋值的时候competition-add.html的下拉框渲染还没开始，无法选中
                    body.find(".prizeLevelHide").val(edit.prizeLevel.id);//使用中间变量
                    body.find(".prizeGradeHide").val(edit.prizeGrade.id);//使用中间变量
                    // body.find("#instructor").hide();//指导老师隐藏,教师竞赛没有指导老师
                    body.find(".updateFlag").val(1);//更新

                    /**
                     * 回显示文件
                     */
                    $.ajax({
                        type: "POST",
                        url: '/teacher/biz/competitionAnnex_findByCompetitionId.action',//数据接口
                        data: {competitionId: edit.itemId},
                        success: function (data) {
                            if (data.code === 0) {
                                var fileLength = data.data.length;
                                $.each(data.data, function (index, item) {

                                    var oldtr = $(['<tr id="upload-'+ index +'">',
                                        '<td>'+ item.fileName +'</td>',
                                        // '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                                        '<td></td>',
                                        '<td>已上传</td>',
                                        '<td>',
                                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                                        '</td>',
                                        '</tr>'].join(''));

                                    //删除
                                    oldtr.find('.demo-delete').on('click', function(){
                                        $.post("/teacher/biz/competitionAnnex_delete.action", {
                                            competitionAnnexId: item.competitionAnnexId
                                        }, function (data) {
                                            if (data.code === 0) {
                                                layer.msg("删除成功");
                                                fileLength -= 1;
                                                if (fileLength == 0) {
                                                    layer.msg("您删除了所有附件！");
                                                    body.find("#manyFilePath").val('');
                                                }
                                            } else {
                                                layer.msg("删除失败");
                                            }
                                            tableIns.reload();
                                            layer.close(index);
                                        });
                                        oldtr.remove();//删除当前
                                    });
                                    body.find("#demoList").append(oldtr);
                                    // 定义规则，如果当前项目有附件，它就会被赋值，防止xx-add.html验证规则出错
                                    body.find("#manyFilePath").val('haveFile');
                                });
                            } else {
                                layer.msg("未知错误，请联系管理员！" + data.msg);
                            }
                        },
                        error: function () {
                            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
                        }
                    });
                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //竞赛项目详情
    function detailUser(data) {
        var index = layui.layer.open({
            title: "详情",
            type: 2,
            content: "competition-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find(".itemId").val(data.itemId);
                body.find(".matchName").val(data.match.matchName);
                body.find(".itemName").val(data.itemName);
                body.find('#workName1').html("比赛科目");
                body.find('#workName2').html("主持人");
                body.find(".teacherName").val(data.teacher.teacherName);//主持人
                body.find(".awardee").val(data.awardee);//参赛队员
                body.find(".prizeTime").val(util.toDateString(data.prizeTime, "yyyy年"));
                body.find(".prizeLevel").val(data.prizeLevel.title);
                body.find(".prizeGrade").val(data.prizeGrade.title);
                body.find(".prizeImg").val(data.prizeImg);
                body.find("#imgPreview").attr('src', data.prizeImg);//图片显示

                /**
                 * 附件详情
                 */
                var str = "";
                $.ajax({
                    type: "POST",
                    url: '/teacher/biz/competitionAnnex_findByCompetitionId.action',
                    data: {competitionId: data.itemId},
                    success: function (data) {
                        if (data.code === 0) {
                            $.each(data.data, function (index, item) {
                                str += '<a class="fileCss" href="/teacher/download/download_downloadFile.action?' +
                                    'downloadPath=' + item.filePath + '&filename=' + encodeURI(encodeURI(item.fileName)) + '">' + item.fileName + '</a><br>';
                            });
                            body.find("#manyFile").html(str);
                        }
                    }});

                form.render();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index", index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize", function () {
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    // 添加竞赛项目成员--弃用
    function addStudent(edit){
        window.sessionStorage.setItem("thisRaceItemId", edit.itemId);//暂时保存当前项目id，跳转之后马上删除！
        window.sessionStorage.setItem("thisRaceItemName", edit.itemName);//暂时保存当前项目名称，跳转之后马上删除！
        location.href = "/teacher/page/system/competition/member/member-list.html";
    }

    //鼠标经过查看图片
    // function hoverOpenImg(){
    //     var img_show = null; // tips提示
    //     $('td img').hover(function(){
    //         //alert($(this).attr('src'));
    //         var img = "<img class='img_msg' src='"+$(this).attr('src')+"' style='width:130px;' />";
    //         img_show = layer.tips(img, this,{
    //             tips:[2, 'rgba(41,41,41,.5)']
    //             ,area: ['160px']
    //         });
    //     },function(){
    //         layer.close(img_show);
    //     });
    //     $('td img').attr('style','max-width:70px');
    // }
});