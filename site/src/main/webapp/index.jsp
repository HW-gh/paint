<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" type="text/css" href="style/common.css"/>
    <link rel="stylesheet" type="text/css" href="style/index.css"/>
</head>
<body>
<script>
    function jump(page) {
        var last = parseInt(${L});
        if(page>last){
            page = last;
        }else if(page<=0){
            page = 1;
        }
        location.href="/CanvasList.do?page="+page+"&categoryId=${categoryId}";
    }
</script>
<div class="header">
    <div class="logo f1">
        <img src="image/logo.png">
    </div>

    <div class="auth fr">
        <ul>
            <li>
                <a href="/CanvasList.do">全部</a>
            </li>
            <c:forEach items="${categorys}" var="type">
            <li>
                <a href="/CanvasListByCategoryId.do?categoryId=${type.id}">${type.name}</a>
            </li>
            </c:forEach>
            <li><a href="#">登录</a></li>
            <li><a href="#">注册</a></li>
        </ul>
    </div>
</div>
<div class="content">
    <div class="banner">
        <img class="banner-img" src="./image/welcome.png" width="732px" height="372" alt="图片描述">
    </div>

    <div class="img-content">
        <ul>
            <c:forEach items="${canvasList}" var="temp">
                <li>
                    <img class="img-li-fix" src="/getImg.do?id=${temp.id}" alt="无名女郎">
                    <div class="info">
                        <h3 class="img_title">${temp.name}</h3>
                        <p>
                            ${temp.description}
                        </p>
                        <div class="btn">
                            <a href="/CanvasDetail.do?id=${temp.id}" class="edit">详情</a>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="page-nav">
        <ul>
            <li><button onclick="jump(1)">首页</button></li>
            <li><button onclick="jump(${P-1})">上一页</button></li>
            <li><span class="first-page">${P}</span></li>
            <li><button onclick="jump(${P+1})">下一页</button></li>
            <li><button onclick="jump(${L})">尾页</button></li>
        </ul>
    </div>
</div>

<div class="footer">
    <p><span>M-GALLARY</span>©2017 POWERED BY IMOOC.INC</p>
</div>
</body>
</html>