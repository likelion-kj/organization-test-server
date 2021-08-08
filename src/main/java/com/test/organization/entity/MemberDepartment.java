package com.test.organization.entity;

import com.test.organization.entity.convert.BooleanToYNConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "member_department")
public class MemberDepartment {
    @Id
    @Column(name = "seq", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "MANAGER_YN")
    @Convert(converter = BooleanToYNConverter.class)
    private boolean manager;
}
