package thymeleaf.dao;

import java.util.List;

import thymeleaf.entity.Category;

public interface CategoryDao {
	List<Category> findAll();
    Category findById(Long id);
    Category findByName(String name);
    Category create(Category c);
    Category update(Category c);
    void delete(Long id);
	List<Category> search(String keyword);
}
