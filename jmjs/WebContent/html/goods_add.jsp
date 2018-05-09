<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <a href="<%=request.getContextPath() %>/goodsController?method=findAll">数据查询</a>
                </li>
                <li class="active">
                    <a href="javascript:void(0)">数据添加</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <h3 style="border-bottom: 2px solid #66c9f3;margin-bottom:0px; padding-bottom: 10px ;width:96px">基本信息</h3>
            <hr style="margin-top: 0px ;" />
        </div>
        <div class="row">
            <form id="form1" action="<%=request.getContextPath() %>/goodsController?method=add" method="post">
                <div class="form-group col-md-6">
                    <label for="gName">商品名称</label>
                    <input type="text" class="form-control" name="gName" id="gName" placeholder="请输入商品名称">
                </div>
                <div class="form-group col-md-6">
                    <label for="gNumber">商品数量</label>
                    <input type="text" class="form-control" name="gNumber" id="gNumber" placeholder="请输入商品数量">
                </div>
                <div class="form-group col-md-6">
                    <label for="gProduce">商品产地</label>
                    <input type="text" class="form-control" name="gProduce" id="gProduce" placeholder="请输入商品数量">
                </div>
                <div class="form-group col-md-6">
                    <label for="gProductionDate">商品生产日期</label>
                    <input type="date" class="form-control" name="gProductionDate" id="gProductionDate" placeholder="请输入商品生产日期">
                </div>
                <div class="form-group col-md-6">
                    <label for="gReleaseDate">商品过期时间</label>
                    <input type="date" class="form-control" name="gReleaseDate" id="gReleaseDate" placeholder="请输入商品过期时间">
                </div>
                <div class="form-group col-md-6">
                    <label for="gType">商品类型</label>
                    <input type="text" class="form-control" name="gType" id="gType" placeholder="请输入商品类型">
                </div>
                <div class="form-group col-md-6">
                    <label for="gUnit">计量单位</label>
                    <input type="text" class="form-control" name="gUnit" id="gUnit" placeholder="请输入计量单位">
                </div>
                <div class="form-group col-md-6">
                    <label for="gSupplier">供应商</label>
                    <div class="form-group">
                        <select class="form-control" id="gSupplier" name="gSupplier">
                            <c:forEach items="${suppliers}" var="suppliers" >
                            	<option>${suppliers.sName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="col-md-12">
	                <div class="form-group">
					    <label for="gRemark">备注</label>
					    <textarea class="form-control" id="gRemark" name="gRemark" rows="2" style="resize:none;"></textarea>
					</div>
                </div>
                <div class="col-md-12">
                    <hr/>
                    <div class="row">
                        <div class="col-md-1 col-md-push-9">
                            <button class="btn btn-default" onclick="history.back();" type="button">取消</button>

                        </div>
                        <div class="col-md-1 col-md-push-9">
                            <button class="btn btn-primary" onclick="save()" type="button">保存</button>

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
		function save(){
			$("#form1").submit();
		}
        
        
    </script>
</body>

</html>