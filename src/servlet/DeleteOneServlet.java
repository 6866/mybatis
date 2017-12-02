package servlet;

import dao.CommandDao;
import dao.Impl.CommandDaoImpl;
import dao.Impl.MicroMessageDaoImpl;
import dao.MicroMessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangxingrom on 2017/11/19.
 */
@WebServlet(name = "DeleteOneServlet")
public class DeleteOneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        CommandDao dao = new CommandDaoImpl();

        dao.delete(Integer.valueOf(id));
        response.sendRedirect("list.action");
    }
}
