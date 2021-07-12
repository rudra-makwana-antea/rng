<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script>
    <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
    <title>${pageName}</title>
    <link rel="stylesheet" href="/css/standard.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="main-header container-fluid">
    <nav class="navbar navbar-expand-sm navbar-dark">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="img-fluid logo lazyloaded" href="https://antea.tech/">
                    <img src="/images/antea-logo.png" alt="Logo" style="width:50%;">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
            <div class="navbar-collapse collapse" id="collapsibleNavbar">
                <ul class="nav navbar-nav ml-auto">
                    <li
                        <c:if test="${pageName == 'Home'}">class="nav-item active"</c:if>
                        <c:if test="${pageName != 'Home'}">class="nav-item"</c:if>
                    >
                        <a class="nav-link h5" href="/">Home</a>
                    </li>
                    <li
                        <c:if test="${pageName == 'Release Note'}">class="nav-item active"</c:if>
                        <c:if test="${pageName != 'Release Note'}">class="nav-item"</c:if>
                    >
                        <a class="nav-link h5" href="/releasenotes">Release Note Genertor</a>
                    </li>
                    <li
                        <c:if test="${pageName == 'Template Management'}">class="nav-item active"</c:if>
                        <c:if test="${pageName != 'Template Management'}">class="nav-item"</c:if>
                    >
                        <a class="nav-link h5" href="/template">Template Management</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>