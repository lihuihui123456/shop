package com.lzh.shopweb.facade;

import com.lzh.shopcommon.page.Page;
import com.lzh.shopentity.Category;
import com.lzh.shopservice.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CategoryFacade {
    @Autowired
    private CategoryManager categoryManager;
    public Category getCategory(Long categoryId) {
        return categoryManager.getCategory(categoryId);
    }
    public void update(Category category) {
        categoryManager.update(category);
    }

    public void insert(Category category) {
        categoryManager.insert(category);
    }
    public void delete(Long categoryId) {
        categoryManager.delete(categoryId);
    }

    public void batchDelete(List<Long> categoryIds) {
        categoryManager.batchDelete(categoryIds);
    }
    public List<Category> listCategory(Map<String, Object> filters) {
        return categoryManager.listCategory(filters);
    }
    public Page<Category> listCategory(Page<Category> page, Map<String, Object> filters) {
        return categoryManager.listCategory(page, filters);
    }
}
