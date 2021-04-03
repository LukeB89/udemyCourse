package com.lbhc.fullstackapp.student;


import com.lbhc.fullstackapp.EmailValidator;
import com.lbhc.fullstackapp.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final  StudentDataAcessService studentDataAcessService;
    private final EmailValidator emailValidator;

    @Autowired
    public StudentService(StudentDataAcessService studentDataAcessService, EmailValidator emailValidator) {
        this.studentDataAcessService = studentDataAcessService;
        this.emailValidator = emailValidator;
    }

    public List<Student> getAllStudents() {

        return studentDataAcessService.selectAllStudent();
    }

    void addNewStudent(Student student) {
        addNewStudent(null, student);
    }
    void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId =  Optional.ofNullable(studentId)
                .orElse(UUID.randomUUID());

        // TODO Validate Email
        if (!emailValidator.test(student.getEmail())){
            throw new ApiRequestException(student.getEmail() + " is not valid");
        }
        // TODO: Verify that email is not taken
        if (studentDataAcessService.isEmailTaken(student.getEmail())){
            throw new ApiRequestException(student.getEmail() + " is taken");
        }

        studentDataAcessService.insertStudent(newStudentId, student);
    }
}
