package thymeleaf.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import decorator.constant.Constant;
import decorator.entity.User;
import decorator.service.UserService;
import decorator.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserService userService = new UserServiceImpl();

    public RegisterController() {
        // TODO Auto-generated constructor stub
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
	}

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String username = req.getParameter("username");
        String fullname = req.getParameter("fullname");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        // upload avatar
        Part avatarPart = req.getPart("avatar");
        String avatarPath = null;
        if (avatarPart != null && avatarPart.getSize() > 0) {
            String fileName = Paths.get(avatarPart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/uploads/avatars");
            new File(uploadDir).mkdirs();
            avatarPart.write(uploadDir + File.separator + fileName);
            avatarPath = "uploads/avatars/" + fileName;
        }

        User u = new User();
        u.setUsername(username);
        u.setFullname(fullname);
        u.setPassword(password);
        u.setPhone(phone);
        u.setEmail(email);
        u.setAvatar(avatarPath);
        u.setRoleid(3); // default: User
        u.setActive(true);

        try {
            userService.register(u);
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (Exception e) {
            req.setAttribute("error", "Đăng ký thất bại: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
	}

}
