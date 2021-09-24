<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Procedures</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
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

<h1 class="text-center"><fmt:message key="admin_users"/> <span class="badge bg-primary"><fmt:message
        key="admin_role_changer"/></span></h1>
<p style="color: red;" class="text-center">${requestScope.message}</p>
<table class="table table-hover table-bordered align-content-center text-center">
    <caption></caption>
    <thead>
    <tr>
        <th scope="col"><fmt:message key="procedure_uniqid"/></th>
        <th scope="col"><fmt:message key="admin_login"/></th>
        <th scope="col"><fmt:message key="role"/></th>
        <th scope="col"><fmt:message key="admin_change"/></th>
        <th scope="col"><fmt:message key="specialization"/></th>
        <th scope="col"><fmt:message key="rating"/></th>
        <th scope="col"><fmt:message key="recall_count"/></th>
        <th scope="col"><fmt:message key="procedure_delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.users}">
    <c:if test="${user.role.name().equals('CLIENT')}">
    <tr class="table-success">
        </c:if>
        <c:if test="${user.role.name().equals('MASTER')}">
    <tr class="table-primary">
        </c:if>
        <c:if test="${user.role.name().equals('ADMIN')}">
    <tr class="table-danger">
        </c:if>
        <c:if test="${user.role.name().equals('GUEST')}">
    <tr class="table-secondary">
        </c:if>
        <td><c:out value="${user.id}"/></td>
        <td><c:out value="${user.login}"/></td>
        <td><fmt:message key="${user.role}"/></td>
        <td>
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                <fmt:message key="${user.role}"/>
            </button>
            <div class="dropdown-menu p-2 shadow rounded-3" style="width: 300px">
                <form action="/main" method="post">
                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example" name="roleCh">
                        <c:forEach var="role" items="${requestScope.roles}">
                            <option value=${role.ordinal()}><fmt:message key="${role.name()}"/></option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="id" value="${user.id}">
                    <button type="submit" class="btn btn-primary" name="command" value="adminMenu"><fmt:message
                            key="submit"/></button>
                </form>
            </div>
        </td>
        <td>
            <table class="table align-content-center">
                <c:forEach var="skill" items="${user.skills}">
                    <tr>
                        <td><fmt:message key="${skill.name}"/></td>
                        <td>
                            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                                +
                            </button>
                            <div class="dropdown-menu p-2 shadow rounded-3" style="width: 300px">
                                <form action="/main" method="post">
                                    <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                                            name="skillCh">
                                        <c:forEach var="procedure" items="${requestScope.procedures}">
                                            <option value=${procedure.id}><fmt:message
                                                    key="${procedure.name}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="idAddSkill" value="${user.id}">
                                    <button type="submit" class="btn btn-primary" name="command" value="adminMenu">
                                        <fmt:message
                                                key="submit"/></button>
                                </form>
                            </div>
                        </td>
                        <td>
                            <form action="/main" method="post">
                                <input type="hidden" name="userIdDeleteSkill" value="${user.id}">
                                <input type="hidden" name="skillIdDeleteSkill" value="${skill.id}">
                                <button type="submit" class="btn btn-danger" name="command" value="adminMenu">X</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td><c:out value="${user.rating}"/></td>
        <td><c:out value="${user.recallCount}"/></td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="delete" value="${user.id}">
                <button type="submit" class="btn btn-danger" name="command" value="adminMenu">X</button>
            </form>
        </td>
        </c:forEach>
    </tbody>
</table>

<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
<c:forEach var="page" items="${requestScope.paginationNumbers}">
        <li class="page-item"><a class="page-link" href="http://localhost:8080/main?command=adminMenu&pageId=${page}"><c:out value="${page}"/></a></li>
</c:forEach>
    </ul>
</nav>

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
