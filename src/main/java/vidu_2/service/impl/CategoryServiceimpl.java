package vidu_2.service.impl;

import java.io.File;
import java.util.List;

import vidu_2.dao.CategoryDao;
import vidu_2.dao.impl.CategoryDaoimpl;
import vidu_2.model.Category;
import vidu_2.service.CategoryService;
import vidu_2.util.Constant;

public class CategoryServiceimpl implements CategoryService{
	CategoryDao categoryDao = new CategoryDaoimpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}
	@Override
	public void edit(Category newCategory) {
		
		// Lấy thông tin category cũ từ DB
		Category oldCategory = categoryDao.get(newCategory.getCateid());

		// Cập nhật tên 
		oldCategory.setCatename(newCategory.getCatename());

		// Kiểm tra xem Controller có gửi lên icon mới không
		if (newCategory.getIcon() != null) {
			
			// Có gửi icon mới (xóa ảnh cũ)
			String oldIconName = oldCategory.getIcon();
			
			// Kiểm tra xem có ảnh cũ không
			if (oldIconName != null && !oldIconName.isEmpty()) {
				File oldFile = new File(Constant.DIR + File.separator + oldIconName);
				if (oldFile.exists()) {
					oldFile.delete(); // Xóa file
				}
			}
			
			// Cập nhật icon mới vào đối tượng oldCategory
			oldCategory.setIcon(newCategory.getIcon());
		}
		
		categoryDao.edit(oldCategory);
	}
//	@Override
//	public void edit(Category newCategory) {
//		
//		Category oldCategory = categoryDao.get(newCategory.getCateid());
//
//		oldCategory.setCatename(newCategory.getCatename());
//		oldCategory.setIcon(newCategory.getIcon()); 
//		
//		categoryDao.edit(oldCategory);
//	}
	@Override
	public void delete(int id) {
		categoryDao.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDao.get(id);
	}
	@Override
	public Category get(String name) {
		return categoryDao.get(name);
	}


	@Override
	public List<Category> getAll() {
		return categoryDao.getAll();
	}


	@Override
	public List<Category> search(String keyword) {
		return categoryDao.search(keyword);
	}
}
