<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meals</title>
</head>
<body>
<h3><a href="meals.jsp">Back</a></h3>
<div>
    <h3>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h3>
    <form action="meals" method="post">
        <input type="hidden" name="id" value="${meal.id}">
        Create date: <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
        Description: <input type="text" value="${meal.description}" name="description">
        Calories: <input type="number" value="${meal.calories}" name="calories">
        <button type="submit">Save</button>
    </form>
</div>
</body>
</html>