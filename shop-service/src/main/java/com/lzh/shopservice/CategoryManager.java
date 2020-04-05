package com.lzh.shopservice;

import com.lzh.shopcommon.page.Page;
import com.lzh.shop.dao.CategoryDao;
import com.lzh.shopentity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CategoryManager {
    @Autowired
    private CategoryDao categoryDao;
    public Category getCategory(Long categoryId) {
        return categoryDao.get(categoryId);
    }
    public void update(Category category) {
        categoryDao.update(category);
    }

    public void insert(Category category) {
        categoryDao.insert(category);
    }
    public void delete(Long categoryId) {
        categoryDao.delete(categoryId);
    }

    public void batchDelete(List<Long> categoryIds) {
        categoryDao.batchDelete(categoryIds);
    }
    public List<Category> listCategory(Map<String, Object> filters) {
        return categoryDao.findListForMap(filters);
    }
    public Page<Category> listCategory(Page<Category> page, Map<String, Object> filters) {
        return categoryDao.findPage(page,"findListForMap", filters);
    }
}
