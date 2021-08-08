package com.test.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;
}
