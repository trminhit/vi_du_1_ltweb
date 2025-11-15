package vidu_2.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vidu_2.model.User;
import vidu_2.service.UserService;
import vidu_2.service.impl.UserServiceimpl;

@WebServlet(urlPatterns = "/forgot-password")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceimpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Hiển thị View 1 
		RequestDispatcher rd = req.getRequestDispatcher("/view/forgot-password.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		User user = userService.getByEmail(email); 

		if (user == null) {
			// Email không tồn tại, báo lỗi quay lại
			req.setAttribute("alert", "Email không tồn tại trong hệ thống!");
			req.getRequestDispatcher("/view/forgot-password.jsp").forward(req, resp);
		} else {
			// Email tồn tại, lưu vào session và chuyển sang View 2
			HttpSession session = req.getSession();
			session.setAttribute("resetEmail", email); 
			resp.sendRedirect(req.getContextPath() + "/reset-password");
		}
	}
}