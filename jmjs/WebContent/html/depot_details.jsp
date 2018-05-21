<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
       <link href="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />  

</head>

<body>

    <div class="container-fluid">
        <!--  标签型导航 -->
        <div class="row">
            <ul class="nav nav-tabs">
                <li>
                    <a href="<%=request.getContextPath() %>/depotController?method=findAll">数据查询</a>
                </li>
               	<li>
                    <a href="javascript:toAdd()">数据添加</a>
                </li>
                <li class="active">
                    <a href="javascript:void(0)">数据明细</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <h3 style="border-bottom: 2px solid #66c9f3;margin-bottom:0px; padding-bottom: 10px ;width:96px">订单信息</h3>
            <hr style="margin-top: 0px ;" />
        </div>
        <div class="row">
                <div class="form-group col-md-6">
                    <label for="dId">订单编号</label>
                    <input type="text" readonly="readonly" class="form-control" name="dId" id="dId" value="${depot.dId}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dVarietyNum">品种数量</label>
                    <input type="text" readonly="readonly" class="form-control" name="dVarietyNum" id="dVarietyNum" value="${depot.dVarietyNum}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dTotalPrice">总价</label>
                    <input type="text" readonly="readonly" class="form-control" name="dTotalPrice" id="dTotalPrice" value="${depot.dTotalPrice}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dDate">进货日期</label>
                    <input type="text" readonly="readonly" class="form-control" name="dDate" id="dDate" value="${depot.dDate}" placeholder="请输入进货日期">
                </div>
                <div class="form-group col-md-6">
                    <label for="sId">供应商</label>
                    <div class="form-group">
                        <select readonly="readonly" class="form-control" id="sId" name="sId">
                        	<c:forEach items="${suppliers}" var="sup">
                        		<c:choose>
                        			<c:when test="${sup.sId == depot.supplierId.sId}">
                        				<option value="${sup.sId}" selected="selected">${sup.sName}</option>
                        			</c:when>
                        			<c:otherwise>
                        				<option value="${sup.sId}">${sup.sName}</option>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="dSettlementWay">支付方式</label>
                    <div class="form-group">
                        <select class="form-control" disabled="disabled" id="dSettlementWay" name="dSettlementWay">
                        	<option selected="selected">${depot.dSettlementWay}</option>
                        </select>
                    </div>
                </div>
                
        		<div class="col-md-12" id="addDiv"> 
        			<div class="table-responsive">
	                    <table class="table table-bordered table-striped table-hover">
	                        <thead>
	                            <tr style="background-color: #c0b8b8;">
	                                <th>序号</th>
	                                <th>品种</th>
	                                <th>商品单价</th>
	                                <th>商品数量</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <c:choose>
	                        	<c:when test="${!empty depot.purchases}">
		                        	<c:forEach items="${depot.purchases}" var="purchase" varStatus="vs">
				        			 	<tr>
					        			 	<td>${vs.count}</td>
			                                <td>
												<c:forEach items="${goods}" var="gs">
						        					<c:if test="${gs.gId == purchase.goodsId.gId}" >
						        						${gs.gName}
						        					</c:if>
						        				</c:forEach>
											</td>
			                                 <td>${purchase.goodsPrice}</td>
			                                <td>${purchase.goodsNumber}</td>
			                            </tr>
				        			</c:forEach>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<tr>
	                        			<td colspan="10" align="center">订单中无明细</td>
	                        		</tr>
	                        	</c:otherwise>
	                        </c:choose>
	                        </tbody>
	                    </table>
	                </div>
        		</div>
        </div>

    </div>

      <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/moment.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.min.js"></script>
    
    <script>
		/*
        $('input[id=lefile]').change(function () {
            $('#photoCover').val($(this).val());
        });
		*/
		
		function toAdd() {
            window.location = "<%=request.getContextPath() %>/depotController?method=toAdd";
        }
        
    </script>
</body>

</html>