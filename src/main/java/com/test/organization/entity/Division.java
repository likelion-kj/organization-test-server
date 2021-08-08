package com.test.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DIVISION")
public class Division {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "CODE")
    private String code;
}
