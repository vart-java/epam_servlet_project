<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                        <a class="nav-link active" aria-current="page" href="/main?command=main">| <fmt:message key="menu_main"/></a>
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
                            <li><a class="dropdown-item" href="/main?command=ratings"><fmt:message key="menu_by_master_rat"/></a></li>
                            <li><a class="dropdown-item" href="/main?command=regByMasterName"><fmt:message key="menu_by_master_name"/></a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="/main?command=regByProcedure"><fmt:message key="menu_by_procedure"/></a></li>
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
                        <li class="nav-item"> <a class="nav-link" href="/main?command=adminMenu"><fmt:message key="menu_admin"/></a></li>
                    </c:if>
                </ul>
                <form action="/main" method="post">
                    <label><input type="radio" name="language" value="en"  ${loc.equals('en') ?
                            'Checked' :
                            ''}>EN</label>
                    <label><input type="radio" name="language" value="ukr"  ${loc.equals('ukr') ?
                            'Checked' :
                            ''}>UKR</label>
                    <button type="submit" class="btn btn-sm btn-secondary" name="command" value="locale">✓</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<main>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true"
                    aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
            <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="3" aria-label="Slide 4"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="/images/main_hair_coloring.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption text-start">
                        <h1><fmt:message key="hair_coloring"/></h1>
                        <p><fmt:message key="main_hair_coloring_desc"/></p>
                        <p><a class="btn btn-lg btn-primary" href=
                        <c:if test="${!user.role.name().equals('CLIENT')}">
                           #
                        </c:if>
                        <c:if test="${user.role.name().equals('CLIENT')}">
                            "http://localhost:8080/main?command=regByProcedure"
                        </c:if>
                        ><fmt:message key="main_sign_up_now"/></a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_face_massage.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption">
                        <h1><fmt:message key="face_massage"/></h1>
                        <p><fmt:message key="main_face_massage_desc"/></p>
                        <p><a class="btn btn-lg btn-primary" href=
                        <c:if test="${!user.role.name().equals('CLIENT')}">
                                #
                            </c:if>
                            <c:if test="${user.role.name().equals('CLIENT')}">
                                "http://localhost:8080/main?command=regByProcedure"
                            </c:if>
                        ><fmt:message key="main_sign_up_now"/></a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_eyelash_extension.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption">
                        <h1><fmt:message key="eyelash_extension"/></h1>
                        <p><fmt:message key="main_eyelash_extension_desc"/></p>
                        <p><a class="btn btn-lg btn-primary" href=
                        <c:if test="${!user.role.name().equals('CLIENT')}">
                                #
                            </c:if>
                            <c:if test="${user.role.name().equals('CLIENT')}">
                                "http://localhost:8080/main?command=regByProcedure"
                            </c:if>
                        ><fmt:message key="main_sign_up_now"/></a></p>
                    </div>
                </div>
            </div>
            <div class="carousel-item">
                <img src="/images/main_nail_coloring.jpg" class="img-fluid">
                <div class="container">
                    <div class="carousel-caption text-end">
                        <h1><fmt:message key="nail_staining"/></h1>
                        <p><fmt:message key="main_nail_staining_desc"/></p>
                        <p><a class="btn btn-lg btn-primary" href=
                        <c:if test="${!user.role.name().equals('CLIENT')}">
                                #
                            </c:if>
                            <c:if test="${user.role.name().equals('CLIENT')}">
                                "http://localhost:8080/main?command=regByProcedure"
                            </c:if>
                        ><fmt:message key="main_sign_up_now"/></a></p>
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
                <h2><fmt:message key="main_best_master"/></h2>
                <p><fmt:message key="main_difficult_appoint"/></p>
                <p><a class="btn btn-secondary" href=
                <c:if test="${!user.role.name().equals('CLIENT')}">
                        #
                    </c:if>
                    <c:if test="${user.role.name().equals('CLIENT')}">
                        "http://localhost:8080/main?command=ratings"
                    </c:if>
                ><fmt:message key="main_view_schedule"/> &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="/images/main_master_2.jpg" class="img-fluid">
                <h2><fmt:message key="main_best_master"/></h2>
                <p><fmt:message key="main_difficult_appoint"/></p>
                <p><a class="btn btn-secondary" href=
                <c:if test="${!user.role.name().equals('CLIENT')}">
                        #
                    </c:if>
                    <c:if test="${user.role.name().equals('CLIENT')}">
                        "http://localhost:8080/main?command=ratings"
                    </c:if>
                ><fmt:message key="main_view_schedule"/> &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="/images/main_master_3.jpg" class="img-fluid">
                <h2><fmt:message key="main_best_master"/></h2>
                <p><fmt:message key="main_difficult_appoint"/></p>
                <p><a class="btn btn-secondary" href=
                <c:if test="${!user.role.name().equals('CLIENT')}">
                        #
                    </c:if>
                    <c:if test="${user.role.name().equals('CLIENT')}">
                        "http://localhost:8080/main?command=ratings"
                    </c:if>
                ><fmt:message key="main_view_schedule"/> &raquo;</a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->

        <!-- FOOTER -->
        <footer class="container">
            <p class="float-end"><a href="#"><fmt:message key="main_back_top"/></a></p>
            <p>&copy; 2017–2021 <fmt:message key="epam"/> &middot; <a href="https://careers.epam.ua/"><fmt:message key="main_rights"/> </a>
                &middot; <a href="https://zakon.rada.gov.ua/laws/show/254%D0%BA/96-%D0%B2%D1%80#Text"><fmt:message key="main_terms"/></a></p>
        </footer>
</main>
<script defer src="/js/bootstrap.bundle.js"></script>
</body>
</html>
