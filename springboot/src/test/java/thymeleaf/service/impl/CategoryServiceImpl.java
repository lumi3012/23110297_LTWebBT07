package thymeleaf.service.impl;


import java.util.List;

import thymeleaf.dao.CategoryDao;
import thymeleaf.dao.impl.CategoryDaoImpl;
import thymeleaf.service.CategoryService;
import thymeleaf.entity.Category;

public class CategoryServiceImpl implements CategoryService {
	
    private CategoryDao dao = new CategoryDaoImpl();

    @Override
    public List<Category> list() {
        return dao.findAll();
    }

    @Override
    public Category create(Category c) {
        if (dao.findByName(c.getName()) != null) {
            throw new RuntimeException("category.exists");
        }
        return dao.create(c);
    }

    @Override
    public Category update(Category c) {
        return dao.update(c);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    
    @Override
    public Category findById(Long id) {
        return dao.findById(id);
    }
    
    @Override
    public List<Category> search(String q) {
        return dao.search(q);
    }
}