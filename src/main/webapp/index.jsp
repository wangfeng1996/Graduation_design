<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>

<head>
    <title>首页界面</title>
    <style type="text/css">
        a {
            text-decoration: none;
            color: black;
            font-size: 20px;
        }
        h1 {
            /*长度*/
            width: 250px;
            /*高度*/
            height: 45px;
            /*外边距*/
            margin: 100px auto;
            /*文本居中*/
            text-align:center;
            /*上下居中*/
            line-height: 38px;
            /*背景色*/
            background: #9fffb1;
            /*圆角边框*/
            border-radius:20px;
        }
    </style>
</head>


<h1>
    <a href="${pageContext.request.contextPath}/login.page">进入图书管理界面</a>
</h1>

</body>
</html>
