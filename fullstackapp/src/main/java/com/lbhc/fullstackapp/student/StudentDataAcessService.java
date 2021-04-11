package com.lbhc.fullstackapp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class StudentDataAcessService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDataAcessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> selectAllStudent(){
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, mapStudentFromDb());
    }

    int insertStudent(UUID studentId, Student student) {
        String sql = "" +
                "INSERT INTO student " +
                "(student_id, first_name, last_name, email, gender) " +
                " VALUES (?, ?, ?, ?, ?::gender)";
        return jdbcTemplate.update(
                sql,
                studentId,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender().name().toUpperCase()
        );

    }

    @SuppressWarnings("ConstantConditions")
    boolean isEmailTaken(String email){
        String query = "" +
                "SELECT EXISTS (" +
                "SELECT 1 " +
                "FROM student " +
                "WHERE email = ?)";

        return jdbcTemplate.queryForObject(
                query,
                (resultSet, i) -> resultSet.getBoolean(1),
                email);
    }

    private RowMapper<Student> mapStudentFromDb() {
        return (resultSet, i) -> {
            UUID studentId = UUID.fromString(resultSet.getString("student_id"));
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            Student.Gender gender = Student.Gender.valueOf(resultSet.getString("gender").toUpperCase());

            return new Student(
                    studentId,
                    firstName,
                    lastName,
                    email,
                    gender
            );
        };
    }


}
