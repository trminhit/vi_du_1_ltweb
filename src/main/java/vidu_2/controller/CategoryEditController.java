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

import java.io.File;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import vidu_2.util.Constant;


@WebServlet(urlPatterns = { "/admin/category/edit" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
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
		resp.setContentType("text/html;charset=UTF-8");

		Category category = new Category();

		try {
			// Lấy các trường id và name
			int id = Integer.parseInt(req.getParameter("id"));
			String catename = req.getParameter("catename");
			
			category.setCateid(id);
			category.setCatename(catename);

			// Lấy trường 'icon' (dạng file)
			Part part = req.getPart("icon");
			
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) { 				
				// Xử lý ghi file mới
				String originalFileName = fileName;
				int index = originalFileName.lastIndexOf(".");
				String ext = originalFileName.substring(index + 1);
				String newFileName = System.currentTimeMillis() + "." + ext;
				
				File uploadDir = new File(Constant.DIR + "/category");
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}
				
				String uploadPath = Constant.DIR + "/category" + File.separator + newFileName;
				part.write(uploadPath); // Lưu file mới

				category.setIcon("category/" + newFileName); // Đặt tên file mới
				
			} else {
				// Nếu không upload file mới, set là null
				category.setIcon(null);
			}

			cateService.edit(category);
			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html;charset=UTF-8");
//
//		Category category = new Category();
//		try {
//			int id = Integer.parseInt(req.getParameter("id"));
//			String catename = req.getParameter("catename");
//			
//			category.setCateid(id);
//			category.setCatename(catename);
//			
//			// Lấy đường dẫn thật của thư mục "uploads"
//	        String realPath = req.getServletContext().getRealPath("/uploads");
//
//			Part part = req.getPart("icon");
//			String fileName = part.getSubmittedFileName();
//			
//			if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) { 	
//				// Logic chuyển từ Service về
//				Category oldCategory = cateService.get(id); // Lấy thông tin cũ
//				if (oldCategory.getIcon() != null && !oldCategory.getIcon().isEmpty()) {
//					File oldFile = new File(realPath + File.separator + oldCategory.getIcon());
//					if (oldFile.exists()) {
//						oldFile.delete();
//					}
//				}
//				
//				// Lưu file mới
//				String originalFileName = fileName;
//				int index = originalFileName.lastIndexOf(".");
//				String ext = originalFileName.substring(index + 1);
//				String newFileName = System.currentTimeMillis() + "." + ext;
//				
//				File uploadDir = new File(realPath + "/category");
//				if (!uploadDir.exists()) {
//					uploadDir.mkdirs();
//				}
//				
//				String uploadPath = realPath + "/category" + File.separator + newFileName;
//				part.write(uploadPath); 
//				category.setIcon("category/" + newFileName); 
//				
//			} else {
//				// Nếu không upload file mới, giữ lại icon cũ từ DB
//				category.setIcon(cateService.get(id).getIcon());
//			}
//
//			cateService.edit(category); 
//			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
