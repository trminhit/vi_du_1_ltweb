package vidu_2.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vidu_2.model.Category;
import vidu_2.service.CategoryService;
import vidu_2.service.impl.CategoryServiceimpl;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CategoryService cateService = new CategoryServiceimpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> cateList = cateService.getAll();

		// Đẩy danh sách (List) này ra View (JSP)
		req.setAttribute("cateList", cateList);

		RequestDispatcher rd = req.getRequestDispatcher("/view/admin/list-category.jsp");
		rd.forward(req, resp);
	}

}
