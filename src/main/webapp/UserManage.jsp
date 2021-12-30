<%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/15
  Time: 22:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="js/jquery.min.js"></script>
    <style>
    </style>
    <script>
        var current;

        function del(username,password) {
            var r=confirm("您确定要删除用户"+username+"吗？")
            if (r==true){
                $.ajax({
                    type:"get",
                    url: "DeleteUserServlet",
                    data: {
                        "username":username,
                        "password":password,
                    },
                    success:function (data) {
                        if(data.flag){
                            alert("删除成功！")
                            location.reload()
                        }
                        else {alert(data.errorMessage)}
                    }
                })
            }

        }
        function free(id,username,password){
            var r=confirm("您确定要"+$("#"+id).html()+"用户"+username+"吗？")
           if(r==true){
               $.ajax({
                   type:"get",
                   url: "UpdateStatusServlet",
                   data: {
                       "username":username,
                       "password":password,
                       "status":$("#"+id).html()
                   },
                   success:function (data){
                       if (data.flag){

                           if ($("#"+id).html()=="冻结"){
                               alert("冻结成功！")
                               $("#"+id).css("color","red")
                               $("#"+id).html("解冻")
                           }else {
                               alert("解冻成功！")
                               $("#"+id).css("color","green")
                               $("#"+id).html("冻结")
                           }
                       }else {
                           if ($("#"+id).html()=="冻结"){
                           alert("冻结失败!")}else {
                               alert("解冻失败!")
                           }
                       }
                   }
               })
           }

        }
        function up( currentPage, pageSize) {
            $.ajax({
                type: "get",
                url: "UserManageServlet",
                data: {
                    "currentPage": currentPage,
                    "pageSize": pageSize,
                },
                success: function (data) {
                    current=currentPage
                    $("#totalPage").html(data.totalPage)
                    $("#totalCount").html(data.totalCount)
                    var list = "";
                    var firstPage = '<li> <a href="javascript:up(1,5)">首页</a> </li>'
                    if( data.currentPage==1){
                        var pre=1
                    }
                    else  pre=currentPage-1
                    var prePage = '<li> <a href="javascript:up('+pre+',5)">上一页</a> </li>'
                    list += firstPage
                    list += prePage
                    for (var i = 1; i <= data.totalPage; i++) {
                        // alert(Type)
                        if(data.currentPage==i){
                            var li = '<li  ><a  style="background-color: #459d3e" href = "javascript:up('+i+',5)">' + i + '</a></li>'

                        }else { var li = '<li><a href = "javascript:up('+i+',5)">' + i + '</a></li>'
                        }
                        list += li;
                    }
                    if( data.currentPage==data.totalPage){
                        var last=data.totalPage


                    }
                    else var last=data.currentPage+1
                    var lastPage = '<li> <a href="javascript:up('+last+',5)">下一页</a> </li>'

                    var nextPage = '<li> <a href="javascript:up('+data.totalPage+',5)">尾页</a> </li>'
                    list += lastPage
                    list += nextPage
                    $("#list").html(list)
                    var list2 = "";
                    for (var j = 0; j < data.list.length; j++) {
                        var user=data.list[j]
                        var username=data.list[j].username
                        var password=data.list[j].password
                        var email=data.list[j].email
                        var status=data.list[j].status
                        var li2 = ' <tr>\n'+
                            ' <td> '+ ((currentPage-1)*5+j+1)+' </td>\n '+
                            '<td>'+ username+'</td>\n'+
                            '<td>'+ password+ '</td>\n'+
                            '<td>'+ email+ '</td>'+
                            ' <td>'+
                            '  <a href="AdminAlterUserServlet?username='+username+'&password='+password+'&email='+email+'&status='+status+'" style="text-decoration: none">修改</a>'+
                            '  <a href="javascript:del(&quot;'+username+'&quot;,&quot;'+password+'&quot;)" style="text-decoration: none;color: red">删除</a>';
                        if (status=="正常"){
                            li2+='  <a href="javascript:free(&quot;'+((currentPage-1)*5+j+1)+'&quot;,&quot;'+username+'&quot;,&quot;'+password+'&quot;)" id="'+((currentPage-1)*5+j+1)+'" style="text-decoration: none;color: #499249">冻结</a>'+
                                '</td>'+
                                '</tr>';
                        }else {
                            li2+='  <a href="javascript:free(&quot;'+((currentPage-1)*5+j+1)+'&quot;,&quot;'+username+'&quot;,&quot;'+password+'&quot;)" id="'+((currentPage-1)*5+j+1)+'" style="text-decoration: none;color: red">解冻</a>'+
                                '</td>'+
                                '</tr>';
                        }

                        list2+=li2

                    }
                    $("#tb").html(list2)
                }

            })
        }

        $(function ()
            {

                    up(${sessionScope.current}, 5)

            }
        )

    </script>

</head>
<body>


<div class="container-fluid">
    <h1 style="text-align: center">用户列表</h1>
    <div class="row-fluid">
        <div class="span12">

            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>
                        id
                    </th>
                    <th>
                        用户名
                    </th>
                    <th>
                        密码
                    </th>
                    <th>
                        email
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
                        <span><h3>共<span id="totalPage"></span>页<span id="totalCount"></span>位用户</h3> </span>
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
