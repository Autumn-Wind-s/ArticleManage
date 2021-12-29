<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/20
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文章上传</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <script>
        function hello() {

        }
        function upload() {

            var formData=new FormData(document.getElementById("fileForm"))
            $.ajax({
                type: "post",
                url: "ArticleUploadServlet",
                data: formData,
                contentType: false,
                processData:false,
                success:function (data) {
                    if(data.flag){
                        alert("上传成功！")
                    }
                    else {alert(data.errorMessage)}
                }
            })

        }
        $(function () {
        })
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h1 class="text-center">
               文件上传
            </h1>
            <form class="form-inline" style=" margin-left: 450px;margin-top: 160px; width: 500px;height: 400px; font-size: 20px" id="fileForm" action="ArticleUploadServlet" method="post" enctype="multipart/form-data" >
                <p style="width: 400px ;height: 50px;margin-left: -16px;"><label style="font-size: 20px">文章名：</label><input type="text" name="articleName" required/></p>
                <p style="width: 400px;height: 60px; margin-left: -39px">
        <span style=" font-size: 20px;">
            文章类型：
        </span>
                    <select  style="width: 250px" id="select" name="type" >
                        <option id="议论文">议论文</option>
                        <option id="散文" >散文</option>
                        <option id="3">说明文</option>
                        <option id="记叙文">记叙文</option>
                        <option id="小说" >小说</option>
                        <option id="诗歌">诗歌</option>
                        <option id="应用文">应用文</option>
                    </select></p>
<%--                <p style="width: 400px ;height: 50px; margin-left: -28px;"><label>文章类型：</label><input type="" name="type" required/></p>--%>
                <p style="width: 400px ;height: 50px"><label  style="font-size: 20px">文件：</label><input type="file" name="file" multiple required/></p>
                <input type="submit" class="btn" id="uo"  style=" margin-left: 112px;margin-top: 60px; width: 100px" value="上传">
                </button>

            </form>
        </div>
    </div>
</div>
</body>
</html>
