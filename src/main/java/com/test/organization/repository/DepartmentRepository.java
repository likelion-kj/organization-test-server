package com.test.organization.repository;

import com.test.organization.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    boolean existsByParentId(@NonNull Long parentId);

    boolean existsByCode(@NonNull String code);

    Optional<Department> findByCode(String code);
    List<Department> findByParentIdsStartingWithOrderByDepthAsc(@NonNull String parentIds);
}
