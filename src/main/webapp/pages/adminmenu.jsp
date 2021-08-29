<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Procedures</title>
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

<h1 class="text-center"> Users <span class="badge bg-primary">Role changer</span></h1>
<p style="color: red;" class="text-center">${requestScope.message}</p>
<div class="container tables">
    <div class="row">
        <c:forEach var="UserList" items="${requestScope.ratings}">
            <div class="col-lg-3">
                <table class="table align-middle">
                    <h4 class="text-center"> ${UserList.isEmpty() ? ''
                            : UserList.get(0).role.toString() } </h4>
                    <thead>
                    <tr>
                        <th scope="col" class="text-center">Login</th>
                        <th scope="col" class="text-center">Сhange Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user1" items="${UserList}">
                        <tr class="table table-borderless">
                            <td class="text-center align-middle"><c:out value="${user1.login}"/></td>
                            <td>
                                <form action="/main" method="post">
                                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                                            name="role">
                                        <c:forEach var="role" items="${requestScope.roles}">
                                            <option value=${role.toString()}><c:out
                                                    value="${role.toString()}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="login" value="${user1.login}">
                                    <button type="submit" class="btn btn-primary" name="command" value="adminMenu">
                                        submit
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>

<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
