
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="app">
<head>
    <meta charset="utf-8" />
    <title>管理员登录</title>
    <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
    <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css" />
    <link rel="stylesheet" href="css/app.css" type="text/css" />
    <script src="js/jquery.min.js"></script>
    <script>
$(function () {
    $("#validate-submit").click(function () {
       
        $.ajax({
            type:"get",
            url:"AdminLoginServlet",
            data:{
                name:$("#username").val(),
                password:$("#password").val()
            },
            success:function (data){
                if (data.flag){
                    location.href="AdminIndex.jsp"
                }else {
                    alert(data.errorMessage)
                }
            }

        })
    })

})
    </script>
</head>
<body >
<h3><a href="UserLogin.jsp" style="color: black;margin-left: 30px" >返回</a></h3>

<div class="container aside-xl" style="margin-top: 100px;">
    <a class="navbar-brand block"><span class="h1 font-bold" style="color:black">管理员后台登录</span></a>
    <header class="wrapper text-center">
        <strong class="text-sucess">管理员登陆</strong>
    </header>
    <form  method="post" >
        <div class="form-group">
            <input type="username" name="username" id="username" placeholder="用户名" class="form-control  input-lg text-center no-border">
        </div>
        <div class="form-group">
            <input type="password" name="password"  id="password" placeholder="密码" class="form-control  input-lg text-center no-border">
        </div>

        <button type="button" class="btn btn-lg btn-danger lt b-white b-2x btn-block" id="validate-submit">
            <span class="m-r-n-lg " style="margin-right: 0px">登录</span></button>
    </form>
</div>

</body>
</html>
