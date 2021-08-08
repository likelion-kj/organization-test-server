package com.test.organization.controller;

import com.test.organization.entity.Department;
import com.test.organization.entity.enums.DepartmentType;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.DepartmentRepository;
import com.test.organization.repository.MemberDepartmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class DepartmentController {
    private final DepartmentRepository departmentRepository;
    private final MemberDepartmentRepository memberDepartmentRepository;

    @PostMapping("/org/dept")
    @Transactional
    public Long insertDepartment(@RequestBody InsertDepartmentRequestBody body) {
        if (body.parentId == null || body.type == null || isEmpty(body.code) || isEmpty(body.name)) {
            throw new BadRequestException("입력 파라미터 오류.");
        }
        if (body.getType() == DepartmentType.COMPANY) {
            throw new BadRequestException("회사는 추가가 불가능 합니다.");
        }

        if (departmentRepository.existsByCode(body.getCode())) {
            throw new BadRequestException("이미 사용되고 있는 code 입니다.");
        }

        Department parentDepartment = departmentRepository.findById(body.parentId).orElseThrow(() -> new BadRequestException("상위 부서 id를 찾을 수 없습니다."));
        if (parentDepartment.getType().ordinal() > body.type.ordinal()) {
            throw new BadRequestException("상위 부서 보다 큰 규모를 설정할 수 없습니다.");
        }
        Department department = new Department();
        department.setDepth(parentDepartment.getDepth() + 1);
        department.setParentId(parentDepartment.getId());
        department.setParentIds(isEmpty(parentDepartment.getParentIds())
                ? String.valueOf(parentDepartment.getId())
                : parentDepartment.getParentIds() + "," + parentDepartment.getId());
        department.setType(body.type);
        department.setName(body.name);
        department.setCode(body.code);
        return departmentRepository.save(department).getId();
    }

    @Data
    public static class InsertDepartmentRequestBody {
        private Long parentId;
        private DepartmentType type;
        private String name;
        private String code;
    }

    @PutMapping("/org/dept/{deptId}")
    @Transactional
    public Long updateDepartment(@PathVariable Long deptId, @RequestBody UpdateDepartmentRequestBody body) {
        Department department = departmentRepository.findById(deptId).orElseThrow(() -> new BadRequestException("해당 id를 찾을 수 없습니다."));
        if (isEmpty(body.code) && isEmpty(body.name)) {
            throw new BadRequestException("변경할 내용이 존재하지 않습니다.");
        }

        if (!isEmpty(body.code)) {
            if (departmentRepository.existsByCode(body.getCode())) {
                throw new BadRequestException("이미 사용되고 있는 code 입니다.");
            } else {
                department.setCode(body.code);
            }
        }

        if (!isEmpty(body.name)) {
            department.setName(body.name);
        }
        departmentRepository.save(department);

        return deptId;
    }

    @Data
    public static class UpdateDepartmentRequestBody {
        private String name;
        private String code;
    }

    @DeleteMapping("/org/dept/{deptId}")
    @Transactional
    public Long deleteDepartment(@PathVariable Long deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow(() -> new BadRequestException("해당 id를 찾을 수 없습니다."));
        if (department.getType() == DepartmentType.COMPANY) {
            throw new BadRequestException("회사는 삭제가 불가능 합니다.");
        }
        if (departmentRepository.existsByParentId(department.getId())) {
            throw new BadRequestException("하위 부서를 가지고 있으면 삭제를 할 수 없습니다.");
        }
        Long deletedMemberCount = memberDepartmentRepository.deleteByDepartment_Id(department.getId());
        departmentRepository.delete(department);
        return deletedMemberCount;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
