<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css"
          integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body class="hgu">
<ul class="nav justify-content-center navbar-light bg-light" style="margin-bottom: 10px;">
    <li class="btn btn-sm btn-light">
        <a class="nav-link" href="/main?command=main">Home</a>
    </li>
    <li class="btn btn-sm btn-light">
        <a class="nav-link" href="/main?command=upcoming">Upcoming confs</a>
    </li>
    <li class="btn btn-sm btn-light">
        <a class="nav-link" href="/main?command=past">Past confs</a>
    </li>
    <li class="btn btn-sm btn-light">
        <a class="nav-link" href="/main?command=myconfs">My confs</a>
    </li>
    <li class="btn btn-light btn-sm">
        <a class="nav-link" href="/main?command=ratings">Ratings</a>
    </li>
    <li class="btn justify-content-left btn-light btn-sm">
        <a class="nav-link" href="/main?command=logOut">LogOut</a>
    </li>
</ul>
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
