package vidu_2.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vidu_2.model.Category;
import vidu_2.service.CategoryService;
import vidu_2.service.impl.CategoryServiceimpl;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CategoryService cateService = new CategoryServiceimpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/view/admin/add-category.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String catename = req.getParameter("catename");

		Category category = new Category();
		
		category.setCatename(catename);

		cateService.insert(category);

		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
