package com.test.organization.controller;

import com.test.organization.entity.Department;
import com.test.organization.entity.enums.DepartmentType;
import com.test.organization.exception.BadRequestException;
import com.test.organization.repository.DepartmentRepository;
import com.test.organization.repository.MemberDepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

class DepartmentControllerTest {

    private final DepartmentRepository departmentRepository = Mockito.mock(DepartmentRepository.class);
    private final MemberDepartmentRepository memberDepartmentRepository = Mockito.mock(MemberDepartmentRepository.class);

    private final DepartmentController controller = new DepartmentController(departmentRepository, memberDepartmentRepository);

    @Test
    void insertDepartment_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody());
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.COMPANY);
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.COMPANY);
                setName("name");
            }});
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.COMPANY);
                setName("name");
                setCode("code");
            }});
        });

        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.existsByCode("code")).thenReturn(true);
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.DEPARTMENT);
                setName("name");
                setCode("code");
            }});
        });

        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.existsByCode("code")).thenReturn(false);
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.DEPARTMENT);
                setName("name");
                setCode("code");
            }});
        });

        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.existsByCode("code")).thenReturn(false);
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department() {{
                setType(DepartmentType.DEPARTMENT);
            }}));
            controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
                setParentId(1L);
                setType(DepartmentType.DIVISION);
                setName("name");
                setCode("code");
            }});
        });
    }

    @Test
    void insertDepartment_success() {
        Mockito.when(departmentRepository.existsByCode("code")).thenReturn(false);
        Mockito.when(departmentRepository.findById(2L)).thenReturn(Optional.of(new Department() {{
            setType(DepartmentType.DIVISION);
            setDepth(1);
            setParentId(1L);
            setParentIds("1");
        }}));
        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(new Department() {{
            setParentId(2L);
            setType(DepartmentType.DEPARTMENT);
            setName("name");
            setCode("code");
            setId(3L);
        }});

        controller.insertDepartment(new DepartmentController.InsertDepartmentRequestBody() {{
            setParentId(2L);
            setType(DepartmentType.DEPARTMENT);
            setName("name");
            setCode("code");
        }});

        Mockito.verify(departmentRepository).save(Mockito.any(Department.class));
    }

    @Test
    void updateDepartment_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
            controller.updateDepartment(1L, new DepartmentController.UpdateDepartmentRequestBody());
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department() {{
                setId(1L);
            }}));
            controller.updateDepartment(1L, new DepartmentController.UpdateDepartmentRequestBody());
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department() {{
                setId(1L);
            }}));
            Mockito.when(departmentRepository.existsByCode("code")).thenReturn(true);
            controller.updateDepartment(1L, new DepartmentController.UpdateDepartmentRequestBody() {{
                setCode("code");
            }});
        });
    }

    @Test
    void updateDepartment_success_update_name() {
        Department department = new Department() {{
            setId(1L);
        }};
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        controller.updateDepartment(1L, new DepartmentController.UpdateDepartmentRequestBody() {{
            setName("name");
        }});

        Mockito.verify(departmentRepository).save(department);
    }

    @Test
    void updateDepartment_success_update_code() {
        Department department = new Department() {{
            setId(1L);
        }};
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(departmentRepository.existsByCode("code")).thenReturn(false);
        controller.updateDepartment(1L, new DepartmentController.UpdateDepartmentRequestBody() {{
            setCode("code");
        }});

        Mockito.verify(departmentRepository).save(department);
    }

    @Test
    void deleteDepartment_BadRequestException() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.empty());
            controller.deleteDepartment(1L);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department() {{
                setId(1L);
                setType(DepartmentType.COMPANY);
            }}));
            controller.deleteDepartment(1L);
        });
        Assertions.assertThrows(BadRequestException.class, () -> {
            Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(new Department() {{
                setId(1L);
                setType(DepartmentType.DEPARTMENT);
            }}));
            Mockito.when(departmentRepository.existsByParentId(1L)).thenReturn(true);
            controller.deleteDepartment(1L);
        });
    }

    @Test
    void deleteDepartment_success() {
        Department department = new Department() {{
            setId(1L);
            setType(DepartmentType.DEPARTMENT);
        }};
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));
        Mockito.when(departmentRepository.existsByParentId(1L)).thenReturn(false);

        controller.deleteDepartment(1L);

        Mockito.verify(memberDepartmentRepository).deleteByDepartment_Id(department.getId());
        Mockito.verify(departmentRepository).delete(department);
    }
}