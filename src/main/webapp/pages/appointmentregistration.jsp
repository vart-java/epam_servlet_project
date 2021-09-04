<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Ratings</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/appointmentregistration.css" rel="stylesheet">
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
                            <li><a class="dropdown-item" href="/main?command=ratings"><fmt:message
                                    key="menu_by_master_rat"/></a></li>
                            <li><a class="dropdown-item" href="/main?command=regByMasterName"><fmt:message
                                    key="menu_by_master_name"/></a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/main?command=regByProcedure"><fmt:message
                                    key="menu_by_procedure"/></a></li>
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
                        <li class="nav-item"><a class="nav-link" href="/main?command=adminMenu"><fmt:message
                                key="menu_admin"/></a></li>
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

<h1 class="text-center">
    <c:if test="${user.role.name().equals('GUEST')}">
        <fmt:message key="procedure_procedure"/>
    </c:if>
    <c:if test="${user.role.name().equals('GUEST')}">
        <fmt:message key="registration_on_procedure"/>
    </c:if>
    <fmt:message key="${requestScope.master.specialization.name}"/>
    <fmt:message key="to_the"/>
    <span class="badge bg-primary"><c:out value="${requestScope.master.simpleName}"/></span></h1>
<div class="container">
    <div class="row align-content-center">
        <div class="col-lg-6">
            <table class="table table-striped table-hover">
                <h5 class="text-lg-start"><fmt:message key="schedule_this_day"/></h5>
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="procedure_uniqid"/></th>
                    <th scope="col"><fmt:message key="start_time"/></th>
                    <th scope="col"><fmt:message key="end_time"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${requestScope.appointments}">
                    <tr>
                        <td><c:out value="${appointment.id}"/></td>
                        <td><fmt:formatDate pattern="HH:mm"
                                            value="${appointment.startTime}"/></td>
                        <td><c:out
                                value="${appointment.startTime.toLocalDateTime().plusSeconds(appointment.procedure.duration/1000).toLocalTime().toString()}"/>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-lg-6">
            <h5 class="text-center"><fmt:message key="please_choose_time"/> <c:out
                    value="${requestScope.time}"/>.
                <fmt:message key="salon_is_open"/></h5>
            <h6 class="text-center"> <fmt:message key="you_cannot"/></h6>

            <div class="container in">
                <div class="row align-content-center">
                    <form action="/main" method="post">
                        <div class="col-xl-3">
                            <input type="time" class="form-control" placeholder="Start time" aria-label="Start time"
                                   min="11:00" max="18:01" name="newApp" value="11:00">
                        </div>
                        <div class="col-xl-3">
                            <button type="submit" class="btn btn-info" name="command" value="regToApp"
                                    <c:if test="${user.role.name().equals('GUEST')}">disabled</c:if>><fmt:message key="submit"/>
                            </button>
                        </div>
                        <input type="hidden" name="day" value=${requestScope.time}>
                        <input type="hidden" name="master" value=${requestScope.master.login}>
                        <input type="hidden" name="dateAppointment" value=${requestScope.wrongTime}>
                    </form>
                </div>
            </div>
        </div>

        <p style="color: red;" class="text-center">
            <c:if test="${null != requestScope.message}">
                <fmt:message key="${requestScope.message}"/>
            </c:if>
           </p>
    </div>
    <script defer src="/js/bootstrap.bundle.js"></script>
</div>
</body>
</html>
