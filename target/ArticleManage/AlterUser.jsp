<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/9
  Time: 8:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script>
        function CheckUsername() {
            var username = $("#inputaccount").val()
            var reg_username = /^\w{4,15}$/;
            var flag = reg_username.test(username)
            if (flag) {
                $("#inputaccount").css("border", "1px solid green")
            } else {

                $("#inputaccount").css("border", "1px solid red")
            }
            return flag
        }

        function CheckPrePassword() {
            var password = $("#inputPrePassword").val()
            var reg_password = /^\w{8,15}$/;
            var flag = reg_password.test(password)
            if (flag) {
                $("#inputPrePassword").css("border", "1px solid green")
            } else {
                $("#inputPrePassword").css("border", "1px solid red")
            }
            return flag

        }

        function CheckNewPassword() {
            var password = $("#inputNewPassword").val()
            var reg_password = /^\w{8,15}$/;
            var flag = reg_password.test(password)
            if (flag) {
                $("#inputNewPassword").css("border", "1px solid green")
            } else {
                $("#inputNewPassword").css("border", "1px solid red")
            }
            return flag

        }

        function CheckEmail() {
            var email = $("#inputEmail").val()
            var reg_email = /^\w+@\w+\.\w+$/;
            var flag = reg_email.test(email)
            if (flag) {
                $("#inputEmail").css("border", "1px solid green")
            } else {
                $("#inputEmail").css("border", "1px solid red ")
            }
            return flag
        }

        $(function () {
            $("#bb").click(function (){
                location.href="UserManage.jsp"
            })
            if ("${requestScope.get("path")}"=="admin" ) {

            }else {
                $("#bb").css("display","none")
            }

            $("#inputaccount").blur(CheckUsername)
            $("#inputPrePassword").blur(CheckPrePassword)
            $("#inputEmail").blur(CheckEmail)
            $("#inputNewPassword").blur(CheckNewPassword)

            $("#sub").click(function () {
                if (CheckUsername() && CheckPrePassword() && CheckEmail() && CheckNewPassword()) {
                    $.ajax({
                        type: "post",
                        url: "AlterUserServlet",
                        data: { // 提交数据
                            "username": $("#inputaccount").val(),
                            "prePassword": $("#inputPrePassword").val(),
                            "newPassword": $("#inputNewPassword").val(),
                            "email": $("#inputEmail").val(),
                        },
                        success: function (data) {


                            if (data.flag) {

                                $("#Msg").css("color", "green")
                                $("#Msg").html("恭喜您修改成功！")
                                window.setTimeout(function () {
                                    $("#Msg").html("")
                                }, 3000);
                            } else {
                                $("#Msg").css("color", "red")
                                $("#Msg").html(data.errorMessage)
                                window.setTimeout(function () {
                                    $("#Msg").html("")
                                }, 3000);
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
        #d1 {
            margin-top: 0px;
            margin-left: 0px;
            position: relative;
            width: 1200px;
            height: 500px;
        }

        #b1 {
            margin-top: -5px;
            width: 35px;
            text-decoration: none;

        }

        #a1 {
            text-decoration: none;
        }

        #s2 {
            margin-left: 0px;
            width: 30px;
        }

        #sub {
            margin-left: 90px;
        }
    </style>
</head>

<body>
<div class="container-fluid" id="d1" >
    <div class="row-fluid">
        <div class="span12">
            <button id="bb" class="btn btn-link" type="button"  style=" margin-left: 22px;font-size: 18px;"   >返回</button>
            <div class="row-fluid">
                <div class="span2" id="s2">

                </div>

            </div>
            <h3 class="text-center">
                请更改您的个人信息
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
                        <input id="inputaccount" type="text" required="required" placeholder="请输入4-15位数字或字母"
                               value="${sessionScope.user.username}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputPrePassword">原密码:</label>
                    <div class="controls">
                        <input id="inputPrePassword" type="text" required="required" placeholder="请输入原密码"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputNewPassword">新密码:</label>
                    <div class="controls">
                        <input id="inputNewPassword" type="text" required="required" placeholder="请输入新密码"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputEmail">邮箱:</label>
                    <div class="controls">
                        <input id="inputEmail" type="email" required="required" placeholder="请输入您的邮箱地址"
                               value="${sessionScope.user.email}"/>
                    </div>
                </div>

                <div class="control-group">
                    <div class="controls">
                        <%--                        <label class="checkbox"><input type="checkbox" /> Remember me</label> --%>
                        <button type="submit" class="btn" id="sub">修改</button>
                    </div>
                </div>

            </form>

        </div>
    </div>
</div>
<%--<input type="button" value="houtui" onclick="window.history.go(-1)">--%>
</body>
</html>
