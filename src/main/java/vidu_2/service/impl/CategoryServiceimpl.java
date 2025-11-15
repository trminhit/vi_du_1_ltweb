package vidu_2.service.impl;

import java.util.List;

import vidu_2.dao.CategoryDao;
import vidu_2.dao.impl.CategoryDaoimpl;
import vidu_2.model.Category;
import vidu_2.service.CategoryService;

public class CategoryServiceimpl implements CategoryService{
	CategoryDao categoryDao = new CategoryDaoimpl();

	@Override
	public void insert(Category category) {
		categoryDao.insert(category);
	}
	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDao.get(newCategory.getCateid());
		
		oldCategory.setCatename(newCategory.getCatename()); 
		if (newCategory.getIcon() != null) {
			oldCategory.setIcon(newCategory.getIcon()); 
		}
		categoryDao.edit(oldCategory); 
	}
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
