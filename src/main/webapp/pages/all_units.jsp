<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ratings</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/allunits.css.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${loc}"/>
<fmt:setBundle basename="localization"/>

<header>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #ecc2ff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><fmt:message key="${user.getRole()}"/> : <c:out
                    value="${user.getSimpleName()}"/></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                    aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/main?command=main">| <fmt:message
                                key="menu_main"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=allUnits" }
                        ><fmt:message key="masters_menu"/></a>
                    </li>
                    <li class="nav-item">
                        <a ${user.role.name().equals('GUEST') ?
                                'class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true"' :
                                'class="nav-link" href="/main?command=procedures"'}
                        ><fmt:message key="menu_my_procedures"/></a>
                    </li>
                    <li class="nav-item">
                        <a ${!user.role.name().equals('CLIENT') ?
                                'class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true"' :
                                'class="nav-link" href="/main?command=ratings"'}
                        ><fmt:message key="menu_book"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=logOut">
                            <c:if test="${user.role.name().equals('GUEST')}">
                                <fmt:message key="menu_create_an_account"/>
                            </c:if>
                            <c:if test="${!user.role.name().equals('GUEST')}">
                                <fmt:message key="menu_log_out"/>
                            </c:if>
                        </a>
                    </li>
                    <c:if test="${user.role.name().equals('ADMIN')}">
                        <li class="nav-item"><a class="nav-link" href="/main?command=adminMenu"><fmt:message
                                key="menu_admin"/></a></li>
                    </c:if>
                </ul>
                <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                    <input type="radio" class="btn-check" name="btnradio" id="btnradio1"
                           autocomplete="off" ${loc.equals('en') ?
                            'Checked' :
                            '' }>
                    <label class="btn btn-outline-secondary" for="btnradio1">EN</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2"
                           autocomplete="off" ${loc.equals('ukr') ?
                            'Checked' :
                            '' }>
                    <label class="btn btn-outline-secondary" for="btnradio2">УКР</label>
                </div>
            </div>
        </div>
    </nav>
</header>

<div class="container tables">
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <h1 class="text-center"><fmt:message key="our_masters"/></h1>
            <table class="table align-middle">
                <thead>
                <tr>
                    <th scope="col" class="text-center">
                        <form action="/main" method="post">
                            <input type="hidden" name="sort" value="procedure">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <fmt:message key="procedure_procedure"/></button>
                        </form>
                    </th>
                    <th scope="col" class="text-center">
                        <form action="/main" method="post">
                            <input type="hidden" name="sort" value="master">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <fmt:message key="MASTER"/></button>
                        </form>
                    </th>
                    <th scope="col" class="text-center">
                        <form action="/main" method="post">
                            <input type="hidden" name="sort" value="rating">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <fmt:message key="rating"/></button>
                        </form>
                        </a>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="entity" items="${requestScope.masters_entity}">
                    <td>
                        <form action="/main" method="post" class="text-center">
                            <input type="hidden" name="userFilter" value="${entity.procedureName}">
                            <input type="hidden" name="filterId" value="1">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <fmt:message key="${entity.procedureName}"/></button>
                        </form>
                    </td>
                    <td>
                        <form action="/main" method="post" class="text-center">
                            <input type="hidden" name="userFilter" value="${entity.masterName}">
                            <input type="hidden" name="filterId" value="2">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <c:out value="${entity.masterName}"/></button>
                        </form>
                    </td>
                    <td>
                        <form action="/main" method="post" class="text-center">
                            <input type="hidden" name="userFilter" value="${entity.masterRating}">
                            <input type="hidden" name="filterId" value="3">
                            <button type="submit" class="btn btn-light" name="command" value="allUnits">
                                <c:out value="${entity.masterRating}"/></button>
                        </form>
                    </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="/js/bootstrap.bundle.js"></script>
<script src="/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
    $('#btnradio2').click(function () {
        var x = new XMLHttpRequest();
        x.open("GET", "/main?command=locale&language=ukr", true);
        x.onload = function () {
        }
        x.send(null);
        location.reload();
    });
    $('#btnradio1').click(function () {
        var x = new XMLHttpRequest();
        x.open("GET", "/main?command=locale&language=en", true);
        x.onload = function () {
        }
        x.send(null);
        location.reload();
    });
</script>
</body>
</html>
