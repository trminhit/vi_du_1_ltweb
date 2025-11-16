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
import java.io.File;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import vidu_2.util.Constant;

@WebServlet(urlPatterns = { "/admin/category/add" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) //50MB
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
		resp.setContentType("text/html;charset=UTF-8");

		Category category = new Category();
		try {
			String catename = req.getParameter("catename");
			category.setCatename(catename);
			
			Part part = req.getPart("icon");
			
			//Xử lý file
			String fileName = part.getSubmittedFileName();
			if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) {
				
	            // Tạo tên file mới duy nhất
	            String originalFileName = fileName;
	            int index = originalFileName.lastIndexOf(".");
	            String ext = originalFileName.substring(index + 1);
	            String newFileName = System.currentTimeMillis() + "." + ext;

	            // Tạo thư mục nếu chưa tồn tại
	            File uploadDir = new File(Constant.DIR + "/category");
	            if (!uploadDir.exists()) {
	                uploadDir.mkdirs();
	            }

	            // Ghi file vào thư mục
	            String uploadPath = Constant.DIR + "/category" + File.separator + newFileName;
	            part.write(uploadPath);

	            // Lưu đường dẫn tương đối vào DB
	            category.setIcon("category/" + newFileName);
	        }
			
			cateService.insert(category);

			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
		}catch (Exception e) {
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
//			String catename = req.getParameter("catename");
//			category.setCatename(catename);
//			
//			Part part = req.getPart("icon");
//			
//			//Xử lý file
//			String fileName = part.getSubmittedFileName();
//			if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) {
//				
//	            // Tạo tên file mới duy nhất
//	            String originalFileName = fileName;
//	            int index = originalFileName.lastIndexOf(".");
//	            String ext = originalFileName.substring(index + 1);
//	            String newFileName = System.currentTimeMillis() + "." + ext;
//
//	            // Lấy đường dẫn thật của thư mục "uploads"
//	            String realPath = req.getServletContext().getRealPath("/uploads");
//	            
//	            // Tạo thư mục /uploads/category nếu chưa có
//	            File uploadDir = new File(realPath + "/category");
//	            if (!uploadDir.exists()) {
//	                uploadDir.mkdirs();
//	            }
//
//	            // Ghi file vào đường dẫn thật
//	            String uploadPath = realPath + "/category" + File.separator + newFileName;
//	            part.write(uploadPath);
//
//	            // Lưu đường dẫn tương đối vào DB
//	            category.setIcon("category/" + newFileName);
//	        }
//			
//			cateService.insert(category);
//
//			resp.sendRedirect(req.getContextPath() + "/admin/category/list");
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
