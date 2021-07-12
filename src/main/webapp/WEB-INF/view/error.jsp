<%@ page language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ include file="parts/header.jsp" %>
<body>
<div class="container">
    <br/>
    <div class="container">
        <h1 style="text-align: center">Error</h1>
    </div>
    <table class="table">
        <tr>
            <td>Date:</td>
            <td>${timestamp}</td>
        </tr>
        <tr>
            <td>Error:</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status:</td>
            <td>${status}</td>
        </tr>
        <tr>
            <td>Message:</td>
            <td>${message}</td>
        </tr>
        <tr>
            <td>Exception:</td>
            <td>${exception}</td>
        </tr>
        <tr>
            <td>Trace:</td>
            <td>
                ${trace}
            </td>
        </tr>
    </table>
</div>
</body>
</html>