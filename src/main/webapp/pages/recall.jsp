<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Review</title>
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/recall.css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #ecc2ff;">
        <div class="container-fluid">
        </div>
    </nav>
</header>

<h1 class="text-center"> Rate our master <c:out
        value="${requestScope.appointment.masterLogin.toString().substring(0, requestScope.appointment.masterLogin.toString().indexOf('@'))}"/>
    for the procedure <c:out value="${requestScope.appointment.procedure.name}"/> <span
            class="badge bg-primary">Please</span></h1>
<div class="container tables">
    <div class="row">
        <div class="col-lg-2">
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
                <input type="hidden" name="id" value=${requestScope.appointment.id}>
                <button type="submit" class="btn btn-primary ${requestScope.appointment.rated ?
                        'disabled' :
                        ''}" name="command" value="getRecall">submit
                </button>
            </form>
        </div>
    </div>
</div>

<script defer src="/js/bootstrap.bundle.js"></script>
<script defer src="/js/jquery.js"></script>
</body>
</html>
