<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.84.0">
    <title>Beauty saloon</title>



    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/css/bootstrap.css">

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
    <link href="/css/carousel.css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" style="background-color: #ecc2ff;">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><c:out value="${user.getRole()}"/> : <c:out value="${user.getSimpleName()}"/></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/main?command=main">| Main</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=procedures">My procedures</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarScrollingDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Registration
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarScrollingDropdown">
                            <li><a class="dropdown-item" href="/main?command=ratings">By master rating</a></li>
                            <li><a class="dropdown-item" href="#">By master name</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="#">By procedure</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/main?command=logOut">Log out</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Coming soon</a>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<main>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="3" aria-label="Slide 4"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/images/main_hair_coloring.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption text-start">
                        <h1>Hair coloring</h1>
                        <p>Best hair coloring in Eastern Europe</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Sign up now</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_face_massage.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Face massage</h1>
                        <p>Best face massage in Eastern Europe</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Sign up now</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_eyelash_extension.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Eyelash extension</h1>
                        <p>Best eyelash extension in Eastern Europe</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Sign up now</a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_nail_coloring.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption text-end">
                        <h1>Nail staining</h1>
                        <p>Best nail staining in Eastern Europe</p>
                        <p><a class="btn btn-lg btn-primary" href="#">Sign up now</a></p>
                    </div>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-lg-4">
                <img src="/images/main_master_1.jpg" class="img-fluid">
               <h2>The best nail master</h2>
                <p>It is very difficult to make an appointment with him for the procedure. Hurry up!</p>
                <p><a class="btn btn-secondary" href="#">View schedule &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="/images/main_master_2.jpg" class="img-fluid">
                <h2>The best hair master</h2>
                <p>It is very difficult to make an appointment with him for the procedure. Hurry up!</p>
                <p><a class="btn btn-secondary" href="#">View schedule &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="/images/main_master_3.jpg" class="img-fluid">
                <h2>The best eyelash master</h2>
                <p>It is very difficult to make an appointment with him for the procedure. Hurry up!</p>
                <p><a class="btn btn-secondary" href="#">View schedule &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->

    <!-- FOOTER -->
    <footer class="container">
        <p class="float-end"><a href="#">Back to top</a></p>
        <p>&copy; 2017–2021 Epam, Inc. &middot; <a href="https://careers.epam.ua/">All rights not reserved </a> &middot; <a href="#">Terms</a></p>
    </footer>
</main>
<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
