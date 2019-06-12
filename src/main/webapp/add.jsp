<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>Add meal</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="meals" enctype="application/x-www-form-urlencoded">
        <jsp:useBean id="date" type="java.time.LocalDateTime" scope="request"/>
        <dl>
            <dt>Дата:</dt>
            <dd><input type="datetime-local" name="date" size="50" value="${date}"></dd>
        </dl>
        <dl>
            <dt>Описание:</dt>
            <dd><input type="text" name="description" size="50" value="Описание"></dd>
        </dl>
        <dl>
            <dt>Калории:</dt>
            <dd><input type="number" name="calories" size="50" value="0"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Back</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
