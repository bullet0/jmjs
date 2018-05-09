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
    <link href="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />

    <!--[if lt IE 9]>
         <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
         <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        /*
        不能直接写th 这样无法覆盖bootstrap的样式，需要这么写才能覆盖
        调整table中的行高
        */

        .row .table tr th {
            padding: 0px;
            padding-left: 10px;
            vertical-align: middle;
        }

        .row .table tr td {
            padding: 0px;
            padding-left: 10px;
            vertical-align: middle;
        }
        body{
        	overflow-x:hidden;
        }
    </style>
</head>

<body>

    <div class="container-fluid">
        <!--  标签型导航 -->
        <div class="row" style="margin-bottom: 20px">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="javascript:void(0)">数据查询</a>
                </li>
                <li>
                    <a href="javascript:toAdd()">数据添加</a>
                </li>
            </ul>
        </div>

        <!-- 中间的表格 -->

        <div class="row" style="margin-bottom: 20px">
            <!-- 表格上面的搜索框 -->
            <form action="tijiao biaodan" class="form-inline" role="form">
                <div class="col-md-4">
                    <a class="btn btn-default" type="button" onclick="toAdd()">
                        <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;添加</a>
                    <a class="btn btn-danger" onclick="deleteAll()" type="button">
                        <span class="glyphicon glyphicon-glyphicon glyphicon-trash"></span>&nbsp;&nbsp;全部删除</a>
                </div>
                <div class="col-md-4">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <select name="type" class="form-control">
                                <option value="1">全部</option>
                                <option value="1">姓名</option>
                                <option value="2">性别</option>
                            </select>
                        </div>
                        <input type="text" class="form-control" id="username" placeholder="请输入你要查询的内容">
                        <span class="input-group-btn">
                            <button class="btn btn-info" type="button">
                                <span class="glyphicon glyphicon-search"></span>&nbsp;&nbsp;搜索</button>
                        </span>
                    </div>
                </div>
            </form>
        </div>

        <!--数据表格 -->
        <div class="row">
            <form id="form1" action="<%=request.getContextPath() %>/goodsController?method=deleteAll" method="post">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped table-hover">
                        <thead>
                            <tr style="background-color: #c0b8b8;">
                                <th>  
                                    <div class="checkbox">
                                        <label>
                                            <input onclick="chooseAll(this.checked)" type="checkbox">序号
                                        </label>
                                    </div>
                                </th>
                                <th>商品名称</th>
                                <th>商品数量</th>
                                <th>产地</th>
                                <th>生产日期</th>
                                <th>过期时间</th>
                                <th>商品类型</th>
                                <th>计量单位</th>
                                <th>供应商</th>
                                <th>备注</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${goods}" var="gs" varStatus="vs">
                         <tr>
                                <td>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" value="${gs.gId}" name="gId"> ${vs.count}
                                        </label>
                                    </div>
                                </td>
                                <td>${gs.gName}</td>
                                <td>${gs.gNumber}</td>
                                <td>${gs.gProduce}</td>
                                <td>${gs.gProductionDate}</td>
                                <td>${gs.gReleaseDate}</td>
                                <td>${gs.gType}</td>
                                <td>${gs.gUnit}</td>
                                <td>${gs.gSupplier}</td>
                                <td>${gs.gRemark}</td>
                                <td>
                                    <a href="javascript:toUpdate('${gs.gId}')">修改</a>
                                </td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
            </form>
            </div>

            <!-- 翻页导航条 -->
            <div class="row">
                <div class="col-md-4 col-md-push-4">
                    <ul class="pagination">
                        <li>
                            <a href="#">&laquo;首页</a>
                        </li>
                        <li class="active">
                            <a href="#">1</a>
                        </li>
                        <li>
                            <a href="#">2</a>
                        </li>
                        <li>
                            <a href="#">3</a>
                        </li>
                        <li>
                            <a href="#">4</a>
                        </li>
                        <li>
                            <a href="#">5</a>
                        </li>
                        <li>
                            <a href="#">尾页&raquo;</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>




    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
        function toAdd() {
            window.location = "<%=request.getContextPath() %>/goodsController?method=toAdd";
        }

        function toUpdate(id) {
            window.location = "<%=request.getContextPath() %>/goodsController?method=toUpdate&gId="+id;
        }
        
        function deleteAll(){
        	$("#form1").submit();
        }
        
        function chooseAll(f){
        	$("input[name='gId']").each(function(){
        			this.checked = f;
        	});
        }
    </script>



</body>

</html>