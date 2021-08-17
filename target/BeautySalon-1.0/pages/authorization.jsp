<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>
<div class="login-form" style="text-align: center;">
    <form action="/main" method="post">
        <h2 class="text-center">Log in</h2>
        <div class="form-group  w-25 p-3" style="margin: auto;">
            <input type="text" class="form-control" placeholder="Username" required="required" name="login" pattern="[a-zA-Z0-9]+" minlength="4" maxlength="8">
        </div>
        <div class="form-group  w-25 p-3" style="margin: auto;">
            <input type="password" class="form-control" placeholder="Password" required="required" name="password" pattern="[a-zA-Z0-9]+" minlength="8" maxlength="8">
        </div>
        <div class="form-group w-25 p-3" style="margin: auto;">
            <input type="hidden" name="command" value="auth">
            <button type="submit" class="btn btn-dark">Log in</button>
        </div>
    </form>
    <p style="color: red;" class="text-center">${requestScope.message}</p>
    <p class="text-center"><a href="/main?command=registration">Create an Account</a></p>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
</body>
</html>
