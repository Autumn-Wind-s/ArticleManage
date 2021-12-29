<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/15
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <style>
    </style>
    <script>
        var Type = null

        function del(username, articleName, type) {
            var r = confirm("您确定要删除《" + articleName + "》这篇文章吗？")
            if (r == true) {
                $.ajax({
                    type: "get",
                    url: "DeleteArticleServlet",
                    data: {
                        "username": username,
                        "articleName": articleName,
                        "type": type
                    },
                    success: function (data) {
                        if (data.flag) {
                            alert("删除成功！")
                        } else {
                            alert(data.errorMessage)
                        }
                    }
                })
            }

        }

        function up(pageType, currentPage, pageSize) {
            $.ajax({
                type: "get",
                url: "PersonalPaginationServlet",
                data: {
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                    "pageType": pageType,
                },
                success: function (data) {
                    Type = pageType
                    $("#" + pageType).focus()
                    $("#totalPage").html(data.totalPage)
                    $("#totalCount").html(data.totalCount)
                    var list = "";
                    var firstPage = '<li> <a href="javascript:up(' + pageType + ',1,5)">首页</a> </li>'
                    if (data.currentPage == 1) {
                        var pre = 1
                    } else pre = currentPage - 1
                    var prePage = '<li> <a href="javascript:up(' + pageType + ',' + pre + ',5)">上一页</a> </li>'
                    list += firstPage
                    list += prePage
                    for (var i = 1; i <= data.totalPage; i++) {
                        // alert(Type)
                        if (data.currentPage == i) {
                            var li = '<li  ><a  style="background-color: #459d3e" href = "javascript:up(' + pageType + ',' + i + ',5)">' + i + '</a></li>'

                        } else {
                            var li = '<li><a href = "javascript:up(' + pageType + ',' + i + ',5)">' + i + '</a></li>'
                        }
                        list += li;
                    }
                    if (data.currentPage == data.totalPage) {
                        var last = data.totalPage


                    } else var last = data.currentPage + 1
                    var lastPage = '<li> <a href="javascript:up(' + pageType + ',' + last + ',5)">下一页</a> </li>'

                    var nextPage = '<li> <a href="javascript:up(' + pageType + ',' + data.totalPage + ',5)">尾页</a> </li>'
                    list += lastPage
                    list += nextPage
                    $("#list").html(list)
                    var list2 = "";
                    for (var j = 0; j < data.list.length; j++) {
                        var article = data.list[j]
                        var username = data.list[j].username
                        var articleName = data.list[j].articleName
                        var type = data.list[j].type
                        var li2 = ' <tr>\n' +
                            ' <td> ' + ((currentPage - 1) * 5 + j + 1) + ' </td>\n ' +
                            '<td>' + article.articleName + '</td>\n' +
                            '<td>' + article.username + '</td>\n' +
                            '<td>' + article.type + '</td>' +
                            ' <td>' +
                            ' <a href="ShowArticleServlet?username=' + data.list[j].username + '&articleName=' + data.list[j].articleName + '&type=' + data.list[j].type + '" style="text-decoration: none">查看</a>' +
                            '  <a href="GetArticleServlet?username=' + data.list[j].username + '&articleName=' + data.list[j].articleName + '&type=' + data.list[j].type + '" style="text-decoration: none">修改</a>' +
                            '  <a href="javascript:del(&quot;' + username + '&quot;,&quot;' + articleName + '&quot;,&quot;' + type + '&quot;)" style="text-decoration: none;color: red">删除</a>' +
                            '  <a href="DownLoadServlet?username=' + data.list[j].username + '&articleName=' + data.list[j].articleName + '&type=' + data.list[j].type + '" style="text-decoration: none">下载</a>' +
                            '</td>' +
                            '</tr>';
                        list2 += li2

                    }
                    $("#tb").html(list2)
                }

            })
        }

        $(function () {
                up(${sessionScope.personalType}, ${sessionScope.personalCurrent}, 5)

            }
        )

    </script>

</head>
<body>


<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="row-fluid">
                <div class="span10" style="width: 100%">
                    <div class="navbar navbar-inverse">
                        <div class="navbar-inner">
                            <div class="container-fluid">
                                <a class="btn btn-navbar" data-target=".navbar-responsive-collapse"
                                   data-toggle="collapse"></a> <a class="brand" href="#" style="pointer-events: none"
                            >个人文章</a>
                                <div class="nav-collapse collapse navbar-responsive-collapse">
                                    <ul class="nav">
                                        <li>
                                            <a href="javascript:up(1,1,5)" id="1">议论文</a>
                                        </li>
                                        <li>
                                            <a href="javascript:up(2,1,5)" id="2">散文</a>
                                        </li>
                                        <li>
                                            <a href="javascript:up(3,1,5)" id="3">说明文</a>
                                        </li>
                                        <li>
                                            <a href="javascript:up(4,1,5)" id="4">记叙文</a>
                                        </li>
                                        <li>
                                            <a href="javascript:up(5,1,5)" id="5">小说</a>
                                        </li>
                                        <li>
                                            <a href="javascript:up(6,1,5)" id="6">诗歌</a>
                                        </li>

                                        <li>
                                            <a href="javascript:up(7,1,5)" id="7">应用文</a>
                                        </li>
                                        </li>--%>
                                    </ul>
                                    <ul class="nav pull-right">
                                        </li>--%>
                                        <li>
                                            <div class="span2" style="width: 300px">
                                                <form class="form-search"
                                                      style="margin: 0 0 20px; margin-left: 0;margin-top: 8px;width: 300px;margin-left: 24px;">
                                                    <input class="input-medium search-query" type="text"
                                                           style="width: 180px"/>
                                                    <button type="submit" class="btn" style="margin-left: 10px">查找
                                                    </button>
                                                </form>
                                            </div>
                                        </li>
                                    </ul>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

            </div>
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        文章名
                    </th>
                    <th>
                        作者
                    </th>
                    <th>
                        类型
                    </th>
                    <th>
                        操作
                    </th>
                </tr>
                </thead>
                <tbody id="tb">


                </tbody>
            </table>
            <div class="row-fluid" style="position: absolute;top: 500px;left: -5px;">
                <div class="span12">
                    <div class="pagination pagination-centered">
                        <span><h3>共<span id="totalPage"></span>页<span id="totalCount"></span>篇</h3> </span>
                        <ul id="list">

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div>
    <%--    <form action="PaginationServlet" method="post">--%>
    <%--        <input type="text" value="字符" name="te">--%>
    <%--        <input type="submit" value="提交">--%>
    <%--    </form>--%>
</div>
</body>

</html>
