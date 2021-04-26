<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<a href="<c:url value="/user/addUser.jsp"/>" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
        class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
</div>


<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Szczegóły użytkownika</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table">
                <tbody>
                <tr>
                    <th scope="row">Id</th>
                    <td>${id}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th scope="row">Nazwa użytkownika</th>
                    <td>${name}</td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <th scope="row">Email</th>
                    <td>${email}</td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
