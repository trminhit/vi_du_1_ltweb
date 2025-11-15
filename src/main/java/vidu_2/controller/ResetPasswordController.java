package vidu_2.controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vidu_2.service.UserService;
import vidu_2.service.impl.UserServiceimpl;

@WebServlet(urlPatterns = "/reset-password")
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceimpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Hiển thị View 2 
		RequestDispatcher rd = req.getRequestDispatcher("/view/reset-password.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("resetEmail"); 
		
		String password = req.getParameter("password");
		String repassword = req.getParameter("repassword");

		if (email == null) {
			// Nếu session mất, đá về login
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		if (!password.equals(repassword)) {
			req.setAttribute("alert", "Mật khẩu nhập lại không khớp!");
			req.getRequestDispatcher("/view/reset-password.jsp").forward(req, resp);
			return;
		}
		
		// Gọi service để cập nhật
		userService.updatePassword(email, password); 
		
		// Xóa session
		session.removeAttribute("resetEmail");
		
		// Chuyển về trang login với thông báo thành công
		resp.sendRedirect(req.getContextPath() + "/login?alert=reset_success");
	}
}