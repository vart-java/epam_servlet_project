<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ratings</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/myratings.css" rel="stylesheet">
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

<h1 class="text-center"> Rating of our masters <span
        class="badge bg-primary">Click on masters name for registration</span></h1>
<div class="container tables">
    <div class="row">
        <c:forEach var="UserList" items="${requestScope.ratings}">
            <div class="col-lg-3">
                <table class="table align-middle">
                    <h4 class="text-center"><c:out
                            value="${UserList.get(0).specialization.name.toUpperCase()}"/></h4>
                    <thead>
                    <tr>
                        <th scope="col" class="text-center">Name</th>
                        <th scope="col" class="text-center">Rating</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${UserList}">
                        <tr class="table table-borderless">
                            <td>
                                <button type="button" class="btn btn-primary dropdown-toggle"
                                        data-bs-toggle="dropdown">
                                    <c:out value="${user.simpleName}"/>
                                </button>
                                <div class="dropdown-menu p-2 shadow rounded-3" style="width: 300px"
                                     id="dropdownCalendar">
                                    <form action="/main" method="post">
                                    <input type="date" value=${requestScope.dateNow1}
                                           min=${requestScope.dateNow1} max=${requestScope.dateNow7} name="dateAppointment">
                                        <input type="hidden" name="master" value="${user.login}">
                                    <button type="submit" class="btn btn-primary" name="command" value="regToApp">submit</button>
                                    </form>
                                </div>
                            </td>
                            <td class="text-center align-middle"><c:out value="${user.rating}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>


<script defer src="/js/bootstrap.bundle.js"></script>
<script defer src="/js/jquery.js"></script>
</body>
</html>
