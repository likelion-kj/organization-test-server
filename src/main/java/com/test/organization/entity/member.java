package com.test.organization.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

}
