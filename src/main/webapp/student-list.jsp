<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP Demo</title>
</head>
<body>
    <h2>Student List (Rendered via JSP)</h2>
    <ul>
        <c:forEach var="name" items="${list}">
            <li>${name}</li>
        </c:forEach>
    </ul>
</body>
</html>