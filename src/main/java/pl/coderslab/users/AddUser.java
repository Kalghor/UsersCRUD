package pl.coderslab.users;

import pl.coderslab.utils.DbUtil;
import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/user/add")
public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection;
        try {
            connection = DbUtil.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        UserDao userDao = new UserDao();
        User user = new User(userName,userEmail,userPassword);
        user = userDao.addUserToDB(user);
        getServletContext().getRequestDispatcher("/user/list.jsp").forward(request, response);
    }
}
