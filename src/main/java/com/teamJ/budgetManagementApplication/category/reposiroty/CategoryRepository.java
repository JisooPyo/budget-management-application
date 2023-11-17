package com.teamJ.budgetManagementApplication.category.reposiroty;

import com.teamJ.budgetManagementApplication.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByIdAsc();

    Optional<Category> findByName(String name);
}
