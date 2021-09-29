<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ratings</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/myratings.css" rel="stylesheet">
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

<p>_</p>
<p>.</p>
<h1 class="text-center"> Rate our master <c:out
        value="${requestScope.recallModel.masterName}"/>
    for the procedure <c:out value="${requestScope.recallModel.procedureName}"/> <span
            class="badge bg-primary">Please</span></h1>
<div class="container tables">
    <div class="row">
        <div class="col-lg-1">
            <form action="/main" method="post">
                <select class="form-select form-select-lg mb-3" aria-label=".form-select-lg example"
                        name="recall_rating">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
                <button type="submit" class="btn btn-primary ${requestScope.recallDto.status.name().equals('RATED') ?
                        'disabled' :
                        ''}" name="command" value="getRecall">submit
                </button>
                <input type="hidden" name="id" value=${requestScope.recallDto.appointmentId}>
            </form>
        </div>
        <div class="col-lg-8">

        </div>
        <div class="col-lg-3">
            <mytag:table cell1="${requestScope.recallDto.procedureName}" cell2="${requestScope.recallDto.masterName}"/>
        </div>
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
