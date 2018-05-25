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
            </ul>
        </div>

        <!-- 中间的表格 -->

        <div class="row" style="margin-bottom: 20px">
            <!-- 表格上面的搜索框 -->
            <form action="tijiao biaodan" class="form-inline" role="form">
                <div class="col-md-4  col-md-push-4">
                    <div class="input-group">
                        <div class="input-group-btn">
                            <select name="type" class="form-control">
                                <option>全部</option>
                                <option>库存超出</option>
                                <option>库存低于</option>
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
                                            	序号
                                        </label>
                                    </div>
                                </th>
                                <th>商品名称</th>
                                <th>供应商名称</th>
                                <th>商品平均单价<span class="label label-warning" style="float: right;margin-right: 10px;cursor: default;" title="平均价格是按照商品总价值除以总库存量求得的">!</span></th>
                                <th>商品类型</th>
                                <th>商品库存数量<span class="label label-warning" style="float: right;margin-right: 10px;cursor: default;" title="当库存数量低于100时以红色字体显示">!</span></th>
                                <th>商品总价值</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageUtil.list}" var="storage" varStatus="vs">
                         <tr>
                                <td>
                                    <div class="checkbox">
                                        <label>
                                             ${vs.count}
                                        </label>
                                    </div>
                       		    </td>
                                <td>${storage.sGoodsName}</td>
                                <td>${storage.sSupplierName}</td>
                                <td>${storage.sAvgPrice}</td>
                                <td>${storage.sType}</td>
                                <td>
                                	<c:choose>
                                		<c:when test="${storage.sSumStockNum < 100}">
                                			<font color="red">${storage.sSumStockNum}</font>
                                		</c:when>
                                		<c:otherwise>
                                			${storage.sSumStockNum}
                                		</c:otherwise>
                                	</c:choose>
                                </td>
                                <td>${storage.sSumPrice}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
            </div>

            <!-- 翻页导航条 -->
            <jsp:include page="/html/page.jsp"></jsp:include>
        </div>
    




    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
	    function toPage(curPage){
	    	location = "<%=request.getContextPath() %>/storageController?method=findAllByPage&curPage="+curPage;
	    }
    </script>



</body>

</html>