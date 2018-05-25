<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .form-control{
        	width: 90%;
        }
        .th-fixed{
        	width: 100px;
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
                                            	序号
                                        </label>
                                    </div>
                                </th>
                                <th>商品名称</th>
                                <th>供应商</th>
                                <th>计量单位</th>
                                <th>库存量</th>
                                <th class="th-fixed">建议价格</th>
                                <th class="th-fixed">零售价</th>
                                <th class="th-fixed">促销价</th>
                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageUtil.list}" var="gs" varStatus="vs">
                         <tr>
                                <td>
                                    <div class="checkbox">
                                        <label>
                                           ${vs.count}
                                        </label>
                                    </div>
                                </td>
                                <td>${gs.gName}</td>
                                <td>${gs.gSupplier}</td>
                                <td>${gs.gUnit}</td>
                                <td>${gs.storageNumber}</td>
                                <td><fmt:formatNumber value="${gs.gAdvisePrice}" pattern="0.00"></fmt:formatNumber></td>
                                <td><fmt:formatNumber value="${gs.gSalePrice}" pattern="0.00"></fmt:formatNumber></td>
                                <td><fmt:formatNumber value="${gs.gPromotionPrice}" pattern="0.00"></fmt:formatNumber></td>
                                <td>
                                    <a href="javascript:void(0)" onclick="toUpdate(this,'${gs.gId}')">修改</a>
                                </td>
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

        function toUpdate(ths,id) {
        	var promotionPriceTb = $(ths).parent().prev();
        	promotionPriceTb.html("<input class='form-control' type='text' value='"+promotionPriceTb.html()+"'/>");
        	var salePriceTb = $(ths).parent().prev().prev();
        	salePriceTb.html("<input class='form-control' type='text' value='"+salePriceTb.html()+"'/>");
        	var advisePriceTb = $(ths).parent().prev().prev().prev();
        	advisePriceTb.html("<input class='form-control' type='text' value='"+advisePriceTb.html()+"'/>");
        	$(ths).parent().html("<a href=\"javascript:void(0)\" onclick=\"save(this,'"+id+"')\">保存</a>")
        }
        
        function save(ths,id){
       		var promotionPriceTb = $(ths).parent().prev();
       		var promotionPrice = promotionPriceTb.children("input:first").val();
           	
           	var salePriceTb = $(ths).parent().prev().prev();
           	var salePrice = salePriceTb.children("input:first").val();
           	
           	
           	
           	var advisePriceTb = $(ths).parent().prev().prev().prev();
           	var advisePrice = advisePriceTb.children("input:first").val()
           
           	
           	$.ajax({
           		type:'post',
           		url:'<%=request.getContextPath() %>/goodsController?method=changePrice',
           		data:{"gId":id,"gAdvisePrice":advisePrice,"gSalePrice":salePrice,"gPromotionPrice":promotionPrice},
           		success:function(msg){
           			var msg = JSON.parse(msg);
           			if(msg.msg == "error"){
           				alert("修改失败，请重新提交数据");
           			}else if(msg.msg == "success"){
           				
           				promotionPriceTb.html(msg.object.gPromotionPrice);
           				salePriceTb.html(msg.object.gSalePrice);
           				advisePriceTb.html(msg.object.gAdvisePrice);
           				alert("修改成功");
           			}
           		}
           	});
        	$(ths).parent().html("<a href=\"javascript:void(0)\" onclick=\"toUpdate(this,'"+id+"')\">修改</a>")
        }
        
        function toPage(curPage){
        	location = "<%=request.getContextPath() %>/goodsController?method=findAllPriceByPage&curPage="+curPage;
        }
    </script>



</body>

</html>