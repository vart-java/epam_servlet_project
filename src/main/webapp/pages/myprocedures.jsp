<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Procedures</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/myprocedures.css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #ecc2ff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><c:out value="${user.getRole()}"/> : <c:out
                    value="${user.getSimpleName()}"/></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                    aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/main?command=main">| Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=procedures">My procedures</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Registration
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="#">By master rating</a></li>
                            <li><a class="dropdown-item" href="#">By master name</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">By procedure</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=logOut">Log out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Coming soon</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<h1> Your procedures <span class="badge bg-primary">New</span></h1>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <th scope="col">UniqID</th>
        <th scope="col">Procedure</th>
        <th scope="col">Date</th>
        <th scope="col">Duration</th>
        <th scope="col">Master</th>
        <th scope="col">Confirmed</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="appointment" items="${requestScope.appointments}">
        <c:if test="${appointment.confirmed == true}">
            <tr class="table-success">
        </c:if>
        <c:if test="${appointment.confirmed == false}">
            <tr class="table-primary">
        </c:if>
            <td><c:out value="${appointment.id}"/></td>
            <td><c:out value="${appointment.procedure.name}"/></td>
            <td><c:out value="${appointment.startTime}"/></td>
            <td><c:out value="${appointment.procedure.duration}"/></td>
            <td><c:out value="${appointment.masterLogin}"/></td>
            <td><c:out value="${appointment.confirmed}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
