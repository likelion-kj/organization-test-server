package com.test.organization.entity;

import com.test.organization.entity.convert.BooleanToYNConverter;
import com.test.organization.entity.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "MEMBER_ORGANIZATION")
@IdClass(MemberOrganization.MemberOrganizationId.class)
public class MemberOrganization {

    @Id
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Id
    @Column(name = "ORGANIZATION_TYPE")
    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    @Id
    @Column(name = "ORGANIZATION_ID")
    private Long organizationId;

    @Column(name = "MANAGER_YN")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean manager;

    @Data
    @AllArgsConstructor
    public static class MemberOrganizationId implements Serializable {
        private Long memberId;
        private OrganizationType organizationType;
        private Long organizationId;
    }
}
