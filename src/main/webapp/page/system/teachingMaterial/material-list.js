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

    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher/biz/teachingMaterial_findByPage.action',
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 20,
        title: '竞赛获奖列表',
        id : "infoListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'materialName', title: '教材名称', minWidth:100, align:"center"},
            {field: 'teacherName', title: '主编', minWidth:100, align:'center', templet:function(d){
                    return d.teacher.teacherName;
                }},
            {field: 'press', title: '出版社', minWidth:100, align:"center"},
            {field: 'publishTime', title: '出版时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.publishTime, "yyyy年")
                }},
            {field: 'remarks', title: '备注', minWidth:100, align:"center"},
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
                $.get("/teacher/biz/teachingMaterial_delete.action",{
                    materialId : data.materialId  //将需要删除的newsId作为参数传入
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
                newsId.push(data[i].materialId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher/biz/teachingMaterial_deleteBatch.action",{
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
                newsId.push(data[i].materialId);
            }
            $.ajax({
                url: '/teacher/biz/teachingMaterial_findByExport.action',
                data: {ids: newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        var dataIndex = 0;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            materialId: function (value, line, data) {
                                dataIndex += 1;
                                return dataIndex;
                            },
                            materialName: function(value, line, data) {
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            },
                            teacher: function (value, line, data) {
                                return value.teacherName;
                            },
                            press: 'press',
                            publishTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年");
                            },
                            remarks: 'remarks',
                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({materialId: '编号', materialName: '教材名称', teacher: '主编', press: '出版社', publishTime: '发布时间', remarks: '备注'});

                        excel.exportExcel({
                            sheet1: data
                        }, '教材信息列表.xlsx', 'xlsx');
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
            url: '/teacher/biz/teachingMaterial_findByExport.action',
            data: {exportCode: 1},
            dataType: 'json',
            success: function (res) {
                if (res.code===0){
                    var data = res.data;
                    var dataIndex = 0;
                    // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                    data = excel.filterExportData(data, {
                        materialId: function (value, line, data) {
                            dataIndex += 1;
                            return dataIndex;
                        },
                        materialName: function(value, line, data) {
                            return {
                                v: value,
                                s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                            };
                        },
                        teacher: function (value, line, data) {
                            return value.teacherName;
                        },
                        press: 'press',
                        publishTime: function (value, line, data) {
                            return util.toDateString(value, "yyyy年");
                        },
                        remarks: 'remarks',
                    });
                    // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                    data.unshift({materialId: '编号', materialName: '教材名称', teacher: '主编', press: '出版社', publishTime: '发布时间', remarks: '备注'});

                    excel.exportExcel({
                        sheet1: data
                    }, '全部教材信息.xlsx', 'xlsx');
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
            content : "material-add.html",
            maxmin: true,
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.materialId);
                    body.find(".materialName").val(edit.materialName);
                    body.find(".press").val(edit.press);
                    body.find(".publishTime").val(util.toDateString(edit.publishTime, "yyyy"));
                    body.find(".remarks").val(edit.remarks);
                    body.find(".teacherHide").val(edit.teacher.teacherId);
                    body.find(".updateFlag").val(1);//更新

                    /**
                     * 回显示文件
                     */
                    $.ajax({
                        type: "POST",
                        url: '/teacher/biz/teachingMaterialAnnex_findByMaterialId.action',//数据接口
                        data: {materialId: edit.materialId},
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
                                        $.post("/teacher/biz/teachingMaterialAnnex_delete.action", {
                                            annexId: item.annexId
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
            content: "material-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find(".materialId").val(data.materialId);
                body.find(".materialName").val(data.materialName);
                body.find(".teacherName").val(data.teacher.teacherName);
                body.find(".press").val(data.press);
                body.find(".publishTime").val(util.toDateString(data.publishTime, "yyyy年"));
                body.find(".remarks").val(data.remarks);

                /**
                 * 附件详情
                 */
                var str = "";
                $.ajax({
                    type: "POST",
                    url: '/teacher/biz/teachingMaterialAnnex_findByMaterialId.action',
                    data: {materialId: data.materialId},
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
});