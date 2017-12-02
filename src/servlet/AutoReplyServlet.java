package servlet;

import dao.CommandDao;
import dao.Impl.CommandDaoImpl;
import dao.Impl.MicroMessageDaoImpl;
import dao.MicroMessageDao;
import entity.Command_content;
import entity.Micro_message;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by yangxingrom on 2017/11/20.
 */
@WebServlet(name = "AutoReplyServlet")
public class AutoReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String content  = request.getParameter("content");
        System.out.println("AutoReplyServlet  "+ content);
        CommandDao dao = new CommandDaoImpl();
        PrintWriter printWriter = response.getWriter();
        Command_content content1 = dao.findOneRandom(content);
        if(content1 != null){
            printWriter.write(content1.getContent());
        }else{
            printWriter.write("你说啥？ ");
        }
        printWriter.flush();
        printWriter.close();


    }
}
