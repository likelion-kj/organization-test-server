package com.test.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DEPARTMENT1")
public class Department1 {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "DIVISION_ID")
    private Long divisionId;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "CODE")
    private String code;
}
