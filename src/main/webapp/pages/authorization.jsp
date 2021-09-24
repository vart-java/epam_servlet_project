<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Signin Beauty Saloon</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">
</head>
<body class="text-center">
<fmt:setLocale value="${loc}"/>
<fmt:setBundle basename="localization"/>
<main class="form-signin">
    <form action="/main" method="post">
        <img class="mb-4" src="/images/authorization_logo-removebg-preview.png" alt="" width="220" height="220">
        <h1 class="h3 mb-3 fw-normal"><fmt:message key="sign_in_please"/>
        </h1>

        <div class="form-floating">
            <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="username"
                   minlength="6" maxlength="24">
            <label for="floatingInput">Email address</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password"
                   pattern="[a-zA-Zа-яА-ЯіІїЇєЄ0-9]+" minlength="5" maxlength="8">
            <label for="floatingPassword">Password</label>
        </div>
        <button class="w-100 btn btn-lg btn-primary" type="submit" name="command" value="auth">
            <fmt:message key="sign_in"/></button>
        <p style="color: red;" class="text-center"></p>
        <button class="w-100 btn btn-lg btn-primary" type="submit" name="command" value="registration">
            <fmt:message key="create_acc"/></button>
        <p style="color: red;" class="text-center">
            <c:if test="${null != requestScope.message}">
                <fmt:message key="${requestScope.message}"/>
            </c:if>
        </p>
        <button class="w-100 btn btn-lg btn-secondary" name="command" value="authAsGuest">
            <fmt:message key="as_guest"/></button>
    </form>
    <form class="text-center" action="/main" method="post">
        <label><input type="radio" name="language" value="en"  ${loc.equals('en') ?
                'Checked' :
                ''}>EN</label>
        <label><input type="radio" name="language" value="ukr"  ${loc.equals('ukr') ?
                'Checked' :
                ''}>UKR</label>
        <button type="submit" class="btn btn-sm btn-secondary" name="command" value="locale">✓</button>
    </form>
    <p class="mt-5 mb-3 text-muted">&copy; 2001–2021</p>
</main>
</body>
</html>
