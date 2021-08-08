package com.test.organization.repository;

import com.test.organization.entity.MemberDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface MemberDepartmentRepository extends JpaRepository<MemberDepartment, Long> {
    List<MemberDepartment> findAllByDepartmentId(Long departmentId);
}
