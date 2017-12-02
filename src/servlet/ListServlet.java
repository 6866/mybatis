package servlet;

import com.sun.org.apache.xpath.internal.SourceTree;
import dao.CommandDao;
import dao.Impl.CommandDaoImpl;
import dao.Impl.MicroMessageDaoImpl;
import dao.MicroMessageDao;
import entity.Micro_message;
import entity.Pages;

import java.io.IOException;

/**
 * Created by yangxingrom on 2017/11/14.
 */
public class ListServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String command = request.getParameter("command");
//        System.out.println("command:" + command);
//        String description = request.getParameter("description");
//        Micro_message message = null;
//
//        if(command != null && !"".equals(command.trim())){
//
//            message = new Micro_message();
//            message.setCommand(command);
//            System.out.println("command.trim()" + command.trim());
//        }
//
//        if(description != null && !"".equals(description.trim())){
//            if(message == null)
//                message = new Micro_message();
//            message.setDescription(description);
//        }
//        System.out.println(message);
//        MicroMessageDao dao = new MicroMessageDaoImpl();
//        request.setAttribute("messageList",dao.findAll(message));
//        request.getRequestDispatcher("WEB-INF/back/jsp/list.jsp").forward(request,response);


        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter("command");
        System.out.println("command:" + command);
        String description = request.getParameter("description");
        CommandDao dao = new CommandDaoImpl();
        Pages pages = new Pages();
        String currentPage = request.getParameter("currentPage");
        if(currentPage != null && !"".equals(currentPage)){
            pages.setCurrentPage(Integer.valueOf(currentPage));

        }

        pages.setSumNumber(dao.findCount(command, description));
        pages.count();
        request.setAttribute("messageList", dao.findAll(command, description,pages));
        request.setAttribute("pages",pages);
        request.setAttribute("description",description);
        request.setAttribute("command",command);
        request.getRequestDispatcher("WEB-INF/back/jsp/list.jsp").forward(request, response);


    }
}
