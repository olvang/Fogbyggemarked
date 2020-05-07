<%--
  Created by IntelliJ IDEA.
  User: gamma
  Date: 06-05-2020
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="PresentationLayer.Orders" %>
<%@ page import="PresentationLayer.ViewOrder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="style/style.css">
<%
    new ViewOrder().execute(request, response);
%>
<jsp:include page="WEB-INF/includes/header.jsp"/>

    <!-- Title header -->
    <div class="text-center">
        <h1 class="display-1">Ordre ${requestScope.order.orderId}</h1>
    </div>
    <div class="flex-container shadow my-5 py-5 px-5">
        <div><a class="btn btn-primary btn-lg" href="orders.jsp" role="button">Tilbage</a></div>
        <div class="flex-item">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <h2 class="display-4">Carport</h2>
                        <p class="entry-text">Dybde: </p>
                        <p class="entry-conent"><fmt:formatNumber value="${requestScope.order.depth.depth}" type="number"/> cm</p>

                        <p class="entry-text">Bredde: </p>
                        <p class="entry-conent"><fmt:formatNumber value="${requestScope.order.width.width}" type="number"/> cm</p>

                        <p class="entry-text">Højde: </p>
                        <p class="entry-conent"><fmt:formatNumber value="${requestScope.order.height.height}" type="number"/> cm</p>

                        <c:choose>
                            <c:when test="${requestScope.order.withShed}">
                                <h2 class="display-4">Skur</h2>

                                <p class="entry-text">Dybde: </p>
                                <p class="entry-conent"><fmt:formatNumber value="${requestScope.order.shedDepth.depth}" type="number"/> cm</p>

                                <p class="entry-text">Bredde: </p>
                                <p class="entry-conent"><fmt:formatNumber value="${requestScope.order.shedWidth.width}" type="number"/> cm</p>
                            </c:when>
                        </c:choose>

                    </div>
                    <div class="col-lg-6">
                        <h2 class="display-4">Kunde</h2>

                        <p class="entry-text">Navn: </p>
                        <p class="entry-conent">John Doe</p>

                        <p class="entry-text">Adresse: </p>
                        <p class="entry-conent">KlodenRundt 4</p>

                        <p class="entry-text">Email: </p>
                        <p class="entry-conent">john@gmail.com</p>

                        <p class="entry-text">Telefon: </p>
                        <p class="entry-conent">12345678</p>

                        <p class="entry-text">Postnr: </p>
                        <p class="entry-conent">1234</p>

                        <p class="entry-text">By: </p>
                        <p class="entry-conent">Aabybro</p>
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div><a style="float:right;" class="btn btn-info btn-lg" href="FrontController?target=bill&order_id=${requestScope.order.orderId}" role="button">Stykliste</a></div>
                    </div>
                    <div class="col-lg-6">
                        <div><a style="float:left;" class="btn btn-secondary btn-lg" href="index.jsp" role="button">Tegning</a></div>
                    </div>
                </div>
            </div>

        </div>
    </div>


<!-- jQuery  JavaScript -->
<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>

<!-- Datatables JavaScript -->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

<script>
    $('#orders').DataTable( {
        responsive: true,
        "order": [[ 5, "desc" ]]
    } );</script>


<jsp:include page="WEB-INF/includes/footer.jsp"/>


