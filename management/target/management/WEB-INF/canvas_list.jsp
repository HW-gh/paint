<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>油画列表</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style type="text/css">
        .table tbody tr td{
            max-width: 400px;
            overflow: hidden;
            text-overflow:ellipsis;
            white-space: nowrap;
        }
    </style>
</head>

<body>
<header>
    <div class="container">
        <c:forEach items="${categorys}" var="type">
            <nav>
                <a href="/back/CanvasListByCategoryId.do?categoryId=${type.id}">${type.name}</a>
            </nav>
        </c:forEach>
        <nav>
            <a href="/back/CanvasList.do?">所有油画</a>
        </nav>
        <nav>
            <a href="/back/CategoryList.do">分类管理</a>
        </nav>
        <nav>
            <c:if test="${user==null}">
                <a href="/back/loginPrompt.do">登录</a>
                <a href="#" onclick="alert('功能暂未开放');">注册</a>
            </c:if>
            <c:if test="${user!=null}">
                <a href="#">您好,${user.name}</a>
            </c:if>
        </nav>
    </div>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>油画</h1>
            <p>油画列表</p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>分类</th>
                <th>价格</th>
                <th>创建时间</th>
                <th>最后修改时间</th>
                <th>描述</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${CanvasList}" var="paint">
                <tr>
                    <td>${paint.name}</td>
                    <c:forEach items="${categorys}" var="category">
                        <c:if test="${category.id==paint.categoryId}">
                            <td>${category.name}</td>
                        </c:if>
                    </c:forEach>
                    <td>￥<fmt:formatNumber type="currency" pattern="#,#00.00#" value="${paint.price/100}"/></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${paint.createTime}"/></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${paint.updateTime}"/></td>
                    <td>${paint.description}</td>
                    <td><a href="/back/updateCanvasPrompt.do?id=${paint.id}">编辑</a></td>
                    <td><a href="/back/deleteCanvas.do?id=${paint.id}">删除</a>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="/back/addCanvasPrompt.do">
                <button>新建</button>
            </a>
        </div>
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
</html>