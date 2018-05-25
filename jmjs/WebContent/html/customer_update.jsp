<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Document</title>
    <link href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />

    <!--[if lt IE 9]>
     <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
     <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>

    </style>
</head>

<body>

    <div class="container-fluid">
        <!--  标签型导航 -->
        <div class="row">
            <ul class="nav nav-tabs">
                <li>
                    <a href="<%=request.getContextPath() %>/customerController?method=findAllByPage&condition=">数据查询</a>
                </li>
                <li>
                    <a href="javascript:toAdd()">数据添加</a>
                </li>
                <li class="active">
                    <a href="javascript:void(0)">数据修改</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <h3 style="border-bottom: 2px solid #66c9f3;margin-bottom:0px; padding-bottom: 10px ;width:96px">基本信息</h3>
            <hr style="margin-top: 0px ;" />
        </div>
        <div class="row">
            <form id="form1" action="<%=request.getContextPath() %>/customerController?method=update" method="post">
                <input type="hidden" name="cId" value="${customer.cId}">
                <div class="form-group col-md-6">
                    <label for="cName">客户名</label>
                    <input type="text" class="form-control" name="cName" id="cName" value="${customer.cName}" placeholder="请输入公司名称">
                </div>
                <div class="form-group col-md-6">
                    <label for="cPhone">公司联系方式</label>
                    <input type="text" class="form-control" name="cPhone"  id="cPhone" value="${customer.cPhone}" placeholder="请输入公司的座机号码">
                </div>
                <div class="form-group col-md-6">
                    <label for="cAddress">公司地址</label>
                    <input type="text" class="form-control" name="cAddress" id="cAddress" value="${customer.cAddress}" placeholder="请输入公司所在地">
                </div>
                <div class="form-group col-md-6">
                    <label for="cEmail">公司邮箱</label>
                    <input type="text" class="form-control" name="cEmail" id="cEmail" value="${customer.cEmail}" placeholder="请输入企业邮箱">
                </div>
                <div class="form-group col-md-6">
                    <label for="cConName">联系人姓名</label>
                    <input type="text" class="form-control" name="cConName" id="cConName" value="${customer.cConName}" placeholder="请输入您的姓名">
                </div>
                <div class="form-group col-md-6">
                    <label for="cConMobile">联系人手机号</label>
                    <input type="text" class="form-control" name="cConMobile" id="cConMobile" value="${customer.cConMobile}" placeholder="请输入您的手机号">
                </div>
                <div class="form-group col-md-6">
                    <label for="cPostCode">邮编</label>
                    <input type="text" class="form-control" name="cPostCode" id="cPostCode" value="${customer.cPostCode}" placeholder="请输入邮编">
                </div>
                <div class="form-group col-md-6">
                    <label for="cAccount">企业银行账号</label>
                    <input type="text" class="form-control" name="cAccount" id="cAccount" value="${customer.cAccount}" placeholder="请输入企业对公账号">
                </div>
                <div class="col-md-12">
                    <hr/>
                    <div class="row">
                        <div class="col-md-1 col-md-push-9">
                            <button class="btn btn-default" onclick="history.back();" type="button">取消</button>

                        </div>
                        <div class="col-md-1 col-md-push-9">
                            <button class="btn btn-primary" onclick="update()" type="button">修改</button>

                        </div>

                    </div>

                </div>
            </form>
        </div>

    </div>

    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
		/*
        $('input[id=lefile]').change(function () {
            $('#photoCover').val($(this).val());
        });
		*/
		function update(){
			$("#form1").submit();
		}
		function toAdd() {
            window.location = "<%=request.getContextPath() %>/html/customer_add.jsp";
        }
        
    </script>
</body>

</html>