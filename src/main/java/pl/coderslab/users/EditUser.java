package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/users/edit")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String id = request.getParameter("id");
        User user = userDao.read(Integer.parseInt(id));
        request.setAttribute("id",id);
        request.setAttribute("oldName",user.getUserName());
        request.setAttribute("oldEmail",user.getEmail());
        getServletContext().getRequestDispatcher("/user/editUser.jsp").forward(request, response);
        response.sendRedirect("/user/editUser.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String id = request.getParameter("id");
//        User user = userDao.read(Integer.parseInt(id));
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPassword = request.getParameter("userPassword");
        User user = new User(userName, userEmail, userPassword);
        user.setId(Integer.parseInt(id));
        userDao.update(user);
        response.sendRedirect("/users/list"); }
}
