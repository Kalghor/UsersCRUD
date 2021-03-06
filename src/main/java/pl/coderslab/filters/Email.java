package pl.coderslab.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter("/users/add")
public class Email implements Filter {
    private static final String EMAIL_VALID = "[a-zA-z0-9]+[\\.]*[a-zA-z0-9-]+\\@[a-zA-z]+\\.([a-zA-z]{2,}){1}";

    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        Pattern compiledPattern = Pattern.compile(EMAIL_VALID);
        String email_faild = "";
        if (request.getParameter("userEmail") != null) {
            Matcher matcher = compiledPattern.matcher(request.getParameter("userEmail"));
            if (!matcher.find()) {
                System.out.println("Niepoprawny adres email!");
                email_faild = "Niepoprawny adres email. Adres email może zawierać: male i wielkie litery, cyfry od 0 - 9 i znaki: _ - . ";
                ((HttpServletRequest) request).setAttribute("email_faild", email_faild);
                ((HttpServletRequest) request).getServletContext().getRequestDispatcher("/user/addUser.jsp").forward(request,response);
            } else {
                chain.doFilter(request, response);
            }
        }
    }
}
