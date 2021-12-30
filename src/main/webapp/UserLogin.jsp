<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/11/6
  Time: 12:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Purple_loginform Website Template | Home :: w3layouts</title>
    <link href="css/st.css" rel="stylesheet" type="text/css" media="all" />
    <script src="js/jquery-3.3.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!-- -->
    <script>
    function CheckUsername() {
        var username =$("#username").val()
        var reg_username=/^\w{4,15}$/;
        var flag=reg_username.test(username)
        if(flag){
                $("#username_li").css("border","")
        }
        else {

            $("#username_li").css("border","1px solid red")
        }
        return flag
    }
    function CheckPassword() {
        var password =$("#password").val()
        var reg_password=/^\w{8,15}$/;
        var flag=reg_password.test(password)
        if(flag){
                $("#password_li").css("border","")
        }
        else {
            $("#password_li").css("border","1px solid red")
        }
        return flag
    }

    $(function () {
        $("#username").blur(CheckUsername);
        $("#password").blur(CheckPassword);
        $("#in").click(function () {
            if (CheckPassword()&&CheckUsername()){
                $.ajax({
                    type:"get",
                    url:"UserLoginServlet",
                    data:{
                        username:$("#username").val(),
                        password:$("#password").val()
                    },
                    success:function (data) {
                        if (data.flag){
                            location.href="index.jsp"
                        }else {
                            alert(data.errorMessage)
                        }}
                })
            }
        })
    })
    </script>
    <style>
        body{
            background-image: url("images/img.png");
        }
        form{

        }
        #h{
            text-align: center;
            font-size: 40px;
            font-family: cursive;

        }
    </style>
    <script src="js/jquery.min.js"></script>
</head>
<body >
<div style="margin-top: 50px">
    <h1 id="h"><div><img src="images/logo.png" alt="" style="width: 100px;margin-left: -450px"></div><div style="margin-top: -67px"  >智极文章管理系统</div></h1>
</div>
<!-- contact-form -->
<div class="message warning">
    <div class="inset">
        <div class="login-head">
            <h1>欢迎您：</h1>

        </div>
        <form   id="Form" method="get" ACTION="UserLoginServlet" >
          <li id="username_li">  <span>账号</span>
                <input type="text" class="text" id="username" name="username" required="required" placeholder="请输入4-15位数字或字母"  >
            </li>
            <div class="clear"> </div>
            <li id="password_li"> <span>密码</span>
                <input type="password" id="password" name="password" required="required" placeholder="请输入8-15位数字或字母">
            </li>
            <div class="clear"> </div>
            <div class="submit">
                <input type="button" value="Sign in" id="in">
                <h4><a href="AdminLogin.jsp" id="a2">Admin login</a></h4>
                <h4><a href="UserEnroll.jsp" style="margin-left: 88px">User Enroll</a></h4>
                <div class="clear">  </div>
            </div>

        </form>

    </div>
</div>
</div>
<div class="clear"> </div>
<!--- footer --->

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
