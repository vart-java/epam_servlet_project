<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ratings</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/appointmentregistration.css" rel="stylesheet">
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
                            <li><a class="dropdown-item" href="/main?command=ratings">By master rating</a></li>
                            <li><a class="dropdown-item" href="/main?command=regByMasterName">By master name</a></li>
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

<h1 class="text-center"> Registration on procedure <c:out value="${requestScope.master.specialization.name}"/> to the
    <span
            class="badge bg-primary"><c:out value="${requestScope.master.simpleName}"/></span></h1>
<div class="container">
    <div class="row align-content-center">
        <div class="col-lg-6">
            <table class="table table-striped table-hover">
                <h5 class="text-lg-start">Master's schedule for the day of your choice</h5>
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Start time</th>
                    <th scope="col">End time</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${requestScope.appointments}">
                    <tr>
                        <td><c:out value="${appointment.id}"/></td>
                        <td><c:out value="${appointment.startTime.toLocalDateTime().toLocalTime().toString()}"/></td>
                        <td><c:out
                                value="${appointment.startTime.toLocalDateTime().plusSeconds(appointment.procedure.duration/1000).toLocalTime().toString()}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-lg-6">
            <h5 class="text-center">Please choose the time you need for the procedure on <c:out
                    value="${requestScope.time}"/>.
                The
                salon is open this day from 11:00 to 20:00</h5>
            <h6 class="text-center"> You cannot make an appointment when the master is busy</h6>

            <div class="container in">
                <div class="row align-content-center">
                    <form action="/main" method="post">
                        <div class="col-xl-3">
                            <input type="time" class="form-control" placeholder="Start time" aria-label="Start time"
                                   min="11:00" max="18:01" name="newApp" value="11:00">
                        </div>
                        <div class="col-xl-3">
                            <button type="submit" class="btn btn-info" name="command" value="regToApp">Submit</button>
                        </div>
                        <input type="hidden" name="day" value=${requestScope.time}>
                        <input type="hidden" name="master" value=${requestScope.master.login}>
                        <input type="hidden" name="dateAppointment" value=${requestScope.wrongTime}>
                    </form>
                </div>
            </div>
        </div>
        <p style="color: red;" class="text-center">${requestScope.message}</p>
    </div>
    <script defer src="/js/bootstrap.bundle.js"></script>
</div>
</body>
</html>
