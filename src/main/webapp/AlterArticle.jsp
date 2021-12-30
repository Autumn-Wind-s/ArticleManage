<%@ page import="com.it.domain.Article" %><%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/19
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
        window.onload = function()
        {
            CKEDITOR.replace( 'description');
        };
        $(function () {

            $(function () {
                    $("#bb").click(function () {
                        var f=document.referrer;
                        location.href=f
                    })
                }
            )
            $("#${sessionScope.article.type}").attr("selected",true)
            $("#sub").click(function () {
                    $.ajax({
                        type: "post", // 以post方式发起请求
                        url: "AlterArticleServlet", // 你的请求链接
                        data: { // 提交数据
                            "articleName":$("#articleName").val(),
                            "type":$("#select").val(),
                            "text": CKEDITOR.instances.description.getData(), // 前者为字段名，后者为数据
                        },
                        success:function (data) {
                            // data为返回值

                            if (data.flag){
                            alert("恭喜你修改成功！")
                            }
                            else {
                                alert(data.errorMessage)

                            }
                            // 成功后的回调方法
                        }
                    })


                return false
            })
        })

    </script>
</head>
<body>
<button class="btn btn-link" type="button" style="margin-left: 22px;font-size: 18px" id="bb"  >返回</button>
<form method = "get" action = "AlterArticleServlet">
    <p style=" margin-left: 259px;">
        <span style="font-size: 20px">文章名：</span>
        <input type="text" name="articleName" id="articleName" style="height: 28px;" value=${sessionScope.article.articleName}></p>
    <p style="width: 400px;height: 60px; margin-left: 240px">
        <span style=" font-size: 20px;">
            文章类型：
        </span>
        <select  style="width: 205px" id="select" >
        <option id="议论文">议论文</option>
        <option id="散文" >散文</option>
        <option id="说明文">说明文</option>
        <option id="记叙文">记叙文</option>
        <option id="小说" >小说</option>
        <option id="诗歌">诗歌</option>
        <option id="应用文">应用文</option>
    </select></p>
    <p style="width: 1000px">
      <textarea name="description" id="description"  >
        ${sessionScope.article.text}
      </textarea>
        <button type="submit" class="btn" id="sub" style="margin-left: 422px;margin-top: 30px">修改</button>
  </p>
</form>




</body>
</html>
