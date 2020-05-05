<%@ page import="PresentationLayer.Orders" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%
    new Orders().execute(request, response);
%>
<jsp:include page="WEB-INF/includes/header.jsp"/>


    <!-- Title header -->
    <div class="text-center">
        <h1 class="display-1">Ordre Oversigt</h1>
    </div>
    <div class="flex-container shadow my-5 py-5 px-5">
        <div class="flex-item">
            <table id="orders" class="table table-striped table-bordered" style="width:100%">
                <thead class="text-center">
                <tr>
                    <th>#</th>
                    <th>Kunde</th>
                    <th>Dybde</th>
                    <th>Bredde</th>
                    <th>Status</th>
                    <th>Dato</th>
                    <th></th>
                </tr>
                </thead>
                <tbody class="text-center">
                    <c:forEach var="order" items="${requestScope.orders}">
                <tr>
                    <td>${order.orderId}</td>
                    <td>Name Here</td>
                    <td>${order.depth.depth}</td>
                    <td>${order.width.width}</td>
                    <td><span class="badge badge-pill badge-primary">Afventer Sælger</span></td>
                    <td>01/01/2001</td>
                    <td class="text-center"><a href="vieworder.jsp?o=${order.orderId}" style=" color: unset"><i class="fas fa-eye"></i></a></td>
                </tr>
                </c:forEach>

                    <!---
                    For when status is implemeted
                    <td><span class="badge badge-pill badge-info">Afventer Sælger</span></td>
                    <td><span class="badge badge-pill badge-success">Godkendt</span></td>
                    <td><span class="badge badge-pill badge-danger">Annulleret</span></td>
                    -->
                </tbody>
            </table>
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
        responsive: true
    } );</script>


<jsp:include page="WEB-INF/includes/footer.jsp"/>


