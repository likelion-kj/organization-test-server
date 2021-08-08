package com.test.organization.repository;

import com.test.organization.entity.MemberOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberOrganizationRepository extends JpaRepository<MemberOrganization, MemberOrganization.MemberOrganizationId> {
}
