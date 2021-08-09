package com.test.organization.controller;

import com.test.organization.entity.Member;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.MemberDepartmentRepository;
import com.test.organization.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberDepartmentRepository memberDepartmentRepository;

    @PostMapping("/org/member")
    @Transactional
    public Long insertMember(@RequestBody MemberController.InsertMemberRequestBody body) {
        if (isEmpty(body.name)) {
            throw new BadRequestException("입력 파라미터 오류.");
        }

        Member member = new Member();
        member.setName(body.name);
        return memberRepository.save(member).getId();
    }

    @Data
    public static class InsertMemberRequestBody {
        private String name;
    }

    @PutMapping("/org/member/{memberId}")
    @Transactional
    public Long updateMember(@PathVariable Long memberId, @RequestBody MemberController.UpdateMemberRequestBody body) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BadRequestException("해당 id를 찾을 수 없습니다."));
        if (isEmpty(body.name)) {
            throw new BadRequestException("변경할 내용이 존재하지 않습니다.");
        }

        member.setName(body.name);
        memberRepository.save(member);
        return memberId;
    }

    @Data
    public static class UpdateMemberRequestBody {
        private String name;
    }

    @DeleteMapping("/org/member/{memberId}")
    @Transactional
    public Long deleteMember(@PathVariable Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new BadRequestException("해당 id를 찾을 수 없습니다."));

        memberDepartmentRepository.deleteByMember_Id(member.getId());
        memberRepository.delete(member);
        return memberId;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
