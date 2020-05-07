<%@ page import="PresentationLayer.Bill" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%
    //new Bill().execute(request,response);
%>
<jsp:include page="WEB-INF/includes/header.jsp"/>

    <!-- Title header -->
    <div class="text-center">
        <h1 class="display-1">Ordre ${requestScope.order_id} stykliste</h1>
    </div>
    <div class="flex-container shadow my-5 py-5 px-5">
        <div><a class="btn btn-primary btn-lg" href="vieworder.jsp?o=${requestScope.order_id}" role="button">Tilbage</a></div>
        <div class="flex-item">
            <table id="orders" class="table table-striped table-bordered" style="width:100%">
                <thead class="text-center">
                <tr>
                    <th>Materiale</th>
                    <th>Længde</th>
                    <th>Antal</th>
                    <th>Enhed</th>
                    <th>Beskrivelse</th>
                </tr>
                </thead>
                <tbody class="text-center">
                    <c:forEach var="bill" items="${requestScope.bill}">
                        <tr>
                            <td>${bill.material.name}</td>
                            <td><fmt:formatNumber value="${bill.material.length / 10}" type="number"/></td>
                            <td>${bill.amount}</td>
                            <td>Stk/Pakke</td>
                            <td>${bill.description}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


<!-- jQuery  JavaScript -->
<script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous"></script>


<!-- Bootstrap core JavaScript
================================================== -->
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>


<!-- Datatables JavaScript -->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/dataTables.responsive.min.js"></script>
<script src="https://cdn.datatables.net/responsive/2.2.3/js/responsive.bootstrap4.min.js"></script>

<script>
    $('#orders').DataTable( {
        responsive: false,
        paging: false,
        ordering:  false
    } );
</script>


<jsp:include page="WEB-INF/includes/footer.jsp"/>