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
                        ${user.role.name().equals('GUEST') ?
                                '<a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">My procedures</a>' :
                                (user.role.name().equals('ADMINISTRATOR') ? '<a class="nav-link" href="/main?command=procedures">All procedures</a>' :
                                        '<a class="nav-link" href="/main?command=procedures">My procedures</a>')}
                    </li>
                    <li class="nav-item dropdown">
                        ${user.role.name().equals('ADMINISTRATOR') || user.role.name().equals('MASTER') ? '<a class="nav-link disabled" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-disabled="true">' :
                                '<a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">'}
                        ${user.role.name().equals('GUEST') ?
                                'Procedures' :
                                'Book'}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="/main?command=ratings">By master rating</a></li>
                            <li><a class="dropdown-item" href="/main?command=regByMasterName">By master name</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/main?command=regByProcedure">By procedure</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        ${user.role.name().equals('GUEST') ?
                                '<a class="nav-link" href="/main?command=logOut">Create an account</a>' :
                                '<a class="nav-link" href="/main?command=logOut">Log out</a>'}
                    </li>
                    ${user.role.name().equals('ADMINISTRATOR') ?
                            ' <li class="nav-item"> <a class="nav-link" href="/main?command=adminMenu">Admin menu</a></li>'
                            : '' }
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<h1 class="text-center"> Please check the procedures <span class="badge bg-primary">New</span></h1>
<p style="color: red;" class="text-center">${requestScope.message}</p>
<div class="row">
    <div class="col">
        <h4 class="text-center">schedule for today</h4>
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">UniqID</th>
                <th scope="col">Client</th>
                <th scope="col">StartTime</th>
                <th scope="col">EndTime</th>
                <th scope="col">Confirmed</th>
                <th scope="col">Conduct</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="appointment1" items="${requestScope.toDayAppointments}">
                <c:if test="${appointment1.confirmed && appointment1.paidUp == true}">
                    <tr class="table-success">
                </c:if>
                <c:if test="${appointment1.confirmed == true && appointment1.paidUp == false}">
                    <tr class="table-danger">
                </c:if>
                <c:if test="${appointment1.confirmed == false}">
                    <tr class="table-primary">
                </c:if>
                <c:if test="${(appointment1.startTime.before(requestScope.timestamp)) == true}">
                    <tr class="table-info">
                </c:if>
                <td><c:out value="${appointment1.id}"/></td>
                <td><c:out value="${appointment1.clientLogin}"/></td>
                <td><c:out value="${appointment1.startTime.toLocalDateTime().toLocalTime().toString()}"/></td>
                <td><c:out value="${appointment1.startTime.toLocalDateTime().plusSeconds(appointment1.procedure.duration/1000).toLocalTime().toString()}"/></td>
                <td><c:out value="${appointment1.confirmed}"/></td>
                <td>
                    <form action="/main" method="post">
                        <input type="hidden" name="finished" value="${appointment1.id}">
                        <button type="submit" class="btn btn-danger" name="command" value="procedures"
                                <c:if test="${appointment1.confirmed == false || appointment1.finished == true}">disabled</c:if>>✓
                        </button>
                    </form>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <div class="col">
        <h4 class="text-center">future procedures</h4>
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">UniqID</th>
                <th scope="col">Procedure</th>
                <th scope="col">Date</th>
                <th scope="col">Duration</th>
                <th scope="col">Client</th>
                <th scope="col">Confirmed</th>
                <th scope="col">PaidUp</th>
                <th scope="col">Finished</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="appointment" items="${requestScope.appointments}">
                <c:if test="${appointment.confirmed && appointment.paidUp == true}">
                    <tr class="table-success">
                </c:if>
                <c:if test="${appointment.confirmed == true && appointment.paidUp == false}">
                    <tr class="table-danger">
                </c:if>
                <c:if test="${appointment.confirmed == false}">
                    <tr class="table-primary">
                </c:if>
                <c:if test="${(appointment.startTime.before(requestScope.timestamp)) == true}">
                    <tr class="table-info">
                </c:if>
                <td><c:out value="${appointment.id}"/></td>
                <td><c:out value="${appointment.procedure.name}"/></td>
                <td><c:out value="${appointment.startTime}"/></td>
                <td><c:out value="${appointment.procedure.duration}"/></td>
                <td><c:out value="${appointment.clientLogin}"/></td>
                <td><c:out value="${appointment.confirmed}"/></td>
                <td><c:out value="${appointment.paidUp}"/></td>
                <td><c:out value="${appointment.finished}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
