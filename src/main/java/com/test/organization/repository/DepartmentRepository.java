package com.test.organization.repository;

import com.test.organization.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByParentId(@NonNull Long parentId);

    boolean existsByCode(@NonNull String code);

}
