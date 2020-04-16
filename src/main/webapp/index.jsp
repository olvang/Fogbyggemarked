<%-- 
    Document   : index
    Created on : Aug 22, 2017, 2:01:06 PM
    Author     : kasper
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome page</title>
    </head>
    <body>
        <h1>Fog Byggemarked</h1>

        <table>
            <tr><td>Carport designer</td>
                <td>
                    <form name="selections" action="FrontController" method="POST">
                        <input type="hidden" name="target" value="selections">
                        <label for="width">Bredde</label>
                        <br>
                        <input type="number" name="width" id="width" placeholder="Bredde">
                        <label for="depth">Dybde</label>
                        <br>
                        <input type="number" name="depth" id="depth" placeholder="Dybde">
                        <label for="height">Højde</label>
                        <br>
                        <input type="number" name="height" id="height" placeholder="Højde">
                        <br>
                        <input type="submit" value="Send">
                    </form>
                </td>
            </tr>
        </table>


<%--        Bare lige se I har en ide om hvad vi forslå I ikke gør ! det hedder scpript lets --%>
<%--        <% String error = (String) request.getAttribute( "error");--%>
<%--           if ( error != null) { --%>
<%--               out.println("<H2>Error!!</h2>");--%>
<%--               out.println(error);--%>
<%--           }--%>
<%--        %>--%>

        <c:if test = "${requestScope.error!= null}" >

           <h2>Error ! </h2>
            ${requestScope.error}

        </c:if>
    </body>
</html>
