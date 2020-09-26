<%--
  Created by IntelliJ IDEA.
  User: YZH
  Date: 2020/9/23
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div class="layui-row">
            <script type="text/html" id="toolbar">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="save">
                        <i class="fa fa-pencil"></i>
                        评分
                    </button>
                </div>
            </script>
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </div>
    </div>
</div>

<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery, table = layui.table;
        table.render({
            elem: '#currentTableId',
            url: '${basePath}section/query_teacher_section',
            method:"post",
            toolbar: '#toolbar',
            defaultToolbar: ['filter', 'exports', 'print'],
            page: false,
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID'},
                {field: 'year',  title: '年份'},
                {field: 'type', title: '类型'},
                {field: 'clazzName', title: '班级',templet: '<div>{{d.clazz.clazzName}}</div>'},
                {field: 'courseName', title: '课程',templet: '<div>{{d.course.courseName}}</div>'},
                {field: 'remark',title: '备注'}
            ]],
            skin: 'line'
        });

        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'save') {   // 监听添加操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择选要评分的一行数据",{time:1000});
                    return;
                }

                var index = layer.open({
                    title: '评分',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: 'section/teacher_student_score?courseId='+data[0].course.id+"&sectionId="+data[0].id
                });
            }
        });
    });
</script>
</body>
</html>
