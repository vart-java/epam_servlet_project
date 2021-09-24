<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
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
                    <input type="radio" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" ${loc.equals('en') ?
                            'Checked' :
                            '' }>
                    <label class="btn btn-outline-secondary" for="btnradio1" >EN</label>

                    <input type="radio" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off" ${loc.equals('ukr') ?
                            'Checked' :
                            '' }>
                    <label class="btn btn-outline-secondary" for="btnradio2">УКР</label>
                </div>
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
    <fmt:message key="${requestScope.procedure.name}"/>
    <fmt:message key="to_the"/>
    <span class="badge bg-primary"><c:out value="${requestScope.master.name}"/></span></h1>
<div class="container">
    <div class="row align-content-center">
        <div class="col-lg-6">
            <table class="table table-striped table-hover">
                <caption></caption>
                <h5 class="text-lg-start"><fmt:message key="schedule_this_day"/></h5>
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="procedure_uniqid"/></th>
                    <th scope="col"><fmt:message key="start_time"/></th>
                    <th scope="col"><fmt:message key="end_time"/></th>
                    <th scope="col"><fmt:message key="status"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="appointment" items="${requestScope.masterApp}">
                    <tr>
                        <td><c:out value="${appointment.appointmentId}"/></td>
                        <td><c:out value="${appointment.startDateTime}"/></td>
                        <td><c:out value="${appointment.endDateTime}"/></td>
                        <td><fmt:message key="${appointment.status}"/></td>
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
                        <input type="hidden" name="masterId" value=${requestScope.master.id}>
                        <input type="hidden" name="procedureId" value=${requestScope.procedure.id}>
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
</div>
<script src="/js/bootstrap.bundle.js"></script>
<script src="/js/jquery-3.6.0.js"></script>
<script type="text/javascript">
    $('#btnradio2').click(function() {
        var x = new XMLHttpRequest();
        x.open("GET", "/main?command=locale&language=ukr", true);
        x.onload = function (){
        }
        x.send(null);
        location.reload();
    });
    $('#btnradio1').click(function() {
        var x = new XMLHttpRequest();
        x.open("GET", "/main?command=locale&language=en", true);
        x.onload = function (){
        }
        x.send(null);
        location.reload();
    });
</script>
</body>
</html>
