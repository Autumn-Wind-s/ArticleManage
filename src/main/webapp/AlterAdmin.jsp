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


        $(function () {


            $("#sub").click(function () {
               if ( $("#inputaccount").val()!=null&&$("#inputPrePassword").val()!=null&&$("#inputNewPassword").val())
               {$.ajax({
                    type: "post",
                    url: "AlterAdminServlet",
                    data: { // 提交数据
                        "username": $("#inputaccount").val(),
                        "prePassword": $("#inputPrePassword").val(),
                        "newPassword": $("#inputNewPassword").val(),
                    },
                    success: function (data) {
                        if (data.flag) {

                          alert("修改成功")
                        } else {
                           alert(data.errorMessage)
                        }
                        // 成功后的回调方法
                    }
                })}
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
<div class="container-fluid" id="d1">
    <div class="row-fluid">
        <div class="span12">
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
                               value="${sessionScope.admin.username}"/>
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
                    <div class="controls">
                        <%--                        <label class="checkbox"><input type="checkbox" /> Remember me</label> --%>
                        <button type="submit" class="btn" id="sub">修改</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
