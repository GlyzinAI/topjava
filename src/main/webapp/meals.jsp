<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>MealsList</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Дата</th>
            <th>Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr class="${meal.excess ? "excess" : "normal"}">
                <td><%=TimeUtil.format(meal.getDate())%>
                </td>
                <td><%=TimeUtil.format(meal.getTime())%>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?id=${meal.id}&action=edit"><img src="img/edit.png" alt="Edit" width="30" height="30"></a>
                </td>
                <td><a href="meals?id=${meal.id}&action=delete"><img src="img/delete.png" alt="Delete" width="30"
                                                                     height="30"></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="meals?action=add"><img src="img/add2.png" alt="Add2" width="150" height="50" id="add"> </a>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
