package servlets.forAdmin;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class MainAdminServlet extends HttpServlet {
    private UserService service = UserService.getUserService();
    private String add;
    private String update;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> listUser = service.getAllClient();
        request.setAttribute("listUser", listUser);
        request.setAttribute("addResult", add);
        request.setAttribute("UpdateResult", update);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/admin-jsp.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        add = (String) request.getAttribute("addResult");
        update = (String) request.getAttribute("UpdateResult");
        response.sendRedirect("/admin");
    }
}