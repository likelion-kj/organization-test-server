package com.test.organization.controller;

import com.test.organization.entity.MemberDepartment;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.DepartmentRepository;
import com.test.organization.repository.MemberDepartmentRepository;
import com.test.organization.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class MemberDepartmentController {
    private final MemberRepository memberRepository;
    private final MemberDepartmentRepository memberDepartmentRepository;
    private final DepartmentRepository departmentRepository;

    @PostMapping("/org/member/{memberId}/dept")
    @Transactional
    public void insert(@PathVariable Long memberId, @RequestBody InsertRequestBody body) {
        if (body.departmentId == null || body.managerYn == null) {
            throw new BadRequestException("입력 파라미터 오류.");
        }

        memberRepository.findById(memberId).orElseThrow(() -> new BadRequestException("해당 회원 id를 찾을 수 없습니다."));
        departmentRepository.findById(body.departmentId).orElseThrow(() -> new BadRequestException("해당 부서 id를 찾을 수 없습니다."));

        if (!memberDepartmentRepository.existsByMember_IdAndDepartment_Id(memberId, body.departmentId)) {
            throw new BadRequestException("이미 존재하는 정보입니다.");
        }

        MemberDepartment memberDepartment = new MemberDepartment();
        memberDepartment.setMember(memberRepository.getById(memberId));
        memberDepartment.setDepartment(departmentRepository.getById(body.departmentId));
        memberDepartment.setManager(body.managerYn);
        memberDepartmentRepository.save(memberDepartment);
    }

    @Data
    public static class InsertRequestBody {
        private Long departmentId;
        private Boolean managerYn;
    }

    @PutMapping("/org/member/{memberId}/dept/{departmentId}")
    @Transactional
    public void update(@PathVariable Long memberId, @PathVariable Long departmentId, @RequestBody UpdateRequestBody body) {
        if (body.managerYn == null) {
            throw new BadRequestException("입력 파라미터 오류.");
        }

        MemberDepartment memberDepartment = memberDepartmentRepository.findByMember_IdAndDepartment_Id(
                memberId, departmentId).orElseThrow(() -> new BadRequestException("해당 회원 id의 부서 정보를 찾을 수 없습니다."));
        memberDepartment.setManager(body.managerYn);
        memberDepartmentRepository.save(memberDepartment);
    }

    @Data
    public static class UpdateRequestBody {
        private Boolean managerYn;
    }

    @DeleteMapping("/org/member/{memberId}/dept/{departmentId}")
    public void delete(@PathVariable Long memberId, @PathVariable Long departmentId) {
        if (memberDepartmentRepository.deleteByMember_IdAndDepartment_Id(memberId, departmentId) == 0) {
            throw new BadRequestException("해당 회원 id의 부서 정보를 찾을 수 없습니다.");
        }
    }
}
