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
                    <a href="javascript:void(0)">数据修改</a>
                </li>
            </ul>
        </div>
        <div class="row">
            <h3 style="border-bottom: 2px solid #66c9f3;margin-bottom:0px; padding-bottom: 10px ;width:96px">基本信息</h3>
            <hr style="margin-top: 0px ;" />
        </div>
        <div class="row">
            <form id="form1" action="<%=request.getContextPath() %>/depotController?method=update" method="post">
                <input type="hidden" name="dId" id="dId" value="${depot.dId}">
            <div class="col-md-12">
            	<div class="form-group col-md-6">
                    <label for="dNo">订单编号</label>
                    <input type="text" readonly="readonly" class="form-control" name="dNo" id="dNo" value="${depot.dNo}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dVarietyNum">品种数量</label>
                    <input type="text" readonly="readonly" class="form-control" name="dVarietyNum" id="dVarietyNum" value="${depot.dVarietyNum}">
                </div>
            </div>
            
            <div class="col-md-12">
             <div class="form-group col-md-6">
                    <label for="dTotalPrice">总价</label>
                    <input type="text" readonly="readonly" class="form-control" name="dTotalPrice" id="dTotalPrice" value="${depot.dTotalPrice}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dDate">进货日期</label>
                    <div class='input-group date' id="datetimepicker">  
                    	<input type="text" class="form-control" name="dDate" id="dDate" value="${depot.dDate}" placeholder="请输入进货日期">
		                <span class="input-group-addon">  
		                    <span class="glyphicon glyphicon-calendar"></span>  
		                </span>  
		            </div> 
                    
                </div>
            </div>
                
              <div class="col-md-12">
               <div class="form-group col-md-6">
                    <label for="sId">供应商</label>
                    <div class="form-group">
                        <select class="form-control" id="sId" name="sId">
                        	<c:forEach items="${suppliers}" var="supplier">
                        		<c:choose>
                        			<c:when test="${supplier.sId == depot.supplierId.sId}">
                        				<option value="${supplier.sId}" selected="selected">${supplier.sName}</option>
                        			</c:when>
                        			<c:otherwise>
                        				<option value="${supplier.sId}">${supplier.sName}</option>
                        			</c:otherwise>
                        		</c:choose>
                        	</c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="dSettlementWay">支付方式</label>
                    <div class="form-group">
                        <select class="form-control" id="dSettlementWay" name="dSettlementWay">
                        	<c:forEach items="现金,银行卡,信用卡,支付宝" var="way">
                        		<c:choose>
                        			<c:when test="${way == depot.dSettlementWay}">
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
               
                
                <div class="col-md-12">
        			<button class="btn btn-primary col-md-12" onclick="return addGoods()">添 加 商 品 明 细</button>
        		</div>
        		<div class="col-md-12" id="addDiv"> 
        			<c:if test="${!empty depot.purchases}">
        			<c:forEach items="${depot.purchases}" var="purchase">
        			 	<div class="row">
	        				<div class="form-group col-md-4">
		        				<label for="gId">品种</label>
		        				<div class="form-group">
			        				<select class="form-control" id="gId" name="gId" onchange='getAdvisePrice(this)'>
			        				<c:forEach items="${goods}" var="gs">
			        					<c:choose>
			        						<c:when test="${gs.gId == purchase.goodsId.gId}">
			        							<option selected="selected" value='${gs.gId}'>${gs.gName}</option>
			        						</c:when>
			        						<c:otherwise>
			        							<option value='${gs.gId}'>${gs.gName}</option>
			        						</c:otherwise>
			        					</c:choose>
			        				</c:forEach>
			        				</select>
		        				</div>
	        				</div>
	        				<div class="form-group col-md-4">
		        				<label for="goodsPrice">商品单价</label>
		        				<input type="text" class="form-control" name="goodsPrice" id="goodsPrice" onkeyup="countTotalPrice(this)" placeholder="请输入商品单价" value="${purchase.goodsPrice}">
	        				</div>
	        				<div class="form-group col-md-3">
		        				<label for="goodsNumber">商品数量</label>
		        				<input type="text" class="form-control" name="goodsNumber" id="goodsNumber" onkeyup="countTotalPrice(this)" placeholder="请输入商品数量" value="${purchase.goodsNumber}">
	        				</div>
	        					<label>&nbsp;</label>
	        					<button class="btn btn-danger col-md-1" onclick="return deleteRow(this)"><span class="glyphicon glyphicon-glyphicon glyphicon-trash"></span></button>
	        			</div>
        			</c:forEach>
        			</c:if>
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
            window.location = "<%=request.getContextPath() %>/depotController?method=toAdd";
        }
		
		var count = 1;
        function addGoods(){
        	var addDiv = $("#addDiv");

        	var str =   "<div class=\"row\">"+
        				"<div class=\"form-group col-md-4\"><label for=\"gId\">品种</label>"+
        				"<div class=\"form-group\"><select class=\"form-control\" id=\"gId\" name=\"gId\">";
        	<c:forEach items="${goods}" var="gs">
        		str+="<option value='${gs.gId}'>${gs.gName}</option>";
        	</c:forEach>
        				
        		str+="</select>";
                    
        		str+="</div></div><div class=\"form-group col-md-4\">"+
        				"<label for=\"goodsPrice\">商品单价</label>"+
        				"<input type=\"text\" class=\"form-control\" name=\"goodsPrice\" id=\"goodsPrice\" onkeyup=\"countTotalPrice(this)\" placeholder=\"请输入商品单价\">"+
        				"</div><div class=\"form-group col-md-3\"><label for=\"goodsNumber\">商品数量</label>"+
        				"<input type=\"text\" class=\"form-control\" name=\"goodsNumber\" id=\"goodsNumber\" onkeyup=\"countTotalPrice(this)\" placeholder=\"请输入商品数量\">"+
        				"</div>"+
        				"<label>&nbsp;</label>"+
        				"<button class=\"btn btn-danger col-md-1\" onclick=\"return deleteRow(this)\"><span class=\"glyphicon glyphicon-glyphicon glyphicon-trash\"></span></button>"+
        				"</div>";
        	count++;		
        	addDiv.append(str);
        	//每添加一次，都要将商品种类添加一个
        	$("#dVarietyNum").val($("#dVarietyNum").val()*1+1);
        	
        	getAdvisePrice(addDiv.find("select[name='gId']").last());
    		return false;
        }
        
        function deleteRow(t){
        	var f = confirm("确定删除么？");
        	if(f){
        		$("#dVarietyNum").val($("#dVarietyNum").val()*1-1);
        		var row = $(t).parent();
        		var v1 = row.find("input")[0].value;
       			var v2 = row.find("input")[1].value;
       			$("#dTotalPrice").val($("#dTotalPrice").val()*1 - v1*v2);
       			
       			row.remove();
        	}
        	return false;
        }
        function countTotalPrice(t){
        	
       		var total = 0;
       	
       		var rows = $("#addDiv > .row");
       		for(var i = 0;i<rows.length ; i++){
       			var v1 = $(rows[i]).find("input")[0].value;
       			var v2 = $(rows[i]).find("input")[1].value;
       			total += v1*v2;
       		}
       		$("#dTotalPrice").val(total);
        	
        }
        function getAdvisePrice(ths){
        	$.ajax({
        		type:'post',
        		url:'<%=request.getContextPath() %>/goodsController?method=getAdvisePrice',
        		data:'gId='+$(ths).val(),
        		success:function(msg){
        			var inp = $(ths).parent().parent().next().children("input");
        			inp.val(msg)
        			//价格改变后  计算总价
        			countTotalPrice(inp);
        		}
        	});
        }
    </script>
</body>

</html>