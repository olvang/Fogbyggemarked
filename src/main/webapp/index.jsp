<%--
  Created by IntelliJ IDEA.
  User: Oliver
  Date: 17/04/2020
  Time: 16.55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WEB-INF/includes/header.jsp"/>


<!-- Title header -->
<div class="text-center">
    <h1 class="display-1">Carport Designer</h1>
</div>
<div class="flex-container shadow my-5">
    <div class="flex-item">
        <div class="content">
            <container class="container">
                <div class="col-xl-11 col-lg-10 col-md-10 col-sm-10 mx-auto text-center form p-4 pt-5">
                    <c:choose>
                        <c:when test="${requestScope.success != null}">
                            <div class="alert alert-success" role="alert">
                                    ${requestScope.success}
                            </div>
                        </c:when>
                        <c:when test="${requestScope.error != null}">
                            <div class="alert alert-danger" role="alert">
                                    ${requestScope.error}
                            </div>
                        </c:when>
                        <c:otherwise>
                            <form action="FrontController" method="POST">
                                <div class="row">
                                    <div class="col-lg-6 col-md-12" align="center">
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
                                    <div class="col-lg-6 col-md-12" align="center">
                                        <div class="pb-3">
                                            <h2><u>Skur</u></h2>
                                        </div>
                                        <div class="form-group">
                                            <div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input name="shedornot" id="shedornot_0" type="radio"
                                                           required="required" class="custom-control-input" value="true"
                                                           onclick="checkRadioButton()"
                                                           <c:if test="${requestScope.shedornot == true}">checked</c:if>>
                                                    <label for="shedornot_0" class="custom-control-label">Jeg vil gerne
                                                        have skur</label>
                                                </div>
                                                <div class="custom-control custom-radio custom-control-inline">
                                                    <input name="shedornot" id="shedornot_1" type="radio"
                                                           required="required" class="custom-control-input"
                                                           value="false" onclick="checkRadioButton()"
                                                           <c:if test="${requestScope.shedornot == false}">checked</c:if>>
                                                    <label for="shedornot_1" class="custom-control-label">Jeg vil ikke
                                                        have skur</label>
                                                </div>
                                            </div>
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
                                                         <input name="roofIncline" type="range" min="20" max="25" value="20" step="1.0" class="custom-range" id="roofIncline"/>
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
                                <hr>
                                <div class="row">
                                    <div class="col-md-12 col-sm-12" align="center">
                                        <div class="pb-4">
                                            <h2>Kontakt information</h2>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <span class="fas fa-ruler-vertical"> Navn</span>
                                                </span>
                                                </div>
                                                <input type="text" id="name" name="name"
                                                       placeholder="Navn"
                                                       class="form-control"
                                                       required="required"
                                                       value="<c:if test="${requestScope.name != null}">${requestScope.name}</c:if>">
                                            </div>

                                            <c:if test="${requestScope.nameError != null}">
                                                <span class="text-danger">${requestScope.nameError}</span>
                                            </c:if>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <span class="fas fa-ruler-vertical"> Adresse</span>
                                                </span>
                                                </div>
                                                <input type="text" id="address" name="address"
                                                       placeholder="Adresse"
                                                       class="form-control"
                                                       required="required"
                                                       value="<c:if test="${requestScope.address != null}">${requestScope.address}</c:if>">
                                            </div>

                                            <c:if test="${requestScope.addressError != null}">
                                                <span class="text-danger">${requestScope.addressError}</span>
                                            </c:if>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <span class="fas fa-ruler-vertical"> Email</span>
                                                </span>
                                                </div>
                                                <input type="text" id="email" name="email"
                                                       placeholder="Email"
                                                       class="form-control"
                                                       required="required"
                                                       value="<c:if test="${requestScope.email != null}">${requestScope.email}</c:if>">
                                            </div>

                                            <c:if test="${requestScope.emailError != null}">
                                                <span class="text-danger">${requestScope.emailError}</span>
                                            </c:if>
                                        </div>

                                        <div class="form-group">

                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <span class="fas fa-ruler-vertical"> Telefon</span>
                                                </span>
                                                </div>
                                                <input type="text" id="phone" name="phone"
                                                       placeholder="Telefon nummer"
                                                       class="form-control"
                                                       required="required"
                                                       value="<c:if test="${requestScope.phone != null}">${requestScope.phone}</c:if>">
                                            </div>


                                            <c:if test="${requestScope.phoneError != null}">
                                                <span class="text-danger">${requestScope.phoneError}</span>
                                            </c:if>
                                        </div>

                                        <div class="form-group">
                                            <div class="input-group w-75">
                                                <div class="input-group-prepend">
                                                <span class="input-group-text">
                                                    <span class="fas fa-ruler-vertical"> Postnummer</span>
                                                </span>
                                                </div>
                                                <input type="text" id="zipcode" name="zipcode"
                                                       placeholder="Postnummer"
                                                       class="form-control"
                                                       required="required"
                                                       value="<c:if test="${requestScope.zipcode != null}">${requestScope.zipcode}</c:if>">
                                            </div>

                                            <c:if test="${requestScope.zipcodeError != null}">
                                                <span class="text-danger">${requestScope.zipcodeError}</span>
                                            </c:if>
                                        </div>

                                    </div>
                                </div>
                                <div class="form-group pt-5">
                                    <input type="hidden" name="target" value="request">
                                    <button name="submit" type="submit" class="btn btn-primary btn-lg">Send</button>
                                </div>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>

            </container>
        </div>
    </div>

</div>

<script>
    var slider = document.getElementById("roofIncline");
    var output = document.getElementById("inclineDisplay");
    output.innerHTML = slider.value;

    slider.oninput = function() {
        output.innerHTML = this.value;
    }

    function checkRadioButton() {
        //If
        if (document.getElementById('shedornot_0').checked) {
            document.getElementById('shedwidth').disabled = false;
            document.getElementById('sheddepth').disabled = false;
        } else {
            document.getElementById('shedwidth').disabled = true;
            document.getElementById('shedwidth').value = "";
            document.getElementById('sheddepth').disabled = true;
            document.getElementById('sheddepth').value = "";
        }
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

    //On ready disable shed textboxes
    $(document).ready(function () {
        checkRadioButton();
        checkInclineRadio();
    });


</script>


<jsp:include page="WEB-INF/includes/footer.jsp"></jsp:include>
