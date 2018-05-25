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
                    <a href="<%=request.getContextPath() %>/saleController?method=findAll">数据查询</a>
                </li>
                <li>
                    <a href="javascript:toAdd()">数据添加</a>
                </li>
                <li class="active">
                    <a href="javascript:void(0)">销售单明细</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <h3 style="border-bottom: 2px solid #66c9f3;margin-bottom:0px; padding-bottom: 10px ;width:96px">基本信息</h3>
            <hr style="margin-top: 0px ;" />
        </div>
        <div class="row">
            <form id="form1" action="<%=request.getContextPath() %>/saleController?method=update" method="post">
                <input type="hidden" name="sId" id="sId" value="${sale.sId}">
            <div class="col-md-12">
	            <div class="form-group col-md-6">
	                    <label for="sNo">销售单编号</label>
	                    <input type="text" readonly="readonly" class="form-control" name="sNo" id="sNo" value="${sale.sNo}">
                </div>
                <div class="form-group col-md-6">
                    <label for="sVarietyNum">品种数量</label>
                    <input type="text" readonly="readonly" class="form-control" name="sVarietyNum" id="sVarietyNum" value="${sale.sVarietyNum}">
                </div>
            </div>
             <div class="col-md-12">
              <div class="form-group col-md-6">
                    <label for="sTotalPrice">总价</label>
                    <input type="text" readonly="readonly" class="form-control" name="sTotalPrice" id="sTotalPrice" value="${sale.sTotalPrice}">
                </div>
                <div class="form-group col-md-6">
                    <label for="sSaleDate">销售日期</label>
                    <div class='input-group date' id="datetimepicker">  
                    	<input readonly="readonly" type="text" class="form-control" name="sSaleDate" id="sSaleDate" value="${sale.sSaleDate}" placeholder="请输入销售日期">
		                <span class="input-group-addon">  
		                    <span class="glyphicon glyphicon-calendar"></span>  
		                </span>  
		            </div> 
                    
                </div>
             </div>   
        
              <div class="col-md-12">
               <div class="form-group col-md-6">
                    <label for="cId">客户</label>
                    <div class="form-group">
                        <select  disabled="disabled" class="form-control" id="cId" name="cId">
                        	<c:forEach items="${customers}" var="customer">
                        		<c:choose>
                        			<c:when test="${customer.cId == sale.customerId.cId}">
                        				<option value="${customer.cId}" selected="selected">${customer.cName}</option>
                        			</c:when>
                        			<c:otherwise>
                        				<option value="${customer.cId}" >${customer.cName}</option>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="sSettlementWay">支付方式</label>
                    <div class="form-group">
                        <select  disabled="disabled" class="form-control" id="sSettlementWay" name="sSettlementWay">
                        	<c:forEach items="现金,银行卡,信用卡,支付宝" var="way">
                        		<c:choose>
                        			<c:when test="${way == sale.sSettlementWay}">
                        				<option selected="selected">${way}</option>
                        			</c:when>
                        			<c:otherwise>
                        				<option>${way}</option>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        </select>
                    </div>
                </div>
              </div>
               
                
        		<div class="col-md-12" id="addDiv"> 
        			
        			
        			
        			<div class="table-responsive">
	                    <table class="table table-bordered table-striped table-hover">
	                        <thead>
	                            <tr style="background-color: #c0b8b8;">
	                                <th>序号</th>
	                                <th>商品名称</th>
	                                <th>商品单价</th>
	                                <th>商品数量</th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        <c:choose>
	                        	<c:when test="${!empty sale.saleDetails}">
		                        	<c:forEach items="${sale.saleDetails}" var="saleDetail" varStatus="vs">
				        			 	<tr>
					        			 	<td>${vs.count}</td>
			                                <td>
												<c:forEach items="${goods}" var="gs">
						        					<c:if test="${gs.gId == saleDetail.goodsId.gId}" >
						        						${gs.gName}
						        					</c:if>
						        				</c:forEach>
											</td>
			                                 <td>${saleDetail.salePrice}</td>
			                                <td>${saleDetail.saleNumber}</td>
			                            </tr>
				        			</c:forEach>
	                        	</c:when>
	                        	<c:otherwise>
	                        		<tr>
	                        			<td colspan="10" align="center">销售单中无明细</td>
	                        		</tr>
	                        	</c:otherwise>
	                        </c:choose>
	                        </tbody>
	                    </table>
	                </div>
        			
        			
        		</div>
            </form>
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
		function update(){
			
			$("#form1").submit();
		}
		function toAdd() {
            window.location = "<%=request.getContextPath() %>/saleController?method=toAdd";
        }
		
		
    </script>
</body>

</html>