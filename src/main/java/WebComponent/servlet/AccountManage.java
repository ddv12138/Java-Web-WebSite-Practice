package WebComponent.servlet;

import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import passwdUtils.PasswdEncrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountManage extends BaseServlet {

    public void loginInValidate(HttpServletRequest request, HttpServletResponse response) {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            String EncPasswd = PasswdEncrypt.passwdEncrypt(passwd);
            UserMapper um = session.getMapper(UserMapper.class);
            User u = um.selectByName(username);
            boolean login = false;
            if (u.getName().equals(username)) {
                login = true;
            }
            response.getWriter().println(login);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signupValidate(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
    }
}
