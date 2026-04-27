package fr.univ.escaladeclub.climbingclub.service;

import fr.univ.escaladeclub.climbingclub.entity.Category;
import fr.univ.escaladeclub.climbingclub.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public Category update(Long id, Category updatedCategory) {
        Category existing = findById(id);
        existing.setName(updatedCategory.getName());
        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
