<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  	<title>进销存管理系统</title>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />

    <!--[if lt IE 9]>
         <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
         <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <style>
        .remove-margin-bottom {
            margin-bottom: 0;
            background-color: white;
            border: 0px hidden;
        }

        .login-form-div {
            background-color: #f0f7f8;
            border: #f0f7f0 double 5px;
            margin-top: 40px;
            margin-bottom: 40px;
            padding-left: 30px;
            padding-right: 30px;
            padding-bottom: 25px;
            border-radius: 30px;
            -moz-border-radius: 25px;
            /* Old Firefox */
        }

        p {
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .middle{
	        background-image: url('img/banner.jpg');
	        background-size: 100% 100%;
	        height: 450pt;
        }
    </style>
</head>

<body>
    <!-- 登录页面的头部导航，使用响应式导航 -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="navbar navbar-default remove-margin-bottom" role="navigation">
                    <div class="navbar-header">
                        <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".about">
                            <span class="sr-only">关于</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a href="主站网址" class="navbar-brand">文字标题</a>
                        <!--可以换用 图片logo
                            <a class="navbar-brand" href="#">
                                <img alt="Brand" style="max-width:100px;margin-top:-7px;" src="../img/banner.png">
                            </a>
                             -->

                    </div>
                    <div class="collapse navbar-collapse about">
                        <ul class="nav navbar-nav pull-right">
                            <li>
                                <a href="#">联系我们</a>
                            </li>
                            <li class="">
                                <a href="#">关于我们</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 中间为登录from表单面板，背景图片设成全屏，form使用div包裹放在右侧 -->
    <div class="container-fluid middle" >
        <div class="row">
            <div class="col-md-4 col-md-offset-7">
                <div class="login-form-div">
                    <h3>用户登录</h3>
                    <hr/>
                    <form id="loginForm" action="<%=request.getContextPath() %>/loginServlet?method=login" method="post">

                        <div id="usernameDiv" class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-user"></span>
                                </div>
                                <input id="username" type="text" class="form-control input-lg" id="username" name="uName" placeholder="请输入您的账号">
                            </div>
                        </div>
                        <div id="passwordDiv" class="form-group">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                                <input id="password" type="password" class="form-control input-lg" name="uPwd"  placeholder="请输入您的密码">
                            </div>
                        </div>
                        <input type="submit" class="btn btn-primary btn-lg btn-block" type="button" value="登录" onclick="return sbm()" />
						<c:if test="${errorMsg == true}">
							<div id='err' class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 15px;margin-bottom: 0'>
                    			<button type='button' class='close' data-dismiss='alert' aria-label='Close'>
                    				<span aria-hidden='true'>&times;</span>
                    			</button>	
                			您的用户名密码<strong>不匹配</strong>，请重新输入！
               		 		</div>
						</c:if>
						
                    </form>

                </div>

            </div>
        </div>
    </div>
    <!-- 底部提供一些版权信息，同时提供发送邮件功能，但需要用户安装支持发邮件的软件才行 -->
    <div class="container">
        <p class="text-center">我的邮箱:
            <a href="Mailto:871406098@qq.com">871406098@qq.com</a>
        </p>
        <p class="text-center">Copyright © 王印</p>
        <p class="text-center">Powered By 王印 V1.1</p>
    </div>



    <script src="bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
        function sbm() {
            if ($("#username").val().trim() == "") {
                $("#err").remove();

                var html =
                    "<div id='err' class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 15px;margin-bottom: 0'>" +
                    "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                    "<span aria-hidden=''true'>&times;</span>" +
                    "</button>" 
                   

                html += "您的<strong>用户名</strong>不能为空";
                html +=  "</div>";
                $("#loginForm").append($(html));

                $("#usernameDiv").addClass("has-error");
                return false;
            } else {
                $("#err").remove();
                $("#usernameDiv").removeClass("has-error");
            }


            if ($("#password").val().trim() == "") {
                $("#err").remove();

                var html =
                    "<div id='err' class='alert alert-danger alert-dismissible' role='alert' style='margin-top: 15px;margin-bottom: 0'>" +
                    "<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
                    "<span aria-hidden=''true'>&times;</span>" +
                    "</button>";
                    

                html += "您的<strong>密码</strong>不能为空";
                html +=  "</div>";
                $("#loginForm").append($(html));

                $("#passwordDiv").addClass("has-error");
                return false;
            } else {
                $("#err").remove();
                $("#passwordDiv").removeClass("has-error");
            }
        }
    </script>



</body>

</html>