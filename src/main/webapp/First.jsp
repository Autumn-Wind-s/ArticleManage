
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
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

    <style>
        img{
            width: 100%;
            height: 100%;
        }
    </style>
    <script>
        $(function () {

            if(document.referrer=="http://localhost:8085/ArticleManage/index.jsp"){
                $("#h").html("尊敬的${sessionScope.user.username},您好!");

            }else {
                $("#h").html("尊敬的${sessionScope.admin.username},您好!");

            }
        })
    </script>
</head>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="hero-unit">
                <h1 id="h">

                </h1>
                <p style="font-size: 20px">
                  欢迎来到智极文章管理系统，在这里你可以浏览和下载其他用户的文章，您也可以上传您自己的文章供其他用户阅读,希望这里有打动您的文章。
                </p>

            </div><img alt="140x140" src="images/2.jpg" class="img-rounded" style="height: 85%;"/>
        </div>
    </div>
</div>
</html>
