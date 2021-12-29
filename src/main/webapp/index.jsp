<%@ page import="javax.jws.soap.SOAPBinding" %>
<%@ page import="com.it.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: song
  Date: 2021/12/13
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<html>
<head><meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>首页</title>
    <link href="css/layui.css" rel="stylesheet"/>
    <link href="css/magicalcoder.css" rel="stylesheet"/>
    <script src="js/json3.min.js"></script>
    <script src="js/echarts.min.js"></script>
    <script src="js/dataTool.min.js"></script>
    <script src="js/layui.all.js"></script>
    <script src="js/common.js"></script>
    <script src="js/magicalcoder.js"></script>
    <style>
    #h{
        font-size: 40px;
        margin-top: 25px;
        font-family: cursive;
        letter-spacing: 20px;
        font-size: 40px;
        margin-top: 25px;
        font-family: cursive;
        letter-spacing: 20px;
    }
    a{}
    #frame{
        width: 100.6%;
        height: 100%;
    }
    .layui-form-select dl dd {
        color: black;
        cursor: pointer;
    }
    #s{
        background-color: red;
    }
    .layui-form-select .layui-input{
        padding-right: 30px;
        cursor: pointer;
        background-color: #393D49!important;
        background-color: #393D49!important;
        border-color: #393D49!important;
        font-size: 20px;
        margin-top: 28px;
       color: white;
        margin-left: 50px;}
    </style>
    <script src="js/jquery.min.js"></script>

    <script>
        function out() {
            var r=confirm("您确定要退出登录嘛？")
            if (r==true){
                location.href="OutSignServlet"
            }}
        $(function () {
            $("#index").click()

        })
    </script>

</head>
<body  class="layui-form" style="">
<div id="magicalDragScene" class="mc-root mc-ui-absolute-pane" style="height: 508px;">
    <div class="layui-row" style="position: absolute; left: 0.398089%; top: 1.57143%; width: 14.2516%; height:700px;">
        <div class="layui-col-xs12 layui-bg-black" style="height: 140px; width: 86.6197%;">
            <img style="width: 76%; height: 142px; margin-left: 10px " src="images/logo.png"/>
        </div>
        <div class="layui-col-xs12 layui-bg-black" style="height: 605px; width: 86.5%;">
            <ul class="layui-nav" style="height: 522px; width: 75%;margin-left: 20px;margin-top:25px  ">
                <li class="layui-nav-item">
                    <a href="First.jsp" target="sss" id="index"  >首页</a>
                </li>
                <li class="layui-nav-item">
                    <a href="Article.jsp" target="sss">文章库</a>
                </li>
                <li id="uuid94b4a816-37dd-4194-b1da-5db4ce0ba643" class="layui-nav-item">
                    <a href="PersonalArticle.jsp" target="sss">个人文章</a>
                </li>
                <li id="uuidb7babd0a-3886-4123-8963-475250b5b8fd" class="layui-nav-item">
                    <a href="UpLoadArticle.jsp" TARGET="sss">文章上传</a>
                </li> <li  class="layui-nav-item">
                    <a href="AlterUser.jsp" TARGET="sss">修改信息</a>
                </li> <li  class="layui-nav-item">
                    <a href="javascript:out()" >退出登录</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-row" style="position: absolute; left: 12.4%; top: 1.5748%; width: 83.6%; height: 700px;">
        <div class="layui-col-xs12 layui-bg-black" style="height: 115px; width: 104.8%;">
            <div class="layui-row" style="height: 65px; width: 95.7711%;">
                <div class="layui-col-md6" style="width: 81.4536%; height: 66px;">
                    <label class="layui-form-label" style="width: 57.6923%; height: 29px; transform: rotate(0deg);"><h1 id="h">智极文章管理系统</h1></label>
                </div>
                <div class="layui-col-md6" style="height: 66px;  width: 13.9098%;margin-top: 40px">
                    <span style="font-size: 24px;margin-top: 21px;margin-left: 120px" >${sessionScope.user.username}</span>
                </div>
            </div>
        </div>
        <div class="layui-col-xs12 mc-ui-absolute-pane" style="height: 627px; width: 103.8%;">
         <iframe width="100%" height="100%" name="sss" id="frame" src="First.jsp">
         </iframe>
        </div>
    </div>
</div>
<script type="text/JavaScript">
    function gradeChange() {
        var objS = document.getElementById("s");
        var grade = objS.options[objS.selectedIndex].value;
        alert(grade);
    }
