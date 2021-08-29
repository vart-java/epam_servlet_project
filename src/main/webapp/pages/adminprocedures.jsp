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
<table class="table table-striped table-hover table-bordered">
    <thead>
    <tr>
        <th scope="col">UniqID</th>
        <th scope="col">Procedure</th>
        <th scope="col">Date</th>
        <th scope="col">Set another time</th>
        <th scope="col">Duration</th>
        <th scope="col">Master</th>
        <th scope="col">Confirmed</th>
        <th scope="col">PaidUp</th>
        <th scope="col">Finished</th>
        <th scope="col">Confirm</th>
        <th scope="col">Get Paid</th>
        <th scope="col">Delete</th>
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
        <td>
            <form action="/main" method="post">
                <div class="row">
                    <div class="col">
                        <input type="time" class="form-control" placeholder="Start time" aria-label="Start time"
                               min="11:00" max="18:01" name="newApp" value="11:00">
                    </div>
                    <div class="col">
                        <input type="hidden" name="id" value="${appointment.id}">
                        <button type="submit" class="btn btn-info" name="command" value="procedures">🗸
                        </button>
                    </div>
                </div>
            </form>
        </td>
        <td><c:out value="${appointment.procedure.duration}"/></td>
        <td><c:out value="${appointment.masterLogin}"/></td>
        <td><c:out value="${appointment.confirmed}"/></td>
        <td><c:out value="${appointment.paidUp}"/></td>
        <td><c:out value="${appointment.finished}"/></td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="confirm" value="${appointment.id}">
                <button type="submit" class="btn btn-success" name="command" value="procedures">Confirm</button>
            </form>
        </td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="getPaid" value="${appointment.id}">
                <button type="submit" class="btn btn-warning" name="command" value="procedures">Get paid</button>
            </form>
        </td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="delete" value="${appointment.id}">
                <button type="submit" class="btn btn-danger" name="command" value="procedures">X</button>
            </form>
        </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
