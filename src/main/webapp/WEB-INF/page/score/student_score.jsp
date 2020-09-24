<%--
  Created by IntelliJ IDEA.
  User: YZH
  Date: 2020/9/24
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
    <%--<title>学生成绩</title>--%>
<%--</head>--%>
<%--<body>--%>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div class="layui-row">
            <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
        </div>
    </div>
</div>
<script>
    layui.use([ 'table'], function () {
        var table = layui.table;
        table.render({
            elem: '#currentTableId',
            url: '${basePath}score/query_student_score',
            method:"post",
            toolbar: '#toolbar',
            defaultToolbar: ['filter', 'exports', 'print'],
            page: false,
            cols: [[
                {field: 'stuName',  title: '姓名',templet: '<div>{{d.student.stuName}}</div>'},
                {field: 'courseName', title: '课程名称',templet: '<div>{{d.course.courseName}}</div>'},
                {field: 'year', title: '年份',templet: '<div>{{d.section.year}}</div>'},
                {field: 'type', title: '类型',templet: '<div>{{d.section.type}}</div>'},
                {field: 'score', title: '分数'}
            ]],
            skin: 'line'
        });
    });
</script>

<%--</body>--%>
<%--</html>--%>

