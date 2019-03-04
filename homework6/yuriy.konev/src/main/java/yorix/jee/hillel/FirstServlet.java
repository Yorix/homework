package yorix.jee.hillel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(200);
        response.setContentType("text/html");
        response.getWriter().write("<font color='555555' size=50><h1>HELLO WORD</h1><br><a href='/second'>page 2</a></font>");
    }
}
