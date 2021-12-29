<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.it.domain.Article" %><%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/18
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
  <script src="js/jquery.min.js"></script>
  <link href="css/bootstrap-combined.min.css" rel="stylesheet">

  <script>
    $(function () {
      $("#bb").click(function () {
        var f=document.referrer;
        location.href=f
      })
            }
     )

  </script>
</head>
<body>

<div class="container-fluid">
  <div class="row-fluid">
    <div class="span12" style="font-family: cursive">
      <button id="bb" class="btn btn-link" type="button"  style=" margin-left: 22px;font-size: 18px;"   >返回</button>
      <h3 class="text-center">
          ${sessionScope.article.articleName}
      </h3>
      <h4 class="text-center">
        作者:${sessionScope.article.username}

      </h4>
    </div>
  </div>
  <div class="row-fluid">
    <div class="span12">
      <p style="text-indent: 2em;font-family: cursive;font-size: 19px ">
        ${sessionScope.article.text}
      </p>
    </div>
  </div>
</div>
</body>
</html>
