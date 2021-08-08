package com.test.organization.repository;

import com.test.organization.entity.MemberDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.transaction.Transactional;
import java.util.List;

public interface MemberDepartmentRepository extends JpaRepository<MemberDepartment, Long> {
    List<MemberDepartment> findByMember_Id(@NonNull Long id);
    List<MemberDepartment> findByDepartment_Id(@NonNull Long id);
    @Transactional
    long deleteByMember_Id(@NonNull Long id);
    @Transactional
    long deleteByDepartment_Id(@NonNull Long id);
}
