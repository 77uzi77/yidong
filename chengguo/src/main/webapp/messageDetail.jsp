<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset=utf-8" />
    <title>需求详情</title>
    <script type="text/javascript" src="js/jquery-3.3.1.js" ></script>

    <link rel="stylesheet" href="css/font-awesome.css"> <!-- Font-Awesome-Icons-CSS -->
    <link href="css/popup-box.css" rel="stylesheet" type="text/css" media="all" /> <!-- popup box css -->
    <link rel="stylesheet" href="css/music.css">

    <link rel="stylesheet" type="text/css" href="css/common.css">
    <link rel="stylesheet" href="css/message.css">
    <link rel="stylesheet" type="text/css" href="css/route-detail.css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="js/getParameter.js"></script>
</head>
<body>
    <script>
        $(function () {
            //1.获取rid  <img src="'images/'+route.picture1+">
            var detailId = getParameter("demandId");
            <%--var pb = ${pb};--%>
            // var detail;
            <%--for (var i = 0; i < ${fn:length(pb.list)}; i++) {--%>
            <c:forEach  items="${pb.list}" var="detail">
                <%--alert(${detail});--%>
            var readId = ${detail.id};
                <%--alert(readId);--%>
                <%--alert(${detail.id});--%>
                <%--alert(detailId);--%>
                if (readId == detailId){
                    // alert(1);
                    <%--detail = ${pb.list[i]};--%>

            <%--var id = ${requestScope.gid};--%>
            //alert(id);
            //2.发送请求请求 route/findOne
            // $.get("transferInfo/findOne", {id: id}, function (route) {
            //     //3.解析数据填充html
                $("#rname").html("标题："+"${detail.title}");
            //     var position;
            //     switch (route.cid) {
            //         case "1":
            //             position = "上单";break;
            //         case "2":
            //             position = "打野";break;
            //         case "3":
            //             position = "中单";break;
            //         case "4":
            //             position = "射手";break;
            //         case "5" :
            //             position = "辅助";break;
            //     }

                $("#poi").html("单位：" + "${detail.unit}");

                $("#routeIntroduce").html("简介："+"${detail.briefInfo}");
                $("#price").html("预算：¥" + "${detail.budget}" + "起");
                $("#specificInfo").html("详情：" + "${detail.specificInfo}");
                $("#deadline").html("时间要求：" + "${detail.deadline}");
                // var images = "历史高光:<br/>";
                // if (route.picture1 != null) {
                //     images += '<img src="\images/'+route.picture1+'" style="width: 100px;height: 100px">'
                // }
                // if (route.picture2 != null) {
                //     images += '<img src="\images/'+route.picture2+'" style="width: 100px;height: 100px">'
                // }
                // if (route.picture3 != null) {
                //     images += '<img src="\images/'+route.picture3+'" style="width: 100px;height: 100px">'
                // }
                // $("#picture").html(images);

                // recommend(route.cid);


                // $("#sname").html(route.seller.sname);
                // $("#consphone").html(route.seller.consphone);
                // $("#address").html(route.seller.address);
                // //设置收藏次数
                // $("#favoriteCount").html("已收藏"+route.count+"次");
            // })
            //     }

            }
            </c:forEach>
        });

        function recommend(cid) {
            // var localId = getParameter("gid");
            var localId = "${requestScope.gid}";
            $.ajax({
                url:"${pageContext.request.contextPath}/transferInfo/recommend",
                type: "POST",
                data:{
                    cid : cid,
                    localId:localId,
                },
                success: function (data) {
                if (data.status === true) {
                    var message = "";
                    var list = data.data;
                    // alert(list.length);
                    for (var i = 0;i < list.length;i++){
                        // alert(111);
                        message += '<span style="float: left">';
                        message += '<img src="\images/'+list[i].picture1 +'" style="width: 100px;height: 100px;">';
                        message += '<div ">'+list[i].user_name +'</div>';
                        message += '<br/>';
                        message += '<div><a href="selectMessage?gid='+list[i].id+'" style="color: red;">查看详情</a></div>'
                        message += '</span>'
                    }
                    // alert(222);
                    $("#recommend").html(message);
                }else{
                    alert(data.message);
                }
            }
            })
        }
    </script>
    <div class="pros_title" id="rname"></div>
    <div class="pros_title" id="poi"></div>
    <div class="hot" id="routeIntroduce"></div>
    <div class="pros_price">
        <p class="price"><strong id="price"></strong><span></span></p>


<%--        <p class="collect">--%
<%--            <a class="btn" id="favorite" onclick="addFavorite();"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>--%>

<%--            <!-- <a  class="btn already" disabled="disabled"><i class="glyphicon glyphicon-heart-empty"></i>点击收藏</a>-->--%>
<%--            <span id="favoriteCount">已收藏100次</span>--%>
<%--        </p>--%>
    </div>
    <div id = "specificInfo"></div>
    <div id = "deadline"></div>
<%--    <c:if test="${user.status == 3 || user.status == 4}">--%>
<%--    <div><a href="${pageContext.request.contextPath}/transferInfo/download?id=${requestScope.gid}" style="color: red">下载转会信息</a></div>--%>
<%--    </c:if>--%>
    <span id="picture"></span>


    <br/>
    <br/>

    <c:if test="${user.status == 1 || user.status == 2}">
    <div class="register-right" >
        <div class="register-in" >
            <a class="book popup-with-zoom-anim button-isi zoomIn animated"
               data-wow-delay=".5s" href="#small-dialog" style="color: red" >报名需求 »</a>
        </div>
    </div>
    </c:if>

    <div class="pop-up">
        <div id="small-dialog" class="mfp-hide book-form">
            <div class="login-form login-form-left">
                <div class="agile-row">
                    <div class="login-agileits-top">
                        <form id="sign"  method="post" >
                            <input type="text" id="linkName" style="color: #00a8e0" name="linkName" placeholder="报名者" required=""/>
                            <br/>
                            <input type="number" id="phoneNum" style="color: #00a8e0" name="phoneNum" placeholder="联系电话" required=""/>
                            <br/>
                            <textarea class="name" id="introduction" name="introduction" Placeholder="个人信息" required=""></textarea>
                            <br/>
                            <button type="button"  id="signButton">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>




<%--    <br/>--%>
<%--    <div>相关推荐</div>--%>
<%--    <hr/>--%>
<%--    <div id="recommend"></div>--%>

<%--    <c:if test="${user.status == 2 || user.status == 3 || user.status == 4}">--%>

<%--&lt;%&ndash;        <script>&ndash;%&gt;--%>
<%--&lt;%&ndash;            var gid = getParameter("rid");&ndash;%&gt;--%>
<%--&lt;%&ndash;            $(function x() {&ndash;%&gt;--%>
<%--&lt;%&ndash;                location.href = "selectMessage?gid="+gid;&ndash;%&gt;--%>
<%--&lt;%&ndash;            })&ndash;%&gt;--%>
<%--&lt;%&ndash;        </script>&ndash;%&gt;--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <br/>--%>
<%--    <div id="content">--%>
<%--        <div><!-- 评论展示内容 -->--%>
<%--            <div class="MessagecontentBigBox"><!-- 内容外层div -->--%>
<%--                <div class="MessagecontentBigBox-top"> <font>评论区</font> </div>--%>
<%--                <div class="messageinfodiv">--%>
<%--                    <div class="publishmessage">--%>
<%--                        <form action="addMessageBy" method="post" onsubmit="return checkdata()">--%>
<%--                            <div class="publishmessagein">--%>
<%--                                <textarea maxlength="60" id="messagecontent" name="messagecontent"></textarea>--%>
<%--                            </div>--%>
<%--                            <input type="hidden" name="gid" value="<c:out  value="${requestScope.gid }"/>" />--%>
<%--                            <div class="publishmessagebutton">--%>
<%--                                <input type="submit" value="发送">&nbsp;&nbsp;--%>
<%--                                <input type="reset" value="取消">--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                    </div>--%>
<%--                    <ul>--%>
<%--                        <c:forEach items="${requestScope.messageinfo }" var="msg">--%>
<%--                            <li class="thismessageinfodiv">--%>
<%--                                <img src="<c:out value="images/${msg.getWimg()}"/>" class="messageuserimg"/>--%>
<%--                                <font class="messageusername"><c:out value="${msg.getWname() }"/></font><br/>--%>
<%--                                <p class="messageusercontent"> <c:out value="${msg.getMessageText()}" escapeXml="true" default="加载失败"/> </p>--%>
<%--                                <p class="messageusertime"><c:out value="${msg.getWritetime() }"/></p>--%>
<%--                                <font class="messageuserreply" id="<c:out value="${msg.getId() }"/>">回复</font>--%>
<%--                                <c:forEach items="${msg.getReplyMessage() }" var="rel">--%>
<%--                                    <div class="messagereplydiv">--%>
<%--                                        <img class="messagereplyimg" src="<c:out value="images/${rel.getImg()}"/>"/>--%>
<%--                                        <font class="messagereplyname"><c:out value="${rel.getName()}"/></font>--%>
<%--                                        <font class="messagereplycontent"><c:out value="${rel.getContent()}"/></font>--%>
<%--                                        <p class="messagereplytime"><c:out value="${rel.getTime()}"/></p>--%>
<%--                                    </div>--%>
<%--                                </c:forEach>--%>
<%--                                <div class="messageuserreplydiv" id="replybigdiv<c:out value="${msg.getId() }"/>" style="display: none">--%>
<%--                                    <form action="messageUserReply" method="post" id="replymessageform">--%>
<%--                                        <input type="hidden" name="thismessageid" value="<c:out value="${msg.getId() }"/>" />--%>
<%--                                        <input type="hidden" name="gid" value="<c:out value="${requestScope.gid }"/>" />--%>
<%--                                        <textarea required id="replycontent" name="replycontent"></textarea><br/>--%>
<%--                                        <input type="submit" value="确定">&nbsp;&nbsp;--%>
<%--                                        <input type="reset" value="取消" class="cancelinput">--%>
<%--                                    </form>--%>
<%--                                </div>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>


<%--    <script>--%>





<%--        $(document).ready(function (){--%>
<%--            var ti;--%>
<%--            $("#userinfo").mouseover(function(){$("#usernameshow").fadeIn(200); });--%>
<%--            $("#usernameshow").mouseover(function(){--%>
<%--                clearTimeout(ti);--%>
<%--                $("#usernameshow").show();--%>
<%--            });--%>
<%--            $("#usernameshow").mouseout(function(){$("#usernameshow").hide(); });--%>
<%--            $("#userinfo").mouseout(function(){--%>
<%--                $("#usernameshow").stop(false,true);--%>
<%--                ti=setTimeout(function(){--%>
<%--                    $("#usernameshow").hide();--%>
<%--                },100);--%>
<%--            });--%>
<%--            $(".messageuserreply").click(function(){--%>
<%--                var a=this.id;--%>
<%--                $("[id$='div"+a+"']").toggle();--%>
<%--            });--%>
<%--            $(".cancelinput").click(function(){$(this).parents(".messageuserreplydiv").hide(); });--%>

<%--        });--%>
<%--        function checkdata(){var a=document.getElementById("messagecontent").value; if(a==null||a==""){alert("输入不能为空！发布失败。"); return false; } }--%>
<%--    </script>--%>

<%--    </c:if>--%>


    <script>
        $(function () {
            $("#signButton").click(function () {
                // var form = new FormData(document.getElementById("submitTransfer"));
                var introduction = document.getElementById("introduction").value;
                var linkName = document.getElementById("linkName").value;
                var phoneNum = document.getElementById("phoneNum").value;
                var detailId = getParameter("demandId");
                var uid = ${user.id};
                $.ajax({

                    url:"${pageContext.request.contextPath}/demand/sign",

                    type:"post",

                    data:{
                        introduction : introduction,
                        linkName : linkName,
                        phoneNum : phoneNum,
                        uid : uid,
                        demandId:detailId,
                    },

                    success:function(data){
                        // window.clearInterval(timer);
                        //
                        // console.log("over..");
                        if (data == "true"){
                            alert("报名成功！等待企业审核！");
                        }else{
                            alert("报名失败！");
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


<%--    <style>--%>
<%--        .personal{--%>
<%--            margin: 20px 100px;--%>
<%--            border: 2px solid;--%>
<%--        }--%>
<%--        .personal-item{--%>
<%--            height: 50px;--%>
<%--            border-bottom: 2px solid;--%>
<%--            line-height: 50px;--%>
<%--            text-align: center;--%>
<%--        }--%>

<%--    </style>--%>
</body>
</html>
