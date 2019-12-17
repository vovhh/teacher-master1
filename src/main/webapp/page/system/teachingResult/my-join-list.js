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

    var memberCode = window.sessionStorage.getItem("teacherCode");
    //信息列表
    var tableLoad = layer.load(1);
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher/biz/teachingResult_findByMember.action?memberCode='+memberCode,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        title: '竞赛获奖列表',//用于导出
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            {field: 'resultName', title: '成果名称', minWidth:100, align:"center"},
            {field: 'teacherName', title: '主持人', minWidth:100, align:'center', templet:function(d){
                    return d.itemPerson.teacherName;
                }},
            {field: 'memberName', title: '主要完成人', minWidth:100, align:"center"},
            {field: 'resultTime', title: '获奖时间', minWidth:100, align:'center',templet: function (d) {
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
                where: {key: "0,"+dataReload.val()}
            }, 'data');
        }
    };

    $('.reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            addUser(data);
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此记录？',{icon:3, title:'提示信息'},function(index){
                $.get("/teacher/biz/teachingResult_delete.action",{
                    resultId : data.resultId  //将需要删除的newsId作为参数传入
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
                newsId.push(data[i].resultId);
            }
            $.ajax({
                url: '/teacher/biz/teachingResult_findByExport.action',
                data: {ids: newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        var dataIndex = 0;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            resultId: function (value, line, data) {
                                dataIndex += 1;
                                return dataIndex;
                            },
                            resultName: function(value, line, data) {
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            },
                            itemPerson: function (value, line, data) {
                                return value.teacherName;
                            },
                            memberName: 'memberName',
                            resultTime: function (value, line, data) {
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
                        data.unshift({resultId: '编号', resultName: '成果名称', itemPerson: '主持人', memberName: '主要完成人', resultTime: '获奖时间', prizeLevel: '获奖级别', prizeGrade: '获奖等次'});

                        excel.exportExcel({
                            sheet1: data
                        }, '教学成果列表.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });

    //竞赛项目详情
    function detailUser(data) {
        var index = layui.layer.open({
            title: "详情",
            type: 2,
            content: "honor-detail.html",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                body.find(".resultId").val(data.resultId);
                body.find(".resultName").val(data.resultName);
                body.find(".teacherName").val(data.itemPerson.teacherName);//主持人
                body.find(".memberName").val(data.memberName);//参赛队员
                body.find(".resultTime").val(util.toDateString(data.resultTime, "yyyy年"));
                body.find(".prizeLevel").val(data.prizeLevel.title);
                body.find(".prizeGrade").val(data.prizeGrade.title);

                /**
                 * 附件详情
                 */
                var str = "";
                $.ajax({
                    type: "POST",
                    url: '/teacher/biz/teachingResultAnnex_findByResultId.action',
                    data: {resultId: data.resultId},
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