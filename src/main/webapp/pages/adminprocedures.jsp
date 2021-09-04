<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Procedures</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/myprocedures.css" rel="stylesheet">
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
                        <a class="nav-link active" aria-current="page" href="/main?command=main">| <fmt:message key="menu_main"/></a>
                    </li>
                    <li class="nav-item">
                        <a ${user.role.name().equals('GUEST') ?
                                'class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true"' :
                                'class="nav-link" href="/main?command=procedures"'}
                        ><fmt:message key="menu_my_procedures"/></a>
                    </li>
                    <li class="nav-item dropdown">
                        ${user.role.name().equals('ADMINISTRATOR') || user.role.name().equals('MASTER') ? '<a class="nav-link disabled" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-disabled="true">' :
                                '<a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">'}
                        <c:if test="${user.role.name().equals('GUEST')}">
                            <fmt:message key="menu_procedures"/>
                        </c:if>
                        <c:if test="${!user.role.name().equals('GUEST')}">
                            <fmt:message key="menu_book"/>
                        </c:if>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="/main?command=ratings"><fmt:message key="menu_by_master_rat"/></a></li>
                            <li><a class="dropdown-item" href="/main?command=regByMasterName"><fmt:message key="menu_by_master_name"/></a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/main?command=regByProcedure"><fmt:message key="menu_by_procedure"/></a></li>
                        </ul>
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
                    <c:if test="${user.role.name().equals('ADMINISTRATOR')}">
                        <li class="nav-item"> <a class="nav-link" href="/main?command=adminMenu"><fmt:message key="menu_admin"/></a></li>
                    </c:if>
                </ul>
                <form action="/main" method="post">
                    <label><input type="radio" name="language" value="en"  ${loc.equals('en') ?
                            'Checked' :
                            ''}>EN</label>
                    <label><input type="radio" name="language" value="ukr"  ${loc.equals('ukr') ?
                            'Checked' :
                            ''}>UKR</label>
                    <button type="submit" class="btn btn-sm btn-secondary" name="command" value="locale">✓</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<h1 class="text-center"> <fmt:message key="procedure_check_please"/> <span class="badge bg-primary"><fmt:message key="procedure_new"/></span></h1>
<p style="color: red;" class="text-center">${requestScope.message}</p>
<table class="table table-striped table-hover table-bordered">
    <caption></caption>
    <thead>
    <tr>
        <th scope="col"><fmt:message key="procedure_uniqid"/></th>
        <th scope="col"><fmt:message key="procedure_procedure"/></th>
        <th scope="col"><fmt:message key="procedure_date"/></th>
        <th scope="col"><fmt:message key="procedure_set_time"/></th>
        <th scope="col"><fmt:message key="procedure_duration"/></th>
        <th scope="col"><fmt:message key="MASTER"/></th>
        <th scope="col"><fmt:message key="procedure_confirmed"/></th>
        <th scope="col"><fmt:message key="procedure_paidup"/></th>
        <th scope="col"><fmt:message key="procedure_finished"/></th>
        <th scope="col"><fmt:message key="procedure_confirm"/></th>
        <th scope="col"><fmt:message key="procedure_getpaid"/></th>
        <th scope="col"><fmt:message key="procedure_delete"/></th>
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
        <td><fmt:message key="${appointment.procedure.name}"/></td>
        <td><fmt:formatDate pattern="HH:mm  |  dd.MM.yyyy"
                            value="${appointment.startTime}" /></td>
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
        <td><c:out value="${appointment.procedure.duration/3600000}"/></td>
        <td><c:out value="${appointment.masterLogin.substring(0, appointment.masterLogin.indexOf('@'))}"/></td>
        <td><fmt:message key="${appointment.confirmed}"/></td>
        <td><fmt:message key="${appointment.paidUp}"/></td>
        <td><fmt:message key="${appointment.finished}"/></td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="confirm" value="${appointment.id}">
                <button type="submit" class="btn btn-success" name="command" value="procedures"><fmt:message key="procedure_confirm"/></button>
            </form>
        </td>
        <td>
            <form action="/main" method="post">
                <input type="hidden" name="getPaid" value="${appointment.id}">
                <button type="submit" class="btn btn-warning" name="command" value="procedures"><fmt:message key="procedure_getpaid"/></button>
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
