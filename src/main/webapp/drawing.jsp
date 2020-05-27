<%--
  Created by IntelliJ IDEA.
  User: simon
  Date: 5/11/2020
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="WEB-INF/includes/header.jsp"/>

<style>
    .svg-container {
        height: 0;
        padding-top:100%; /* Sizingx */
        position: relative;
    }
    .svg-content {
        height: 100%;
        display:block;
        width: 100%;
        position: absolute;
        top:0;
        left:0;
        align-content: center;
    }
</style>

<div class="text-center">
    <h1 class="display-1">Ordre ${requestScope.order_id} tegning</h1>
</div>
<div class="flex-container shadow my-5 py-5 px-5">
    <div><a class="btn btn-primary btn-lg" href="vieworder.jsp?o=${requestScope.order_id}" role="button">Tilbage</a></div>
    <div class="flex-item">
        <div class="svg-container">
            ${requestScope.svgdrawing}
        </div>
    </div>
</div>


<jsp:include page="WEB-INF/includes/footer.jsp"></jsp:include>
