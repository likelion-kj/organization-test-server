package com.test.organization.controller;

import com.test.organization.entity.Department;
import com.test.organization.entity.Member;
import com.test.organization.entity.MemberDepartment;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.DepartmentRepository;
import com.test.organization.repository.MemberDepartmentRepository;
import com.test.organization.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class MemberDepartmentControllerTest {

    private final MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    private final MemberDepartmentRepository memberDepartmentRepository = Mockito.mock(MemberDepartmentRepository.class);
    private final DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);

    private final MemberDepartmentController controller = new MemberDepartmentController(memberRepository, memberDepartmentRepository, departmentRepository);

    @Test
    void insert_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insert(1L, new MemberDepartmentController.InsertRequestBody());
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insert(1L, new MemberDepartmentController.InsertRequestBody() {{
                setDepartmentId(1L);
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.empty());
            controller.insert(1L, new MemberDepartmentController.InsertRequestBody() {{
                setDepartmentId(1L);
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(new Member()));
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
            controller.insert(1L, new MemberDepartmentController.InsertRequestBody() {{
                setDepartmentId(1L);
                setManagerYn(true);
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(new Member()));
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department()));
            Mockito.when(memberDepartmentRepository.existsByMember_IdAndDepartment_Id(1L, 1L)).thenReturn(false);
            controller.insert(1L, new MemberDepartmentController.InsertRequestBody() {{
                setDepartmentId(1L);
                setManagerYn(true);
            }});
        });
    }

    @Test
    void insert_success() {
        Member member = new Member() {{
            setId(1L);
        }};
        Department department = new Department() {{
            setId(1L);
        }};

        Mockito.when(memberDepartmentRepository.save(Mockito.any(MemberDepartment.class))).thenReturn(new MemberDepartment() {{
            setMember(member);
            setDepartment(department);
        }});

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(memberDepartmentRepository.existsByMember_IdAndDepartment_Id(1L, 1L)).thenReturn(true);
        controller.insert(1L, new MemberDepartmentController.InsertRequestBody() {{
            setDepartmentId(1L);
            setManagerYn(true);
        }});

        Mockito.verify(memberDepartmentRepository).save(Mockito.any(MemberDepartment.class));
    }

    @Test
    void update_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.update(1L, 1L, new MemberDepartmentController.UpdateRequestBody());
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            MemberDepartment memberDepartment = new MemberDepartment() {{
                setSeq(1L);
            }};
            Mockito.when(memberDepartmentRepository.findByMember_IdAndDepartment_Id(1L, 1L)).thenReturn(Optional.empty());
            controller.update(1L, 1L, new MemberDepartmentController.UpdateRequestBody() {{
                setManagerYn(true);
            }});
        });

    }

    @Test
    void update_success() {
        MemberDepartment memberDepartment = new MemberDepartment() {{
            setSeq(1L);
        }};
        Mockito.when(memberDepartmentRepository.findByMember_IdAndDepartment_Id(1L,1L)).thenReturn(Optional.of(memberDepartment));
        controller.update(1L, 1L, new MemberDepartmentController.UpdateRequestBody() {{
            setManagerYn(true);
        }});

        Mockito.verify(memberDepartmentRepository).save(memberDepartment);
    }

    @Test
    void delete_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(memberDepartmentRepository.deleteByMember_IdAndDepartment_Id(1L, 1L)).thenReturn(0L);
            controller.delete(1L, 1L);
        });
    }

    @Test
    void delete_success() {
        MemberDepartment memberDepartment = new MemberDepartment() {{
            setMember(new Member());
            setDepartment(new Department());
        }};
        Mockito.when(memberDepartmentRepository.deleteByMember_IdAndDepartment_Id(1L, 1L)).thenReturn(1L);

        controller.delete(1L, 1L);
        Mockito.verify(memberDepartmentRepository).deleteByMember_IdAndDepartment_Id(1L, 1L);
    }
}