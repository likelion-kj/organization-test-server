package com.test.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DEPARTMENT2")
public class Department2 {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "DEPARTMENT1_ID")
    private Long department1Id;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "CODE")
    private String code;

}
