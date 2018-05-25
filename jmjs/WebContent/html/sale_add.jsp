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
		h3{
			display: inline-block;
		}
    </style>
    <link href="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />  
	
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/moment.js"></script>
    <script src="<%=request.getContextPath() %>/bootstrap-3.3.7-dist/js/bootstrap-datetimepicker.min.js"></script>
</head>

<body>
    <div class="container-fluid">
        <!--  标签型导航 -->
        <div class="row">
            <ul class="nav nav-tabs">
                <li>
                    <a href="<%=request.getContextPath() %>/saleController?method=findAllByPage&condition=">数据查询</a>
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
            <form id="form1" action="<%=request.getContextPath() %>/saleController?method=add" method="post">
                <div class="form-group col-md-6">
                    <label for="sVarietyNum">品种数量</label>
                    <input type="text" readonly="readonly" class="form-control" name="sVarietyNum" id="sVarietyNum" value="0">
                </div>
                <div class="form-group col-md-6">
                    <label for="sTotalPrice">总价</label>
                    <input type="text" readonly="readonly" class="form-control" name="sTotalPrice" id="sTotalPrice" value="0">
                </div>
                <div class="form-group col-md-6">
                    <label for="sSaleDate">销售日期</label>
                    <div class='input-group date' id="datetimepicker">  
                    	<input type="text" class="form-control" name="sSaleDate" id="sSaleDate" placeholder="请输入销售日期">
		                <span class="input-group-addon">  
		                    <span class="glyphicon glyphicon-calendar"></span>  
		                </span>  
		            </div> 
                    
                </div>
                <div class="form-group col-md-6">
                    <label for="customerId">客户</label>
                    <div class="form-group">
                        <select class="form-control" id="customerId" name="customerId">
                        	<c:forEach items="${customers}" var="cus">
                        		<option value="${cus.cId}">${cus.cName}</option>
                        	</c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="sSettlementWay">支付方式</label>
                    <div class="form-group">
                        <select class="form-control" id="sSettlementWay" name="sSettlementWay">
                            <option>现金</option>
                            <option>银行卡</option>
                            <option>信用卡</option>
                            <option>支付宝</option>
                        </select>
                    </div>
                </div>
                
                <div class="col-md-12">
        			<button class="btn btn-primary col-md-12" onclick="return addGoods()">添 加 商 品 明 细</button>
        		</div>
        		<div class="col-md-12" id="addDiv"> 
        			
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

    <script>
    $(function () {  
        $('#datetimepicker').datetimepicker({  
            format: 'YYYY-MM-DD HH:mm:ss',  
            locale: moment.locale('zh-cn')  
        });  
    }); 
    
    	/*
        $('input[id=lefile]').change(function () {
            $('#photoCover').val($(this).val());
        });
		*/
		function save(){
			$("#form1").submit();
		}
        var count = 1;
        function addGoods(){
        	var addDiv = $("#addDiv");

        	var str =   "<div class=\"row\">"+
        				"<div class=\"form-group col-md-4\"><label for=\"gId\">品种</label>"+
        				"<div class=\"form-group\"><select class=\"form-control\" id=\"gId\" name=\"gId\" onchange='getAdvisePrice(this)'>";
        	<c:forEach items="${goods}" var="gs">
        		str+="<option value='${gs.gId}'>${gs.gName}</option>";
        	</c:forEach>
        				
        		str+="</select>";
                    
        		str+="</div></div><div class=\"form-group col-md-4\">"+
        				"<label for=\"goodsPrice\">商品单价</label>"+
        				"<input type=\"text\" class=\"form-control\" name=\"goodsPrice\" id=\"goodsPrice\" onkeyup=\"countTotalPrice(this)\" placeholder=\"请输入商品单价\">"+
        				"</div>"+
        				"<div class=\"form-group col-md-3\"><label for=\"goodsNumber\">商品数量</label>"+
        				"<input type=\"text\" class=\"form-control\" name=\"goodsNumber\" id=\"goodsNumber\" onkeyup=\"countTotalPrice(this)\" placeholder=\"请输入商品数量\">"+
        				"</div>"+
        				"<label>&nbsp;</label>"+
        				"<button class=\"btn btn-danger col-md-1\" onclick=\"return deleteRow(this)\"><span class=\"glyphicon glyphicon-glyphicon glyphicon-trash\"></span></button>"+
        				"</div>";
        	count++;		
        	addDiv.append(str);
        	
        	//每添加一次，都要将商品种类添加一个
        	$("#sVarietyNum").val($("#sVarietyNum").val()*1+1);
        	
        	getAdvisePrice(addDiv.find("select[name='gId']").last())
    		return false;
        }
        
        function deleteRow(t){
        	var f = confirm("确定删除么？");
        	if(f){
        		$("#sVarietyNum").val($("#sVarietyNum").val()*1-1);
        		var row = $(t).parent();
        		var v1 = row.find("input")[0].value;
       			var v2 = row.find("input")[1].value;
       			$("#sTotalPrice").val($("#sTotalPrice").val()*1 - v1*v2);
       			
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
       		$("#sTotalPrice").val(total);
        	
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