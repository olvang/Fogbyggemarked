<%--
  Created by IntelliJ IDEA.
  User: Oliver
  Date: 17/04/2020
  Time: 16.55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WEB-INF/includes/header.jsp" />



    <!-- Title header -->
    <div class="text-center">
        <h1 class="display-1">Carport Designer</h1>
    </div>
    <div class="flex-container shadow my-5">
        <div class="flex-item">
            <div class="content">
                <container class="container">
                    <div class="col-xl-11 col-lg-10 col-md-10 col-sm-10 mx-auto text-center form p-4 pt-5">
                        <form>
                            <div class="row">
                                <div class="col-md-6 col-sm-12" align="center">
                                    <div class="pb-5">
                                    <h2><u>Carport</u></h2>
                                    </div>
                                    <div class="form-group">
                                        <label for="carportwidth">Bredde</label>
                                        <input type="number" id="carportwidth" name="carportwidth" placeholder="Ønsket bredde i meter" type="text" class="form-control w-75" required="required">
                                    </div>
                                    <div class="form-group">
                                        <label for="carportdepth">Dybde</label>
                                        <input type="number" id="carportdepth" name="carportdepth" placeholder="Ønsket dybde i meter" type="text" class="form-control w-75" required="required">
                                    </div>
                                    <div class="form-group">
                                        <label for="carportheight">Højde</label>
                                        <input type="number" id="carportheight" name="carportheight" placeholder="Ønsket højde i meter" type="text" class="form-control w-75" required="required">
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-12" align="center">
                                    <div class="pb-2">
                                    <h2><u>Skur</u></h2>
                                    </div>
                                    <div class="form-group">
                                        <div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                <input name="shedornot" id="shedornot_0" type="radio" required="required" class="custom-control-input" value="true" onclick="checkRadioButton()">
                                                <label for="shedornot_0" class="custom-control-label">Jeg vil gerne have skur</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                <input name="shedornot" id="shedornot_1" type="radio" required="required" class="custom-control-input" value="false" onclick="checkRadioButton()">
                                                <label for="shedornot_1" class="custom-control-label">Jeg vil ikke have skur</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="shedwidth">Bredde</label>
                                        <input type="number" id="shedwidth" name="shedwidth" placeholder="Ønsket bredde på skuret" type="text" class="form-control w-75">
                                    </div>
                                    <div class="form-group">
                                        <label for="sheddepth">Dybde</label>
                                        <input type="number" id="sheddepth" name="sheddepth" placeholder="Ønsket længde på skuret" type="text" class="form-control w-75" checked>
                                    </div>
                                        <h2><u>Tag</u></h2>
                                    <div class="form-group">
                                        <div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                <input name="rooftype" id="flatroof" type="radio" required="required" class="custom-control-input" value="flat">
                                                <label for="flatroof" class="custom-control-label">Jeg vil have flat tag</label>
                                            </div>
                                            <div class="custom-control custom-radio custom-control-inline">
                                                <input name="rooftype" id="inclinedroof" type="radio" required="required" class="custom-control-input" value="inclined">
                                                <label for="inclinedroof" class="custom-control-label">Jeg vil have skråt tag</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group pt-5">
                                <button name="submit" type="submit" class="btn btn-primary btn-lg">Send</button>
                            </div>
                        </form>

                    </div>

                </container>
            </div>
        </div>

    </div>

<script>
    function checkRadioButton()
    {
        //If
        if(document.getElementById('shedornot_0').checked)
        {
            document.getElementById('shedwidth').disabled=false;
            document.getElementById('sheddepth').disabled=false;
        }else{
            document.getElementById('shedwidth').disabled=true;
            document.getElementById('shedwidth').value = "";
            document.getElementById('sheddepth').disabled=true;
            document.getElementById('sheddepth').value = "";
        }
    }

    //On ready disable shed textboxes
    $( document ).ready(function() {
        checkRadioButton();
    });

</script>


<jsp:include page="WEB-INF/includes/footer.jsp"></jsp:include>