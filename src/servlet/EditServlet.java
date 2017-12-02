package servlet;

import dao.CommandDao;
import dao.Impl.CommandDaoImpl;
import entity.Command;
import entity.Command_content;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangxingrom on 2017/11/22.
 */
@WebServlet(name = "EditServlet")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Command command = new Command();
        Map<String, String[]> map = request.getParameterMap();
        List<Command_content> list = new ArrayList<>();
        for (Map.Entry<String,String[]> entry : map.entrySet()) {

            String key = entry.getKey();
            int i = key.indexOf("_");
            System.out.println("发现_:"+ i);
            if(i != -1) {
                Command_content content = new Command_content();
                System.out.println("substring:" + key.substring(i+1,key.length()));
                content.setId(Integer.valueOf(key.substring(i+1, key.length())));
                String content_s = "";
                for (String value: entry.getValue()) {
                    content_s += value;
                }
                content.setContent(content_s);
                list.add(content);
            }

        }
        String[] newContent = request.getParameterValues("newContent");
        if(newContent != null && newContent.length>0){
            for (String content: newContent) {
                Command_content content1 = new Command_content();
                content1.setContent(content);
                list.add(content1);
            }
        }

        command.setContents(list);
        command.setId(Integer.valueOf(request.getParameter("id")));
        command.setCommand(request.getParameter("command"));
        command.setDescription(request.getParameter("description"));
        CommandDao dao = new CommandDaoImpl();

        dao.update(command);
        response.sendRedirect("list.action");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CommandDao dao = new CommandDaoImpl();

        request.setAttribute("command", dao.findById(Integer.valueOf(id)));
        request.getRequestDispatcher("/WEB-INF/back/jsp/edit.jsp").forward(request, response);
    }
}
