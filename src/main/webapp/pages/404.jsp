<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>My Procedures</title>
  <link href="/css/bootstrap.css" rel="stylesheet">
  <link href="/css/404.css" rel="stylesheet">
</head>
<body>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <div class="error-template">
        <h1>
          Oops!</h1>
        <h2>
          404 Not Found</h2>
        <div class="error-details">
          Sorry, an error has occured, Requested page not found!
        </div>
        <div class="error-actions">
          <a href="http://localhost:8080/main?command=main" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
            Take Me Home </a><a href="https://www.epam.com/about/who-we-are/contact" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Contact Support </a>
        </div>
      </div>
    </div>
  </div>
</div>

<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
