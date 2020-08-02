<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->


    <link rel="stylesheet" href="css/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
    <link href="css/popup-box.css" rel="stylesheet" type="text/css" media="all" /> <!-- popup box css -->
    <link rel="stylesheet" href="css/music.css">



    <title>企业需求管理</title>

    <!-- 1. 导入CSS的全局样式 -->
    <%--    <link href="css/bootstrap.min.css" rel="stylesheet">--%>
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <!-- 3. 导入bootstrap的js文件 -->
    <%--    <script src="js/bootstrap.min.js"></script>--%>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>

    <script>
        function deleteUser(id){
            //用户安全提示
            if(confirm("您确定要拒绝吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/enterprise/refuseOne?id="+id;
            }
        }

        function passUser(id){
            //用户安全提示
            if(confirm("您确定要通过吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/enterprise/passOne?id="+id;
            }
        }

        // window.onload = function(){
        //     //给删除选中按钮添加单击事件
        //     document.getElementById("delSelected").onclick = function(){
        //         if(confirm("您确定要删除选中条目吗？")){
        //
        //             var flag = false;
        //             //判断是否有选中条目
        //             var cbs = document.getElementsByName("uid");
        //             for (var i = 0; i < cbs.length; i++) {
        //                 if(cbs[i].checked){
        //                     //有一个条目选中了
        //                     flag = true;
        //                     break;
        //                 }
        //             }
        //
        //             if(flag){//有条目被选中
        //                 //表单提交
        //                 document.getElementById("form").submit();
        //             }
        //
        //         }
        //
        //     };
        //     //1.获取第一个cb
        //     document.getElementById("firstCb").onclick = function(){
        //         //2.获取下边列表中所有的cb
        //         var cbs = document.getElementsByName("uid");
        //         //3.遍历
        //         for (var i = 0; i < cbs.length; i++) {
        //             //4.设置这些cbs[i]的checked状态 = firstCb.checked
        //             cbs[i].checked = this.checked;
        //
        //         }
        //
        //     }
        //
        //
        // }


    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">需求管理</h3>

<%--    <div style="float: left;">--%>

<%--        <form class="form-inline" action="${pageContext.request.contextPath}/teamServlet/findPlayer" method="post">--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleInputName2">用户名</label>--%>
<%--                <input type="text" name="user_name" value="${condition.name[0]}" class="form-control" id="exampleInputName2" >--%>
<%--            </div>--%>
<%--            <div class="form-group">--%>
<%--                <label for="exampleInputName3">上个战队</label>--%>
<%--                <input type="text" name="last_team" value="${condition.address[0]}" class="form-control" id="exampleInputName3" >--%>
<%--            </div>--%>

<%--            <div class="form-group">--%>
<%--                <label for="exampleInputEmail2">邮箱</label>--%>
<%--                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2"  >--%>
<%--            </div>--%>
<%--            <button type="submit" class="btn btn-default">查询</button>--%>
<%--        </form>--%>

<%--    </div>--%>

<%--    <div style="float: right;margin: 5px;">--%>

<%--        &lt;%&ndash;        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>&ndash;%&gt;--%>
<%--        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>--%>

<%--    </div>--%>

    <form id="form" action="" method="post">

        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>标题</th>
                <th>简介</th>
                <th>详情</th>
                <th>单位</th>
                <th>预算</th>
                <th>时间要求</th>
                <th>报名者</th>
                <th>联系方式</th>
                <th>个人介绍</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${demandManage}" var="demand" varStatus="s">
                <c:if test="${demand.result == '0'}">
                <tr>
                    <td><input type="checkbox" name="uid" value="${demand.id}"></td>
                    <td>${s.count}</td>
                    <td>${demand.title}</td>
<%--                    <td><img src="images/${user.icon}" style="width: 50px;height: 50px;"></td>--%>
                    <td>${demand.briefInfo}</td>
                    <td>${demand.specificInfo}</td>
                    <td>${demand.unit}</td>
                    <td>${demand.budget}</td>
                    <td>${demand.deadline}</td>
                    <td>${demand.userName}</td>
<%--                    <c:if test="${demand.userPhone != null}">--%>
                        <td>${demand.userPhone}</td>
<%--                    </c:if>--%>
<%--                    <c:if test="${demand.userPhone == null}">--%>
<%--                        <td></td>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${demand.userMessage != null}">--%>
                        <td>${demand.userMessage}</td>
<%--                    </c:if>--%>
<%--                    <c:if test="${demand.userMessage == null}">--%>
<%--                        <td></td>--%>
<%--                    </c:if>--%>

                    <td>
                            <c:if test="${demand.userName != null}">
                                <a class="btn btn-default btn-sm" href="javascript:passUser(${demand.id});">通过</a>
                                <a class="btn btn-default btn-sm" href="javascript:deleteUser(${demand.id});">拒绝</a>
                            </c:if>
                            <c:if test="${demand.userName == null}">
                                <a></a>
                            </c:if>
<%--                        <a class="btn btn-default btn-sm" href="javascript:passUser(${demand.id});">通过</a>--%>
<%--                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${demand.id});">拒绝</a>--%>
<%--                        <a class="btn btn-default btn-sm" href="submit.jsp?user_name=${user.user_name}">转会</a>--%>
                    </td>
                </tr>
                </c:if>

            </c:forEach>


        </table>
    </form>





    <div class="register-right" >
        <div class="register-in" >
            <a class="book popup-with-zoom-anim button-isi zoomIn animated"
               data-wow-delay=".5s" href="#small-dialog" style="color: red" >发布需求 »</a>
        </div>
    </div>



    <div class="pop-up">
        <div id="small-dialog" class="mfp-hide book-form">
            <div class="login-form login-form-left">
                <div class="agile-row">
                    <div class="login-agileits-top">
                        <form id="sign"  method="post" >
                            <input type="text" id="title" style="color: #00a8e0" name="title" placeholder="标题" required=""/>
                            <br/>
                            <input type="text" id="briefInfo" style="color: #00a8e0" name="briefInfo" placeholder="简介" required=""/>
                            <br/>
                            <textarea class="name" id="specificInfo" name="specificInfo" Placeholder="详情" required=""></textarea>
                            <br/>
                            <input type="text" id="unit" style="color: #00a8e0" name="unit" placeholder="单位" required=""/>
                            <br/>
                            <input type="number" id="budget" style="color: #00a8e0" name="budget" placeholder="预算" required=""/>
                            <br/>
                            <input type="text" id="deadline" style="color: #00a8e0" name="deadline" placeholder="时间要求" required=""/>
                            <br/>
                            <button type="button"  id="signButton">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            $("#signButton").click(function () {
                // var form = new FormData(document.getElementById("submitTransfer"));
                var title = document.getElementById("title").value;
                var briefInfo = document.getElementById("briefInfo").value;
                var specificInfo = document.getElementById("specificInfo").value;
                var unit = document.getElementById("unit").value;
                var budget = document.getElementById("budget").value;
                var deadline = document.getElementById("deadline").value;
                // var deadline = getParameter("deadline");
                var enterpriseId = ${enterpriseId};
                $.ajax({

                    url:"${pageContext.request.contextPath}/enterprise/release",

                    type:"post",

                    data:{
                        title : title,
                        briefInfo : briefInfo,
                        specificInfo : specificInfo,
                        unit : unit,
                        budget:budget,
                        deadline:deadline,
                        enterpriseId:enterpriseId,

                    },

                    success:function(data){
                        // window.clearInterval(timer);
                        //
                        // console.log("over..");
                        if (data == "true"){
                            alert("发布成功！等待管理员审核！");
                        }else{
                            alert("发布失败！");
                        }
                    },
                    error:function(e){

                        alert("错误！！");
                        // window.clearInterval(timer);
                    }
                });
            })
        });
    </script>
