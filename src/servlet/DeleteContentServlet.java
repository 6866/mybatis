package servlet;

import dao.CommandDao;
import dao.Impl.CommandDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangxingrom on 2017/11/26.
 */
@WebServlet(name = "DeleteContentServlet")
public class DeleteContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandId = request.getParameter("commandId");
        String[] id = request.getParameterValues("id");
        Integer[] ids = new Integer[id.length];
        for (int i = 0; i < id.length; i++){
            ids[i] = Integer.valueOf(id[i]);
        }
        CommandDao dao = new CommandDaoImpl();
        dao.deleteContent(ids);
        response.sendRedirect("edit.action?id=" + commandId);
    }
}
