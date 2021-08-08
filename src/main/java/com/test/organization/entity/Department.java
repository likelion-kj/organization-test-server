package com.test.organization.entity;

import com.test.organization.entity.enums.DepartmentType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "depth")
    private Long depth;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private DepartmentType type;

    @Column(name = "name")
    private String name;
    
    @Column(name = "code")
    private String code;

}
