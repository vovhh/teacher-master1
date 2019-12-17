layui.config({
    base: '/teacher/layui/layui_exts/'
}).extend({
    excel: 'excel'
}).use(['form','layer','table','excel','laytpl','util', 'laydate'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        excel = layui.excel,
        laytpl = layui.laytpl,
        util = layui.util,
        laydate = layui.laydate,
        table = layui.table;

    // var teacherId = window.sessionStorage.getItem("teacherId");
    var deptId = window.sessionStorage.getItem("deptId");
    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher/biz/competition_findByDeptTea.action?deptId='+deptId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        // toolbar: '#toolbar',//头部工具栏
        title: '竞赛获奖列表',//用于导出
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            // {field: 'prizeImg', title: '获奖证书', minWidth: 100, align: 'center', templet: function (d) {
            //         return '<img layer-src="'+d.prizeImg+'" src="'+d.prizeImg+'"/>';
            //     }},
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
        }/*/*,done:function(res,curr,count){
            hoverOpenImg();//显示大图
            $('table tr').on('click',function(){
                $('table tr').css('background','');
                $(this).css('background','<%=PropKit.use("config.properties").get("table_color")%>');
            });
        }*/
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {key: "0,"+dataReload.val()}
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
                        }, '教师竞赛获奖列表.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });
    //全部导出
    $(".exportAllBtn").click(function(){
        $.ajax({
            url: '/teacher/biz/competition_findByExport.action',
            data: {deptId: deptId, exportCode: 2},
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
                    }, '全部教师竞赛获奖.xlsx', 'xlsx');
                }else {
                    layer.msg("导出失败");
                }
            }
        })
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            title : "添加",
            type : 2,
            content : "../competition-add.html",
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
                    body.find(".updateFlag").val(1);//更新
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
            content: "../competition-detail.html",
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

    /**
     * 0:根据项目名称等String类型查询
     * 1：根据年度
     * 2：根据指导老师所属教研室--teacher.unitIds
     * 3：根据获奖等次查询--prizeGrade
     * 4：根据获奖级别查询--prizeLevel
     * 5：根据所属赛事id查询
     */
    //执行一个laydate实例
    laydate.render({elem: '#queryTime',trigger: 'click' , type: 'year', done: function(value, date, endDate){//控件选择完毕后的回调
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {key: "1,"+value}
            }, 'data');
        }});
    $.post("/teacher/biz/dept_findSubordinate.action?deptId="+deptId, function (data) {//--所属教研室
        $('#unitId').append(new Option("请选择", ""));
        $.each(data.data, function (index, item) {
            $('#unitId').append(new Option(item.deptName, "2,"+item.deptId));
        });
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher/biz/competitionPrizeGrade_findAll.action", function (data) {//--竞赛获奖等次
        $('#prizeGrade').append(new Option("请选择", ""));
        $.each(data.data, function (index, item) {
            $('#prizeGrade').append(new Option(item.title, "3,"+item.id));
        });
        //重新渲染select
        form.render('select');
    });
    $.post("/teacher/biz/competitionPrizeLevel_findAll.action", function (data) {//--竞赛获奖级别
        $('#prizeLevel').append(new Option("请选择", ""));
        $.each(data.data, function (index, item) {
            $('#prizeLevel').append(new Option(item.title, "4,"+item.id));
        });
        //重新渲染select
        form.render('select');
    });
    // $.post("/teacher/biz/match_findAll.action", function (data) {//--赛事
    //     $('#matchId').append(new Option("请选择", ""));
    //     $.each(data.data, function (index, item) {
    //         $('#matchId').append(new Option(item.matchName, "5,"+item.matchId));
    //     });
    //     //重新渲染select
    //     form.render('select');
    // });

    //当用户选中所属部门时，重载表格  -- unitId:lay-filter绑定的名称
    form.on("select(matchId)", function (data) {reloadTable(data)});
    form.on("select(unitId)", function (data) {reloadTable(data)});
    form.on("select(prizeGrade)", function (data) {reloadTable(data)});
    form.on("select(prizeLevel)", function (data) {reloadTable(data)});

    function reloadTable(data) {
        var key = data.value=="" ? null : data.value;
        //执行重载
        table.reload('infoListTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {key: key}
        }, 'data');
    }
});