<%--    <div>--%>
<%--        <nav aria-label="Page navigation">--%>
<%--            <ul class="pagination">--%>

<%--                <c:if test="${pb.currentPage == 1}">--%>
<%--                    &lt;%&ndash;                    ||${pb.currentPage == pb.totalPage}&ndash;%&gt;--%>
<%--                <li class="disabled">--%>
<%--                    </c:if>--%>

<%--                    <c:if test="${pb.currentPage != 1}">--%>
<%--                        &lt;%&ndash;                    &&${pb.currentPage != pb.totalPage}&ndash;%&gt;--%>
<%--                <li>--%>
<%--                    </c:if>--%>


<%--                    <a href="${pageContext.request.contextPath}/userServlet/pageQuery?currentPage=${pb.currentPage - 1}&pageSize=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">--%>
<%--                        <span aria-hidden="true">&laquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>


<%--                <c:forEach begin="1" end="${pb.totalPage}" var="i" >--%>


<%--                    <c:if test="${pb.currentPage == i}">--%>
<%--                        <li class="active"><a href="${pageContext.request.contextPath}/userServlet/pageQuery?currentPage=${i}&pageSize=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${pb.currentPage != i}">--%>
<%--                        <li><a href="${pageContext.request.contextPath}/userServlet/pageQuery?currentPage=${i}&pageSize=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>--%>
<%--                    </c:if>--%>

<%--                </c:forEach>--%>


<%--                <c:if test="${pb.currentPage == pb.totalPage}">--%>
<%--                <li class="disabled">--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${pb.currentPage != pb.totalPage}">--%>
<%--                <li>--%>
<%--                    </c:if>--%>

<%--                    <a href="${pageContext.request.contextPath}/userServlet/pageQuery?currentPage=${pb.currentPage + 1}&pageSize=5&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">--%>
<%--                        <span aria-hidden="true">&raquo;</span>--%>
<%--                    </a>--%>
<%--                </li>--%>
<%--                <span style="font-size: 25px;margin-left: 5px;">--%>
<%--                    共${pb.totalCount}条记录，共${pb.totalPage}页--%>
<%--                </span>--%>

<%--            </ul>--%>
<%--        </nav>--%>


<%--    </div>--%>


</div>

<%--    <script>--%>
<%--        $(function () {--%>
<%--            $.ajax({--%>
<%--                url:"${pageContext.request.contextPath}/userServlet/pageQuery"--%>
<%--            })--%>
<%--        })--%>
<%--    </script>--%>


<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
<script>
    $(document).ready(function() {
        $('.popup-with-zoom-anim').magnificPopup({
            type: 'inline',
            fixedContentPos: false,
            fixedBgPos: true,
            overflowY: 'auto',
            closeBtnInside: true,
            preloader: false,
            midClick: true,
            removalDelay: 300,
            mainClass: 'my-mfp-zoom-in',
        });

    });
</script>


</body>
</html>
