<%--
  Created by IntelliJ IDEA.
  User: Oliver
  Date: 17/04/2020
  Time: 16.35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html lang="en"><head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Fog - Trælast og Byggecenter</title>

    <!-- Bootstrap core CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Datatables CSS -->
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/responsive/2.2.3/css/responsive.bootstrap4.min.css" rel="stylesheet">


    <style>
        html,body{
            height:100%;
            padding-bottom: 60px;
        }

        .flex-container {
            height: auto;
            min-height: 56vh;
            margin-bottom: 20px;
        }

        .context-dark, .bg-gray-dark, .bg-primary {
            color: rgba(255, 255, 255, 0.8);
        }

        .footer-classic a, .footer-classic a:focus, .footer-classic a:active {
            color: #ffffff;
        }
        .nav-list li {
            padding-top: 5px;
            padding-bottom: 5px;
        }

        .nav-list li a:hover:before {
            margin-left: 0;
            opacity: 1;
            visibility: visible;
        }

        ul, ol {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        a{
            color: #ffffff;
        }


    </style>

</head>

<body data-gr-c-s-loaded="true" cz-shortcut-listen="true">



<!-- Begin page content -->
<main role="main" class="container h-100 " >
    <header>
        <!-- Fixed navbar -->
        <nav class="navbar navbar-expand-md navbar-white" style="background-color: #0C2069;padding: 0px">
            <img src="https://www.johannesfog.dk/globalassets/header/logo.png">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="#">Carporte</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="request.jsp">Byg Selv</a>
                    </li>
                    <li class="nav-item">
                        <form id="testForm" action="FrontController" method="POST">
                        <input type="hidden" name="target" value="bill">
                        <input type="hidden" name="order_id" value="1">
                        <a class="nav-link" href="javascript:{}" onclick="document.getElementById('testForm').submit(); return false;">StykListe</a>
                        </form>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="orders.jsp">Ordre</a>
                    </li>
                </ul>
                <form class="form-inline mt-2 mt-md-0" _lpchecked="1">
                    <button class="btn btn-outline-success my-2 mr-2 my-sm-0" type="submit" style="color:white">Login</button>
                    <button class="btn btn-outline-success my-2 my-sm-0 mr-3" type="submit" style="color:white">Register</button>
                </form>
            </div>
        </nav>
    </header>
