<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/9
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script>
        function CheckUsername() {
            var username =$("#inputaccount").val()
            var reg_username=/^\w{4,15}$/;
            var flag=reg_username.test(username)
            if(flag){
                $("#inputaccount").css("border","1px solid green")
            }
            else {

                $("#inputaccount").css("border","1px solid red")
            }
            return flag
        }
        function CheckPassword() {
            var password =$("#inputPassword").val()
            var reg_password=/^\w{8,15}$/;
            var flag=reg_password.test(password)
            if(flag){
                $("#inputPassword").css("border","1px solid green")
            }
            else {
                $("#inputPassword").css("border","1px solid red")
            }
            return flag

        }
        function CheckEmail() {
            var email=$("#inputEmail").val()
            var reg_email=/^\w+@\w+\.\w+$/;
            var flag=reg_email.test(email)
            if (flag){
                $("#inputEmail").css("border","1px solid green")
            }else
            {
                $("#inputEmail").css("border","1px solid red ")
            }
            return flag
        }
        function CheckSecond() {
            if($("#inputSecondPassword").val()==$("#inputPassword").val()){
                $("#inputSecondPassword").css("border","1px solid green")
                return true
            }
            else { $("#Msg").css("color","red")
                $("#Msg").html("两次输入密码不一致")
                $("#inputSecondPassword").css("border","1px solid red")
                window.setTimeout(function () {
                    $("#Msg").html("")
                },2000); return false}

        }
        $(function (){

            $("#inputaccount").blur(CheckUsername)
            $("#inputPassword").blur(CheckPassword)
            $("#inputEmail").blur(CheckEmail)
            $("#inputSecondPassword").blur(CheckSecond)

            $("#sub").click(function () {
                if(CheckUsername()&&CheckPassword()&&CheckEmail()&&CheckSecond()){
                    $.ajax({
                        type: "post", // 以post方式发起请求
                        url: "EnrollServlet", // 你的请求链接
                        data: { // 提交数据
                            "username":$("#inputaccount").val(),
                            "password":$("#inputPassword").val(),
                            "email": $("#inputEmail").val(), // 前者为字段名，后者为数据
                        },
                        success:function (data) {
                            // data为返回值

                            if (data.flag){

                                $("#Msg").css("color","green")
                                $("#Msg").html("用户添加成功！")
                                window.setTimeout(function () {
                                    $("#Msg").html("")
                                },2000);
                            }
                            else {
                                $("#Msg").css("color","red")
                                $("#Msg").html(data.errorMessage)
                                window.setTimeout(function () {
                                    $("#Msg").html("")
                                },2000);
                            }
                            // 成功后的回调方法
                        }
                    })

                }
                return false
            })
        })
    </script>
    <style>
        #d1{
            margin-top: 50px;
            margin-left: 10px;
            position: relative;
            width: 1200px;
            height: 500px;
        }
        #b1{
            margin-top: -5px;
            width: 35px;
            text-decoration: none;

        }
        #a1{
            text-decoration: none;
        }
        #s2{
            margin-left: 0px;
            width: 30px;
        }
        #sub{
            margin-left: 90px;
        }
    </style>
</head>

<body>
<div class="container-fluid" id="d1">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">

            </div>
            <h3 class="text-center">
                请填写新用户的个人信息
            </h3>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <form class="form-horizontal" id="inform">
                <div id="Msg" style="color:red;height: 20px; margin-bottom: 20px;
    margin-right: 100px;text-align: center"></div>
                <div class="control-group">
                    <label class="control-label" for="inputaccount">账号:</label>
                    <div class="controls">
                        <input id="inputaccount" type="text" required="required" placeholder="请输入4-15位数字或字母"  />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputPassword">密码:</label>
                    <div class="controls">
                        <input id="inputPassword" type="password" required="required" placeholder="请输入8-15位数字或字母"  />
                    </div>
                </div> <div class="control-group">
                <label class="control-label" for="inputSecondPassword">确认密码:</label>
                <div class="controls">
                    <input id="inputSecondPassword" type="password" required="required" placeholder="请再次输入密码"  />
                </div>
            </div>
                <div class="control-group">
                    <label class="control-label" for="inputEmail">邮箱:</label>
                    <div class="controls">
                        <input id="inputEmail" type="email" required="required"  placeholder="请输入您的邮箱地址"   />
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <%--                        <label class="checkbox"><input type="checkbox" /> Remember me</label> --%>
                        <button type="submit" class="btn" id="sub">添加</button>
                    </div>
                </div>

            </form>

        </div>
    </div>
</div>
<%--<input type="button" value="houtui" onclick="window.history.go(-1)">--%>
</body>
</html>
