package com.test.organization.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.test.organization.entity.Department;
import com.test.organization.entity.MemberDepartment;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.DepartmentRepository;
import com.test.organization.repository.MemberDepartmentRepository;
import com.test.organization.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SearchOrganizationController {
    @Autowired
    private final DepartmentRepository departmentRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberDepartmentRepository memberDepartmentRepository;

    @GetMapping({"/org/organizations", "/api/organizations"})
    public Organization searchOrganizations(SearchOrganizationsRequestBody body) {

        if (isEmpty(body.deptCode)) {
            body.deptCode = null;
        }

        if ("dept".equals(body.searchType)) {
            //TODO searchKeyword 에 대해서 부서명을 검색하여 검색된 부서들과 각 상위 부서 정보를 모아서 결과를 응답
            return null;
        } else if ("member".equals(body.searchType)) {
            //TODO searchKeyword 에 대해서 부서원명을 검색하여 검색된 부서원과 부서원이 속한 부서, 관계된 상위 부서를 포함한 트리 구조로 응답
            return null;
        } else {
            Department firstDepartment = departmentRepository.findByCode(body.deptCode).orElseThrow(() -> new BadRequestException("해당 부서 코드를 찾지 못 햇습니다."));
            Organization first = Organization.of(firstDepartment);
            List<Department> departments = departmentRepository.findByParentIdsStartingWithOrderByDepthAsc(getNextParentId(firstDepartment));

            Map<String, Organization> parentOrganization = new HashMap<>();
            parentOrganization.put(getNextParentId(firstDepartment), first);

            for (Department department : departments) {
                Organization child = Organization.of(department);
                parentOrganization.get(department.getParentIds()).children.add(child);
                parentOrganization.put(getNextParentId(department), child);
            }

            if (body.deptOnly != null && !body.deptOnly) {
                for (Organization organization : parentOrganization.values()) {
                    for (MemberDepartment member : memberDepartmentRepository.findByDepartment_Id(organization.id)) {
                        organization.children.add(Organization.of(member));
                    }
                }
            }
            return first;
        }
    }

    @Data
    public static class SearchOrganizationsRequestBody {
        private String deptCode;
        private Boolean deptOnly;
        private String searchType;
        private String searchKeyword;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Organization {
        private Long id;
        private String type;
        private String name;
        private String code;
        private Boolean manager;
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private List<Organization> children = new ArrayList<>();

        public static Organization of(Department department) {
            Organization org = new Organization();
            org.setId(department.getId());
            org.setName(department.getName());

            switch (department.getType()) {
                case COMPANY:
                    org.setType("Company");
                    break;
                case DIVISION:
                    org.setType("Division");
                    org.setCode(department.getCode());
                    break;
                case DEPARTMENT:
                    org.setType("Department");
                    org.setCode(department.getCode());
                    break;
            }
            return org;
        }

        public static Organization of(MemberDepartment member) {
            Organization org = new Organization();
            org.setType("Member");
            org.setId(member.getMember().getId());
            org.setManager(member.isManager());
            org.setName(member.getMember().getName());
            return org;
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    private String getNextParentId(Department department) {
        return isEmpty(department.getParentIds())
                ? String.valueOf(department.getId())
                : department.getParentIds() + "," + department.getId();
    }
}
