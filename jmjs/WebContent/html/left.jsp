<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1"/>
        <title>菜单页面</title>
        <link href="../bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet" />
    
        <!--[if lt IE 9]>
         <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
         <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
		
    </style>
</head>

<body>
    <div class="panel-group table-responsive" role="tablist">
        <div class="panel panel-default">
            <!-- 标题标签 data-target="#systemManager" 指向下拉标签 -->
            <div class="panel-heading" style="border-bottom: 2px solid #dddddd" data-toggle="collapse" data-target="#infoManager" role="tab">
                <h4 class="panel-title">
                    信息管理
                    <span class="glyphicon glyphicon-chevron-down pull-right"></span>
                </h4>
            </div>
            <!-- systemManager下拉标签,.panel-collapse和.collapse标明折叠元素 没有.in表示不要显示 -->
            <div id="infoManager" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                <div class="list-group">
                    <a class="list-group-item" href="<%=request.getContextPath() %>/customerController?method=findAll" target="right">客户管理</a>
                    <a class="list-group-item" href="<%=request.getContextPath() %>/goodsController?method=findAll&curPage=1" target="right">商品管理</a>
                    <a class="list-group-item" href="<%=request.getContextPath() %>/supplierController?method=findAll" target="right">供应商管理</a>
                </div>
            </div>
            <ul class="nav nav-list">
                <li class="divider"></li>
            </ul>
            
             <div class="panel-heading" style="border-bottom: 2px solid #dddddd" data-toggle="collapse" data-target="#bznessManager" role="tab">
                <h4 class="panel-title">
                    业务管理
                    <span class="glyphicon glyphicon-chevron-up pull-right"></span>
                </h4>
            </div>
            <!-- systemManager下拉标签,.panel-collapse和.collapse标明折叠元素 没有.in表示不要显示 -->
            <div id="bznessManager" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                <div class="list-group">
                    <a class="list-group-item" href="<%=request.getContextPath() %>/depotController?method=findAll" target="right">进货管理</a>
                    <a class="list-group-item" href="_show-list.html" target="right">销售管理</a>
                </div>
            </div>
            <ul class="nav nav-list">
                <li class="divider"></li>
            </ul>
            
            <div class="panel-heading" style="border-bottom: 2px solid #dddddd" data-toggle="collapse" data-target="#stockManager" role="tab">
                <h4 class="panel-title">
                    库存管理
                    <span class="glyphicon glyphicon-chevron-up pull-right"></span>
                </h4>
            </div>
            <!-- systemManager下拉标签,.panel-collapse和.collapse标明折叠元素 没有.in表示不要显示 -->
            <div id="stockManager" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                <div class="list-group">
                    <a class="list-group-item" href="<%=request.getContextPath() %>/storageController?method=findAll" target="right">库存盘点</a>
                    <a class="list-group-item" href="_show-list.html" target="right">价格调整</a>
                </div>
            </div>
            <ul class="nav nav-list">
                <li class="divider"></li>
            </ul>
            
            
            
            <div class="panel-heading" style="border-bottom: 2px solid #dddddd" data-toggle="collapse" data-target="#queryStatistics" role="tab">
                <h4 class="panel-title">
                    查询统计
                    <span class="glyphicon glyphicon-chevron-up pull-right"></span>
                </h4>
            </div>
            <!-- systemManager下拉标签,.panel-collapse和.collapse标明折叠元素 没有.in表示不要显示 -->
            <div id="queryStatistics" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                <div class="list-group">
                    <a class="list-group-item" href="_show-list.html" target="right">客户查询</a>
                    <a class="list-group-item" href="_show-list.html" target="right">商品查询</a>
                    <a class="list-group-item" href="_show-list.html" target="right">供应商查询</a>
                    <a class="list-group-item" href="_show-list.html" target="right">销售查询</a>
                    <a class="list-group-item" href="_show-list.html" target="right">入库查询</a>
                </div>
            </div>
            <ul class="nav nav-list">
                <li class="divider"></li>
            </ul>
            
            
            <!-- 标题标签 data-target="#userManager" 指向下拉标签 -->
            <div class="panel-heading" data-toggle="collapse" data-target="#userManager" role="tab">
                <h4 class="panel-title">
                    系统管理
                    <span class="glyphicon glyphicon-chevron-up pull-right"></span>
                </h4>
            </div>
            <!-- userManager下拉标签,.panel-collapse和.collapse标明折叠元素 .in表示要显示出来 -->
            <div id="userManager" class="panel-collapse collapse" role="tabpanel" aria-labelledby="collapseListGroupHeading1">
                <div class="list-group">
                    <a class="list-group-item" href="#">
                        <span class="badge">220</span>人员管理</a>
                    <a class="list-group-item" href="#">操作管理</a>
                </div>
            </div>



        </div>
    </div>


   
    <script src="../bootstrap-3.3.7-dist/js/jquery-3.1.1.min.js"></script>
    <script src="../bootstrap-3.3.7-dist/js/bootstrap.js"></script>
    <script>
        $(function () {
            /*
                修改列表标题的上下箭头图片
            */
            //展开时触发事件
            $('.collapse').on('show.bs.collapse', function () {
                var sp = $("div[data-target='#" + this.id + "']").find("h4").find("span");
                $(sp).addClass("glyphicon-chevron-down");
                $(sp).removeClass("glyphicon-chevron-up");
            })
            //隐藏时触发事件


            $('.collapse').on('hide.bs.collapse', function () {
                var sp = $("div[data-target='#" + this.id + "']").find("h4").find("span");
                $(sp).addClass("glyphicon-chevron-up");
                $(sp).removeClass("glyphicon-chevron-down");
            })

            /*
                选中的选项显示绿色，其他显示正常
            */
            //先获取所有的a标签
            $(".list-group > a").each(function () {
                //给每个标签绑定点击事件
                $(this).click(function () {
                    //移除自己的气泡徽章
                    $(this).find("span").remove();
                    //移除其他a标签的active类，给自己添加active类
                    $(".list-group > a").each(function () {
                        $(this).removeClass("list-group-item-success");
                    });
                    $(this).addClass("list-group-item-success");

                });
            });


        });
    </script>



</body>

</html>