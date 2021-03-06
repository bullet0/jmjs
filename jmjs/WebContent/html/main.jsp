<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>进销存管理系统</title>
    <link href="../bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />

    <!--[if lt IE 9]>
     <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
     <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>

    </style>
</head>

<body>
    <div class="container-fluid">
        <div class="row">
            <div></div>
            <div class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <!-- 添加button，其中 class="navbar-toggle" type="button" data-toggle="collapse" 是固定写法
                        data-target=".mysetting" 指向下面的div的类，表示要弹出和折叠这个div 
                        -->
                    <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".mysetting">
                        <span class="sr-only">菜单</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!-- logo 写在button外面保证总是显示-->
                    <a href="##" class="navbar-brand">logo</a>
                </div>
                <!-- 将要收缩的ul列表用div包裹，并添加collapse navbar-collapse这两个类 -->
                <div class="collapse navbar-collapse mysetting">
                    <ul class="nav navbar-nav pull-right">
                        <li>
                            <a href="#">
                                <span class="glyphicon glyphicon-user"> <b>${userInfo.uName}</b></span>
                            </a>
                        </li>
                        <li>
                            <a href="##">个人中心</a>
                        </li>
                        <li>
                            <a href="<%=request.getContextPath() %>/loginServlet?method=logout" target="_top">登出</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 左侧导航和右侧主页面 -->
        <div class="row">
            <!-- 左侧导航 -->
            <div class="col-md-2">
                <iframe src="<%=request.getContextPath() %>/html/left.jsp" width="100%" frameborder="no" style="height:500pt;"></iframe>

            </div>
            <!-- 右侧主页面 -->
            <div class="col-md-10">
                <iframe name="right" src="<%=request.getContextPath() %>/html/_right.html" width="100%" frameborder="no" style="height:500pt;"></iframe>
            </div>
        </div>
    </div>
    
    <script src="../bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="../bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
        $(function () {
            /*
                选中的选项显示绿色，其他显示正常
            */
            //先获取所有的a标签
            $(".list-group > a").each(function () {
                //给每个标签绑定点击事件
                $(this).click(function () {
                    //移除自己的气泡徽章
                    $(this).find("span").remove();
                    //移除其他a标签的active类，给自己添加active类
                    $(".list-group > a").each(function () {
                        $(this).removeClass("list-group-item-success");
                    });
                    $(this).addClass("list-group-item-success");

                });
            });

        });

    </script>



</body>

</html>