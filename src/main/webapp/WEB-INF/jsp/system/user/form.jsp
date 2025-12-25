<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%--    用户信息表单--%>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>
<form class="layui-form" lay-filter="userForm" id="add_user_form" action="" style="padding:15px 10px;">
    <input type="hidden" name="id">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text" name="username" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">是否锁定</label>
        <div class="layui-input-block">
            <input type="checkbox" name="locked" value="0" lay-skin="switch">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline" >
            <label class="layui-form-label">用户角色</label>
            <div  class="layui-input-block" >
                <select name="rolesId" id="role_add">
                    <option value="-1">请选择</option>
                    <option value="1">超级管理员</option>
                    <option value="2">学生</option>
                    <option value="3">教师</option>
                    <option value="5">通知管理员</option>
                    <option value="6">课程管理员</option>
                </select>
            </div>
        </div>
    </div>

    <script>
        layui.form.render();
        <%--$.get("${pageContext.request.contextPath}/easRole/search",function(data){--%>
        $.get("/easRole/search",function(data){
            $.each(data,function(){
                var opt = $("<option></option>").appendTo("#role_add");
                opt.text(this.name).val(this.id);
            });
            layui.formSelects.render();
        });
    </script>
</form>
</body>
</html>
