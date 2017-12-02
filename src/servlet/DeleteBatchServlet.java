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
@WebServlet(name = "DeleteBatchServlet")
public class DeleteBatchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] arrayId = request.getParameterValues("id");
        Integer[] arrayId1 = new Integer[arrayId.length];
        for (int i = 0; i < arrayId.length; i++){
            arrayId1[i] = Integer.valueOf(arrayId[i]);
        }
        System.out.println(arrayId);
        CommandDao dao = new CommandDaoImpl();
        dao.deleteBatch(arrayId1);
        response.sendRedirect("list.action");
    }
}
