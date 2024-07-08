<%-- 
    Document   : ListAccount
    Created on : Jun 12, 2024, 8:08:32 AM
    Author     : ACER
    Usage      : List accounts file
--%>

<%@page import="controller.Action"%>
<%@page import="controller.Navigation"%>
<%@page import="java.util.List"%>
<%@page import="entities.Accounts"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>List Account Page</title>
        <link rel="shortcut icon" href="images/web_logo.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" 
              integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" 
              crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body >
        <c:if test="${login == null}">
            <jsp:forward page = "<%= Navigation.LOGIN%>"></jsp:forward>
        </c:if>

        <jsp:include page="<%= Navigation.DASHBOARD%>" ></jsp:include>

            <h2 class="text-center mt-4">List All Accounts</h2>
            <table class="table table-hover container mt-3">
                <thead class="table-info">
                    <tr>
                        <th scope="col">Account</th>
                        <th scope="col">Full name</th>
                        <th scope="col">Birthday</th>
                        <th scope="col">Gender</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Role in system</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>                          

                <c:forEach items="${listAccount}" var="a">
                    <tr id="${a.account}" >
                        <td>${a.account}</td>
                        <td>${a.lastName}, ${a.firstName}</td>
                        <td>${a.birthday}</td>
                        <td>
                            <c:choose>
                                <c:when test="${a.gender}">Male</c:when>
                                <c:otherwise>Female</c:otherwise>
                            </c:choose></td>
                        <td>${a.phone}</td>
                        <td>
                            ${a.roleInSystem}
                        </td>
                        <td>

                            <c:url scope="request" var="updateUrl" value="<%= Navigation.UPDATE_ACCOUNT%>">
                                <c:param name="account" value="${a.account}"></c:param>
                            </c:url>
                            <c:url scope="request" var="isUseUpdateUrl" value="<%= Action.ISUSE_UPDATE_ACCOUNT%>">
                                <c:param name="account" value="${a.account}"></c:param>
                            </c:url>
                            <c:url scope="request" var="deleteUrl" value="<%= Action.DELETE_ACCOUNT%>">
                                <c:param name="account" value="${a.account}"></c:param>
                            </c:url>
                            <c:choose>
                                <c:when test="${login.account != a.account}">
                                    <a href="${updateUrl}" class="btn btn-warning">Update</a>
                                    <a href="${isUseUpdateUrl}" onclick="return confirm('Do you want to change status?')" class="btn btn-success" style="width: 90px">
                                        <c:choose>
                                            <c:when test="${a.isUse}">Deactive</c:when>
                                            <c:otherwise>Active</c:otherwise>
                                        </c:choose>
                                    </a>
                                    <a href="${deleteUrl}" onclick="return confirm('Do you want to delete this account?')" class="btn btn-danger" >Delete</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${updateUrl}" class="btn btn-warning disabled">Update</a>
                                    <a href="${isUseUpdateUrl}" class="btn btn-success disabled" style="width: 90px">
                                        <c:choose>
                                            <c:when test="${a.isUse}">Deactive</c:when>
                                            <c:otherwise>Active</c:otherwise>
                                        </c:choose>
                                    </a>
                                    <a href="${deleteUrl}"  class="btn btn-danger disabled" >Delete</a>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>  


                </c:forEach>     
            </tbody>
        </table>


    </body>
</html>
