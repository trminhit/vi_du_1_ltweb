package vidu_2.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vidu_2.service.impl.CategoryServiceimpl;
import vidu_2.model.Category;
import vidu_2.service.CategoryService;


@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	CategoryService cateService = new CategoryServiceimpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Category category = cateService.get(Integer.parseInt(id));
		req.setAttribute("category", category);
		RequestDispatcher rd = req.getRequestDispatcher("/view/admin/edit-category.jsp");
		rd.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		String id = req.getParameter("id"); 
		String catename = req.getParameter("catename");
		
		Category category = new Category();
		category.setCateid(Integer.parseInt(id));
		category.setCatename(catename);
		cateService.edit(category);
		
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
