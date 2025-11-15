package vidu_2.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vidu_2.service.CategoryService;
import vidu_2.service.impl.CategoryServiceimpl;

@WebServlet(urlPatterns = { "/admin/category/delete" })
public class CategoryDeleteController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	CategoryService cateService = new CategoryServiceimpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id"); 
				
		//Gọi Service để thực hiện xóa
		cateService.delete(Integer.parseInt(id)); 
		
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
