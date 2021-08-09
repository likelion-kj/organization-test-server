package com.test.organization.controller;

import com.test.organization.entity.Member;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.MemberDepartmentRepository;
import com.test.organization.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class MemberControllerTest {

    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private final MemberDepartmentRepository memberDepartmentRepository = Mockito.mock(MemberDepartmentRepository.class);

    private final MemberController controller = new MemberController(memberRepository, memberDepartmentRepository);

    @Test
    void insertMember_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertMember(new MemberController.InsertMemberRequestBody());
        });
    }
    
    @Test
    void insertMember_success() {
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(new Member() {{
            setId(1L);
            setName("name");
        }});

        controller.insertMember(new MemberController.InsertMemberRequestBody() {{
            setName("name");
        }});

        Mockito.verify(memberRepository).save(Mockito.any(Member.class));
    }
    
    @Test
    void updateMember_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.empty());
            controller.updateMember(1L, new MemberController.UpdateMemberRequestBody());
        });
    }

    @Test
    void updateMember_success_update_name() {
        Member member = new Member() {{
            setId(1L);
        }};

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        controller.updateMember(1L, new MemberController.UpdateMemberRequestBody() {{
            setName("name");
        }});

        Mockito.verify(memberRepository).save(member);
    }
    
    @Test
    void deleteMember_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
           Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.empty());
           controller.deleteMember(1L);
        });
    }

    @Test
    void deleteMember_success() {
        Member member = new Member() {{
            setId(1L);
        }};
        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        
        controller.deleteMember(1L);
        
        Mockito.verify(memberDepartmentRepository).deleteByMember_Id(member.getId());
        Mockito.verify(memberRepository).delete(member);
    }
}