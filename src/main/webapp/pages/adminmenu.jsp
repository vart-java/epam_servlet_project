<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Procedures</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/myratings.css" rel="stylesheet">
</head>
<body>
<fmt:setLocale value="${loc}"/>
<fmt:setBundle basename="localization"/>

<header>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #ecc2ff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <fmt:message key="${user.getRole()}"/> : <c:out
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
                            ''} disabled>EN</label>
                    <label><input type="radio" name="language" value="ukr"  ${loc.equals('ukr') ?
                            'Checked' :
                            ''} disabled>UKR</label>
                </form>
            </div>
        </div>
    </nav>
</header>

<h1 class="text-center"><fmt:message key="admin_users"/> <span class="badge bg-primary"><fmt:message
        key="admin_role_changer"/></span></h1>
<p style="color: red;" class="text-center">${requestScope.message}</p>
<div class="container tables">
    <div class="row">
        <c:forEach var="UserList" items="${requestScope.ratings}">
            <div class="col-lg-6">
                <table class="table align-middle">
                    <caption></caption>
                    <h4 class="text-center">
                        <c:if test="${UserList.isEmpty()}">
                        </c:if>
                        <c:if test="${!UserList.isEmpty()}">
                            <fmt:message key="${UserList.get(0).role.toString()}"/>
                        </c:if>
                    </h4>
                    <thead>
                    <tr>
                        <th scope="col" class="text-center"><fmt:message key="admin_login"/></th>
                        <th scope="col" class="text-center"><fmt:message key="admin_change"/></th>
                        <c:if test="${!UserList.isEmpty() && UserList.get(0).role.toString().equals('MASTER')}">
                            <th scope="col" class="text-center"><fmt:message key="admin_change_spec"/></th>
                        </c:if>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user1" items="${UserList}">
                        <tr class="table table-borderless">
                            <td class="text-center align-middle"><c:out value="${user1.login}"/></td>
                            <td>
                                <form action="/main" method="post">
                                    <div class="table">
                                        <div class="row row-cols-2">
                                            <div class="col-lg-8">
                                                <select class="form-select form-select-lg mb-3"
                                                        aria-label=".form-select-lg example"
                                                        name="role">
                                                    <c:forEach var="role" items="${requestScope.roles}">
                                                        <option value=${role.toString()}><fmt:message
                                                                key="${role.toString()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col-lg-4">
                                                <input type="hidden" name="login" value="${user1.login}">
                                                <button type="submit" class="btn btn-primary" name="command"
                                                        value="adminMenu">
                                                    ✓
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </td>
                            <c:if test="${!UserList.isEmpty() && UserList.get(0).role.toString().equals('MASTER')}">
                                <td>
                                    <form action="/main" method="post">
                                        <div class="table">
                                            <div class="row row-cols-2">
                                                <div class="col-lg-8">
                                                    <select class="form-select form-select-lg mb-3"
                                                            aria-label=".form-select-lg example"
                                                            name="specialization">
                                                        <c:forEach var="spec" items="${requestScope.procedures}">
                                                            <option value=${spec.name}><fmt:message
                                                                    key="${spec.name}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4">
                                                    <input type="hidden" name="login" value="${user1.login}">
                                                    <button type="submit" class="btn btn-primary" name="command"
                                                            value="adminMenu">
                                                        ✓
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </td>
                            </c:if>
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
