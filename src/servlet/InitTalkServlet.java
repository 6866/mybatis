package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yangxingrom on 2017/11/20.
 */
@WebServlet(name = "InitTalkServlet")
public class InitTalkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码
        request.setCharacterEncoding("UTF-8");
        // 向页面跳转
        request.getRequestDispatcher("/WEB-INF/front/talk.jsp").forward(request, response);
    }
}
