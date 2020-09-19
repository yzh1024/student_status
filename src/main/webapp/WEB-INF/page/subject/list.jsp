<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>专业管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">专业名称</label>
                        <div class="layui-input-inline">
                            <input type="text" name="subjectName" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属院系</label>
                        <div class="layui-input-inline">
                            <input type="text" name="college" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-primary" lay-submit lay-filter="search-btn"><i class="layui-icon"></i> 搜 索
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                    <i class="fa fa-plus"></i>
                    添加
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                    <i class="fa fa-remove"></i>
                    删除
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script>
    layui.use(['form','table'],function () {
        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        table.render({
            elem:'#currentTableId',                 //渲染对象
            url:'${basePath}subject/query',         //Controller中的url
            method:'post',                          //请求方式
            toolbar: '#toolbar',                 //上面‘添加’、‘修改’、‘删除’的按钮
            defaultToolbar: ['filter', 'exports', 'print'],      //页面右侧‘筛选列’‘导出’‘打印’的按钮，layui自带的
            page: true,                         //分页信息
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 80, title: 'ID'},
                {field: 'subjectName', title: '专业名称'},
                {field: 'college', title: '所属系'},
                {field: 'remark', title: '备注'}
            ]],
            // skin: 'line'
            skin: 'border'      //显示边框
        });

        // 监听“搜索”按钮，
        form.on('submit(search-btn)', function (data) {
            //执行表单查询
            console.log(data.field);
            //表格reload
            table.reload('currentTableId',{
                where:data.field
            });
            return false;
        });

        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {   // 监听添加操作
                var index = layer.open({
                    title: '添加专业',
                    type: 2,              //ifram
                    shade: 0.2,           //阴影透明度
                    shadeClose: false,
                    area: ['50%', '50%'],    //占比面积
                    content: '${basePath}subject/add',
                    end:function(){
                        table.reload('currentTableId');     //添加完后刷新
                    }
                });
            } else if (obj.event === 'update') {  // 监听修改操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '修改专业',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: '${basePath}subject/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'delete') { // 监听删除操作
                //checkStatus拿到选项的选中状态
                var checkStatus = table.checkStatus('currentTableId');
                //从状态里面拿数据
                var data = checkStatus.data;
                if(data.length ==0){
                    layer.msg("请选择一行数据删除",{time:1000});
                    return;
                }
                //定义一个数组来存放要删除选项的id
                var arr = [];
                for(index in data){
                    arr.push(data[index].id);
                }
                layer.confirm('确定要删除行吗', function () {
                    $.ajax({
                        url:"${basePath}subject/delete",
                        type:"POST",
                        dataType:'json',
                        data:"ids="+arr.join(","),
                        success:function(data){
                            layer.msg(data.msg,{time:500}, function(){
                                    table.reload("currentTableId");
                                });
                        },
                        error:function(data){
                            console.log(data);
                        }
                    });
                });
            }
        });



    })
</script>

</body>
</html>