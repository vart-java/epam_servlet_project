<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Auth</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>
<%--<div class="text-center w-25">--%>
<%--<form method="post" action="/main">--%>
    <%--<input type="hidden" name="command" value ="registration"/>--%>
    <%--<div class="form-control">--%>
    <%--<input type="text" class="text-input table-cell" name="username">--%>
    <%--</div>--%>
    <%--<div class="form-control">--%>
    <%--<input type="password" class="text-input table-cell" name="password">--%>
    <%--</div>--%>
    <%--<div class="form-control">--%>
    <%--<button class="btn btn-light">register</button>--%>
    <%--</div>--%>
    <%--<div class="form-control">--%>
    <%--<p style="color: red;">${requestScope.message}</p>--%>
    <%--</div>--%>
<%--</form>--%>
<%--</div>--%>
<div class="login-form" style="text-align: center;">
    <form action="/main" method="post">
        <input type="hidden" name="command" value ="registration"/>
        <h2 class="text-center">Registration</h2>
        <div class="form-group  w-25 p-3" style="margin: auto;">
            <input type="text" class="form-control" placeholder="Username" required="required" name="username">
        </div>
        <div class="form-group  w-25 p-3" style="margin: auto;">
            <input type="password" class="form-control" placeholder="Password" required="required" name="password">
        </div>
        <div class="form-group w-25 p-3" style="margin: auto;">
            <button type="submit" class="btn btn-light">Register</button>
        </div>
    </form>
    <p style="color: red;" class="text-center">${requestScope.message}</p>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
        integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"
        integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm"
        crossorigin="anonymous"></script>
</body>
</html>
