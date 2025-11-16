package vidu_2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import vidu_2.util.Constant; 


@WebServlet(urlPatterns = "/image") 
public class DownloadImageController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String fileName = req.getParameter("fname");

		if (fileName == null || fileName.isEmpty()) {
			return; // Không có tên file, không làm gì cả
		}

		// Lấy đường dẫn tuyệt đối của file 
		File file = new File(Constant.DIR + File.separator + fileName);

		resp.setContentType("image/jpeg"); // (Giả sử là ảnh jpeg)

		if (file.exists()) {
			// Nếu file tồn tại, đọc file và trả về response
			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
		}
	}
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String fileName = req.getParameter("fname");
//		if (fileName == null || fileName.isEmpty()) {
//			return; // Không có không làm j hết
//		}
//
//		// Lấy đường dẫn thật của thư mục "uploads"
//        String realPath = req.getServletContext().getRealPath("/uploads");
//        
//        // Tìm file trong đường dẫn thật
//		File file = new File(realPath + File.separator + fileName);
//
//		resp.setContentType("image/jpeg"); 
//
//		if (file.exists()) {
//			IOUtils.copy(new FileInputStream(file), resp.getOutputStream());
//		}
//	}
}