<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/add.css">
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/back">
                慕课网油画管理
            </a>
        </div>
    </div>
</nav>
<div class="container">
    <div class="jumbotron">
        <h1>Hello, admin!</h1>
        <p>请小心的更新油画分类。。。</p>
    </div>
    <div class="page-header">
        <h3><small>编辑</small></h3>
    </div>
    <form class="form-horizontal" action="/back/updateCategory.do" method="post">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">名称 ：</label>
            <div class="col-sm-8">
                <input type="hidden" name="id" class="form-control" id="id" value="${category.id}">
                <input name="name" class="form-control" id="name" value="${category.name}">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">描述 ：</label>
            <div class="col-sm-8">
                <input type="text" name="description" class="form-control" id="description" value="${category.description}">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;&nbsp;
            </div>
        </div>
    </form>
</div>
<footer class="text-center" >
    copy@imooc
</footer>
</body>
</html>
