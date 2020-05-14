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
        <div class="container">
            <div class="row">
                <div class="col-sm-1 mr-5">
                    <div><a class="btn btn-primary btn-lg" href="orders.jsp" role="button">Tilbage</a></div>
                </div>
                <c:choose>
                    <c:when test="${requestScope.editing==true}">
                        <div class="col-sm-1 mr-4">
                            <button type="submit" class="btn btn-outline-success btn-lg" form="editForm">Gem</button>
                        </div>
                        <div class="col-sm-1 mr-4">
                            <div><a class="btn btn-outline-danger btn-lg" href="vieworder.jsp?o=${requestScope.order.orderId}" role="button">Annuller</a></div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="col-sm-1 mr-4">
                            <form action="FrontController" method="POST">
                                <button type="submit" class="btn btn-outline-secondary btn-lg">Rediger</button>
                                <input type="hidden" name="target" value="beginEditOrder">
                                <input type="hidden" name="order" value="${requestScope.order.orderId}">
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>


        <div class="flex-item">
            <c:choose>
                <c:when test="${requestScope.editing != true}">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-6">
                            <h2 class="display-4">Carport</h2>
                            <p class="entry-text">Bredde: </p>
                            <p class="entry-content"><fmt:formatNumber value="${requestScope.order.width}" type="number"/> cm</p>

                            <p class="entry-text">Dybde: </p>
                            <p class="entry-content"><fmt:formatNumber value="${requestScope.order.depth}" type="number"/> cm</p>

                            <p class="entry-text">Højde: </p>

                            <p class="entry-content"><fmt:formatNumber value="${requestScope.order.height}" type="number"/> cm</p>

                            <p class="entry-text">Tag: </p>
                            <c:choose>
                                <c:when test="${requestScope.order.incline == 0}">
                                    <p class="entry-content">Fladt</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="entry-content">Rejst, <fmt:formatNumber value="${requestScope.order.incline}" type="number"/>°</p>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${requestScope.order.withShed}">
                                    <h2 class="display-4">Skur</h2>
                                    <p class="entry-text">Bredde: </p>
                                    <p class="entry-content"><fmt:formatNumber value="${requestScope.order.shedWidth}" type="number"/> cm</p>

                                    <p class="entry-text">Dybde: </p>
                                    <p class="entry-content"><fmt:formatNumber value="${requestScope.order.shedDepth}" type="number"/> cm</p>
                                </c:when>
                            </c:choose>
                        </div>
                        <div class="col-lg-6">
                            <h2 class="display-4">Kunde</h2>

                            <p class="entry-text">Navn: </p>
                            <p class="entry-content">John Doe</p>

                            <p class="entry-text">Adresse: </p>
                            <p class="entry-content">KlodenRundt 4</p>

                            <p class="entry-text">Email: </p>
                            <p class="entry-content">john@gmail.com</p>

                            <p class="entry-text">Telefon: </p>
                            <p class="entry-content">12345678</p>

                            <p class="entry-text">Postnr: </p>
                            <p class="entry-content">1234</p>

                            <p class="entry-text">By: </p>
                            <p class="entry-content">Aabybro</p>
                        </div>
                    </div>
                </div>
            </c:when>
                <c:otherwise>
                    <div class="container">
                        <div class="col-xl-11 col-lg-10 col-md-10 col-sm-10 mx-auto text-center form p-4 pt-5">
                            <form action="FrontController" method="POST" id="editForm">
                                <div class="row">
                                    <div class="col-md-6 col-sm-12" align="center">
                                        <div class="pb-4">
                                            <h2><u>Carport</u></h2>
                                        </div>
                                        <div class="form-group">
                                            <label for="carportwidth">Bredde</label>
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <span class="fas fa-ruler-vertical"> cm</span>
                                                    </span>
                                                </div>
                                                <input type="number" id="carportwidth" name="carportwidth"
                                                       placeholder="Ønsket bredde" type="text"
                                                       class="form-control <c:if test="${requestScope.carportWidthError != null}">is-invalid</c:if>"
                                                       required="required"
                                                       value="<c:if test="${requestScope.carportWidth != null}">${requestScope.carportWidth}</c:if>">
                                            </div>
                                            <c:if test="${requestScope.carportWidthError != null}">
                                                <span class="text-danger">${requestScope.carportWidthError}</span>
                                            </c:if>
                                        </div>
                                        <div class="form-group">
                                            <label for="carportdepth">Dybde</label>
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <span class="fas fa-ruler-vertical"> cm</span>
                                                    </span>
                                                </div>
                                                <input type="number" id="carportdepth" name="carportdepth"
                                                       placeholder="Ønsket dybde" type="text"
                                                       class="form-control <c:if test="${requestScope.carportDepthError != null}">is-invalid</c:if>"
                                                       required="required"
                                                       value="<c:if test="${requestScope.carportDepth != null}">${requestScope.carportDepth}</c:if>">
                                            </div>
                                            <c:if test="${requestScope.carportDepthError != null}">
                                                <span class="text-danger">${requestScope.carportDepthError}</span>
                                            </c:if>
                                        </div>
                                        <div class="form-group">
                                            <label for="carportheight">Højde</label>
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <span class="fas fa-ruler-vertical"> cm</span>
                                                    </span>
                                                </div>
                                                <input type="number" id="carportheight" name="carportheight"
                                                       placeholder="Ønsket højde" type="text"
                                                       class="form-control <c:if test="${requestScope.carportHeightError != null}">is-invalid</c:if>"
                                                       required="required"
                                                       value="<c:if test="${requestScope.carportHeight != null}">${requestScope.carportHeight}</c:if>">
                                            </div>
                                            <c:if test="${requestScope.carportHeightError != null}">
                                                <span class="text-danger">${requestScope.carportHeightError}</span>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6 col-sm-12" align="center">
                                        <c:choose>
                                            <c:when test="${requestScope.order.withShed == true}">
                                                <input type="hidden" name="shedornot" value="true">
                                                <div class="pb-3">
                                                    <h2><u>Skur</u></h2>
                                                </div>
                                                <div class="form-group">
                                                    <label for="shedwidth">Bredde</label>
                                                    <div class="input-group w-75">
                                                        <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <span class="fas fa-ruler-vertical"> cm</span>
                                                    </span>
                                                        </div>
                                                        <input type="number" id="shedwidth" name="shedwidth"
                                                               placeholder="Ønsket bredde på skuret" type="text"
                                                               class="form-control <c:if test="${requestScope.shedWidthError != null}">is-invalid</c:if>"
                                                               value="<c:if test="${requestScope.shedWidth != null}">${requestScope.shedWidth}</c:if>"
                                                               <c:if test="${requestScope.shedornot == false}">disabled</c:if>>
                                                    </div>
                                                    <c:if test="${requestScope.shedWidthError != null}">
                                                        <span class="text-danger">${requestScope.shedWidthError}</span>
                                                    </c:if>
                                                </div>
                                                <div class="form-group">
                                                    <label for="sheddepth">Dybde</label>
                                                    <div class="input-group w-75">
                                                        <div class="input-group-prepend">
                                                    <span class="input-group-text">
                                                        <span class="fas fa-ruler-vertical"> cm</span>
                                                    </span>
                                                        </div>
                                                        <input type="number" id="sheddepth" name="sheddepth"
                                                               placeholder="Ønsket længde på skuret" type="text"
                                                               class="form-control <c:if test="${requestScope.shedDepthError != null}">is-invalid</c:if>"
                                                               value="<c:if test="${requestScope.shedDepth != null}">${requestScope.shedDepth}</c:if>"
                                                               <c:if test="${requestScope.shedornot == false}">disabled</c:if>>
                                                    </div>
                                                    <c:if test="${requestScope.shedDepthError != null}">
                                                        <span class="text-danger">${requestScope.shedDepthError}</span>
                                                    </c:if>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="shedornot" value="false">
                                            </c:otherwise>
                                        </c:choose>
                                        <h2><u>Tag</u></h2>
                                        <div class="form-group">
                                            <div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input name="rooftype" id="flatroof" type="radio"
                                                           required="required" class="custom-control-input"
                                                           value="flat" onclick="checkInclineRadio()"
                                                           <c:if test="${requestScope.rooftype == 'flat'}">checked</c:if>>
                                                    <label for="flatroof" class="custom-control-label">Jeg vil have flat
                                                        tag</label>
                                                </div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input name="rooftype" id="inclinedroof" type="radio"
                                                           required="required" class="custom-control-input"
                                                           value="inclined" onclick="checkInclineRadio()"
                                                           <c:if test="${requestScope.rooftype == 'inclined'}">checked</c:if>>
                                                    <label for="inclinedroof" class="custom-control-label">Jeg vil have
                                                        skråt tag</label>
                                                </div>
                                            </div>

                                            <div class="my-4">
                                                <label for="roofIncline">Tag vinkel</label>
                                                <div class="d-flex justify-content-center">
                                                    <div class="w-75">
                                                        <input name="roofIncline" type="range" min="20" max="25" value="${requestScope.order.incline}" step="1.0" class="custom-range" id="roofIncline"/>
                                                    </div>
                                                    <p><span class="font-weight-bold text-primary ml-2 valueSpan2" id="inclineDisplay"></span>°</p>
                                                    <c:if test="${requestScope.inclineError != null}">
                                                        <span class="text-danger">${requestScope.inclineError}</span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="target" value="saveEditOrder">
                                <input type="hidden" name="orderid" value="${requestScope.order.orderId}">
                            </form>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>


            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div><a style="float:right;" class="btn btn-info btn-lg" href="FrontController?target=bill&order_id=${requestScope.order.orderId}" role="button">Stykliste</a></div>
                    </div>
                    <div class="col-lg-6">
                        <div><a style="float:left;" class="btn btn-secondary btn-lg" href="FrontController?target=drawing&order_id=${requestScope.order.orderId}" role="button">Tegning</a></div>
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
    var slider = document.getElementById("roofIncline");
    var output = document.getElementById("inclineDisplay");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        output.innerHTML = this.value;
    }
    function checkInclineRadio() {
        if(document.getElementById('flatroof').checked) {
            document.getElementById('roofIncline').disabled = true;
            output.innerHTML = 0;
        } else {
            document.getElementById('roofIncline').disabled = false;
            output.innerHTML = slider.value;
        }
    }
    $(document).ready(function () {

        checkInclineRadio();
    });

    $('#orders').DataTable( {
        responsive: true,
        "order": [[ 5, "desc" ]]
    } );
</script>


<jsp:include page="WEB-INF/includes/footer.jsp"/>