</script>
<script>
    //以下脚本为标签属性转换成layui组件的还原过程
    //调试:打开浏览器控制台(F12),在代码中某行增加 debugger 即可调试
    var $ = layui.jquery, laytpl = layui.laytpl, laydate = layui.laydate, form = layui.form, layedit = layui.layedit, slider = layui.slider, element = layui.element, colorpicker = layui.colorpicker, upload = layui.upload, rate = layui.rate, carousel = layui.carousel, table = layui.table, flow = layui.flow;
    var magicalDragLayuiUtil = {
        rebuildLayUiControls: function () {
            var _t = this;
            //日期
            $(".magicalcoder-laydate").each(function (idx, item) {
                laydate.render(_t.iteratorAttr({
                    elem: item
                }, item));
            })
            //富文本
            $(".magicalcoder-layedit").each(function (idx, item) {
                var mcDataId = $(item).attr("id");
                if (mcDataId) {
                    layedit.build(mcDataId, {
                        height: 300
                    });
                }
            })
            //外键
            $(".magicalcoder-foreign-select2").each(function (idx, item) {
                var mcDataId = $(item).attr("id");
                if (mcDataId) {
                    $("#" + mcDataId).select2({
                        allowClear: true,
                        width: "150px",
                        delay: 500,
                    });
                }
            })
            //颜色选择器
            $(".magicalcoder-color-picker").each(function (idx, item) {
                colorpicker.render(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //上传组件
            $(".magicalcoder-layupload").each(function (idx, item) {
                upload.render(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //滑块
            $(".magicalcoder-slider").each(function (idx, item) {
                slider.render(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //评分
            $(".magicalcoder-rate").each(function (idx, item) {
                rate.render(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //轮播
            $(".layui-carousel").each(function (idx, item) {
                carousel.render(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //流加载
            $(".magicalcoder-flow").each(function (idx, item) {
                flow.load(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //代码
            $(".magicalcoder-code").each(function (idx, item) {
                layui.code(_t.iteratorAttr({
                    elem: $(item)}, item));
            })
            //弹窗
            $(".magicalcoder-layer").each(function (idx, item) {
                //先隐藏起来
                $(this).next().hide();
                $(this).click(function () {
                    var config = _t.iteratorAttr({
                        elem: $(item)}, item);
                    var type = config.type;
                    if (type + '' == 1) {
                        config.content = $(this).next();
                        if (!config.content.hasClass("magicalcoder-layer-content")) {
                            config.content = config.content.find(".magicalcoder-layer-content")
                        }                }
                    if (config.btn) {
                        config.btn = config.btn.split(",")
                    }
                    if (config.area) {
                        config.area = config.area.split(",")
                    }
                    layer.open(config)
                })
            })
            //动态表格 我们单独封装了layui-table的初始化方式 至于数据排序 返回格式 等操作请根据你的具体环境自行封装
            $(".magicalcoder-table").each(function (idx,
                                                    item) {
                var cols = [];
                //读取列配置
                $(this).find(".magicalcoder-table-th").each(function (i, th) {
                    cols.push(_t.iteratorAttr({
                        title: $(this).text()}, th));
                })
                var tableConfig = _t.iteratorAttr({
                        elem: $(item),
                        cols: [cols]},
                    item);
                //初始化表格 至于返回的数据格式 您可以根据自己的系统自行改造 这里仅做一个示例 参考js\\data\\list.json
                table.render(tableConfig);
            })
            //部分组件初始化
            element.init();
            //表单初始化
            form.render();
        },
        //将标签上的属性 转换成layui函数初始化时的参数名:参数值
        iteratorAttr: function (renderConfig, dom) {
            var attrs = dom.attributes;
            for (var i = 0; i < attrs.length; i++) {
                var attr = attrs[i];
                var name = attr.name;
                var value = attr.value;
                if (name.indexOf("mc-") === 0) {
                    name = name.replace("mc-attr-", '');
                    name = name.replace("mc-event-", '');
                    if (name.indexOf('str-') === 0) {
                        name = name.replace('str-', '');
                    } else if (name.indexOf('bool-') === 0) {
                        name = name.replace('bool-', '');
                        value == 'true' || value === '' ? value = true: value = value;
                        value == 'false' ? value = false: value = value;
                    } else if (name.indexOf('num-') === 0) {
                        name = name.replace('num-', '');
                        if (value !== '' && !isNaN(value)) {
                            value = parseFloat(value);
                        }
                    } else if (name.indexOf('json-') === 0) {
                        name = name.replace('json-', '');
                        if (value !== '') {
                            value = JSON.parse(value);
                        }
                    }
                    renderConfig[this.htmlAttrNameToTuoFeng(name)] = value;
                }
            }
            return renderConfig;
        },
        //user-name -> userName html上的标签名转换成驼峰名称
        htmlAttrNameToTuoFeng: function (name) {
            var arr = name.split("-");
            var newArr = []
            for (var i = 0; i < arr.length; i++) {
                if (i != 0) {
                    if (arr[i] != '') {
                        newArr.push(this.firstCharToUpLower(arr[i]));
                    }
                } else {
                    newArr.push(arr[i]);
                }
            }
            return newArr.join('');
        },
        //首字母大写
        firstCharToUpLower: function (name) {
            var arr = name.split("");
            arr[0] = arr[0].toUpperCase();
            return arr.join('');
        },
    }
    magicalDragLayuiUtil.rebuildLayUiControls();
    //echarts自动初始化
    new McECharts().render();
    //functions-begin
    //functions-end
</script>
</body>

</html>
