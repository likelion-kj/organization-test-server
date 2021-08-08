package com.test.organization.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.organization.entity.MemberOrganization;
import com.test.organization.entity.enums.OrganizationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql("/data.sql")
@SpringBootTest
public class RepositoryTest {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private Department1Repository department1Repository;

    @Autowired
    private Department2Repository department2Repository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberOrganizationRepository memberOrganizationRepository;

    @Test
    public void checkBaseData() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(companyRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(divisionRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(department1Repository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(department2Repository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(memberRepository.findById(1L).get()));
        System.out.println(objectMapper.writeValueAsString(memberOrganizationRepository.findById(new MemberOrganization.MemberOrganizationId(1L, OrganizationType.COMPANY, 1L)).get()));
    }

}
