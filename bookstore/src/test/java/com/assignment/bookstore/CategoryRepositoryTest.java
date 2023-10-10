package com.assignment.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.bookstore.domain.Category;
import com.assignment.bookstore.domain.CategoryRepository;

@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository cRepository;

	@Test
	public void findCategoryByNameShouldReturnCategory() {
		// Realism is the first category created, so its Id is 1
		List<Category> categories = cRepository.findByName("Realism");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getCategoryId()).isEqualTo(1);
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Action");
		cRepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test
	public void deleteCategory() {
		Category category = new Category("Action");
		cRepository.save(category);

		cRepository.deleteById(category.getCategoryId());
	}

}
