package rikkei.academy.repository;

import rikkei.academy.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findALl();
    List<Student> findByName(String name);
    List<Student> findByNameStatic(String name);

    Student findById(Long id);

    void save(Student student);

    void deleteById(Long id);
}
