package com.test.organization.repository;

import com.test.organization.entity.MemberDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberDepartmentRepository extends JpaRepository<MemberDepartment, Long> {
    List<MemberDepartment> findByMember_Id(@NonNull Long id);
    List<MemberDepartment> findByDepartment_Id(@NonNull Long id);
    @Transactional
    long deleteByMember_Id(@NonNull Long id);
    @Transactional
    long deleteByDepartment_Id(@NonNull Long id);

    Optional<MemberDepartment> findByMember_IdAndDepartment_Id(@NonNull Long memberId, @NonNull Long departmentId);
    boolean existsByMember_IdAndDepartment_Id(@NonNull Long memberId, @NonNull Long departmentId);
    @Transactional
    long deleteByMember_IdAndDepartment_Id(@NonNull Long memberId, @NonNull Long departmentId);
}
