<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<a href="/users/list" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
        class="fas fa-download fa-sm text-white-50"></i> Lista użytkowników</a>
</div>
<!-- DataTales Example -->
<div class="card shadow mb-4">
    <div class="card-header py-3">
        <h6 class="m-0 font-weight-bold text-primary">Edycja użytkownika</h6>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                <form action="/users/edit" method="post">
                    <div class="form-group">
                        <label for="formGroupExampleInput">Nazwa</label>
                        <input name="userName" type="text" class="form-control" id="formGroupExampleInput" value="${oldName}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input name="userEmail" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" value="${oldEmail}">
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1">Hasło</label>
                        <input name="userPassword" type="password" class="form-control" id="exampleInputPassword1" value="Hasło użytkownika">
                    </div>
                    <button type="submit" name="id" value="${id}" class="btn btn-primary">Edycja</button>
                </form>
            </table>
        </div>
    </div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
