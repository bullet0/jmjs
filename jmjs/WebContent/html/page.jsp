<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<div class="row">
                <div class="col-md-4 col-md-push-4">
                    <ul class="pagination">
                        <li>
                            <a href="javascript:void(0)" onclick="toPage(1)">&laquo;首页</a>
                        </li>
                        <c:forEach begin="${pageUtil.begin}" end="${pageUtil.end}" var="i">
                        	<c:choose>
                        		<c:when test="${i == pageUtil.curPage}">
                        			<li class="active">
                            			<a href="javascript:void(0)">${i}</a>
                       				</li>
                        		</c:when>
                        		<c:otherwise>
                        			<li>
                            			<a href="javascript:void(0)" onclick="toPage('${i}')">${i}</a>
                       				 </li>
                        		</c:otherwise>
                        	</c:choose>
                        </c:forEach>
                        <li>
                            <a href="javascript:void(0)" onclick="toPage('${pageUtil.totalPage}')">尾页&raquo;</a>
                        </li>
                    </ul>
                </div>
            </div>
</body>
</html>