package com.test.organization.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

@Sql("/data.sql")
@SpringBootTest
public class RepositoryTest {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberDepartmentRepository memberDepartmentRepository;

    @Test
    public void checkBaseData() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(departmentRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(memberRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findByDepartment_Id(4L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findByDepartment_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.deleteByDepartment_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findByDepartment_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.count()));

        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findByMember_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.deleteByMember_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.findByMember_Id(11L)));
        System.out.println(objectMapper.writeValueAsString(memberDepartmentRepository.count()));
    }

}
