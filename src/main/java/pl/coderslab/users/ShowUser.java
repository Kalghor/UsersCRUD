package pl.coderslab.users;

import pl.coderslab.utils.User;
import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/users/show")
public class ShowUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String id = request.getParameter("id");
        User user = userDao.read(Integer.parseInt(id));
        request.setAttribute("id",id);
        request.setAttribute("name",user.getUserName());
        request.setAttribute("email",user.getEmail());
        getServletContext().getRequestDispatcher("/user/showUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
