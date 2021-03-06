<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<a href="/user/addUser.jsp" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
            class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
</div>


<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Lista użytkowników</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Nazwa użytkownika</th>
                    <th>Email</th>
                    <th>Akcja</th>
                </tr>
                </thead>
                <tfoot>
                <c:forEach var="user" items="${allUsers}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.userName}</td>
                        <td>${user.email}</td>
                        <td><a href='<c:url value="/users/edit?id=${user.id}"/>'>Edytuj</a> <a href='<c:url value="/users/delete?id=${user.id}"/>'>Usuń</a> <a href='<c:url value="/users/show?id=${user.id}"/>'>Pokaż</a></td>

                    </tr>
                </c:forEach>
                </tfoot>
            </table>
        </div>
    </div>
</div>


<%@ include file="footer.jsp" %>
</body>
</html>
