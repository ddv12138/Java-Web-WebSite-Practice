package WebComponent.servlet;

import ORM.Mapper.UserMapper;
import ORM.POJO.User;
import globalUtils.CommonResult;
import globalUtils.DataBaseManage;
import org.apache.ibatis.session.SqlSession;
import passwdUtils.PasswdEncrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountManage extends BaseServlet {

    public void loginValidate(HttpServletRequest request, HttpServletResponse response) {
        try (SqlSession session = DataBaseManage.getSqlSessionFactory().openSession()) {
            String username = request.getParameter("username");
            String passwd = request.getParameter("passwd");
            String data = request.getParameter("data");
            String EncPasswd = PasswdEncrypt.passwdEncrypt(passwd);
            UserMapper um = session.getMapper(UserMapper.class);
            User u = um.selectByName(username);
            if (u.getName().equals(username) && u.getPassword().equals(EncPasswd)) {
                response.getWriter().println(new CommonResult(true, "登录成功", null));
            } else {
                response.getWriter().println(new CommonResult(false, "登陆失败", null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signupValidate(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String passwd = request.getParameter("passwd");
    }
}
