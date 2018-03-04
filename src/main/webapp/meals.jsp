<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://topjava.javawebinar.com/functions" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .normal {
            color: green;
        }
        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<div>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <table border="1">
        <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th colspan="2">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${mealList}" var="meal">
                <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                    <td>${f:formatLocalDateTime(meal.dateTime, 'dd-MM-yyyy HH:mm')}</td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update meal</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete meal</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>