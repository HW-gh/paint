<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>油画分类列表</title>
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
            <a href="/back/CanvasList.do">查看所有油画</a>
        </nav>
        <nav>
            <c:if test="${user == null}">
                <a href="/back/loginPrompt.do">登录</a>
                <a href="#" onclick="alert('功能暂未开放');">注册</a>
            </c:if>
            <c:if test="${user != null}">
                <a href="#">您好,${user.name}</a>
            </c:if>
        </nav>
    </div>
</header>
<section class="banner">
    <div class="container">
        <div>
            <h1>油画分类管理</h1>
            <p>油画分类列表</p>
        </div>
    </div>
</section>
<section class="main">
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>名称</th>
                <th>创建时间</th>
                <th>最后修改时间</th>
                <th>描述</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody id="tbody">
            <c:forEach items="${categorys}" var="type">
                <tr>
                    <td>${type.name}</td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${type.createTime}"/></td>
                    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${type.updateTime}"/></td>
                    <td>${type.description}</td>
                    <td><a href="/back/updateCategoryPrompt.do?id=${type.id}">编辑</a></td>
                    <td><a href="/back/deleteCategory.do?id=${type.id}">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<section class="page">
    <div class="container">
        <div id="fatie">
            <a href="/back/addCategoryPrompt.do">
                <button>新建</button>
            </a>
        </div>


        <!-- <div id="pagefy">
            <ul>
                <form id="messageForm" action="#" method="post">
                    <input type="hidden" id="page" name="page" value="3">
                    <input type="hidden" id="last" name="last" value="1">
                    <li><a href="#" onclick="submitMessageForm('first')">首页</a></li>
                    <li><a href="#" onclick="submitMessageForm('pre')">上一页</a></li>
                    <li><a href="#">当前第1页</a></li>
                    <li><a href="#" onclick="submitMessageForm('next')">下一页</a></li>
                    <li><a href="#" onclick="submitMessageForm('last')">尾页</a></li>
                </form>
            </ul>
        </div> -->
    </div>
</section>
<footer>
    copy@慕课网
</footer>
</body>
</html>